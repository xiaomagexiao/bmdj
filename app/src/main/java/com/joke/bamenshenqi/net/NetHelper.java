package com.joke.bamenshenqi.net;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.widget.Toast;

import com.joke.bamenshenqi.component.app.MyApplication;
import com.joke.bamenshenqi.model.SystemInfo;

public class NetHelper {
	static class NetAsyncTask extends AsyncTask {
		private final WeakReference mListenerReference;
		private String mServiceMethod;
		private String mServiceUrl;

		public  NetAsyncTask(String url, String method, OnResponseListener listener) {
			super();
			this.mServiceUrl = url;
			this.mServiceMethod = method;
			this.mListenerReference = new WeakReference(listener);
		}

		protected Object doInBackground(Object[] arg2) {
			return this.doInBackground(((BasicNameValuePair[]) arg2));
		}

		protected Object doInBackground(BasicNameValuePair[] params) {
			Object v2;
			if (!this.isCancelled()) {
				if (params != null && params.length != 0) {
					ArrayList v0 = new ArrayList();
					int v1;
					for (v1 = 0; v1 < params.length; ++v1) {
						((List) v0).add(new BasicNameValuePair(params[v1].getName(), params[v1].getValue()));
					}

					v2 = NetHelper.processResponse(this.mServiceMethod,
							WebAccessTools.postWebContent(this.mServiceUrl, ((List) v0)));
					return v2;
				}

				v2 = NetHelper.processResponse(this.mServiceMethod, WebAccessTools.getWebContent(this.mServiceUrl));
			} else {
				v2 = null;
			}

			return v2;
		}

		protected void onPostExecute(Object result) {
			if (!this.isCancelled()) {
				Object v0 = this.mListenerReference.get();
				if (v0 != null) {
					((OnResponseListener) v0).OnResponse(this, result);
				}
			}
		}
	}

	public interface OnResponseListener {
		void OnResponse(Object arg1, Object arg2);
	}

	public static final String SERVICE_HOST_NAME = "http://192.168.1.220:8080/bamenCollection/";

	public NetHelper() {
		super();
	}

	static Object access$000(String x0, String x1) {
		return NetHelper.processResponse(x0, x1);
	}

	public static void cancel(AsyncTask tag) {
		if (tag != null && ((tag instanceof NetAsyncTask))) {
			tag.cancel(true);
		}
	}

	public static boolean isNetworkAvailable() {
		boolean v0 = MyApplication.isNetworkAvailable();
		if (!v0) {
			Toast.makeText(MyApplication.getContext(), "无网络连�?", 0).show();
		}

		return v0;
	}

	private static Object parseGetCollectionResult(String result) {
		return result;
	}

	private static Object parseGetStrategyDisplayDetailResult(String result) {
		return result;
	}

	private static Object parseGetStrategyDisplayResult(String result) {
		return result;
	}

	private static Object processResponse(String method, String result) {
		Object v2 = null;
		try {
			Method v1 = NetHelper.class.getDeclaredMethod("parse" + method + "Result", String.class);
			if (v1 != null) {
				v1.setAccessible(true);
				v2 = v1.invoke(null, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v2;
	}

	public static Object requestGetCollection(SystemInfo systemInfo, List arg6, List arg7, OnResponseListener listener) {
		Object v1 = null;
		if (!NetHelper.isNetworkAvailable()) {
			v1 = null;
		} else {
			NetAsyncTask v1_1 = new NetAsyncTask("http://192.168.1.220:8080/bamenCollection/collection", "GetCollection",
					listener);
			v1_1.execute(new BasicNameValuePair[] { new BasicNameValuePair("", "") });
			v1_1.execute(new BasicNameValuePair[0]);
		}

		return v1;
	}

	public static Object requestGetStrategyDisplay(String type, String packageName, String versionCode, int page,
			OnResponseListener listener) {
		Object v0 = null;
		if (!NetHelper.isNetworkAvailable()) {
			v0 = null;
		} else {
			NetAsyncTask v0_1 = new NetAsyncTask("http://192.168.1.220:8080/bamenCollection/query?" + type + "&" + packageName
					+ "&" + versionCode + "&" + page, "GetStrategyDisplay", listener);
			v0_1.execute(new BasicNameValuePair[0]);
		}

		return v0;
	}

	public static Object requestGetStrategyDisplayDetail(String type, String hackPackageId, String appBase, String appMoudle,
			String appOffset, int page, OnResponseListener listener) {
		Object v0 = null;
		if (!NetHelper.isNetworkAvailable()) {
			v0 = null;
		} else {
			NetAsyncTask v0_1 = new NetAsyncTask("http://192.168.1.220:8080/bamenCollection/query?" + type + "&" + hackPackageId
					+ "&" + appBase + "&" + appMoudle + "&" + appOffset + "&" + page, "GetStrategyDisplayDetail", listener);
			v0_1.execute(new BasicNameValuePair[0]);
		}

		return v0;
	}
}
