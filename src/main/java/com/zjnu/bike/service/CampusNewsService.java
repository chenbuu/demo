package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.CampusNews;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.CampusNewsRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class CampusNewsService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private CampusNewsRepository campusNewsRepository;

	public CampusNews findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, CampusNews.class)) {
			return redisRepository.getObject(id, CampusNews.class);
		}
		CampusNews campusNews = campusNewsRepository.findOne(id);
		redisRepository.setObject(campusNews);
		return campusNews;
	}

	public CampusNews save(CampusNews campusNews) throws Exception {
		campusNewsRepository.save(campusNews);
		redisRepository.setObject(campusNews);
		return campusNews;
	}

	public CampusNews insert(CampusNews campusNews) throws Exception {
		campusNewsRepository.insert(campusNews);
		redisRepository.setObject(campusNews);
		return campusNews;
	}

	public Page<CampusNews> findAll(@Valid CampusNews mod, Pageable pageable) throws Exception {
		Page<CampusNews> page = campusNewsRepository.findAll(mod, pageable);
		List<CampusNews> campusNewss = page.getContent();
		redisRepository.setList(campusNewss);
		return page;
	}

	public Page<CampusNews> findAll(Pageable pageable) throws Exception {
		Page<CampusNews> page = campusNewsRepository.findAll(pageable);
		List<CampusNews> campusNewss = page.getContent();
		redisRepository.setList(campusNewss);
		return page;
	}

}
