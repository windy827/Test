package com.tts168.autoset.tools.alart;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.alarm.AlarmOption;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.birthday.BirthDayOption;
import com.autoset.jni.delete.DeleteOption;
import com.autoset.jni.general.GeneralJsonOption;
import com.autoset.jni.remind.RemindEntity;
import com.autoset.jni.remind.RemindOption;
import com.autoset.json.ID_Manager;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;
import com.tts168.autoset.activity.alart.AwakeAlartEditActivity;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.alart.AlartTools.AlartListViewAdapterTools;
import com.tts168.autoset.tools.commen.ActivitySetting;
import com.tts168.autoset.tools.tcpAndudp.TCPTools;

public class MyAlartJsonOptions {
	
	
	
/**
 * 
 * @param al_info 为接收到的所有闹铃的ArrayList，包含Alart，Remind以及BirthDay三个域名
 * @param index 准备修改的闹铃信息的id数组
 *  @param faildMsg 接受失败的结果信息
 *   @param successMsg 接受成功的结果信息
 * @param optionType 操作类型  AlartTools.AlartType.OPTIONS_
 */
	public static void setAlartData(ArrayList<HashMap<String,Object>>al_info,int []index,int jsonType,String faildMsg,String successMsg,int optionType){
	
		String setContent="";
		int[]ids;//用来记录删除的ID
		ArrayList<HashMap<String, Object>> al_alart=new ArrayList<HashMap<String,Object>>();
		if(jsonType==AlartTools.AlartType.JSON_ALART_TYPE_ALART){
			//Json 域名为Alart
			ArrayList<AlarmEntity> entitys=new ArrayList<AlarmEntity>();
			
//			for(int i=0;i<al_info.size();i++){
//				HashMap<String,Object>content=al_info.get(i);
//				int type=(Integer) content.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
//				if(type==AlartTools.AlartType.JSON_ALART_TYPE_ALART){
//					AlarmEntity entity=(AlarmEntity) content.get(AlartListViewAdapterTools.KEY_ENTITY);
//					entitys.add(entity);
//				}
//				
//			}
			for(int i=0;i<index.length;i++){
				HashMap<String,Object>content=al_info.get(index[i]);
				al_alart.add(content);//当前选中的项
				AlarmEntity entity=(AlarmEntity) content.get(AlartListViewAdapterTools.KEY_ENTITY);
				entitys.add(entity);
			}
			if(optionType==AlartTools.AlartType.OPTIONS_UPDATE){
				setContent=AlarmOption.setAlarm(entitys, JsonParseOption.SET_USERDATA, faildMsg,successMsg);
			}else if(optionType==AlartTools.AlartType.OPTIONS_INSERT){
				setContent=AlarmOption.setAlarm(entitys, JsonParseOption.INSERT_USERDATA, faildMsg,successMsg);
			}else{
				ids=AlartTools.getIDsInAdapterArrayList(al_alart, jsonType);
				
				//setContent=JsonEasyOptions.deleteInfoByDomainAndIDs(AlarmEntity.DOMAIN_ALARM, ids, false, faildMsg+"", successMsg+"");
				ArrayList<int[]>list=new ArrayList<int[]>();
				list.add(ids);
				setContent=DeleteOption.deleteContent(ID_Manager.ID_DELETE_ALART, new String[]{AlarmEntity.DOMAIN_ALARM}, list, faildMsg+"", successMsg+"");
				Log.d("JSONDELETE",setContent);
			}
			
			
		}else if(jsonType==AlartTools.AlartType.JSON_ALART_TYPE_REMIND){
			//Json 域名为备忘
			ArrayList<RemindEntity> entitys=new ArrayList<RemindEntity>();
//			for(int i=0;i<al_info.size();i++){
//				HashMap<String,Object>content=al_info.get(i);
//				int type=(Integer) content.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
//				if(type==AlartTools.AlartType.JSON_ALART_TYPE_REMIND){
//					RemindEntity entity=(RemindEntity) content.get(AlartListViewAdapterTools.KEY_ENTITY);
//					entitys.add(entity);
//				}
//				
//			}
			for(int i=0;i<index.length;i++){
				HashMap<String,Object>content=al_info.get(index[i]);
				al_alart.add(content);//当前选中的项
				RemindEntity entity=(RemindEntity) content.get(AlartListViewAdapterTools.KEY_ENTITY);
				entitys.add(entity);
			}
			if(optionType==AlartTools.AlartType.OPTIONS_UPDATE){
				setContent=RemindOption.setRemind(entitys,  JsonParseOption.SET_USERDATA,  faildMsg, successMsg);
			}else if(optionType==AlartTools.AlartType.OPTIONS_INSERT){
				setContent=RemindOption.setRemind(entitys,  JsonParseOption.INSERT_USERDATA,  faildMsg, successMsg);
			}else{
				ids=AlartTools.getIDsInAdapterArrayList(al_alart, jsonType);
			//	setContent=JsonEasyOptions.deleteInfoByDomainAndIDs(RemindEntity.DOMAIN_REMIND, ids, false, faildMsg+"", successMsg+"");
				ArrayList<int[]>list=new ArrayList<int[]>();
				list.add(ids);
				setContent=DeleteOption.deleteContent(ID_Manager.ID_DELETE_ALART, new String[]{RemindEntity.DOMAIN_REMIND}, list, faildMsg+"", successMsg+"");
				Log.d("JSONDELETE",setContent);
			}
			
		
			
		}else if(jsonType==AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY){
			//Json 域名为生日
			ArrayList<BirthDayEntity> entitys=new ArrayList<BirthDayEntity>();
//			for(int i=0;i<al_info.size();i++){
//				HashMap<String,Object>content=al_info.get(i);
//				int type=(Integer) content.get(AlartTools.AlartListViewAdapterTools.KEY_TYPE);
//				if(type==AlartTools.AlartType.JSON_ALART_TYPE_BIRTHDAY){
//					BirthDayEntity entity=(BirthDayEntity) content.get(AlartListViewAdapterTools.KEY_ENTITY);
//					entitys.add(entity);
//				}
//
//			}
			for(int i=0;i<index.length;i++){
				HashMap<String,Object>content=al_info.get(index[i]);
				al_alart.add(content);//当前选中的项
				BirthDayEntity entity=(BirthDayEntity) content.get(AlartListViewAdapterTools.KEY_ENTITY);
				entitys.add(entity);
			}
			if(optionType==AlartTools.AlartType.OPTIONS_UPDATE){
				setContent=BirthDayOption.setBirthDay(entitys, JsonParseOption.SET_USERDATA,  faildMsg, successMsg);
			}else if(optionType==AlartTools.AlartType.OPTIONS_INSERT){
				setContent=BirthDayOption.setBirthDay(entitys, JsonParseOption.INSERT_USERDATA,  faildMsg, successMsg);
			}else{
				ids=AlartTools.getIDsInAdapterArrayList(al_alart, jsonType);
				//setContent=JsonEasyOptions.deleteInfoByDomainAndIDs(BirthDayEntity.DOMAIN_BIRTHDAY, ids, false, faildMsg+"", successMsg+"");
				ArrayList<int[]>list=new ArrayList<int[]>();
				list.add(ids);
				setContent=DeleteOption.deleteContent(ID_Manager.ID_DELETE_ALART, new String[]{BirthDayEntity.DOMAIN_BIRTHDAY}, list, faildMsg+"", successMsg+"");
				Log.d("JSONDELETE",setContent);
			}
			
			
		}
		
		String content=setContent;
		
		ArrayList<String>contents=new ArrayList<String>();
		contents.add(content);
		TCPTools.sendTcp(contents, Tools.Current_SocketIP,true);
	}
}
