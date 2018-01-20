package com.example.wkd_weather;

import com.towave.baidufragment.baidu_weather_fragment;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;

public class BaiduWeatherMainActivity extends Activity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	fragmentManager=getFragmentManager();
	fragmentTransaction=fragmentManager.beginTransaction();
	
	fragmentTransaction.add(R.id.activity_main, new baidu_weather_fragment());
	fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

}
