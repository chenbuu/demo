package com.zjnu.bike.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Map工具,获取Map中第几个键值对
 * @author ChenTao
 * @date 2015年8月14日上午9:04:31
 */
public class MapUtil {

	/**
	 * 获取Map中第几个键值对
	 * @author ChenTao
	 * @date 2015年8月14日上午9:09:28
	 */
	public static Map<Integer, String> getValueMap(Map<String, Object> map) {
		map.keySet();
		int i = map.size() - 1;
		Map<Integer, String> mapValue = new HashMap<Integer, String>();
		for (Entry<String, Object> entry : map.entrySet()) {
			//将原来MAP的KEY放入新的MAP的VALUE 里面
			mapValue.put(i, entry.getKey());
			i--;
		}
		return mapValue;
	}

	/**
	 * 获取Map中第几个键值对
	 * @author ChenTao
	 * @date 2015年8月14日上午9:09:28
	 */
	public static Object getValueObject(Map<String, Object> map, Integer integer) {
		int i = 0;
		for (Object object : map.values()) {
			if (integer == i) {
				return object;
			}
			i++;
		}
		return null;
	}

	/**
	 * 获取Map中第几个键值对
	 * @author ChenTao
	 * @date 2015年8月14日上午9:09:28
	 */
	public static String getValueString(Map<String, Object> map, Integer integer) {
		int i = 0;
		for (Object object : map.values()) {
			if (integer == i) {
				return String.valueOf(object);
			}
			i++;
		}
		return null;
	}

	/**
	 * 获取Map中第几个键值对
	 * @author ChenTao
	 * @date 2015年8月14日上午9:09:31
	 */
	public static Map<Integer, String> getKeyMap(Map<String, Object> map) {
		map.keySet();
		int i = map.size() - 1;
		Map<Integer, String> mapKey = new HashMap<Integer, String>();
		for (Entry<String, Object> entry : map.entrySet()) {
			//将原来MAP的VALUE放入新的MAP的VALUE里面
			mapKey.put(i, String.valueOf(entry.getValue()));
			i--;
		}
		return mapKey;
	}

}
