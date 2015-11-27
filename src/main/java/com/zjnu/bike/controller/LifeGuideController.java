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

import com.zjnu.bike.domain.LifeGuide;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.LifeGuideRepository;
import com.zjnu.bike.security.SessionSecurity;
import com.zjnu.bike.service.LifeGuideService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/lifeGuide")
public class LifeGuideController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private LifeGuideService lifeGuideService;

	@Autowired
	private LifeGuideRepository lifeGuideRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listLifeGuide")
	public Page<LifeGuide> listLifeGuide(LifeGuide lifeGuide, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", lifeGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.lifeGuideService.findAll(lifeGuide, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findLifeGuide")
	public LifeGuide findLifeGuide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.lifeGuideService.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteLifeGuide")
	public LifeGuide deleteLifeGuide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			LifeGuide old = this.lifeGuideService.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.lifeGuideService.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertLifeGuide")
	public LifeGuide insertLifeGuide(LifeGuide lifeGuide, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", lifeGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (lifeGuide != null) {
			return this.lifeGuideService.insert(lifeGuide);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveLifeGuide")
	public LifeGuide saveLifeGuide(LifeGuide lifeGuide, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", lifeGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (lifeGuide != null) {
			return this.lifeGuideService.save(lifeGuide);
		}
		return null;
	}

}
