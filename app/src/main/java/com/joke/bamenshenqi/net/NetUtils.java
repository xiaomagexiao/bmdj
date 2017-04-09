package com.joke.bamenshenqi.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joke.bamenshenqi.model.ResponseEntity;

public class NetUtils {
	public static HttpClient httpClientFirst;

	public NetUtils() {
		super();
	}

	public static HttpResponse doGet(Context context, String host) throws Exception {
		if (!NetUtils.isNetworkConnected(context)) {
			throw new Exception("网络未连?");
		}

		return NetUtils.getHttpClient4BBS(context).execute(new HttpGet(host));
	}

	public static HttpResponse doPost(Context context, String host, final String content) throws Exception {
		if (!NetUtils.isNetworkConnected(context)) {
			throw new Exception("网络未连?");
		}

		HttpPost v4 = new HttpPost(host);
		v4.setEntity(new EntityTemplate(new ContentProducer() {
			public void writeTo(OutputStream outstream) throws IOException {
				OutputStreamWriter v0 = new OutputStreamWriter(outstream, "gbk");
				((Writer) v0).write(content);
				((Writer) v0).flush();
			}
		}));
		HttpClient v2 = NetUtils.getHttpClient4BBS(context);
		v4.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=gbk");
		v4.addHeader("Accept-Encoding", "gzip,deflate");
		return v2.execute(((HttpUriRequest) v4));
	}

