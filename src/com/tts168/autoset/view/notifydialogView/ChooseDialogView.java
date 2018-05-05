package com.tts168.autoset.view.notifydialogView;

import java.util.ArrayList;
import java.util.HashMap;

import com.larkiv.larksmart7618.R;
import com.niftydialogeffects.NiftyDialogBuilderChoose;
import com.tts168.autoset.tools.SendDataTools;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.NotifyDialog;
import com.tts168.autoset.tools.commen.SharedPrefenceSetTools;
import com.tts168.autoset.tools.entity.terminal.TerminalAdapterEntity;
import com.tts168.autoset.tools.highcset.cityset.CitySetTools;
import com.tts168.autoset.tools.highcset.cityset.ProviceAndCityResource;
import com.tts168.autoset.tools.highcset.presskey.LaughContent;
import com.tts168.autoset.tools.others.TimeSetContent;
import com.tts168.autoset.tools.others.time.TimeContent;
import com.tts168.autoset.tools.others.wifi.WifiTool;
import com.tts168.autoset.view.MyWifiView;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ChooseDialogView {
	
	/**
	 * 
	 * @param inflater
	 * @param type 标记显示的是省份还是市PRO_TAG,CITY_TAG
	 * @param bt_City_or_Pro另外一个显示省或者市的按钮
	 * @return sp_TAG 包括SHORT,LONG，DOUBLE
	 */
		public static  View ProAndCityChooseview(LayoutInflater inflater,final int type,final Button bt_v,final TextView tv,final Button bt_City_or_Pro,final String sp_TAG) {
			
			 View view1=inflater.inflate(R.layout.choose_custom_view, null); 
			 ListView lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
			String []from=new String[]{"name"};
			int []to=new int[]{R.id.tv_select};
			 ArrayList<HashMap<String, String>> temp=new ArrayList<HashMap<String,String>>();
			 //省份的视图
			 if(type==CitySetTools.PRO_TAG){
				 temp=ProviceAndCityResource.Provicedata();
			}
			 //市的视图
			else{
				temp=ProviceAndCityResource.Citydata();
			}
			 final ArrayList<HashMap<String, String>> al=temp;
			SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
		lv_choose.setAdapter(sa);
		lv_choose.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(type==CitySetTools.PRO_TAG){
					CitySetTools.PROVICE=al.get(arg2).get("name").toString();
					CitySetTools.City_index=arg2;
					CitySetTools.CITY=ProviceAndCityResource.City[CitySetTools.City_index][0];
					bt_City_or_Pro.setText(ProviceAndCityResource.City[CitySetTools.City_index][0]);
				}else{
					CitySetTools.CITY=al.get(arg2).get("name").toString();
					bt_City_or_Pro.setText(ProviceAndCityResource.getProviceBaseOnCityName(CitySetTools.CITY));
				}
				  
		            	 if(type==CitySetTools.PRO_TAG){
		            	 bt_v.setText(CitySetTools.PROVICE);
		            	 tv.setText(CitySetTools.CITY);
		            	 }
		            	 else if(type==CitySetTools.CITY_TAG){
			            	 bt_v.setText(CitySetTools.CITY);
			            	 tv.setText(CitySetTools.CITY);
			            	 }		            		      
				NotifyDialog.dialogBuilderChoose.dismiss();
				NotifyDialog.dialogBuilderChoose=null;
			}
			
		});
	
			return view1;
			}
/**
 * 快捷城市设置
 */
