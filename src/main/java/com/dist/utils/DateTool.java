/**
 * DateTool.java
 * 版权所有(C) 2013 北京博时远信技术有限公司
 * 创建:刘国龙 2013-5-09
 */
package com.dist.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.date.DateUtil;

/**
 * 时间处理工具
 * 
 * @author 刘国龙
 * @version 1.0
 */
public class DateTool {
	
	private static Logger log = LoggerFactory.getLogger(DateTool.class);

	/**
	 * 缺省的日期显示格式： yyyy-MM-dd
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_FORMAT_CON = "yyyyMMdd";
	public static final String DEFAULT_DATE_HOUR = "yyyy-MM-dd HH";
	public static final String DEFAULT_TIME = "HHmm";
	/**
	 * 缺省的日期时间显示格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_DATETIME = "yyyyMMddHHmmss";

	/**
	 * 1s中的毫秒数
	 */
	private static final int MILLIS = 1000;

	/**
	 * 一年当中的月份数
	 */
	private static final int MONTH_PER_YEAR = 12;

	/**
	 * 私有构造方法，禁止对该类进行实例化
	 */
	private DateTool() {
	}

	/**
	 * 得到系统当前日期时间
	 * 
	 * @return 当前日期时间
	 */
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 得到用缺省方式格式化的当前日期
	 * 
	 * @return 当前日期
	 */
	public static String getDefaultTime() {
		return getDateTime(DEFAULT_TIME);
	}

	/**
	 * 得到用缺省方式格式化的当前日期 格式：yyyy-MM-dd
	 * 
	 * @return 当前日期
	 */
	public static String getDate() {
		return getDateTime(DEFAULT_DATE_FORMAT);
	}

	public static String getCurrTime() {
		return getDateTime(DEFAULT_DATE_FORMAT_CON);
	}

