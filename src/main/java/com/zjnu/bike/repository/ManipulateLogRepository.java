package com.zjnu.bike.repository;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.zjnu.bike.domain.ManipulateLog;
import com.zjnu.bike.domain.QManipulateLog;

public interface ManipulateLogRepository extends BaseRepository<ManipulateLog, String> {

	default Page<ManipulateLog> findAll(@Valid ManipulateLog mod, Pageable pageable) {
		if (mod == null) {
			return findAll(pageable);
		}
		BooleanBuilder builder = new BooleanBuilder();
		QManipulateLog q = QManipulateLog.manipulateLog;
		if (mod.getStatus() != null) {
			builder.and(q.status.eq(mod.getStatus()));
		}
		if (!StringUtils.isBlank(mod.getMethodData())) {
			builder.and(q.methodData.contains(mod.getMethodData()));
		}
		if (!StringUtils.isBlank(mod.getMethodType())) {
			builder.and(q.methodType.contains(mod.getMethodType()));
		}
		if (!StringUtils.isBlank(mod.getMethodName())) {
			builder.and(q.methodName.contains(mod.getMethodName()));
		}
		if (mod.getOperator() != null && mod.getOperator().getId() != null) {
			builder.and(q.operator().id.eq(mod.getOperator().getId()));
		}
		if (mod.getCreateTime() != null) {
			builder.and(q.createTime.goe(mod.getCreateTime()));
		}
		return findAll(builder.getValue(), pageable);
	}
}
