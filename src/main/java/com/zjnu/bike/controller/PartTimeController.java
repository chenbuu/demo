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

import com.zjnu.bike.domain.PartTime;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.PartTimeRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/partTime")
public class PartTimeController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private PartTimeRepository partTimeRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listPartTime")
	public Page<PartTime> listPartTime(PartTime partTime, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", partTime);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.partTimeRepository.findAll(partTime, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findPartTime")
	public PartTime findPartTime(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.partTimeRepository.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePartTime")
	public PartTime deletePartTime(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			PartTime old = this.partTimeRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.partTimeRepository.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertPartTime")
	public PartTime insertPartTime(PartTime partTime, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", partTime);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (partTime != null) {
			return this.partTimeRepository.insert(partTime);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/savePartTime")
	public PartTime savePartTime(PartTime partTime, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", partTime);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (partTime != null) {
			return this.partTimeRepository.save(partTime);
		}
		return null;
	}

}
