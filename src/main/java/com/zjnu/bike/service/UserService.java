package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.User;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.UserRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class UserService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private UserRepository userRepository;

	public User findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, User.class)) {
			return redisRepository.getObject(id, User.class);
		}
		User user = userRepository.findOne(id);
		redisRepository.setObject(user);
		return user;
	}

	public User save(User user) throws Exception {
		userRepository.save(user);
		redisRepository.setObject(user);
		return user;
	}

	public User insert(User user) throws Exception {
		userRepository.insert(user);
		redisRepository.setObject(user);
		return user;
	}

	public Page<User> findAll(@Valid User mod, Pageable pageable) throws Exception {
		Page<User> page = userRepository.findAll(mod, pageable);
		List<User> users = page.getContent();
		redisRepository.setList(users);
		return page;
	}

	public Page<User> findAll(Pageable pageable) throws Exception {
		Page<User> page = userRepository.findAll(pageable);
		List<User> users = page.getContent();
		redisRepository.setList(users);
		return page;
	}

	public List<User> findAllByUserNameAndPassword(String userName, String password) throws Exception {
		List<User> users = userRepository.findAllByUserNameAndPassword(userName, password);
		redisRepository.setList(users);
		return users;
	}

	public List<User> findAllByUserName(String userName) throws Exception {
		List<User> users = userRepository.findAllByUserName(userName);
		redisRepository.setList(users);
		return users;
	}
}
