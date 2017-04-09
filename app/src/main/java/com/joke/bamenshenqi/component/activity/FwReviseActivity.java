package com.joke.bamenshenqi.component.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.component.entity.ReportInfo;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.module.ui.activity.FwRecommendActivity;
import com.joke.bamenshenqi.module.ui.activity.FwSearchActivity;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.HistoryRecordUtils;
import com.joke.bamenshenqi.util.InitPopuWindown;
import com.xiaomage.edit.R;

public class FwReviseActivity extends BaseActivity implements View.OnClickListener {
	class NewsAdapter extends BaseAdapter {
		public class MyClickListener implements View.OnClickListener {
			int position;

			public MyClickListener(NewsAdapter a, int position) {
				super();
				this.position = position;
			}

			public void onClick(View v) {
				Object v0;
				boolean v2 = true;
				switch (v.getId()) {
				case R.id.dailyPic: {
					v0 = data.get(this.position);
					if (data.get(this.position).is) {
						v2 = false;
					}

					((ReportInfo) v0).is = v2;
					if (data.get(this.position).is) {
						recordList.add(data.get(this.position));
						return;
					}

					recordList.remove(this.position);
					break;
				}
				case R.id.isCheakBox: {
					if (!data.get(this.position).islock) {
						retLock = mNativeCtrl.lockData(data.get(this.position).getModule(), data.get(this.position).getBase()
								+ data.get(this.position).getOffset(), data.get(this.position).getValue());
						if (retLock == 0) {
							v0 = data.get(this.position);
							boolean v1 = !data.get(this.position).islock ? true : false;
							((ReportInfo) v0).islock = v1;
							Toast.makeText(FwReviseActivity.this, "锁定成功", 0).show();
							getLockData();
							adapter.setData(data);
							return;
						}

						Toast.makeText(FwReviseActivity.this, "锁定失败，请重新操作", 0).show();
						return;
					}

					retUnlock = mNativeCtrl.unlockData(data.get(this.position).getModule(), data.get(this.position).getBase()
							+ data.get(this.position).getOffset());
					if (retUnlock == 0) {
						Toast.makeText(FwReviseActivity.this, "解锁成功", 0).show();
						v0 = data.get(this.position);
						if (data.get(this.position).islock) {
							v2 = false;
						}

						((ReportInfo) v0).islock = v2;
						getLockData();
						adapter.setData(data);
						return;
					}

					Toast.makeText(FwReviseActivity.this, "解锁失败，请重新操作", 0).show();
					break;
				}
				}
			}
		}

		public final class ViewHolder {
			public CheckBox cBox;
			public CheckBox dBox;
			public RelativeLayout itemRl;
			public TextView tvName;

			public ViewHolder(NewsAdapter a) {
				super();
			}
		}

		private List<ReportInfo> data;

		NewsAdapter(FwReviseActivity a) {
			super();
		}

		public int getCount() {
			int v0 = this.data == null ? 0 : this.data.size();
			return v0;
		}

		public ReportInfo getItem(int location) {
			ReportInfo v0 = null;
			if (this.data == null) {
				v0 = null;
			} else {
				Object v0_1 = this.data.get(location);
			}

			return v0;
		}

		public long getItemId(int position) {
			return ((long) position);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			Object v4_1 = null;
			if (convertView == null) {
				ViewHolder v4 = new ViewHolder(this);
				convertView = LayoutInflater.from(FwReviseActivity.this).inflate(R.layout.item_revise, null);
				v4.itemRl = (RelativeLayout) convertView.findViewById(R.id.rl_item);
				v4.tvName = (TextView) convertView.findViewById(R.id.dailyName);
				v4.cBox = (CheckBox) convertView.findViewById(R.id.isCheakBox);
				v4.dBox = (CheckBox) convertView.findViewById(R.id.dailyPic);
				convertView.setTag(v4);
			} else {
				v4_1 = convertView.getTag();
			}

			if (FwReviseActivity.this.dataList == null || FwReviseActivity.this.dataList.size() <= 0) {
				((ViewHolder) v4_1).cBox.setChecked(this.data.get(position).islock);
			} else {
				int v3 = 0;
				long v6 = this.getItem(position).getBase() + this.getItem(position).getOffset();
				int v5 = 0;
				while (v5 < FwReviseActivity.this.dataList.size()) {
					if (v6 == FwReviseActivity.this.dataList.get(v5).getOffset()) {
						v3 = 1;
					} else {
						++v5;
						continue;
					}

					break;
				}

				if (v3 != 0) {
					this.getItem(position).islock = true;
					((ViewHolder) v4_1).cBox.setChecked(this.getItem(position).islock);
				}
			}

			((ViewHolder) v4_1).dBox.setChecked(this.data.get(position).is);
			((ViewHolder) v4_1).tvName.setText(Integer.toHexString(new Long(this.data.get(position).getBase()
					+ this.data.get(position).getOffset()).intValue())
					+ " 的值" + this.data.get(position).getValue());
			MyClickListener v2 = new MyClickListener(this, position);
			((ViewHolder) v4_1).dBox.setOnClickListener(((View.OnClickListener) v2));
			((ViewHolder) v4_1).cBox.setOnClickListener(((View.OnClickListener) v2));
			return convertView;
		}

