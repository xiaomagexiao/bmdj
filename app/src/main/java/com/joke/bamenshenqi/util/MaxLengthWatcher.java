package com.joke.bamenshenqi.util;


import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaxLengthWatcher implements TextWatcher {
    private EditText editText;
    private int maxLen;

    public MaxLengthWatcher(int maxLen, EditText editText) {
        super();
        this.maxLen = 0;
        this.editText = null;
        this.maxLen = maxLen;
        this.editText = editText;
    }

    public void afterTextChanged(Editable arg0) {
    }

    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }

    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        Editable v0 = this.editText.getText();
        if(v0.length() > this.maxLen) {
            int v4 = Selection.getSelectionEnd(((CharSequence)v0));
            this.editText.setText(v0.toString().substring(0, this.maxLen));
            v0 = this.editText.getText();
            if(v4 > v0.length()) {
                v4 = v0.length();
            }

            Selection.setSelection(((Spannable)v0), v4);
        }
    }
}

