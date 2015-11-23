package com.zjnu.bike.domain;

import java.util.Date;
import java.util.Map;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 课程表
 * @author ChenTao
 * @date 2015年11月19日上午1:45:33
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Schedule {

	@Id
	private String id;

	/**
	 * 操作人User
	 */
	@DBRef
	private User operator;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 星期X课程表
	 * 里层Map 1-15表示第几节课
	 */
	private Map<Integer, String> lessonOne;

	/**
	 * 星期X课程表
	 */
	private Map<Integer, String> lessonTwo;

	/**
	 * 星期X课程表
	 */
	private Map<Integer, String> lessonThree;

	/**
	 * 星期X课程表
	 */
	private Map<Integer, String> lessonFour;

	/**
	 * 星期X课程表
	 */
	private Map<Integer, String> lessonFive;

	/**
	 * 星期X课程表
	 */
	private Map<Integer, String> lessonSix;

	/**
	 * 星期X课程表
	 */
	private Map<Integer, String> lessonSeven;

}
