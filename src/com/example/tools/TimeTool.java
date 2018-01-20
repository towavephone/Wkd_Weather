package com.example.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.annotation.SuppressLint;

public class TimeTool {

	/**
	 * �Ƚ��ϴ�ˢ��ʱ�䣨oldTime���뵱ǰʱ�䣨currTime��֮��ľ���ֵ�Ƿ����ָ��ʱ��min
	 * @param oldTime �ϴ�ˢ��ʱ��
	 * @param min ָ����ʱ���
	 * @return false ʱ���С��min ,true ʱ�����ڵ���min
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
	 * ������ʱ��ת��Ϊ����ʽ������ʱ���ַ���
	 * @param milliTime ��ת���ĺ������ݣ��Ӹ�������ʱ�俪ʼ��
	 * @return ����ʽ������ʱ���ַ���
	 */
	@SuppressLint("SimpleDateFormat")
	public static String milliTime2LongStr(long milliTime){
		Calendar c = Calendar.getInstance() ;
		c.setTimeInMillis(milliTime) ;
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss/EEEE").format(c.getTime());
		
		return time ;
	}
	
	/**
	 * ������ʱ��ת��Ϊ�̸�ʽ������ʱ���ַ���
	 * @param milliTime ��ת���ĺ������ݣ��Ӹ�������ʱ�俪ʼ��
	 * @return ����ʽ������ʱ���ַ���
	 */
	@SuppressLint("SimpleDateFormat")
	public static String milliTime2ShortStr(long milliTime){
		Calendar c = Calendar.getInstance() ;
		c.setTimeInMillis(milliTime) ;
		String time = new SimpleDateFormat("MM-dd HH:mm/EEEE").format(c.getTime());
		
		return time ;
	}
	
	/**
	 * ������ʱ��ת��ΪMM-dd/EEEE
	 * @param milliTime ��ת���ĺ������ݣ��Ӹ�������ʱ�俪ʼ��
	 * @return ����ʽ������ʱ���ַ���
	 */
	@SuppressLint("SimpleDateFormat")
	public static String milliTime2dayWeek(long milliTime){
		Calendar c = Calendar.getInstance() ;
		c.setTimeInMillis(milliTime) ;
		String time = new SimpleDateFormat("MM-dd/EEEE").format(c.getTime());
		
		return time ;
	}
}
