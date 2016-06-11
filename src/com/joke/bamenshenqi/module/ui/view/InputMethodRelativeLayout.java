package com.joke.bamenshenqi.module.ui.view;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.LinearLayout;

public class InputMethodRelativeLayout extends LinearLayout {
    public interface OnSizeChangedListenner {
        void onSizeChange(boolean arg1, int arg2, int arg3);
    }

    private int height;
    protected OnSizeChangedListenner onSizeChangedListenner;
    private int screenHeight;
    private int screenWidth;
    private boolean sizeChanged;
    private int width;

    public InputMethodRelativeLayout(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.sizeChanged = false;
        Display v0 = ((Activity)paramContext).getWindowManager().getDefaultDisplay();
        DisplayMetrics v1 = new DisplayMetrics();
        v0.getMetrics(v1);
        this.screenWidth = v1.widthPixels;
        this.screenHeight = v1.heightPixels;
    }

    public InputMethodRelativeLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        this.sizeChanged = false;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.width = widthMeasureSpec;
        this.height = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(this.onSizeChangedListenner != null && w == oldw && oldw != 0 && oldh != 0) {
            if(h < oldh && Math.abs(h - oldh) > this.screenHeight / 4) {
                this.sizeChanged = true;
            }
            else if(h <= oldh) {
                return;
            }
            else if(Math.abs(h - oldh) > this.screenHeight / 4) {
                this.sizeChanged = false;
            }
            else {
                return;
            }

            this.onSizeChangedListenner.onSizeChange(this.sizeChanged, oldh, h);
            this.measure(this.width - w + this.getWidth(), this.height - h + this.getHeight());
        }
    }

    public void setOnSizeChangedListenner(OnSizeChangedListenner paramonSizeChangedListenner) {
        this.onSizeChangedListenner = paramonSizeChangedListenner;
    }
}

