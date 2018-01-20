package com.example.wkd_weather;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.services.WeatherSharedPreference;
import com.example.tools.CityCodeTool;
import com.example.tools.MessageQueue;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BaiduEditCityActivity extends Activity {
    private Context context;
    private CityCodeTool ccTool;
    private Button selectCityBackTV, selectSubmitTV;
    private TextView provinceTV, cityTV, countyTV;
    private ProgressBar provincePB, cityPB, countyPB;
    private ListView provinceLV, cityLV, countyLV;
    private SimpleAdapter adapterPro, adapterCi, adapterCo;
    private ArrayList<HashMap<String, Object>> mapArrPro, mapArrCi, mapArrCo;

    private String provinceId = "", cityId = "", weatherCode = "",
	    cityName = "";

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
	public void handleMessage(android.os.Message msg) {
	    String str = (String) msg.obj;
	    switch (msg.what) {
	    case MessageQueue.LOAD_RESOURCES_FAILED:
	    case MessageQueue.LOAD_PROVIENCE_FAILED:
	    case MessageQueue.LOAD_CITY_FAILED:
	    case MessageQueue.LOAD_COUNTY_FAILED:
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
		break;

	    case MessageQueue.LOAD_RESOURCES_SUCCEED:
		loadProvince();
		break;

	    case MessageQueue.LOAD_PROVIENCE_SUCCEED:
		provinceLV.setEnabled(true);
		provincePB.setVisibility(View.GONE);
		adapterPro.notifyDataSetChanged();
		// provinceId = (String)mapArrPro.get(0).get("id") ;
		// loadCity(provinceId) ;
		break;

	    case MessageQueue.LOAD_CITY_SUCCEED:
		cityLV.setEnabled(true);
		cityPB.setVisibility(View.GONE);
		adapterCi.notifyDataSetChanged();
		// cityId = (String)mapArrCi.get(0).get("id") ;
		// loadCounty(cityId) ;
		break;

	    case MessageQueue.LOAD_COUNTY_SUCCEED:
		countyLV.setEnabled(true);
		countyPB.setVisibility(View.GONE);
		adapterCo.notifyDataSetChanged();
		break;
	    }
	}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_city);
	context = getApplicationContext();
	init();
	addListener();
	loadResources();
    }

    /**
     * 加载城市编码资源文件，加载完成后调用loadProvience()，获取省份数据 因数据量较大，调用子线程可防止UI出现卡顿
     */
    public void loadResources() {
	// TODO Auto-generated method stub
	provinceLV.setEnabled(false);
	cityLV.setEnabled(false);
	countyLV.setEnabled(false);
	provincePB.setVisibility(View.VISIBLE);
	new Thread(new myThread()).start();
    }

    public class myThread implements Runnable {

	@Override
	public void run() {
	    // TODO Auto-generated method stub
	    Message msg = Message.obtain();
	    try {
		ccTool = new CityCodeTool(context);
		msg.what = MessageQueue.LOAD_RESOURCES_SUCCEED;
		handler.sendMessage(msg);
	    } catch (Exception e) {
		msg.obj=e.getClass().getSimpleName();
		msg.what = MessageQueue.LOAD_RESOURCES_FAILED;
		handler.sendMessage(msg);
	    }
	}
    }

    public void addListener() {
	// TODO Auto-generated method stub
	MyButtonClickListener myClickListener = new MyButtonClickListener();
	selectCityBackTV.setOnClickListener(myClickListener);
	selectSubmitTV.setOnClickListener(myClickListener);
	provinceLV.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		    long arg3) {
		// TODO Auto-generated method stub
		provinceTV.setText((String)mapArrPro.get(arg2).get("name"));
		provinceId = (String)mapArrPro.get(arg2).get("id");
		// 在用户选择省份后，加载市的数据之前，将旧的市和县数据清空，防止点击频率过快导致ListView反应不过来而崩溃
		mapArrCi.clear();
		adapterCi.notifyDataSetChanged();

		mapArrCo.clear();
		adapterCo.notifyDataSetChanged();
		loadCity(provinceId);
	    }

	});
	cityLV.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		    long arg3) {
		// TODO Auto-generated method stub
		cityTV.setText((String) mapArrCi.get(arg2).get("name"));
		cityId = (String) mapArrCi.get(arg2).get("id");
		// 在用户选择省份后，加载市的数据之前，将旧的市和县数据清空，防止点击频率过快导致ListView反应不过来而崩溃
		mapArrCo.clear();
		adapterCo.notifyDataSetChanged();

		loadCounty(cityId);
	    }
	});
	countyLV.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		    long arg3) {
		// TODO Auto-generated method stub
		cityName = (String) mapArrCo.get(arg2).get("name");
		weatherCode = (String) mapArrCo.get(arg2).get("weatherCode");
		countyTV.setText(cityName);
		Toast.makeText(context,
			"您已选择“" + cityName + "”，\n点击“确定”可保存修改！",
			Toast.LENGTH_SHORT).show();
	    }
	});
    }

    public void loadCounty(final String cityId) {
	// TODO Auto-generated method stub
	countyPB.setVisibility(View.GONE);
	countyLV.setEnabled(true);
	new Thread(new Runnable() {
	    Message message=null;
	    @Override
	    public void run() {
		message = Message.obtain();
		try {
		    mapArrCo.clear();
		    ArrayList<HashMap<String, Object>> mapArrayList = ccTool
			    .getCounty(cityId);
		    for (int i = 0; i < mapArrayList.size(); i++) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("name", mapArrayList.get(i).get("name"));
			hashMap.put("weatherCode", mapArrayList.get(i).get("id"));
			mapArrCo.add(hashMap);
		    }
			message.what=MessageQueue.LOAD_COUNTY_SUCCEED;
			handler.sendMessage(message);
		    
		} catch (Exception e) {
		    message.obj=e.getClass().getSimpleName();
		    message.what=MessageQueue.LOAD_COUNTY_FAILED;
		    handler.sendMessage(message);
		}
	    }
	}).start();
    }

    public void loadCity(final String provinceId) {
	// TODO Auto-generated method stub
	cityPB.setVisibility(View.GONE);
	cityLV.setEnabled(true);
	new Thread(new Runnable() {
	    Message message=null;
	    @Override
	    public void run() {
		message = Message.obtain();
		try {
		    mapArrCi.clear();
		    ArrayList<HashMap<String, Object>> mapArrayList = ccTool
			    .getCity(provinceId);
		    for (int i = 0; i < mapArrayList.size(); i++) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("name", mapArrayList.get(i).get("name"));
			hashMap.put("id", mapArrayList.get(i).get("id"));
			mapArrCi.add(hashMap);
		    }
			message.what=MessageQueue.LOAD_CITY_SUCCEED;
			handler.sendMessage(message);
		    
		} catch (Exception e) {
		    message.obj=e.getClass().getSimpleName();
		    message.what=MessageQueue.LOAD_CITY_FAILED;
		    handler.sendMessage(message);
		}
	    }
	}).start();
    }

    public void loadProvince() {
	// TODO Auto-generated method stub
	provincePB.setVisibility(View.GONE);
	provinceLV.setEnabled(true);
	new Thread(new Runnable() {
	    Message message=null;
	    @Override
	    public void run() {
		message = Message.obtain();
		try {
		    mapArrPro.clear();
		    ArrayList<HashMap<String, Object>> mapArrayList = ccTool
			    .getprovince();
		    for (int i = 0; i < mapArrayList.size(); i++) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("name", mapArrayList.get(i).get("name"));
			hashMap.put("id", mapArrayList.get(i).get("id"));
			mapArrPro.add(hashMap);
		    }
			message.what=MessageQueue.LOAD_PROVIENCE_SUCCEED;
			handler.sendMessage(message);
		} catch (Exception e) {
		    message.obj=e.getClass().getSimpleName();
		    message.what=MessageQueue.LOAD_PROVIENCE_FAILED;
		    handler.sendMessage(message);
		}
	    }
	}).start();
    }

    public class MyButtonClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
	    // TODO Auto-generated method stub
	    switch (v.getId()) {
	    case R.id.selectCityButton:
		Toast.makeText(context, "你未修改城市", Toast.LENGTH_SHORT).show();
		break;
	    case R.id.selectSubmitButton:
		if (cityName != null && !cityName.equals("")) {
		    Toast.makeText(context, "修改城市成功", Toast.LENGTH_SHORT)
			    .show();
		    WeatherSharedPreference.saveCityName(context, cityName);
		    WeatherSharedPreference.setNeedRefresh(context, true);
		} else {
		    Toast.makeText(context, "你未修改城市", Toast.LENGTH_SHORT)
			    .show();
		}

		break;
	    default:
		break;
	    }
	    finish();
	}
    }

    public void init() {
	// TODO Auto-generated method stub
	selectCityBackTV = (Button) findViewById(R.id.selectCityButton);
	selectSubmitTV = (Button) findViewById(R.id.selectSubmitButton);
	provinceTV = (TextView) findViewById(R.id.provinceTextView);
	cityTV = (TextView) findViewById(R.id.cityTextView);
	countyTV = (TextView) findViewById(R.id.countyTextView);

	provinceLV = (ListView) findViewById(R.id.provinceListView);
	cityLV = (ListView) findViewById(R.id.cityListView);
	countyLV = (ListView) findViewById(R.id.countyListView);
	provinceLV.setEnabled(false);
	cityLV.setEnabled(false);
	countyLV.setEnabled(false);

	provincePB = (ProgressBar) findViewById(R.id.provinceProgressBar);
	cityPB = (ProgressBar) findViewById(R.id.cityProgressBar);
	countyPB = (ProgressBar) findViewById(R.id.countyProgressBar);
	provincePB.setVisibility(View.VISIBLE);
	cityPB.setVisibility(View.GONE);
	countyPB.setVisibility(View.GONE);

	mapArrPro = new ArrayList<HashMap<String, Object>>();
	mapArrCi = new ArrayList<HashMap<String, Object>>();
	mapArrCo = new ArrayList<HashMap<String, Object>>();

	adapterPro = new SimpleAdapter(context, mapArrPro,
		R.layout.item_weather_city_code, new String[] { "name" },
		new int[] { R.id.nameTV });
	adapterCi = new SimpleAdapter(context, mapArrCi,
		R.layout.item_weather_city_code, new String[] { "name" },
		new int[] { R.id.nameTV });
	adapterCo = new SimpleAdapter(context, mapArrCo,
		R.layout.item_weather_city_code, new String[] { "name" },
		new int[] { R.id.nameTV });
	provinceLV.setAdapter(adapterPro);
	cityLV.setAdapter(adapterCi);
	countyLV.setAdapter(adapterCo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.bai_du_edit_city, menu);
	return true;
    }

}
