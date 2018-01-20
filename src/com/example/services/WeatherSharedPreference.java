package com.example.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class WeatherSharedPreference {
    private static String FILE_NAME = "BaiduWeather";
    /**
     * ������б���
     * 
     * @param cityCode
     */
    public static void saveCityCode(Context context, String citycode) {
	SharedPreferences sharedPreferences = context.getSharedPreferences(
		FILE_NAME, Context.MODE_PRIVATE);
	Editor editor = sharedPreferences.edit();
	editor.putString("citycode", citycode);
	editor.commit();
    }

    public static String getCityCode(Context context) {
	SharedPreferences sharedPreferences = context.getSharedPreferences(
		FILE_NAME, Context.MODE_PRIVATE);
	return sharedPreferences.getString("citycode", "101010100");
    }

    /**
     * ���������
     * 
     * @param cityName
     */
    public static void saveCityName(Context context, String cityname) {
	SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	Editor editor=sharedPreferences.edit();
	editor.putString("cityname", cityname);
	editor.commit();
    }
    
    public static String getCityName(Context context){
	SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	return sharedPreferences.getString("cityname", "����");
	
    }
    /**
     * ��������ˢ��ʱ��
     * 
     * @param time
     */
    public static void setWeatherRefreshTime(Context context, long time) {
	SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	Editor editor=sharedPreferences.edit();
	editor.putLong("WeatherRefreshTime", time);
	editor.commit();
    }
    
    public static long getWeatherRefreshTime(Context context){
	SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	return sharedPreferences.getLong("WeatherRefreshTime",System.currentTimeMillis() );
    }

    /**
     * ���ó����л���ˢ�²���
     * 
     * @param isFirst
     */
    public static void setNeedRefresh(Context context, boolean NeedRefresh) {
	SharedPreferences sharedPreferences = context.getSharedPreferences(
		FILE_NAME, Context.MODE_PRIVATE);
	Editor editor = sharedPreferences.edit();
	editor.putBoolean("NeedRefresh", NeedRefresh);
	editor.commit();
    }

    public static boolean getNeedRefresh(Context context) {
	SharedPreferences sharedPreferences = context.getSharedPreferences(
		FILE_NAME, Context.MODE_PRIVATE);
	return sharedPreferences.getBoolean("NeedRefresh", true);
    }

    
    /**
     * ����ٶ�������Ϣ
     * 
     * @param elmts
     */
    public static void saveBaiduWeatherMessage(Context context, String BaiduWeatherMessage) {
	SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	Editor editor=sharedPreferences.edit();
	editor.putString("BaiduWeatherMessage", BaiduWeatherMessage.trim());
	editor.commit();
    }
    
    public static String getBaiduWeatherMessage(Context context){
	SharedPreferences sharedPreferences=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	return sharedPreferences.getString("BaiduWeatherMessage", "");
	
    }
}
