package com.tts168.autoset.service;



import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.tools.commen.MyLogTools;
import com.tts168.autoset.tools.commen.SystemTools;
import com.tts168.autoset.tools.network.Network;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 保护视力的服务
 *@author 袁剑
 *
 */
public class CheckStateService extends Service {
	private int sleeptime=15;
	private boolean quit=false; 
	public static  boolean canAdd=true;
	private int count; 
	private ServiceBinder serviceBinder = new ServiceBinder(); 
	private final static String PAKAGENAME="com.larkiv.larksmart";
	@Override
	public IBinder onBind(Intent intent) {
		return serviceBinder; 
	}
	public class ServiceBinder extends Binder { 
	   
	    public int getCount() { 
	        return count; 
	    } 
	} 
	
	@Override 
	public void onCreate() { 
	    super.onCreate();   
	   

	    //一直开启检测应用进程是否还存在的服务
	   
	    new Thread(new Runnable() { 
	        @Override 
	        public void run() { 
	        	
	            while (!quit) { 
	            	
	            	if(!SystemTools.isRunningApp(getApplicationContext(), PAKAGENAME)){
	            		quit=true;
	            		Network.getInstance(null).closeAllSocket();
	            		MyLogTools.d("EXITAPP","已关闭掉了所有的SOCKET");
	            		stopSelf();
	            	}else{
	            		MyLogTools.d("EXITAPP","应用未关闭");
	            	}
	                try { 
	                Thread.sleep(1000); 
	                } catch (InterruptedException e) {
	                	e.printStackTrace();
	                } 	                	 	                		                	               	     
	        } 
	        }
	    }).start(); 
	} 
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return START_STICKY;
	}
	@Override 
	public void onDestroy() { 
	    super.onDestroy();
	    MyLogTools.d("EXITAPP","已关闭");
	    //this.quit = false;
	    //this.startService(new Intent(this, EyeProtectService.class));
	    
	} 

}