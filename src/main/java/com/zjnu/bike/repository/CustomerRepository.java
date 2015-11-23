package com.zjnu.bike.repository;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.BooleanBuilder;
import com.zjnu.bike.domain.Customer;
import com.zjnu.bike.domain.QCustomer;

public interface CustomerRepository extends BaseRepository<Customer, String> {

	Customer findByFirstName(String firstName);

	List<Customer> findByLastName(String lastName);

	/**
	 * goe >=
	 * gt >
	 * lt <
	 * loe <=
	 * eq =
	 * ne <>, !=
	 * between between
	 * contains instr(, ?1) > 0
	 * like  like
	 * in in
	 * @Valid校验
	 */
	default Page<Customer> findAll(@Valid Customer search, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		QCustomer q = QCustomer.customer;
		if (!StringUtils.isBlank(search.getLastName())) {
			builder.and(q.lastName.contains(search.getLastName()));
		}
		return findAll(builder.getValue(), pageable);
	}
}
