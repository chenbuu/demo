package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.Navigation;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.NavigationRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class NavigationService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private NavigationRepository navigationRepository;

	public Navigation findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, Navigation.class)) {
			return redisRepository.getObject(id, Navigation.class);
		}
		Navigation navigation = navigationRepository.findOne(id);
		redisRepository.setObject(navigation);
		return navigation;
	}

	public Navigation save(Navigation navigation) throws Exception {
		navigationRepository.save(navigation);
		redisRepository.setObject(navigation);
		return navigation;
	}

	public Navigation insert(Navigation navigation) throws Exception {
		navigationRepository.insert(navigation);
		redisRepository.setObject(navigation);
		return navigation;
	}

	public Page<Navigation> findAll(@Valid Navigation mod, Pageable pageable) throws Exception {
		Page<Navigation> page = navigationRepository.findAll(mod, pageable);
		List<Navigation> navigations = page.getContent();
		redisRepository.setList(navigations);
		return page;
	}

	public Page<Navigation> findAll(Pageable pageable) throws Exception {
		Page<Navigation> page = navigationRepository.findAll(pageable);
		List<Navigation> navigations = page.getContent();
		redisRepository.setList(navigations);
		return page;
	}

}
