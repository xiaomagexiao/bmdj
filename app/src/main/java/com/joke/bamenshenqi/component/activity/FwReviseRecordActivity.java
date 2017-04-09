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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.component.entity.ReportInfo;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.InitPopuWindown;
import com.xiaomage.edit.R;

public class FwReviseRecordActivity extends BaseActivity implements View.OnClickListener {
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
						FwReviseRecordActivity.this.recordList.add(data.get(this.position));
					} else {
						FwReviseRecordActivity.this.recordList.remove(this.position);
					}

					FwReviseRecordActivity.this.adapter.setData(data);
					break;
				}
				case R.id.isCheakBox: {
					if (!data.get(this.position).islock) {
						FwReviseRecordActivity.this.retLock = FwReviseRecordActivity.this.mNativeCtrl.lockData(
								data.get(this.position).getModule(), data.get(this.position).getBase()
										+ data.get(this.position).getOffset(), data.get(this.position).getValue());
						if (FwReviseRecordActivity.this.retLock == 0) {
							v0 = data.get(this.position);
							boolean v1 = !data.get(this.position).islock ? true : false;
							((ReportInfo) v0).islock = v1;
							Toast.makeText(FwReviseRecordActivity.this, "锁定成功", 0).show();
							FwReviseRecordActivity.this.adapter.setData(data);
							return;
						}

						Toast.makeText(FwReviseRecordActivity.this, "锁定失败，请重新操作", 0).show();
						return;
					}

					FwReviseRecordActivity.this.retUnlock = FwReviseRecordActivity.this.mNativeCtrl.unlockData(
							data.get(this.position).getModule(), data.get(this.position).getBase()
									+ data.get(this.position).getOffset());
					if (FwReviseRecordActivity.this.retUnlock == 0) {
						Toast.makeText(FwReviseRecordActivity.this, "解锁成功", 0).show();
						v0 = data.get(this.position);
						if (data.get(this.position).islock) {
							v2 = false;
						}

						((ReportInfo) v0).islock = v2;
						FwReviseRecordActivity.this.adapter.setData(data);
						return;
					}

					Toast.makeText(FwReviseRecordActivity.this, "解锁失败，请重新操作", 0).show();
					break;
				}
				}
			}
		}

		public final class ViewHolder {
			public CheckBox cBox;
			public CheckBox dBox;
			public TextView tvName;

			public ViewHolder(NewsAdapter a) {
				super();
			}
		}

		private List<ReportInfo> data;

		NewsAdapter(FwReviseRecordActivity a) {
			super();
		}

		public int getCount() {
			int v0 = this.data == null ? 0 : this.data.size();
			return v0;
		}

		public Object getItem(int location) {
			return this.data.get(location);
		}

		public long getItemId(int position) {
			return ((long) position);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder v1 = null;
			if (convertView == null) {
				v1 = new ViewHolder(this);
				convertView = LayoutInflater.from(FwReviseRecordActivity.this).inflate(R.layout.item_revise, null);
				v1.tvName = (TextView) convertView.findViewById(R.id.dailyName);
				v1.cBox = (CheckBox) convertView.findViewById(R.id.isCheakBox);
				v1.dBox = (CheckBox) convertView.findViewById(R.id.dailyPic);
				convertView.setTag(v1);
			} else {
				v1 = (ViewHolder) convertView.getTag();
			}

			v1.cBox.setChecked(this.data.get(position).islock);
			v1.dBox.setChecked(this.data.get(position).is);
			v1.tvName.setText(Integer.toHexString(new Long(this.data.get(position).getBase()
					+ this.data.get(position).getOffset()).intValue())
					+ " 的�?�?" + this.data.get(position).getValue());
			MyClickListener v0 = new MyClickListener(this, position);
			v1.dBox.setOnClickListener(((View.OnClickListener) v0));
			v1.cBox.setOnClickListener(((View.OnClickListener) v0));
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
	private ProcessInfo datapi;
	private int flag;
	private ImageView imageChoice;
	private boolean isChoiceBox;
	private List<ReportInfo> itemList;
	private NativeCtrl mNativeCtrl;
	private LinearLayout menu;
	private ImageView naMenu;
	private List recordList;
	private int retLock;
	private int retUnlock;
	private ListView revise;
	private TextView titleName;

	public FwReviseRecordActivity() {
		super();
		this.isChoiceBox = true;
		this.recordList = new ArrayList();
	}

	private void deleteDialog(final int position) {
		final AlertDialog.Builder v0 = new AlertDialog.Builder(((Context) this));
		v0.setMessage("确认删除吗？");
		v0.setTitle("提示").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
				if (FwReviseRecordActivity.this.itemList != null) {
					if (FwReviseRecordActivity.this.mNativeCtrl.deleteItem(FwReviseRecordActivity.this.itemList.get(position)
							.getModule(), FwReviseRecordActivity.this.itemList.get(position).getOffset()) == 0) {
						FwReviseRecordActivity.this.itemList.remove(position);
						Toast.makeText(FwReviseRecordActivity.this, "删除成功", 0).show();
					} else {
						Toast.makeText(FwReviseRecordActivity.this, "删除失败，请重新尝试删除", 0).show();
					}

					FwReviseRecordActivity.this.adapter.notifyDataSetChanged();
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
				if (FwReviseRecordActivity.this.itemList != null) {
					FwReviseRecordActivity.this.itemList = v2;
					FwReviseRecordActivity.this.recordList = v2;
					FwReviseRecordActivity.this.adapter.setData(FwReviseRecordActivity.this.itemList);
					if (NativeCtrlBiz.getStutas(FwReviseRecordActivity.this.mNativeCtrl) == 0) {
						Toast.makeText(FwReviseRecordActivity.this, "清空完毕�?", 0).show();
					}

					FwReviseRecordActivity.this.choice.setText("全�?");
					FwReviseRecordActivity.this.imageChoice.setImageDrawable(FwReviseRecordActivity.this.getResources()
							.getDrawable(R.drawable.un_square_radio));
				} else {
					Toast.makeText(FwReviseRecordActivity.this, "暂无数据，无�?��空�?", 0).show();
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

	private void getValue() {
		this.adapter = new NewsAdapter(this);
		this.itemList = (List<ReportInfo>) this.getIntent().getExtras().getParcelableArrayList("list").get(0);
		if (this.itemList != null) {
			this.adapter.setData(this.itemList);
		}

		this.revise.setAdapter(this.adapter);
		this.revise.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView arg2, View view, int position, long id) {
				FwReviseRecordActivity.this.deleteDialog(position);
				return false;
			}
		});
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
	}

	private void initView() {
		this.bar = (LinearLayout) this.findViewById(R.id.ll_all);
		this.findViewById(R.id.ll_all_choice).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_no_choice).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_revise_choice).setOnClickListener(((View.OnClickListener) this));
		this.imageChoice = (ImageView) this.findViewById(R.id.iv_choice);
		this.choice = (TextView) this.findViewById(R.id.choice);
		this.findViewById(R.id.ll_speed).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_return).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_exit).setOnClickListener(((View.OnClickListener) this));
		this.findViewById(R.id.ll_record).setOnClickListener(((View.OnClickListener) this));
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

			Toast.makeText(((Context) this), "没有可以选择的数据，请回到修改界面，尝试重新修改数据�?", 1).show();
			break;
		}
		case R.id.ll_no_choice: {
			this.emptyDialog();
			break;
		}
		case R.id.ll_revise_choice: {
			if (this.recordList != null && this.recordList.size() > 0) {
				Intent v8 = new Intent();
				Bundle v6 = new Bundle();
				v6.putSerializable("list", (Serializable) this.recordList);
				v8.putExtras(v6);
				this.setResult(-1, v8);
				this.finish();
				return;
			}

			Toast.makeText(((Context) this), "请�?择需要修改的数据", 0).show();
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
		this.getValue();
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
		}
	}
}
