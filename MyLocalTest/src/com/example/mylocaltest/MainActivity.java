package com.example.mylocaltest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
								BufferedWriter br = new BufferedWriter(new OutputStreamWriter(lsocket.getOutputStream()));
								br.write("你呀别路啊" + (i++));
								br.newLine();
								br.write("砌砖 莀 "+ (i++));
								br.newLine();
								br.write("我的 "+ (i++));
								br.flush();
								br.close();
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
