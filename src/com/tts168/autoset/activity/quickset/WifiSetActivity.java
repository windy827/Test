package com.tts168.autoset.activity.quickset;

import java.util.Timer;
import java.util.TimerTask;

import com.larkiv.larksmart7618.R;

import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;

import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.view.MyWifiView;
import com.tts168.autoset.view.title.TitleView;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
/**
 * wifi�����û��������������
 * @author Ԭ��
 *
 */
public class WifiSetActivity extends MyBaseActivity {
	public static final int MSG_WIFICHANGED=0x02; 
	public static final int INPUT_SET = Menu.FIRST;
	public static final String TITLE = "��������";
	TitleView titleView;// ���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	/**
	 * ��ȡ�����ļ��е�������Ϣ
	 */
	//public static HashMap<String, String> Info_Set;
	LayoutInflater inflater;
	View v1;
	MyWifiView myWifiView;
	public static final String ActivityName="WifiSetActivity";
	WifiAdmin wa;
	NetManager netManager ;
	Timer timer;
	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		inflater = getLayoutInflater();
		myWifiView=new MyWifiView();
		myWifiView.getWifiView((Activity)MyApplication.getInstance().getCur_Activity());
		// ������
		titleView = new TitleView(this);
		titleView.setTitle(TITLE);
		titleView.setSaveText(Tools.TITLE_REFRESH);
		
		wa = new WifiAdmin(this);
    	netManager = new NetManager(MyApplication.getInstance().getCur_Activity()); 				
    	timer = new Timer(true);
    	timer.schedule(task, 2000, 2000);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    switch (keyCode) {
	    case KeyEvent.KEYCODE_VOLUME_UP:
	    	MyApplication.getInstance().getAudioManager().adjustStreamVolume(
	            AudioManager.STREAM_MUSIC,
	            AudioManager.ADJUST_RAISE,
	            AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
	        return true;
	    case KeyEvent.KEYCODE_VOLUME_DOWN:
	    	MyApplication.getInstance().getAudioManager().adjustStreamVolume(
	            AudioManager.STREAM_MUSIC,
	            AudioManager.ADJUST_LOWER,
	            AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
	        return true;
	    default:
	        break;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// wifi���ûָ����û���ʼ����
		
		// �ر�UDP���ͺͽ����߳�,�ر�TCP����ͨѶ���߳�
//		Tools.isRec = false;
//		Tools.isSend = false;
		SearchDevicesActivity.isSearching = false;
		// MyWifiView.isConnect_waitting=false;
		//this.finish();
		Log.d("MYBASEACTIVITYRECYCLE","WIFISET onBackPressed");
		super.onBackPressed();
	}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	Log.d("MYBASEACTIVITYRECYCLE","WIFISET onDestroy");
	super.onDestroy();
	timer.cancel();
}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	Log.d("MYBASEACTIVITYRECYCLE","WIFISET onStop");
	super.onStop();
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		menu.add(Menu.FIRST, INPUT_SET, Menu.NONE, R.string.inputsetfile)
				.setIcon(R.drawable.input);
		return super.onCreateOptionsMenu(menu);
	}

	

//	/**
//	 * ���ݳ�������ȡʡ����Ϣ
//	 * 
//	 * @param shortCity
//	 */
//	public String getProvice(String shortCity) {
//		int Pro_index = 0;
//		for (int i = 0; i < ProviceAndCityResource.Provice.length; i++) {
//			for (int j = 0; j < ProviceAndCityResource.City[i].length; j++) {
//				if (shortCity.equals(ProviceAndCityResource.City[i][j])) {
//					Pro_index = i;
//					break;
//				}
//			}
//		}
//		String Provice = ProviceAndCityResource.Provice[Pro_index];
//		return Provice;
//	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void FindMyListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getContentViewID() {
		// TODO Auto-generated method stub
		return R.layout.wifiview;
	}

	@Override
	public void rightToLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leftToRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		myWifiView.RefreshData();
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}
	
	 TimerTask task = new TimerTask(){  
	        public void run() { 
	        	// �õ����������
	        	
	    		boolean isenable=wa.mWifiManager.isWifiEnabled();
	    		
	    		if (netManager.isOpenNetwork()) {
	    			String ssid = WifiTool.getCurrentSSID(wa);
	        	if(!ssid.equals(myWifiView.bt_SSID.getText().toString())){
	        		
	        		Message message = new Message();      
	 		        message.what =MSG_WIFICHANGED;      
	 		        handler_wifi.sendMessage(message);    
	        	}
	    		}
	       
	     }  
	  }; 
	/**
	 * ����Wifi
	 */
	public  Handler handler_wifi = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == MSG_WIFICHANGED) {
				myWifiView.RefreshData();
				ToastTools.long_Toast(MyApplication.getInstance().getCur_Activity(),"�������л���"+myWifiView.bt_SSID.getText().toString());
			} else {
				
			}
		}

	};
	
}
