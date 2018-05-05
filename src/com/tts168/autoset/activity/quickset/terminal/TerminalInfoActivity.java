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
 * 设备信息
 * @author 袁剑
 *
 */
public class TerminalInfoActivity extends MyBaseActivity implements OnClickListener{
	public static final String TITLE="设备详情";
	TitleView titleView;//标题栏，如果想相应点击事件，需要在titleSave方法里面去实现
	
	private Button bt_edit;//编辑按钮
	private TextView tv_DeviceName;//设备名称
	private EditText et_DeviceName;//可编辑的设备名称
	public  ImageView iv_wifissi;//显示设备当前Wifi强度
	public  TextView tv_Batterey;//电池电量	
	private TextView tv_IP;//IP地址
	private TextView tv_Mac;//Mac地址
	private TextView tv_Version;//版本号
	private TextView tv_SWVersion;//软件版本号
	private TextView tv_ID;//产品ID
	private TextView tv_activity_terminal_info_config;//设备配置文件版本号
	public static final String ActivityName="TerminalInfoActivity";
	public static int power;//设备当前电量
	public static int ssi;//设备当前Wifi强度
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
		
		//接受实体类
		Intent intent=getIntent();
		DeviceEntity de=(DeviceEntity) intent.getSerializableExtra("deviceinfo");
		//标题栏
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
		 * 设置光标到字符串尾部
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
						//如果是不可见
						et_DeviceName.setVisibility(View.VISIBLE);
						tv_DeviceName.setVisibility(View.INVISIBLE);
					}else{
						//如果是可见
						et_DeviceName.setVisibility(View.INVISIBLE);
						tv_DeviceName.setVisibility(View.VISIBLE);
					}
					
					break;
				case R.id.bt_model_save_save:
					//保存操作
					rightViewOption();
					break;
				
				}
	}

	@Override
	public void rightViewOption() {
		// TODO Auto-generated method stub
		//保存编辑信息
		//保存操作
		String deviceName=et_DeviceName.getText().toString();
		Tools.generalEntity.setNickname(deviceName);
		String content=GeneralJsonOption.setData_general(Tools.generalEntity, JsonParseOption.SET_USERDATA,""+RemindVoiceTools.CommentRemindTools.FAILED_UPDATE,""+RemindVoiceTools.CommentRemindTools.SUCESS_UPDATE);
		
		ArrayList<String>contents=new ArrayList<String>();
		contents.add(content);
		TCPTools.sendTcp(contents, TerminalListAdapter.clickSocketIP,true);
		/**
		 * 销毁当前Activity，进入AlartActivity
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
