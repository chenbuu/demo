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

import com.zjnu.bike.domain.DrivingSchool;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.DrivingSchoolRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/drivingSchool")
public class DrivingSchoolController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private DrivingSchoolRepository drivingSchoolRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listDrivingSchool")
	public Page<DrivingSchool> listDrivingSchool(DrivingSchool drivingSchool, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", drivingSchool);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.drivingSchoolRepository.findAll(drivingSchool, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findDrivingSchool")
	public DrivingSchool findDrivingSchool(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.drivingSchoolRepository.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteDrivingSchool")
	public DrivingSchool deleteDrivingSchool(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			DrivingSchool old = this.drivingSchoolRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.drivingSchoolRepository.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertDrivingSchool")
	public DrivingSchool insertDrivingSchool(DrivingSchool drivingSchool, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", drivingSchool);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (drivingSchool != null) {
			return this.drivingSchoolRepository.insert(drivingSchool);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveDrivingSchool")
	public DrivingSchool saveDrivingSchool(DrivingSchool drivingSchool, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", drivingSchool);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (drivingSchool != null) {
			return this.drivingSchoolRepository.save(drivingSchool);
		}
		return null;
	}

}
