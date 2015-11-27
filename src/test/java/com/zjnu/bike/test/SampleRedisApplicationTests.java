package com.zjnu.bike.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zjnu.bike.Application;
import com.zjnu.bike.domain.Customer;
import com.zjnu.bike.domain.User;
import com.zjnu.bike.redis.DemoService;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.CustomerRepository;
import com.zjnu.bike.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Tests for {@link SampleMongoApplication}.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class SampleRedisApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private DemoService demoService;

	@Autowired
	private UserService userService;

	@Test
	@Ignore
	public void test() throws Exception {

		this.customerRepository.deleteAll();
		// save a couple of customers
		this.customerRepository.save(new Customer(null, "Alice", "Smith"));
		this.customerRepository.save(new Customer(null, "Bob", "Smith"));

		// fetch all customers
		System.out.println("---------------this.customerRepository.findAll()----------------");
		for (Customer customer : this.customerRepository.findAll()) {
			log.debug("{}", customer);
		}
		System.out.println();

		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		String key = "spring.boot.redis.test2";
		if (!this.stringRedisTemplate.hasKey(key)) {
			System.out.println("!hasKey");
			ops.set(key, "foo1");
		}
		System.out.println("Found key " + key + ", value=" + ops.get(key));
		System.out.println();

		ValueOperations<String, Object> ops2 = this.redisRepository.getRedis(Customer.class).opsForValue();
		key = "Customer";
		if (!this.redisRepository.getRedis(Customer.class).hasKey(key)) {
			System.out.println("!hasKey" + key);
			ops2.set(key, new Customer(null, "Alice", "Smith"));
		}
		Customer c = (Customer) ops2.get(key);
		log.debug("{}", c);
	}

	@Test
	@Ignore
	public void test2() throws Exception {
		System.out.println("---------------test2----------------");
		for (Customer customer : this.customerRepository.findAll()) {
			this.redisRepository.setObject(customer);
		}
		System.out.println(Customer.class.getName());
		System.out.println(Customer.class.getSimpleName());
		String key = "56557cbdb35ddd5356b94f8e";
		Customer c1 = this.redisRepository.getObject("56557cbdb35ddd5356b94f8e", Customer.class);
		log.debug("{}", c1);
		if (this.redisRepository.hasKey(key, Customer.class)) {
			Customer c = this.redisRepository.getObject(key, Customer.class);
			log.debug("{}", c);
		}
		System.out.println("---------------test end----------------");
	}

	@Test
	@Ignore
	public void test3() throws Exception {
		System.out.println("---------------test3----------------");
		Customer c = demoService.findCustomer("11111", "aaaaa", "bbbbb");
		log.debug("{}", c);
		System.out.println("---------------test end----------------");
	}

	@Test
	@Ignore
	public void test4() throws Exception {
		System.out.println("---------------test4----------------");
		Customer c = demoService.deleteCustomer("11111", "aaaaa", "bbbbb");
		log.debug("{}", c);
		System.out.println("---------------test end----------------");
	}

	@Test
	@Ignore
	public void test5() throws Exception {
		System.out.println("---------------test5----------------");
		User u = this.userService.findOne("56508d60b35dabf3cc5f15c0");
		log.debug("{}", u);
		u.setNickName("1");
		this.userService.save(u);
		log.debug("{}", u);
		/*this.userService.findOne("56508d60b35dabf3cc5f15c0");
		log.debug("{}", u);*/
		System.out.println("---------------test end----------------");
	}
	
	@Test
	@Ignore
	public void test6() throws Exception {
		System.out.println("---------------test6----------------");
		PageRequest pageRequest = new PageRequest(0, 10);
		List<User> uList = this.userService.findAll(pageRequest).getContent();
		for (User u : uList) {
			log.debug("{}", u);
		}
		System.out.println("---------------test end----------------");
	}
	
	@Test
	//@Ignore
	public void test7() throws Exception {
		System.out.println("---------------test7----------------");
		PageRequest pageRequest = new PageRequest(0, 10);
		List<User> uList = this.userService.findAll(pageRequest).getContent();
		List<String> ids = new ArrayList<>(); 
		for (User u : uList) {
			ids.add(u.getId());
		}
		List<User> uList2 = this.redisRepository.getList(ids, User.class);
		for (User u : uList2) {
			log.debug("{}", u);
		}
		System.out.println("---------------test end----------------");
	}
}