		public void setData(List arg1) {
			this.data = arg1;
			this.notifyDataSetChanged();
		}
	}

	private NewsAdapter adapter;
	private LinearLayout bar;
	private TextView choice;
	private List<ReportInfo> dataList;
	private ProcessInfo datapi;
	private ImageView imageChoice;
	private boolean isChoiceBox;
	private List<ReportInfo> itemList;
	private NativeCtrl mNativeCtrl;
	private LinearLayout menu;
	private ImageView naMenu;
	private String packageName;
	private List recordList;
	private int retLock;
	private int retUnlock;
	private ListView revise;
	private LinearLayout showEmpty;
	private TextView titleName;
	private EditText valueShare;

	public FwReviseActivity() {
		super();
		this.isChoiceBox = true;
		this.recordList = new ArrayList();
	}

	private void deleteDialog(final int position) {
		final AlertDialog.Builder v0 = new AlertDialog.Builder(((Context) this));
		v0.setMessage("确认删除吗？");
		v0.setTitle("提示").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
				if (FwReviseActivity.this.itemList != null) {
					if (FwReviseActivity.this.mNativeCtrl.deleteItem(FwReviseActivity.this.itemList.get(position).getModule(),
							FwReviseActivity.this.itemList.get(position).getOffset()) == 0) {
						FwReviseActivity.this.itemList.remove(position);
						HistoryRecordUtils.saveAllHistoryList(FwReviseActivity.this, FwReviseActivity.this.itemList,
								FwReviseActivity.this.packageName);
						Toast.makeText(FwReviseActivity.this, "删除成功", 0).show();
					} else {
						Toast.makeText(FwReviseActivity.this, "删除失败，请重新尝试删除", 0).show();
					}

					FwReviseActivity.this.adapter.notifyDataSetChanged();
				}
			}
		});
		v0.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				v0.create().dismiss();
			}
		});
		v0.create().show();
	}

	private void emptyDialog() {
		final AlertDialog.Builder v0 = new AlertDialog.Builder(((Context) this));
		v0.setMessage("确认清空吗？");
		v0.setTitle("提示").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
				List v2 = null;
				if (FwReviseActivity.this.itemList != null) {
					FwReviseActivity.this.itemList = v2;
					FwReviseActivity.this.recordList = v2;
					FwReviseActivity.this.adapter.setData(FwReviseActivity.this.itemList);
					if (NativeCtrlBiz.getStutas(FwReviseActivity.this.mNativeCtrl) == 0) {
						Toast.makeText(FwReviseActivity.this, "清空完毕�?", 0).show();
					}

					HistoryRecordUtils.saveAllHistoryList(FwReviseActivity.this, FwReviseActivity.this.recordList,
							FwReviseActivity.this.packageName);
					FwReviseActivity.this.choice.setText("全�?");
					FwReviseActivity.this.imageChoice.setImageDrawable(FwReviseActivity.this.getResources().getDrawable(
							R.drawable.un_square_radio));
				} else {
					Toast.makeText(FwReviseActivity.this, "暂无数据，无�?��空�?", 0).show();
				}
			}
		});
		v0.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				v0.create().dismiss();
			}
		});
		v0.create().show();
	}

	private void getLockData() {
		this.dataList = NativeCtrlBiz.getDataList(this.mNativeCtrl);
	}

	private void initBar() {
		this.findViewById(R.id.ll_back).setOnClickListener(((View.OnClickListener) this));
		this.titleName = (TextView) this.findViewById(R.id.tv_title_name);
		this.titleName.setText("修改记录");
		this.menu = (LinearLayout) this.findViewById(R.id.ll_pp_menu);
		this.naMenu = (ImageView) this.findViewById(R.id.im_na_menu);
		this.findViewById(R.id.ll_back).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_contain_menu).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_speed).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_return).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_exit).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_record).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_all_revise).setOnClickListener(((View.OnClickListener) this));
	}

	private void initView() {
		this.bar = (LinearLayout) this.findViewById(R.id.ll_all);
		this.findViewById(R.id.ll_all_choice).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_no_choice).setOnClickListener(((View.OnClickListener) this));
		this.imageChoice = (ImageView) this.findViewById(R.id.iv_choice);
		this.choice = (TextView) this.findViewById(R.id.choice);
		this.findViewById(R.id.ll_speed).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_return).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_exit).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_record).setOnClickListener(((View.OnClickListener) this));
		this.showEmpty = (LinearLayout) this.findViewById(R.id.ll_recommend_empty);
		this.revise = (ListView) this.findViewById(R.id.lv_revise);
	}

	public void onClick(View v) {
		Iterator<ReportInfo> v0;
		switch (v.getId()) {
		case R.id.ll_back: {
			this.finish();
			break;
		}
		case R.id.ll_contain_menu: {
			new InitPopuWindown().initPopu(this, this, v, this.datapi, this.mNativeCtrl);
			break;
		}
		case R.id.ll_all_choice: {
			if (this.itemList != null) {
				if (this.isChoiceBox) {
					this.choice.setText("反�?");
					this.recordList = this.itemList;
					this.imageChoice.setImageDrawable(this.getResources().getDrawable(R.drawable.square_radio));
					v0 = this.itemList.iterator();
					while (v0.hasNext()) {
						v0.next().is = true;
					}

					this.adapter.setData(this.itemList);
					this.isChoiceBox = false;
					return;
				}

				v0 = this.itemList.iterator();
				while (v0.hasNext()) {
					v0.next().is = false;
				}

				this.adapter.setData(this.itemList);
				this.choice.setText("全�?");
				this.recordList = null;
				this.imageChoice.setImageDrawable(this.getResources().getDrawable(R.drawable.un_square_radio));
				this.isChoiceBox = true;
				return;
			}

			Toast.makeText(((Context) this), "没有可以选择的数据，请回到修改界面，尝试重新修改数据�?", 0).show();
			break;
		}
		case R.id.ll_no_choice: {
			this.emptyDialog();
			break;
		}
		case R.id.ll_revise_choice: {
			if (this.recordList != null && this.recordList.size() > 0) {
				Intent v7 = new Intent();
				Bundle v6 = new Bundle();
				ArrayList v9 = new ArrayList();
				v9.add(this.recordList);
				v6.putSerializable("list", ((Serializable) v9));
				v7.setClass(((Context) this), FwSearchActivity.class);
				v7.putExtras(v6);
				v7.putExtra("test", "test");
				this.startActivity(v7);
				this.finish();
				return;
			}

			Toast.makeText(((Context) this), "请�?择需要修改的数据", 0).show();
			break;
		}
		case R.id.ll_all_revise: {
			this.startActivity(new Intent(((Context) this), FwRecommendActivity.class));
			break;
		}
		}
	}

	@Nullable
	public void onContainerBound(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
		inflater.inflate(R.layout.fw_activity_revise_record, parent);
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
			this.mNativeCtrl.cancelSearch();
			this.datapi = NativeCtrlBiz.getCurSelectPid(this.mNativeCtrl);
			this.getLockData();
			if (this.datapi != null) {
				this.packageName = this.datapi.getName();
				if (this.packageName != null) {
					this.itemList = HistoryRecordUtils.getAllHistoryData(((Context) this), this.packageName);
					if (this.itemList != null) {
						this.findViewById(R.id.ll_revise_choice).setOnClickListener(((View.OnClickListener) this));
						int v0;
						for (v0 = 0; v0 < this.itemList.size(); ++v0) {
							this.itemList.get(v0).is = false;
						}

						this.adapter = new NewsAdapter(this);
						this.revise.setAdapter(this.adapter);
						this.adapter.setData(this.itemList);
						this.revise.setAdapter(this.adapter);
						this.revise.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
							public boolean onItemLongClick(AdapterView arg2, View view, int position, long id) {
								FwReviseActivity.this.deleteDialog(position);
								return true;
							}
						});
					} else {
						this.revise.setEmptyView(this.showEmpty);
					}
				}
			}
		}
	}

}
