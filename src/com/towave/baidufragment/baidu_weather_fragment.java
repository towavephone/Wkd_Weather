package com.towave.baidufragment;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.conn.ConnectTimeoutException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.services.WeatherSharedPreference;
import com.example.tools.HttpTools;
import com.example.tools.MessageQueue;
import com.example.tools.TimeTool;
import com.example.wkd_weather.BaiduEditCityActivity;
import com.example.wkd_weather.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class baidu_weather_fragment extends Fragment {
    private Context context;
    private ProgressDialog dialog;
    private Button tv_navi[];// ������ť��0���أ�1���࣬2�޸ĳ��У�3ˢ�£�4δ������
    private TextView cityNameTV, dayWeekTV, weather0TV, refreshTimeTV,
	    baiduLiveTV[];
    private LinearLayout baiduMoreLayout, futureLayout;
    private ListView futureLV;
    private SimpleAdapter adapterFru;
    private ArrayList<HashMap<String, Object>> mapArrFru;
    private View view;
    private ScrollView scrollView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
	public void handleMessage(android.os.Message msg) {
	    dialog.dismiss();
	    String str = (String) msg.obj;
	    switch (msg.what) {
	    case MessageQueue.CONNECT_FAILED:
	    case MessageQueue.PARSE_FAILED:
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
		break;

	    case MessageQueue.PARSE_SUCCEED:
		loadData();
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
		break;
	    }
	}
    };
    private int HOUR = 2;

    @Override
    public void onAttach(Activity activity) {
	// TODO Auto-generated method stub
	super.onAttach(activity);
	context = activity.getApplicationContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	view = inflater.inflate(R.layout.fragment_baidu_weather, null);
	init();
	MyClickListener myClickListener = new MyClickListener();
	for (int i = 0; i < tv_navi.length; i++) {
	    tv_navi[i].setOnClickListener(myClickListener);
	}
	mapArrFru = new ArrayList<HashMap<String, Object>>();
	adapterFru = new SimpleAdapter(context, mapArrFru,
		R.layout.item_weather_baidu, new String[] { "weather_day",
			"weather_degree", "weather_des" },
		new int[] { R.id.dayTextView, R.id.degreeTextView,
			R.id.desTextView });
	futureLV.setAdapter(adapterFru);
	
	return view;
    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        loadData();
	long oldTime = WeatherSharedPreference
		.getWeatherRefreshTime(context);
	boolean needRefresh = WeatherSharedPreference
		.getNeedRefresh(context);
	System.err.print("--needRefresh-->"+needRefresh);
	if (needRefresh || TimeTool.compare2Time(oldTime, HOUR * 60)) {
	    refreshData();
	}
	
    }
    public void refreshData() {
	// TODO Auto-generated method stub
	String cityName = WeatherSharedPreference.getCityName(context).trim();
	if (cityName != null && !cityName.equals("")) {
	    dialog.show();
	    new Thread(new Runnable() {
		Message message;

		@Override
		public void run() {
		    // TODO Auto-generated method stub
		    message = Message.obtain();
		    try {
			WeatherSharedPreference.setWeatherRefreshTime(context,
				System.currentTimeMillis());
			WeatherSharedPreference.setNeedRefresh(context, false);
			String weatherMessage = HttpTools
				.getBaiduWeatherHtml(context);
			WeatherSharedPreference.saveBaiduWeatherMessage(
				context, weatherMessage);
			message.obj = "���ݻ�ȡ�ɹ���";
			message.what = MessageQueue.PARSE_SUCCEED;
			handler.sendMessage(message);
		    } catch (UnknownHostException e) { // һ���������粻����������
			message.obj = "��ѽ����������������Ŷ��";
			message.what = MessageQueue.CONNECT_FAILED;
			handler.sendMessage(message);
		    } catch (ConnectTimeoutException e) {
			message.obj = "����������ʱ��\nԭ������ǣ�1����������̫�������ˣ�2��������Ъ���ˡ�";
			message.what = MessageQueue.CONNECT_FAILED;
			handler.sendMessage(message);
		    } catch (SocketTimeoutException e) {
			message.obj = "��������Ӧ��ʱ��\nԭ������ǣ�1����������̫�������ˣ�2��������Ъ���ˡ�";
			message.what = MessageQueue.CONNECT_FAILED;
			handler.sendMessage(message);
		    } catch (IndexOutOfBoundsException e) {
			message.obj = "���ݽ����쳣���뷴����";
			message.what = MessageQueue.PARSE_FAILED;
			handler.sendMessage(message);
		    } catch (Exception e) {
			message.obj = "δ֪�쳣��" + e.toString();
			message.what = MessageQueue.PARSE_FAILED;
			handler.sendMessage(message);
		    }
		}
	    }).start();
	} else {
	    Toast.makeText(context, "����ѡ����У�", Toast.LENGTH_SHORT).show();
	    Intent intent = new Intent(context, BaiduEditCityActivity.class);
	    context.startActivity(intent);
	}

    }

    public void loadData() {
	String weatherMessage = WeatherSharedPreference
		.getBaiduWeatherMessage(context);
	if (weatherMessage != null && !weatherMessage.equals("")) {
	    Document document = Jsoup.parse(weatherMessage);
	    cityNameTV.setText(document.select("span.city_name_txt").text()
		    .trim());
	    dayWeekTV.setText(document.select("div#but_date").text().trim());
	    String temp = document.select("div#low_degree_deg").text().trim();
	    temp += "\n" + document.select("div#low_degree_des").text().trim();
	    weather0TV.setText(temp);
	    Elements elements = document.select("div.index_value");
	    for (int i = 0; i < baiduLiveTV.length; i++) {
		baiduLiveTV[i].setText(elements.get(i).text().trim());
	    }
	    elements.clear();
	    mapArrFru.clear();
	    elements = document.select("div.weather_item");

	    for (Element element : elements) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("weather_day", element.select("div.weather_day")
			.text().trim());
		hashMap.put("weather_degree",
			element.select("div.weather_degree").text().trim());
		hashMap.put("weather_des", element.select("div.weather_des")
			.text().trim());
		mapArrFru.add(hashMap);
	    }
	    adapterFru.notifyDataSetChanged();
	}
	long refreshTime = WeatherSharedPreference
		.getWeatherRefreshTime(context);
	refreshTimeTV.setText(TimeTool.milliTime2LongStr(refreshTime));

    }

    public void init() {
	// TODO Auto-generated method stub
	dialog = new ProgressDialog(getActivity());
	dialog.setCancelable(false);
	dialog.setTitle("��ʾ");
	dialog.setMessage("���ڸ�������");

	tv_navi = new Button[5];
	tv_navi[0] = (Button) view.findViewById(R.id.returnButton);
	tv_navi[1] = (Button) view.findViewById(R.id.moreButton);
	tv_navi[2] = (Button) view.findViewById(R.id.editCityButton);
	tv_navi[3] = (Button) view.findViewById(R.id.baiduRefreshButton);
	tv_navi[4] = (Button) view.findViewById(R.id.moreDayButton);

	cityNameTV = (TextView) view.findViewById(R.id.cityNameTextView);
	dayWeekTV = (TextView) view.findViewById(R.id.dayWeekTextView);
	weather0TV = (TextView) view.findViewById(R.id.weatherTextView);
	refreshTimeTV = (TextView) view.findViewById(R.id.refreshTimeTextView);

	baiduLiveTV = new TextView[8];
	baiduLiveTV[0] = (TextView) view
		.findViewById(R.id.baiduLivingAirTextView);
	baiduLiveTV[1] = (TextView) view
		.findViewById(R.id.baiduLivingAllergicTextView);
	baiduLiveTV[2] = (TextView) view
		.findViewById(R.id.baiduLivingColdTextView);
	baiduLiveTV[3] = (TextView) view
		.findViewById(R.id.baiduLivingUltravioletTextView);
	baiduLiveTV[4] = (TextView) view
		.findViewById(R.id.baiduLivingDressTextView);
	baiduLiveTV[5] = (TextView) view
		.findViewById(R.id.baiduLivingCosyTextView);
	baiduLiveTV[6] = (TextView) view
		.findViewById(R.id.baiduLivingTravelTextView);
	baiduLiveTV[7] = (TextView) view
		.findViewById(R.id.baiduLivingExeriseTextView);

	baiduMoreLayout = (LinearLayout) view
		.findViewById(R.id.baiduMoreLinearLayout);
	futureLayout = (LinearLayout) view
		.findViewById(R.id.futureWeatherLinearLayout);
	scrollView = (ScrollView) view.findViewById(R.id.weatherScrollView);
	baiduMoreLayout.setVisibility(View.GONE);
	futureLayout.setVisibility(View.GONE);

	futureLV = (ListView) view.findViewById(R.id.futureWeatherListView);
    }

    public class MyClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
	    // TODO Auto-generated method stub
	    switch (v.getId()) {
	    case R.id.returnButton:
		baiduMoreLayout.setVisibility(View.GONE);
		getActivity().finish();
		break;
	    case R.id.moreButton:
		baiduMoreLayout
			.setVisibility(baiduMoreLayout.getVisibility() == View.GONE ? View.VISIBLE
				: View.GONE);
		break;
	    case R.id.editCityButton:
		baiduMoreLayout.setVisibility(View.GONE);
		Intent intent = new Intent(getActivity(),
			BaiduEditCityActivity.class);
		getActivity().startActivity(intent);

		break;
	    case R.id.baiduRefreshButton:
		baiduMoreLayout.setVisibility(View.GONE);
		refreshData();
		break;
	    case R.id.moreDayButton:
		baiduMoreLayout.setVisibility(View.GONE);
		if (futureLayout.getVisibility() == View.GONE) {
		    scrollView.setVisibility(View.GONE);
		    futureLayout.setVisibility(View.VISIBLE);
		    tv_navi[4].setText("����ָ��");
		} else {
		    scrollView.setVisibility(View.VISIBLE);
		    futureLayout.setVisibility(View.GONE);
		    tv_navi[4].setText("δ������");
		}
		break;

	    default:
		break;
	    }
	}

    }

}
