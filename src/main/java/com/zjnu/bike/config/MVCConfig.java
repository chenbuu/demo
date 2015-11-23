package com.zjnu.bike.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件入口
 * 从/src/main/resources/application.properties读取具体数值
 * @author ChenTao
 * @date 2015年7月21日下午10:55:49
 */
@Configuration
@Getter
public class MVCConfig {

	@Value("${upload.path}")
	private String uploadPath;
	
	@Value("${developer.mode}")
	private Boolean developerMode;
}
