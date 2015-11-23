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

import com.zjnu.bike.domain.Schedule;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.ScheduleRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private ScheduleRepository scheduleRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listSchedule")
	public Page<Schedule> listSchedule(Schedule schedule, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", schedule);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.scheduleRepository.findAll(schedule, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findSchedule")
	public Schedule findSchedule(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.scheduleRepository.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteSchedule")
	public Schedule deleteSchedule(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			Schedule old = this.scheduleRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.scheduleRepository.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertSchedule")
	public Schedule insertSchedule(Schedule schedule, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", schedule);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (schedule != null) {
			return this.scheduleRepository.insert(schedule);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveSchedule")
	public Schedule saveSchedule(Schedule schedule, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", schedule);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (schedule != null) {
			return this.scheduleRepository.save(schedule);
		}
		return null;
	}

}
