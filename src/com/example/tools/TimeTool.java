package com.example.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.annotation.SuppressLint;

public class TimeTool {

	/**
	 * 比较上次刷新时间（oldTime）与当前时间（currTime）之差的绝对值是否大于指定时间min
	 * @param oldTime 上次刷新时间
	 * @param min 指定的时间差
	 * @return false 时间差小于min ,true 时间差大于等于min
	 */
	public static boolean compare2Time(long oldTime , int min){
		long currTime = System.currentTimeMillis() ;
		long time = Math.abs((currTime - oldTime)/(1000*60)) ;
		if(time >= min)
			return true ;
		else
			return false ;
	}
	
	/**
	 * 将毫秒时间转换为长格式的日期时间字符串
	 * @param milliTime 待转换的毫秒数据，从格林尼治时间开始算
	 * @return 长格式的日期时间字符串
	 */
	@SuppressLint("SimpleDateFormat")
	public static String milliTime2LongStr(long milliTime){
		Calendar c = Calendar.getInstance() ;
		c.setTimeInMillis(milliTime) ;
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss/EEEE").format(c.getTime());
		
		return time ;
	}
	
	/**
	 * 将毫秒时间转换为短格式的日期时间字符串
	 * @param milliTime 待转换的毫秒数据，从格林尼治时间开始算
	 * @return 长格式的日期时间字符串
	 */
	@SuppressLint("SimpleDateFormat")
	public static String milliTime2ShortStr(long milliTime){
		Calendar c = Calendar.getInstance() ;
		c.setTimeInMillis(milliTime) ;
		String time = new SimpleDateFormat("MM-dd HH:mm/EEEE").format(c.getTime());
		
		return time ;
	}
	
	/**
	 * 将毫秒时间转换为MM-dd/EEEE
	 * @param milliTime 待转换的毫秒数据，从格林尼治时间开始算
	 * @return 长格式的日期时间字符串
	 */
	@SuppressLint("SimpleDateFormat")
	public static String milliTime2dayWeek(long milliTime){
		Calendar c = Calendar.getInstance() ;
		c.setTimeInMillis(milliTime) ;
		String time = new SimpleDateFormat("MM-dd/EEEE").format(c.getTime());
		
		return time ;
	}
}