/**
 * 
 * @param inflater
 * @param tv 显示城市的TextView
 * @return
 */
		
		public static  View QuickProAndCityChooseview(LayoutInflater inflater,final TextView tv) {
			
			View view1=inflater.inflate(R.layout.choose_custom_view, null); 
			ListView  lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
			String []from=new String[]{"name"};
			int []to=new int[]{R.id.tv_select};
			 final ArrayList<HashMap<String, String>> al=ProviceAndCityResource.canUseCitydata();

			SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
		lv_choose.setAdapter(sa);
		lv_choose.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				CitySetTools.CITY=al.get(arg2).get("name").toString();
			            	 tv.setText(CitySetTools.CITY);

			            	
				NotifyDialog.dialogBuilderChoose.dismiss();
				NotifyDialog.dialogBuilderChoose=null;
			}
			
		});
	
			return view1;
			}
		
		
		
		
				/**
				 * 
				 *点击发送FSK按钮时弹出来的使用提示对话框
				 * @param inflater
				 *  
				 * @return
				 */			
				public static  View FSKAlartView(LayoutInflater inflater) {
					
					 final ImageView iv_anim;
						AnimationDrawable anim;
					
						// 引入窗口配置文件
						View view = inflater.inflate(R.layout.wifi_connection_dialog, null);
						// 创建PopupWindow对象
						final PopupWindow pop = new PopupWindow(view,
								Tools.getScreenWidth(MyApplication.getInstance().getCur_Activity()) * 3 / 4,
								Tools.getScreenHeight(MyApplication.getInstance().getCur_Activity()) / 3, true);
						iv_anim = (ImageView) view.findViewById(R.id.iv_wifi_connect);

						Object ob;
						//播放动画
						
						ob = iv_anim.getBackground();
						anim = (AnimationDrawable) ob;
						anim.stop();//停止播放
						anim.start();//开始播放
					
			
					return view;
					}
				//**************************************************************************************
				//**************************************************************************************
				//**************************************************************************************
					//**************************************************************************************
						//**************************************************************************************
						//**************************************************************************************
						//**************************************************************************************
						//**************************************************************************************
		/**
		 * 睡眠时长设置
		 * @param inflater
		 * @param tv 睡眠时长的TextView
		 * @return
		 */
				
				public static  View SleepTimeChooseview(LayoutInflater inflater,final TextView tv,ArrayList<HashMap<String, String>> al_list) {
					
					View view1=inflater.inflate(R.layout.choose_custom_view, null); 
					ListView   lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
					String []from=new String[]{"name"};
					int []to=new int[]{R.id.tv_select};
					 final ArrayList<HashMap<String, String>>al=al_list;
					SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
				lv_choose.setAdapter(sa);
				lv_choose.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						String text=al.get(arg2).get(TimeSetContent.KEY_NAME);
						int time=Integer.parseInt(text.split(TimeSetContent.SPLIT_CHAR)[0]);
						SharedPrefenceSetTools.ed.putInt(SharedPrefenceSetTools.Sleep,
								time);
						SharedPrefenceSetTools.ed.commit();
						

						NotifyDialog.dialogBuilderChoose.dismiss();
						NotifyDialog.dialogBuilderChoose=null;
					}
					
				});
			
					return view1;
					}
		
				
				/**
				 * 设备切换设置
				 * @param inflater
				 * @param tv 显示当前设备名的TextView
				 * @return
				 */
						
						public static  View DeviceChooseview(LayoutInflater inflater,final TextView tv,final ArrayList<TerminalAdapterEntity> al_info) {
							
							View view1=inflater.inflate(R.layout.choose_custom_view, null); 
							 ArrayList<HashMap<String, Object>> al_list_temp=new ArrayList<HashMap<String,Object>>();
							 for(TerminalAdapterEntity entity:al_info){
								 HashMap hm=new HashMap<String, String>();
								 hm.put("Rec_Device_name", entity.getGeneralEntity().getNickname());
								 al_list_temp.add(hm);
							 }
							 final ArrayList<HashMap<String, Object>> al_list=al_list_temp;
							 ListView lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
							String []from=new String[]{"Rec_Device_name"};
							int []to=new int[]{R.id.tv_select};
							
							SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al_list, R.layout.choose_dialogselect_item, from, to);
						lv_choose.setAdapter(sa);
						lv_choose.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
								// TODO Auto-generated method stub
								String text=(String) al_list.get(arg2).get(TimeSetContent.KEY_NAME);
