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
 * 地图导航
 * @author ChenTao
 * @date 2015年11月19日上午1:45:33
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Navigation implements Serializable {

	private static final long serialVersionUID = -3067811187673375870L;

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
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

}
