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
import com.joke.bamenshenqi.util.ActivityManager;

public class ReviseResultActivity extends BaseActivity implements View.OnClickListener {
    public ReviseResultActivity() {
        super();
    }

    private void initView() {
        this.findViewById(R.id.ll_close).setOnClickListener(((View.OnClickListener)this));
        this.findViewById(R.id.li_su_commit).setOnClickListener(((View.OnClickListener)this));
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ll_close: {
                this.startActivity(new Intent(((Context)this), ResultCheckActivity.class));
                break;
            }
            case R.id.li_su_commit: {
                this.startActivity(new Intent(((Context)this), ResultCheckActivity.class));
                break;
            }
        }
    }

    @Nullable public void onContainerBound(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fw_activity_revise_result, parent);
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
}

