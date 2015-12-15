package com.zjnu.bike.test.crawler;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.zjnu.bike.Application;
import com.zjnu.bike.domain.CampusNews;
import com.zjnu.bike.enums.NewsTypeEnum;
import com.zjnu.bike.repository.CampusNewsRepository;
import com.zjnu.bike.webclient.CampusNewsClient;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ChenTao
 * @date 2015年12月1日下午3:06:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class CampusNewsClientTests {

	@Autowired
	private CampusNewsClient campusNewsClient;

	@Autowired
	private CampusNewsRepository campusNewsRepository;
	
	@Test
	@Ignore
	public void test() throws Exception {
		System.out.println("-------------------------------");

		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient.getPage("http://news.163.com/domestic/");
		//DomNodeList<HtmlElement> elements = page.getElementBy

		System.out.println("---------------标题----------------");
		DomNodeList<DomNode> domNodes = page.querySelectorAll(".item-top");
		//log.debug("{}", domNodes);
		for (DomNode domNode : domNodes) {
			HtmlDivision htmlDivision = (HtmlDivision) domNode;
			DomNodeList<HtmlElement> aElements = htmlDivision.getElementsByTagName("a");
			HtmlAnchor htmlAnchor = (HtmlAnchor) aElements.get(0);
			//HTMLHeadingElement htmlHeading2 = (HTMLHeadingElement) htmlDivision.getElementsByTagName("h2");
			//HtmlAnchor htmlAnchor = (HtmlAnchor) htmlDivision.getElementsByTagName("a");
			log.debug("{}", htmlAnchor.asText());
			log.debug("{}", htmlAnchor.getAttribute("href"));

			DomNodeList<HtmlElement> pElements = htmlDivision.getElementsByTagName("p");
			HtmlParagraph htmlParagraph = (HtmlParagraph) pElements.get(0);
			log.debug("{}", htmlParagraph.asText());

			DomNodeList<HtmlElement> iEelements = htmlDivision.getElementsByTagName("img");
			for (HtmlElement iEelement : iEelements) {
				log.debug("{}", iEelement.getAttribute("src"));
			}

			String detailUrl = htmlAnchor.getAttribute("href");
			if (detailUrl.equals("http://news.163.com/15/1215/17/BAT2L8RB00014JB6.html#f=dlist")) {
				HtmlPage detailPage = webClient.getPage(detailUrl);
				System.out.println("---------------正文----------------");
				DomElement endTextElement = detailPage.getElementById("endText");
				log.debug("{}", endTextElement.asText());

				System.out.println("---------------图片----------------");
				DomNodeList<DomNode> imgNodes = endTextElement.querySelectorAll(".f_center");
				for (DomNode imgNode : imgNodes) {
					HtmlParagraph imgpara = (HtmlParagraph) imgNode;
					DomNodeList<HtmlElement> endImgs = imgpara.getElementsByTagName("img");
					for (HtmlElement endImg : endImgs) {
						log.debug("{}", endImg.getAttribute("src"));
					}
				}
			}
		}
		webClient.close();
		System.out.println("-------------------------------");
	}

	@Test
	@Ignore
	public void test3() throws Exception {
		System.out.println("-------------------------------");

		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient.getPage("http://www.zjnu.edu.cn/news/common/article_show.aspx?article_id=19285");

		System.out.println("---------------标题----------------");
		HtmlSpan span1 = (HtmlSpan) page.getElementById("mytitle");
		System.out.println(span1.asText());
		System.out.println("-------------------------------");

		System.out.println("---------------正文----------------");
		HtmlSpan span2 = (HtmlSpan) page.getElementById("mycontent");
		System.out.println(span2.asText());
		System.out.println("-------------------------------");

		System.out.println("---------------图片----------------");
		DomNodeList<HtmlElement> elements = span2.getElementsByTagName("img");
		for (HtmlElement element : elements) {
			System.out.println(element.getAttribute("src"));
		}
		//log.debug("{}", elements);
		System.out.println("-------------------------------");

		webClient.close();
		System.out.println("-------------------------------");
	}

	@Test
	@Ignore
	public void test2() throws Exception {
		System.out.println("-------------------------------");
		campusNewsClient.detailHandler();
		System.out.println("-------------------------------");
	}
	
	@Test
	public void test4() throws Exception {
		System.out.println("-------------------------------");
		/*PageRequest pageRequest = new PageRequest(0, 1000);
		CampusNews search = new CampusNews();
		search.setNewsType(NewsTypeEnum.Out);
		List<CampusNews> cList = campusNewsRepository.findAll(search, pageRequest).getContent();
		campusNewsRepository.delete(cList);*/
		campusNewsClient.outDetailHandler();
		System.out.println("-------------------------------");
	}

}
