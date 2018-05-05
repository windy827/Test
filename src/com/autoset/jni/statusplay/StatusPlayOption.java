package com.autoset.jni.statusplay;

import java.util.ArrayList;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.wifi.WifiEntity;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;

/**
 *状态回复的实体类
 * @author 袁剑
 *
 */
public class StatusPlayOption {
/**
 * 设置状态回复
 */
	public static String setStatusPlay(String setFailed,String setSuccess ){
		byte[]domainName= MyTools.getJNIUseByte(StatusPlayEntity.DOMAIN_STATUS_PLAY);
		JsonAllOption.creatDomainJsonArray();
		JsonAllOption.cJSONAddItemToArray(JsonAllOption.JsonType.TYPE_DOMAINJSON);
		JsonAllOption.cJSONAddItemToArray(JsonAllOption.JsonType.TYPE_DOMAINJSON);
		//添加一个
		JsonAllOption.cJSONAddNumberToArrayItem(JsonAllOption.JsonType.TYPE_DOMAINJSON,0,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_PLAY_STATUS),Double.parseDouble(0+""));
		JsonAllOption.creatPlayObjectJsonObject();
		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_TYPE),0);
		JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_CONTENT),MyTools.getJNIUseByte(setFailed));
		JsonAllOption.cJSONAddItemToArrayWithSomething(JsonAllOption.JsonType.TYPE_PlayObjectJson,0, MyTools.getJNIUseByte(StatusPlayEntity.STATUS_PLAYOBJECT));
		//添加第二个
		JsonAllOption.cJSONAddNumberToArrayItem(JsonAllOption.JsonType.TYPE_DOMAINJSON,1,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_PLAY_STATUS),Double.parseDouble(1+""));
		JsonAllOption.creatPlayObjectJsonObject();
		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_TYPE),0);
		JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_PlayObjectJson,MyTools.getJNIUseByte(StatusPlayEntity.STATUS_CONTENT),MyTools.getJNIUseByte(setSuccess));
		JsonAllOption.cJSONAddItemToArrayWithSomething(JsonAllOption.JsonType.TYPE_PlayObjectJson,1, MyTools.getJNIUseByte(StatusPlayEntity.STATUS_PLAYOBJECT));
		
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_PARAMSJSON,domainName,JsonAllOption.JsonType.TYPE_DOMAINJSON);
		
		String result=MyTools.byteArray2String(JsonAllOption.cJSONPrint());
		return result;
	}
	
	
	/**
	 * 根据域名得到状态回复结果
	 * @param domainName 域名
	 * @param data
	 * @return
	 */
	public static String getStatusPlayResult(String domainName,String data){
		int total = JsonParseOption.setFocusByDomain(
				MyTools.getJNIUseByte(data),
				MyTools.getJNIUseByte(domainName));
		
		
			String result =MyTools.byteArray2String( JsonParseOption
					.getFocusJsonGetString(1, MyTools
							.getJNIUseByte(StatusPlayEntity.STATUS_CONTENT)));
		
		
		return result;
	}
}
