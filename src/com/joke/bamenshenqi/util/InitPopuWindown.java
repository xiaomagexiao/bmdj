package com.joke.bamenshenqi.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.joke.bamenshenqi.component.activity.BaseActivity;
import com.joke.bamenshenqi.component.activity.FwReviseActivity;
import com.joke.bamenshenqi.component.activity.MenuSpeedActivity;
import com.joke.bamenshenqi.component.view.FilterPopupWindowTools;
import com.joke.bamenshenqi.component.view.FilterPopupWindowTools.MyOnItemClick;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;

public class InitPopuWindown {
	private BaseActivity mactivity;
	private Context mcontext;
	private ProcessInfo mdatapi;
	private NativeCtrl nNativeCtrl;

	public InitPopuWindown() {
		super();
	}

	static ProcessInfo access$000(InitPopuWindown x0) {
		return x0.mdatapi;
	}

	static Context access$100(InitPopuWindown x0) {
		return x0.mcontext;
	}

	static BaseActivity access$200(InitPopuWindown x0) {
		return x0.mactivity;
	}

	static NativeCtrl access$300(InitPopuWindown x0) {
		return x0.nNativeCtrl;
	}

	public void initPopu(final BaseActivity activity, Context context, View v, ProcessInfo datapi, NativeCtrl mNativeCtrl) {
		this.mcontext = context;
		this.mdatapi = datapi;
		this.nNativeCtrl = mNativeCtrl;
		this.mactivity = activity;
		ArrayList v1 = new ArrayList();
		v1.add("修改记录");
		v1.add("加速");
		// v1.add("回主�?");
		v1.add("退出");
		FilterPopupWindowTools v0 = new FilterPopupWindowTools(context, v.getWidth(), -2, ((List) v1));
		v0.setItemClick(new MyOnItemClick() {
			public void onItemClick(AdapterView arg7, View subView, int position, long arg3) {
				int v5 = -1;
				switch (position) {
				case 0: {
					if (InitPopuWindown.this.mdatapi == null) {
						Toast.makeText(InitPopuWindown.this.mcontext, "请先选择程序", 1).show();
						return;
					}

					if (InitPopuWindown.this.mdatapi.getPid() == v5) {
						Toast.makeText(InitPopuWindown.this.mcontext, "请先选择程序", 1).show();
						return;
					}

					InitPopuWindown.this.mcontext
							.startActivity(new Intent(InitPopuWindown.this.mcontext, FwReviseActivity.class));
					break;
				}
				case 1: {
					int v1 = Build.VERSION.SDK_INT < 21 ? 1 : 0;
					if (v1 != 0) {
						if (InitPopuWindown.this.mdatapi == null) {
							Toast.makeText(InitPopuWindown.this.mcontext, "请先选择程序", 1).show();
							return;
						}

						if (InitPopuWindown.this.mdatapi.getPid() == v5) {
							Toast.makeText(InitPopuWindown.this.mcontext, "请先选择程序", 1).show();
							return;
						}

						InitPopuWindown.this.mcontext.startActivity(new Intent(InitPopuWindown.this.mcontext,
								MenuSpeedActivity.class));
						return;
					}

					Toast.makeText(InitPopuWindown.this.mcontext, "抱歉!当前系统版本过高，暂仅支�?.0以下安卓版本", 1).show();
					break;
				}
				// case 2: {
				// AppUtil.runApp(InitPopuWindown.this.mcontext,
				// "com.zhangkongapp.joke.bamenshenqi",
				// "com.joke.bamenshenqi.components.activity.MainActivity");
				// InitPopuWindown.this.mactivity.finish();
				// break;
				// }
				case 2: {
					NativeCtrl.release();
					activity.closeSelf();
					ActivityManager.getInstance().exit();
					break;
				}
				}
			}
		});
		v0.showAsDropDown(v);
	}
}
