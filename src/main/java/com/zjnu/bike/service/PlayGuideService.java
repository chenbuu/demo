package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.PlayGuide;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.PlayGuideRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class PlayGuideService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private PlayGuideRepository playGuideRepository;

	public PlayGuide findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, PlayGuide.class)) {
			return redisRepository.getObject(id, PlayGuide.class);
		}
		PlayGuide playGuide = playGuideRepository.findOne(id);
		redisRepository.setObject(playGuide);
		return playGuide;
	}

	public PlayGuide save(PlayGuide playGuide) throws Exception {
		playGuideRepository.save(playGuide);
		redisRepository.setObject(playGuide);
		return playGuide;
	}

	public PlayGuide insert(PlayGuide playGuide) throws Exception {
		playGuideRepository.insert(playGuide);
		redisRepository.setObject(playGuide);
		return playGuide;
	}

	public Page<PlayGuide> findAll(@Valid PlayGuide mod, Pageable pageable) throws Exception {
		Page<PlayGuide> page = playGuideRepository.findAll(mod, pageable);
		List<PlayGuide> playGuides = page.getContent();
		redisRepository.setList(playGuides);
		return page;
	}

	public Page<PlayGuide> findAll(Pageable pageable) throws Exception {
		Page<PlayGuide> page = playGuideRepository.findAll(pageable);
		List<PlayGuide> playGuides = page.getContent();
		redisRepository.setList(playGuides);
		return page;
	}

}
