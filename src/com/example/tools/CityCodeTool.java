package com.example.tools;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.content.res.AssetManager;

public class CityCodeTool {
    private static String CITY_CODE_NAME = "CityCode.xml";
    AssetManager assetManager;
    InputStream inputStream;
    Document document;
    Context context;

    public CityCodeTool(Context context) throws Exception {
	this.context = context;
	assetManager = context.getResources().getAssets();

	inputStream = assetManager.open(CITY_CODE_NAME);
	document = Jsoup.parse(inputStream, "gbk", "");
	inputStream.close();
    }

    public ArrayList<HashMap<String, Object>> getprovince() throws Exception{
	ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
	Elements elements = document.select("province");
	for (Element element : elements) {
	    HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    hashMap.put("id", element.attr("id"));
	    hashMap.put("name", element.attr("name"));
	    arrayList.add(hashMap);
	}
	return arrayList;
    }

    public ArrayList<HashMap<String, Object>> getCity(String provinceId) throws Exception{
	ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
	Elements elements = document.select("province[id=" + provinceId
		+ "]>city");
	for (Element element : elements) {
	    HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    hashMap.put("id", element.attr("id"));
	    hashMap.put("name", element.attr("name"));
	    arrayList.add(hashMap);
	}
	return arrayList;
    }

    public ArrayList<HashMap<String, Object>> getCounty(String cityId) throws Exception{
	ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
	Elements elements = document.select("city[id=" + cityId + "]>county");
	for (Element element : elements) {
	    HashMap<String, Object> hashMap = new HashMap<String, Object>();
	    hashMap.put("weatherCode", element.attr("weatherCode"));
	    hashMap.put("name", element.attr("name"));
	    arrayList.add(hashMap);
	}
	return arrayList;
    }
}
