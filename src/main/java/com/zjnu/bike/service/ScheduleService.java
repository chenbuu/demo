package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.Schedule;
import com.zjnu.bike.domain.User;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.ScheduleRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class ScheduleService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	public Schedule findMySchedule(String userId) throws Exception {
		User user = new User();
		user.setId(userId);
		return scheduleRepository.findAllByOperator(user).get(0);
	}

	public Schedule findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, Schedule.class)) {
			return redisRepository.getObject(id, Schedule.class);
		}
		Schedule schedule = scheduleRepository.findOne(id);
		redisRepository.setObject(schedule);
		return schedule;
	}

	public Schedule save(Schedule schedule) throws Exception {
		scheduleRepository.save(schedule);
		redisRepository.setObject(schedule);
		return schedule;
	}

	public Schedule insert(Schedule schedule) throws Exception {
		scheduleRepository.insert(schedule);
		redisRepository.setObject(schedule);
		return schedule;
	}

	public Page<Schedule> findAll(@Valid Schedule mod, Pageable pageable) throws Exception {
		Page<Schedule> page = scheduleRepository.findAll(mod, pageable);
		List<Schedule> schedules = page.getContent();
		redisRepository.setList(schedules);
		return page;
	}

	public Page<Schedule> findAll(Pageable pageable) throws Exception {
		Page<Schedule> page = scheduleRepository.findAll(pageable);
		List<Schedule> schedules = page.getContent();
		redisRepository.setList(schedules);
		return page;
	}

}
