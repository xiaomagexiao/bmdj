package com.joke.bamenshenqi.module.ui.activity;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joke.bamenshenqi.biz.DataCollectionBiz;
import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.component.activity.BaseActivity;
import com.joke.bamenshenqi.component.activity.FwMainActivity;
import com.joke.bamenshenqi.component.activity.FwReviseDetailActivity;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.HackPackageDetail;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.model.ResponseEntity;
import com.joke.bamenshenqi.module.ui.view.common.CommonAdapter;
import com.joke.bamenshenqi.module.ui.view.common.ViewHolder;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.InitPopuWindown;
import com.xiaomage.edit.R;

public class FwRecommendActivity extends BaseActivity implements View.OnClickListener {
	class MyTask extends AsyncTask {
		private Context context;
		private FwRecommendActivity ac;

		public MyTask(FwRecommendActivity ac, Context context) {
			super();
			this.ac = ac;
			this.context = context;
		}

		protected ResponseEntity doInBackground(String[] params) {
			ResponseEntity v1 = null;
			if (FwRecommendActivity.this.packageName != null && FwRecommendActivity.this.versionCode != null) {
				try {
					v1 = DataCollectionBiz.getHackPackageListResult(this.context, FwRecommendActivity.this.packageName,
							FwRecommendActivity.this.versionCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return v1;
		}

		protected Object doInBackground(Object[] arg2) {
			return this.doInBackground(((String[]) arg2));
		}

		protected void onPostExecute(ResponseEntity entity) {
			if (entity == null && entity.getStatus() == 0) {
				Log.e("typetoken", "onPostExecute" + entity.getResult());
				Object v1 = new Gson().fromJson(entity.getResult(), new TypeToken() {
				}.getType());
				if (v1 == null) {
					FwRecommendActivity.this.listView.setEmptyView(FwRecommendActivity.this.emptyView);
				} else {
					FwRecommendActivity.this.adapter.setData(((List) v1));
					FwRecommendActivity.this.adapter.notifyDataSetChanged();
				}
			}
		}

		protected void onPostExecute(Object arg1) {
			this.onPostExecute(((ResponseEntity) arg1));
		}
	}

	private CommonAdapter adapter;
	private ImageView appImage;
	private TextView appName;
	private ProcessInfo datapi;
	private TextView editText;
	private View emptyView;
	private ListView listView;
	private NativeCtrl mNativeCtrl;
	private String packageName;
	private String versionCode;

	private void getPackgeName() {
		this.packageName = ActivityManager.getInstance().getPackageName();
		try {
			this.versionCode = this.getPackageManager().getPackageInfo(this.packageName, 0).versionCode + "";
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void getTitleNameDrable() {
		this.appName = (TextView) this.findViewById(R.id.tv_app_name);
		this.appImage = (ImageView) this.findViewById(R.id.im_app_icon);
		if (ActivityManager.getInstance().getNativeAppInfo() != null) {
			this.appImage.setBackgroundDrawable(ActivityManager.getInstance().getNativeAppInfo().getAppIcon());
			this.appName.setText(ActivityManager.getInstance().getNativeAppInfo().getAppName());
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_recommend_edit: {
			if (this.datapi == null) {
				Toast.makeText(((Context) this), "不能搜索，请先�?择程", 1).show();
				return;
			}

			if (this.datapi.getPid() == -1) {
				Toast.makeText(((Context) this), "不能搜索，请先�?择程", 1).show();
				this.startActivity(new Intent(((Context) this), FwMainActivity.class));
				return;
			}

			this.startActivity(new Intent(((Context) this), FwSearchActivity.class));
			break;
		}
		case R.id.ll_choiceApp: {
			this.startActivity(new Intent(((Context) this), FwMainActivity.class));
			break;
		}
		case R.id.ll_containmenu: {
			new InitPopuWindown().initPopu(this, this, v, this.datapi, this.mNativeCtrl);
			break;
		}
		}
	}

	public void onContainerBound(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		inflater.inflate(R.layout.fw_activity_recommend, parent);
		this.getTitleNameDrable();
		ActivityManager.getInstance().addActivity(((Activity) this));
		this.editText = (TextView) this.findViewById(R.id.id_recommend_edit);
		this.listView = (ListView) this.findViewById(R.id.id_recommend_recommand_list);
		this.findViewById(R.id.ll_containmenu).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_choiceApp).setOnClickListener(((View.OnClickListener) this));
		this.emptyView = (View) this.findViewById(R.id.id_recommend_empty);
		this.editText.setOnClickListener(((View.OnClickListener) this));
		this.adapter = new CommonAdapter(this, null, R.layout.abc_action_bar_title_item) {
			public void convert(ViewHolder helper, HackPackageDetail item) {
				View v2 = helper.getView(R.id.order);
				View v1 = helper.getView(R.id.order_sx);
				View v0 = helper.getView(R.id.order_rs);
				((TextView) v2).setText(helper.getPosition() + 1 + ".");
				((TextView) v1).setText(item.getHackMeaning());
				((TextView) v0).setText(item.getHackCount() + "?");
			}

			public void convert(ViewHolder arg1, Object arg2) {
				this.convert(arg1, ((HackPackageDetail) arg2));
			}
		};
		this.listView.setAdapter(this.adapter);
		this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView arg6, View view, int position, long id) {
				Object v0 = FwRecommendActivity.this.adapter.getItem(position);
				Intent v2 = new Intent(FwRecommendActivity.this, FwReviseDetailActivity.class);
				Bundle v1 = new Bundle();
				v1.putSerializable("HackPackageDetail", ((Serializable) v0));
				v2.putExtras(v1);
				FwRecommendActivity.this.startActivity(v2);
			}
		});
		this.listView.setEmptyView(this.emptyView);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean v0;
		if (keyCode == 4) {
			this.show();
			v0 = true;
		} else {
			v0 = super.onKeyDown(keyCode, event);
		}

		return v0;
	}

	public void serviceConnected(NativeCtrl nativeCtrl) {
		super.serviceConnected(nativeCtrl);
		this.mNativeCtrl = nativeCtrl;
		this.datapi = NativeCtrlBiz.getCurSelectPid(this.mNativeCtrl);
	}
}
