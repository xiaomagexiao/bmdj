package com.joke.bamenshenqi.jni;

public class NativeHelper {
	
	static {
		System.loadLibrary("Native");
	}

	public NativeHelper() {
		super();
	}

	public static native String cleardata();

	public static native void close();

	public static native String getCmdAck();

	public static native String getallproc();

	public static native String getlockdata();

	public static native int initHelper(String arg1);

	public static native boolean isConnect();

	public static native int isRoot();

	public static native int reconnect();

	public static native int sendCommand(String arg1);

	public static native int size();
	
}
