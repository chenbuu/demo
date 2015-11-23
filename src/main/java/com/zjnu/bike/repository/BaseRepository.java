package com.zjnu.bike.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 要求数据库操作类继承该类
 *
 * @author ChenTao
 * @param <T> Entity class
 * @param <ID> id class of the entity, usually java.lang.Long
 */
//this interface should not be instantiate, the annotation NoRepositoryBean is required,
//all of our repository may extends this interface for convinent
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID>, QueryDslPredicateExecutor<T> {

}
