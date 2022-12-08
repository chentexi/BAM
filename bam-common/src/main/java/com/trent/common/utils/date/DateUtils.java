package com.trent.common.utils.date;

import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Trent
 * @Date: 2021/12/15 16:34
 * @program: BAM
 * @Description: JDK 8  旧日期类 格式化与字符串转换 工具类
 */
@EnableScheduling
public class DateUtils{
	public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";
	public static final String HOUR_MINUTE_SECOND_PATTERN = "HH:mm:ss";
	public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String YMDHM_PATTERN = "yyyy-MM-dd HH:mm";
	public static final String YEAR_MONTH = "yyyy-MM";
	public static final String MONTH_DAY = "MM-dd";
	public static final String HOUR_MIN = "HH:mm";
	public static final String YMDHMS_PATTERNS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final long PARTY_ONE_DAY = 86400000L;
	public static final Integer PERIOD_YEAR = 1;
	public static final Integer PERIOD_MONTH = 2;
	public static final Integer PERIOD_DAY = 5;
	public static final Integer PERIOD_WEEK = 7;
	public static final Integer PERIOD_HALFYEAR = 11;
	public static final Integer PERIOD_QUARTER = 12;
	
	public DateUtils() {
	}
	
	public static Long getTimeGapsMonth(String startTime, String endTime, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Calendar bef = Calendar.getInstance();
			Calendar aft = Calendar.getInstance();
			bef.setTime(sdf.parse(startTime));
			aft.setTime(sdf.parse(endTime));
			Integer surplus = aft.get(5) - bef.get(5);
			Integer result = aft.get(2) - bef.get(2);
			Integer month = (aft.get(1) - bef.get(1)) * 12;
			Integer i = Math.abs(month + result) + surplus;
			return i.longValue();
		} catch ( ParseException var10) {
			var10.printStackTrace();
			return 0L;
		}
	}
	
	public static Long getTimeGapsDay(Date before, Date after) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Long days = null;
		
		try {
			before = dateFormat.parse(dateFormat.format(before));
			after = dateFormat.parse(dateFormat.format(after));
			long diff = after.getTime() - before.getTime();
			days = diff / 86400000L;
			return days;
		} catch (ParseException var6) {
			return 0L;
		}
	}
	
	public static String getTimeGaps(Timestamp parseTime) {
		String timeDistance = "";
		String minUnit = "";
		String secondUnit = "";
		String beforeUnit = "";
		String todayUnit = "";
		
		try {
			minUnit = "分钟";
			secondUnit = "秒";
			beforeUnit = "前";
			todayUnit = "今天";
		} catch (Exception var14) {
			return "";
		}
		
		if (parseTime != null) {
			long distance = (new Date()).getTime() - parseTime.getTime();
			if (distance < 1L) {
				timeDistance = 1 + secondUnit + beforeUnit;
				return timeDistance;
			}
			
			int t1 = getDay(new Date());
			int t2 = getDay(new Date(parseTime.getTime()));
			int t = t1 - t2;
			if (distance < 86400000L && t == 0) {
				int hours = (int)(distance / 3600000L);
				if (hours > 0) {
					timeDistance = todayUnit + " " + format(new Date(parseTime.getTime()), "HH:mm");
					return timeDistance;
				}
				
				int minutes = (int)((distance / 1000L - (long)(hours * 3600)) / 60L);
				if (minutes > 0) {
					timeDistance = minutes + minUnit + beforeUnit;
					return timeDistance;
				}
				
				int second = (int)(distance / 1000L - (long)(hours * 3600) - (long)(minutes * 60)) + 1;
				if (second > 0) {
					timeDistance = second + secondUnit + beforeUnit;
					return timeDistance;
				}
			} else {
				timeDistance = format(new Date(parseTime.getTime()), "yyyy-MM-dd HH:mm");
			}
		}
		
		return timeDistance;
	}
	
	public static String timeStampToString(Timestamp parseTime) {
		String timeDistance = format(new Date(parseTime.getTime()), "yyyy-MM-dd HH:mm:ss");
		return timeDistance;
	}
	
	public static String timeStampToString(Timestamp parseTime, String YMDHM_PATTERN) {
		String timeDistance = format(new Date(parseTime.getTime()), YMDHM_PATTERN);
		return timeDistance;
	}
	
	public static Boolean getTimeGaps(Timestamp startTime, Timestamp endTime, long gap) {
		long distance = endTime.getTime() - startTime.getTime();
		return distance < gap ? true : false;
	}
	
	public static Date currentDate(Date date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(date);
		Date d = df.parse(s);
		return d;
	}
	
	public static Date currentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	public static Timestamp currentTimestamp() {
		return new Timestamp(currentDate().getTime());
	}
	
	public static int getCurrentYear() {
		return getYear(currentDate());
	}
	
	public static String currentTimestampToString() {
		return currentTimestamp().toString();
	}
	
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(1);
	}
	
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(2) + 1;
	}
	
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(5);
	}
	
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(11);
	}
	
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(12);
	}
	
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(13);
	}
	
	public static Integer getYearMonth(Date date) {
		return new Integer(format(date, "yyyyMM"));
	}
	
	public static Date parseYearMonth(Integer yearMonth) throws ParseException {
		return parse(String.valueOf(yearMonth), "yyyyMM");
	}
	
	public static Date parseYearMonthDay(Integer yearMonthDay) throws ParseException {
		return parse(String.valueOf(yearMonthDay), "yyyyMMdd");
	}
	
	public static Date addYear(Date date, int ammount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(1, ammount);
		return c.getTime();
	}
	
	public static Date addMonth(Date date, int ammount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(2, ammount);
		return c.getTime();
	}
	
	public static Date addDate(Date date, int ammount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(5, ammount);
		return c.getTime();
	}
	
	public static Date getYesterday() {
		Date today = currentDate();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(5, -1);
		return c.getTime();
	}
	
	public static String format(Date date, String pattern) {
		if (null == date) {
			return "";
		} else {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
	}
	
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}
	
	public static Date parse(String dateStr, String pattern) throws ParseException {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.parse(dateStr);
	}
	
	public static Date parse(String dateStr) throws ParseException {
		if (dateStr.length() == "yyyy-MM-dd".length()) {
			return parse(dateStr, "yyyy-MM-dd");
		} else {
			return dateStr.length() == "yyyy-MM-dd HH:mm:ss".length() ? parse(dateStr, "yyyy-MM-dd HH:mm:ss") : parse(dateStr, "yyyy-MM-dd");
		}
	}
	
	private static Date parse2(String dateStr) throws ParseException {
		if (dateStr.length() == "yyyy-MM-dd".length()) {
			return parse(dateStr, "yyyy-MM-dd");
		} else if (dateStr.length() == "yyyy-MM-dd HH:mm:ss".length()) {
			return parse(dateStr, "yyyy-MM-dd HH:mm:ss");
		} else if (dateStr.length() == "yyyy-MM-dd HH:mm:ss.SSS".length()) {
			return parse(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
		} else {
			return dateStr.length() > "yyyy-MM-dd HH:mm:ss".length() && dateStr.length() < "yyyy-MM-dd HH:mm:ss.SSS".length() ? parse(dateStr, "yyyy-MM-dd HH:mm:ss.SSS") : parse(dateStr, "yyyy-MM-dd");
		}
	}
	
	public static boolean isYearMonth(Integer yearMonth) {
		String yearMonthStr = yearMonth.toString();
		return isYearMonth(yearMonthStr);
	}
	
	public static boolean isYearMonth(String yearMonthStr) {
		if (yearMonthStr.length() != 6) {
			return false;
		} else {
			String yearStr = yearMonthStr.substring(0, 4);
			String monthStr = yearMonthStr.substring(4, 6);
			
			try {
				int year = Integer.parseInt(yearStr);
				int month = Integer.parseInt(monthStr);
				if (year >= 1800 && year <= 3000) {
					return month >= 1 && month <= 12;
				} else {
					return false;
				}
			} catch (Exception var5) {
				return false;
			}
		}
	}
	
	public static Timestamp parseTimestamp(String dateStr, String pattern) throws ParseException {
		return new Timestamp(parse(dateStr, pattern).getTime());
	}
	
	public static Timestamp parseTimestamp(String dateStr) throws ParseException {
		return new Timestamp(parse2(dateStr).getTime());
	}
	
	public static int getCurrentMonth() {
		return getMonth(currentDate());
	}
	
	public static Timestamp getBeforeTimeStamp(Timestamp currentTimestamp, long dayGap) {
		Timestamp timestamp = null;
		long d = currentTimestamp.getTime() - 86400000L * dayGap;
		timestamp = new Timestamp(d);
		return timestamp;
	}
	
	public static Date getDate(Long timeline) {
		return new Date(timeline);
	}
	
	public static String getDate(String month, String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String str = sdf.format(d);
		String nowmonth = str.substring(5, 7);
		String nowday = str.substring(8, 10);
		String result = null;
		int temp = Integer.parseInt(nowday) - Integer.parseInt(day);
		switch(temp) {
			case 0:
				result = "今天";
				break;
			case 1:
				result = "昨天";
				break;
			case 2:
				result = "前天";
				break;
			default:
				StringBuilder sb = new StringBuilder();
				sb.append(Integer.parseInt(month) + "月");
				sb.append(Integer.parseInt(day) + "日");
				result = sb.toString();
		}
		
		return result;
	}
	
	public static String getTime(int timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = null;
		
		try {
			Date currentdate = new Date();
			long i = (currentdate.getTime() / 1000L - (long)timestamp) / 60L;
			System.out.println(currentdate.getTime());
			System.out.println(i);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			System.out.println("now-->" + now);
			String str = sdf.format(new Timestamp(IntToLong(timestamp)));
			time = str.substring(11, 16);
			String month = str.substring(5, 7);
			String day = str.substring(8, 10);
			System.out.println(str);
			System.out.println(time);
			System.out.println(getDate(month, day));
			time = getDate(month, day) + time;
		} catch (Exception var10) {
			var10.printStackTrace();
		}
		
		return time;
	}
	
	public static long IntToLong(int i) {
		long result = (long)i;
		result *= 1000L;
		return result;
	}
	
	public static String getNextMonth() {
		Date date = new Date();
		String hms = format(date, "HH:mm:ss");
		int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(date));
		int month = Integer.parseInt((new SimpleDateFormat("MM")).format(date)) + 1;
		int day = Integer.parseInt((new SimpleDateFormat("dd")).format(date));
		if (month == 0) {
			--year;
			month = 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 != 0 && (year % 4 != 0 || year % 100 == 0)) {
					day = 28;
				} else {
					day = 29;
				}
			} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
				day = 30;
			}
		}
		
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		
		return y + "-" + m + "-" + d + " " + hms;
	}
	
	public static boolean checkToDay(Timestamp parseTime) {
		if (parseTime != null) {
			long distance = (new Date()).getTime() - parseTime.getTime();
			int t1 = getDay(new Date());
			int t2 = getDay(new Date(parseTime.getTime()));
			int t = t1 - t2;
			if (distance < 86400000L && t == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public static int checkDayToWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		return c.get(7);
	}
	
	public static Date getMonday() throws ParseException {
		int toDay = checkDayToWeek();
		Date date = new Date();
		if (toDay == 3) {
			date = addDate(date, -1);
		} else if (toDay == 4) {
			date = addDate(date, -2);
		} else if (toDay == 5) {
			date = addDate(date, -3);
		} else if (toDay == 6) {
			date = addDate(date, -4);
		} else if (toDay == 7) {
			date = addDate(date, -5);
		} else if (toDay == 1) {
			date = addDate(date, -6);
		}
		
		return date;
	}
	
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(5, calendar.getActualMinimum(5));
		return format(calendar.getTime(), "yyyy-MM-dd");
	}
	
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(5, calendar.getActualMaximum(5));
		return format(calendar.getTime(), "yyyy-MM-dd");
	}
	
	/** @deprecated */
	@Deprecated
	public static String getUniqueNoWithCurrentTime() {
		Date date = currentDate();
		StringBuffer sbf = new StringBuffer();
		int year = getYear(date);
		int month = getMonth(date);
		int day = getDay(date);
		int hour = getHour(date);
		sbf.append(year);
		sbf.append(month);
		sbf.append(day);
		sbf.append(hour);
		return sbf.toString();
	}
	
	public static Timestamp getFirstTime(int type, String dateStr) throws ParseException {
		Date date = null;
		if (dateStr != null) {
			date = parse(dateStr);
		}
		
		return getFirstTime(type, date);
	}
	
	public static Timestamp getFirstTime(int type, Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		
		if (type == PERIOD_YEAR) {
			calendar.set(2, 0);
			calendar.set(5, calendar.getMinimum(5));
		} else if (type == PERIOD_HALFYEAR) {
			if (calendar.getTime().getMonth() < 6) {
				calendar.set(2, 0);
			} else {
				calendar.set(2, 6);
			}
			
			calendar.set(5, calendar.getMinimum(5));
		} else if (type == PERIOD_QUARTER) {
			Integer month = calendar.getTime().getMonth();
			if (month < 3) {
				calendar.set(2, 0);
			} else if (month < 6) {
				calendar.set(2, 3);
			} else if (month < 9) {
				calendar.set(2, 6);
			} else {
				calendar.set(2, 9);
			}
			
			calendar.set(5, calendar.getMinimum(5));
		} else if (type == PERIOD_WEEK) {
			calendar.set(7, 2);
		} else if (type != PERIOD_DAY) {
			calendar.set(5, calendar.getMinimum(5));
		}
		
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	public static Timestamp getLastTime(int type, String dateStr) throws ParseException {
		Date date = null;
		if (dateStr != null) {
			date = parse(dateStr);
		}
		
		return getLastTime(type, date);
	}
	
	public static Timestamp getLastTime(int type, Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		
		if (type == PERIOD_YEAR) {
			calendar.set(2, 11);
			calendar.set(5, calendar.getMaximum(5));
		} else if (type == PERIOD_HALFYEAR) {
			if (calendar.getTime().getMonth() < 6) {
				calendar.set(2, 5);
			} else {
				calendar.set(2, 11);
			}
			
			calendar.set(5, calendar.getMaximum(5));
		} else if (type == PERIOD_QUARTER) {
			Integer month = calendar.getTime().getMonth();
			if (month < 3) {
				calendar.set(2, 2);
			} else if (month < 6) {
				calendar.set(2, 5);
			} else if (month < 9) {
				calendar.set(2, 8);
			} else {
				calendar.set(2, 11);
			}
			
			calendar.set(5, calendar.getMaximum(5));
		} else if (type == PERIOD_WEEK) {
			calendar.set(7, 1);
			calendar.add(3, 1);
		} else if (type != PERIOD_DAY) {
			calendar.set(5, calendar.getMaximum(5));
		}
		
		calendar.set(11, calendar.getMaximum(11));
		calendar.set(12, calendar.getMaximum(12));
		calendar.set(13, calendar.getMaximum(13));
		calendar.set(14, calendar.getMaximum(14));
		return new Timestamp(calendar.getTimeInMillis());
	}
}
