package com.zjnu.bike.gridfs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * GridFS返回信息
 * @author ChenTao
 * @date 2015年12月1日下午7:43:30
 */
@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class FileObject implements Serializable {

	private static final long serialVersionUID = 4643077672760511607L;

	@Id
	private String id;

	private String filename;

	private List<String> aliases;

	private Long chunkSize;

	private Date uploadDate;

	private Long length;

	private String contentType;

	private String md5;

}
