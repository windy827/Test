package com.tts168.autoset.tools.tcpAndudp.tcpsever;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONObject;


import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.alarm.AlarmOption;
import com.autoset.jni.answer.AnswerHelpJsonOption;
import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.birthday.BirthDayOption;
import com.autoset.jni.deviceInfo.DeviceInfoEntity;
import com.autoset.jni.deviceInfo.DeviceInfoJsonOption;

import com.autoset.jni.fullread.FullReadEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.general.GeneralJsonOption;

import com.autoset.jni.getdomain.GetDomainOptions;
import com.autoset.jni.getupset.GetUpSetEntity;
import com.autoset.jni.getupset.GetUpSetOption;
import com.autoset.jni.heartbeat.HeartBeatEntity;
import com.autoset.jni.otherset.ParameterEntity;
import com.autoset.jni.otherset.ParameterOptions;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.play.PlayOptions;
import com.autoset.jni.presskey.PressKeyEntity;
import com.autoset.jni.presskey.PressKeyOption;
import com.autoset.jni.remind.RemindEntity;
import com.autoset.jni.remind.RemindOption;
import com.autoset.jni.sleepset.SleepSetEntity;
import com.autoset.jni.sleepset.SleepSetJsonOption;
import com.autoset.jni.statusplay.StatusPlayEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.jni.udp.UDPNetEntity;
import com.autoset.jni.undisturbed.UndisturbedEntity;
import com.autoset.jni.undisturbed.UndisturbedOptions;
import com.autoset.jni.wakeup.WakeUpEntity;
import com.autoset.jni.wakeup.WakeUpOption;
import com.autoset.jni.wifi.WifiEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.ID_Manager;

import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.alart.AlartActivity;
import com.tts168.autoset.activity.alart.AwakeAlartCommentSetActivity;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.activity.alart.BirthdayAlartActivity;
import com.tts168.autoset.activity.alart.DefinedAlartEditActivity;
import com.tts168.autoset.activity.alart.RemindAlartActivity;
import com.tts168.autoset.activity.alart.SleepAlartEditActivity;
import com.tts168.autoset.activity.alart.addalart.AlartFragmentTabActivity;
import com.tts168.autoset.activity.answerhelper.AnswerRecordActivity;
import com.tts168.autoset.activity.highset.FuncSetActivity;

import com.tts168.autoset.activity.highset.undisturbed.UndisturbedActivity;
import com.tts168.autoset.activity.player.PlayingActivity;
import com.tts168.autoset.activity.quickset.ConnectWifiActiivity;
import com.tts168.autoset.activity.quickset.SendFSKActivity;
import com.tts168.autoset.activity.quickset.WifiSetWaitingActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalActivity;
import com.tts168.autoset.activity.quickset.terminal.TerminalInfoActivity;
import com.tts168.autoset.activity.welcome.SharedConfig;
import com.tts168.autoset.activity.highset.wakeset.WakeSetActivity;

import com.tts168.autoset.activity.localmusic.LocalMusicActivity;
import com.tts168.autoset.activity.mainmenu.MainMenuActivity;
import com.tts168.autoset.adapter.alart.AlartListViewAdapter;
import com.tts168.autoset.adapter.terminal.TerminalListAdapter;
import com.tts168.autoset.database.answerhelper.AnswerHelperDBOptions;
import com.tts168.autoset.database.connectedproduct.DB_ConnectedProduct_Option;
import com.tts168.autoset.tools.SyncLock;
import com.tts168.autoset.tools.Tools;

import com.tts168.autoset.tools.alart.AlartTools;


import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.MyLogTools;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.device.DeviceEntity;
import com.tts168.autoset.tools.device.DeviceInfoTools;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.otherset.OtherSetTools;
import com.tts168.autoset.tools.highcset.terminal.TerminalNotifyDialog;
import com.tts168.autoset.tools.highcset.terminal.TerminalTools;
import com.tts168.autoset.tools.highcset.undisturbed.UndisturbedTools;
import com.tts168.autoset.tools.highcset.wakeset.WakeSetTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.others.wifi.WifiAdmin;
import com.tts168.autoset.tools.player.PlayerTools;
import com.tts168.autoset.tools.presskey.PressKeyTools;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;
import com.tts168.autoset.view.WaitView;
import com.tts168.autoset.view.sidemenu.SideMenuView;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 * ����TCP�Ĵ���
 * @author Ԭ�� 
 * 
 *
 */
