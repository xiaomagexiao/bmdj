package com.joke.bamenshenqi.component.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.component.entity.NativeAppInfo;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.module.ui.activity.FwRecommendActivity;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.CheckData;
import com.joke.bamenshenqi.util.InitPopuWindown;
import com.joke.bamenshenqi.util.LogUtil;
import com.xiaomage.edit.R;

public class FwMainActivity extends BaseActivity implements View.OnClickListener {
	class LineHoder {
		public TextView appName;
		public CheckBox cBox;
		public ImageView img;
		public LinearLayout listclick;
		private FwMainActivity activity;

		private LineHoder(FwMainActivity activity) {

			super();
			this.activity = activity;
		}

		LineHoder(FwMainActivity x0, com.joke.bamenshenqi.component.activity.FwMainActivity x1) {
			this(x0);
		}
	}

	class MyProcessTask extends AsyncTask {
		private ProgressDialog MyDialog;

		MyProcessTask(FwMainActivity a) {
			super();
		}

		protected Object doInBackground(Object[] arg2) {
			return this.doInBackground(((String[]) arg2));
		}

		protected List doInBackground(String[] params) {
			return NativeCtrlBiz.getProcessList(FwMainActivity.this.mNativeCtrl);
		}

		protected void onPostExecute(Object arg1) {
			this.onPostExecute(((List) arg1));
		}

		protected void onPostExecute(List arg6) {
			if (this.MyDialog != null) {
				this.MyDialog.dismiss();
			}

			if (arg6 == null) {
				FwMainActivity.this.handler.sendEmptyMessage(0);
			} else {
				List v0 = FwMainActivity.this.getAppInfos(arg6);
				if (v0 == null) {
					FwMainActivity.this.handler.sendEmptyMessage(0);
				} else {
					FwMainActivity.this.handler.sendEmptyMessage(1);
					FwMainActivity.this.adapter = new NewsAdapter(FwMainActivity.this);
					FwMainActivity.this.listSelect.setAdapter(FwMainActivity.this.adapter);
					FwMainActivity.this.adapter.setData(v0);
					FwMainActivity.this.adapter.notifyDataSetChanged();
				}
			}
		}

		protected void onPreExecute() {
			super.onPreExecute();
			this.MyDialog = ProgressDialog.show(FwMainActivity.this, "获取进程APP", "正在加载数据，请稍等...", true);
		}
	}

	class NewsAdapter extends BaseAdapter {
		private List<NativeAppInfo> data;

		private NewsAdapter(FwMainActivity arg1) {
			super();
		}

		public int getCount() {
			int v0 = this.data == null ? 0 : this.data.size();
			return v0;
		}

		public Object getItem(int location) {
			Object v0 = this.data == null ? null : this.data.get(location);
			return v0;
		}

		public long getItemId(int position) {
			Integer v0 = this.data == null ? null : Integer.valueOf(position);
			return ((long) v0.intValue());
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			LineHoder v0 = null;
			ViewGroup v3 = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(FwMainActivity.this).inflate(R.layout.item_topselectlayout_new, v3);
				v0 = new LineHoder(FwMainActivity.this);
				v0.cBox = (CheckBox) convertView.findViewById(R.id.ck);
				v0.img = (ImageView) convertView.findViewById(R.id.im_appicon);
				v0.appName = (TextView) convertView.findViewById(R.id.tv_appname);
				v0.listclick = (LinearLayout) convertView.findViewById(R.id.ll_listvie);
				convertView.setTag(v0);
			} else {
				v0 = (LineHoder) convertView.getTag();
			}

			if (this.data.get(position).getAppName().equals(FwMainActivity.this.appname)) {
				v0.cBox.setChecked(true);
				this.notifyDataSetChanged();
			} else {
				v0.cBox.setChecked(this.data.get(position).isSelected);
				this.notifyDataSetChanged();
			}

