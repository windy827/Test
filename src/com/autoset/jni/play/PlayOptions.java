package com.autoset.jni.play;

import java.util.ArrayList;

import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.presskey.PressKeyEntity;
import com.autoset.jni.statusplay.StatusPlayEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.ID_Manager;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;

public class PlayOptions {
	

	/**
	 * 
	 * @param titles
	 * @param entity
	 * @param method
	 * @param withMediaInfo ÊÇ·ñÐèÒªÖÕ¶Ë·µ»Ø²¥·ÅµÄ×´Ì¬
	 * @return
	 */
	public static String setPlay(String []titles,ArrayList<PlayItemEntity> entity,String method,boolean withMediaInfo){
		AutoSetJsonTools autoSetJsonCreatTools=new AutoSetJsonTools();
		ArrayList<Object[]>values=new ArrayList<Object[]>();
		
		for(int i=0;i<entity.size();i++){
			String content=entity.get(i).getContent();
			double type=entity.get(i).getType();
			PlayItemEntity be=new PlayItemEntity((int) type, content);
			
			Object[] temp=be.getValues();
			values.add(temp);
			
		}
		String result=autoSetJsonCreatTools.setMediaJsonObject(titles,ID_Manager.ID_ALART_RINGCHOOSE, method, PlayItemEntity.KEYS, values,withMediaInfo);
		return result;
	}
	/**
	 * ÔÝÍ£×´Ì¬µÄ×Ö·û´®
	 * @return
	 */
	public static String setStatePause(){
		 String result="{"+  MyTools.getDoubQuot("id")+":"+ID_Manager.ID_PLAYSTATE_PAUSE+","
		        + MyTools.getDoubQuot("method")+":"+MyTools.getDoubQuot("mediaPause")+"}";
		return result;
	}
	/**
	 * Í£Ö¹×´Ì¬µÄ×Ö·û´®
	 * @return
	 */
	public static String setStateStop(){
		 String result="{"+  MyTools.getDoubQuot("id")+":"+ID_Manager.ID_PLAYSTATE_STOP+","
		        + MyTools.getDoubQuot("method")+":"+MyTools.getDoubQuot("mediaStop")+"}";
		return result;
	}
	/**
	 * ¼ÌÐø²¥·Å×´Ì¬µÄ×Ö·û´®
	 * @return
	 */
	public static String setStateResume(){
		 String result="{"+  MyTools.getDoubQuot("id")+":"+ID_Manager.ID_PLAYSTATE_RESUME+","
			        + MyTools.getDoubQuot("method")+":"+MyTools.getDoubQuot("mediaResume")+"}";
		return result;
	}
	
	
	
	
	public static void reDomethod(int item,String content, double type){
		byte[]domainName= MyTools.getJNIUseByte(PlayEntity.DOMAINNAME);
		
		
		JsonAllOption.cJSONAddItemToArray(JsonAllOption.JsonType.TYPE_DOMAINJSON);
		//JsonAllOption.cJSONAddItemToArray(JsonAllOption.JsonType.TYPE_DOMAINJSON);
		//Ìí¼ÓÒ»¸ö
		
		JsonAllOption.cJSONAddNumberToArrayItem(JsonAllOption.JsonType.TYPE_DOMAINJSON, 0, MyTools.getJNIUseByte(PlayEntity.PLAY_ITEM_TYPE), type);
		JsonAllOption.cJSONAddStringToArrayItem(JsonAllOption.JsonType.TYPE_DOMAINJSON, 0, MyTools.getJNIUseByte(PlayEntity.PLAY_ITEM_CONTENT), MyTools.getJNIUseByte(content));
//		JsonAllOption.creatPlayObjectJsonObject();
//		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(PlayEntity.PLAY_ITEM_TYPE),type);
//		JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(PlayEntity.PLAY_ITEM_CONTENT),MyTools.getJNIUseByte(content));
//		JsonAllOption.cJSONAddItemToArrayWithSomething(JsonAllOption.JsonType.TYPE_PlayObjectJson,0, MyTools.getJNIUseByte(null));
//		//Ìí¼ÓµÚ¶þ¸ö
//		JsonAllOption.cJSONAddNumberToArrayItem(JsonAllOption.JsonType.TYPE_DOMAINJSON,1,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_PLAY_STATUS),Double.parseDouble(1+""));
//		JsonAllOption.creatPlayObjectJsonObject();
//		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_TYPE),0);
//		JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_CONTENT),MyTools.getJNIUseByte(setSuccess));
//		JsonAllOption.cJSONAddItemToArrayWithSomething(JsonAllOption.JsonType.TYPE_PlayObjectJson,1, MyTools.getJNIUseByte(StatusPlayEntity.STATUS_PLAYOBJECT));
		
//		JsonAllOption.creatDomainJsonObject();
//		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_DOMAINJSON,MyTools.getJNIUseByte(PlayEntity.PLAY_ITEM_TYPE),type);
//		JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_DOMAINJSON,MyTools.getJNIUseByte(PlayEntity.PLAY_ITEM_CONTENT),MyTools.getJNIUseByte(content));
		JsonAllOption.cJSONAddItemToArrayWithSomething(JsonAllOption.JsonType.TYPE_DOMAINJSON,item, MyTools.getJNIUseByte(PlayEntity.PLAY_ITEM_NAME));
		
		
	//	JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_PARAMSJSON,domainName,JsonAllOption.JsonType.TYPE_DataJson);
	}
}
