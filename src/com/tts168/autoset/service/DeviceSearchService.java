package com.tts168.autoset.service;



import com.tts168.autoset.activity.SearchDevicesActivity;

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
public class DeviceSearchService extends Service {
	private int sleeptime=15;
	private boolean quit=false; 
	public static  boolean canAdd=true;
	private int count; 
	private ServiceBinder serviceBinder = new ServiceBinder(); 
	private final static String DIVIDE_RESULT="com.tts168.autoset.reciver.DIVIDE";
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
	   

	    //一直开启UDP发送接收，生命周期伴随服务
	    SearchDevicesActivity.getUDPreciver();
	    new Thread(new Runnable() { 
	        @Override 
	        public void run() { 
	        	
	            while (!quit) { 
	                try { 
	                Thread.sleep(1000); 
	                } catch (InterruptedException e) {} 
	                
	 	                	Intent intent = new Intent(DIVIDE_RESULT);
	 	                	intent.putExtra("count", count);
	 	                	count=0;
	 	                	canAdd=false;
	 	                	sendBroadcast(intent);
	 	              
	 	                Log.i("CountService", count+"");
	                
	               
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
	    //this.quit = false;
	    //this.startService(new Intent(this, EyeProtectService.class));
	    
	} 

}