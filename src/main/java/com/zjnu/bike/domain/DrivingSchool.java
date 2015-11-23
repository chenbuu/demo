package com.zjnu.bike.domain;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 驾校
 * @author ChenTao
 * @date 2015年11月19日上午1:46:23
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class DrivingSchool {

	@Id
	private String id;

	/**
	 * 操作人User
	 */
	@DBRef
	private User operator;

	/**
	 * 图片List "FileInfo"
	 */
	@DBRef
	private List<FileInfo> images;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

}
