package com.zjnu.bike.domain;

import java.io.Serializable;
import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.zjnu.bike.enums.CityEnum;
import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 游玩指南
 * @author ChenTao
 * @date 2015年11月19日上午1:45:33
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class PlayGuide implements Serializable {

	private static final long serialVersionUID = 452502101250329930L;

	@Id
	private String id;

	/**
	 * 城市CityEnum
	 */
	private CityEnum city;

	/**
	 * 标题图片 "FileInfo"
	 */
	@DBRef
	private FileInfo titleImage;

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
	 * 内容
	 */
	private String content;

	/**
	 * 摘要
	 */
	private String summary;

	/**
	 * 百度页码
	 */
	private Integer baiduPage;
}
