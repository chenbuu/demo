package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.LostAndFound;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.LostAndFoundRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class LostAndFoundService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private LostAndFoundRepository lostAndFoundRepository;

	public LostAndFound findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, LostAndFound.class)) {
			return redisRepository.getObject(id, LostAndFound.class);
		}
		LostAndFound lostAndFound = lostAndFoundRepository.findOne(id);
		redisRepository.setObject(lostAndFound);
		return lostAndFound;
	}

	public LostAndFound save(LostAndFound lostAndFound) throws Exception {
		lostAndFoundRepository.save(lostAndFound);
		redisRepository.setObject(lostAndFound);
		return lostAndFound;
	}

	public LostAndFound insert(LostAndFound lostAndFound) throws Exception {
		lostAndFoundRepository.insert(lostAndFound);
		redisRepository.setObject(lostAndFound);
		return lostAndFound;
	}

	public Page<LostAndFound> findAll(@Valid LostAndFound mod, Pageable pageable) throws Exception {
		Page<LostAndFound> page = lostAndFoundRepository.findAll(mod, pageable);
		List<LostAndFound> lostAndFounds = page.getContent();
		redisRepository.setList(lostAndFounds);
		return page;
	}

	public Page<LostAndFound> findAll(Pageable pageable) throws Exception {
		Page<LostAndFound> page = lostAndFoundRepository.findAll(pageable);
		List<LostAndFound> lostAndFounds = page.getContent();
		redisRepository.setList(lostAndFounds);
		return page;
	}

}
