package com.zjnu.bike.test.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zjnu.bike.Application;
import com.zjnu.bike.domain.Schedule;
import com.zjnu.bike.domain.User;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.ScheduleRepository;
import com.zjnu.bike.repository.UserRepository;
import com.zjnu.bike.util.RandUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据库数据模拟
 * @author ChenTao
 * @date 2015年11月19日下午9:42:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class ScheduleTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Test
	public void save() throws Exception {
		System.out.println("---------------save----------------");
		List<Schedule> cList = new ArrayList<Schedule>();
		PageRequest pageRequest = new PageRequest(0, 10);
		List<User> uList = this.userRepository.findAll(pageRequest).getContent();
		for (User u : uList) {
			log.debug("{}", u);
		}
		for (int i = 0; i < 100; i++) {
			Schedule m = new Schedule();
			m.setCreateTime(RandUtil.getDate());
			m.setStatus(RandUtil.getEnum(StatusEnum.values()));
			m.setTitle(RandUtil.getStringChineseRange(3, 6));
			m.setOperator(RandUtil.getObject(uList));
			m.setLessonOne(randLesson());
			m.setLessonTwo(randLesson());
			m.setLessonThree(randLesson());
			m.setLessonFour(randLesson());
			m.setLessonFive(randLesson());
			m.setLessonSix(randLesson());
			m.setLessonSeven(randLesson());
			cList.add(m);
		}
		this.scheduleRepository.save(cList);
		System.out.println("-------------------------------");
	}

	public Map<Integer, String> randLesson() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 13; i++) {
			map.put(RandUtil.getInteger(1, 13), RandUtil.getStringChineseRange(2, 7));
		}
		return map;
	}

	@Test
	public void findAll() throws Exception {
		System.out.println("--------------findAll-----------------");
		for (Schedule model : this.scheduleRepository.findAll()) {
			log.debug("{}", model);
		}
		System.out.println("-------------------------------");
	}
}
