package com.joke.bamenshenqi.util;


import com.google.gson.Gson;
import com.joke.bamenshenqi.model.CollectionData;
import com.joke.bamenshenqi.model.SystemInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class PostCollectionForService {
    public PostCollectionForService() {
        super();
    }

    public static void HttpPostData(SystemInfo systemInfo, List arg12, List arg13) {
        DefaultHttpClient v3 = new DefaultHttpClient();
        HttpPost v4 = new HttpPost("http://192.168.1.220:8080/bamenCollection/collection");
        v4.addHeader("Authorization", "your token");
        v4.addHeader("Content-Type", "application/json");
        v4.addHeader("User-Agent", "imgfornote");
        CollectionData v1 = new CollectionData();
        v1.setSystemInfo(systemInfo);
        v1.setUserAppUsages(arg12);
        v1.setHackPackages(arg13);
        LogUtil.e(PostCollectionForService.class, "systemInfo :" + systemInfo);
        LogUtil.e(PostCollectionForService.class, "userAppUsage :" + arg12);
        LogUtil.e(PostCollectionForService.class, "hackPackage :" + arg13);
        String v6 = new Gson().toJson(v1);
        LogUtil.e(PostCollectionForService.class, "request toJson :" + v6);
        try {
			v4.setEntity(new StringEntity(v6));
			((HttpClient)v3).execute(((HttpUriRequest)v4)).getStatusLine().getStatusCode();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

