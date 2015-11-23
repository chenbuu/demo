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

import com.zjnu.bike.domain.StudyGuide;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.StudyGuideRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/studyGuide")
public class StudyGuideController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private StudyGuideRepository studyGuideRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status content title operator createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listStudyGuide")
	public Page<StudyGuide> listStudyGuide(StudyGuide studyGuide, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map)
			throws Exception {
		log.debug("{}", studyGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.studyGuideRepository.findAll(studyGuide, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findStudyGuide")
	public StudyGuide findStudyGuide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return this.studyGuideRepository.findOne(id);
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteStudyGuide")
	public StudyGuide deleteStudyGuide(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			StudyGuide old = this.studyGuideRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return this.studyGuideRepository.save(old);
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertStudyGuide")
	public StudyGuide insertStudyGuide(StudyGuide studyGuide, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", studyGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (studyGuide != null) {
			return this.studyGuideRepository.insert(studyGuide);
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveStudyGuide")
	public StudyGuide saveStudyGuide(StudyGuide studyGuide, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", studyGuide);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (studyGuide != null) {
			return this.studyGuideRepository.save(studyGuide);
		}
		return null;
	}

}
