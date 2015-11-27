package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.ManipulateLog;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.ManipulateLogRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class ManipulateLogService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private ManipulateLogRepository manipulateLogRepository;

	public ManipulateLog findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, ManipulateLog.class)) {
			return redisRepository.getObject(id, ManipulateLog.class);
		}
		ManipulateLog manipulateLog = manipulateLogRepository.findOne(id);
		redisRepository.setObject(manipulateLog);
		return manipulateLog;
	}

	public ManipulateLog save(ManipulateLog manipulateLog) throws Exception {
		manipulateLogRepository.save(manipulateLog);
		redisRepository.setObject(manipulateLog);
		return manipulateLog;
	}

	public ManipulateLog insert(ManipulateLog manipulateLog) throws Exception {
		manipulateLogRepository.insert(manipulateLog);
		redisRepository.setObject(manipulateLog);
		return manipulateLog;
	}

	public Page<ManipulateLog> findAll(@Valid ManipulateLog mod, Pageable pageable) throws Exception {
		Page<ManipulateLog> page = manipulateLogRepository.findAll(mod, pageable);
		List<ManipulateLog> manipulateLogs = page.getContent();
		redisRepository.setList(manipulateLogs);
		return page;
	}

	public Page<ManipulateLog> findAll(Pageable pageable) throws Exception {
		Page<ManipulateLog> page = manipulateLogRepository.findAll(pageable);
		List<ManipulateLog> manipulateLogs = page.getContent();
		redisRepository.setList(manipulateLogs);
		return page;
	}

}
