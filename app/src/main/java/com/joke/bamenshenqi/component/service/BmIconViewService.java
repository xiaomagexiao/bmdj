package com.joke.bamenshenqi.component.service;

import java.util.Iterator;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.view.View;

import com.joke.bamenshenqi.component.view.BmFloatView;
import com.joke.bamenshenqi.jni.NativeCtrl;

public class BmIconViewService extends Service {
	public class MyBinder extends Binder {
		BmIconViewService service;

		public MyBinder(BmIconViewService service) {
			super();
			this.service = service;
		}

		public BmIconViewService getService() {
			return BmIconViewService.this;
		}
	}

	private static BmFloatView bmFloatView;
	private NativeCtrl mNativeCtrl;
	private final MyBinder myBinder;
	private int ret;

	public BmIconViewService() {
		super();
		this.myBinder = new MyBinder(this);
	}

	public void closeSelf() {
		this.stopSelf();
	}

	private synchronized BmFloatView getFloatViewInstance() {
		if (BmIconViewService.bmFloatView == null) {
			BmIconViewService.bmFloatView = new BmFloatView(this.getApplicationContext());
		}
		return BmIconViewService.bmFloatView;
	}

	public NativeCtrl getNativeCtrl() {
		return this.mNativeCtrl;
	}

	public int getRet() {
		return this.ret;
	}

	public void hideView(boolean isHideOnly) {
		if (BmIconViewService.bmFloatView != null || (BmIconViewService.bmFloatView.isShowing())) {
			BmIconViewService.bmFloatView.hide();
			if (isHideOnly) {
				this.moveTask();
			}
		}
	}

	public NativeCtrl initNativeCtrl() {
		if (this.mNativeCtrl == null) {
			this.mNativeCtrl = NativeCtrl.getInstance();
		}

		this.ret = this.ret();
		return this.mNativeCtrl;
	}

	private void moveTask() {
		Object v0 = this.getSystemService("activity");
		Iterator v3 = ((ActivityManager) v0).getRunningTasks(50).iterator();
		while (v3.hasNext()) {
			Object v1 = v3.next();
			if (!((ActivityManager.RunningTaskInfo) v1).topActivity.getPackageName().equals(this.getPackageName())) {
				continue;
			}

			if (Build.VERSION.SDK_INT < 11) {
				continue;
			}

			((ActivityManager) v0).moveTaskToFront(((ActivityManager.RunningTaskInfo) v1).id, 2);
		}
	}

	public IBinder onBind(Intent intent) {
		return this.myBinder;
	}

	public void onCreate() {
		super.onCreate();
		this.initNativeCtrl();
		BmIconViewService.bmFloatView = this.getFloatViewInstance();
		BmIconViewService.bmFloatView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				BmIconViewService.this.hideView(true);
			}
		});
	}

	public void onDestroy() {
		super.onDestroy();
		this.hideView(false);
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		BmIconViewService.bmFloatView.show();
		return super.onStartCommand(intent, 1, startId);
	}

	public int ret() {
		int v2;
		if (!this.mNativeCtrl.hasRoot()) {
			this.ret = -99;
			v2 = this.ret;
		} else {
			boolean v1 = Build.VERSION.SDK_INT >= 21 ? true : false;
			this.ret = this.mNativeCtrl.init(this.getAssets(), this.getPackageName(), this.getFilesDir().getPath(), v1);
			v2 = this.ret;
		}

		return v2;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public void showView() {
		if (BmIconViewService.bmFloatView != null) {
			BmIconViewService.bmFloatView.show();
		}
	}
}
