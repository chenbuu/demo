package com.zjnu.bike.domain;

import java.io.Serializable;
import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 操作日志
 * @author ChenTao
 * @date 2015年7月20日下午10:13:26
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class ManipulateLog implements Serializable {

	private static final long serialVersionUID = 6523931250661464934L;

	@Id
	private String id;

	/**
	 * 操作人User
	 */
	@DBRef
	private User operator;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 方法名称
	 */
	private String methodName;

	/**
	 * 方法类型
	 */
	private String methodType;

	/**
	 * 数据
	 */
	private String methodData;

}
