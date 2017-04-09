package com.joke.bamenshenqi.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.util.Log;

import com.joke.bamenshenqi.component.entity.ReportInfo;
import com.joke.bamenshenqi.model.ProcessInfo;

public class CheckData {
    public CheckData() {
        super();
    }

    public static void IsCheck(List arg4, List arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            if(((ReportInfo) arg4.get(v0)).isSameAttr(((ReportInfo) arg5.get(0)).getAttr())) {
                arg4.remove(v0);
                arg4.addAll(((Collection)arg5));
            }
        }
    }

    public List getAllPackage() {
        ArrayList v0 = new ArrayList();
        ((List)v0).add("com.miui.");
        ((List)v0).add("system");
        ((List)v0).add("com.android.");
        ((List)v0).add("system_server");
        ((List)v0).add("com.google.");
        ((List)v0).add("android.");
        ((List)v0).add("com.xiaomi.");
        ((List)v0).add("com.baidu.");
        ((List)v0).add("com.mipay.");
        ((List)v0).add("com.qihoo.");
        ((List)v0).add("com.mipay.");
        ((List)v0).add("com.joke.");
        ((List)v0).add("com.kingroot.");
        ((List)v0).add("app_process");
        ((List)v0).add("com.zhangkongapp.");
        ((List)v0).add("com.tencent.");
        ((List)v0).add("android.process.");
        ((List)v0).add("com.lbe.security.");
        ((List)v0).add(".sdk");
        ((List)v0).add("com.sdu.didi.");
        ((List)v0).add("com.svox.");
        ((List)v0).add("com.huawei.");
        ((List)v0).add("com.cootek.");
        ((List)v0).add("com.nuance.");
        ((List)v0).add("com.meizu.");
        ((List)v0).add("com.vivo.");
        return ((List)v0);
    }

    public boolean getResult(List<ProcessInfo> arg6, int index) {
        List v2 = this.getAllPackage();
        boolean v0 = false;
        int v1 = 0;
        while(true) {
            if(v1 < v2.size()) {
                if(!arg6.get(index).getName().equals(v2.get(v1)) && !arg6.get(index).getName().contains((CharSequence) v2.get(v1))) {
                    ++v1;
                    continue;
                }

                return true;
            }

            return v0;
        }
    }

    public static List<ReportInfo> getRlist(List<ReportInfo> arg6, String value) {
    	Log.e("typetoken", arg6.size()+"");
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < arg6.size(); ++v1) {
            ReportInfo v2 = new ReportInfo();
            v2.setValue(value);
            v2.setOffset(arg6.get(v1).getOffset());
            v2.setBase(arg6.get(v1).getBase());
            v2.setModule(arg6.get(v1).getModule());
            v2.setIs(arg6.get(v1).is);
            v2.setIslock(arg6.get(v1).islock);
            ((List)v0).add(v2);
        }

        return ((List)v0);
    }
}

