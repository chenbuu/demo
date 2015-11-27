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

import com.zjnu.bike.domain.CampusNews;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.CampusNewsRepository;
import com.zjnu.bike.security.SessionSecurity;
import com.zjnu.bike.service.CampusNewsService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/campusNews")
public class CampusNewsController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private CampusNewsService campusNewsService;

	@Autowired
	private CampusNewsRepository campusNewsRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listCampusNews")
	public Page<CampusNews> listCampusNews(CampusNews campusNews, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", campusNews);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.campusNewsService.findAll(campusNews, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findCampusNews")
	public CampusNews findCampusNews(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.campusNewsService.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCampusNews")
	public CampusNews deleteCampusNews(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			CampusNews old = this.campusNewsService.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.campusNewsService.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertCampusNews")
	public CampusNews insertCampusNews(CampusNews campusNews, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", campusNews);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (campusNews != null) {
			return this.campusNewsService.insert(campusNews);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveCampusNews")
	public CampusNews saveCampusNews(CampusNews campusNews, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", campusNews);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (campusNews != null) {
			return this.campusNewsService.save(campusNews);
		}
		return null;
	}

}
