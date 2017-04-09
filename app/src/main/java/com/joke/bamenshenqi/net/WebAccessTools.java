package com.joke.bamenshenqi.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;

public class WebAccessTools {
	private Context context;

	public WebAccessTools(Context context) {
		super();
		this.context = context;
	}

	public static String getWebContent(String url) {
		String v0 = null;
		HttpGet v4 = new HttpGet(url);
		BasicHttpParams v3 = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(((HttpParams) v3), 3000);
		HttpConnectionParams.setSoTimeout(((HttpParams) v3), 5000);
		DefaultHttpClient v2 = new DefaultHttpClient(((HttpParams) v3));
		try {
			HttpResponse v5 = ((HttpClient) v2).execute(((HttpUriRequest) v4));
			if (v5.getStatusLine().getStatusCode() == 200) {
				v0 = EntityUtils.toString(v5.getEntity());
				((HttpClient) v2).getConnectionManager().shutdown();
			} else {
				((HttpClient) v2).getConnectionManager().shutdown();
				v0 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return v0;
	}

	public static String postWebContent(String url, List arg7) {
		HttpPost v1 = new HttpPost(url);
		String v3 = null;
		try {
			v1.setEntity(new UrlEncodedFormEntity(arg7, "UTF-8"));
			HttpResponse v2 = new DefaultHttpClient().execute(((HttpUriRequest) v1));
			v3 = ((HttpResponse) v2).getStatusLine().getStatusCode() == 200 ? EntityUtils.toString(((HttpResponse) v2)
					.getEntity()) : null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v3;
	}
}
