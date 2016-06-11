package com.joke.bamenshenqi.module.ui.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.component.activity.BaseActivity;
import com.joke.bamenshenqi.component.activity.FwMainActivity;
import com.joke.bamenshenqi.component.activity.FwReviseRecordActivity;
import com.joke.bamenshenqi.component.activity.ReviseResultActivity;
import com.joke.bamenshenqi.component.entity.ReportInfo;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.model.SearchData;
import com.joke.bamenshenqi.module.ui.view.KeyboardUtils;
import com.joke.bamenshenqi.module.ui.view.KeyboardUtils.OnSearchClickedListener;
import com.joke.bamenshenqi.module.ui.view.SearchResultView;
import com.joke.bamenshenqi.module.ui.view.SearchResultView.ISearchResultMode;
import com.joke.bamenshenqi.module.ui.view.SearchResultView.OnButtonClickedListener;
import com.joke.bamenshenqi.util.ActivityManager;
import com.joke.bamenshenqi.util.CheckData;
import com.joke.bamenshenqi.util.HistoryRecordUtils;
import com.joke.bamenshenqi.util.InitPopuWindown;
import com.joke.bamenshenqi.util.MaxLengthWatcher;
import com.xiaomage.edit.R;

public class FwSearchActivity extends BaseActivity implements View.OnClickListener {
    class SearchResultMode implements ISearchResultMode {
        SearchResultMode(FwSearchActivity a) {
            super();
        }

        public void onModeChanged(int curMode) {
            if(curMode > 0) {
                FwSearchActivity.this.keyboardUtils.hideKeyboard();
            }
            else {
                FwSearchActivity.this.keyboardUtils.showKeyboard();
            }
        }
    }

    class SearchTask extends AsyncTask {
        private Context context;
        private String searchContent;

        public SearchTask(FwSearchActivity a, Context context, String searchContent) {
            super();
            this.context = context;
            this.searchContent = searchContent;
        }

        protected SearchData doInBackground(String[] params) {
            SearchData v1 = null;
            if(FwSearchActivity.this.mNativeCtrl != null && (FwSearchActivity.this.mNativeCtrl.isConnect()) && NativeCtrlBiz.searchData(FwSearchActivity.this.mNativeCtrl, this.searchContent) == 0) {
                v1 = NativeCtrlBiz.searchForResultnew(FwSearchActivity.this.mNativeCtrl);
            }

            return v1;
        }

        protected Object doInBackground(Object[] arg2) {
            return this.doInBackground(((String[])arg2));
        }

        protected void onPostExecute(SearchData searchData) {
            FwSearchActivity.this.loadingView.setVisibility(View.GONE);
            if(searchData == null) {
                Toast.makeText(FwSearchActivity.this, "无搜索结果，请重新搜?", 1).show();
                NativeCtrlBiz.cancelSearch(FwSearchActivity.this.mNativeCtrl);
                FwSearchActivity.this.startActivity(new Intent(FwSearchActivity.this, FwRecommendActivity.class));
            }
            else {
                List v0 = searchData.getData();
                if(searchData.getSize() == 0) {
                    Toast.makeText(FwSearchActivity.this, "无搜索结果，请重新搜?", 1).show();
                    NativeCtrlBiz.cancelSearch(FwSearchActivity.this.mNativeCtrl);
                    FwSearchActivity.this.startActivity(new Intent(FwSearchActivity.this, FwRecommendActivity.class));
                }
                else {
                    FwSearchActivity.this.modifiedList = v0;
                    FwSearchActivity.this.lastSearchedContent = this.searchContent;
                    FwSearchActivity.this.searchResultView.setResultSize(FwSearchActivity.this.lastSearchedContent, searchData.getSize(), FwSearchActivity.this.searchResultMode);
                    FwSearchActivity.this.searchState = 1;
                }
            }
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((SearchData)arg1));
        }

