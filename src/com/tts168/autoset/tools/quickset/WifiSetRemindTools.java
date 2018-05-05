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
 * Wifi�������Ѳ����ͽ���
 * @author Ԭ��
 *
 */
public class WifiSetRemindTools {

	

	public static String guideimageURL="";//Wifi������
	public static int guideType=1;//������


/**
 * 
 * @param sendData
 * @param IsremindPassword �Ƿ��ס����
 * @param  �Ƿ���Ҫ��˯�ߵȴ�ǰ�������������ϡ������������Wifi��
 */
	public void sendWifiSet(final Context context,final boolean IsremindPassword){
		// �����̲߳��ž���������ʵ�����Ļ�����
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					if(IsremindPassword){
						/**
						 * ���ݿ����
						 */
						insertOrUpdateWifiSet_info(MyWifiView.ssid, MyWifiView.password);
						/**
						 * �޸������ļ� Ӧ�������ύ������˺����޸�
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
	

	// ʵ����

	
	
	/**
	 * ����SSID�õ���Ӧ��Wifi��Ϣ
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
