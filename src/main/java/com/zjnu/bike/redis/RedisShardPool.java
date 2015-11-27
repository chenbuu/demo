package com.zjnu.bike.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

/**
 * Redis哈稀分片连接池
 * 注意：推荐用redis cluster集群，需要redis3.0以上版本，redis自带的功能
 * @author ChenTao
 * @date 2015年11月26日下午3:36:21
 */
public class RedisShardPool {

	static ShardedJedisPool pool;

	static {

		JedisPoolConfig config = new JedisPoolConfig();//Jedis池配置

		config.setMaxTotal(500);//最大活动的对象个数

		config.setMaxIdle(1000 * 60);//对象最大空闲时间

		config.setMaxWaitMillis(1000 * 10);//获取对象时最大等待时间

		config.setTestOnBorrow(true);

		String hostA = "10.7.90.2010";//修改IP

		int portA = 6379;

		String hostB = "10.7.90.2000";//修改IP

		int portB = 6379;

		List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);

		JedisShardInfo infoA = new JedisShardInfo(hostA, portA);

		infoA.setPassword("123456");

		JedisShardInfo infoB = new JedisShardInfo(hostB, portB);

		infoB.setPassword("123456");

		jdsInfoList.add(infoA);

		jdsInfoList.add(infoB);

		pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,

		Sharded.DEFAULT_KEY_TAG_PATTERN);

	}
}
