package com.zjnu.bike.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.zjnu.bike.domain.Customer;

/**
 * MongoDB标准查询语句
 * @author ChenTao
 * @date 2015年11月19日上午2:42:45
 */
@Repository
public class MongoTemplateRepository {

	@Autowired
	protected MongoTemplate mongoTemplate;

	public List<Customer> findaaa() {
		return mongoTemplate.find(new Query(), Customer.class);
	}
}
