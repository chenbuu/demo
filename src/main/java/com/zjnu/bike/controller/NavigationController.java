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

import com.zjnu.bike.domain.Navigation;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.NavigationRepository;
import com.zjnu.bike.security.SessionSecurity;
import com.zjnu.bike.service.NavigationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/navigation")
public class NavigationController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private NavigationService navigationService;

	@Autowired
	private NavigationRepository navigationRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listNavigation")
	public Page<Navigation> listNavigation(Navigation navigation, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", navigation);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.navigationService.findAll(navigation, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findNavigation")
	public Navigation findNavigation(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.navigationService.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNavigation")
	public Navigation deleteNavigation(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			Navigation old = this.navigationService.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.navigationService.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertNavigation")
	public Navigation insertNavigation(Navigation navigation, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", navigation);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (navigation != null) {
			return this.navigationService.insert(navigation);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveNavigation")
	public Navigation saveNavigation(Navigation navigation, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", navigation);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (navigation != null) {
			return this.navigationService.save(navigation);
		}
		return null;
	}

}
