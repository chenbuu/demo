package com.zjnu.bike.domain;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zjnu.bike.enums.GenderEnum;
import com.zjnu.bike.enums.RoleEnum;
import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 用户
 * 底层对象不关联外键
 * 注意：修改属性时候需要维护Dto
 * @author ChenTao
 * @date 2015年11月18日下午9:41:14
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private String id;

	/**
	 * 头像FileInfo Id
	 */
	private String headPortraitId;

	/**
	 * 角色RoleEnum
	 */
	@JsonIgnore
	private RoleEnum role;

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
	 * 登录名称
	 */
	@JsonIgnore
	private String userName;

	/**
	 * 密码
	 */
	@JsonIgnore
	private String password;

	/**
	 * 邮箱
	 */
	@JsonIgnore
	private String email;

	/**
	 * 昵称姓名
	 */
	private String nickName;

	/**
	 * 真实姓名
	 */
	@JsonIgnore
	private String realName;

	/**
	 * 性别GenderEnum
	 */
	@JsonIgnore
	private GenderEnum gender;

	/**
	 * 电话
	 */
	@JsonIgnore
	private String phoneNumber;

	/**
	 * 电话短号
	 */
	@JsonIgnore
	private String phoneShorter;
	
	/**
	 * 身份证
	 */
	@JsonIgnore
	private String identityCard;

	/**
	 * 学号或工号
	 */
	@JsonIgnore
	private String jobNumber;

	/**
	 * 用户联系地址
	 */
	@JsonIgnore
	private String contactAddr;

	/**
	 * 邮编
	 */
	@JsonIgnore
	private String zipcode;

	/**
	 * 最近一次修改时间
	 */
	@JsonIgnore
	private Date modifyTime;

	/**
	 * 用户描述
	 */
	@JsonIgnore
	private String description;

}
