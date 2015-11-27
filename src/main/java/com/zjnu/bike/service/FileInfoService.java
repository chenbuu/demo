package com.zjnu.bike.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.redis.RedisRepository;
import com.zjnu.bike.repository.FileInfoRepository;

/**
 * 
 * @author ChenTao
 * @date 2015年11月25日下午11:12:31
 */
@Service
public class FileInfoService {

	@Autowired
	private RedisRepository redisRepository;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	public FileInfo findOne(String id) throws Exception {
		if (redisRepository.hasKey(id, FileInfo.class)) {
			return redisRepository.getObject(id, FileInfo.class);
		}
		FileInfo fileInfo = fileInfoRepository.findOne(id);
		redisRepository.setObject(fileInfo);
		return fileInfo;
	}

	public FileInfo save(FileInfo fileInfo) throws Exception {
		fileInfoRepository.save(fileInfo);
		redisRepository.setObject(fileInfo);
		return fileInfo;
	}

	public FileInfo insert(FileInfo fileInfo) throws Exception {
		fileInfoRepository.insert(fileInfo);
		redisRepository.setObject(fileInfo);
		return fileInfo;
	}

	public Page<FileInfo> findAll(@Valid FileInfo mod, Pageable pageable) throws Exception {
		Page<FileInfo> page = fileInfoRepository.findAll(mod, pageable);
		List<FileInfo> fileInfos = page.getContent();
		redisRepository.setList(fileInfos);
		return page;
	}

	public Page<FileInfo> findAll(Pageable pageable) throws Exception {
		Page<FileInfo> page = fileInfoRepository.findAll(pageable);
		List<FileInfo> fileInfos = page.getContent();
		redisRepository.setList(fileInfos);
		return page;
	}

}
