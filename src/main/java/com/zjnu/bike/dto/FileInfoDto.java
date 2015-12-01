package com.zjnu.bike.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class FileInfoDto implements Serializable {

	private static final long serialVersionUID = 6529048698051747838L;

	public FileInfoDto(FileInfo f) throws Exception {
		this.aliases = f.getAliases();
		this.chunkSize = f.getChunkSize();
		this.contentType = f.getContentType();
		this.download = f.getDownload();
		this.filename = f.getFilename();
		this.id = f.getId();
		this.length = f.getLength();
		this.md5 = f.getMd5();
		this.operatorId = f.getOperatorId();
		this.status = f.getStatus();
		this.uploadDate = f.getUploadDate();
	}

	private String id;

	/**
	 * 下载外键
	 */
	private String download;

	/**
	 * 操作人User Id
	 */
	private String operatorId;

	/**
	 * 状态StatusEnum
	 */
	private StatusEnum status;

	/**
	 * 文件名称
	 */
	private String filename;

	/**
	 * ？？？
	 */
	private List<String> aliases;

	/**
	 * chunk数量
	 */
	private Long chunkSize;

	/**
	 * 创建时间
	 */
	private Date uploadDate;

	/**
	 * 大小
	 */
	private Long length;

	/**
	 * 文件类型
	 */
	private String contentType;

	/**
	 * MD5
	 */
	private String md5;

}
