package com.zjnu.bike.redis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Repository;

/**
 * Redis手动管理类
 * @author ChenTao
 * @date 2015年11月25日下午6:44:25
 */
@Repository
public class RedisRepository {

	/**
	 * 缓存过期时间
	 */
	private static final int expireTime = 1;
	/**
	 * 缓存过期时间单位
	 */
	private static final TimeUnit expireTimeUnit = TimeUnit.HOURS;
	/**
	 * 单次保存List数量上限
	 */
	private static final int maxNumber = 30;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 获取RedisTemplate
	 * @author ChenTao
	 * @date 2015年11月25日下午6:44:43
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> RedisTemplate<String, Object> getRedis(Class<T> clazz) throws Exception {
		RedisTemplate<String, Object> redis = new RedisTemplate<String, Object>();
		redis.setConnectionFactory(stringRedisTemplate.getConnectionFactory());
		redis.setKeySerializer(stringRedisTemplate.getKeySerializer());
		redis.setValueSerializer(new Jackson2JsonRedisSerializer(clazz));
		redis.afterPropertiesSet();
		return redis;
	}

	/**
	 * 是否存在缓存key
	 * @author ChenTao
	 * @date 2015年11月25日下午6:58:16
	 */
	public <T> Boolean hasKey(String key, Class<T> clazz) throws Exception {
		String redisKey = clazz.getName() + "_" + key;
		if (stringRedisTemplate.hasKey(redisKey)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取缓存Object
	 * @author ChenTao
	 * @date 2015年11月25日下午6:59:13
	 */
	@SuppressWarnings("unchecked")
	public <T> T getObject(String key, Class<T> clazz) throws Exception {
		String redisKey = clazz.getName() + "_" + key;
		System.out.println(redisKey);
		ValueOperations<String, Object> ops = getRedis(clazz).opsForValue();
		T mod = (T) ops.get(redisKey);
		return mod;
	}

	/**
	 * 保存缓存Object
	 * @author ChenTao
	 * @date 2015年11月25日下午6:59:13
	 */
	public <T> Boolean setObject(T t) throws Exception {
		Method getId = t.getClass().getMethod("getId");
		String id = (String) getId.invoke(t);
		if (!StringUtils.isBlank(id)) {
			String clazz = t.getClass().getName();
			String redisKey = clazz + "_" + id;
			ValueOperations<String, Object> ops = getRedis(t.getClass()).opsForValue();
			ops.set(redisKey, t, expireTime, expireTimeUnit);
			return true;
		}
		return false;
	}

	/**
	 * 获取缓存List
	 * @author ChenTao
	 * @date 2015年11月25日下午7:53:44
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(List<String> keys, Class<T> clazz) throws Exception {
		String clazzStr = clazz.getName();
		List<String> redisKeys = new ArrayList<>();
		for (String key : keys) {
			redisKeys.add(clazzStr + "_" + key);
		}
		ValueOperations<String, Object> ops = getRedis(clazz).opsForValue();
		List<T> mods = (List<T>) ops.multiGet(redisKeys);
		return mods;
	}

	/**
	 * 保存缓存List
	 * 超过maxNumber触发跳过保存，ts.size<=maxNumber
	 * @author ChenTao
	 * @date 2015年11月25日下午6:59:13
	 */
	public <T> Boolean setList(List<T> ts) throws Exception {
		if (ts != null && ts.size() > 0 && ts.size() <= maxNumber) {
			String clazz = ts.get(0).getClass().getName();
			Method getId = ts.get(0).getClass().getMethod("getId");
			Map<String, T> map = new HashMap<>();
			for (T t : ts) {
				String id = (String) getId.invoke(t);
				String redisKey = clazz + "_" + id;
				map.put(redisKey, t);
			}
			ValueOperations<String, Object> ops = getRedis(ts.get(0).getClass()).opsForValue();
			ops.multiSet(map);
			for (String key : map.keySet()) {
				stringRedisTemplate.expire(key, expireTime, expireTimeUnit);
			}
			return true;
		}
		return false;
	}
}
