package com.zjnu.bike.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.zjnu.bike.Application;
import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.enums.FileTypeEnum;
import com.zjnu.bike.enums.StatusEnum;
import com.zjnu.bike.gridfs.GridFSConfig;
import com.zjnu.bike.gridfs.GridFSRepository;
import com.zjnu.bike.repository.FileInfoRepository;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

/**
 * 
 * @author ChenTao
 * @date 2015年12月1日下午3:07:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class GetSmallImageTests {

	@Autowired
	private GridFSRepository gridFSRepository;

	@Autowired
	private GridFSConfig gridFSConfig;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Test
	public void test() throws Exception {
		System.out.println("-------------------------------");
		Mongo mongo = null;
		GridFSDBFile gfsFile = null;
		PageRequest pageRequest2 = new PageRequest(0, 40);
		List<FileInfo> fList = this.fileInfoRepository.findAll(pageRequest2).getContent();
		for (FileInfo f : fList) {
			log.debug("{}", f);
		}
		List<FileInfo> bigImages = new ArrayList<FileInfo>();
		List<FileInfo> smallImages = new ArrayList<FileInfo>();
		for (FileInfo f : fList) {
			if (f.getFileType() == FileTypeEnum.BigImage) {
				bigImages.add(f);
			} else if (f.getFileType() == FileTypeEnum.SmallImage) {
				smallImages.add(f);
			}
		}
		try {
			mongo = gridFSConfig.mongo();
			DB db = mongo.getDB(gridFSConfig.getDatabase());
			GridFS gridFS = new GridFS(db);
			DBCollection files = db.getCollection("fs.files");
			DBCollection chunks = db.getCollection("fs.chunks");
			for (FileInfo fileInfo : smallImages) {
				String id = fileInfo.getDownload();
				gfsFile = gridFS.findOne(new ObjectId(id));
				files.remove(gfsFile);
			}
			for (FileInfo fileInfo : bigImages) {
				String id = fileInfo.getDownload();
				gfsFile = gridFS.findOne(new ObjectId(id));
				if (gfsFile == null) {
					throw new Exception("文件不存在");
				}
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				gfsFile.writeTo(out);
				out.flush();
				ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
				ByteArrayOutputStream out2 = new ByteArrayOutputStream();
				Thumbnails.of(in).size(64, 64).outputFormat("jpg").outputQuality(0.2).toOutputStream(out2);
				out2.flush();
				ByteArrayInputStream in2 = new ByteArrayInputStream(out2.toByteArray());
				String firstName = fileInfo.getFilename();
				if (firstName.indexOf(".") > -1) {
					firstName = firstName.substring(0, firstName.lastIndexOf("."));
				}
				FileInfo fileInfo2 = gridFSRepository.saveFile(in2, "samll_" + firstName + ".jpg", fileInfo.getContentType());
				if (fileInfo2 == null || StringUtils.isBlank(fileInfo2.getDownload())) {
					throw new Exception("保存错误");
				}
				fileInfo2.setStatus(StatusEnum.Use);
				fileInfo2.setFileType(FileTypeEnum.SmallImage);
				//fileInfo2.setOperatorId(fileInfo1.getOperatorId());
				fileInfoRepository.save(fileInfo2);
			}
		} catch (Exception e) {
			log.error("{}", e);
		} finally {
			mongo.close();
		}
		System.out.println("-------------------------------");
	}

}
