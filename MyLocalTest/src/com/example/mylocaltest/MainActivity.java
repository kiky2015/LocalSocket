package com.example.mylocaltest;

import java.io.IOException;
import java.io.OutputStream;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	LocalSocket lsocket = null;
	LocalSocketAddress addr = new LocalSocketAddress("4A:4B:4C:4D:4E:4F");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						int i = 0;
						while(true) {
							try {
								lsocket = new LocalSocket();
								lsocket.connect(addr);
								OutputStream os = lsocket.getOutputStream();
								os.write(new String("你呀别路啊"+(i++)).getBytes("gbk"));
								os.flush();
								os.close();
								lsocket.close();
								Thread.sleep(200);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
