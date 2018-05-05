package com.tts168.autoset.view.sidemenu;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autoset.jni.deviceInfo.DeviceInfoEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.JsonParseOption;
import com.larkiv.larksmart7618.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalInfoActivity;
import com.tts168.autoset.adapter.terminal.TerminalListAdapter;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.ForceTime;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.PreventForceClick;
import com.tts168.autoset.tools.commen.PreventViolence;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.commen.VersionTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.device.DeviceInfoTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.quickset.DeviceSelectTools;
import com.tts168.autoset.tools.quickset.WifiSetRemindTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.MyBaseView;
import com.tts168.autoset.view.WaitView;

public class SideMenuView extends MyBaseView{

	ImageView iv_pro;//当前连接的产品图片
	TextView tv_pro;//当前连接的产品昵称
	TextView tv_update_alarm;//更新提示图片
	TextView tv_version;//显示当前版本号

	RelativeLayout rl_deviceinfo;//关于产品


	RelativeLayout rl_update;//检测更新
	RelativeLayout rl_exit;//退出
	public  WaitView waitView;
	public static boolean isClickDeviceInfo=false;//是否点击了设备详情 
	public SideMenuView(View view) {
		super(view);
		// TODO Auto-generated constructor stub
		
	}
/**
 * 设置产品名称和对应的图片
 * @param name
 * @param res
 */
	public void setProductNameAndPic(String name,String res){
		//iv_pro.setImageBitmap(res);
		//MyApplication.asyncBitmapLoader.DisplayImage(DeviceSelectTools.devices_path, Tools.Current_DeviceBitmapURL, iv_pro, false);
		
		//ImageLoader.getInstance().displayImage(Tools.Current_DeviceBitmapURL, iv_pro, MyApplication.options);
		tv_pro.setText(name);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		PreventViolence.preventClick(v, PreventViolence.SHORT_TIME);//防暴力点击
		switch(v.getId()){
		case R.id.rl_sidemenue_mainmenu_deviceinfo:
			//设备详情界面
			boolean hascontent=false;
			if(DeviceInfoTools.al_DeviceInfos!=null&&DeviceInfoTools.al_DeviceInfos.size()>0){
				for(int i=0;i<DeviceInfoTools.al_DeviceInfos.size();i++){
					HashMap<String,DeviceEntity> temp=DeviceInfoTools.al_DeviceInfos.get(i);
					DeviceEntity entity=temp.get(Tools.Current_SocketIP);
					if(temp.containsKey(Tools.Current_SocketIP)){
						hascontent=true;
						Intent intent = new Intent(
								MyApplication.getInstance().getCur_Activity(),
								TerminalInfoActivity.class);
						entity.setDeviceName(Tools.Current_DeviceName);
						intent.putExtra("deviceinfo",entity );
						MyApplication.getInstance().getCur_Activity()
								.startActivity(intent);
						break;
					}
				}
			} 
			if(!hascontent){
				waitView.setPBVisable();
				isClickDeviceInfo=true;
				ArrayList<String>domainNames=new ArrayList<String>();
				domainNames.add(DeviceInfoEntity.DOMAIN_DEVICE_INFO);
				TCPTools.sendTcpByDomain(domainNames, Tools.Current_SocketIP);
//				ArrayList<String> names = new ArrayList<String>();
//				String content=new AutoSetJsonTools()
//				.setGetDomainJsonObject(JsonParseOption.GET_HEARTBEATDATE,
//						new String[]{DeviceInfoEntity.DOMAIN_DEVICE_INFO});
//				try {
//				JSONObject main=new JSONObject(content);
//				
//					main.put("timeD", GetAndSetTime.setTime());
//					Log.d("NETWORKSENDHEART",main.toString());
//					names.add(main.toString());
//					TCPTools.sendTcp(names,  Tools.Current_SocketIP, false);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			
			
			break;
		
		case R.id.rl_sidemenue_mainmenu_update:
			//检测更新
		
			break;
		case R.id.rl_sidemenue_mainmenu_exit:
			//退出
			NotifyDialog.ExitAppDialog(MyApplication.getInstance().getCur_Activity());
			break;
		}
		
		
	}

	@Override
	protected void getDataRefresh() {
		// TODO Auto-generated method stub
		
	}
/**
 * 更新提示的TextView是否要显示，哟扑更新的时候显示
 */
	public void setUpdateTextViewVisiable(){
		tv_update_alarm.setVisibility(View.VISIBLE);
	}
	@Override
	public void staticFindViewByView() {
		// TODO Auto-generated method stub
		isClickDeviceInfo=false;
		waitView=new WaitView(view);
		iv_pro=(ImageView) view.findViewById(R.id.iv_model_login);
		tv_pro=(TextView) view.findViewById(R.id.tv_model_login);
		tv_version=(TextView) view.findViewById(R.id.tv_version);
		
		rl_deviceinfo=(RelativeLayout) view.findViewById(R.id.rl_sidemenue_mainmenu_deviceinfo);
		rl_update=(RelativeLayout) view.findViewById(R.id.rl_sidemenue_mainmenu_update);
		tv_update_alarm=(TextView) view.findViewById(R.id.tv_activity_mainmenu_update_alart);
		rl_exit=(RelativeLayout) view.findViewById(R.id.rl_sidemenue_mainmenu_exit);
		tv_version.setText("V"+VersionTools.getVersionNameWithOutAppName(MyApplication.getInstance().getCur_Activity()));
	}

	@Override
	public void staticByViewListener() {
		// TODO Auto-generated method stub
		rl_deviceinfo.setOnClickListener(this);
		
		rl_update.setOnClickListener(this);
		rl_exit.setOnClickListener(this);
	}

	@Override
	public void staticFindViewByActivity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void staticByActivityListener() {
		// TODO Auto-generated method stub
		
	}

}
