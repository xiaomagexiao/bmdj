package com.joke.bamenshenqi.component.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.component.entity.ReportInfo;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.CollectionData;
import com.joke.bamenshenqi.model.HackPackageDetail;
import com.joke.bamenshenqi.module.ui.activity.FwRecommendActivity;
import com.joke.bamenshenqi.module.ui.view.InputMethodRelativeLayout;
import com.joke.bamenshenqi.module.ui.view.InputMethodRelativeLayout.OnSizeChangedListenner;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.CollectData;
import com.joke.bamenshenqi.util.HistoryRecordUtils;
import com.xiaomage.edit.R;

public class ResultShareActivity extends BaseActivity implements View.OnClickListener, OnSizeChangedListenner {
	Handler handler;
	private InputMethodRelativeLayout layout;
	private NativeCtrl mNativeCtrl;
	private String str;
	private LinearLayout suShare;
	private TextWatcher textWatcher;
	private EditText valueShare;

	public ResultShareActivity() {
		super();
		this.textWatcher = new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ResultShareActivity.this.str = ResultShareActivity.this.valueShare.getText().toString();
				if (ResultShareActivity.this.str.length() == 10) {
					Toast.makeText(ResultShareActivity.this, "您输入字数过�?..", 1);
				}
			}
		};
		this.handler = new Handler();
	}

	static NativeCtrl access$000(ResultShareActivity x0) {
		return x0.mNativeCtrl;
	}

	static String access$100(ResultShareActivity x0) {
		return x0.str;
	}

	static String access$102(ResultShareActivity x0, String x1) {
		x0.str = x1;
		return x1;
	}

	static EditText access$200(ResultShareActivity x0) {
		return x0.valueShare;
	}

	private void initView() {
		this.findViewById(R.id.ll_close).setOnClickListener(((View.OnClickListener) this));
		this.suShare = (LinearLayout) this.findViewById(R.id.li_su_share);
		this.valueShare = (EditText) this.findViewById(R.id.et_revise_success);
		this.suShare.setOnClickListener(((View.OnClickListener) this));
		this.valueShare.addTextChangedListener(this.textWatcher);
	}

	public void onClick(View v) {
		CollectionData v0_1 = null;
		String v8 = this.valueShare.getText().toString();
		switch (v.getId()) {
		case R.id.ll_close: {
			this.startActivity(new Intent(((Context) this), FwRecommendActivity.class));
			break;
		}
		case R.id.li_su_share: {
			Toast.makeText(((Context) this), "共享成功，跳转至修改主页...", 1).show();
			if (v8.isEmpty()) {
				v8 = "修改";
			}

			String v6 = ActivityManager.getInstance().getPackageName();
			if (v6 != null) {
				List<ReportInfo> v5 = HistoryRecordUtils.getAllHistoryData(((Context) this), v6);
				if (v5.size() > 0) {
					ArrayList v3 = new ArrayList();
					int v4;
					for (v4 = 0; v4 < v5.size(); ++v4) {
						HackPackageDetail v1 = new HackPackageDetail();
						v1.setAppBase(v5.get(v4).getBase() + "");
						v1.setAppMoudle(v5.get(v4).getModule());
						v1.setAppOffset(v5.get(v4).getOffset() + "");
						v1.setHackMeaning(v8);
						v1.setTargetType(1);
						v1.setHackTarget(v5.get(v4).getValue());
						((List) v3).add(v1);
					}

					List v7 = NativeCtrlBiz.getUserAppUsage(this.mNativeCtrl, ((Context) this));
					List v2 = CollectData.getHackPackages(((Context) this), ((List) v3), v6);
					Object v0 = HistoryRecordUtils.getHistoryData(((Context) this), "HISTORY_COLLECTION");
					if (v0 == null) {
						v0_1 = new CollectionData();
						v0_1.setSystemInfo(CollectData.getSystemInfo(((Context) this)));
						if (v2 != null) {
							v0_1.setHackPackages(v2);
						}

						if (v7 != null) {
							v0_1.setUserAppUsages(v7);
						}
					} else {
						if (v7 != null) {
							((CollectionData) v0).getUserAppUsages().addAll(((Collection) v7));
						}

						if (v2 != null) {
							((CollectionData) v0).getHackPackages().addAll(((Collection) v2));
						}
					}

					HistoryRecordUtils.saveAllHistoryList(((Context) this), v0_1, "HISTORY_COLLECTION");
				}
			}

			this.handler.postDelayed(new Runnable() {
				public void run() {
					NativeCtrlBiz.cancelSearch(ResultShareActivity.this.mNativeCtrl);
					ResultShareActivity.this.startActivity(new Intent(ResultShareActivity.this, FwRecommendActivity.class));
				}
			}, 500);
			break;
		}
		}
	}

	@Nullable
	public void onContainerBound(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
		inflater.inflate(R.layout.activity_revise_share, parent);
		ActivityManager.getInstance().addActivity(((Activity) this));
		this.initView();
		this.layout = (InputMethodRelativeLayout) this.findViewById(R.id.loginpage);
		this.layout.setOnSizeChangedListenner(((OnSizeChangedListenner) this));
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case 3: {
			this.show();
			break;
		}
		case 4: {
			this.show();
			break;
		}
		}

		return super.onKeyDown(keyCode, event);
	}

	public void onSizeChange(boolean paramBoolean, int w, int h) {
		if (paramBoolean) {
			this.layout.setPadding(0, 0, 0, 0);
		} else {
			this.layout.setPadding(0, 0, 0, 0);
		}
	}

	public void serviceConnected(NativeCtrl nativeCtrl) {
		super.serviceConnected(nativeCtrl);
		this.mNativeCtrl = nativeCtrl;
	}
}
