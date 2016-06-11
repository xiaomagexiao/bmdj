package com.joke.bamenshenqi.module.ui.view;

import java.lang.reflect.Method;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xiaomage.edit.R;

public class KeyboardUtils {
    class KeyboardListener implements KeyboardView.OnKeyboardActionListener {
    	
    	KeyboardListener keyboardUtils;
        KeyboardListener(KeyboardUtils keyboardUtils) {
            super();
            keyboardUtils = keyboardUtils;
        }

        public void onKey(int primaryCode, int[] arg1) {
            Editable v0 = KeyboardUtils.this.mEditText.getText();
            int v1 = KeyboardUtils.this.mEditText.getSelectionStart();
            if(primaryCode == -5) {
                if(!TextUtils.isEmpty(((CharSequence)v0)) && v1 > 0) {
                    v0.delete(v1 - 1, v1);
                }
            }
            else if(primaryCode == -7) {
                if((v0.toString().startsWith("-")) && !"0".equals(v0)) {
                    v0.delete(0, 1);
                    return;
                }

                v0.insert(0, "-");
            }
            else {
                if(primaryCode == -6) {
                    v0.clear();
                    return;
                }

                if(primaryCode == -2) {
                    if(v0.toString().contains(".")) {
                        return;
                    }

                    String v2 = v1 == 0 ? "0." : ".";
                    v0.insert(v1, ((CharSequence)v2));
                    return;
                }

                if(primaryCode == -33) {
                    if(KeyboardUtils.this.listener == null) {
                        return;
                    }

                    KeyboardUtils.this.listener.onSearchClicked(KeyboardUtils.this.mEditText);
                    return;
                }

                v0.insert(v1, Character.toString(((char)primaryCode)));
            }
        }

        public void onPress(int arg0) {
        }

        public void onRelease(int arg0) {
        }

        public void onText(CharSequence arg0) {
        }

        public void swipeDown() {
        }

        public void swipeLeft() {
        }

        public void swipeRight() {
        }

        public void swipeUp() {
        }
    }

    public interface OnSearchClickedListener {
        void onSearchClicked(EditText arg1);
    }

    private OnSearchClickedListener listener;
    private Context mContext;
    private EditText mEditText;
    private Keyboard mKeyboard;
    private KeyboardView mKeyboardView;

    public KeyboardUtils(Context context, KeyboardView view, EditText editText) {
        super();
        this.mContext = context;
        this.mKeyboardView = view;
        this.mEditText = editText;
        this.enableSoftInputMode(this.mEditText);
        this.mKeyboard = new Keyboard(this.mContext, R.xml.symbols);
        this.mKeyboardView.setEnabled(true);
        this.mKeyboardView.setPreviewEnabled(true);
        this.mKeyboardView.setOnKeyboardActionListener(new KeyboardListener(this));
        this.mKeyboardView.setKeyboard(this.mKeyboard);
    }

    static EditText access$000(KeyboardUtils x0) {
        return x0.mEditText;
    }

    static OnSearchClickedListener access$100(KeyboardUtils x0) {
        return x0.listener;
    }

    private void enableSoftInputMode(EditText editText) {
        if(Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(0);
        }
        else {
            try {
				Class v0 = EditText.class;
				Method v1 = v0.getMethod("setShowSoftInputOnFocus", Boolean.TYPE);
				v1.setAccessible(true);
				v1.invoke(editText, Boolean.valueOf(false));
				v1 = v0.getMethod("setSoftInputShownOnFocus", Boolean.TYPE);
				v1.setAccessible(true);
				v1.invoke(editText, Boolean.valueOf(false));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

    public void hideKeyboard() {
        if(this.mKeyboardView != null && this.mKeyboardView.getVisibility() == 0) {
            this.mKeyboardView.setVisibility(View.GONE);
        }
    }

    public boolean isNumber(String str) {
        boolean v1 = "0123456789".indexOf(str) > -1 ? true : false;
        return v1;
    }

    public boolean isword(String str) {
        boolean v1 = "abcdefghijklmnopqrstuvwxyz".indexOf(str.toLowerCase()) > -1 ? true : false;
        return v1;
    }

    public void setListener(OnSearchClickedListener listener) {
        this.listener = listener;
    }

    public void showKeyboard() {
        if(this.mKeyboardView != null) {
            int v0 = this.mKeyboardView.getVisibility();
            if(v0 != 8 && v0 != 4) {
                return;
            }

            this.mKeyboardView.setVisibility(View.VISIBLE);
        }
    }
}

