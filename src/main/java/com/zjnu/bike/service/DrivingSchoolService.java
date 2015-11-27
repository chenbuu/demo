package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.DrivingSchool;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.DrivingSchoolRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class DrivingSchoolService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private DrivingSchoolRepository drivingSchoolRepository;

	public DrivingSchool findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, DrivingSchool.class)) {
			return redisRepository.getObject(id, DrivingSchool.class);
		}
		DrivingSchool drivingSchool = drivingSchoolRepository.findOne(id);
		redisRepository.setObject(drivingSchool);
		return drivingSchool;
	}

	public DrivingSchool save(DrivingSchool drivingSchool) throws Exception {
		drivingSchoolRepository.save(drivingSchool);
		redisRepository.setObject(drivingSchool);
		return drivingSchool;
	}

	public DrivingSchool insert(DrivingSchool drivingSchool) throws Exception {
		drivingSchoolRepository.insert(drivingSchool);
		redisRepository.setObject(drivingSchool);
		return drivingSchool;
	}

	public Page<DrivingSchool> findAll(@Valid DrivingSchool mod, Pageable pageable) throws Exception {
		Page<DrivingSchool> page = drivingSchoolRepository.findAll(mod, pageable);
		List<DrivingSchool> drivingSchools = page.getContent();
		redisRepository.setList(drivingSchools);
		return page;
	}

	public Page<DrivingSchool> findAll(Pageable pageable) throws Exception {
		Page<DrivingSchool> page = drivingSchoolRepository.findAll(pageable);
		List<DrivingSchool> drivingSchools = page.getContent();
		redisRepository.setList(drivingSchools);
		return page;
	}

}
