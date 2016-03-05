package com.example.mylocalserversocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.IBinder;
import android.app.Service;
import android.content.Intent;

public class LocalServerSocketService extends Service {

	private LocalServerSocket mlocalServerSocket = null;

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			mlocalServerSocket = new LocalServerSocket("4A:4B:4C:4D:4E:4F");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
						LocalSocket accept = mlocalServerSocket.accept();
						InputStream inputStream = accept.getInputStream();
						BufferedWriter bw = null;
						BufferedReader br = null;
						br = new BufferedReader(new InputStreamReader(inputStream));
						bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("log.txt", MODE_PRIVATE)));
						String tmpStr = null;
						while (null != (tmpStr = br.readLine()) ) {
							bw.write(tmpStr);
						}
						bw.flush();
						bw.close();
						br.close();
						accept.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
