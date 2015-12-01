package com.zjnu.bike.test;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bson.types.ObjectId;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mongodb.gridfs.GridFSDBFile;
import com.zjnu.bike.Application;
import com.zjnu.bike.domain.FileInfo;
import com.zjnu.bike.gridfs.FileObject;
import com.zjnu.bike.gridfs.GridFSConfig;
import com.zjnu.bike.gridfs.GridFSRepository;
import com.zjnu.bike.util.RandUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ChenTao
 * @date 2015年12月1日下午3:06:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Slf4j
public class SampleGridFsApplicationTests {

	@Autowired
	private GridFSRepository gridFSRepository;

	@Autowired
	private GridFSConfig gridFsConfig;

	@Test
	@Ignore
	public void testMongo() throws Exception {
		System.out.println("-------------------------------");
		FileObject fileObject = new FileObject();
		fileObject.setFilename(RandUtil.getStringChineseRange(3, 6));
		gridFSRepository.saveFileObject(fileObject);
		log.debug("{}", fileObject);
		System.out.println("-------------------------------");
	}

	@Test
	public void testMongo2() throws Exception {
		System.out.println("-------------------------------");
		File f = new File("C:/Users/tao/Desktop/测试图片/1095413.png");
		FileInfo fileInfo = gridFSRepository.saveFile(f);
		log.debug("{}", fileInfo);

		GridFSDBFile gfsFile;
		gfsFile = gridFSRepository.findOne(new ObjectId("565d579faeabb3ce93209376"));
		try (InputStream is = gfsFile.getInputStream()) {
			Files.copy(is, Paths.get("F:/", gfsFile.getFilename()));
		}
		System.out.println("-------------------------------");
	}

}
