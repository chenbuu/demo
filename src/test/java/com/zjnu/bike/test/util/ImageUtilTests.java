package com.zjnu.bike.test.util;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zjnu.bike.Application;
import com.zjnu.bike.util.ImageUtil;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

/**
 * 
 * @author ChenTao
 * @date 2015年12月1日下午3:07:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class ImageUtilTests {

	@Test
	@Ignore
	public void test() throws Exception {
		System.out.println("-------------------------------");
		String url = "C:/Users/tao/Desktop/测试图片/1095413.png";
		log.debug("{}", url);
		ImageUtil.thumbnailImage(url, 64, 64);

		System.out.println("-------------------------------");
	}

	@Test
	public void test2() throws Exception {
		System.out.println("-------------------------------");
		String url = "C:/Users/tao/Desktop/测试图片/1095413.png";
		String url2 = "C:/Users/tao/Desktop/测试图片/新建文件夹/nnn.png";
		log.debug("{}", url);
		Thumbnails.of(url).size(256, 256).outputFormat("jpg").outputQuality(0.1).toFile(url2);
		//Thumbnails.of(url).size(256, 256).outputFormat("jpg").outputQuality(0.2).toFile(url2);

		System.out.println("-------------------------------");
	}

}
