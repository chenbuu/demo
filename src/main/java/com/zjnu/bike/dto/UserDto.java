package com.zjnu.bike.dto;

import java.io.Serializable;
import java.util.Date;

import com.zjnu.bike.domain.User;
import com.zjnu.bike.enums.GenderEnum;
import com.zjnu.bike.enums.RoleEnum;
import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 用户Dto
 * @author ChenTao
 * @date 2015年11月18日下午9:41:14
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = 3398777341543102382L;

	public UserDto(User u) throws Exception {
		this.contactAddr = u.getContactAddr();
		this.createTime = u.getCreateTime();
		this.description = u.getDescription();
		this.email = u.getEmail();
		this.gender = u.getGender();
		this.headPortraitId = u.getHeadPortraitId();
		this.id = u.getId();
		this.identityCard = u.getIdentityCard();
		this.jobNumber = u.getJobNumber();
		this.modifyTime = u.getModifyTime();
		this.nickName = u.getNickName();
		this.phoneNumber = u.getPhoneNumber();
		this.phoneShorter = u.getPhoneShorter();
		this.realName = u.getRealName();
		this.role = u.getRole();
		this.status = u.getStatus();
		this.userName = u.getUserName();
		this.zipcode = u.getZipcode();
	}

	private String id;

	/**
	 * 头像FileInfo Id
	 */
	private String headPortraitId;

	/**
	 * 角色RoleEnum
	 */
	private RoleEnum role;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 登录名称
	 */
	private String userName;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 昵称姓名
	 */
	private String nickName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 性别GenderEnum
	 */
	private GenderEnum gender;

	/**
	 * 电话
	 */
	private String phoneNumber;

	/**
	 * 电话短号
	 */
	private String phoneShorter;

	/**
	 * 身份证
	 */
	private String identityCard;

	/**
	 * 学号或工号
	 */
	private String jobNumber;

	/**
	 * 用户联系地址
	 */
	private String contactAddr;

	/**
	 * 邮编
	 */
	private String zipcode;

	/**
	 * 最近一次修改时间
	 */
	private Date modifyTime;

	/**
	 * 用户描述
	 */
	private String description;

}
