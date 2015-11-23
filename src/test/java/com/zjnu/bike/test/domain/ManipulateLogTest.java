package com.zjnu.bike.test.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zjnu.bike.Application;
import com.zjnu.bike.domain.ManipulateLog;
import com.zjnu.bike.domain.User;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.repository.ManipulateLogRepository;
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
public class ManipulateLogTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ManipulateLogRepository manipulateLogRepository;

	@Test
	public void save() throws Exception {
		System.out.println("---------------save----------------");
		List<ManipulateLog> cList = new ArrayList<ManipulateLog>();
		PageRequest pageRequest = new PageRequest(0, 10);
		List<User> uList = this.userRepository.findAll(pageRequest).getContent();
		for (User u : uList) {
			log.debug("{}", u);
		}
		for (int i = 0; i < 100; i++) {
			ManipulateLog m = new ManipulateLog();
			m.setCreateTime(RandUtil.getDate());
			m.setStatus(RandUtil.getEnum(StatusEnum.values()));
			m.setOperator(RandUtil.getObject(uList));
			m.setMethodName(RandUtil.getStringLowerLetterRange(5, 10));
			m.setMethodType(RandUtil.getStringLowerLetterRange(5, 10));
			m.setMethodData(RandUtil.getStringNumberRange(5, 10));
			cList.add(m);
		}
		this.manipulateLogRepository.save(cList);
		System.out.println("-------------------------------");
	}

	@Test
	public void findAll() throws Exception {
		System.out.println("--------------findAll-----------------");
		for (ManipulateLog model : this.manipulateLogRepository.findAll()) {
			log.debug("{}", model);
		}
		System.out.println("-------------------------------");
	}
}
