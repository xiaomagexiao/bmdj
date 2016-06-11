package com.joke.bamenshenqi.util;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {
    private final String NET;
    private final String WIFI;
    private boolean mConnected;
    private Context mContext;
    private static NetworkManager mNetworkManager;
    private BroadcastReceiver mNetworkReceiver;

    static {
        NetworkManager.mNetworkManager = null;
    }

    private NetworkManager(Context context) {
        super();
        this.WIFI = "WIFI";
        this.NET = "3G";
        this.mContext = null;
        this.mConnected = false;
        this.mNetworkReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    NetworkManager.this.mConnected = NetworkManager.this.getNetWorkType(context) ? true : false;
                }
            }
        };
        this.mContext = context;
        this.mConnected = this.getNetWorkType(this.mContext);
    }

    static boolean access$000(NetworkManager x0, Context x1) {
        return x0.getNetWorkType(x1);
    }

    static boolean access$102(NetworkManager x0, boolean x1) {
        x0.mConnected = x1;
        return x1;
    }

    public static NetworkManager getInstance(Context context) {
        if(NetworkManager.mNetworkManager == null) {
            NetworkManager.mNetworkManager = new NetworkManager(context);
        }

        return NetworkManager.mNetworkManager;
    }

    private boolean getNetWorkType(Context context) {
        boolean v5 = false;
        Object v0 = context.getSystemService("connectivity");
        if(v0 != null) {
            NetworkInfo[] v3 = ((ConnectivityManager)v0).getAllNetworkInfo();
            if(v3 != null) {
                int v4 = v3.length;
                int v2 = 0;
                while(v2 < v4) {
                    if(v3[v2].isConnected()) {
                        v5 = true;
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    return v5;
                }
            }
        }

        return v5;
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    public void registerNetworkReceiver() {
        IntentFilter v0 = new IntentFilter();
        v0.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.mContext.registerReceiver(this.mNetworkReceiver, v0);
    }

    public void unRegisterNetworkReceiver() {
        if(this.mContext != null && this.mNetworkReceiver != null) {
            this.mContext.unregisterReceiver(this.mNetworkReceiver);
        }
    }
}

