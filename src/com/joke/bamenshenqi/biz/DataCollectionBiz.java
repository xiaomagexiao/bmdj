package com.joke.bamenshenqi.biz;


import android.content.Context;
import com.google.gson.Gson;
import com.joke.bamenshenqi.model.CollectionData;
import com.joke.bamenshenqi.model.ResponseEntity;
import com.joke.bamenshenqi.net.NetUtils;

public class DataCollectionBiz {
    public DataCollectionBiz() {
        super();
    }

    public static ResponseEntity getHackPackageDetailListResult(Context context, String hackPackageId, String appBase, String appMoudle, String appOffset) throws Exception {
        return NetUtils.getResponseEntity(context, "http://www.bamenzhushou.com:8080/bamenCollection/query?type=byMoudle&hackPackageId=" + hackPackageId + "&" + "appBase=" + appBase + "&" + "appMoudle=" + appMoudle + "&" + "appOffset=" + appOffset + "&" + "page=1");
    }

    public static ResponseEntity getHackPackageListResult(Context context, String packageName, String versionCode) throws Exception {
        return NetUtils.getResponseEntity(context, "http://www.bamenzhushou.com:8080/bamenCollection/query?type=byPackage&packageName=" + packageName + "&" + "versionCode=" + versionCode + "&" + "page=1");
    }

    public static ResponseEntity postData(Context context, CollectionData data) throws Exception {
        return NetUtils.postResponseEntity(context, "http://www.bamenzhushou.com:8080/bamenCollection/collection", "data=" + new Gson().toJson(data));
    }
}

