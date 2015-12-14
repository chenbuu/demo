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
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.zjnu.bike.Application;
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
public class SampleHttpCrawlerTests {

	@Autowired
	private CampusNewsClient campusNewsClient;

	@Test
	public void test() throws Exception {
		System.out.println("-------------------------------");

		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient.getPage("http://news.163.com/special/00011K6L/rss_gn.xml");

		/*System.out.println("---------------标题----------------");
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
		System.out.println("-------------------------------");*/

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

}
