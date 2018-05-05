package com.autoset.json;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.statusplay.StatusPlayEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.jni.wifi.WifiEntity;

/**
 * 再封装原始接口
 * @author 袁剑
 *
 */
public class JsonEasyOptions {

	/**
	 *  删除信息【目前只有闹铃和Wifi有】
	 * @param domain 删除的域名
	 * @param ids 删除的ID数组
	 * @param delAll 是否全部删除
	 * @param setFailed 设置失败的播报的内容
	 * @param setSuccess 设置成功的播报的内容
	 * @return
	 */
	 
//	public static String deleteInfoByDomainAndIDs(String domain,int[]ids,boolean delAll,String setFailed,String setSuccess){
//		String domainName=domain;
//		JsonEasyOptions.setArrayJsonHead(JsonParseOption.DELETE_USERDATA);
//		if(!delAll){
//			for(int i=0;i<ids.length;i++){
//				
//				double ss=Double.parseDouble(ids[i]+"");
//				
//				JsonAllOption.cJSONAddItemToArray(JsonAllOption.JsonType.TYPE_DOMAINJSON);
//				//添加一个
//				JsonEasyOptions.cJSONAddNumberToArrayItem(JsonAllOption.JsonType.TYPE_DOMAINJSON,i,JsonAllOption.NameAndValues.JSON_DELETEID, ss);
//				
//				JsonAllOption.cJSONAddItemToArrayWithSomething(JsonAllOption.JsonType.TYPE_PlayObjectJson,i,MyTools.getJNIUseByte(domainName));
//			}
//		}
//		
//		JsonEasyOptions.setJsonTailWithReply(domainName, setFailed, setSuccess,true);
//		
//		String result=MyTools.byteArray2String(JsonAllOption.cJSONPrint());
//	//	JsonAllOption.cJSONDelete();
//		return result;
//	}
//	
	
	/**
	 * 完整ArrayObjectJson构造的头
	 * @param setOrget
	 */
//	public static void setArrayJsonHead1(String setOrget){
//		JsonAllOption.SYNcJsonMEMInit();
//		JsonAllOption.creatMainJsonObject();
//		JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_METHOD),MyTools.getJNIUseByte(setOrget));	
//		JsonAllOption.creatParamsJsonObject();
//		JsonAllOption.creatDataJsonObject();
//		JsonAllOption.creatDomainJsonArray();
//	} 
	/*******************************************************************************
	 * 
	 * 
	 * *******************************************************************************/
	/**
	 * 完整ObjectJson构造的头
	 * @param setOrget
	 */
	public static void setObjectJsonHead(String setOrget){
		JsonAllOption.SYNcJsonMEMInit();
		JsonAllOption.creatMainJsonObject();
		JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_METHOD),MyTools.getJNIUseByte(setOrget));	
		JsonAllOption.creatParamsJsonObject();
		JsonAllOption.creatDataJsonObject();
		JsonAllOption.creatDomainJsonObject();
	} 
	
	//_______________________________________________________________________________________________
	//***********************************************************************************
	//***********************************************************************************
	//***********************************************************************************
	/**
	 *  包含状态恢复的尾
	 * @param domainName 需要挂载的域名
	 * @param setFailed 设置成功的回复内容
	 * @param setSuccess 设置失败的回复内容
	 * @param isSetOrInsertdata是否为修改或添加数据
	 */
	
	public static void setJsonTailWithReply(String domainName,String setFailed, String setSuccess,boolean isSetOrInsertdata){
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_DataJson,MyTools.getJNIUseByte(domainName),JsonAllOption.JsonType.TYPE_DOMAINJSON);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_PARAMSJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_DATA),JsonAllOption.JsonType.TYPE_DataJson);
		//状态回复
		StatusPlayOption.setStatusPlay(setFailed, setSuccess);
		int id =JsonParseOption.ID_setUserData;
		if(!isSetOrInsertdata){
			id =JsonParseOption.ID_getUserData;
		}
		//设置数据对应的ID
		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_ID),id);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_PARAMS),JsonAllOption.JsonType.TYPE_PARAMSJSON);
	} 
	/**
	 *  不包含状态恢复的尾
	 * @param domainName 需要挂载的域名
	 * @param isSetdata 是否是设置数据
	 * @param isSetOrInsertdata是否为修改或添加数据
	 */
	
	public static void setJsonTailNoReply(String domainName,boolean isSetOrInsertdata){
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_DataJson,MyTools.getJNIUseByte(domainName),JsonAllOption.JsonType.TYPE_DOMAINJSON);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_PARAMSJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_DATA),JsonAllOption.JsonType.TYPE_DOMAINJSON);
		int id =JsonParseOption.ID_setUserData;
		if(!isSetOrInsertdata){
			id =JsonParseOption.ID_getUserData;
		}
		//设置数据对应的ID
		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_ID),id);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_PARAMS),JsonAllOption.JsonType.TYPE_PARAMSJSON);
	} 
	
	/**
	 * 添加item的String型内容到ArrayObject数组
	 * @param item
	 * @param name
	 * @param values
	 */
	public static void cJSONAddStringToArrayItem(int cJsonType,int item,String name,String values){
		JsonAllOption.cJSONAddStringToArrayItem(cJsonType,item,MyTools.getJNIUseByte(name),MyTools.getJNIUseByte(values));
	}
	/**
	 * 添加item的Double型内容内容到ArrayObject数组
	 * @param item
	 * @param name
	 * @param values
	 */
	public static void cJSONAddNumberToArrayItem(int cJsonType,int item,String name,double values){
		JsonAllOption.cJSONAddNumberToArrayItem(cJsonType,item,MyTools.getJNIUseByte(name),Double.parseDouble(values+""));
	}
	//*****************************************************************************************************
	//********************************Json 解析**************************************************
	//*****************************************************************************************************
	/**
	 * 根据当前锁定的焦点，以及数组的第几个成员的键的名称返回对应的boolean结果值
	 * 
	 * @param num
	 *            第几个成员
	 * @param keyName
	 *            成员的键的名称
	 * @return
	 */
	public static boolean getFocusJsonGetJavaBoolean(int num,String keyName){
		boolean result=false;
		int temp=JsonParseOption.getFocusJsonGetBool(num, MyTools.getJNIUseByte(keyName));
		if(temp>0){
			result=true;
		}
		return result;
	}
}
