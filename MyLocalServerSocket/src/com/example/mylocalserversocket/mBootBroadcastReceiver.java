package com.example.mylocalserversocket;

import android.provider.Settings;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class mBootBroadcastReceiver extends BroadcastReceiver {

	static final String ACTION = "android.intent.action.BOOT_COMPLETED";
	private Context mContext;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@SuppressWarnings("deprecation")
		public void handleMessage(android.os.Message msg) {
			if(Settings.Secure.getInt(mContext.getContentResolver(),Settings.Secure.DEVICE_PROVISIONED, 0) == 1) {
				Intent basibosIntent = new Intent(mContext, LocalServerSocketService.class);
//				basibosIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startService(basibosIntent);
			}else {
				handler.sendEmptyMessageDelayed(0, 15000);
			}
		};
	};

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION)) {
			this.mContext = context;
			Message msg = handler.obtainMessage();
			msg.sendToTarget();
		}
	}

}
