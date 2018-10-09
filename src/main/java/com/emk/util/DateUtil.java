/**
 * 
 */
package com.emk.util;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 
 * 日期工具类对象
 */
public class DateUtil {
	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public final static long DAY_MILLIS = 24 * 60 * 60 * 1000;

	public static String format(Date date, String format) {
		if (format == null) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 取指定某一天，以当前时间为基准，正数为加，负数为减，0为当天
	 * 
	 * @param days
	 * @return
	 */
	public static Date getDay(int days) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		if (days != 0) {
			c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
		}
		return c.getTime();
	}

	/**
	 * @param format
	 *            如"yyyy-MM-dd"的日期格式，如果为null,默认为"yyyy-MM-dd"格式
	 * @return 返回指定的日期格式
	 */
	public static String getTodayForString(String format) {
		SimpleDateFormat sdf = null;
		if (format == null) {
			sdf = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT);
		} else {
			sdf = new SimpleDateFormat(format);
		}
		return sdf.format(getDay(0));
	}

	/**
	 * 根据格式转换成日期
	 * 
	 * @param format
	 *            格式，默认为'yyyy-MM-dd'格式
	 * @param dateString
	 *            时间串
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String format, String dateString)
			throws ParseException {

		SimpleDateFormat sdf = null;
		Date date = null;
		if (format == null) {
			sdf = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT);
		} else {
			sdf = new SimpleDateFormat(format);
		}
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return date;
	}
	
	/**
	 * 按给定日期时间获得月初时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 按给定日期时间获得月末时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 按给定日期时间获得周初时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK,
				calendar.getActualMinimum(Calendar.DAY_OF_WEEK));

		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期的周末时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK,
				calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
		
		return calendar.getTime();
	}
	
	/**
	 * 按给定日期时间获得上一周周初时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfLastWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,-7);  
		calendar.set(Calendar.DAY_OF_WEEK,
				calendar.getActualMinimum(Calendar.DAY_OF_WEEK));

		return calendar.getTime();
	}
	
	/**
	 * 按给定日期时间获得上一周周末时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfLastWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,-7);  
		calendar.set(Calendar.DAY_OF_WEEK,
				calendar.getActualMaximum(Calendar.DAY_OF_WEEK));

		return calendar.getTime();
	}
	
	/**
	 * 获取给定日期的上个月月初时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获取给定日期的上个月月末时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfLastMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);//由于9月没有31号，所以溢出。如2014-10-31 13:35:56 的 上个月月末时间为 2014-10-01 13:35:56
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	/**
	 * 获取指定日期的周一时间，每周的第一天为周一
	 * @return
	 */
	public static Date getMonday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return c.getTime();
	}
	
	public static Date getDayByMonth(int months) {
		Calendar c = Calendar.getInstance();
		if (months != 0) {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)+months);
		}
		return c.getTime();
	}
	
	public static long getBigIntegerByStr(String posimap){
		String[] bytes = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
		String val ="";
		String[] posimapdata = posimap.split(",");
		for(int i =0 ; i <bytes.length ; i++){
			for(int j =0 ; j < posimapdata.length ; j++){
				if(posimapdata[j].equals(String.valueOf(i))){
					bytes[i]="1";
				}
			}
		}
		for(int i =0 ; i <bytes.length ; i++){
			val += bytes[bytes.length-1-i];
		}
		System.out.println(val);
		BigInteger src1= new BigInteger(val,2);//转换为BigInteger类型 
		System.out.println(src1.toString());	//转换为10进制并输出结果 
		return Long.parseLong(src1.toString());
	}
	
	public static String getCurrentTimeString(String formatDate) {
		if (formatDate == null) {
			formatDate = "yyyy-MM-dd HH:mm:ss";
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		return sdf.format(c.getTime());
	}

	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 开始日期
			Date end = format.parse(endDate);// 结束日期
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtnn = begin + (long) (Math.random() * (end - begin));
		if (rtnn == begin || rtnn == end) {
			return random(begin, end);
		}
		return rtnn;
	}


}
