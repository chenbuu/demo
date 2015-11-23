package com.zjnu.bike.repository;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.zjnu.bike.domain.QUser;
import com.zjnu.bike.domain.User;

public interface UserRepository extends BaseRepository<User, String> {

	List<User> findAllByUserNameAndPassword(String userName, String password);

	List<User> findAllByUserName(String userName);

	default Page<User> findAll(@Valid User mod, Pageable pageable) {
		if (mod == null) {
			return findAll(pageable);
		}
		BooleanBuilder builder = new BooleanBuilder();
		QUser q = QUser.user;
		if (mod.getStatus() != null) {
			builder.and(q.status.eq(mod.getStatus()));
		}
		if (mod.getRole() != null) {
			builder.and(q.role.eq(mod.getRole()));
		}
		if (mod.getGender() != null) {
			builder.and(q.gender.eq(mod.getGender()));
		}
		if (!StringUtils.isBlank(mod.getDescription())) {
			builder.and(q.description.contains(mod.getDescription()));
		}
		if (!StringUtils.isBlank(mod.getContactAddr())) {
			builder.and(q.contactAddr.contains(mod.getContactAddr()));
		}
		if (!StringUtils.isBlank(mod.getUserName())) {
			builder.and(q.userName.contains(mod.getUserName()));
		}
		if (!StringUtils.isBlank(mod.getNickName())) {
			builder.and(q.nickName.contains(mod.getNickName()));
		}
		if (!StringUtils.isBlank(mod.getRealName())) {
			builder.and(q.realName.contains(mod.getRealName()));
		}
		if (!StringUtils.isBlank(mod.getEmail())) {
			builder.and(q.email.eq(mod.getEmail()));
		}
		if (!StringUtils.isBlank(mod.getPhoneNumber())) {
			builder.and(q.phoneNumber.eq(mod.getPhoneNumber()));
		}
		if (!StringUtils.isBlank(mod.getIdentityCard())) {
			builder.and(q.identityCard.eq(mod.getIdentityCard()));
		}
		if (!StringUtils.isBlank(mod.getJobNumber())) {
			builder.and(q.jobNumber.eq(mod.getJobNumber()));
		}
		if (!StringUtils.isBlank(mod.getZipcode())) {
			builder.and(q.zipcode.eq(mod.getZipcode()));
		}
		if (mod.getModifyTime() != null) {
			builder.and(q.modifyTime.goe(mod.getModifyTime()));
		}
		if (mod.getCreateTime() != null) {
			builder.and(q.createTime.goe(mod.getCreateTime()));
		}
		if (mod.getHeadPortraitId() != null && !StringUtils.isBlank(mod.getHeadPortraitId().toString())) {
			builder.and(q.headPortraitId.eq(mod.getHeadPortraitId()));
		}
		return findAll(builder.getValue(), pageable);
	}
}
