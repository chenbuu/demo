package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.C2C;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.C2CRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class C2CService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private C2CRepository c2CRepository;

	public C2C findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, C2C.class)) {
			return redisRepository.getObject(id, C2C.class);
		}
		C2C c2C = c2CRepository.findOne(id);
		redisRepository.setObject(c2C);
		return c2C;
	}

	public C2C save(C2C c2C) throws Exception {
		c2CRepository.save(c2C);
		redisRepository.setObject(c2C);
		return c2C;
	}

	public C2C insert(C2C c2C) throws Exception {
		c2CRepository.insert(c2C);
		redisRepository.setObject(c2C);
		return c2C;
	}

	public Page<C2C> findAll(@Valid C2C mod, Pageable pageable) throws Exception {
		Page<C2C> page = c2CRepository.findAll(mod, pageable);
		List<C2C> c2Cs = page.getContent();
		redisRepository.setList(c2Cs);
		return page;
	}

	public Page<C2C> findAll(Pageable pageable) throws Exception {
		Page<C2C> page = c2CRepository.findAll(pageable);
		List<C2C> c2Cs = page.getContent();
		redisRepository.setList(c2Cs);
		return page;
	}

}