	/**
	 * 得到用缺省方式格式化的当前日期及时间 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前日期及时间
	 */
	public static String getDateTime() {
		return getDateTime(DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * 得到用缺省方式格式化的当前日期及时间 格式：yyyyMMddHHmmss
	 * 
	 * @return 当前日期及时间
	 */
	public static String getDefaultDateTime() {
		return getDateTime(DEFAULT_DATETIME);
	}

	/**
	 * 得到系统当前日期及时间，并用指定的方式格式化
	 * 
	 * @param pattern
	 *            显示格式
	 * @return 当前日期及时间
	 */
	public static String getDateTime(String pattern) {
		Date datetime = Calendar.getInstance().getTime();
		return getDateTime(datetime, pattern);
	}

	/**
	 * 得到用指定方式格式化的日期
	 * 
	 * @param date
	 *            需要进行格式化的日期
	 * @param pattern
	 *            显示格式
	 * @return 日期时间字符串
	 */
	public static String getDateTime(Date date, String pattern) {
		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATETIME_FORMAT;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  
		String s = "0000-00-00 00:00:00";
		try {
			s=dateFormat.format(date);
		} catch (Exception e) {
			//如果日期为null 则返回这个000
			log.info("格式化失败,失败的原因是:"+e.toString());
		}
		return s;
	}

	/**
	 * 得到当前年份
	 * 
	 * @return 当前年份
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);

	}

	/**
	 * 得到当前月份
	 * 
	 * @return 当前月份
	 */
	public static int getCurrentMonth() {
		// 用get得到的月份数比实际的小1，需要加上
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前日
	 * 
	 * @return 当前日
	 */
	public static int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	/**
	 * 取得当前日期以后若干天的日期。如果要得到以前的日期，参数用负数。 例如要得到上星期同一天的日期，参数则为-7
	 * 
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date addDays(int days) {
		return add(getNow(), days, Calendar.DATE);
	}

	/**
	 * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
	 * 
	 * @param date
	 *            基准日期
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date addDays(Date date, int days) {
		return add(date, days, Calendar.DATE);
	}

	/**
	 * 取得当前日期以后某月的日期。如果要得到以前月份的日期，参数用负数。
	 * 
	 * @param months
	 *            增加的月份数
	 * @return 增加以后的日期
	 */
	public static Date addMonths(int months) {
		return add(getNow(), months, Calendar.MONTH);
	}

	/**
	 * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数。 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
	 * 
	 * @param date
	 *            基准日期
	 * @param months
	 *            增加的月份数
	 * @return 增加以后的日期
	 */
	public static Date addMonths(Date date, int months) {
		return add(date, months, Calendar.MONTH);
	}

	/**
	 * 内部方法。为指定日期增加相应的天数或月数
	 * 
	 * @param date
	 *            基准日期
	 * @param amount
	 *            增加的数量
	 * @param field
	 *            增加的单位，年，月或者日
	 * @return 增加以后的日期
	 */
	private static Date add(Date date, int amount, int field) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.add(field, amount);

		return calendar.getTime();
	}

	/**
	 * 获取某一天
	 * 
	 * @param amount
	 * @return
	 */
	public static String deleteTime(int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, amount);
		String newday = new SimpleDateFormat("yyyy-MM-dd")
				.format(cal.getTime());
		return newday + " 23:59:59";
	}

	/**
	 * 通过date对象取得格式为小时:分钟的实符串
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getHourMin(Date date) {
		StringBuffer sf = new StringBuffer();
		sf.append(date.getHours());
		sf.append(":");
		sf.append(date.getMinutes());
		return sf.toString();
	}

	/**
	 * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差天数
	 */
	public static long diffDays(Date one, Date two) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(one);
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONDAY), calendar
						.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date d1 = calendar.getTime();
		calendar.clear();
		calendar.setTime(two);
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONDAY), calendar
						.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date d2 = calendar.getTime();
		final int MILISECONDS = 24 * 60 * 60 * 1000;
		BigDecimal r = new BigDecimal(new Double((d1.getTime() - d2.getTime()))
				/ MILISECONDS);
		return Math.round(r.doubleValue());
	}

	/**
	 * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差月份数
	 */
	public static int diffMonths(Date one, Date two) {

		Calendar calendar = Calendar.getInstance();

		// 得到第一个日期的年分和月份数
		calendar.setTime(one);
		int yearOne = calendar.get(Calendar.YEAR);
		int monthOne = calendar.get(Calendar.MONDAY);
		// 得到第二个日期的年份和月份
		calendar.setTime(two);
		int yearTwo = calendar.get(Calendar.YEAR);
		int monthTwo = calendar.get(Calendar.MONDAY);

		return (yearOne - yearTwo) * MONTH_PER_YEAR + (monthOne - monthTwo);
	}

	/**
	 * 获取某一个日期的年份
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 将一个字符串用给定的格式转换为日期类型。 <br>
	 * 注意：如果返回null，则表示解析失败
	 * 
	 * @param datestr
	 *            需要解析的日期字符串
	 * @param pattern
	 *            日期字符串的格式，默认为"yyyy-MM-dd"的形式
	 * @return 解析后的日期
	 */
	public static Date parse(String datestr, String pattern) {
		Date date = null;

		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATE_FORMAT;
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			date = dateFormat.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 返回本月的最后一天
	 * 
	 * @return 本月最后一天的日期
	 */
	public static Date getMonthLastDay() {
		return getMonthLastDay(getNow());
	}

	/**
	 * 返回给定日期中的月份中的最后一天
	 * 
	 * @param date
	 *            基准日期
	 * @return 该月最后一天的日期
	 */
	public static Date getMonthLastDay(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 将日期设置为下一月第一天
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, 1);

		// 减去1天，得到的即本月的最后一天
		calendar.add(Calendar.DATE, -1);

		return calendar.getTime();
	}

	/**
	 * 计算两个具体日期之间的秒差，第一个日期-第二个日期
	 * 
	 * @param date1
	 * @param date2
	 * @param onlyTime
	 *            是否只计算2个日期的时间差异，忽略日期，true代表只计算时间差
	 * @return
	 */
	public static long diffSeconds(Date date1, Date date2, boolean onlyTime) {
		if (onlyTime) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			// calendar.set(1984, 5, 24);
			long t1 = calendar.getTimeInMillis();
			calendar.setTime(date2);
			// calendar.set(1984, 5, 24);
			long t2 = calendar.getTimeInMillis();
			return (t1 - t2) / MILLIS;
		} else {
			return (date1.getTime() - date2.getTime()) / MILLIS;
		}
	}

	/**
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long diffSeconds(Date date1, Date date2) {
		return diffSeconds(date1, date2, false);
	}

	/**
	 * 根据日期确定星期几:1-星期日，2-星期一.....s
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int mydate = cd.get(Calendar.DAY_OF_WEEK);
		return mydate;
	}

	/**
	 * 验证用密码是否在有效期内(跟当前日期比较)
	 * 
	 * @param format
	 *            "yyyyMMdd"
	 * @param validDate
	 * @return
	 */
	public static boolean afterDate(String validDate, String format) {
		Date valid = parse(validDate, format);
		Date now = new Date();
		String nowStr = new SimpleDateFormat(format).format(now);
		try {
			now = new SimpleDateFormat(format).parse(nowStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return valid.after(now);
	}

	public static boolean beforeDate(String validDate, String format) {
		Date valid = parse(validDate, format);
		Date now = new Date();
		String nowStr = new SimpleDateFormat(format).format(now);
		try {
			now = new SimpleDateFormat(format).parse(nowStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return valid.before(now);
	}
	
	/**
	 * 判断活动时间
	 * 多个时间段之间以逗号分隔
	 * true 可以登陆， false 不能登陆
	 * @return
	 */
	public static boolean judgeActiveTime(String activeTime){
		boolean flag = false;
		try {
//			String activeTime = "09:00-11:30,12:00-13:30,15:00-16:00";
			activeTime = activeTime.replaceAll("：", "");
			activeTime = activeTime.replaceAll(":", "");
			activeTime = activeTime.replaceAll("，", ",");
			activeTime = activeTime.replaceAll("——", "-");
			String[] times = activeTime.split(";");
			for (int i = 0; i < times.length; i++) {
				String[] time = times[i].split("-");
				Long currentTime = Long.valueOf(DateTool.getDefaultTime());
				Long startTime = Long.valueOf(time[0]);
				Long endTime = Long.valueOf(time[1]);
				if (currentTime >= startTime && currentTime <= endTime) {
					flag = true;
				}
			}
		} catch (NumberFormatException e) {
			flag = false; 
		}
		return flag;
	}
	
	public static long intervalDate (Date preDate) {
		Date nowDate = new Date();
		Long nowDateMs= nowDate.getTime();
		Long preDateMs= preDate.getTime();
		return nowDateMs-preDateMs;
	}
	
	public static void main(String[] args) {
//		System.out.println(getDateTime(null,DEFAULT_DATETIME_FORMAT));
		//然后计算时间
		
		int s = 3599;
		
		
	}
	
}
