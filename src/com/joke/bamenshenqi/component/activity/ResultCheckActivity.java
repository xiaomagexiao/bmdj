package com.joke.bamenshenqi.component.activity;

import com.xiaomage.edit.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.module.ui.activity.FwRecommendActivity;
import com.joke.bamenshenqi.util.ActivityManager;

public class ResultCheckActivity extends BaseActivity implements View.OnClickListener {
    private NativeCtrl mNativeCtrl;
    private LinearLayout suBack;

    public ResultCheckActivity() {
        super();
    }

    private void initView() {
        this.findViewById(R.id.ll_close).setOnClickListener(((View.OnClickListener)this));
        this.suBack = (LinearLayout)this.findViewById(R.id.ll_su_back);
        this.findViewById(R.id.ll_su_again).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.ll_su_success).setOnClickListener(((View.OnClickListener)this));
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ll_close: {
                this.show();
                break;
            }
            case R.id.ll_su_again: {
                NativeCtrlBiz.cancelSearch(this.mNativeCtrl);
                this.startActivity(new Intent(((Context)this), FwRecommendActivity.class));
                break;
            }
            case R.id.ll_su_success: {
                this.startActivity(new Intent(((Context)this), ResultShareActivity.class));
                break;
            }
        }
    }

    @Nullable public void onContainerBound(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fw_activity_revise_check, parent);
        ActivityManager.getInstance().addActivity(((Activity)this));
        this.initView();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean v0;
        if(keyCode == 4) {
            this.show();
            v0 = true;
        }
        else {
            v0 = super.onKeyDown(keyCode, event);
        }

        return v0;
    }

    public void serviceConnected(NativeCtrl nativeCtrl) {
        super.serviceConnected(nativeCtrl);
        this.mNativeCtrl = nativeCtrl;
        this.show();
    }
}

