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

import com.zjnu.bike.domain.PlayGuide;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.PlayGuideRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/playGuide")
public class PlayGuideController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private PlayGuideRepository playGuideRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listPlayGuide")
	public Page<PlayGuide> listPlayGuide(PlayGuide playGuide, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", playGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.playGuideRepository.findAll(playGuide, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findPlayGuide")
	public PlayGuide findPlayGuide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.playGuideRepository.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePlayGuide")
	public PlayGuide deletePlayGuide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			PlayGuide old = this.playGuideRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.playGuideRepository.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertPlayGuide")
	public PlayGuide insertPlayGuide(PlayGuide playGuide, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", playGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (playGuide != null) {
			return this.playGuideRepository.insert(playGuide);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/savePlayGuide")
	public PlayGuide savePlayGuide(PlayGuide playGuide, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", playGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (playGuide != null) {
			return this.playGuideRepository.save(playGuide);
		}
		return null;
	}

}
