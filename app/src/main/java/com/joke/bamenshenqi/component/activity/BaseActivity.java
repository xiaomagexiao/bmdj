package com.joke.bamenshenqi.component.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.joke.bamenshenqi.component.service.BmIconViewService;
import com.joke.bamenshenqi.component.service.BmIconViewService.MyBinder;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.util.AppUtil;
import com.joke.bamenshenqi.util.DpPxUtil;
import com.xiaomage.edit.R;

public class BaseActivity extends Activity {
    private FrameLayout activityContainer;
    private ImageView backgroundBorder;
    protected BmIconViewService bmIconViewService;
    private ServiceConnection connection;
    private DisplayMetrics displayMetrics;
    private LayoutInflater inflater;
    private boolean isFirst;
    private NativeCtrl nativeCtrl;

    public BaseActivity() {
        super();
        this.isFirst = true;
        this.connection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder service) {
                BaseActivity.this.bmIconViewService = ((MyBinder)service).getService();
                BaseActivity.this.nativeCtrl = BaseActivity.this.bmIconViewService.getNativeCtrl();
                if(BaseActivity.this.getRet() != 0) {
                    BaseActivity.this.noRootDialog(BaseActivity.this.bmIconViewService.ret());
                }
                else {
                    BaseActivity.this.serviceConnected(BaseActivity.this.nativeCtrl);
                }
            }

            public void onServiceDisconnected(ComponentName name) {
            }
        };
    }

    public void closeSelf() {
        if(this.bmIconViewService != null) {
            this.bmIconViewService.stopSelf();
        }
    }

    public int getRet() {
        return this.bmIconViewService.getRet();
    }

    public void hide(boolean onlyHide) {
        if(this.bmIconViewService != null) {
            this.bmIconViewService.hideView(onlyHide);
        }
    }

    private void noRootDialog(int ret) {
        String v1 = null;
        if(ret == -99) {
            v1 = "Root权限不足";
        }
        else if(ret != 0) {
            v1 = "修改器发生异�?";
          
        }
        AlertDialog.Builder v0 = new AlertDialog.Builder(((Context)this));
        v0.setMessage(((CharSequence)v1));
        v0.setCancelable(false);
        v0.setTitle("提示").setPositiveButton("回首�?", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                AppUtil.runApp(BaseActivity.this, "com.zhangkongapp.joke.bamenshenqi", "com.joke.bamenshenqi.components.activity.MainActivity");
                BaseActivity.this.finish();
            }
        });
        v0.create().show();
    }

    public void onContainerBound(LayoutInflater inflater, ViewGroup parent, @Nullable Bundle savedInstanceState) {
    }

    protected void onCreate(Bundle savedInstanceState) {
        float v6 = 20f;
        super.onCreate(savedInstanceState);
        this.bindService(new Intent(((Context)this), BmIconViewService.class), this.connection, 1);
        this.displayMetrics = DpPxUtil.initWidthAndHeight(((Context)this));
        int v3 = this.displayMetrics.widthPixels - DpPxUtil.dip2px(((Context)this), v6);
        int v1 = this.getResources().getConfiguration().orientation;
        if(v1 == 1) {
            v3 = this.displayMetrics.widthPixels - DpPxUtil.dip2px(((Context)this), v6);
        }
        else if(v1 == 2) {
            v3 = this.displayMetrics.heightPixels - DpPxUtil.dip2px(((Context)this), 40f);
        }

        this.setContentView(R.layout.fw_container_activity_framelayout);
        this.inflater = LayoutInflater.from(((Context)this));
        this.activityContainer = (FrameLayout)this.findViewById(R.id.id_container_activity);
        if(this.isFirst) {
            this.isFirst = false;
            this.activityContainer.setBackgroundColor(this.getResources().getColor(R.color.bg_view));
        }

        FrameLayout.LayoutParams v2 = new FrameLayout.LayoutParams(v3, v3);
        v2.gravity = 17;
        this.activityContainer.setLayoutParams(((ViewGroup.LayoutParams)v2));
        this.backgroundBorder = (ImageView)this.findViewById(R.id.id_container_background);
        this.backgroundBorder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BaseActivity.this.show();
            }
        });
        this.onContainerBound(this.inflater, this.activityContainer, savedInstanceState);
    }

    protected void onDestroy() {
        this.unbindService(this.connection);
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.hide(false);
    }

    public void serviceConnected(NativeCtrl nativeCtrl) {
    }

    public void show() {
        if(this.bmIconViewService != null) {
            this.bmIconViewService.showView();
            this.moveTaskToBack(true);
        }
    }
}

