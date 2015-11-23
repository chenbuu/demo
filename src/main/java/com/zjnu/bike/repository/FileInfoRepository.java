package com.zjnu.bike.repository;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.domain.QFileInfo;

public interface FileInfoRepository extends BaseRepository<FileInfo, String> {

	default Page<FileInfo> findAll(@Valid FileInfo mod, Pageable pageable) {
		if (mod == null) {
			return findAll(pageable);
		}
		BooleanBuilder builder = new BooleanBuilder();
		QFileInfo q = QFileInfo.fileInfo;
		if (mod.getStatus() != null) {
			builder.and(q.status.eq(mod.getStatus()));
		}
		if (mod.getOperatorId() != null && !StringUtils.isBlank(mod.getOperatorId().toString())) {
			builder.and(q.operatorId.eq(mod.getOperatorId()));
		}
		if (!StringUtils.isBlank(mod.getFileName())) {
			builder.and(q.fileName.contains(mod.getFileName()));
		}
		if (!StringUtils.isBlank(mod.getFileUrl())) {
			builder.and(q.fileUrl.contains(mod.getFileUrl()));
		}
		if (mod.getCreateTime() != null) {
			builder.and(q.createTime.goe(mod.getCreateTime()));
		}
		return findAll(builder.getValue(), pageable);
	}
}