        protected void onPreExecute() {
            super.onPreExecute();
            FwSearchActivity.this.loadingView.setVisibility(View.VISIBLE);
        }
    }

    private ImageView appImage;
    private TextView appName;
    private TextView cancelView;
    private TextView continueSearchView;
    private EditText editText;
    private KeyboardUtils keyboardUtils;
    private String lastSearchedContent;
    private LinearLayout loadingView;
    private NativeCtrl mNativeCtrl;
    private List<ReportInfo> modifiedList;
    private ProcessInfo processInfo;
    private SearchResultMode searchResultMode;
    private SearchResultView searchResultView;
    private int searchState;
    private KeyboardView view;

    public FwSearchActivity() {
        super();
        this.searchState = 0;
    }

    private void closeInputMethod(EditText editText) {
        Object v0 = this.getSystemService("input_method");
        if(((InputMethodManager)v0).isActive()) {
            ((InputMethodManager)v0).hideSoftInputFromWindow(editText.getWindowToken(), 2);
        }
    }

    private void getTitleNameDrable() {
        this.appName = (TextView)this.findViewById(R.id.tv_app_name);
        this.appImage = (ImageView)this.findViewById(R.id.im_app_icon);
        if(ActivityManager.getInstance().getNativeAppInfo() != null) {
            this.appImage.setBackgroundDrawable(ActivityManager.getInstance().getNativeAppInfo().getAppIcon());
            this.appName.setText(ActivityManager.getInstance().getNativeAppInfo().getAppName());
        }

        this.findViewById(R.id.ll_choiceApp).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FwSearchActivity.this.startActivity(new Intent(FwSearchActivity.this, FwMainActivity.class));
            }
        });
    }

    private void getValue() {
        Intent v1 = this.getIntent();
        v1.getStringExtra("test");
        Bundle v0 = v1.getExtras();
        if(v0 != null) {
            ArrayList v2 = v0.getParcelableArrayList("list");
            if(v2.size() >= 0) {
                this.modifiedList = (List) v2.get(0);
                this.continueSearchView.setVisibility(View.GONE);
                this.searchState = 2;
                this.editText.getEditableText().clear();
                this.searchResultView.setResultSize(this.lastSearchedContent, 0, this.searchResultMode);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == -1 && requestCode == 9999) {
            this.modifiedList = (List) data.getExtras().getSerializable("list");
            this.continueSearchView.setVisibility(View.GONE);
            this.searchState = 2;
            this.editText.getEditableText().clear();
            this.searchResultView.setResultSize(this.lastSearchedContent, 0, this.searchResultMode);
        }
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.id_recommend_cancel: {
                if(this.loadingView.getVisibility() == 0) {
                    this.loadingView.setVisibility(View.GONE);
                }

                NativeCtrlBiz.cancelSearch(this.mNativeCtrl);
                this.searchState = 0;
                this.searchResultView.clear();
                this.finish();
                break;
            }
            case R.id.ll_containmenu: {
                new InitPopuWindown().initPopu(this, this, v, this.processInfo, this.mNativeCtrl);
                break;
            }
        }
    }

    @Nullable public void onContainerBound(LayoutInflater inflater, ViewGroup parent, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fw_activity_search, parent);
        this.getTitleNameDrable();
        ActivityManager.getInstance().addActivity(((Activity)this));
        this.loadingView = (LinearLayout)this.findViewById(R.id.id_keyboard_loading);
        this.continueSearchView = (TextView)this.findViewById(R.id.id_keyboard_continue_search);
        this.searchResultMode = new SearchResultMode(this);
        this.editText = (EditText)this.findViewById(R.id.id_keyboard_edit);
        this.editText.addTextChangedListener(new MaxLengthWatcher(10, this.editText));
        this.findViewById(R.id.ll_containmenu).setOnClickListener(((View.OnClickListener)this));
        this.cancelView = (TextView)this.findViewById(R.id.id_recommend_cancel);
        this.cancelView.setOnClickListener(((View.OnClickListener)this));
        this.view = (KeyboardView)this.findViewById(R.id.id_keyboard_input_method);
        this.keyboardUtils = new KeyboardUtils(((Context)this), this.view, this.editText);
        this.keyboardUtils.showKeyboard();
        this.keyboardUtils.setListener(new OnSearchClickedListener() {
            public void onSearchClicked(EditText editText) {
                String v4 = editText.getEditableText().toString();
                if(v4.isEmpty()) {
                    Toast.makeText(FwSearchActivity.this, "不能为空", 1).show();
                }
                else {
                    switch(FwSearchActivity.this.searchState) {
                        case 0: 
                        case 1: {
                        	 new SearchTask(FwSearchActivity.this, FwSearchActivity.this, v4).execute(new String[0]);
                        	 break;
                        }
                        case 2: {
                        	  NativeCtrlBiz.modifyAll(FwSearchActivity.this.mNativeCtrl, FwSearchActivity.this.modifiedList, v4);
                              if(FwSearchActivity.this.processInfo != null && FwSearchActivity.this.modifiedList != null) {
                            	  //这里gson自动封装的还不是reportinfo
                                  List<ReportInfo> v1 = CheckData.getRlist(FwSearchActivity.this.modifiedList, v4);
                                  List<ReportInfo> v2 = HistoryRecordUtils.getAllHistoryData(FwSearchActivity.this, FwSearchActivity.this.processInfo.getName());
                                  if(v2 == null) {
                                      HistoryRecordUtils.saveAllHistoryList(FwSearchActivity.this, v1, FwSearchActivity.this.processInfo.getName());
                                  }

                                  if(v2 == null||v1.size() <= 0) {
                                	  FwSearchActivity.this.startActivity(new Intent(FwSearchActivity.this, ReviseResultActivity.class));
                                      return;
                                  }

                                  int v0;
                                  for(v0 = 0; v0 < v2.size(); ++v0) {
                                      int v3 = 0;
                                      while(v3 < v1.size()) {
                                          if(v2.get(v0).isSameAttr(v1.get(v3).getAttr())) {
                                              v2.remove(v0);
                                              v2.add(v1.get(v3));
                                          }
                                          else {
                                              ++v3;
                                              continue;
                                          }

                                          break;
                                      }
                                  }

                                  HistoryRecordUtils.saveAllHistoryList(FwSearchActivity.this, v2, FwSearchActivity.this.processInfo.getName());
                              }

                              FwSearchActivity.this.startActivity(new Intent(FwSearchActivity.this, ReviseResultActivity.class));
                              return;
                        }
                    }

                    return;
                }
            }
        });
        this.searchResultView = (SearchResultView)this.findViewById(R.id.id_keyboard_searchresultview);
        this.searchResultView.setOnButtonClickedListener(new OnButtonClickedListener() {
            public void onLeftButtonClicked(View v) {
                FwSearchActivity.this.searchState = 1;
                FwSearchActivity.this.continueSearchView.setVisibility(View.VISIBLE);
                FwSearchActivity.this.continueSearchView.setText(String.format(FwSearchActivity.this.getResources().getString(R.string.continue_search_tips), FwSearchActivity.this.lastSearchedContent));
                FwSearchActivity.this.editText.getEditableText().clear();
                FwSearchActivity.this.searchResultView.setResultSize(FwSearchActivity.this.lastSearchedContent, 0, FwSearchActivity.this.searchResultMode);
                FwSearchActivity.this.show();
            }

            public void onMiddleButtonClicked(View v) {
                FwSearchActivity.this.continueSearchView.setVisibility(View.GONE);
                Intent v1 = new Intent();
                Bundle v0 = new Bundle();
                ArrayList v2 = new ArrayList();
                v2.add(FwSearchActivity.this.modifiedList);
                v0.putSerializable("list", ((Serializable)v2));
                v1.setClass(FwSearchActivity.this, FwReviseRecordActivity.class);
                v1.putExtras(v0);
                FwSearchActivity.this.startActivityForResult(v1, 9999);
            }

            public void onRightButtonClicked(View v) {
                FwSearchActivity.this.continueSearchView.setVisibility(View.GONE);
                FwSearchActivity.this.searchState = 2;
                FwSearchActivity.this.editText.getEditableText().clear();
                FwSearchActivity.this.searchResultView.setResultSize(FwSearchActivity.this.lastSearchedContent, 0, FwSearchActivity.this.searchResultMode);
            }
        });
        this.getValue();
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
        if(nativeCtrl.isConnect()) {
            this.mNativeCtrl = nativeCtrl;
            this.processInfo = NativeCtrlBiz.getCurSelectPid(this.mNativeCtrl);
        }
    }
}

