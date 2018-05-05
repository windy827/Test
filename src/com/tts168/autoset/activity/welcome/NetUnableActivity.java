package com.tts168.autoset.activity.welcome;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.others.wifi.NetManager;
import com.tts168.autoset.tools.others.wifi.WifiAPTools;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.view.MyWifiView;
import com.tts168.autoset.view.title.TitleView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * �̳���MyBaseActivity��һ��ģ��
 * @author Ԭ��
 *
 */
public class NetUnableActivity extends MyBaseActivity implements OnClickListener{
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	public static final String TITLE="������������";
	
	public static final String ActivityName="NetUnableActivity";
	Button bt_next;//��һ����ť
	@Override
	public String getActivityName() {
		// TODO Auto-generated method stub
		return ActivityName;
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
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		if(!Tools.CurrentActivityName.equals(getActivityName())){
			refresh();
		}
	}
	/**
	 * �ṩ���ⲿ��ˢ�½ӿ�
	 */
		public static void refresh(){
		
		}
	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		//����ʵ����
		
		titleView=new TitleView(this);		
		titleView.setTitle(TITLE);
		bt_next=(Button) findViewById(R.id.bt_activity_netunable);
		
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_next.setOnClickListener(this);
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
		return R.layout.activity_netunable;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				switch(v.getId()){
			
				case R.id.bt_activity_netunable:
					NetManager netManager = new NetManager(MyApplication.getInstance().getCur_Activity()); 				// �õ����������
					if (netManager.isOpenNetwork()) {
						
						WifiAdmin wa = new WifiAdmin(this);
						String mac=wa.GetMacAddress();
						 String info=wa.GetBSSID();
						 SharedPreferences shared = new SharedConfig(this).GetConfig(); 	// �õ������ļ�
						 shared.edit().putString(SharedConfig.KEY_WIFIBSSID, info).commit();
						 shared.edit().putString(SharedConfig.KEY_WIFI_MAC, mac).commit();
						ActivitySetting.startActivity((Activity)MyApplication.getInstance().getCur_Activity(), SearchDevicesActivity.class, null,true);
					}else{
						ToastTools.short_Toast(this, "WIFI��û�����ӣ���������WIFI�ٵ����һ����");
					}
					
					break;
				}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//�������
		ToastTools.short_Toast((Activity)MyApplication.getInstance().getCur_Activity(), "����ɹ�!!");
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
