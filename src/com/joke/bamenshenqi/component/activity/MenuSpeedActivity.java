package com.joke.bamenshenqi.component.activity;

import com.xiaomage.edit.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.InitPopuWindown;

public class MenuSpeedActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout bar;
    private ProcessInfo datapi;
    private TextView etValue;
    private int index;
    private boolean isKeep;
    private boolean isRestore;
    private boolean isStop;
    private TextView keepValue;
    private NativeCtrl mNativeCtrl;
    private LinearLayout menu;
    private ImageView naMenu;
    private ListView revise;
    private TextView reviseValue;
    private TextView titleName;
    private EditText valueShare;

    public MenuSpeedActivity() {
        super();
        this.isKeep = false;
        this.isStop = false;
        this.index = 1;
        this.isRestore = false;
    }

    private void initBar() {
        this.titleName = (TextView)this.findViewById(R.id.tv_title_name);
        this.titleName.setText("加�?");
        this.menu = (LinearLayout)this.findViewById(R.id.ll_pp_menu);
        this.naMenu = (ImageView)this.findViewById(R.id.im_na_menu);
        this.findViewById(R.id.ll_back).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.ll_contain_menu).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.ll_speed).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.ll_return).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.ll_exit).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.ll_record).setOnClickListener(((View.OnClickListener)this));
    }

    private void initView() {
        this.etValue = (TextView)this.findViewById(R.id.et_value);
        this.findViewById(R.id.speed_stop).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.speed_back).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.speed_start).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.speed_restore).setOnClickListener(((View.OnClickListener)this));
    }

    public void onClick(View v) {
        int v4 = 50;
        int v1 = -20;
        switch(v.getId()) {
            case R.id.ll_back: {
                this.finish();
                break;
            }
            case R.id.ll_contain_menu: {
                new InitPopuWindown().initPopu(this, this, v, this.datapi, this.mNativeCtrl);
                break;
            }
            case R.id.speed_back: {
                if(this.isKeep) {
                    return;
                }

                if(this.isRestore) {
                    this.index = 1;
                    this.isRestore = false;
                }

                if(this.index > v1 && this.index <= v4) {
                    --this.index;
                    this.etValue.setText(this.index + "");
                    this.mNativeCtrl.setSpeed(this.index);
                    return;
                }

                if(this.index != v1) {
                    return;
                }

                this.etValue.setText("-50");
                this.mNativeCtrl.setSpeed(-50);
                break;
            }
            case R.id.speed_start: {
                if(this.isKeep) {
                    return;
                }

                if(this.isRestore) {
                    this.index = 1;
                    this.isRestore = false;
                }

                if(this.index <= 20) {
                    ++this.index;
                    this.etValue.setText(this.index + "");
                    this.mNativeCtrl.setSpeed(this.index);
                    return;
                }

                if(this.index != 21) {
                    return;
                }

                this.etValue.setText("50");
                this.mNativeCtrl.setSpeed(v4);
                break;
            }
            case R.id.speed_restore: {
                if(this.isKeep) {
                    return;
                }

                this.etValue.setText("1");
                this.isRestore = true;
                this.mNativeCtrl.setSpeed(1);
                break;
            }
            case R.id.speed_stop: {
                if(!this.isStop) {
                    this.etValue.setText("0");
                    this.mNativeCtrl.setSpeed(0);
                    this.isStop = true;
                    this.isKeep = true;
                    return;
                }

                this.etValue.setText(this.index + "");
                this.mNativeCtrl.setSpeed(this.index);
                this.isStop = false;
                this.isKeep = false;
                break;
            }
        }
    }

    @Nullable public void onContainerBound(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.menu_activity_speed, parent);
        ActivityManager.getInstance().addActivity(((Activity)this));
        this.initBar();
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
        this.datapi = NativeCtrlBiz.getCurSelectPid(this.mNativeCtrl);
    }
}

