package com.tts168.autoset.tools.update;


import com.tts168.autoset.tools.update.DownloadService.DownloadBinder;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class UpdateServiceConnection implements ServiceConnection{
	DownloadBinder binder;
	public static  boolean isBinded;
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// TODO Auto-generated method stub
		
		binder = (DownloadBinder) service;
		System.out.println("服务启动!!!");
		// 开始下载
		isBinded = true;
		binder.start();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		isBinded = false;
	}
	
}
