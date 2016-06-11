package com.joke.bamenshenqi.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Base64;

import com.joke.bamenshenqi.component.entity.ReportInfo;

public class HistoryRecordUtils {
    public HistoryRecordUtils() {
        super();
    }

    public static void addHistoryData(Context context, String key, Object obj) {
        List v0 = HistoryRecordUtils.getAllHistoryData(context, key);
        if(v0 != null && !v0.contains(obj)) {
            v0.add(obj);
            HistoryRecordUtils.saveAllHistoryList(context, v0, key);
        }
    }

    public static void clearAllData(Context context, String key) {
        SharePreferenceUtils.getStringSharePreference(context, "HISTORY_DATAS", key);
    }

    public static boolean deleteObject(Context context, String key, int index) {
        List v0 = HistoryRecordUtils.initAllHistoryList(context, SharePreferenceUtils.getStringSharePreference(context, "HISTORY_DATAS", key));
        Object v1 = v0.remove(index);
        HistoryRecordUtils.saveAllHistoryList(context, v0, key);
        boolean v2 = v1 == null ? true : false;
        return v2;
    }

    public static List<ReportInfo> getAllHistoryData(Context context, String key) {
        return HistoryRecordUtils.initAllHistoryList(context, SharePreferenceUtils.getStringSharePreference(context, "HISTORY_DATAS", key));
    }

    public static Object getHistoryData(Context context, String key) {
        Object v3 = null;
        String v2 = SharePreferenceUtils.getStringSharePreference(context, "HISTORY_DATAS", key);
        if(!Utils.isEmpty(v2)) {
            try {
				v3 = HistoryRecordUtils.getOutputStream(context, v2).readObject();
			} catch (OptionalDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return v3;
    }

    private static ObjectInputStream getOutputStream(Context context, String value) throws StreamCorruptedException, IOException {
        ObjectInputStream v2 = Utils.isNull(value) ? null : new ObjectInputStream(new ByteArrayInputStream(Base64.decode(value.getBytes(), 0)));
        return v2;
    }

    private static List initAllHistoryList(Context context, String value) {
        // Decompilation failed
    	return new ArrayList();
    }

    public static void saveAllHistoryList(Context context, List arg8, String key) {
        if(Utils.isNull(arg8)) {
            SharePreferenceUtils.setStringSharePreference(context, "HISTORY_DATAS", key, "");
        }
        else if(arg8.size() > 0) {
            ByteArrayOutputStream v0 = new ByteArrayOutputStream();
            try {
				ObjectOutputStream v4 = new ObjectOutputStream(((OutputStream)v0));
				v4.writeInt(arg8.size());
				Iterator v5 = arg8.iterator();
				while(v5.hasNext()) {
				    v4.writeObject(v5.next());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            SharePreferenceUtils.setStringSharePreference(context, "HISTORY_DATAS", key, new String(Base64.encode(v0.toByteArray(), 0)));
        }
        else {
            SharePreferenceUtils.setStringSharePreference(context, "HISTORY_DATAS", key, "");
        }
    }

    public static void saveAllHistoryList(Context context, Object arg7, String key) {
        if(arg7 != null) {
            ByteArrayOutputStream v0 = new ByteArrayOutputStream();
            try {
				new ObjectOutputStream(((OutputStream)v0)).writeObject(arg7);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            SharePreferenceUtils.setStringSharePreference(context, "HISTORY_DATAS", key, new String(Base64.encode(v0.toByteArray(), 0)));
        }
        else {
            SharePreferenceUtils.setStringSharePreference(context, "HISTORY_DATAS", key, "");
        }
    }
}

