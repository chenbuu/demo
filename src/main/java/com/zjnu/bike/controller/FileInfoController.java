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

import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.dto.FileInfoDto;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.FileInfoRepository;
import com.zjnu.bike.security.SessionSecurity;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/fileInfo")
public class FileInfoController {

	@Autowired
	private SessionSecurity sessionSecurity;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	/**
	 * 表格分页查询
	 * 可选参数：status operatorId fileName fileUrl createTime
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/listFileInfo")
	public Page<FileInfo> listFileInfo(FileInfo fileInfo, @PageableDefault(size = 10) Pageable pageable, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", fileInfo);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		return this.fileInfoRepository.findAll(fileInfo, pageable);
	}

	/**
	 * 单个查询
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/findFileInfo")
	public FileInfoDto findFileInfo(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			return new FileInfoDto(this.fileInfoRepository.findOne(id));
		}
		return null;
	}

	/**
	 * 删除
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteFileInfo")
	public FileInfoDto deleteFileInfo(String id, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", id);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (!StringUtils.isBlank(id)) {
			FileInfo old = this.fileInfoRepository.findOne(id);
			old.setStatus(StatusEnum.Unused);
			return new FileInfoDto(this.fileInfoRepository.save(old));
		}
		return null;
	}

	/**
	 * 新增
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/insertFileInfo")
	public FileInfoDto insertFileInfo(FileInfo fileInfo, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", fileInfo);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (fileInfo != null) {
			return new FileInfoDto(this.fileInfoRepository.insert(fileInfo));
		}
		return null;
	}

	/**
	 * 修改
	 * @author ChenTao
	 * @date 2015年11月19日下午8:54:33
	 */
	@ResponseBody
	@RequestMapping(value = "/saveFileInfo")
	public FileInfoDto saveFileInfo(FileInfo fileInfo, HttpSession session, ModelMap map) throws Exception {
		log.debug("{}", fileInfo);
		if (!this.sessionSecurity.getMethod(session)) {
			throw new Exception("权限错误");
		}
		if (fileInfo != null) {
			return new FileInfoDto(this.fileInfoRepository.save(fileInfo));
		}
		return null;
	}

}
