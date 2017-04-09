package com.joke.bamenshenqi.module.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaomage.edit.R;

public class SearchResultView extends LinearLayout {
    public interface ISearchResultMode {
        void onModeChanged(int arg1);
    }

    public interface OnButtonClickedListener {
        void onLeftButtonClicked(View arg1);

        void onMiddleButtonClicked(View arg1);

        void onRightButtonClicked(View arg1);
    }

    private Context context;
    private int curMode;
    ISearchResultMode iSearchResultMode;
    private Button leftButton;
    private OnButtonClickedListener listener;
    private Button middleButton;
    private Button rightButton;
    private String searchData;
    private TextView tipsView;
    private int totalSize;

    public SearchResultView(Context context) {
        super(context);
        this.curMode = 0;
        this.totalSize = 0;
        this.initView(context);
    }

    public SearchResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.curMode = 0;
        this.totalSize = 0;
        this.initView(context);
    }

    static OnButtonClickedListener access$000(SearchResultView x0) {
        return x0.listener;
    }

    public void clear() {
        this.setResultSize(0);
    }

    private String getString(int id) {
        return this.getResources().getString(id);
    }

    private int getType(int num) {
        int v2 = 30;
        int v0 = 0;
        int v1 = 3;
        if(num != 0) {
            if(num > 0 && num <= v1) {
                return 1;
            }

            if(num > v1 && num <= v2) {
                return 2;
            }

            if(num <= v2) {
                return v0;
            }

            v0 = v1;
        }

        return v0;
    }

    private void initView(Context context) {
        this.context = context;
        SearchResultView.inflate(context, R.layout.fw_view_searchresult, ((ViewGroup)this));
        this.tipsView = (TextView)this.findViewById(R.id.id_searchresult_txt_tips);
        this.leftButton = (Button)this.findViewById(R.id.id_searchresult_btn_left);
        this.middleButton = (Button)this.findViewById(R.id.id_searchresult_btn_middle);
        this.rightButton = (Button)this.findViewById(R.id.id_searchresult_btn_right);
        this.leftButton.setText(this.getString(R.string.search_button_left));
        this.leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(SearchResultView.this.listener != null) {
                    SearchResultView.this.listener.onLeftButtonClicked(v);
                }
            }
        });
        this.middleButton.setText(this.getString(R.string.search_button_middle));
        this.middleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(SearchResultView.this.listener != null) {
                    SearchResultView.this.listener.onMiddleButtonClicked(v);
                }
            }
        });
        this.rightButton.setText(this.getString(R.string.search_button_right));
        this.rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(SearchResultView.this.listener != null) {
                    SearchResultView.this.listener.onRightButtonClicked(v);
                }
            }
        });
    }

    public void setOnButtonClickedListener(OnButtonClickedListener listener) {
        this.listener = listener;
    }

    public void setResultSize(int num) {
        this.setResultSize(this.searchData, num, this.iSearchResultMode);
    }

    public void setResultSize(String searchData, int num, ISearchResultMode iSearchResultMode) {
        int v6 = 8;
        this.searchData = searchData;
        this.totalSize = num;
        String v1 = "";
        int v0 = this.getType(num);
        if(iSearchResultMode != null) {
            iSearchResultMode.onModeChanged(v0);
        }

        switch(v0) {
            case 0: {
                this.tipsView.setText(((CharSequence)v1));
                this.leftButton.setVisibility(v6);
                this.middleButton.setVisibility(v6);
                this.rightButton.setVisibility(v6);
                break;
            }
            case 1: {
                this.tipsView.setText(String.format(this.getString(R.string.search_tips_below_4), Integer.valueOf(num)));
                this.leftButton.setVisibility(v6);
                this.middleButton.setVisibility(v6);
                this.rightButton.setVisibility(View.VISIBLE);
                break;
            }
            case 2: {
                this.tipsView.setText(String.format(this.getString(R.string.search_tips_4_30), Integer.valueOf(num)));
                this.leftButton.setVisibility(View.VISIBLE);
                this.middleButton.setVisibility(View.VISIBLE);
                this.rightButton.setVisibility(View.VISIBLE);
                break;
            }
            case 3: {
                this.tipsView.setText(String.format(this.getString(R.string.search_tips_above_30), Integer.valueOf(num), searchData));
                this.leftButton.setVisibility(View.VISIBLE);
                this.middleButton.setVisibility(v6);
                this.rightButton.setVisibility(v6);
                break;
            }
        }
    }

    public void setiSearchResultMode(ISearchResultMode iSearchResultMode) {
        this.iSearchResultMode = iSearchResultMode;
    }
}

