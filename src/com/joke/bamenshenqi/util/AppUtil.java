package com.joke.bamenshenqi.util;

import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class AppUtil {
	public AppUtil() {
		super();
	}

	public static void goHome(Context context) {
		Intent v0 = new Intent("android.intent.action.MAIN");
		v0.addCategory("android.intent.category.HOME");
		v0.addFlags(268435456);
		context.startActivity(v0);
	}

	public static void runApp(Context context, String packageName, String componentName) {
		Intent v0 = new Intent();
		v0.setComponent(new ComponentName(packageName, componentName));
		v0.addFlags(270663680);
		context.startActivity(v0);
	}

	public static void runApp(Context context, String packageName) {
        Intent v0 = context.getPackageManager().getLaunchIntentForPackage(packageName);
        v0.addFlags(270663680);
        context.startActivity(v0);
    }

	public static void runApp(Context context, String packageName, int type) {
		Intent v3 = context.getPackageManager().getLaunchIntentForPackage(packageName);
		List<ActivityManager.RunningTaskInfo> v6 = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(20);
		int v2 = 0;
		while (true) {
			if (v2 >= v6.size()) {
				break;
			} else if (packageName.equals(v6.get(v2).topActivity.getPackageName())) {
				String v0 = v6.get(v2).topActivity.getClassName();
				v3.setAction("android.intent.action.MAIN");
				v3.addCategory("android.intent.category.LAUNCHER");
				try {
					v3.setComponent(new ComponentName(context, Class.forName(v0)));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				v3.addFlags(270663680);
				context.startActivity(v3);
			} else {
				++v2;
				continue;
			}

			return;
		}

		context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
	}
}
