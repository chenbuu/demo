package com.zjnu.bike.gridfs;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.zjnu.bike.domain.FileInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件数据库操作
 * @author ChenTao
 * @date 2015年12月1日下午3:19:53
 */
@Repository
@Slf4j
public class GridFSRepository {

	@Autowired
	private GridFSConfig gridFSConfig;

	/**
	 * 测试
	 * @author ChenTao
	 * @date 2015年12月1日下午6:54:01
	 */
	public Boolean saveFileObject(FileObject fileObject) throws Exception {
		gridFSConfig.template().save(fileObject);
		return true;
	}

	/**
	 * 保存文件File
	 * @author ChenTao
	 * @date 2015年12月1日下午5:30:37
	 */
	public FileInfo saveFile(File file) {
		Mongo mongo = null;
		FileInfo f = new FileInfo();
		try {
			mongo = gridFSConfig.mongo();
			DB db = mongo.getDB(gridFSConfig.getDatabase());
			GridFS gridFS = new GridFS(db);
			GridFSInputFile inputFile = gridFS.createFile(file);
			inputFile.save();
			f = setFileInfo(inputFile);
		} catch (Exception e) {
			log.error("{}", e);
		} finally {
			mongo.close();
		}
		return f;
	}

	/**
	 * 保存文件InputStream
	 * @author ChenTao
	 * @date 2015年12月1日下午5:30:37
	 */
	public FileInfo saveFile(InputStream inputStream, String filename) {
		return saveFile(inputStream, filename, null);
	}

	/**
	 * 保存文件InputStream
	 * @author ChenTao
	 * @date 2015年12月1日下午5:30:37
	 */
	public FileInfo saveFile(InputStream inputStream, String filename, String contentType) {
		Mongo mongo = null;
		FileInfo f = new FileInfo();
		try {
			mongo = gridFSConfig.mongo();
			DB db = mongo.getDB(gridFSConfig.getDatabase());
			GridFS gridFS = new GridFS(db);
			GridFSInputFile inputFile = gridFS.createFile(inputStream);
			inputFile.setFilename(filename);
			inputFile.setContentType(contentType);
			inputFile.save();
			f = setFileInfo(inputFile);
		} catch (Exception e) {
			log.error("{}", e);
		} finally {
			mongo.close();
		}
		return f;
	}

	public FileInfo setFileInfo(GridFSInputFile inputFile) {
		FileInfo f = new FileInfo();
		f.setDownload(inputFile.getId() == null ? null : inputFile.getId().toString());
		f.setFilename(inputFile.getFilename());
		f.setAliases(inputFile.getAliases());
		f.setChunkSize(inputFile.getChunkSize());
		f.setContentType(inputFile.getContentType());
		f.setLength(inputFile.getLength());
		f.setMd5(inputFile.getMD5());
		f.setUploadDate(inputFile.getUploadDate());
		return f;
	}

	/**
	 * 查询文件（主键）
	 * @author ChenTao
	 * @date 2015年12月1日下午5:30:45
	 */
	public GridFSDBFile findOne(ObjectId objectId) {
		if (StringUtils.isBlank(objectId.toString())) {
			return null;
		}
		Mongo mongo = null;
		GridFSDBFile gfsFile = null;
		try {
			mongo = gridFSConfig.mongo();
			DB db = mongo.getDB(gridFSConfig.getDatabase());
			GridFS gridFS = new GridFS(db);
			gfsFile = gridFS.findOne(objectId);
		} catch (Exception e) {
			log.error("{}", e);
		} finally {
			//无法关闭
			//mongo.close();
		}
		return gfsFile;
	}

	/**
	 * 查询文件（主键）
	 * @author ChenTao
	 * @date 2015年12月1日下午5:31:07
	 */
	public GridFSDBFile findOne(String id) {
		return findOne(new ObjectId(id));
	}

}
