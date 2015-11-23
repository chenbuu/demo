package com.zjnu.bike.dto;

import java.util.Date;

import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 文件信息Dto
 * @author ChenTao
 * @date 2015年7月20日下午10:13:46
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FileInfoDto {

	public FileInfoDto(FileInfo f) throws Exception {
		this.createTime = f.getCreateTime();
		this.fileName = f.getFileName();
		this.fileUrl = f.getFileUrl();
		this.id = f.getId();
		this.operatorId = f.getOperatorId();
		this.status = f.getStatus();
	}

	private String id;

	/**
	 * 操作人User Id
	 */
	private String operatorId;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件路径
	 */
	private String fileUrl;

}
