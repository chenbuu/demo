package com.zjnu.bike.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * https://github.com/xetorthio/jedis
 * @author ChenTao
 * @date 2015年11月26日下午10:05:18
 */
public class RedisCluster {

	private static JedisCluster jedisCluster;

	static {
		//只给集群里一个实例就可以  
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 6380));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 6381));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 6382));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 6383));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 6384));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 7380));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 7381));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 7382));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 7383));
		jedisClusterNodes.add(new HostAndPort("10.10.34.14", 7384));
		jedisCluster = new JedisCluster(jedisClusterNodes);
	}

}
