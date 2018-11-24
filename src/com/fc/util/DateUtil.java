package com.fc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *时间操作工具类
 */
public class DateUtil {
	public static  String logFileDow;
	public static  String logFileInfoDow;
	
	/**
	 * 初始化日志
	 */
	public static void initLog(){
		DateUtil.logFileDow=null;
		DateUtil.logFileInfoDow=null;
	}
	public static void main(String[] args) {
		getDates();
	}
	/**
	 * 返回最近五天的时间数组
	 * @return
	 */
	public static String [] getDates(){
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String [] dates=new String [5];
		dates[0]=format.format(new Date());
		for (int i = 1; i < dates.length; i++) {
			Calendar c= Calendar.getInstance();
			c.add(Calendar.DATE, 0-i);
			dates[i]=format.format(c.getTime());
		}
		return dates;
	}
}
