package com.autoset.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * ����Json�Ĺ�����
 * @author Ԭ��
 *
 */
public class JSonCreatTools {
	/**
	 * ��JsonArray��ֹ��ͨ����,���� [true,12,"trt"]
	 * 
	 * @param list
	 *            Ҫ��ŵ�����
	 * @return
	 */
	public static JSONArray createCommentJsonArray(Object[] list) {
		JSONArray result = new JSONArray();

		for (int i = 0; i < list.length; i++) {
			result.put(list[i]);
		}

		return result;
	}

	/**
	 * ��JsonArray����Ƕ��JsonObject�ṹ,���� [{ "name","haha" },{ "name","hrer" }]��[{ "id",1 },{ "id",2 }]
	 * 
	 * @param list size=2
	 *            Я����Ҫ��ŵ����ݣ�����name�����value����
	 * @return
	 */
	public static JSONArray createJsonObjectInJsonArray(
			ArrayList<HashMap<String, Object[]>> list) {
		JSONArray result = new JSONArray();
		for (int j = 0; j < list.size(); j++) {
			HashMap<String, Object[]> keysAndvalues = list.get(j);
			JSONObject temp = creatCommentJsonObject(keysAndvalues);
			result.put(temp);
		}

		return result;
	}
	
	public static JSONArray createJsonObjectInJsonArrayInt(
			ArrayList<HashMap<String, Object[]>> list) {
		JSONArray result = new JSONArray();
		for (int j = 0; j < list.size(); j++) {
			HashMap<String, Object[]> keysAndvalues = list.get(j);
			JSONObject temp = creatCommentJsonObjectInt(keysAndvalues);
			result.put(temp);
		}

		return result;
	}
	/**��JSonArray�����ÿһ��Item�������JsonObject,���� 
	 * [{ "name",
	 * "playObject":{
	 * "id",1
	 * }
	 * 		 }]
	 * @param fatherJsonObject ��JsonObject
	 * @param chidJsonName  ׼�������JsonObject������
	 * @param list
	 *            Я����Ҫ��ŵ����ݣ�����name�����value����
	 * @return
	 */
	public static void createJsonObjectsInJSONArray(JSONArray fatherJsonArray,String chidJsonName,
			ArrayList<HashMap<String, Object[]>> list) {
		JSONObject temp = null;
		for (int j = 0; j < list.size(); j++) {
			try {
			JSONObject fatherJsonObject=(JSONObject) fatherJsonArray.get(j);
			HashMap<String, Object[]> keysAndvalues = list.get(j);
			temp = creatCommentJsonObject(keysAndvalues);
			
				fatherJsonObject.put(chidJsonName, temp);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
	/**
	 * ������ͨ��JsonObject�ṹ
	 * 
	 * @param list
	 *            ������ֵ��;�봴��һ���յ�JsonObject���Դ�nullֵ
	 * @return
	 */
	public static JSONObject creatCommentJsonObject(
			HashMap<String, Object[]> list) {
		JSONObject result = new JSONObject();
		if (list != null) {
			String[] name = (String[]) list.get("key");
			Object[] values = list.get("values");
			for (int i = 0; i < name.length; i++) {
				try {
					result.put(name[i], values[i]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	public static JSONObject creatCommentJsonObjectInt(
			HashMap<String, Object[]> list) {
		JSONObject result = new JSONObject();
		if (list != null) {
			String[] name = (String[]) list.get("key");
			Object[] values = list.get("values");
			for (int i = 0; i < name.length; i++) {
				try {
					result.put(name[i], values[i]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	/**
	 * ����һһ��Ӧ�ļ�ֵ�����飬��װ��һ��HashMap����
	 * 
	 * @param keys
	 * @param values
	 * @return
	 */
	public static HashMap<String, Object[]> getHashMapContainKeysAndValues(
			String[] keys, Object[] values) {
		HashMap<String, Object[]> result = new HashMap<String, Object[]>();
		result.put("key", keys);
		result.put("values", values);
		return result;
	}
}
