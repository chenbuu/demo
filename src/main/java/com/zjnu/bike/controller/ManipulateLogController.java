package com.zjnu.bike.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjnu.bike.domain.ManipulateLog;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.ManipulateLogRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/manipulateLog")
public class ManipulateLogController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private ManipulateLogRepository manipulateLogRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status methodData methodType methodName operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listManipulateLog")
	public Page<ManipulateLog> listManipulateLog(ManipulateLog manipulateLog, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", manipulateLog);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.manipulateLogRepository.findAll(manipulateLog, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findManipulateLog")
	public ManipulateLog findManipulateLog(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.manipulateLogRepository.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteManipulateLog")
	public ManipulateLog deleteManipulateLog(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			ManipulateLog old = this.manipulateLogRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.manipulateLogRepository.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertManipulateLog")
	public ManipulateLog insertManipulateLog(ManipulateLog manipulateLog, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", manipulateLog);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (manipulateLog != null) {
			return this.manipulateLogRepository.insert(manipulateLog);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveManipulateLog")
	public ManipulateLog saveManipulateLog(ManipulateLog manipulateLog, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", manipulateLog);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (manipulateLog != null) {
			return this.manipulateLogRepository.save(manipulateLog);
		}
		return null;
	}

}
