package com.zjnu.bike.webclient;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.zjnu.bike.config.Upload;
import com.zjnu.bike.domain.CampusNews;
import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.enums.FileTypeEnum;
import com.zjnu.bike.enums.NewsTypeEnum;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.CampusNewsRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ChenTao
 * @date 2015年12月13日下午1:32:43
 */
@Service
@Slf4j
public class CampusNewsClient {

	@Autowired
	private CampusNewsRepository campusNewsRepository;

	@Autowired
	private Upload upload;

	/**
	 * 校内新闻详情抓取
	 * @author ChenTao
	 * @date 2015年12月13日下午2:35:21
	 */
	public Boolean detailHandler() throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		try {
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			String url = "http://www.zjnu.edu.cn/news/common/article_show.aspx?article_id=";
			PageRequest pageRequest = new PageRequest(0, 1, Direction.DESC, "border");
			List<CampusNews> cList = campusNewsRepository.findAll(pageRequest).getContent();
			long id = 18000;
			if (cList != null && cList.size() > 0 && cList.get(0).getBorder() != null) {
				id = cList.get(0).getBorder();
				id++;
			}
			int failCount = 0;
			for (; id < id + 10000; id++) {
				try {
					if (failCount > 99) {
						break;
					}
					String newPageUrl = url + id;
					HtmlPage page = webClient.getPage(newPageUrl);
					CampusNews campusNews = new CampusNews();
					//标题
					HtmlSpan span1 = (HtmlSpan) page.getElementById("mytitle");
					campusNews.setTitle(span1.asText());
					//正文
					HtmlSpan span2 = (HtmlSpan) page.getElementById("mycontent");
					campusNews.setContent(span2.asText());
					//图片
					List<FileInfo> images = new ArrayList<FileInfo>();
					DomNodeList<HtmlElement> elements = span2.getElementsByTagName("img");
					int i = 1;
					for (HtmlElement element : elements) {
						HtmlImage htmlImage = (HtmlImage) element;
						BufferedImage bufferedImage = htmlImage.getImageReader().read(0);
						ByteArrayOutputStream outImage = new ByteArrayOutputStream();
						ImageIO.write(bufferedImage, "jpg", outImage);
						outImage.flush();
						ByteArrayInputStream inImage = new ByteArrayInputStream(outImage.toByteArray());
						String firstName = htmlImage.getAttribute("src");
						if (firstName.indexOf("/") > -1) {
							firstName = firstName.substring(firstName.lastIndexOf("/") + 1);
						}
						if (i == 1) {
							//获取缩略图
							List<FileInfo> uploads = upload.uploadImage(inImage, bufferedImage, firstName, null, null);
							images.add(uploads.get(0));
							campusNews.setTitleImage(uploads.get(1));
						} else {
							FileInfo uploadBigImg = upload.upload(inImage, firstName, null, FileTypeEnum.BigImage, null);
							images.add(uploadBigImg);
						}
						i++;
					}
					campusNews.setImages(images);
					campusNews.setNewsType(NewsTypeEnum.In);
					campusNews.setStatus(StatusEnum.Use);
					campusNews.setCreateTime(new Date());
					campusNews.setBorder(id);
					log.debug("{}", campusNews);
					campusNewsRepository.save(campusNews);
					failCount = 0;
				} catch (Exception e) {
					failCount++;
				}
			}
		} finally {
			webClient.close();
		}
		return true;
	}
}
