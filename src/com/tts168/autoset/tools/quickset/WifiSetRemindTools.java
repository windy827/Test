package com.tts168.autoset.tools.quickset;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hellojni.GetWave;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.database.LarkSmartDataBaseConnection;
import com.tts168.autoset.database.GetOrUpdateWifi;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.view.MyWifiView;
/**
 * Wifi配置提醒并发送界面
 * @author 袁剑
 *
 */
public class WifiSetRemindTools {

	

	public static String guideimageURL="";//Wifi连接向导
	public static int guideType=1;//向导类型


/**
 * 
 * @param sendData
 * @param IsremindPassword 是否记住密码
 * @param  是否需要先睡眠等待前面的声音播放完毕【针对语音配置Wifi】
 */
	public void sendWifiSet(final Context context,final boolean IsremindPassword){
		// 开启线程播放静音波与真实声波的混声波
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					if(IsremindPassword){
						/**
						 * 数据库添加
						 */
						insertOrUpdateWifiSet_info(MyWifiView.ssid, MyWifiView.password);
						/**
						 * 修改配置文件 应该是在提交到服务端后再修改
						 */
						SharedPrefenceSetTools.ed.putString(
								SharedPrefenceSetTools.SSID, MyWifiView.ssid);
						SharedPrefenceSetTools.ed.putString(
								SharedPrefenceSetTools.PassWord,
								MyWifiView.password);
						SharedPrefenceSetTools.ed.commit();
					}
					

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

			}
		});
		t.start();
	}
	

	// 实例化

	
	
	/**
	 * 根据SSID得到对应的Wifi信息
	 */
	public  void insertOrUpdateWifiSet_info(String SSID, String PASSWORD) {
		
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			
				GetOrUpdateWifi.insertOrUpdate_WifiSet(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase(), SSID, PASSWORD);
			
			
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().endTransaction();
			if (LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase() != null) {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().close();
			}

		}
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().unlock();

	}

}
