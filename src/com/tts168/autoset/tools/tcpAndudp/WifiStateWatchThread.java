package com.tts168.autoset.tools.tcpAndudp;

import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.tools.SyncLock;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.view.MyWifiView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

/**
 * ��Wifi״̬����ʵʱ�ļ���
 * @author Ԭ��
 *
 */
public class WifiStateWatchThread {
	Context context;
	SyncLock synclock;
	public static boolean isWatchWifiState=true;
	public static final int MSG_STATE_WIFINOMAL=0x03;//WIFI����
	public static final int MSG_STATE_WIFICLOSE=0x01;//WIFI������
	public static final String Alarm_STATE_WIFICLOSE="��������״������ǰû�п��õ�Wifi~";//WIFI������
	public static final int MSG_STATE_WIFICHANGE=0x02;//WIFI�Ѿ��л���
	public static final String  Alarm__STATE_WIFICHANGE="Wifi�Ѿ������ˣ���ȷ�ϵ�ǰWifi�Ƿ��������õ�Wifi~";//WIFI������
	SharedPreferences shared;
	NetManager netManager;
	WifiAdmin wa;//Wifi������
	int alrm_count=0;//����Toast�Ĵ���
	public static final int alrm_can_show=20;
	public WifiStateWatchThread(Context context){
		this.context=context;
		isWatchWifiState=true;
		synclock = new SyncLock();
		shared = new SharedConfig(context).GetConfig(); 	// �õ������ļ�
		netManager = new NetManager(context); 				// �õ����������
		wa = new WifiAdmin(context);
	}
	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			synclock.lock();
			switch(msg.what){
			case MSG_STATE_WIFICLOSE:
				if(alrm_count==0){
					ToastTools.long_Toast(context, Alarm_STATE_WIFICLOSE);
				}
				alrm_count++;
				if(alrm_count>alrm_can_show){
					alrm_count=0;
				}
				
				break;
			case MSG_STATE_WIFICHANGE:
				if(alrm_count==0){
					ToastTools.long_Toast(context, Alarm__STATE_WIFICHANGE);	
				}
				alrm_count++;
				if(alrm_count>alrm_can_show){
					alrm_count=0;
				}
				
				break;
			}
			synclock.unlock();
			}
		};
		/**
		 * Wifi ״̬�ļ���
		 */
		public void doListen() {
			Thread th = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
						
					while(isWatchWifiState){
						Message msg=new Message();
						if(!netManager.isOpenWifi()){
							//WIFI������
							msg.what=MSG_STATE_WIFICLOSE;
						}else{
							String bssid=wa.GetBSSID();
							 String sharedBSSID=shared.getString(SharedConfig.KEY_WIFIBSSID, "");
							
							 if(bssid.equals(sharedBSSID)){
								 msg.what=MSG_STATE_WIFINOMAL;
							 }else{
								//WIFI�Ѿ��л���
								
								msg.what=MSG_STATE_WIFICHANGE;
							 }
						}
						handler.sendMessage(msg);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
			});
			th.start();

	}

		
		
	
}
