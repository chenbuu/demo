package com.zjnu.bike.test.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zjnu.bike.Application;
import com.zjnu.bike.util.ImageUtil;

import lombok.extern.slf4j.Slf4j;

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
	public void test() throws Exception {
		System.out.println("-------------------------------");
		String url = "C:/Users/tao/Desktop/测试图片/1095413.png";
		log.debug("{}", url);
		ImageUtil.thumbnailImage(url, 64, 64);

		System.out.println("-------------------------------");
	}

}
