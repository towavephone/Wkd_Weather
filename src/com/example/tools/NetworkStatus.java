package com.example.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {
	
	Context context ;
	
	public NetworkStatus(Context context) {
		this.context = context ;
	}

	/**
	 * �����������״̬
	 * @return
	 */
	public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null){
        	NetworkInfo[] info = connectivity.getAllNetworkInfo();
        	if (info != null){
        		for (int i = 0; i < info.length; i++)
        			if (info[i].getState() == NetworkInfo.State.CONNECTED){
        				return true;
        			}
        	}
        	else
        		return false;
		}
		return false;
	}
}
