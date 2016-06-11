package com.joke.bamenshenqi.model.phoneinfo;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class PhoneInfo {
    private String ERRORMSG;
    Context context;
    TelephonyManager mTm;
    static PhoneInfo phoneBaseInfo;

    public PhoneInfo(Context context) {
        super();
        this.ERRORMSG = "TelephonyManager not accessable";
        this.context = context;
    }

    public String getAndroidID() {
        return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    }

    public String getBaseband() {
        try {
			Class v0 = Class.forName("android.os.SystemProperties");
			return (String) v0.getMethod("get", String.class, String.class).invoke(v0.newInstance(), "gsm.version.baseband", "no message");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

    public String getBoard() {
        return Build.BOARD;
    }

    public String getBootLoader() {
        return Build.BOOTLOADER;
    }

    public String getBrand() {
        return Build.BRAND;
    }

    public String getDevice() {
        return Build.DEVICE;
    }

    public String getDeviceId() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getDeviceId() : this.ERRORMSG;
        return v0;
    }

    public String getDisplay() {
        return Build.DISPLAY;
    }

    public String getIncremental() {
        return Build.VERSION.INCREMENTAL;
    }

    public static PhoneInfo getInstance(Context context) {
        if(PhoneInfo.phoneBaseInfo == null) {
            PhoneInfo.phoneBaseInfo = new PhoneInfo(context);
        }

        return PhoneInfo.phoneBaseInfo;
    }

    public String getLocalIpAddress() {
        String v5 = null;
        Object v3;
        Enumeration v0 = null;
		try {
			v0 = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
        if(v0.hasMoreElements()) {
            Enumeration v1 = ((NetworkInterface) v0.nextElement()).getInetAddresses();
            do {
                if(!v1.hasMoreElements()) {
                	  return v5;
                }

                v3 = v1.nextElement();
            }
            while(((InetAddress)v3).isLoopbackAddress());

            v5 = ((InetAddress)v3).getHostAddress().toString();
        }
        else {
            v5 = null;
        }

        return v5;
    }

    public String getMacAddress() {
        return ((WifiManager) this.context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static HashMap getMobileHardwareInfo(Context context) {
        PhoneInfo v1 = new PhoneInfo(context);
        HashMap v0 = new HashMap();
        v0.put("manufacturer", v1.getManufacturer());
        v0.put("model", v1.getModel());
        v0.put("device", v1.getDevice());
        v0.put("product", v1.getProduct());
        v0.put("display", v1.getDisplay());
        v0.put("brand", v1.getBrand());
        v0.put("board", v1.getBoard());
        v0.put("bootloader", v1.getBootLoader());
        v0.put("serial", v1.getSerial());
        v0.put("releaze", v1.getVersionRelease());
        v0.put("incremental", v1.getIncremental());
        v0.put("sdk", v1.getVersionSdk());
        v0.put("sdkint", v1.getVersionSdkInt() + "");
        v0.put("codename", v1.getVersionCodeName());
        v0.put("macaddress", v1.getMacAddress());
        v0.put("bassid", v1.getSSID());
        v0.put("androidid", v1.getAndroidID());
        v0.put("simoperator", v1.getSimOperator());
        v0.put("simcountryiso", v1.getSimCountryIso());
        v0.put("simserialnumber", v1.getSimSerialNumber());
        v0.put("simstate", v1.getSimState() + "");
        v0.put("simoperatorname", v1.getSimOperatorName());
        v0.put("networktype", v1.getNetworkType() + "");
        v0.put("networkoperator", v1.getNetworkOperator());
        v0.put("networkoperatorname", v1.getNetworkOperatorName());
        v0.put("networkcountryiso", v1.getNetworkCountryIso());
        v0.put("deviceid", v1.getDeviceId());
        v0.put("subscriberid", v1.getSubscriberId());
        return v0;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getNetworkCountryIso() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getNetworkCountryIso() : this.ERRORMSG;
        return v0;
    }

    public String getNetworkOperator() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getNetworkOperator() : this.ERRORMSG;
        return v0;
    }

    public String getNetworkOperatorName() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getNetworkOperatorName() : this.ERRORMSG;
        return v0;
    }

    public int getNetworkType() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        int v0 = this.mTm != null ? this.mTm.getNetworkType() : -1;
        return v0;
    }

    public String getProduct() {
        return Build.PRODUCT;
    }

    public String getSSID() {
        return ((WifiManager) this.context.getSystemService("wifi")).getConnectionInfo().getSSID();
    }

    @SuppressLint(value={"NewApi"}) public String getSerial() {
        return Build.SERIAL;
    }

    public String getSimCountryIso() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getSimCountryIso() : this.ERRORMSG;
        return v0;
    }

    public String getSimOperator() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getSimOperator() : this.ERRORMSG;
        return v0;
    }

    public String getSimOperatorName() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getSimOperatorName() : this.ERRORMSG;
        return v0;
    }

    public String getSimSerialNumber() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getSimSerialNumber() : this.ERRORMSG;
        return v0;
    }

    public int getSimState() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        int v0 = this.mTm != null ? this.mTm.getSimState() : -1;
        return v0;
    }

    public String getSubscriberId() {
        this.mTm = (TelephonyManager) this.context.getSystemService("phone");
        String v0 = this.mTm != null ? this.mTm.getSubscriberId() : this.ERRORMSG;
        return v0;
    }

    public long getTotalMemorySize() {
        String v5 = null;
		try {
			BufferedReader v0 = new BufferedReader(new FileReader("/proc/meminfo"), 2048);
			String v4 = v0.readLine();
			v5 = v4.substring(v4.indexOf("MemTotal:"));
			v0.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ((long)Integer.parseInt(v5.replaceAll("\\D+", "")));
    }

    public String getVersionCodeName() {
        return Build.VERSION.INCREMENTAL;
    }

    public String getVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public String getVersionSdk() {
        return Build.VERSION.SDK;
    }

    public int getVersionSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    public String getWifiIp(Context context) {
        Object v2 = context.getSystemService("wifi");
        if(!((WifiManager)v2).isWifiEnabled()) {
            ((WifiManager)v2).setWifiEnabled(true);
        }

        return this.intToIp(((WifiManager)v2).getConnectionInfo().getIpAddress());
    }

    private String intToIp(int i) {
        return (i & 255) + "." + (i >> 8 & 255) + "." + (i >> 16 & 255) + "." + (i >> 24 & 255);
    }
}

