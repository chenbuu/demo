package com.zjnu.bike.util;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具
 * @author ChenTao
 * @date 2015年8月5日上午10:02:03
 */
public class RandUtil {
	private static final String baseChar = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static final String allChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String lowerChar = "abcdefghijklmnopqrstuvwxyz";
	private static final String numberChar = "0123456789";
	//最常用的500个汉字
	private static final String chineseChar = "的一是在不了有和人这中大为上个国我以要他时来用们生到作地于出就分对成会可主发年动同工也能下过子说产种面而方后多定行学法所民得经十三之进着等部度家电力里如水化高自二理起小物现实加量都两体制机当使点从业本去把"
			+ "那社义事平形相全表间样与关各重新线内数正心反你明看原又么利比或但质气第向道命此变条只没结解问意建月公无系军很情者最立代想已通并提直题党程展五果料象员革位入常文总次品式活设及管特件长求老头基资边流路级少图山统接知较将组见计别她手角期根论运农指"
			+ "几九区强放决西被干做必战先回则任取据处队南给色光门即保治北造百规热领七海口东导器压志世金增争济阶油思术极交受联什认六共权收证改清己美再采转更单风切打白教速花带安场身车例真务具万每目至达走积示议声报斗完类八离华名确才科张信马节话米整空元况今集"
			+ "温传土许步群广石记需段研界拉林律叫且究观越织装影算低持音众书布复容儿须际商非验连断深难近矿千周委素技备半办青省列习响约支般史感劳便团往酸历市克何除消构府称太准精值号率族维划选标写存候毛亲快效斯院查江型眼王按格养易置派层片始却专状育厂京识适属"
			+ "圆包火住调满县局照参红细引听该铁价严龙飞性好应开它合还因由其些然前外天政四日";

	private static final String[] emailArray = { "@qq.com", "@163.com", "@126.com", "@189.com", "@263.com", "@21cn.com", "@yeah.com", "@sina.com",
			"@hotmail.com", "@gmail.com", "@foxmail.com", "@outlook.com" };

	/**
	 * 随机字符串
	 * 参数：生成字符串的长度length
	 * 范围："abcdefghijklmnopqrstuvwxyz0123456789"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getString(int length) {
		Random random = ThreadLocalRandom.current();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(baseChar.length());
			str[i] = baseChar.charAt(number);
		}
		return new String(str);
	}

	/**
	 * 随机字符串
	 * 参数：范围[Min,Max]
	 * 范围："abcdefghijklmnopqrstuvwxyz0123456789"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringRange(Integer min, Integer max) {
		return getString(getInteger(min, max));
	}

	/**
	 * 随机Email
	 * 参数：无
	 * 范围："abcdefghijklmnopqrstuvwxyz0123456789"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getEmail() {
		return getStringLowerLetterRange(8, 16) + emailArray[getInteger(1, 12) - 1];
	}

	/**
	 * 随机字符串
	 * 参数：生成字符串的长度length
	 * 范围："abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringAll(int length) {
		Random random = ThreadLocalRandom.current();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(allChar.length());
			str[i] = allChar.charAt(number);
		}
		return new String(str);
	}

	/**
	 * 随机字符串
	 * 参数：范围[Min,Max]
	 * 范围："abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringAllRange(Integer min, Integer max) {
		return getStringAll(getInteger(min, max));
	}

	/**
	 * 随机字符串
	 * 参数：生成字符串的长度length
	 * 范围："abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringLetter(int length) {
		Random random = ThreadLocalRandom.current();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(letterChar.length());
			str[i] = letterChar.charAt(number);
		}
		return new String(str);
	}

	/**
	 * 随机字符串
	 * 参数：范围[Min,Max]
	 * 范围："abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringLetterRange(Integer min, Integer max) {
		return getStringLetter(getInteger(min, max));
	}

	/**
	 * 随机字符串
	 * 参数：生成字符串的长度length
	 * 范围："abcdefghijklmnopqrstuvwxyz"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringLowerLetter(int length) {
		Random random = ThreadLocalRandom.current();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(lowerChar.length());
			str[i] = lowerChar.charAt(number);
		}
		return new String(str);
	}

	/**
	 * 随机字符串
	 * 参数：范围[Min,Max]
	 * 范围："abcdefghijklmnopqrstuvwxyz"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringLowerLetterRange(Integer min, Integer max) {
		return getStringLowerLetter(getInteger(min, max));
	}

	/**
	 * 随机字符串
	 * 参数：生成字符串的长度length
	 * 范围："0123456789"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringNumber(int length) {
		Random random = ThreadLocalRandom.current();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(numberChar.length());
			str[i] = numberChar.charAt(number);
		}
		return new String(str);
	}

	/**
	 * 随机字符串
	 * 参数：范围[Min,Max]
	 * 范围："0123456789"
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringNumberRange(Integer min, Integer max) {
		return getStringNumber(getInteger(min, max));
	}

	/**
	 * 随机字符串
	 * 参数：生成字符串的长度length
	 * 范围：常用的500个汉字
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringChinese(int length) {
		Random random = ThreadLocalRandom.current();
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(chineseChar.length());
			str[i] = chineseChar.charAt(number);
		}
		return new String(str);
	}

	/**
	 * 随机字符串
	 * 参数：范围[Min,Max]
	 * 范围：常用的500个汉字
	 * @author ChenTao
	 * @date 2015年7月20日下午9:27:01
	 */
	public static String getStringChineseRange(Integer min, Integer max) {
		return getStringChinese(getInteger(min, max));
	}

