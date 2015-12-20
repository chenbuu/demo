package com.zjnu.bike.repository;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.zjnu.bike.domain.CampusNews;
import com.zjnu.bike.domain.QCampusNews;

public interface CampusNewsRepository extends BaseRepository<CampusNews, String> {

	default Page<CampusNews> findAll(@Valid CampusNews mod, Pageable pageable) {
		if (mod == null) {
			return findAll(pageable);
		}
		BooleanBuilder builder = new BooleanBuilder();
		QCampusNews q = QCampusNews.campusNews;
		if (mod.getStatus() != null) {
			builder.and(q.status.eq(mod.getStatus()));
		}
		if (mod.getNewsType() != null) {
			builder.and(q.newsType.eq(mod.getNewsType()));
		}
		if (!StringUtils.isBlank(mod.getContent())) {
			builder.and(q.content.contains(mod.getContent()));
		}
		if (!StringUtils.isBlank(mod.getSummary())) {
			builder.and(q.summary.contains(mod.getSummary()));
		}
		if (!StringUtils.isBlank(mod.getTitle())) {
			builder.and(q.title.contains(mod.getTitle()));
		}
		if (mod.getBorder() != null) {
			builder.and(q.border.eq(mod.getBorder()));
		}
		if (mod.getCreateTime() != null) {
			builder.and(q.createTime.goe(mod.getCreateTime()));
		}
		return findAll(builder.getValue(), pageable);
	}
}
