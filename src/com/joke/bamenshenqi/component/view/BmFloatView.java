package com.joke.bamenshenqi.component.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.joke.bamenshenqi.model.Position;
import com.joke.bamenshenqi.util.SharePreferenceUtils;
import com.xiaomage.edit.R;

public class BmFloatView extends Button implements View.OnClickListener {
    private boolean isShowing;
    private Context mContext;
    private float mDownX;
    private float mDownY;
    private float mLastX;
    private float mLastY;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    private int paramX;
    private int paramY;

    public BmFloatView(Context context) {
        super(context);
        this.mContext = context;
        this.init();
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        event.getAction();
        return super.dispatchKeyEvent(event);
    }

    private Position getPointPosition() {
        Position v0 = new Position();
        v0.x = SharePreferenceUtils.getIntSharePreference(this.mContext, "point", "params_x");
        v0.y = SharePreferenceUtils.getIntSharePreference(this.mContext, "point", "params_y");
        return v0;
    }

    public void hide() {
        if(this.mWindowManager != null && (this.isShowing)) {
            this.mWindowManager.removeView(((View)this));
            this.isShowing = false;
        }
    }

    private void init() {
        this.mLayoutParams = new WindowManager.LayoutParams();
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        Position v0 = this.getPointPosition();
        this.mLayoutParams.x = v0.x;
        this.mLayoutParams.y = v0.y;
        this.setBackgroundResource(R.drawable.icon);
        this.setOnClickListener(((View.OnClickListener)this));
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public void onClick(View view) {
        this.hide();
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case 0: {
                this.mDownX = event.getRawX();
                this.mDownY = event.getRawY();
                this.mLastX = event.getRawX();
                this.mLastY = event.getRawY();
                this.paramX = this.mLayoutParams.x;
                this.paramY = this.mLayoutParams.y;
                break;
            }
            case 2: {
                this.mLastX = event.getRawX();
                this.mLastY = event.getRawY();
                if(Math.abs(this.mLastY - this.mDownY) < 50f) {
                	 return super.onTouchEvent(event);
                }

                int v0 = ((int)(this.mLastX - this.mDownX));
                int v1 = ((int)(this.mLastY - this.mDownY));
                this.mLayoutParams.x = this.paramX + v0;
                this.mLayoutParams.y = this.paramY + v1;
                this.savePointPosition(this.mLayoutParams.x, this.mLayoutParams.y);
                this.postInvalidate();
                this.mWindowManager.updateViewLayout(((View)this), this.mLayoutParams);
                break;
            }
        }

        return super.onTouchEvent(event);
    }

    private void savePointPosition(int x, int y) {
        SharePreferenceUtils.setIntSharePreference(this.mContext, "point", "params_x", x);
        SharePreferenceUtils.setIntSharePreference(this.mContext, "point", "params_y", y);
    }

    private void saveServiceStatus() {
        SharePreferenceUtils.setBooleanSharePreference(this.mContext, "ServiceStart", "isStart", this.isShowing);
    }

    public void setShowing(boolean show) {
        this.isShowing = show;
    }

    public void show() {
        int v3 = 100;
        if(!this.isShowing) {
            this.mLayoutParams.gravity = 17;
            this.mLayoutParams.width = v3;
            this.mLayoutParams.height = v3;
            this.mLayoutParams.type = 2003;
            this.mLayoutParams.format = 1;
            this.mLayoutParams.flags = 40;
            this.mLayoutParams.alpha = 0.9f;
            this.mWindowManager.addView(((View)this), this.mLayoutParams);
            this.isShowing = true;
        }
    }
}