	/**
	 * 随机Integer数字
	 * 参数：范围[Min,Max]
	 * @author ChenTao
	 * @date 2015年8月5日上午9:38:35
	 */
	public static Integer getInteger(Integer min, Integer max) {
		Random random = ThreadLocalRandom.current();
		//nextInt(int n)该方法的作用是生成一个随机的int值，该值介于[0,n)的区间
		Integer temp = random.nextInt(max) + min;
		return temp;
	}

	/**
	 * 随机Double数字
	 * 参数：范围[Min,Max)
	 * @author ChenTao
	 * @date 2015年8月5日上午10:18:44
	 */
	public static Double getDouble(Double min, Double max) {
		Random random = ThreadLocalRandom.current();
		Double temp = random.nextDouble() * (max - min) + min;
		return temp;
	}

	/**
	 * 随机Boolean
	 * @author ChenTao
	 * @date 2015年8月5日上午10:18:44
	 */
	public static Boolean getBoolean() {
		Random random = ThreadLocalRandom.current();
		return random.nextBoolean();
	}

	/**
	 * 获取当前时间
	 * @author ChenTao
	 * @date 2015年8月5日上午10:18:44
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取当前时间
	 * @author ChenTao
	 * @date 2015年8月5日上午10:18:44
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 随机Enum
	 * @author ChenTao
	 * @date 2015年8月5日上午10:18:44
	 */
	public static <T> T getEnum(T[] values) {
		Random random = ThreadLocalRandom.current();
		return values[random.nextInt(values.length)];
	}

	/**
	 * 随机Enum
	 * @author ChenTao
	 * @date 2015年8月5日上午10:18:44
	 */
	public static <T extends Enum<T>> T getEnum(Class<T> ec) {
		return getEnum(ec.getEnumConstants());
	}

	/**
	 * 随机List
	 * @author ChenTao
	 * @param <T>
	 * @date 2015年8月5日上午10:18:44
	 */
	public static <T> List<T> getList(List<T> values, Integer num) {
		Integer n = getInteger(1, num);
		List<T> tList = new ArrayList<T>();
		List<T> dateList = new ArrayList<T>();
		dateList.addAll(values);
		Collections.shuffle(dateList);
		for (int i = 0; i < n; i++) {
			tList.add(dateList.get(i));
		}
		return tList;
	}

	/**
	 * 随机Object
	 * @author ChenTao
	 * @param <T>
	 * @date 2015年8月5日上午10:18:44
	 */
	public static <T> T getObject(List<T> values) {
		Random random = ThreadLocalRandom.current();
		return values.get(random.nextInt(values.size()));
	}

	/**
	 * 随机ObjectId
	 * @author ChenTao
	 * @param <T>
	 * @date 2015年8月5日上午10:18:44
	 */
	public static <T> String getObjectId(List<T> values) {
		T t = getObject(values);
		try {
			Method getId = t.getClass().getMethod("getId");
			return (String) getId.invoke(t);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 随机属性
	 * @author ChenTao
	 * @param <T>
	 * @param <V>
	 * @date 2015年12月2日上午12:09:47
	 */
	@SuppressWarnings("unchecked")
	public static <T, V> V getAttr(List<T> values, String attrMethod, Class<V> ec) {
		T t = getObject(values);
		try {
			Method method = t.getClass().getMethod(attrMethod);
			return (V) method.invoke(t);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}