//								int time=Integer.parseInt(text.split(TimeSetContent.SPLIT_CHAR)[0]);
//								SharedPrefenceSetTools.ed.putInt(SharedPrefenceSetTools.Sleep,
//										time);
//								SharedPrefenceSetTools.ed.commit();
								tv.setText(text);

								NotifyDialog.dialogBuilderChoose.dismiss();
								NotifyDialog.dialogBuilderChoose=null;
							}
							
						});
					
							return view1;
							}
		/**
		 * 
		 * @param inflater
		 * 点击笑话键相应按钮弹出来的对话框
		 * @return
		 */
			public static  View Laughview(LayoutInflater inflater,final TextView tv,final String sp_TAG) {
				
				View view1=inflater.inflate(R.layout.choose_custom_view, null); 
				 ListView  lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
				String []from=new String[]{"name"};
				int []to=new int[]{R.id.tv_select};
				 final ArrayList<HashMap<String, String>> al=LaughContent.getData();
				SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
			lv_choose.setAdapter(sa);
			lv_choose.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
				
				    tv.setText(al.get(arg2).get("name").toString());
					   if(sp_TAG==Tools.SP_TAG_Short){
						   
						   Tools.Short_Laugh=al.get(arg2).get("name").toString();
					   }else if(sp_TAG==Tools.SP_TAG_Long){
						 
						   Tools.Double_Laugh=al.get(arg2).get("name").toString();
					   }else{
						  
						   Tools.Long_Laugh=al.get(arg2).get("name").toString();
					   }
					 
						
					NotifyDialog.dialogBuilderChoose.dismiss();
					NotifyDialog.dialogBuilderChoose=null;
				}
				
			});
		
				return view1;
				}
			
			/**
			 * 
			 * @param inflater
			 * 点击提前天数相应按钮弹出来的对话框
			 * @param type 是来自闹铃的设置还是来自节日节气的设置Tools.TYPE_SET_ALART,Tools.TYPE_SET_FESTIVE
			 * @return
			 */
				public static  View AheadDaysView(LayoutInflater inflater,final TextView tv,final String sp_TAG,final int type) {
					
					View view1=inflater.inflate(R.layout.choose_custom_view, null); 
					 ListView lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
					String []from=new String[]{"name"};
					int []to=new int[]{R.id.tv_select};
					 final ArrayList<HashMap<String, String>> al=TimeContent.getData();
					SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
				lv_choose.setAdapter(sa);
				lv_choose.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						String days=al.get(arg2).get("name").toString();
//						
//							SharedPrefenceSetTools.ed.putInt(sp_TAG, Integer.parseInt(days.split("天")[0]));
//							SharedPrefenceSetTools.ed.commit();
						
					
						
						tv.setText(days);
						 
							
						NotifyDialog.dialogBuilderChoose.dismiss();
						NotifyDialog.dialogBuilderChoose=null;
					}
					
				});
			
					return view1;
					}
			
				
				/**
				 * 整体设置的内容
				 * @param inflater
				 * key 为Tools.SET_WHOLE_KEY
				 * @param type 是来自闹铃的设置还是来自节日节气的设置Tools.TYPE_SET_ALART,Tools.TYPE_SET_FESTIVE
				 * @return
				 */
					public static  View WholeSetView(LayoutInflater inflater,final Button bt,final String sp_TAG,final ArrayList<HashMap<String, String>>content) {
						
						View view1=inflater.inflate(R.layout.choose_custom_view, null); 
						 ListView lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
						String []from=new String[]{Tools.SET_WHOLE_KEY};
						int []to=new int[]{R.id.tv_select};
						 final ArrayList<HashMap<String, String>>  al=content;
					
						SimpleAdapter sa=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), al, R.layout.choose_dialogselect_item, from, to);
						lv_choose.setAdapter(sa);
						lv_choose.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
							// TODO Auto-generated method stub
							String content=al.get(arg2).get(Tools.SET_WHOLE_KEY).toString();
							
//							if(sp_TAG.equals(SharedPrefenceSetTools.enunciators)){
//								
//								SharedPrefenceSetTools.ed.putString(sp_TAG, content);
//							}							
//							SharedPrefenceSetTools.ed.commit();							
							bt.setText(content);	
							SendDataTools.ANOUNCER=content;//给准备发送的发音人赋值
							NotifyDialog.dialogBuilderChoose.dismiss();
							NotifyDialog.dialogBuilderChoose=null;
						}
						
					});
				
						return view1;
						}
				
			/**
			 * 
			 * @param inflater
			 * 点击SSID相应按钮弹出来的对话框
			 * @return
			 */
			public static SimpleAdapter sa_SSID;
				public static  View SSIDview(LayoutInflater inflater,final Button bt) {
					
					View view1=inflater.inflate(R.layout.choose_custom_view, null); 
					ListView lv_choose=(ListView) view1.findViewById(R.id.lv_choose);
					 String []from=new String[]{"SSID"};
						int []to=new int[]{R.id.tv_select};
								
						ChooseDialogView.sa_SSID=new SimpleAdapter(MyApplication.getInstance().getCur_Activity(), MyWifiView.al_SSID, R.layout.choose_dialogselect_item, from, to);
				lv_choose.setAdapter(sa_SSID);
				lv_choose.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
					String ssid=MyWifiView.al_SSID.get(arg2).get("SSID").toString();
						bt.setText(ssid);	
					//	MyWifiView.et_inputSSID.setText(ssid);
						/**
						 * 如果这个SSID在数据库里面有记录，则读取出来它的PassWord在MyWifiView.et_password上
						 */
						if(MyWifiView.getWifiSet_info(ssid).size()>0){
							new MyWifiView().setPasswordContent((String)MyWifiView.getWifiSet_info(ssid).get(0).get("PASSWORD"));
							//MyWifiView.et_password.setText((CharSequence) MyWifiView.getWifiSet_info(ssid).get(0).get("PASSWORD"));
					}
						else{
							new MyWifiView().setPasswordContent("");
							//MyWifiView.et_password.setText("");
						}
					
						
						NotifyDialog.dialogBuilderChoose.dismiss();
						NotifyDialog.dialogBuilderChoose=null;
					
					}
					
				});
			
					return view1;
					}

}