public class TCPThread {

	public static final String DOMAIN_NAMES[] = { AlarmEntity.DOMAIN_ALARM,
			GetUpSetEntity.DOMAIN_NAME, RemindEntity.DOMAIN_REMIND,SleepSetEntity.DOMAIN_NAME,
			BirthDayEntity.DOMAIN_BIRTHDAY, FullReadEntity.DOMAIN_CLOCKCHIME,
			GeneralEntity.DOMAIN_NAME, PressKeyEntity.DOMAIN_KEYBOARD,
			StatusPlayEntity.DOMAIN_STATUS_PLAY, UDPNetEntity.DOMAIN_NET,
			WifiEntity.DOMAIN_WIFI, DeviceInfoEntity.DOMAIN_DEVICE_INFO,
			WakeUpEntity.DOMAIN_NAME,UndisturbedEntity.DOMAIN_NAME,ParameterEntity.DOMAIN_NAME, GeneralEntity.DOMAIN_DEVICE_FLUSH };

	public static final String Handler_MSG = "MSG";
	public static Boolean mainThreadFlag = true;
	public static Boolean ioThreadFlag = true;
	ServerSocket serverSocket = null;
	public static Context context;
	public static Socket cursocket = null;
	/**
	 * ���յ�������
	 */
	public String msg_handler = "";
	/**
	 * �߳���
	 */
	SyncLock synclock;
	public static WaitView waitView;// �ȴ�����
	public TCPThread(Context context) {
		this.context = context;
		synclock = new SyncLock();
	}

