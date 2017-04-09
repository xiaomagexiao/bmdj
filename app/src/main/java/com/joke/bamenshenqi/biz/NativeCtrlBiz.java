package com.joke.bamenshenqi.biz;

import java.util.List;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joke.bamenshenqi.component.entity.ReportInfo;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.model.ResponseForJNI;
import com.joke.bamenshenqi.model.SearchData;
import com.joke.bamenshenqi.util.LogUtil;

public class NativeCtrlBiz {
	private NativeCtrlBiz() {
		super();
	}

	public static void cancelSearch(NativeCtrl mNativeCtrl) {
		if (mNativeCtrl == null || !mNativeCtrl.isConnect()) {
			Class v1 = NativeCtrlBiz.class;
			String v0 = mNativeCtrl == null ? "NativeCtrl为空" : "NativeCtrl.isConnect=" + mNativeCtrl.isConnect();
			LogUtil.e(v1, v0);
		} else {
			mNativeCtrl.cancelSearch();
		}
	}

	public static String getActivitys(NativeCtrl mNativeCtrl) {
		return new Gson().fromJson(mNativeCtrl.getActivitys(), ResponseForJNI.class).getResult();
	}

	public static ProcessInfo getCurSelectPid(NativeCtrl mNativeCtrl) {
		ProcessInfo v1 = null;
		String v3 = mNativeCtrl.getCurSelect();
		Gson v2 = new Gson();
		ResponseForJNI v4 = v2.fromJson(v3, ResponseForJNI.class);
		if (v4 != null && v4.getStatus() == 0) {
			v1 = v2.fromJson(((ResponseForJNI) v4).getResult(), ProcessInfo.class);
		}
		return v1;
	}

	public static List getDataList(NativeCtrl mNativeCtrl) {
		Object v2_1 = null;
		List v2 = null;
		String v1 = mNativeCtrl.getLockData();
		if (v1 != null) {
			Gson v0 = new Gson();
			ResponseForJNI v3 = v0.fromJson(v1, ResponseForJNI.class);
			if (v3.getStatus() == 0) {
				Log.e("typetoken", "getDataList" + v3.getResult());
				v2_1 = v0.fromJson(v3.getResult(), new TypeToken() {
				}.getType());
			}
		}

		return ((List) v2_1);
	}

	public static List getProcessList(NativeCtrl mNativeCtrl) {
		Object v2_1 = null;
		List v2 = null;
		String v1 = mNativeCtrl.getProcessList();
		if (v1 != null) {
			Gson v0 = new Gson();
			Object v3 = v0.fromJson(v1, ResponseForJNI.class);
			if (((ResponseForJNI) v3).getStatus() == 0) {
				Log.e("info", "" + ((ResponseForJNI) v3).getResult());
				v2_1 = v0.fromJson(((ResponseForJNI) v3).getResult(), new TypeToken<List<ProcessInfo>>() {
				}.getType());
			}
		}

		return ((List) v2_1);
	}

	public static int getStutas(NativeCtrl mNativeCtrl) {
		int v3 = -1;
		String v1 = mNativeCtrl.clearData();
		if (v1 != null && new Gson().fromJson(v1, ResponseForJNI.class).getStatus() == 0) {
			v3 = 0;
		}

		return v3;
	}

	public static List getUserAppUsage(NativeCtrl mNativeCtrl, Context context) {
		Object v5_1 = null;
		List v5 = null;
		String v4 = mNativeCtrl.getUsagestats(((WifiManager) context.getSystemService("wifi")).getConnectionInfo()
				.getMacAddress());
		if (v4 != null) {
			Gson v2 = new Gson();
			ResponseForJNI v7 = v2.fromJson(v4, ResponseForJNI.class);
			if (((ResponseForJNI) v7).getStatus() == 0) {
				Log.e("typetoken", "getUserAppUsage" + v7.getResult());
				v5_1 = v2.fromJson(((ResponseForJNI) v7).getResult(), new TypeToken() {
				}.getType());
			}
		}

		return ((List) v5_1);
	}

	public static void modifyAll(NativeCtrl mNativeCtrl, List<ReportInfo> arg6, String modifyContent) {
		if (mNativeCtrl == null || !mNativeCtrl.isConnect()) {
			Class v3 = NativeCtrlBiz.class;
			String v2 = mNativeCtrl == null ? "NativeCtrl为空" : "NativeCtrl.isConnect=" + mNativeCtrl.isConnect();
			LogUtil.e(v3, v2);
		} else {
			// type 0 修改整数类型
			mNativeCtrl.modifyAll(arg6, modifyContent, 0);
		}
	}

	public static int searchData(NativeCtrl mNativeCtrl, String data) {
		int v1;
		if (mNativeCtrl == null || !mNativeCtrl.isConnect()) {
			Class v2 = NativeCtrlBiz.class;
			String v1_1 = mNativeCtrl == null ? "NativeCtrl为空" : "NativeCtrl.isConnect=" + mNativeCtrl.isConnect();
			LogUtil.e(v2, v1_1);
			v1 = 100;
		} else {
			int v0 = data.contains(".") ? 1 : 0;
			v1 = mNativeCtrl.searchData(data, v0);
		}

		return v1;
	}

	public static SearchData searchForResultnew(NativeCtrl mNativeCtrl) {
		Object v1_1 = null;
		long v8 = 500;
		SearchData v1 = null;
		int v0 = 0;
		while (v0 < 40) {
			String v4 = mNativeCtrl.getAsyncReport();
			if (TextUtils.isEmpty(((CharSequence) v4))) {
				NativeCtrlBiz.sleep(v8);
				++v0;
				continue;
			} else {
				Gson v2 = new Gson();
				Object v3 = v2.fromJson(v4, ResponseForJNI.class);
				if (((ResponseForJNI) v3).getStatus() == 1) {
					NativeCtrlBiz.sleep(v8);
					++v0;
					continue;
				} else if (((ResponseForJNI) v3).getStatus() == 0) {
					v1_1 = v2.fromJson(((ResponseForJNI) v3).getResult(), SearchData.class);
					if (v1_1 == null) {
						continue;
					}
				} else {
					NativeCtrlBiz.sleep(v8);
					++v0;
					continue;
				}
			}

			break;
		}

		return ((SearchData) v1_1);
	}

	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