			v0.img.setBackgroundDrawable(this.data.get(position).getAppIcon());
			v0.appName.setText(this.data.get(position).getAppName());
			v0.listclick.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					FwMainActivity.this.loction = position;
					FwMainActivity.this.serverpid = FwMainActivity.this.mNativeCtrl.selectApp(data.get(
							FwMainActivity.this.loction).getPid());
					if (FwMainActivity.this.serverpid == 0) {
						ActivityManager.getInstance().setNativeAppInfo(data.get(FwMainActivity.this.loction));
						ActivityManager.getInstance().setPackageName(
								FwMainActivity.this.newList.get(FwMainActivity.this.loction).getName());
						FwMainActivity.this.startActivity(new Intent(FwMainActivity.this.getApplicationContext(),
								FwRecommendActivity.class));
					} else {
						Toast.makeText(FwMainActivity.this, "选择程序失败，请点左上角图标刷新后重新�?�?", 0).show();
					}
				}
			});
			return convertView;
		}

		public void setData(List arg1) {
			this.data = arg1;
			this.notifyDataSetChanged();
		}
	}

	private NewsAdapter adapter;
	private Drawable apkicon;
	private String apkname;
	private ImageView appIcon;
	private TextView appName;
	private Drawable appicon;
	private String appname;
	private ProcessInfo datapi;
	private Handler handler;
	private ImageView imageChoice;
	private ListView listSelect;
	private int loction;
	private NativeCtrl mNativeCtrl;
	private MyProcessTask mpt;
	private List<ProcessInfo> newList;
	private List processApp;
	private int serverpid;
	private TextView showMessage;

	public FwMainActivity() {
		super();
		this.handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0: {
					FwMainActivity.this.listSelect.setEmptyView(FwMainActivity.this.showMessage);
					break;
				}
				case 1: {
					FwMainActivity.this.changeStutas();
					break;
				}
				case 2: {
					new MyProcessTask(FwMainActivity.this).execute(new String[0]);
					break;
				}
				}

				super.handleMessage(msg);
			}
		};
	}

	private void changeStutas() {
		if (this.datapi != null) {
			if (this.datapi.getPid() == -1) {
				this.appName.setText("八门神器");
				this.appIcon.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.icon));
			} else {
				this.appname = FwMainActivity.getProgramNameByPackageName(((Context) this), this.datapi.getName());
				this.appicon = this.getAppIcon(((Context) this), this.datapi.getName());
				this.appName.setText(this.appname);
				this.appIcon.setBackgroundDrawable(this.appicon);
			}
		}
	}

	public Drawable getAppIcon(Context context, String packageName) {
		PackageManager v3 = context.getPackageManager();
		try {
			return v3.getApplicationInfo(packageName, 0).loadIcon(v3);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apkicon;
	}

	public List getAppInfos(List<ProcessInfo> arg8) {
		this.processApp = new ArrayList();
		this.newList = new ArrayList();
		CheckData v0 = new CheckData();
		int v1;
		for (v1 = 0; v1 < arg8.size(); ++v1) {
			if (!v0.getResult(arg8, v1)) {
				NativeAppInfo v2 = new NativeAppInfo();
				ProcessInfo v3 = new ProcessInfo();
				this.apkname = FwMainActivity.getProgramNameByPackageName(((Context) this), arg8.get(v1).getName());
				this.apkicon = this.getAppIcon(((Context) this), arg8.get(v1).getName());
				if (!TextUtils.isEmpty(this.apkname) && this.apkicon != null) {
					v2.setAppName(this.apkname);
					v2.setAppIcon(this.apkicon);
					v2.setPid(arg8.get(v1).getPid());
					v3.setName(arg8.get(v1).getName());
					this.newList.add(v3);
					this.processApp.add(v2);
				}
			}
		}

		LogUtil.e("newList", "newList" + this.newList);
		if (this.newList.size() == 0) {
			this.handler.sendEmptyMessage(0);
		}

		return this.processApp;
	}

	private List getHomes() {
		ArrayList v1 = new ArrayList();
		PackageManager v2 = this.getPackageManager();
		Intent v0 = new Intent("android.intent.action.MAIN");
		v0.addCategory("android.intent.category.HOME");
		Iterator<ResolveInfo> v5 = v2.queryIntentActivities(v0, 65536).iterator();
		while (v5.hasNext()) {
			((List) v1).add(v5.next().activityInfo.packageName);
		}

		return ((List) v1);
	}

	public static String getProgramNameByPackageName(Context context, String packageName) {
		PackageManager v2 = context.getPackageManager();
		try {
			return v2.getApplicationLabel(v2.getApplicationInfo(packageName, 128)).toString();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packageName;
	}

	private void initBar() {
		this.appIcon = (ImageView) this.findViewById(R.id.im_app_icon);
		this.appName = (TextView) this.findViewById(R.id.tv_app_name);
		this.imageChoice = (ImageView) this.findViewById(R.id.im_choiceApp);
		this.findViewById(R.id.ll_containmenu).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_choiceApp).setOnClickListener(((View.OnClickListener) this));
	}

	private void initPup() {
		this.findViewById(R.id.ll_speed).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_return).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_exit).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_record).setOnClickListener(((View.OnClickListener) this));
	}

	private void initView() {
		this.listSelect = (ListView) this.findViewById(R.id.lv_select);
		this.showMessage = (TextView) this.findViewById(R.id.show_message);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_choiceApp: {
			this.handler.sendEmptyMessage(2);
			break;
		}
		case R.id.ll_containmenu: {
			new InitPopuWindown().initPopu(this, this, v, this.datapi, this.mNativeCtrl);
			break;
		}
		}
	}

	@Nullable
	public void onContainerBound(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
		inflater.inflate(R.layout.fw_activity_main, parent);
		ActivityManager.getInstance().addActivity(((Activity) this));
		this.mpt = new MyProcessTask(this);
		this.initView();
		this.initBar();
		this.initPup();
		this.imageChoice.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.down));
		Intent v7 = new Intent("ELITOR_CLOCK");
		v7.putExtra("msg", "OK");
		((AlarmManager) this.getSystemService("alarm")).setRepeating(0, System.currentTimeMillis(), 86400000,
				PendingIntent.getBroadcast(((Context) this), 0, v7, 134217728));
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean v0 = true;
		if (keyCode == 4) {
			this.show();
		} else if (keyCode == 3) {
			this.show();
		} else {
			v0 = super.onKeyDown(keyCode, event);
		}

		return v0;
	}

	protected void onResume() {
		super.onResume();
	}

	public void serviceConnected(NativeCtrl nativeCtrl) {
		super.serviceConnected(nativeCtrl);
		this.mNativeCtrl = nativeCtrl;
		this.datapi = NativeCtrlBiz.getCurSelectPid(this.mNativeCtrl);
		this.mpt.execute(new String[0]);
	}
}
