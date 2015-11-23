package com.zjnu.bike.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjnu.bike.config.MVCConfig;

/**
 * 通过session判断权限
 * @author ChenTao
 * @date 2015年8月5日上午10:02:03
 */
@Service
public class SessionSecurity {

	@Autowired
	public MVCConfig config;

	/**
	 * 校核页面跳转权限
	 * @author ChenTao
	 * @date 2015年8月10日上午9:05:42
	 */
	public Boolean getPage(HttpSession session) {
		if (config.getDeveloperMode()) {
			return true;
		}
		if (session.getAttribute("user") != null) {
			return true;
		}
		return false;
	}

	/**
	 * 校核方法权限
	 * @author ChenTao
	 * @date 2015年8月10日上午9:26:12
	 */
	public Boolean getMethod(HttpSession session) {
		if (config.getDeveloperMode()) {
			return true;
		}
		if (session.getAttribute("user") != null) {
			return true;
		}
		return false;
	}
}