package com.joke.bamenshenqi.module.ui.view;

import com.xiaomage.edit.R;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class EditContainer extends RelativeLayout {
    private Context context;
    private EditText editText;
    private KeyboardUtils keyboardUtils;
    private KeyboardView keyboardView;
    private LinearLayout tipsContainer;

    public EditContainer(Context context) {
        super(context);
        this.initView(context);
    }

    public EditContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public EditContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        EditContainer.inflate(context, R.layout.fw_activity_search, ((ViewGroup)this));
        this.tipsContainer = (LinearLayout)this.findViewById(R.id.id_keyboard_tips_container);
        this.keyboardView = (KeyboardView)this.findViewById(R.id.id_keyboard_input_method);
    }
}

