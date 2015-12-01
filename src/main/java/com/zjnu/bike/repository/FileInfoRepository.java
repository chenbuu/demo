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
		if (!StringUtils.isBlank(mod.getContentType())) {
			builder.and(q.contentType.contains(mod.getContentType()));
		}
		if (!StringUtils.isBlank(mod.getFilename())) {
			builder.and(q.filename.contains(mod.getFilename()));
		}
		if (mod.getUploadDate() != null) {
			builder.and(q.uploadDate.goe(mod.getUploadDate()));
		}
		if (mod.getChunkSize() != null) {
			builder.and(q.chunkSize.eq(mod.getChunkSize()));
		}
		if (mod.getLength() != null) {
			builder.and(q.length.eq(mod.getLength()));
		}
		if (!StringUtils.isBlank(mod.getMd5())) {
			builder.and(q.md5.eq(mod.getMd5()));
		}
		if (!StringUtils.isBlank(mod.getDownload())) {
			builder.and(q.download.eq(mod.getDownload()));
		}
		return findAll(builder.getValue(), pageable);
	}
}
