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

import com.zjnu.bike.domain.Ride;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.RideRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ride")
public class RideController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private RideRepository rideRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listRide")
	public Page<Ride> listRide(Ride ride, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", ride);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.rideRepository.findAll(ride, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findRide")
	public Ride findRide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.rideRepository.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRide")
	public Ride deleteRide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			Ride old = this.rideRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.rideRepository.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertRide")
	public Ride insertRide(Ride ride, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", ride);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (ride != null) {
			return this.rideRepository.insert(ride);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRide")
	public Ride saveRide(Ride ride, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", ride);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (ride != null) {
			return this.rideRepository.save(ride);
		}
		return null;
	}

}
