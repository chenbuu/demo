package com.zjnu.bike.webclient;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.zjnu.bike.config.Upload;
import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.domain.PlayGuide;
import com.zjnu.bike.enums.CityEnum;
import com.zjnu.bike.enums.FileTypeEnum;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.PlayGuideRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ChenTao
 * @date 2015年12月13日下午1:32:43
 */
@Service
@Slf4j
public class PlayGuideClient {

	@Autowired
	private PlayGuideRepository playGuideRepository;

	@Autowired
	private Upload upload;

	/**
	 * 游玩抓取
	 * @author ChenTao
	 * @date 2015年12月20日下午3:45:23
	 */
	public Boolean detailHandler(String url, CityEnum city) throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		try {
			WebClientOptions options = webClient.getOptions();
			options.setThrowExceptionOnFailingStatusCode(false);
			options.setThrowExceptionOnScriptError(false);
			options.setCssEnabled(false);
			options.setJavaScriptEnabled(true);
			options.setTimeout(50000);
			//String url = "http://lvyou.baidu.com/jinhua/jingdian";
			HtmlPage pageOrgin = webClient.getPage(url);
			Thread.sleep(10000);
			DomNodeList<DomNode> pageNodes = pageOrgin.querySelectorAll(".pagination");
			HtmlDivision pageDiv = (HtmlDivision) pageNodes.get(0);
			DomNodeList<HtmlElement> liElements = pageDiv.getElementsByTagName("li");
			Integer pageSize = liElements.size() - 1;
			Thread.sleep(2000);
			for (int pageNow = 0; pageNow < pageSize; pageNow++) {
				try {
					int baiduPage = pageNow + 1;
					DomNodeList<HtmlElement> pageAnchors = liElements.get(pageNow).getElementsByTagName("a");
					HtmlAnchor pageAnchor = (HtmlAnchor) pageAnchors.get(0);
					HtmlPage page = pageAnchor.click();
					Thread.sleep(10000);
					DomElement jViewDom = page.getElementById("J-view-list-container");
					DomNodeList<HtmlElement> lis = jViewDom.getElementsByTagName("li");
					PlayGuide pSearch = new PlayGuide();
					pSearch.setCity(city);
					//pSearch.setBaiduPage(baiduPage);
					List<PlayGuide> pList = playGuideRepository.findAll(pSearch, null).getContent();
					for (HtmlElement li : lis) {
						try {
							PlayGuide playGuide = new PlayGuide();
							DomNodeList<DomNode> titleNodes = li.querySelectorAll(".title");
							HtmlAnchor titleAnchor = (HtmlAnchor) titleNodes.get(0);
							//System.out.println("---------------标题----------------");
							//log.debug("{}", titleAnchor.asText());
							String title = titleAnchor.asText();
							boolean hasTitle = false;
							for (PlayGuide p : pList) {
								if (title.equals(p.getTitle())) {
									hasTitle = true;
								}
							}
							if (hasTitle) {
								continue;
							}
							playGuide.setTitle(title);

							DomNodeList<DomNode> picNodes = li.querySelectorAll(".pic");
							HtmlAnchor picAnchor = (HtmlAnchor) picNodes.get(0);
							//System.out.println("---------------详情URL----------------");
							String detailUrl = "http://lvyou.baidu.com" + picAnchor.getAttribute("href");
							//log.debug("{}", detailUrl);
							playGuide.setContent(detailUrl);
							DomNodeList<HtmlElement> imgEelements = picAnchor.getElementsByTagName("img");
							for (HtmlElement imgEelement : imgEelements) {
								//System.out.println("---------------图片----------------");
								//log.debug("{}", imgEelement.getAttribute("src"));
								HtmlImage htmlImage = (HtmlImage) imgEelement;
								BufferedImage bufferedImage = htmlImage.getImageReader().read(0);
								ByteArrayOutputStream outImage = new ByteArrayOutputStream();
								ImageIO.write(bufferedImage, "jpg", outImage);
								outImage.flush();
								ByteArrayInputStream inImage = new ByteArrayInputStream(outImage.toByteArray());
								String firstName = htmlImage.getAttribute("src");
								if (firstName.indexOf("/") > -1) {
									firstName = firstName.substring(firstName.lastIndexOf("/") + 1);
								}
								FileInfo uploadBigImg = upload.upload(inImage, firstName, null, FileTypeEnum.BigImage, null);
								playGuide.setTitleImage(uploadBigImg);
							}

							DomNodeList<DomNode> sumNodes = li.querySelectorAll(".view-userSays");
							HtmlDivision sumDiv = (HtmlDivision) sumNodes.get(0);
							DomNodeList<HtmlElement> sumElements = sumDiv.getElementsByTagName("p");
							HtmlParagraph sumPara = (HtmlParagraph) sumElements.get(0);
							//System.out.println("---------------摘要----------------");
							//log.debug("{}", sumPara.asText());
							String sumStr = sumPara.asText();
							playGuide.setSummary(sumStr.substring(1, sumStr.length() - 1));

							playGuide.setCity(city);
							playGuide.setStatus(StatusEnum.Use);
							playGuide.setCreateTime(new Date());
							playGuide.setBaiduPage(baiduPage);
							log.debug("{}", playGuide);
							playGuideRepository.save(playGuide);
						} catch (Exception e) {

						}
					}
				} catch (Exception e) {

				}
			}
		} finally {
			webClient.close();
		}
		return true;
	}
}
