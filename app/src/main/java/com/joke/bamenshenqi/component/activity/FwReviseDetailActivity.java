package com.joke.bamenshenqi.component.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joke.bamenshenqi.biz.DataCollectionBiz;
import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.HackPackageDetail;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.model.ResponseEntity;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.InitPopuWindown;
import com.xiaomage.edit.R;

public class FwReviseDetailActivity extends BaseActivity implements View.OnClickListener {

	static class DataStrategyDetailTask extends AsyncTask {
		FwReviseDetailActivity activity;

		DataStrategyDetailTask(FwReviseDetailActivity a) {
			super();
			this.activity = a;
		}

		protected Object doInBackground(Object[] arg2) {
			return this.doInBackground(((String[]) arg2));
		}

		protected List doInBackground(String[] params) {
			return activity.getHackPackageDetailList();
		}

		protected void onPostExecute(Object arg1) {
			this.onPostExecute(((List) arg1));
		}

		protected void onPostExecute(List arg4) {
			activity.adapter = new NewsAdapter(activity);
			activity.reviseStrategys.setAdapter(activity.adapter);
			activity.adapter.setData(arg4);
		}

		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	static class NewsAdapter extends BaseAdapter {
		FwReviseDetailActivity activity;

		public final class ViewHolder {
			public TextView orderDetail;
			public TextView orderRsDetail;
			public LinearLayout reviseStrategy;
			public TextView status;

			public ViewHolder(NewsAdapter a) {
				super();
			}
		}

		private List<HackPackageDetail> data;

		public NewsAdapter(FwReviseDetailActivity activity) {
			this.activity = activity;
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
			Object v0_1 = null;
			if (convertView == null) {
				ViewHolder v0 = new ViewHolder(this);
				convertView = LayoutInflater.from(activity).inflate(R.layout.item_topstrategydetaillayout, null);
				v0.orderDetail = (TextView) convertView.findViewById(R.id.order_detail);
				v0.orderRsDetail = (TextView) convertView.findViewById(R.id.order_rs_detail);
				v0.status = (TextView) convertView.findViewById(R.id.tv_stutas);
				v0.reviseStrategy = (LinearLayout) convertView.findViewById(R.id.ll_strategy);
				convertView.setTag(v0);
			} else {
				v0_1 = convertView.getTag();
			}

			if (activity.isStatus) {
				((ViewHolder) v0_1).status.setText("已修�?");
			}

			((ViewHolder) v0_1).orderDetail.setText(this.data.get(position).getHackTarget());
			((ViewHolder) v0_1).orderRsDetail.setText(this.data.get(position).getHackCount() + "人修�?");
			((ViewHolder) v0_1).reviseStrategy.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					int v7 = data.get(position).getHackTarget().contains(".") ? 1 : 0;
					if (activity.mNativeCtrl.modifyData(Long.parseLong(data.get(position).getAppBase()),
							Long.parseLong(data.get(position).getAppOffset()), data.get(position).getHackTarget(), v7) == 0) {
						Toast.makeText(activity, "数据修改成功", 0).show();
						activity.isStatus = true;
						notifyDataSetChanged();
						activity.startActivity(new Intent(activity, ReviseResultActivity.class));
					} else {
						Toast.makeText(activity, "条数据修改失�?", 0).show();
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
	private LinearLayout bar;
	private TextView contentStatus;
	private ProcessInfo datapi;
	private HackPackageDetail hackPackageDetail;
	private boolean isOpenMenu;
	private boolean isStatus;
	private NativeCtrl mNativeCtrl;
	private LinearLayout menu;
	private ImageView naMenu;
	private ListView reviseStrategys;
	private TextView tContent;
	private TextView titleName;
	private EditText valueShare;

	public FwReviseDetailActivity() {
		super();
		this.isOpenMenu = true;
		this.isStatus = false;
	}

	public List getHackPackageDetailList() {
		Object v4_1 = null;
		this.hackPackageDetail = (HackPackageDetail) this.getIntent().getExtras().getSerializable("HackPackageDetail");
		if (this.hackPackageDetail != null) {
			this.tContent.setText(this.hackPackageDetail.getHackCount() + "人修改了" + this.hackPackageDetail.getHackMeaning());
		}

		List v4 = null;
		Gson v3 = new Gson();
		ResponseEntity v2 = null;
		try {
			v2 = DataCollectionBiz.getHackPackageDetailListResult(((Context) this), this.hackPackageDetail.getHackPackageId(),
					this.hackPackageDetail.getAppBase(), this.hackPackageDetail.getAppMoudle(),
					this.hackPackageDetail.getAppOffset());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (v2.getStatus() == 0) {
			Log.e("typetoken", "getHackPackageDetailList" + v2.getResult());
			v4_1 = v3.fromJson(v2.getResult(), new TypeToken() {
			}.getType());
		}

		return ((List) v4_1);
	}

	private void initBar() {
		this.findViewById(R.id.ll_back).setOnClickListener(((View.OnClickListener) this));
		this.titleName = (TextView) this.findViewById(R.id.tv_title_name);
		this.titleName.setText("修改详情");
		this.menu = (LinearLayout) this.findViewById(R.id.ll_pp_menu);
		this.naMenu = (ImageView) this.findViewById(R.id.im_na_menu);
		this.findViewById(R.id.ll_back).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_contain_menu).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_speed).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_return).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_exit).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_record).setOnClickListener(((View.OnClickListener) this));
	}

	private void initView() {
		this.reviseStrategys = (ListView) this.findViewById(R.id.lv_revise_strategys);
		this.tContent = (TextView) this.findViewById(R.id.tv_content);
		this.contentStatus = (TextView) this.findViewById(R.id.tv_content_status);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_back: {
			this.finish();
			break;
		}
		case R.id.ll_contain_menu: {
			new InitPopuWindown().initPopu(this, this, v, this.datapi, this.mNativeCtrl);
			break;
		}
		case R.id.ll_revise_strategy: {
			int v7 = this.hackPackageDetail.getHackTarget().contains(".") ? 1 : 0;
			if (this.mNativeCtrl.modifyData(Long.parseLong(this.hackPackageDetail.getAppBase()),
					Long.parseLong(this.hackPackageDetail.getAppOffset()), this.hackPackageDetail.getHackTarget(), v7) == 0) {
				Toast.makeText(((Context) this), "数据修改成功", 0).show();
				this.contentStatus.setText("我已修改");
				this.startActivity(new Intent(((Context) this), ReviseResultActivity.class));
				return;
			}

			Toast.makeText(((Context) this), "数据修改失败", 0).show();
			break;
		}
		}
	}

	@Nullable
	public void onContainerBound(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
		inflater.inflate(R.layout.fw_activity_revise_detail, parent);
		ActivityManager.getInstance().addActivity(((Activity) this));
		this.initView();
		this.initBar();
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
		if (nativeCtrl.isConnect()) {
			this.mNativeCtrl = nativeCtrl;
			this.datapi = NativeCtrlBiz.getCurSelectPid(this.mNativeCtrl);
			new DataStrategyDetailTask(this).execute(new String[0]);
		}
	}

}
