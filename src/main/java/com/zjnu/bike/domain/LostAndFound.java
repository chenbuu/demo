package com.zjnu.bike.domain;

import java.io.Serializable;
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
 * 失误招领
 * @author ChenTao
 * @date 2015年11月19日上午1:45:33
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class LostAndFound implements Serializable {

	private static final long serialVersionUID = -5429446501180027845L;

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
