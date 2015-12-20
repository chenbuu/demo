package com.zjnu.bike.repository;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.zjnu.bike.domain.PlayGuide;
import com.zjnu.bike.domain.QPlayGuide;

public interface PlayGuideRepository extends BaseRepository<PlayGuide, String> {

	default Page<PlayGuide> findAll(@Valid PlayGuide mod, Pageable pageable) {
		if (mod == null) {
			return findAll(pageable);
		}
		BooleanBuilder builder = new BooleanBuilder();
		QPlayGuide q = QPlayGuide.playGuide;
		if (mod.getStatus() != null) {
			builder.and(q.status.eq(mod.getStatus()));
		}
		if (mod.getBaiduPage() != null) {
			builder.and(q.baiduPage.eq(mod.getBaiduPage()));
		}
		if (mod.getCity() != null) {
			builder.and(q.city.eq(mod.getCity()));
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
		if (mod.getCreateTime() != null) {
			builder.and(q.createTime.goe(mod.getCreateTime()));
		}
		return findAll(builder.getValue(), pageable);
	}
}
