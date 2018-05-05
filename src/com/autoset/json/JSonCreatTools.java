package com.autoset.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 创建Json的工具类
 * @author 袁剑
 *
 */
public class JSonCreatTools {
	/**
	 * 在JsonArray防止普通内容,形如 [true,12,"trt"]
	 * 
	 * @param list
	 *            要存放的内容
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
	 * 在JsonArray里面嵌套JsonObject结构,形如 [{ "name","haha" },{ "name","hrer" }]或[{ "id",1 },{ "id",2 }]
	 * 
	 * @param list size=2
	 *            携带有要存放的内容，包括name数组和value数组
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
	/**往JSonArray里面的每一项Item里面添加JsonObject,形如 
	 * [{ "name",
	 * "playObject":{
	 * "id",1
	 * }
	 * 		 }]
	 * @param fatherJsonObject 父JsonObject
	 * @param chidJsonName  准备插入的JsonObject的名称
	 * @param list
	 *            携带有要存放的内容，包括name数组和value数组
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
	 * 创建普通的JsonObject结构
	 * 
	 * @param list
	 *            包含键值对;想创建一个空的JsonObject可以传null值
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
	 * 根据一一对应的键值对数组，包装成一个HashMap对象
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
