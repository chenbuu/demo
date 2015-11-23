package com.zjnu.bike.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjnu.bike.util.InputStreamUtil;

/**
 * 提供图片验证码
 * 
 * @author ChenTao
 * @date 2015年3月17日上午12:07:43
 */
@Controller
@RequestMapping("/securitycode")
public class SecurityCodeController {

	/**
	 * 获取图片
	 * @author ChenTao
	 * @date 2015年8月6日上午8:36:54
	 */
	@RequestMapping(value = "")
	public ResponseEntity<byte[]> getCodeImage(HttpSession session, ModelMap map) throws IOException {
		// 如果开启Hard模式，可以不区分大小写
		// SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard,false).toLowerCase();
		// 获取默认难度和长度的验证码
		String securityCode = SecurityCodeUtil.getSecurityCode();
		ByteArrayInputStream imageStream = SecurityCodeUtil.getImageAsInputStream(securityCode);
		// 放入session中
		session.setAttribute("SESSION_SECURITY_CODE", securityCode);
		// 构建ResponseEntity
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(InputStreamUtil.InputStreamTOByte(imageStream), headers, HttpStatus.CREATED);
	}
}
