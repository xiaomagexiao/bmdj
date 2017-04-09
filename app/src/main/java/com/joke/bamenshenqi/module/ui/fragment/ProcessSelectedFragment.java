package com.joke.bamenshenqi.module.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.component.entity.NativeAppInfo;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.ProcessInfo;
import com.joke.bamenshenqi.module.ui.view.common.CommonAdapter;
import com.joke.bamenshenqi.module.ui.view.common.ViewHolder;
import com.joke.bamenshenqi.util.LogUtil;
import com.xiaomage.edit.R;

public class ProcessSelectedFragment extends DialogFragment {
    class DataTask extends AsyncTask {
        private Context context;

        public DataTask(ProcessSelectedFragment f, Context context) {
            super();
            this.context = context;
        }

        protected Object doInBackground(Object[] arg2) {
            return this.doInBackground(((String[])arg2));
        }

        protected List doInBackground(String[] params) {
            return NativeCtrlBiz.getProcessList(ProcessSelectedFragment.this.nativeCtrl);
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((List)arg1));
        }

        protected void onPostExecute(List arg4) {
            if(arg4 != null && arg4.size() > 0) {
                ProcessSelectedFragment.this.adapter.setData(ProcessSelectedFragment.this.getAppInfos(this.context, arg4));
                ProcessSelectedFragment.this.adapter.notifyDataSetChanged();
            }
        }
    }

    class ProcessAdapter extends CommonAdapter {
        public ProcessAdapter(ProcessSelectedFragment fragment, Context context, List arg3, int itemLayoutId) {
            super(context, arg3, itemLayoutId);
        }

        public void convert(ViewHolder helper, NativeAppInfo item) {
            ((TextView) helper.getView(R.id.id_item_process_selected_name)).setText(item.getAppName());
        }

        public void convert(ViewHolder arg1, Object arg2) {
            this.convert(arg1, ((NativeAppInfo)arg2));
        }
    }

    private Activity activity;
    private ProcessAdapter adapter;
    private ListView listView;
    private NativeCtrl nativeCtrl;

    public ProcessSelectedFragment() {
        super();
        this.nativeCtrl = NativeCtrl.getInstance();
        this.activity = this.getActivity();
    }

    static NativeCtrl access$000(ProcessSelectedFragment x0) {
        return x0.nativeCtrl;
    }

    static ProcessAdapter access$100(ProcessSelectedFragment x0) {
        return x0.adapter;
    }

    public Drawable getAppIcon(Context context, String packageName) {
        PackageManager v3 = context.getPackageManager();
        try {
			return v3.getApplicationInfo(packageName, 0).loadIcon(v3);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    public List getAppInfos(Context context, List<NativeAppInfo> arg11) {
        ArrayList v6 = new ArrayList();
        ArrayList v4 = new ArrayList();
        LogUtil.e("zl", "进入1");
        int v2;
        for(v2 = 0; v2 < arg11.size(); ++v2) {
            if(!arg11.get(v2).getName().contains("com.miui.") && !arg11.get(v2).getName().equals("system") && !arg11.get(v2).getName().contains("com.android.") && !arg11.get(v2).getName().equals("system_server") && !arg11.get(v2).getName().contains("com.google.") && !arg11.get(v2).getName().contains("android.") && !arg11.get(v2).getName().contains("com.xiaomi.") && !arg11.get(v2).getName().contains("com.baidu.") && !arg11.get(v2).getName().contains("com.qihoo.") && !arg11.get(v2).getName().contains("com.mipay.") && !arg11.get(v2).getName().contains("com.joke.") && !arg11.get(v2).getName().contains("com.kingroot.") && !arg11.get(v2).getName().equals("app_process") && !arg11.get(v2).getName().contains("com.zhangkongapp.")) {
                NativeAppInfo v3 = new NativeAppInfo();
                ProcessInfo v5 = new ProcessInfo();
                LogUtil.e("zl", "进入2");
                String v1 = ProcessSelectedFragment.getProgramNameByPackageName(context, arg11.get(v2).getName());
                Drawable v0 = this.getAppIcon(this.activity, arg11.get(v2).getName());
                if(!TextUtils.isEmpty(((CharSequence)v1)) && v0 != null) {
                    v3.setAppName(v1);
                    v3.setAppIcon(v0);
                    v3.setPid(((NativeAppInfo) arg11.get(v2)).getPid());
                    v5.setName(arg11.get(v2).getName());
                    LogUtil.e("zl", "进入3");
                    ((List)v4).add(v5);
                    ((List)v6).add(v3);
                }
            }
        }

        return ((List)v6);
    }

    public static String getProgramNameByPackageName(Context context, String packageName) {
        PackageManager v2 = context.getPackageManager();
        try {
			return v2.getApplicationLabel(v2.getApplicationInfo(packageName, 128)).toString();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packageName;
    }

    @NonNull public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.activity = this.getActivity();
        AlertDialog.Builder v0 = new AlertDialog.Builder(this.activity);
        View v2 = this.activity.getLayoutInflater().inflate(R.layout.fw_fragment_process_selected, null);
        this.listView = (ListView)v2.findViewById(R.id.id_process_selected_list);
        this.adapter = new ProcessAdapter(this, this.activity, null, R.layout.fw_item_process_selected);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg1, View view, int position, long id) {
            }
        });
        this.listView.setAdapter(this.adapter);
        v0.setView(v2);
        new DataTask(this, this.activity).execute(new String[0]);
        return v0.create();
    }
}

