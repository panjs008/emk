package com.emk.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


public class Utils {

    /**
     * 字符串是否为空串
     *
     * @param str
     * @return null,"" ," " 均算作空串
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param col
     * @return
     */
    public static boolean isEmpty(Collection col) {
        if (col == null || col.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断数组是否为空
     *
     * @param col
     * @return
     */
    public static boolean isEmpty(Object[] col) {
        if (col == null || col.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是否空指针
     *
     * @param obj
     * @return
     */
    public static boolean isNULL(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断long是否为0
     *
     * @param l
     * @return
     */
    public static boolean isEmpty(long l) {
        if (l == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss 格式
     * @return
     */
    public static boolean isDateFormat(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dateStr == null || dateStr.length() != 19) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr yyyyMMddHHmmss 格式
     * @return
     */
    public static boolean isDateFormat2(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        if (dateStr == null || dateStr.length() != 14) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr yyyy-MM-dd HH:mm 格式
     * @return
     */
    public static boolean isDateFormat3(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (dateStr == null || dateStr.length() != 16) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr HH:mm:ss 格式
     * @return
     */
    public static boolean isTimeFormat(String dateStr) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        if (dateStr == null || dateStr.length() != 8) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr HH:mm 格式
     * @return
     */
    public static boolean isTimeFormat2(String dateStr) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        if (dateStr == null || dateStr.length() != 5) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr yyyy-MM-dd 格式
     * @return
     */
    public static boolean isDayFormat(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (dateStr == null || dateStr.length() != 10) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr yyyy-MM 格式
     * @return
     */
    public static boolean isMonthFormat(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        if (dateStr == null || dateStr.length() != 7) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }/**
     * 判断时间格式是否正确
     *
     * @param dateStr yyyy-MM 格式
     * @return
     */
    public static boolean isYearFormat(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy");
        if (dateStr == null || dateStr.length() != 4) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断时间格式是否正确
     *
     * @param dateStr yyyy 格式
     * @return
     */
    public static boolean isDayFormat5(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy");
        if (dateStr == null || dateStr.length() != 4) {
            return false;
        }
        try {
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否可转换为Long
     *
     * @param str
     * @return true 表示可以，false表示不可以
     */
    public static boolean isLong(String str) {
        try {
            Long l = Long.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否可转换为Integer
     *
     * @param str
     * @return true 表示可以，false表示不可以
     */
    public static boolean isInteger(String str) {
        try {
            Integer l = Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否可转换为Long
     *
     * @param str
     * @return true 表示可以，false表示不可以
     */
    public static boolean isDouble(String str) {
        try {
            Double d = Double.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 替代默认println,方便生产环境去除System.out.println
     *
     * @param msg
     */
    public static void P(Object msg) {
        boolean isDebug = false;
        if (isDebug) {
            System.out.println(msg);
        }
    }

    /**
     * 替代默认println,方便生产环境去除System.out.println
     *
     * @param msg  "hi,{0}"
     * @param args xx
     */
    public static void P(String msg, Object... args) {
        if (args == null || args.length == 0) {
            P(msg);
        } else {
            P(MessageFormat.format(msg, args));
        }
    }

    /**
     * 当对象为空指针时 设置默认值
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static <T> T defaultValue(T value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 创建map
     *
     * @param key
     * @param value
     * @return HashMap
     */
    public static <K, V> Map<K, V> createMap(K key, V value) {
        Map<K, V> m = new HashMap<K, V>();
        m.put(key, value);
        return m;
    }

    /**
     * 创建List
     *
     * @param value
     * @return
     */
    public static <E> List<E> createList(E... value) {
        List<E> l = new ArrayList<E>();
        for (E e : value) {
            l.add(e);
        }
        return l;
    }

    /**
     * 转换为数字
     *
     * @param obj
     * @param def 异常出错时返回默认值
     * @return
     */
    public static int parseInt(Object obj, int def) {
        if (obj == null) {
            return def;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            Utils.P(e);
        }
        return def;
    }

    /**
     * 转换为数字
     *
     * @param obj
     * @return
     */
    public static Integer parseInt(Object obj) throws Exception {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        String number = obj.toString();
        if (isEmpty(number)) {
            return null;
        }
        return Integer.parseInt(number);
    }

    /**
     * 转换为数字
     *
     * @param obj
     * @param def 异常出错时返回默认值
     * @return
     */
    public static long parseLong(Object obj, long def) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            String number = obj.toString();
            if (number.charAt(number.length() - 1) == 'L' || number.charAt(number.length() - 1) == 'l') {
                number = number.substring(0, number.length() - 1);
            }
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            Utils.P(e);
        }
        return def;
    }

    /**
     * 转换为数字对象，可为null
     *
     * @param obj
     * @return
     */
    public static Long parseLong(Object obj) throws Exception {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        String number = obj.toString();
        if (isEmpty(number)) {
            return null;
        }
        if (number.charAt(number.length() - 1) == 'L' || number.charAt(number.length() - 1) == 'l') {
            number = number.substring(0, number.length() - 1);
        }
        return Long.parseLong(number);
    }

    /**
     * 转换为数字
     *
     * @param obj
     * @param def 异常出错时返回默认值
     * @return
     */
    public static Float parseFloat(Object obj, Float def) {
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        if (obj != null) {
            try {
                return Float.parseFloat(obj.toString());
            } catch (Exception e) {
                Utils.P(e);
            }
        }
        return def;
    }
    /**
     * 转换为数字
     *
     * @param obj
     * @return
     */
    public static Double parseDouble(Object obj)throws NumberFormatException {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        String number = obj.toString();
        if (isEmpty(number)) {
            return null;
        }
        return Double.parseDouble(number);
    }
    /**
     * 转换为数字
     *
     * @param obj
     * @param def 异常出错时返回默认值
     * @return
     */
    public static Double parseDouble(Object obj, Double def) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            Utils.P(e);
        }
        return def;
    }

    /**
     * 字符串转时间
     *
     * @param date yyyy-MM-dd HH:mm:ss 格式
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return parseDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字符串转时间
     *
     * @param date yyyyMMddHHmmss 格式
     * @return
     * @throws ParseException
     */
    public static Date parseDate2(String date) throws ParseException {
        return parseDate(date, "yyyyMMddHHmmss");
    }

    public static Date parseDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    /**
     * 字符串转时间
     *
     * @param date HH:mm:ss 格式
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String date) throws ParseException {
        return parseTime(date, "HH:mm:ss");
    }

    /**
     * 字符串转时间
     *
     * @param date HHmmss 格式
     * @return
     * @throws ParseException
     */
    public static Date parseTime3(String date) throws ParseException {
        return parseTime(date, "HHmmss");
    }

    private static Date parseTime(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    /**
     * 字符串转时间2
     *
     * @param date HH:mm 格式
     * @return
     * @throws ParseException
     */
    public static Date parseTime2(String date) throws ParseException {
        return parseDate(date, "HH:mm");
    }

    /**
     * 字符串转时间
     *
     * @param date yyyy-MM-dd 格式
     * @return
     * @throws ParseException
     */
    public static Date parseDay(String date) throws ParseException {
        return parseDate(date, "yyyy-MM-dd");
    }

    public static Date parseMonth(String date) throws ParseException {
        return parseDate(date, "yyyy-MM");
    }

    /**
     * 字符串转Date,出错返回默认值
     *
     * @param date yyyy-MM-dd HH:mm:ss 格式
     * @param def
     * @return
     */
    public static Date parseDate(String date, Date def) {
        try {
            return parseDate(date);
        } catch (ParseException e) {
            Utils.P(e);
            return def;
        }
    }

    /**
     * 字符串转Time,出错返回默认值
     *
     * @param date HH:mm:ss 格式
     * @param def
     * @return
     */
    public static Date parseTime(String date, Date def) {
        try {
            return parseTime(date);
        } catch (ParseException e) {
            Utils.P(e);
            return def;
        }
    }

    /**
     * 字符串转Time,出错返回默认值
     *
     * @param date HH:mm 格式
     * @param def
     * @return
     */
    public static Date parseTime2(String date, Date def) {
        try {
            return parseTime2(date);
        } catch (ParseException e) {
            Utils.P(e);
            return def;
        }
    }
    /**
     * 将时间类型转化字符串
     *
     * @param date yyyy-MM-dd HH 格式
     * @return
     */
    public static Date parseDate4(String date) throws ParseException {
        return parseDate(date,"yyyy-MM-dd HH");
    }
    /**
     * 字符串转Day,出错返回默认值
     *
     * @param date yyyy-MM-dd 格式
     * @param def
     * @return
     */
    public static Date parseDay(String date, Date def) {
        try {
            return parseDay(date);
        } catch (ParseException e) {
            Utils.P(e);
            return def;
        }
    }
    /**
     * 将时间类型转化字符串
     *
     * @param date 日期
     * @param format 格式
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    /**
     * 将时间类型转化字符串
     *
     * @param date yyyy-MM-dd HH:mm:ss 格式
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串
     *
     * @param date yyyyMMddHHmmss 格式
     * @return
     */
    public static String formatDate2(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串
     *
     * @param date yyyyMMddHH 格式
     * @return
     */
    public static String formatDate3(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串
     *
     * @param date yyyy-MM-dd HH 格式
     * @return
     */
    public static String formatDate4(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串
     *
     * @param date MM/dd/yyyy HH:mm:ss 格式
     * @return
     */
    public static String formatDate5(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串
     *
     * @param date HH:mm:ss 格式
     * @return
     */
    public static String formatTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串2
     *
     * @param date HH:mm 格式
     * @return
     */
    public static String formatTime2(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串3
     *
     * @param date HHmmss 格式
     * @return
     */
    public static String formatTime3(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(date);
    }
    public static String formatHour(Date date){
        if(date ==null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return sdf.format(date);
    }
    /**
     * 将时间类型转化字符串
     *
     * @param date yyyy-MM-dd 格式
     * @return
     */
    public static String formatDay(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串
     *
     * @param date yyyy-MM 格式
     * @return
     */
    public static String formatMonth(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }

    /**
     * 将时间类型转化字符串
     *
     * @param date yyyyMMdd 格式
     * @return
     */
    public static String formatDay2(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * 将yyyyMMddHHmmss格式的字符串，转化为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param date yyyyMMddHHmmss 格式
     * @return
     * @throws ParseException
     */
    public static String formatDateStr(String date) throws ParseException {
        if (date == null || "".equals(date)) {
            return "";
        }
        return formatDate(parseDate2(date));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		return sdf.format(date);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串，转化为yyyyMMddHHmmss格式字符串
     *
     * @param date yyyy-MM-dd HH:mm:ss 格式
     * @return
     * @throws ParseException
     */
    public static String formatDateStr2(String date) throws ParseException {
        if (date == null || "".equals(date)) {
            return "";
        }
        return formatDate2(parseDate(date));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		return sdf.format(date);
    }

    /**
     * 将yyyyMMddHHmmss格式的字符串，转化为MM/dd/yyyy HH:mm:ss格式字符串
     *
     * @param date yyyyMMddHHmmss 格式
     * @return
     * @throws ParseException
     */
    public static String formatDateStr4(String date) throws ParseException {
        if (date == null || "".equals(date)) {
            return "";
        }
        return formatDate5(parseDate2(date));
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		return sdf.format(date);
    }

    /**
     * 将map的K，V都转换为String类型
     *
     * @param map
     * @return
     */
    public static Map<String, String> mapToStringMap(Map map) {
        if (map != null) {
            HashMap<String, String> r = new HashMap<String, String>(map.size());
            Iterator<Entry> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry e = it.next();
                r.put(String.valueOf(e.getKey()), (e.getValue() == null ? null : String.valueOf(e.getValue())));
            }
            return r;
        }
        return Collections.EMPTY_MAP;
    }

    /**
     * 将list转换生String的泛型
     *
     * @param objList
     * @return
     */
    public static List<String> listToStringList(List objList) {
        if (objList != null && !objList.isEmpty()) {
            List<String> list = new ArrayList<String>();
            for (Object obj : objList) {
                list.add(String.valueOf(obj));
            }
            return list;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 将list中内容拼接成一个','相隔的字符串
     *
     * @param list
     * @return
     */
    public static String joinString(List<String> list) {
        if (Utils.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(list.size() * 33);
        for (String str : list) {
            sb.append(",").append(Utils.defaultValue(str, ""));
        }
        return sb.substring(1);
    }


    /**
     * 获取userId
     *
     * @param args
     * @return
     */
    public static String getUserId(Map<String, String> args) {
        if (!Utils.isEmpty(args)) {
            if (args.containsKey("loginSysUserId")) {
                return args.get("loginSysUserId");
            }
        }
        return "";
    }

    /**
     * 参数中是否包含操作参数,默认包含true
     *
     * @param args 查询参数,key:searchFor
     * @return
     */
    public static boolean isForOperate(Map args) {
        if (Utils.isEmpty(args) || !args.containsKey("searchFor") || "operate".equals(args.get("searchFor"))) {
            return true;
        }
        return false;
    }

    /**
     * 参数中是否包含查看参数
     *
     * @param args 查询参数,key:searchFor ,value:look
     * @return
     */
    public static boolean isForLook(Map args) {
        return !isForOperate(args);
    }

    /**
     * 毫秒转时分秒
     *
     * @param seconds
     * @return
     */
    public static String getStrOfSeconds(long seconds) {
        seconds /= 1000;
        if (seconds < 0) {
            return "秒数必须大于0";
        }
        long one_hour = 60 * 60;
        long one_minute = 60;
        long hour, minute, second = 0L;
        hour = seconds / one_hour;
        minute = seconds % one_hour / one_minute;
        second = seconds % one_hour % one_minute;
        if (seconds < one_minute) {
            return "00:00:" + (seconds < 10 ? "0" + seconds : seconds);
        } else if (seconds >= one_minute && seconds < one_hour) {
            return "00:" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second);
        } else {
            return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second);
        }
    }

    /**
     * 对double数据进行取精度.
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式.
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale,
                               int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    /**
     * 将当前传入的时间的日进行加减计算
     *
     * @param d         时间
     * @param dayNumber 要进行计算的值，例：加一天：1，减一天：-1
     * @return
     */
    public static Date calculateDateDay(Date d, int dayNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DATE, dayNumber);
        return calendar.getTime();
    }

    public static Date calculateDate(int field, Date d, int dayNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(field, dayNumber);
        return calendar.getTime();
    }

    /**
     * 判断对象是否为空<br />
     * 不为空返回true，为空返回false
     *
     * @param o
     * @return
     */
    public static boolean notEmpty(Object o) {
        boolean notEmpty = false;
        if (o instanceof String) {
            String s = (String) o;
            if (s != null && !"".equals(s) && !"undefined".equals(s)
                    && !"null".equals(s)) {
                notEmpty = true;
            }
        } else if (o instanceof Collection) {
            Collection c = (Collection) o;
            if (c != null && c.size() > 0) {
                notEmpty = true;
            }
        } else if (o instanceof Object[]) {
            Object[] arr = (Object[]) o;
            if (arr != null && arr.length > 0) {
                notEmpty = true;
            }
        } else if (o != null) {
            return true;
        }
        return notEmpty;
    }

    public static boolean allEmpty(Object... os) {
        boolean res = true;
        for (Object o : os) {
            if (notEmpty(o)) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 判断对象是否为空<br />
     * 不为空返回false，为空返回true
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        return !notEmpty(o);
    }

    /**
     * 时间天数加减
     *
     * @param date 日期
     * @param days 传负数表示减，正数表示加
     */
    public static Date dateCalculate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * @param inStream
     * @return 字节数组
     * @throws Exception
     * @功能 读取流
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static String byte2HexString(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * 十六进制字符串转换成bytes
     */
    public static byte[] hexString2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }

    /**
     * 十进制转换为十六进制字符串
     *
     * @param algorism int 十进制的数字
     * @return String 对应的十六进制字符串
     */
    public static String algorismToHEXString(int algorism) {
        String result = "";
        result = Integer.toHexString(algorism);
        if (result.length() % 2 == 1) {
            result = "0" + result;

        }
        result = result.toUpperCase();
        return result;
    }

    /**
     * 将十进制转换为指定长度的十六进制字符串
     *
     * @param algorism  int 十进制数字
     * @param maxLength int 转换后的十六进制字符串长度
     * @return String 转换后的十六进制字符串
     */
    public static String algorismToHEXString(int algorism, int maxLength) {
        String result = "";
        result = Integer.toHexString(algorism);

        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return patchHexString(result.toUpperCase(), maxLength);
    }

    /**
     * HEX字符串前补0，主要用于长度位数不足。
     *
     * @param str       String 需要补充长度的十六进制字符串
     * @param maxLength int 补充后十六进制字符串的长度
     * @return 补充结果
     */
    static public String patchHexString(String str, int maxLength) {
        String temp = "";
        for (int i = 0; i < maxLength - str.length(); i++) {
            temp = "0" + temp;
        }
        str = (temp + str).substring(0, maxLength);
        return str;
    }

    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

    /**
     * 字节数组转为普通字符串（ASCII对应的字符）
     *
     * @param bytearray byte[]
     * @return String
     */
    public static String byteToString(byte[] bytearray) {
        String result = "";
        char temp;

        int length = bytearray.length;
        for (int i = 0; i < length; i++) {
            temp = (char) bytearray[i];
            result += temp;
        }
        return result;
    }

    /**
     * 将4个字节数组转换为32位有符号整数
     * 同BitConverter.ToInt32(bytes, 0);(C#)
     *
     * @param bytes
     * @param startIndex
     * @return
     */
    public static int toInt32(byte[] bytes, int startIndex) {
        int l = (int) bytes[startIndex] & 0xFF;
        l += ((int) bytes[startIndex + 1] & 0xFF) << 8;
        l += ((int) bytes[startIndex + 2] & 0xFF) << 16;
        l += ((int) bytes[startIndex + 3] & 0xFF) << 24;
        return l;
    }

    /**
     * 将字符串转成16进制编码格式
     *
     * @param s
     * @return
     */
    public static String toHexString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String tmp = Integer.toHexString(ch);
            sb.append(tmp);
        }
        return sb.toString();
    }

    /**
     * 将16进制编码的字符串还原成字符串
     *
     * @param s
     * @return
     */
    public static String hex2String(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static short toUInt16(byte[] bytes, int startIndex) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put(bytes[0]);
        byteBuffer.put(bytes[1]);
        return byteBuffer.getShort(startIndex);

        //this.Weight = num;
        /*int num2 = 0x3720 - num;
        if (num2 > -1){
            return (short)num2;
        }
        return 1;*/
    }

    public static String getString(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static Map<String, Object> createMap(String key, Object value) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(key, value);
        return map;
    }

    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i = begin; i < begin + count; i++)
            bs[i - begin] = src[i];
        return bs;
    }

    /**
     * @param inStream
     * @return 字节数组
     * @throws Exception
     * @方法功能 InputStream 转为 byte
     */
    public static byte[] inputStream2Byte(InputStream inStream)
            throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

    /**
     * @param b 字节数组
     * @return InputStream
     * @throws Exception
     * @方法功能 byte 转为 InputStream
     */
    public static InputStream byte2InputStream(byte[] b) throws Exception {
        InputStream is = new ByteArrayInputStream(b);
        return is;
    }

    /**
     * @param number 短整型
     * @return 两位的字节数组
     * @功能 短整型与字节的转换
     */
    public static byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
            temp = temp >> 8; // 向右移8位  
        }
        return b;
    }

    /**
     * @param b 两位的字节数组
     * @return 短整型
     * @功能 字节的转换与短整型
     */
    public static short byteToShort(byte[] b) {
        short s = 0;
        short s0 = (short) (b[0] & 0xff);// 最低位  
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * @param i 整型
     * @return 四位的字节数组
     * @方法功能 整型与字节数组的转换
     */
    public static byte[] intToByte(int i) {
        byte[] bt = new byte[4];
        bt[0] = (byte) (0xff & i);
        bt[1] = (byte) ((0xff00 & i) >> 8);
        bt[2] = (byte) ((0xff0000 & i) >> 16);
        bt[3] = (byte) ((0xff000000 & i) >> 24);
        return bt;
    }

    /**
     * @param bytes 字节数组
     * @return 整型
     * @方法功能 字节数组和整型的转换
     */
    public static int bytesToInt(byte[] bytes) {
        int num = bytes[0] & 0xFF;
        num |= ((bytes[1] << 8) & 0xFF00);
        num |= ((bytes[2] << 16) & 0xFF0000);
        num |= ((bytes[3] << 24) & 0xFF000000);
        return num;
    }

    /**
     * @param number 字节数组
     * @return 长整型
     * @方法功能 字节数组和长整型的转换
     */
    public static byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(temp & 0xff).byteValue();
            // 将最低位保存在最低位  
            temp = temp >> 8;
            // 向右移8位  
        }
        return b;
    }

    /**
     * @param b 字节数组
     * @return 长整型
     * @方法功能 字节数组和长整型的转换
     */
    public static long byteToLong(byte[] b) {
        long s = 0;
        long s0 = b[0] & 0xff;// 最低位  
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        long s4 = b[4] & 0xff;// 最低位  
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff; // s0不变  
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

    public static String getServerUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() //服务器地址  
                + ":"
                + request.getServerPort()           //端口号  
                + request.getContextPath();      //项目名称  
    }


    public static String cutPointZero(String num) {
        if (num.length() > 2 && num.indexOf(".0") == num.length() - 2){
            return num.substring(0, num.length() - 2);
        }
        return num;
    }

    public static String getFileNameCN(String fileNameCN, HttpServletRequest request){
        final String userAgent = request.getHeader("USER-AGENT");
        try {
            if (StringUtils.contains(userAgent, "MSIE")) {//IE浏览器
                fileNameCN = URLEncoder.encode(fileNameCN, "UTF8");
            } else if (StringUtils.contains(userAgent, "Mozilla")) {//google,火狐浏览器
                fileNameCN = new String(fileNameCN.getBytes(), "ISO8859-1");
            } else {
                fileNameCN = URLEncoder.encode(fileNameCN, "UTF8");//其他浏览器
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileNameCN;
    }
}
