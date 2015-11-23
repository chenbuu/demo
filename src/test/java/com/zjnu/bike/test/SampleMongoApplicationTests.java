package com.zjnu.bike.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zjnu.bike.Application;
import com.zjnu.bike.domain.Customer;
import com.zjnu.bike.repository.CustomerRepository;
import com.zjnu.bike.repository.MongoTemplateRepository;

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
public class SampleMongoApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MongoTemplateRepository mongoTemplateRepository;

	@Test
	@Ignore
	public void testMongo() throws Exception {

		//this.repository.deleteAll();

		// save a couple of customers
		this.customerRepository.save(new Customer(null, "Alice", "Smith"));
		this.customerRepository.save(new Customer(null, "Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : this.customerRepository.findAll()) {
			log.debug("{}", customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : this.customerRepository.findByLastName("Smith")) {
			log.debug("{}", customer);
		}
	}

	@Test
	@Ignore
	public void testMongo2() throws Exception {
		System.out.println("-------------------------------");
		for (Customer customer : this.mongoTemplateRepository.findaaa()) {
			log.debug("{}", customer);
		}
		System.out.println("-------------------------------");
	}

	@Test
	public void testMongo3() throws Exception {
		System.out.println("-------------------------------");
		for (Customer customer : this.customerRepository.findAll(new Customer(null, "Alice", "Smith"), null)) {
			log.debug("{}", customer);
		}
		System.out.println("-------------------------------");
	}
}
