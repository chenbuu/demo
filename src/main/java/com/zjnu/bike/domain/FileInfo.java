package com.zjnu.bike.domain;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 文件信息
 * 底层对象不关联外键
 * 注意：修改属性时候需要维护Dto
 * @author ChenTao
 * @date 2015年7月20日下午10:13:46
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class FileInfo {

	@Id
	private String id;

	/**
	 * 操作人User Id
	 */
	@JsonIgnore
	private String operatorId;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 创建时间
	 */
	@JsonIgnore
	private Date createTime;

	/**
	 * 文件名称
	 */
	@JsonIgnore
	private String fileName;

	/**
	 * 文件路径
	 */
	@JsonIgnore
	private String fileUrl;

}
