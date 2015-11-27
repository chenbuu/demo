package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.LifeGuide;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.LifeGuideRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class LifeGuideService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private LifeGuideRepository userRepository;

	public LifeGuide findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, LifeGuide.class)) {
			return redisRepository.getObject(id, LifeGuide.class);
		}
		LifeGuide user = userRepository.findOne(id);
		redisRepository.setObject(user);
		return user;
	}

	public LifeGuide save(LifeGuide user) throws Exception {
		userRepository.save(user);
		redisRepository.setObject(user);
		return user;
	}

	public LifeGuide insert(LifeGuide user) throws Exception {
		userRepository.insert(user);
		redisRepository.setObject(user);
		return user;
	}

	public Page<LifeGuide> findAll(@Valid LifeGuide mod, Pageable pageable) throws Exception {
		Page<LifeGuide> page = userRepository.findAll(mod, pageable);
		List<LifeGuide> users = page.getContent();
		redisRepository.setList(users);
		return page;
	}

	public Page<LifeGuide> findAll(Pageable pageable) throws Exception {
		Page<LifeGuide> page = userRepository.findAll(pageable);
		List<LifeGuide> users = page.getContent();
		redisRepository.setList(users);
		return page;
	}

}
