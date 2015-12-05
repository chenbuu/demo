package com.zjnu.bike.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zjnu.bike.enums.FileTypeEnum;
import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 文件信息
 * 底层对象不关联外键
 * 注意：修改属性时候需要维护Dto
 * @author ChenTao
 * @date 2015年7月20日下午10:13:46
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {

	private static final long serialVersionUID = -4943866506112002047L;

	@Id
	private String id;

	/**
	 * 下载外键
	 */
	private String download;

	/**
	 * 操作人User Id
	 */
	@JsonIgnore
	private String operatorId;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 文件类型
	 */
	@JsonIgnore
	private FileTypeEnum fileType;

	/**
	 * 文件名称
	 */
	@JsonIgnore
	private String filename;

	/**
	 * ？？？
	 */
	@JsonIgnore
	private List<String> aliases;

	/**
	 * chunk数量
	 */
	@JsonIgnore
	private Long chunkSize;

	/**
	 * 创建时间
	 */
	@JsonIgnore
	private Date uploadDate;

	/**
	 * 大小
	 */
	@JsonIgnore
	private Long length;

	/**
	 * 文件类型
	 */
	@JsonIgnore
	private String contentType;

	/**
	 * MD5
	 */
	@JsonIgnore
	private String md5;

}
