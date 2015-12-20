package com.zjnu.bike.test.crawler;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.zjnu.bike.Application;
import com.zjnu.bike.enums.CityEnum;
import com.zjnu.bike.webclient.PlayGuideClient;

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
public class PlayGuideClientTests {

	@Autowired
	private PlayGuideClient playGuideClient;

	@Test
	@Ignore
	public void test01() throws Exception {
		System.out.println("-------------------------------");
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		try {
			WebClientOptions options = webClient.getOptions();
			options.setThrowExceptionOnFailingStatusCode(false);
			options.setThrowExceptionOnScriptError(false);
			options.setCssEnabled(false);
			options.setJavaScriptEnabled(true);
			options.setTimeout(50000);
			//webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			HtmlPage pageOrgin = webClient.getPage("http://lvyou.baidu.com/jinhua/jingdian");
			Thread.sleep(5000);
			DomNodeList<DomNode> pageNodes = pageOrgin.querySelectorAll(".pagination");
			HtmlDivision pageDiv = (HtmlDivision) pageNodes.get(0);
			DomNodeList<HtmlElement> liElements = pageDiv.getElementsByTagName("li");
			Integer pageSize = liElements.size() - 1;
			for (int pageNow = 0; pageNow < pageSize; pageNow++) {
				DomNodeList<HtmlElement> pageAnchors = liElements.get(pageNow).getElementsByTagName("a");
				HtmlAnchor pageAnchor = (HtmlAnchor) pageAnchors.get(0);
				HtmlPage page = pageAnchor.click();
				Thread.sleep(10000);
				DomElement jViewDom = page.getElementById("J-view-list-container");
				DomNodeList<HtmlElement> lis = jViewDom.getElementsByTagName("li");
				for (HtmlElement li : lis) {
					DomNodeList<DomNode> titleNodes = li.querySelectorAll(".title");
					HtmlAnchor titleAnchor = (HtmlAnchor) titleNodes.get(0);
					System.out.println("---------------标题----------------");
					log.debug("{}", titleAnchor.asText());

					DomNodeList<DomNode> picNodes = li.querySelectorAll(".pic");
					HtmlAnchor picAnchor = (HtmlAnchor) picNodes.get(0);
					System.out.println("---------------详情URL----------------");
					String detailUrl = "http://lvyou.baidu.com" + picAnchor.getAttribute("href");
					log.debug("{}", detailUrl);
					DomNodeList<HtmlElement> imgEelements = picAnchor.getElementsByTagName("img");
					for (HtmlElement imgEelement : imgEelements) {
						System.out.println("---------------图片----------------");
						log.debug("{}", imgEelement.getAttribute("src"));
					}

					DomNodeList<DomNode> sumNodes = li.querySelectorAll(".view-userSays");
					HtmlDivision sumDiv = (HtmlDivision) sumNodes.get(0);
					DomNodeList<HtmlElement> sumElements = sumDiv.getElementsByTagName("p");
					HtmlParagraph sumPara = (HtmlParagraph) sumElements.get(0);
					System.out.println("---------------摘要----------------");
					log.debug("{}", sumPara.asText());
				}
			}
		} finally {
			webClient.close();
		}
		System.out.println("-------------------------------");
	}

	@Test
	public void test02() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/jinhua/jingdian", CityEnum.Jinhua);
	}

	@Test
	public void test03() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/hangzhou/jingdian", CityEnum.Hangzhou);
	}

	@Test
	public void test04() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/huzhou/jingdian", CityEnum.Huzhou);
	}

	@Test
	public void test05() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/jiaxing/jingdian", CityEnum.Jiaxing);
	}

	@Test
	public void test06() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/lishui/jingdian", CityEnum.Lishui);
	}

	@Test
	public void test07() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/ningbo/jingdian", CityEnum.Ningbo);
	}

	@Test
	public void test08() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/quzhou/jingdian", CityEnum.Quzhou);
	}

	@Test
	public void test09() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/shaoxing/jingdian", CityEnum.Shaoxing);
	}

	@Test
	public void test10() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/zhejiangtaizhou/jingdian", CityEnum.Taizhou);
	}

	@Test
	public void test11() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/wenzhou/jingdian", CityEnum.Wenzhou);
	}

	@Test
	public void test12() throws Exception {
		playGuideClient.detailHandler("http://lvyou.baidu.com/zhoushan/jingdian", CityEnum.Zhoushan);
	}

}