	// ���Handler��������IOSocket�̷߳�������Ϣ���õ��߳�����
	public Handler handler = new Handler(MyApplication.getInstance().getCur_Activity().getMainLooper()) {
		public void handleMessage(Message msg) {
			synclock.lock();
			if (msg.what == 0x1) {
				// Tools.Device_Is_Connect = true;
				msg_handler = msg.getData().getString(Handler_MSG);
			
				String socketIP = (String) msg.obj;
				boolean hasDomainInfo = false;
				Log.d("GHETMSG", msg_handler);	
				
				//���յ��µ��������ݺ󣬶Ծɵ����ݽ������
				if(GetDomainOptions.getDomainEntity(
						msg_handler, AlarmEntity.DOMAIN_ALARM)){
					Log.d("ALARMINFOMATION",msg_handler);
					if(AlartTools.alartAdapter_content!=null
							&&AlartTools.alartAdapter_content.size()>0){
						AlartTools.alartAdapter_content.clear();
					}
				}
				//-------------------------------------------------------------------------------------------
				if (GetDomainOptions.getJsonID(msg_handler)==AutoSetJsonTools.ID_setUserData){
					if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
							((TerminalActivity)MyApplication.getInstance().getCur_Activity()).searchDevice2Refresh();
						
					}
				}
				
				
				for (int j = 0; j < DOMAIN_NAMES.length; j++) {
					String domainname = DOMAIN_NAMES[j];
					boolean isresult = GetDomainOptions.getDomainEntity(
							msg_handler, domainname);
					if (isresult) {
						hasDomainInfo = true;
						boolean isFromGet = true;
						if (domainname.equals(AlarmEntity.DOMAIN_ALARM)) {
							// ����
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								Log.d("GETALARMINFO",msg_handler);
								ArrayList<AlarmEntity> ae_array = AlarmOption
										.getAlarmEntityEntity(msg_handler);
								for (int i = 0; i < ae_array.size(); i++) {    
									AlarmEntity ae = ae_array.get(i);
									HashMap<String, Object> temp = new HashMap<String, Object>();
									temp.put(
											AlartTools.AlartListViewAdapterTools.KEY_TYPE,
											AlartTools.AlartType.JSON_ALART_TYPE_ALART);
									temp.put(
											AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
											ae);
									AlartTools.alartAdapter_content.add(temp);
								}
								if (Tools.CurrentActivityName
										.equals(AlartActivity.ActivityName)) {
									// ����ǰ�ķ���˳������Ϊһ���ж�������ʵ����ת
									AlartActivity.hasData=true;
									((AlartActivity)MyApplication.getInstance().getCur_Activity()).refresh();
								}

							} else {
								// SETDATARETURN����Ϣ
							}
						} else if (domainname
								.equals(GetUpSetEntity.DOMAIN_NAME)) {
							// ������ͨ������
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								AlartTools.getUpSetEntity = GetUpSetOption
										.getGetUpSetEntity(msg_handler);

								 if (Tools.CurrentActivityName
											.equals(AwakeAlartEditActivity.ActivityName)) {
										((AwakeAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
										
									} else if (Tools.CurrentActivityName
											.equals(SleepAlartEditActivity.ActivityName)) {
										((SleepAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
										
									} else if (Tools.CurrentActivityName
											.equals(DefinedAlartEditActivity.ActivityName)) {
										((DefinedAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
										
									} else if (Tools.CurrentActivityName
											.equals(BirthdayAlartActivity.ActivityName)) {
										BirthdayAlartActivity.waitView.setPBGone();
										
									} else if (Tools.CurrentActivityName
											.equals(RemindAlartActivity.ActivityName)) {
										((RemindAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
										
									} else if (Tools.CurrentActivityName
											.equals(AlartFragmentTabActivity.ActivityName)) {
										((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
										
									}
								ActivitySetting.startUnfinishedActivity(
										(Activity) MyApplication.getInstance().getCur_Activity(),
										AwakeAlartCommentSetActivity.class,
										null,false);
							} else {
								// SETDATARETURN����Ϣ
								// String
								// result=StatusPlayOption.getStatusPlayResult(DeviceInfoEntity.DOMAIN_DEVICE_INFO,msg_handler);
								// ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), result);
							}
							// ToastTools.short_Toast(context, "�豸��Ϣ");

						} else if (domainname
								.equals(RemindEntity.DOMAIN_REMIND)) {
							// ����
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								ArrayList<RemindEntity> re_array = RemindOption
										.getRemindEntity(msg_handler);
								for (int i = 0; i < re_array.size(); i++) {
									RemindEntity re = re_array.get(i);
									HashMap<String, Object> temp = new HashMap<String, Object>();
									temp.put(
											AlartTools.AlartListViewAdapterTools.KEY_TYPE,
											AlartTools.AlartType.JSON_ALART_TYPE_REMIND);
									temp.put(
											AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
											re);
									AlartTools.alartAdapter_content.add(temp);
								}
								if (Tools.CurrentActivityName
										.equals(AlartActivity.ActivityName)) {
									// ����ǰ�ķ���˳������Ϊһ���ж�������ʵ����ת
									AlartActivity.hasData=true;
									((AlartActivity)MyApplication.getInstance().getCur_Activity()).refresh();
								}
							} else {
								// SETDATARETURN����Ϣ
							}
						} else if (domainname
								.equals(BirthDayEntity.DOMAIN_BIRTHDAY)) {
							// ���ռ�����
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								ArrayList<BirthDayEntity> be_array = BirthDayOption
										.getBirthDayEntity(msg_handler);
								for (int i = 0; i < be_array.size(); i++) {
									BirthDayEntity be = be_array.get(i);
									HashMap<String, Object> temp = new HashMap<String, Object>();
									temp.put(
											AlartTools.AlartListViewAdapterTools.KEY_TYPE,
											AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY);
									temp.put(
											AlartTools.AlartListViewAdapterTools.KEY_ENTITY,
											be);
									AlartTools.alartAdapter_content.add(temp);
								}
								if (Tools.CurrentActivityName
										.equals(AlartActivity.ActivityName)) {
									// ����ǰ�ķ���˳������Ϊһ���ж�������ʵ����ת
									AlartActivity.hasData=true;
									((AlartActivity)MyApplication.getInstance().getCur_Activity()).refresh();
								}

							} else {
								// SETDATARETURN����Ϣ
							}
						} else if (domainname
								.equals(FullReadEntity.DOMAIN_CLOCKCHIME)) {
							// ���㱨ʱ
							if (isFromGet) {
								// GETDATARETURN����Ϣ
							} else {
								// SETDATARETURN����Ϣ
							}
						} else if (domainname.equals(GeneralEntity.DOMAIN_NAME)) {
							// ��������
							Log.d("UDPANDTCP",msg_handler);
							Log.d("UDPTCPSIZE","�յ���gernal ����"+msg_handler+GetAndSetTime.setTime());
							Log.d("UDPTCOCONNECTTIME","�յ�Gerner��ʱ��"+System.currentTimeMillis());
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								Log.d("UDPTCPSIZE","����general");
								GeneralEntity ge = GeneralJsonOption
										.getGeneral(msg_handler);
								//------------------FSK�����ϵ���Ϣ----------------------------------------------------------------
								if (ge.isFSKSet()){
									MyLogTools.d("EMTMFLOG","���յ���FSK����");
									if(Tools.CurrentActivityName.equals(ConnectWifiActiivity.ActivityName)||Tools.CurrentActivityName.equals(SendFSKActivity.ActivityName)){
										ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "�����ɹ�");
										ActivitySetting.startActivity(MyApplication.getInstance().getCur_Activity(), TerminalActivity.class, null, true);
									}
								}
								//----AdapterInfo����
								boolean hasAdapterAdd = false;
							
								for (int i = 0; i < TerminalTools.adapter_infoByIP.size(); i++) {
									TerminalAdapterEntity cur_temp_ae=TerminalTools.adapter_infoByIP.get(i);
									if (((GeneralEntity)cur_temp_ae.getGeneralEntity()).getDeviceid().equals(ge.getDeviceid())) 
									{
										hasAdapterAdd = true;
										TerminalAdapterEntity temp_ae=new TerminalAdapterEntity(ge, socketIP, 4);
										
										TerminalTools.adapter_infoByIP.set(i, temp_ae);
										break;
									}
								}
							
								
								if (!hasAdapterAdd) {
									TerminalAdapterEntity temp_ae=new TerminalAdapterEntity(ge, socketIP, 4);
									Log.d("UDPTCPSIZE","���general");
									TerminalTools.adapter_infoByIP.add(temp_ae);
									
								}else{
									Log.d("UDPTCPSIZE","�Ƿ������-----------"+hasAdapterAdd+"����"+TerminalTools.adapter_infoByIP.size());
								}
								
								
								Log.d("TERMINAL","���յ�"+TerminalTools.adapter_infoByIP.size()+"������");
								//����ǰ���ӵ�Socketָ���µ�Socket
								
									if(Tools.Current_SocketIP.equals(socketIP)){
										
										Tools.generalEntity = ge;
										Tools.Rec_Device_name = ge.getNickname();
									}
									
								
								if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
									TerminalActivity.hasData=true;
									((TerminalActivity)MyApplication.getInstance().getCur_Activity()).refresh();
								}
								

							} else {
								// SETDATARETURN����Ϣ
								String result = StatusPlayOption
										.getStatusPlayResult(
												GeneralEntity.DOMAIN_NAME,
												msg_handler);
								ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), result);
							}

						}else if (domainname
								.equals(DeviceInfoEntity.DOMAIN_DEVICE_INFO)) {
							// �豸��Ϣ
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								try {
									JSONObject obj=new JSONObject(msg_handler);
									obj.put("TIME_CELLPHONE", GetAndSetTime.setTime());
									Log.d("HEART_DEVICEINFO",obj.toString());
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								
								
								DeviceInfoEntity de = DeviceInfoJsonOption
										.getDeviceInfo(msg_handler);
								String deviceIp = de.getDevice_ip();
								String deviceMac = de.getDevice_mac();
								String device_version = de.getDevice_version();
								String device_swversion = de.getSw_version();
								String goods_id = de.getGoodsid();
								String openid = de.getOpenid();
								if(openid.length()>0&&!openid.equals(AutoSetParsorTools.RETURN_NULL_String_ERR)){
									Tools.generalEntity.setOpenid(openid);
								}
								/**
								 * ���ɨ�赽���豸ID�����ݿ�
								 */
								//DB_ConnectedProduct_Option.addProductid(MyApplication.getInstance(), goods_id);
								int power = de.getDevice_power();
								String deviceName = TerminalListAdapter.ClickDeviceName;
								int rssi=de.getRssi();
								DeviceEntity entity = new DeviceEntity(
										deviceName, deviceIp, deviceMac,
										device_version,de.getSw_version(), goods_id, power,rssi,de.getHost(),de.getConfig());
								
								//----����豸������ʷ��¼
								boolean needAdd=true;
								if(DeviceInfoTools.al_DeviceInfos!=null&&DeviceInfoTools.al_DeviceInfos.size()>0){
									for(int i=0;i<DeviceInfoTools.al_DeviceInfos.size();i++){
										HashMap<String,DeviceEntity> temp=DeviceInfoTools.al_DeviceInfos.get(i);
										if(temp.containsKey(deviceIp)){
											HashMap<String,DeviceEntity> update_entity=new HashMap<String, DeviceEntity>();
											update_entity.put(deviceIp, entity);
											DeviceInfoTools.al_DeviceInfos.set(i, update_entity);
											needAdd=false;
											break;
										}
									}
								}
								if(needAdd){
									if(DeviceInfoTools.al_DeviceInfos==null){
										DeviceInfoTools.al_DeviceInfos=new ArrayList<HashMap<String,DeviceEntity>>();
									}
									HashMap<String,DeviceEntity> add_entity=new HashMap<String, DeviceEntity>();
									add_entity.put(deviceIp, entity);
									DeviceInfoTools.al_DeviceInfos.add(add_entity);
								}
								//----����豸������ʷ��¼---------------------------
								
								
								if(TerminalListAdapter.isDeviceInfo_Click){
									// �ر��豸�����߳�,�����豸�������
									if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
									((TerminalActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
									TerminalActivity.isSearch.set(false);
									Intent intent = new Intent(
											MyApplication.getInstance().getCur_Activity(),
											TerminalInfoActivity.class);
									intent.putExtra("deviceinfo", entity);
									MyApplication.getInstance().getCur_Activity()
											.startActivity(intent);
									TerminalListAdapter.isDeviceInfo_Click=false;
									}
								}else{
									//����ӵ��Wifiǿ�ȵĽ���
									if(Tools.CurrentActivityName.equals(TerminalActivity.ActivityName)){
										for(int i=0;i<TerminalTools.adapter_infoByIP.size();i++){
											TerminalAdapterEntity adapter_entity=TerminalTools.adapter_infoByIP.get(i);
											String curIP=(String) adapter_entity.getSocketIp();
											GeneralEntity ge = (GeneralEntity) adapter_entity.getGeneralEntity();
											if(!curIP.equals("")&&curIP.equals(socketIP)){
												if(rssi!=(Integer)adapter_entity.getRssi()||
														(Math.abs(power-ge.getDevice_power())>=10)){
													TerminalAdapterEntity temp_adapter_entity=new TerminalAdapterEntity(ge, socketIP,  rssi);
													TerminalTools.adapter_infoByIP.set(i, temp_adapter_entity);
													
													((TerminalActivity)MyApplication.getInstance().getCur_Activity()).refresh();
												}
												
											}
										}
										
									}else if(Tools.CurrentActivityName.equals(MainMenuActivity.ActivityName)){
										//������
				
										if(SideMenuView.isClickDeviceInfo){
											((MainMenuActivity)MyApplication.getInstance().getCur_Activity()).sideMenuView.waitView.setPBGone();
											entity.setDeviceName(Tools.Current_DeviceName);
											if(Tools.Current_SocketIP.equals(entity.getIP())){
												Intent intent = new Intent(
														MyApplication.getInstance().getCur_Activity(),
														TerminalInfoActivity.class);
												intent.putExtra("deviceinfo", entity);
												MyApplication.getInstance().getCur_Activity()
														.startActivity(intent);
												SideMenuView.isClickDeviceInfo=false;
											}
										}
										
										
									
										
									}
									
									
								}
								
							} else {
								// SETDATARETURN����Ϣ
								String result = StatusPlayOption
										.getStatusPlayResult(
												DeviceInfoEntity.DOMAIN_DEVICE_INFO,
												msg_handler);
								ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), result);
							}
							// ToastTools.short_Toast(context, "�豸��Ϣ");

						}
						
						else if (domainname.equals(WakeUpEntity.DOMAIN_NAME)) {
							// ����������Ϣ
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								if(Tools.CurrentActivityName.equals(FuncSetActivity.ActivityName)){
								WakeUpEntity entity = WakeUpOption
										.getWeakUp(msg_handler);
								((FuncSetActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								Intent intent = new Intent(
										MyApplication.getInstance().getCur_Activity(),
										WakeSetActivity.class);
								intent.putExtra(WakeSetTools.KEYS_ENTITY,
										entity);
								MyApplication.getInstance().getCur_Activity()
										.startActivity(intent);
								}
							} else {
								
							}
						

						}
						else if (domainname.equals(UndisturbedEntity.DOMAIN_NAME)) {
							// ����������Ϣ
							if (isFromGet) {
								// GETDATARETURN����Ϣ
								if(Tools.CurrentActivityName.equals(FuncSetActivity.ActivityName)){
								UndisturbedEntity entity = UndisturbedOptions
										.getUndisturbedEntity(msg_handler);
								((FuncSetActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								Intent intent = new Intent(
										MyApplication.getInstance().getCur_Activity(),
										UndisturbedActivity.class);
								intent.putExtra(UndisturbedTools.KEYS_ENTITY,
										entity);
								MyApplication.getInstance().getCur_Activity()
										.startActivity(intent);
								}
							} else {
								
							}
						

						}

					
								
						}
					}
				
				if (!hasDomainInfo) {
//					if(GetDomainOptions.getJsonID(msg_handler)==HeartBeatEntity.ID_HeartBeat){
//						//��Ϊ������������
//						Log.d("RECEVIVEHEART","SUCESS"+"***"+msg.getData().getString(Handler_MSG));
//						HeartBeatTools.setFlagGetHeartBeat(socket);
//					}
					if (Tools.answerHelperEntity != null) {
						if (GetDomainOptions.getJsonID(msg_handler) == ID_Manager.ID_PLAYSTATE_PAUSE) {
							// ��Ϊ������������
								Tools.answerHelperEntity
										.setStatus(AnswerHelperEntity.STATUS_PAUSE);
								PlayerTools.PlayerViewTools.isplay = true;
						}
						if (GetDomainOptions.getJsonID(msg_handler) == ID_Manager.ID_PLAYSTATE_STOP) {
							// ��Ϊ������������
								Tools.answerHelperEntity
										.setStatus(AnswerHelperEntity.STATUS_STOP);
								PlayerTools.PlayerViewTools.isplay = true;
						}
						if (GetDomainOptions.getJsonID(msg_handler) == ID_Manager.ID_PLAYSTATE_RESUME) {
							// ��Ϊ������������
								Tools.answerHelperEntity
										.setStatus(AnswerHelperEntity.STATUS_START);
								PlayerTools.PlayerViewTools.isplay = false;
						}
						//�����ǰ�������ʴ��ؽ��棬֪ͨ���½���
						if(Tools.CurrentActivityName.equals(AnswerRecordActivity.ActivityName)){
							//֪ͨ����
							Message msg_answer = new Message();			
							msg_answer.what = AnswerRecordActivity.HANDLER_REFRESH_ADAPTER;;	
							((AnswerRecordActivity)MyApplication.getInstance().getCur_Activity()).handler.sendMessage(msg_answer);
						}else if(Tools.CurrentActivityName.equals(MainMenuActivity.ActivityName)){
							//֪ͨ����
							((MainMenuActivity)MyApplication.getInstance().getCur_Activity()).pv.setPlayerContent(Tools.answerHelperEntity);
						}else if(Tools.CurrentActivityName.equals(LocalMusicActivity.ActivityName)){
							//֪ͨ����
							((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).pv.setPlayerContent(Tools.answerHelperEntity);
						}
						else if(Tools.CurrentActivityName.equals(PlayingActivity.ActivityName)){
							//֪ͨ����
							((PlayingActivity)MyApplication.getInstance().getCur_Activity()).setPlayingContent(Tools.answerHelperEntity);
						}
					}
					
					
					if(GetDomainOptions.getMediaInfoDomainEntity(msg_handler, AnswerHelperEntity.DOMAIN_NAME)){
						hasDomainInfo=true;
						for (int i = 0; i < TerminalTools.adapter_infoByIP
								.size(); i++) {
							TerminalAdapterEntity cur_entity=TerminalTools.adapter_infoByIP.get(i);
							String answer_socketIP=cur_entity.getSocketIp();
							String nickname=(String) cur_entity.getGeneralEntity().getNickname();
							AnswerHelperEntity entity=AnswerHelpJsonOption.getAnswerHelper(msg_handler);
							if (answer_socketIP.length()>0&&socketIP.equals(answer_socketIP)) {
//								HashMap<String,Object> info=new HashMap<String, Object>();
//								info.put(AnswerTools.KEY_NICKNAME, nickname);
//								info.put(AnswerTools.KEY_SOCKET, answer_socket);
//								info.put(AnswerTools.KEY_ENTITY, entity);
//								AnswerTools.al_AnswerInfo.add(info);                            
								
								if(Tools.Current_SocketIP.length()>0 ){
									if(socketIP.equals(Tools.Current_SocketIP)){
										Tools.answerHelperEntity=entity;
										Log.d("PlayState",Tools.answerHelperEntity.getStatus()+"****");
										if(Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_START)){
											//���ſ�ʼ��ʱ����ȥ���
											AnswerHelperDBOptions.insertInfo(nickname, entity);
											}
//										else if(Tools.answerHelperEntity.getStatus().equals(AnswerHelperEntity.STATUS_STOP)){
//											if(PlayerTools.PlayerViewTools.PLAYLIST_URLS!=null&&PlayerTools.PlayerViewTools.PLAYLIST_URLS.length>0){
//
//												//�������
//												
//													PlayerTools.PlayerViewTools.current_index=new Random().nextInt(PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES.length-1);
//												
//												//���Ͷ�Ӧ��URL��Ϣ
//												ArrayList<String>content1=new ArrayList<String>();
//												ArrayList<PlayItemEntity>playItems=new ArrayList<PlayItemEntity>();
//												PlayItemEntity playItemEntity=new PlayItemEntity(PlayItemEntity.TYPE_WEB, PlayerTools.PlayerViewTools.PLAYLIST_URLS[PlayerTools.PlayerViewTools.current_index]);
//												playItems.add(playItemEntity);
//												String urlsend=PlayOptions.setPlay(new String[]{PlayerTools.PlayerViewTools.PLAYLIST_SONGNAMES[PlayerTools.PlayerViewTools.current_index]},playItems, PlayEntity.METHOD,true);
//														
//												content1.add(urlsend);
//												TCPTools.sendTcp(content1, Tools.Current_SocketIP,true);
//							
//												if(Tools.CurrentActivityName.equals(WebMusicPlayActivity.ActivityName)){
//													//����ڲ��Ž������л�����һ�����Ӧ��Adapter�Ľ���ҲҪ�ı�
//													((WebMusicPlayActivity)MyApplication.getInstance().getCur_Activity()).adapter.SetFocus(PlayerTools.PlayerViewTools.current_index);//���ý���
//												}else if(Tools.CurrentActivityName.equals(LocalMusicActivity.ActivityName)){
//													//����ڲ��Ž������л�����һ�����Ӧ��Adapter�Ľ���ҲҪ�ı�
//													((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).msDialog.adapter.SetFocus(PlayerTools.PlayerViewTools.current_index);//���ý���
//												}
//											}
//										}
										
										//�����ǰ�������ʴ��ؽ��棬֪ͨ���½���
										if(Tools.CurrentActivityName.equals(AnswerRecordActivity.ActivityName)){
											//֪ͨ����
											Message msg_answer = new Message();			
											msg_answer.what = AnswerRecordActivity.HANDLER_REFRESH_ADAPTER;;	
											((AnswerRecordActivity)MyApplication.getInstance().getCur_Activity()).handler.sendMessage(msg_answer);
										}else if(Tools.CurrentActivityName.equals(MainMenuActivity.ActivityName)){
											//֪ͨ����
											((MainMenuActivity)MyApplication.getInstance().getCur_Activity()).pv.setPlayerContent(Tools.answerHelperEntity);
										}else if(Tools.CurrentActivityName.equals(LocalMusicActivity.ActivityName)){
											//֪ͨ����
											((LocalMusicActivity)MyApplication.getInstance().getCur_Activity()).pv.setPlayerContent(Tools.answerHelperEntity);
										}
										else if(Tools.CurrentActivityName.equals(PlayingActivity.ActivityName)){
											//֪ͨ����
											((PlayingActivity)MyApplication.getInstance().getCur_Activity()).setPlayingContent(Tools.answerHelperEntity);
										}
									}
								}
								
								
					}
				}
					}
				}
				// ��ֻ���ڵ�ǰ�豸�²�����Ч�����ݷ��صĽ�����Ƿ���Я�����ؽ������Ϣ��������Ŀǰֻ�漰�����¼����Ĵ���
				if (!hasDomainInfo && Tools.Current_SocketIP.length()>0) {
					if(Tools.Current_SocketIP.equals(socketIP)){
						boolean isSucess = GetDomainOptions
								.isReturnTrue(msg_handler);
						
						if (GetDomainOptions.getJsonID(msg_handler) != ID_Manager.ID_ALART_RINGCHOOSE
								&&GetDomainOptions.getJsonID(msg_handler) != ID_Manager.ID_HeartBeat
										&&GetDomainOptions.getJsonID(msg_handler) != ID_Manager.ID_PLAYSTATE_STOP
										&&GetDomainOptions.getJsonID(msg_handler) != ID_Manager.ID_PLAYSTATE_RESUME
										&&GetDomainOptions.getJsonID(msg_handler) != ID_Manager.ID_PLAYSTATE_PAUSE
										) {
							if (Tools.CurrentActivityName
									.equals(WakeSetActivity.ActivityName)) {
								((WakeSetActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								if(!WakeSetActivity.isPrompt_save){
									((MyBaseActivity)MyApplication.getInstance().getCur_Activity()).onBackPressed();
								}
								
							}
							else if (Tools.CurrentActivityName
									.equals(UndisturbedActivity.ActivityName)) {
								((UndisturbedActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								if(!UndisturbedActivity.isUndisturbedOpenSave){
									((MyBaseActivity)MyApplication.getInstance().getCur_Activity())
									.onBackPressed();
								}else{
									UndisturbedActivity.refresh();
								}
								
							}
							else if (Tools.CurrentActivityName
									.equals(AwakeAlartCommentSetActivity.ActivityName)) {
								((AwakeAlartCommentSetActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								
								((MyBaseActivity)MyApplication.getInstance().getCur_Activity())
										.onBackPressed();
							} else if (Tools.CurrentActivityName
									.equals(AwakeAlartEditActivity.ActivityName)) {
								((AwakeAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								((MyBaseActivity)MyApplication.getInstance().getCur_Activity()).onBackPressed();
							} else if (Tools.CurrentActivityName
									.equals(SleepAlartEditActivity.ActivityName)) {
								((SleepAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								((MyBaseActivity)MyApplication.getInstance().getCur_Activity()).onBackPressed();
							} else if (Tools.CurrentActivityName
									.equals(DefinedAlartEditActivity.ActivityName)) {
								((DefinedAlartEditActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								((MyBaseActivity)MyApplication.getInstance().getCur_Activity()).onBackPressed();
							} else if (Tools.CurrentActivityName
									.equals(BirthdayAlartActivity.ActivityName)) {
								BirthdayAlartActivity.waitView.setPBGone();
								((MyBaseActivity)MyApplication.getInstance().getCur_Activity()).onBackPressed();
							} else if (Tools.CurrentActivityName
									.equals(RemindAlartActivity.ActivityName)) {
								((RemindAlartActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								((MyBaseActivity)MyApplication.getInstance().getCur_Activity()).onBackPressed();
							} else if (Tools.CurrentActivityName
									.equals(AlartFragmentTabActivity.ActivityName)) {
								((AlartFragmentTabActivity)MyApplication.getInstance().getCur_Activity()).waitView.setPBGone();
								((Activity)MyApplication.getInstance().getCur_Activity()).onBackPressed();
							}

						}
					}
					

				}

			}
		
			synclock.unlock();
		}
	};

//	/**
//	 * �������񲢼���
//	 */
//	public void doListen() {
//		serverSocket = null;
//		try {
//			serverSocket = new ServerSocket(TCPTools.port);
//			Log.d("TCPSERVICER", "OK2222");
//			Thread th = new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					mainThreadFlag = true;
//
//					while (mainThreadFlag) { // ������û�����Կͻ��˵����ӣ�û������һֱ����������
//						Socket client = null;
//						try {
//							client = serverSocket.accept();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						/**
//						 * �����Ϻ����ĵ�һ���¾��Ƿ���gener���󣬵õ���Ϣ
//						 */
//						new Thread(new IOSocket(handler, client)).start();
//					}
//				}
//			});
//			th.start();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
