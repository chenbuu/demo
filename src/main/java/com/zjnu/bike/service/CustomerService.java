package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.Customer;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.CustomerRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class CustomerService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Customer findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, Customer.class)) {
			return redisRepository.getObject(id, Customer.class);
		}
		Customer customer = customerRepository.findOne(id);
		redisRepository.setObject(customer);
		return customer;
	}

	public Customer save(Customer customer) throws Exception {
		customerRepository.save(customer);
		redisRepository.setObject(customer);
		return customer;
	}

	public Customer insert(Customer customer) throws Exception {
		customerRepository.insert(customer);
		redisRepository.setObject(customer);
		return customer;
	}

	public Page<Customer> findAll(@Valid Customer mod, Pageable pageable) throws Exception {
		Page<Customer> page = customerRepository.findAll(mod, pageable);
		List<Customer> customers = page.getContent();
		redisRepository.setList(customers);
		return page;
	}

	public Page<Customer> findAll(Pageable pageable) throws Exception {
		Page<Customer> page = customerRepository.findAll(pageable);
		List<Customer> customers = page.getContent();
		redisRepository.setList(customers);
		return page;
	}

}
