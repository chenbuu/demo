package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.PartTime;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.PartTimeRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class PartTimeService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private PartTimeRepository partTimeRepository;

	public PartTime findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, PartTime.class)) {
			return redisRepository.getObject(id, PartTime.class);
		}
		PartTime partTime = partTimeRepository.findOne(id);
		redisRepository.setObject(partTime);
		return partTime;
	}

	public PartTime save(PartTime partTime) throws Exception {
		partTimeRepository.save(partTime);
		redisRepository.setObject(partTime);
		return partTime;
	}

	public PartTime insert(PartTime partTime) throws Exception {
		partTimeRepository.insert(partTime);
		redisRepository.setObject(partTime);
		return partTime;
	}

	public Page<PartTime> findAll(@Valid PartTime mod, Pageable pageable) throws Exception {
		Page<PartTime> page = partTimeRepository.findAll(mod, pageable);
		List<PartTime> partTimes = page.getContent();
		redisRepository.setList(partTimes);
		return page;
	}

	public Page<PartTime> findAll(Pageable pageable) throws Exception {
		Page<PartTime> page = partTimeRepository.findAll(pageable);
		List<PartTime> partTimes = page.getContent();
		redisRepository.setList(partTimes);
		return page;
	}

}
