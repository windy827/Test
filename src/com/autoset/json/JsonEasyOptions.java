package com.autoset.json;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.statusplay.StatusPlayEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.jni.wifi.WifiEntity;

/**
 * �ٷ�װԭʼ�ӿ�
 * @author Ԭ��
 *
 */
public class JsonEasyOptions {

	/**
	 *  ɾ����Ϣ��Ŀǰֻ�������Wifi�С�
	 * @param domain ɾ��������
	 * @param ids ɾ����ID����
	 * @param delAll �Ƿ�ȫ��ɾ��
	 * @param setFailed ����ʧ�ܵĲ���������
	 * @param setSuccess ���óɹ��Ĳ���������
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
//				//���һ��
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
	 * ����ArrayObjectJson�����ͷ
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
	 * ����ObjectJson�����ͷ
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
	 *  ����״̬�ָ���β
	 * @param domainName ��Ҫ���ص�����
	 * @param setFailed ���óɹ��Ļظ�����
	 * @param setSuccess ����ʧ�ܵĻظ�����
	 * @param isSetOrInsertdata�Ƿ�Ϊ�޸Ļ��������
	 */
	
	public static void setJsonTailWithReply(String domainName,String setFailed, String setSuccess,boolean isSetOrInsertdata){
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_DataJson,MyTools.getJNIUseByte(domainName),JsonAllOption.JsonType.TYPE_DOMAINJSON);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_PARAMSJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_DATA),JsonAllOption.JsonType.TYPE_DataJson);
		//״̬�ظ�
		StatusPlayOption.setStatusPlay(setFailed, setSuccess);
		int id =JsonParseOption.ID_setUserData;
		if(!isSetOrInsertdata){
			id =JsonParseOption.ID_getUserData;
		}
		//�������ݶ�Ӧ��ID
		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_ID),id);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_PARAMS),JsonAllOption.JsonType.TYPE_PARAMSJSON);
	} 
	/**
	 *  ������״̬�ָ���β
	 * @param domainName ��Ҫ���ص�����
	 * @param isSetdata �Ƿ�����������
	 * @param isSetOrInsertdata�Ƿ�Ϊ�޸Ļ��������
	 */
	
	public static void setJsonTailNoReply(String domainName,boolean isSetOrInsertdata){
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_DataJson,MyTools.getJNIUseByte(domainName),JsonAllOption.JsonType.TYPE_DOMAINJSON);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_PARAMSJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_DATA),JsonAllOption.JsonType.TYPE_DOMAINJSON);
		int id =JsonParseOption.ID_setUserData;
		if(!isSetOrInsertdata){
			id =JsonParseOption.ID_getUserData;
		}
		//�������ݶ�Ӧ��ID
		JsonAllOption.cJSONAddNumberToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_ID),id);
		JsonAllOption.cJSONAddItemToObject(JsonAllOption.JsonType.TYPE_MAINJSON,MyTools.getJNIUseByte(JsonAllOption.NameAndValues.JSON_PARAMS),JsonAllOption.JsonType.TYPE_PARAMSJSON);
	} 
	
	/**
	 * ���item��String�����ݵ�ArrayObject����
	 * @param item
	 * @param name
	 * @param values
	 */
	public static void cJSONAddStringToArrayItem(int cJsonType,int item,String name,String values){
		JsonAllOption.cJSONAddStringToArrayItem(cJsonType,item,MyTools.getJNIUseByte(name),MyTools.getJNIUseByte(values));
	}
	/**
	 * ���item��Double���������ݵ�ArrayObject����
	 * @param item
	 * @param name
	 * @param values
	 */
	public static void cJSONAddNumberToArrayItem(int cJsonType,int item,String name,double values){
		JsonAllOption.cJSONAddNumberToArrayItem(cJsonType,item,MyTools.getJNIUseByte(name),Double.parseDouble(values+""));
	}
	//*****************************************************************************************************
	//********************************Json ����**************************************************
	//*****************************************************************************************************
	/**
	 * ���ݵ�ǰ�����Ľ��㣬�Լ�����ĵڼ�����Ա�ļ������Ʒ��ض�Ӧ��boolean���ֵ
	 * 
	 * @param num
	 *            �ڼ�����Ա
	 * @param keyName
	 *            ��Ա�ļ�������
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
