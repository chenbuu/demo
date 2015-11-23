package com.zjnu.bike.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间格式化工具
 * @author ChenTao
 * @date 2015年7月22日上午12:14:08
 */
@SuppressWarnings("all")
public class DateFormat {

	/**
	 * getDateStr get a string with format YYYY-MM-DD from a Date object
	 *
	 * @param date date
	 * @return String
	 */
	static public String getDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	static public String getYearStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(date);
	}

	static public String getDateStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	static public String getDateStrCompact(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}

	static public String getDateStrCompact2(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = format.format(date);
		return str;
	}

	static public String getDateTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	static public String getDateTimeStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	public static String getCurDateStr(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	public static String getCurDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	public static String getUploadDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}

	/**
	 * Parses text in 'YYYY-MM-DD' format to produce a date.
	 *
	 * @param s the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDate(String s) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	static public String parseDateToString(String s) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.format(format.parse(s));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	static public Date parseDateC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.parse(s);
	}

	static public Date parseDateTime(String s) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	static public Date parseDateTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.parse(s);
	}

	static public Date parseTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.parse(s);
	}

	static public Date parseTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss日");
		return format.parse(s);
	}

	/**
	 * Parses text in 'YYYY-MM-DD' format to int.
	 *
	 * @param s the text
	 * @return int
	 * @throws ParseException
	 */

	static public int yearOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(0, 4));
	}

	static public int monthOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(5, 7));
	}

	static public int dayOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(8, 10));
	}

	@SuppressWarnings("deprecation")
	static public String getDateTimeStr(java.sql.Date date, double time) {
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		String dateStr = year + "-" + month + "-" + day;
		Double d = new Double(time);
		String timeStr = String.valueOf(d.intValue()) + ":00:00";

		return dateStr + " " + timeStr;
	}

	/**
	 * Get the total month from two date.
	 *
	 * @param sd the start date
	 * @param ed the end date
	 * @return int month form the start to end date
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	static public int diffDateM(Date sd, Date ed) throws ParseException {
		return (ed.getYear() - sd.getYear()) * 12 + ed.getMonth() - sd.getMonth() + 1;
	}

	static public int diffDateD(Date sd, Date ed) throws ParseException {
		return Math.round((ed.getTime() - sd.getTime()) / 86400000) + 1;
	}

	static public int diffDateM(int sym, int eym) throws ParseException {
		return (Math.round(eym / 100) - Math.round(sym / 100)) * 12 + (eym % 100 - sym % 100) + 1;
	}

	static public java.sql.Date getNextMonthFirstDate(java.sql.Date date) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.set(Calendar.DATE, 1);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	static public java.sql.Date getFrontDateByDayCount(java.sql.Date date, int dayCount) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, -dayCount);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	/**
	 * Get first day of the month.
	 *
	 * @param year the year
	 * @param month the month
	 * @return Date first day of the month.
	 * @throws ParseException
	 */
	static public Date getFirstDay(String year, String month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getLastDay(String year, String month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	static public Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	/**
	 * getToday get todat string with format YYYY-MM-DD from a Date object
	 *
	 * @param date date
	 * @return String
	 */
	static public String getTodayStr() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStr(calendar.getTime());
	}

	static public Date getToday() throws ParseException {
		return new Date(System.currentTimeMillis());
	}

	static public String getTodayAndTime() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	static public String getTodayC() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStrC(calendar.getTime());
	}

	@SuppressWarnings("deprecation")
	static public int getThisYearMonth() throws ParseException {
		Date today = Calendar.getInstance().getTime();
		return (today.getYear() + 1900) * 100 + today.getMonth() + 1;
	}

	@SuppressWarnings("deprecation")
	static public int getYearMonth(Date date) throws ParseException {
		return (date.getYear() + 1900) * 100 + date.getMonth() + 1;
	}

	// 获取相隔月数
	@SuppressWarnings("deprecation")
	static public long getDistinceMonth(String beforedate, String afterdate) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long monthCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth() - d1.getMonth();
			// dayCount = (d2.getTime()-d1.getTime())/(30*24*60*60*1000);

		} catch (ParseException e) {
			// throw e;
		}
		return monthCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(String beforedate, String afterdate) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			// throw e;
		}
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(Date beforedate, Date afterdate) throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime()) / (24 * 60 * 60 * 1000);

		} catch (Exception e) {
		}
		return dayCount;
	}

	static public long getDistinceDay(java.sql.Date beforedate, java.sql.Date afterdate) throws ParseException {
		long dayCount = 0;

		try {
			dayCount = (afterdate.getTime() - beforedate.getTime()) / (24 * 60 * 60 * 1000);

		} catch (Exception e) {
		}
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(String beforedate) throws ParseException {
		return getDistinceDay(beforedate, getTodayStr());
	}

	// 获取相隔时间日
	static public long getDistinceTime(String beforeDateTime, String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long timeCount = 0;
		try {
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);

		} catch (ParseException e) {
			throw e;
		}
		return timeCount;
	}

	// 获取相隔时间日
	@SuppressWarnings("deprecation")
	static public long getDistinceTime(String beforeDateTime) throws ParseException {
		return getDistinceTime(beforeDateTime, new Timestamp(System.currentTimeMillis()).toLocaleString());
	}

	/**
	 * 去掉多余精确度
	 * @author ChenTao
	 * @date 2015年8月19日上午11:27:55
	 */
	static public String filterDateTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(format.parse(s));
	}

}
