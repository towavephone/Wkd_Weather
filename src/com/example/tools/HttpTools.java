package com.example.tools;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.example.services.WeatherSharedPreference;
import android.content.Context;
import android.widget.Toast;

public class HttpTools {
    public static String getBaiduWeatherHtml(Context context) throws Exception {
	String htmlContent = null;
	String cityname = WeatherSharedPreference.getCityName(context).trim();
	cityname = URLEncoder.encode(cityname, "utf-8");
	String path = UrlInfo.baiduWeatherUrl + cityname;
	HttpGet httpGet = new HttpGet(path);
	HttpClient httpClient = new DefaultHttpClient();
	// ����ʱ
	httpClient.getParams().setParameter(
		CoreConnectionPNames.CONNECTION_TIMEOUT, 8000);
	// ��ȡ��ʱ
	httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		8000);

	HttpResponse httpResponse = httpClient.execute(httpGet);
	int statusCode;
	if ((statusCode=httpResponse.getStatusLine().getStatusCode()) == HttpStatus.SC_OK) {
	    htmlContent = EntityUtils.toString(httpResponse.getEntity(),
		    "utf-8");
	    Document document = Jsoup.parse(htmlContent);
	    htmlContent="";
	  //����
	    Elements cityNameElements=document.select("div.city_name");
	    htmlContent+=cityNameElements.toString();
	  //����
	    Elements dateTimeElements=document.select("div#but_date");
	    htmlContent+=dateTimeElements.toString();
	  //�������
	    Elements lowDegreeElements=document.select("div#low_degree");
	    htmlContent+=lowDegreeElements.toString();
	  //δ������
	    Elements futureWeatherElements=document.select("div#weather_future");
	    htmlContent+=futureWeatherElements.toString();
	    //����ָ��
	    Elements livingElements=document.select("div.index_value");
	    htmlContent+=livingElements.toString();
	}else{
	    Toast.makeText(context, "��������Ӧ�쳣�����쳣�룺0000,get��\n"+HttpInfo.httpStatus(statusCode), Toast.LENGTH_SHORT).show();
	}

	return htmlContent;

    }
}
