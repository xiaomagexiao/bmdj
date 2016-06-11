package com.joke.bamenshenqi.util;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

import com.joke.bamenshenqi.biz.NativeCtrlBiz;
import com.joke.bamenshenqi.jni.NativeCtrl;
import com.joke.bamenshenqi.model.CollectionData;
import com.joke.bamenshenqi.model.HackPackage;
import com.joke.bamenshenqi.model.SystemInfo;
import com.joke.bamenshenqi.model.phoneinfo.PhoneInfo;

public class CollectData {
    private CollectionData collectionData;
    private static CollectData instance;
    private SystemInfo systemInfo;

    private CollectData() {
        super();
    }

    public static CollectionData getCollectionData() {
        return CollectData.instance.collectionData;
    }

    public static List getHackPackages(Context context, List arg9, String packageName) {
        String v5 = null;
		try {
			v5 = context.getPackageManager().getPackageInfo(packageName, 0).versionCode + "";
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ArrayList v2 = new ArrayList();
        HackPackage v1 = new HackPackage();
        v1.setPackageName(packageName);
        v1.setVersionCode(v5);
        v1.setHackDetail(arg9);
        ((List)v2).add(v1);
        return ((List)v2);
    }

    public static CollectData getInstance() {
        if(CollectData.instance == null) {
            CollectData.instance = new CollectData();
        }

        return CollectData.instance;
    }

    public static SystemInfo getSystemInfo(Context context) {
        SystemInfo v2 = new SystemInfo();
        PhoneInfo v1 = new PhoneInfo(context);
        v2.setMac(v1.getMacAddress());
        v2.setModel(v1.getModel());
        v2.setManufacturer(v1.getManufacturer());
        v2.setRam("");
        v2.setAndroidVersion(v1.getVersionCodeName());
        v2.setSdk(v1.getVersionSdk());
        v2.setBaseband(v1.getBaseband());
        v2.setTypename(v1.getNetworkType() + "");
        v2.setHostname("");
        v2.setCountry("");
        v2.setLang("");
        v2.setDescription("");
        v2.setFingerprint("");
        v2.setImei(((PhoneInfo) context.getSystemService("phone")).getDeviceId());
        v2.setSerialno(v1.getSerial());
        v2.setSimnumeric("");
        v2.setSimalpha("");
        v2.setLinux("");
        return v2;
    }

    public static List getUserAppUsages(NativeCtrl mNativeCtrl, Context context) {
        return NativeCtrlBiz.getUserAppUsage(mNativeCtrl, context);
    }

    public static void setCollectionData(CollectionData collectionData) {
        CollectData.instance.collectionData = collectionData;
    }
}

