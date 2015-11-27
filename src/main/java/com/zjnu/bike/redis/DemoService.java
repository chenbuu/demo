package com.zjnu.bike.redis;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.Customer;

/**
 * DemoService
 * @author ChenTao
 * @date 2015年11月25日下午10:51:44
 */
@Service
public class DemoService {

	@Cacheable(value = "customerCache", keyGenerator = "myGenerator")
	public Customer findCustomer(String id, String firstName, String lastName) {
		System.out.println("无缓存的时候调用这里");
		return new Customer(id, firstName, lastName);
	}

	@CacheEvict(value = "customerCache", allEntries = true)
	public Customer deleteCustomer(String id, String firstName, String lastName) {
		System.out.println("无缓存的时候调用这里2");
		return new Customer(id, firstName, lastName);
	}
}