package com.autoset.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.answer.AnswerHelperEntity;

public class JsonParsorTools {
	
	
	/**
	 * ���ݵõ�����������Json�õ�������Ӧ��JsonArray
	 * @param data
	 * @param domainName
	 * @return
	 */
	public static JSONArray getDomainJsonArray(String data,String domainName){
		JSONArray array =new JSONArray();
		try {
			JSONObject object = new JSONObject(data);
			if(object!=null){
//		JSONTokener jsonParser = new JSONTokener(data);
//		// ��ʱ��δ��ȡ�κ�json�ı���ֱ�Ӷ�ȡ����һ��JSONObject����
//		JSONObject main = (JSONObject) jsonParser.nextValue();
//		
//		array = (JSONArray) main
//					.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_RESULT)
//					.getJSONArray(domainName);
				boolean has=object.has(AutoSetJsonTools.NameAndValues.JSON_RESULT);
				if(has){
					JSONObject res = object.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_RESULT);
					boolean hasaudio=res.has(domainName);
					if(hasaudio){
						array = (JSONArray) res.getJSONArray(domainName);
						
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	/**
	 * ���ݵõ�����������Json�õ�������Ӧ��JsonObject
	 * @param data
	 * @param domainName
	 * @return
	 */
	public static JSONObject getDomainJsonObject(String data,String domainName){
		JSONObject result =new JSONObject();
		try {
			JSONObject object = new JSONObject(data);
			if(object!=null){
//				JSONTokener jsonParser = new JSONTokener(data);
//				// ��ʱ��δ��ȡ�κ�json�ı���ֱ�Ӷ�ȡ����һ��JSONObject����
//				JSONObject main = (JSONObject) jsonParser.nextValue();
//				
//				object = (JSONObject) main
//							.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_RESULT)
//							.getJSONObject(domainName);
				boolean has=object.has(AutoSetJsonTools.NameAndValues.JSON_RESULT);
				if(has){
					JSONObject res = object.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_RESULT);
					boolean hasaudio=res.has(domainName);
					if(hasaudio){
						result = (JSONObject) res.getJSONObject(domainName);
						
					}
				}
			}
//		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	/**
	 * ���������ʴ��ء�
	 * ���ݵõ�����������Json�õ�������Ӧ��JsonObject
	 * @param data
	 * @param domainName
	 * @return
	 */
	public static JSONObject getDomainByEventJsonObject(String data,String domainName){
		JSONObject result =new JSONObject();
		try {
			JSONObject object = new JSONObject(data);
			if(object!=null){

				boolean has=object.has(AutoSetJsonTools.NameAndValues.JSON_PARAMS);
				if(has){
					JSONObject parmas = object.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_PARAMS);
					JSONObject res = parmas.getJSONObject(AnswerHelperEntity.PARAMAS_VALUES);
					boolean hasaudio=res.has(domainName);
					if(hasaudio){
						result = (JSONObject) res.getJSONObject(domainName);
						
					}
				}
			}	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
