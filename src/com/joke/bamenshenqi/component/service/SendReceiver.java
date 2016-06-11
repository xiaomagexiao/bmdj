package com.joke.bamenshenqi.component.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.joke.bamenshenqi.biz.DataCollectionBiz;
import com.joke.bamenshenqi.model.CollectionData;
import com.joke.bamenshenqi.model.ResponseEntity;
import com.joke.bamenshenqi.util.HistoryRecordUtils;
import com.joke.bamenshenqi.util.LogUtil;

public class SendReceiver extends BroadcastReceiver {
    class DataTask extends AsyncTask {
    	public SendReceiver receiver;
        DataTask(SendReceiver receiver) {
            super();
            receiver = receiver;
        }

        protected ResponseEntity doInBackground(String[] params) {
            return SendReceiver.this.getResult();
        }

        protected Object doInBackground(Object[] arg2) {
            return this.doInBackground(((String[])arg2));
        }

        protected void onPostExecute(ResponseEntity hpd) {
            LogUtil.e("zl", "hpd" + hpd);
            if(hpd != null && hpd.getStatus() == 0) {
                HistoryRecordUtils.clearAllData(SendReceiver.this.mContext, "HISTORY_COLLECTION");
            }
        }

        protected void onPostExecute(Object arg1) {
            this.onPostExecute(((ResponseEntity)arg1));
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    public static final String ACTION_SEND = "ELITOR_CLOCK";
    public static final String OK = "OK";
    private CollectionData collectData;
    private Context mContext;

    public SendReceiver() {
        super();
    }

    public ResponseEntity getResult() {
        ResponseEntity v1 = null;
		try {
			v1 = DataCollectionBiz.postData(this.mContext, this.collectData);
			v1.getStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return v1;
    }

    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        if("OK".equals(intent.getStringExtra("msg"))) {
            this.collectData = (CollectionData) HistoryRecordUtils.getHistoryData(context, "HISTORY_COLLECTION");
            if(this.collectData != null) {
                new DataTask(this).execute(new String[0]);
            }
        }
    }
}

