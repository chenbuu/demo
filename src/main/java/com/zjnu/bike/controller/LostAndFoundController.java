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

import com.zjnu.bike.domain.LostAndFound;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.LostAndFoundRepository;
import com.zjnu.bike.security.SessionSecurity;
import com.zjnu.bike.service.LostAndFoundService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/lostAndFound")
public class LostAndFoundController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private LostAndFoundService lostAndFoundService;

	@Autowired
	private LostAndFoundRepository lostAndFoundRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listLostAndFound")
	public Page<LostAndFound> listLostAndFound(LostAndFound lostAndFound, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", lostAndFound);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.lostAndFoundService.findAll(lostAndFound, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findLostAndFound")
	public LostAndFound findLostAndFound(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.lostAndFoundService.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteLostAndFound")
	public LostAndFound deleteLostAndFound(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			LostAndFound old = this.lostAndFoundService.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.lostAndFoundService.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertLostAndFound")
	public LostAndFound insertLostAndFound(LostAndFound lostAndFound, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", lostAndFound);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (lostAndFound != null) {
			return this.lostAndFoundService.insert(lostAndFound);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveLostAndFound")
	public LostAndFound saveLostAndFound(LostAndFound lostAndFound, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", lostAndFound);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (lostAndFound != null) {
			return this.lostAndFoundService.save(lostAndFound);
		}
		return null;
	}

}
