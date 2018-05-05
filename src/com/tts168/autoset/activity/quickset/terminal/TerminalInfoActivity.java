package com.tts168.autoset.activity.quickset.terminal;

import java.util.ArrayList;

import com.autoset.jni.general.GeneralJsonOption;
import com.autoset.json.JsonParseOption;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.adapter.terminal.TerminalListAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.remindvoice.RemindVoiceTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.title.TitleView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * �豸��Ϣ
 * @author Ԭ��
 *
 */
public class TerminalInfoActivity extends MyBaseActivity implements OnClickListener{
	public static final String TITLE="�豸����";
	TitleView titleView;//���������������Ӧ����¼�����Ҫ��titleSave��������ȥʵ��
	
	private Button bt_edit;//�༭��ť
	private TextView tv_DeviceName;//�豸����
	private EditText et_DeviceName;//�ɱ༭���豸����
	public  ImageView iv_wifissi;//��ʾ�豸��ǰWifiǿ��
	public  TextView tv_Batterey;//��ص���	
	private TextView tv_IP;//IP��ַ
	private TextView tv_Mac;//Mac��ַ
	private TextView tv_Version;//�汾��
	private TextView tv_SWVersion;//����汾��
	private TextView tv_ID;//��ƷID
	private TextView tv_activity_terminal_info_config;//�豸�����ļ��汾��
	public static final String ActivityName="TerminalInfoActivity";
	public static int power;//�豸��ǰ����
	public static int ssi;//�豸��ǰWifiǿ��
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
		
	}

	@Override
	public void staticFindView() {
		// TODO Auto-generated method stub
		
		//����ʵ����
		Intent intent=getIntent();
		DeviceEntity de=(DeviceEntity) intent.getSerializableExtra("deviceinfo");
		//������
		titleView=new TitleView(this);	
		//titleView.setSaveText(Tools.TITLE_SAVE);
		titleView.setTitle(TITLE);
		
		
		bt_edit=(Button) findViewById(R.id.bt_activity_terminal_info_edit);
		tv_DeviceName=(TextView) findViewById(R.id.tv_activity_terminal_info_devicename);
		et_DeviceName=(EditText) findViewById(R.id.et_activity_terminal_info_devicename);
		tv_Batterey=(TextView) findViewById(R.id.tv_activity_terminal_info_batterey);
		tv_IP=(TextView) findViewById(R.id.tv_activity_terminal_info_ip);
		tv_Mac=(TextView) findViewById(R.id.tv_activity_terminal_info_mac);
		tv_Version=(TextView) findViewById(R.id.tv_activity_terminal_info_version);
		tv_SWVersion=(TextView) findViewById(R.id.tv_activity_terminal_info_sw_version);
		tv_ID=(TextView) findViewById(R.id.tv_activity_terminal_info_id);
		tv_activity_terminal_info_config=(TextView) findViewById(R.id.tv_activity_terminal_info_config);
		et_DeviceName.setText(de.getDeviceName());
		tv_activity_terminal_info_config.setText(de.getConfig());
		/**
		 * ���ù�굽�ַ���β��
		 */
		et_DeviceName.setSelection(et_DeviceName.getText().length());
		tv_DeviceName.setText(de.getDeviceName());
		tv_IP.setText(de.getIP());
		tv_Mac.setText(de.getMacIP());
	
		tv_ID.setText(de.getGid());
		tv_Version.setText(de.getVersion());
		tv_SWVersion.setText(de.getSwversion());
	}

	@Override
	public void staticListener() {
		// TODO Auto-generated method stub
		bt_edit.setOnClickListener(this);
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
		return R.layout.activity_terminal_info;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				switch(v.getId()){
				case R.id.bt_activity_terminal_info_edit:
					if(et_DeviceName.getVisibility()==View.INVISIBLE){
						//����ǲ��ɼ�
						et_DeviceName.setVisibility(View.VISIBLE);
						tv_DeviceName.setVisibility(View.INVISIBLE);
					}else{
						//����ǿɼ�
						et_DeviceName.setVisibility(View.INVISIBLE);
						tv_DeviceName.setVisibility(View.VISIBLE);
					}
					
					break;
				case R.id.bt_model_save_save:
					//�������
					rightViewOption();
					break;
				
				}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//����༭��Ϣ
		//�������
		String deviceName=et_DeviceName.getText().toString();
		Tools.generalEntity.setNickname(deviceName);
		String content=GeneralJsonOption.setData_general(Tools.generalEntity, JsonParseOption.SET_USERDATA,""+RemindVoiceTools.CommentRemindTools.FAILED_UPDATE,""+RemindVoiceTools.CommentRemindTools.SUCESS_UPDATE);
		
		ArrayList<String>contents=new ArrayList<String>();
		contents.add(content);
		TCPTools.sendTcp(contents, TerminalListAdapter.clickSocketIP,true);
		/**
		 * ���ٵ�ǰActivity������AlartActivity
		 */
		this.finish();
		
//		et_DeviceName.setVisibility(View.INVISIBLE);
//		tv_DeviceName.setVisibility(View.VISIBLE);
	}
	@Override
	protected void applyScrollListener() {
		// TODO Auto-generated method stub
		
	}

}
