package com.zjnu.bike.util;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 时间查询工具类
 * @author ChenTao
 * @date 2015年8月6日上午11:33:01
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TimeSearchUtil {

	/**
	 * 创建时间-开始
	 */
	private Timestamp createTimeStart;
	
	/**
	 * 创建时间-结束
	 */
	private Timestamp createTimeEnd;

	/**
	 * 修改时间-开始
	 */
	private Timestamp modifyTimeStart;
	
	/**
	 * 修改时间-结束
	 */
	private Timestamp modifyTimeEnd;
	
	/**
	 * 激活时间-开始
	 */
	private Timestamp activeTimeStart;
	
	/**
	 * 激活时间-结束
	 */
	private Timestamp activeTimeEnd;
	
	/**
	 * 发布时间-开始
	 */
	private Timestamp publishTimeStart;
	
	/**
	 * 发布时间-结束
	 */
	private Timestamp publishTimeEnd;
}
