package com.zjnu.bike.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.zjnu.bike.enums.NewsTypeEnum;
import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 校园新闻
 * @author ChenTao
 * @date 2015年11月19日上午1:45:33
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class CampusNews implements Serializable {

	private static final long serialVersionUID = 6542387358181251748L;

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
	 * 标题图片 "FileInfo"
	 */
	@DBRef
	private FileInfo titleImage;

	/**
	 * 类型NewsTypeEnum
	 */
	private NewsTypeEnum newsType;

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