	public static HttpResponse doPostMap(Context context, String host, Object[] obj) {
		HttpPost v8 = new HttpPost(host);
		List<String> v5 =  (List<String>)obj[0];
		List<String> v9 = (List<String>)obj[1];
		ArrayList v7 = new ArrayList();
		int v4;
		for (v4 = 0; v4 < v5.size(); ++v4) {
			((List) v7).add(new BasicNameValuePair(v5.get(v4), v9.get(v4)));
		}

		try {
			v8.setEntity(new UrlEncodedFormEntity(((List) v7)));
			HttpClient v2 = NetUtils.getHttpClient(context);
			v8.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=gbk");
			v8.addHeader("Accept-Encoding", "gzip,deflate");
			return v2.execute(((HttpUriRequest) v8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public synchronized static HttpClient getHttpClient(Context context) {
		Class v6 = NetUtils.class;
		BasicHttpParams v1 = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(((HttpParams) v1), 10000);
		HttpConnectionParams.setSoTimeout(((HttpParams) v1), 10000);
		HttpClientParams.setRedirecting(((HttpParams) v1), true);
		HttpProtocolParams.setUserAgent(((HttpParams) v1),
				"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.14) Gecko/20110218 Firefox/3.6.14");
		HttpProtocolParams.setVersion(((HttpParams) v1), HttpVersion.HTTP_1_1);
		HttpClientParams.setCookiePolicy(((HttpParams) v1), "compatibility");
		HttpProtocolParams.setUseExpectContinue(((HttpParams) v1), false);
		SchemeRegistry v3 = new SchemeRegistry();
		v3.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		v3.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		ThreadSafeClientConnManager v0 = new ThreadSafeClientConnManager(((HttpParams) v1), v3);
		HttpHost v2 = NetUtils.getProxy(context);
		if (v2 != null) {
			((HttpParams) v1).setParameter("http.route.default-proxy", v2);
		}

		((HttpParams) v1).setParameter("Accept-Encoding", "gzip,deflate");
		return new DefaultHttpClient(((ClientConnectionManager) v0), ((HttpParams) v1));
	}

	public synchronized static HttpClient getHttpClient4BBS(Context context) {
		HttpClient v5;
		Class v6 = NetUtils.class;
		if (NetUtils.httpClientFirst != null) {
			v5 = NetUtils.httpClientFirst;
		} else {
			BasicHttpParams v1 = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(((HttpParams) v1), 10000);
			HttpConnectionParams.setSoTimeout(((HttpParams) v1), 10000);
			HttpClientParams.setRedirecting(((HttpParams) v1), true);
			HttpProtocolParams.setUserAgent(((HttpParams) v1),
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.14) Gecko/20110218 Firefox/3.6.14");
			HttpProtocolParams.setVersion(((HttpParams) v1), HttpVersion.HTTP_1_1);
			HttpClientParams.setCookiePolicy(((HttpParams) v1), "compatibility");
			HttpProtocolParams.setUseExpectContinue(((HttpParams) v1), false);
			SchemeRegistry v3 = new SchemeRegistry();
			v3.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			v3.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
			ThreadSafeClientConnManager v0 = new ThreadSafeClientConnManager(((HttpParams) v1), v3);
			HttpHost v2 = NetUtils.getProxy(context);
			if (v2 != null) {
				((HttpParams) v1).setParameter("http.route.default-proxy", v2);
			}

			((HttpParams) v1).setParameter("Accept-Encoding", "gzip,deflate");
			if (NetUtils.httpClientFirst == null) {
				NetUtils.httpClientFirst = new DefaultHttpClient(((ClientConnectionManager) v0), ((HttpParams) v1));
			}

			v5 = NetUtils.httpClientFirst;
		}

		return v5;
	}

	public static String getNetworkType(Context context) {
		String v3 = null;
		NetworkInfo v0 = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
		if (v0 != null && ("mobile".equalsIgnoreCase(v0.getTypeName()))) {
			v3 = v0.getExtraInfo();
		}

		return v3;
	}

	public static HttpHost getProxy(Context context) {
		HttpHost v1;
		int v4 = 80;
		String v0 = NetUtils.getNetworkType(context);
		if (v0 == null || !v0.equalsIgnoreCase("cmwap")) {
			if (v0 != null && (v0.equalsIgnoreCase("uniwap"))) {
				return new HttpHost("10.0.0.172", v4, "http");
			}

			if (v0 != null && (v0.equalsIgnoreCase("ctwap"))) {
				return new HttpHost("10.0.0.200", v4, "http");
			}

			v1 = null;
		} else {
			v1 = new HttpHost("10.0.0.172", v4, "http");
		}

		return v1;
	}

	public static ResponseEntity getResponseEntity(Context context, String url) {
		ResponseEntity v2 = null;
		try {
			HttpResponse v1 = NetUtils.doGet(context, url);
			v2 = v1.getStatusLine().getStatusCode() == 200 ? NetUtils.jsonToResponseEntity(v1) : new ResponseEntity(0,
					"status code is : " + v1.getStatusLine().getStatusCode(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v2;
	}

	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		BufferedReader v2 = new BufferedReader(new InputStreamReader(stream, charset), 8192);
		StringWriter v3 = new StringWriter();
		char[] v0 = new char[8192];
		while (true) {
			int v1 = v2.read(v0);
			if (v1 <= 0) {
				break;
			}

			v3.write(v0, 0, v1);
		}

		String v4 = v3.toString();
		if (stream != null) {
			stream.close();
		}

		return v4;
	}

	public static boolean isNetworkConnected(Context context) {
		boolean v2;
		if (context != null) {
			NetworkInfo v0 = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
			if (v0 == null) {
				v2 = false;
			} else if (v0.isConnected()) {
				v2 = v0.isAvailable();
			} else {
				v2 = false;
			}
		} else {
			v2 = false;
		}

		return v2;
	}

	private static ResponseEntity jsonToResponseEntity(HttpResponse httpResponse) throws Exception {
		return new Gson().fromJson(NetUtils.response2String(httpResponse), new TypeToken() {
		}.getType());
	}

	public static HttpResponse postData(String posturl, List arg8) {
		BasicHttpParams v3 = new BasicHttpParams();
		((HttpParams) v3).setParameter("charset", "gbk");
		DefaultHttpClient v1 = new DefaultHttpClient(((HttpParams) v3));
		HttpPost v2 = new HttpPost(posturl);
		v2.addHeader("charset", "gbk");
		HttpResponse result = null ;
		try {
			v2.setEntity(new UrlEncodedFormEntity(arg8, "gbk"));
			result = ((HttpClient) v1).execute(((HttpUriRequest) v2));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static ResponseEntity postResponseEntity(Context context, String url, String content) {
		ResponseEntity v2 = null;
		try {
			HttpResponse v1 = NetUtils.doPost(context, url, content);
			v2 = v1.getStatusLine().getStatusCode() == 200 ? NetUtils.jsonToResponseEntity(v1) : new ResponseEntity(0,
					"status code is : " + v1.getStatusLine().getStatusCode(), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v2;
	}

	public static String response2String(HttpResponse httpResponse) {
		GZIPInputStream v2_1 = null;
		try {
			InputStream v2 = httpResponse.getEntity().getContent();
			Header v0 = httpResponse.getFirstHeader("Content-Encoding");
			if (v0 != null && (v0.getValue().equalsIgnoreCase("gzip"))) {
				v2_1 = new GZIPInputStream(v2);
			}

			return NetUtils.getStreamAsString(((InputStream) v2_1), "UTF-8");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
