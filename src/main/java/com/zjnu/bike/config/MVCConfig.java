package com.zjnu.bike.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

/**
 * 配置文件入口
 * 从/src/main/resources/application.properties读取具体数值
 * @author ChenTao
 * @date 2015年7月21日下午10:55:49
 */
@Configuration
@Getter
public class MVCConfig {

	@Value("${developer.mode}")
	private Boolean developerMode;
}
