package com.joke.bamenshenqi.jni;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.AssetManager;
import android.util.Log;

import com.joke.bamenshenqi.component.entity.ReportInfo;
import com.joke.bamenshenqi.util.LogUtil;

public class NativeCtrl {
	private AssetManager mAssetMgr;
	private String mCoreName;
	private int mInitStatus;
	private static NativeCtrl mInstance;
	private boolean mIsExit;
	private String mPackageName;

	static {
		NativeCtrl.mInstance = null;
	}

	private NativeCtrl() {
		super();
		this.mIsExit = false;
		this.mInitStatus = 0;
		this.mAssetMgr = null;
	}

	public int cancelSearch() {
		return this.sendCmd("{\"cmd\":\"cancelsearch\"}");
	}

	private boolean checkConnect(int timeout) {
		int v1 = 0;
		do {
			if (NativeHelper.isConnect()) {
				break;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			++v1;
		} while (v1 < timeout);

		boolean v2 = v1 < timeout ? true : false;
		return v2;
	}

	public String clearData() {
		return NativeHelper.cleardata();
	}

	private int deleteFilesByShell(String fileName, boolean isDir) {
		String v0 = "rm ";
		if (isDir) {
			v0 += "-r ";
		}

		Process v2 = null;
		int v3 = 0;
		try {
			v2 = Runtime.getRuntime().exec(v0 + fileName);
			v3 = v2.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (v3 > 0) {
			v3 = 0;
		}

		if (v2 != null) {
			v2.exitValue();
		}

		return v3;
	}

	public int deleteItem(String moduleName, long offset) {
		return this.sendCmd("{\"cmd\":\"deleteresult\",\"param\":{\"module\":\"" + moduleName + "\",\"offset\":" + offset + "}}");
	}

	private void findDisconnect(String filesPath, String packageName) {
		if (this.tryReconnect() != 0 && (this.invokeCore(filesPath, this.mPackageName) != 0 || this.tryReconnect() != 0)
				&& this.getAsset(this.mAssetMgr, filesPath) == 0 && this.invokeCore(filesPath, this.mPackageName) == 0
				&& this.tryReconnect() != 0 && this.tryReconnect() != 0) {
			LogUtil.e(packageName, "connect failed!");
		}
	}

	public String getActivitys() {
		String v2 = "{\"cmd\":\"getactivitys\"}";
		String v0 = NativeHelper.sendCommand(v2) != 0 ? null : this.waitForCmd(500, v2);
		return v0;
	}

	public List getAllData(List arg2) {
		return new ArrayList();
	}

	private int getAsset(AssetManager assetManager, String filesPath) {
		int v8 = 0;
		String[] v1 = new String[] { this.mCoreName, "libmole.so", "libNative.so" };
		int v13 = v1.length;
		int v12;
		for (v12 = 0; v12 < v13; ++v12) {
			String v9 = v1[v12];
			String v10 = filesPath + File.separator + v9;
			byte[] v2 = new byte[307200];
			this.deleteFilesByShell(v10, false);
			Process v7 = null;
			try {
				BufferedInputStream v5 = new BufferedInputStream(assetManager.open(v9));
				FileOutputStream v6 = new FileOutputStream(v10);
				while (true) {
					int v4 = v5.read(v2);
					if (v4 == -1) {
						break;
					}

					v6.write(v2, 0, v4);
				}

				v5.close();
				v6.close();
				v7 = Runtime.getRuntime().exec("chmod 777 " + v10);
				v8 = v7.waitFor();
				if (v8 >= 0) {
					v8 = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			v7.exitValue();
			if (v7 != null) {
				v7.exitValue();
			}
		}

		return v8;
	}

	public String getAsyncReport() {
		String v2 = "{\"cmd\":\"getreport\"}";
		String v0 = NativeHelper.sendCommand(v2) != 0 ? null : this.waitForCmd(500, v2);
		return v0;
	}

	public String getCurSelect() {
		String v2 = "{\"cmd\":\"getcurselect\"}";
		String v0 = NativeHelper.sendCommand(v2) != 0 ? null : this.waitForCmd(500, v2);
		return v0;
	}

	public synchronized static NativeCtrl getInstance() {
		if (NativeCtrl.mInstance == null) {
			Class v1 = NativeCtrl.class;
			if (NativeCtrl.mInstance == null) {
				NativeCtrl.mInstance = new NativeCtrl();
			}
		}
		return NativeCtrl.mInstance;
	}

	public String getLockData() {
		return NativeHelper.getlockdata();
	}

	public String getPackagesList() {
		String v2 = "{\"cmd\":\"getallpackages\"}";
		String v0 = NativeHelper.sendCommand(v2) != 0 ? null : this.waitForCmd(500, v2);
		return v0;
	}

	public String getProcessList() {
		return NativeHelper.getallproc();
	}

	@Deprecated
	public JSONObject getReport() {
		JSONObject v3 = null;
		JSONObject v5 = null;
		String v4 = "{\"cmd\":\"getreport\"}";
		if (NativeHelper.sendCommand(v4) != 0) {
			v3 = v5;
		} else {
			String v0 = this.waitForCmd(500, v4);
			try {
				v3 = v0 != null ? new JSONObject(v0) : v5;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return v3;
	}

	public int getSpeed() {
		int v5;
		String v4 = "{\"cmd\":\"getspeed\"}";
		int v2 = NativeHelper.sendCommand(v4);
		if (v2 != 0) {
			v5 = 0;
		} else {
			String v0 = this.waitForCmd(500, v4);
			if (v0 != null) {
				try {
					JSONObject v3 = new JSONObject(v0);
					if (v3.has("result")) {
						return v3.getInt("result");
					} else {
						LogUtil.w("fp", v3.toString());
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			v5 = v2;
		}

		return v5;
	}

	public String getUsagestats(String mac) {
		String v2 = "{\"cmd\":\"getusagestats\",\"param\":\"" + mac + "\"}";
		String v0 = NativeHelper.sendCommand(v2) != 0 ? null : this.waitForCmd(500, v2);
		return v0;
	}

	public boolean hasRoot() {
		boolean v0 = NativeHelper.isRoot() == 0 ? true : false;
		return v0;
	}

	public int init(AssetManager assetManager, String packageName, String filesPath, boolean isAndroid5) {
		int v1;
		this.mCoreName = isAndroid5 ? "core5" : "core4";
		int v0 = NativeHelper.isRoot();
		if (v0 != 0) {
			v1 = v0;
		} else {
			v0 = this.getAsset(assetManager, filesPath);
			if (v0 != 0) {
				v1 = v0;
			} else {
				this.mAssetMgr = assetManager;
				this.mInitStatus = 0;
				v0 = this.initNativeHelper(filesPath);
				if (v0 != 0) {
					v1 = v0;
				} else {
					v0 = this.invokeCore(filesPath, packageName);
					this.mPackageName = packageName;
					v1 = v0;
				}
			}
		}

		return v1;
	}

	private int initNativeHelper(final String filesPath) {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		int v1 = NativeHelper.initHelper(filesPath);
		if (v1 == 0) {
			new Thread(new Runnable() {
				public void run() {
					while (!NativeCtrl.this.mIsExit) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (NativeHelper.isConnect()) {
							continue;
						}

						NativeCtrl.this.findDisconnect(filesPath, NativeCtrl.this.mPackageName);
					}
				}
			}).start();
		}

		return v1;
	}

	private int invokeCore(final String filesPath, final String packageName) {
		int v2;
		int v7 = 100;
		this.mInitStatus = 0;
		new Thread(new Runnable() {
			public void run() {
				try {
					String v4 = "" + filesPath + File.separator + NativeCtrl.this.mCoreName + " " + packageName + "\n";
					Process v2 = Runtime.getRuntime().exec("su");
					new DataOutputStream(v2.getOutputStream()).write(v4.getBytes());
					NativeCtrl.this.mInitStatus = 1;
					v2.waitFor();
					NativeCtrl.this.mInitStatus = 0;
					if (v2 != null) {
						v2.exitValue();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		int v3 = 0;
		do {
			if (this.mInitStatus == 1) {
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			++v3;
		} while (v3 < v7);

		if (v3 < v7 || this.mInitStatus == 1) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			v2 = 0;
		} else {
			v2 = -5;
		}

		return v2;
	}

	public boolean isConnect() {
		return NativeHelper.isConnect();
	}

	public int lockData(String moduleName, long offset, String objValue) {
		return this.sendCmd("{\"cmd\":\"lockdata\",\"param\":{\"address\":[{\"module\":\"" + moduleName + "\",\"offset\":"
				+ offset + ",\"base\":0}],\"object\":\"" + objValue + "\"}}");
	}

	public int modifyAll(List<ReportInfo> arg9, String objValue, int type) {
		String v1 = new String();
		Iterator<ReportInfo> v3 = arg9.iterator();
		while (v3.hasNext()) {
			ReportInfo v0 = v3.next();
			if (v1.length() > 0) {
				v1 += ',';
			}

			v1 = v1 + "{\"base\":" + v0.getBase() + ",\"offset\":" + v0.getOffset() + "}";
		}

		return this.sendCmd("{\"cmd\":\"modify\",\"param\":{\"address\":[" + v1 + "],\"object\":\"" + objValue + "\",\"type\":"
				+ type + "}}");
	}

	public int modifyData(long base, long offset, String objValue, int type) {
		return this.sendCmd("{\"cmd\":\"modify\",\"param\":{\"address\":[{\"base\":" + base + ",\"offset\":" + offset
				+ "}],\"type\":" + type + ",\"object\":\"" + objValue + "\"}}");
	}

	public synchronized static void release() {
		if (NativeCtrl.mInstance != null) {
			Class v3 = NativeCtrl.class;
			if (NativeCtrl.mInstance != null) {
				String v1 = "{\"cmd\":\"exit\"}";
				if (NativeHelper.sendCommand(v1) == 0) {
					NativeCtrl.mInstance.waitForCmd(500, v1);
				}

				NativeCtrl.mInstance.mIsExit = true;
				NativeCtrl.mInstance = null;
			}

		}
	}

	public int resetSearch() {
		return this.sendCmd("{\"cmd\":\"resetsearch\"}");
	}

	public int searchData(String data, int type) {
		return this.sendCmd("{\"cmd\":\"search\",\"param\":{\"data\":\"" + data + "\",\"type\":" + type + "}}");
	}

	public int selectApp(int pid) {
		int v3;
		String v2 = "{\"cmd\":\"selectproc\",\"param\":{\"pid\":" + pid + "}}";
		if (NativeHelper.sendCommand(v2) != 0) {
			v3 = -1;
		} else {
			this.waitForCmd(800, v2);
			v3 = this.sendCmd(v2);
		}

		return v3;
	}

	private int sendCmd(String strCmd) {
		Log.e("sendCmd", "cmd = " + strCmd);
		int v4;
		int v2 = NativeHelper.sendCommand(strCmd);
		if (v2 != 0) {
			v4 = v2;
		} else {
			String v0 = this.waitForCmd(500, strCmd);
			if (v0 != null) {
				try {
					JSONObject v3 = new JSONObject(v0);
					if ((v3.has("ack")) && (v3.getString("ack").equals("success"))) {
						return 0;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				v2 = 0;
			} else {
				v2 = -2;
			}

			v4 = v2;
		}

		return v4;
	}

	public int setSpeed(int speed) {
		return this.sendCmd("{\"cmd\":\"modifyspeed\",\"param\":{\"speed\":" + speed + "}}");
	}

	private int tryReconnect() {
		int v1;
		int v0 = NativeHelper.reconnect();
		if (v0 != 0) {
			v0 = -1;
			v1 = v0;
		} else if (this.checkConnect(30)) {
			v1 = v0;
		} else {
			v1 = -1;
		}

		return v1;
	}

	public int unlockData(String moduleName, long offset) {
		return this.sendCmd("{\"cmd\":\"unlockdata\",\"param\":{\"address\":[{\"module\":\"" + moduleName + "\",\"offset\":"
				+ offset + ",\"base\":0}]}}");
	}

	private String waitForCmd(int timeout, String strCmd) {
		String v0 = null;
		int v2;
		try {
			for (v2 = 0; v2 < timeout; ++v2) {
				if (!NativeHelper.isConnect()) {
					int v3;
					for (v3 = 0; v3 < 10; ++v3) {
						Thread.sleep(10);
						if (NativeHelper.isConnect()) {
							break;
						}
					}
				}

				if (NativeHelper.isConnect()) {
					if (NativeHelper.size() > 0) {
						v0 = NativeHelper.getCmdAck();
						if (v0.indexOf("\"version\"") != -1) {
							v0 = null;
						} else {
							return v0;
						}
					} else {
						Thread.sleep(10);
					}
				} else if (this.tryReconnect() == 0) {
					NativeHelper.sendCommand(strCmd);
					Thread.sleep(10);
				} else {
					Thread.sleep(10);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return v0;
	}
}
