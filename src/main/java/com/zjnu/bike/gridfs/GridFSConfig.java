package com.zjnu.bike.gridfs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.gridfs.GridFS;

import lombok.Getter;

/**
 * 文件数据库配置
 * @author ChenTao
 * @date 2015年12月1日下午3:25:03
 */
@Configuration
@Getter
public class GridFSConfig {

	@Value("${gridfs.data.mongodb.host}")
	private String host;

	@Value("${gridfs.data.mongodb.port}")
	private Integer port;

	@Value("${gridfs.data.mongodb.database}")
	private String database;

	@Value("${gridfs.data.mongodb.username}")
	private String username;

	@Value("${gridfs.data.mongodb.password}")
	private String password;

	@Value("${gridfs.data.mongodb.authenticationDatabase}")
	private String authenticationDatabase;

	public Mongo mongo() throws Exception {
		ServerAddress sa = new ServerAddress(host, port);
		List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
		mongoCredentialList.add(MongoCredential.createMongoCRCredential(username, authenticationDatabase, password.toCharArray()));
		return new MongoClient(sa, mongoCredentialList);
	}

	public MongoTemplate template() throws Exception {
		return new MongoTemplate(mongo(), database);
	}

	public DB db() throws Exception {
		return mongo().getDB(database);
	}

	public GridFS gridFS() throws Exception {
		return new GridFS(db());
	}
}
