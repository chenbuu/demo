package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.Ride;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.RideRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class RideService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private RideRepository rideRepository;

	public Ride findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, Ride.class)) {
			return redisRepository.getObject(id, Ride.class);
		}
		Ride ride = rideRepository.findOne(id);
		redisRepository.setObject(ride);
		return ride;
	}

	public Ride save(Ride ride) throws Exception {
		rideRepository.save(ride);
		redisRepository.setObject(ride);
		return ride;
	}

	public Ride insert(Ride ride) throws Exception {
		rideRepository.insert(ride);
		redisRepository.setObject(ride);
		return ride;
	}

	public Page<Ride> findAll(@Valid Ride mod, Pageable pageable) throws Exception {
		Page<Ride> page = rideRepository.findAll(mod, pageable);
		List<Ride> rides = page.getContent();
		redisRepository.setList(rides);
		return page;
	}

	public Page<Ride> findAll(Pageable pageable) throws Exception {
		Page<Ride> page = rideRepository.findAll(pageable);
		List<Ride> rides = page.getContent();
		redisRepository.setList(rides);
		return page;
	}

}
