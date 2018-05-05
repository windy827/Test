package com.autoset.jni.getdomain;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.json.AutoSetJsonTools;



/**
 * 获取域名操作类
 * @author 袁剑
 *
 */
public class GetDomainOptions {
	/**
	 * Json数据中是否含有fsk配置上的信息返回
	 * @param data
	 * @param domainName
	 * @return
	 */
	public static boolean isFSKSetSuccess(String data){
		boolean result=false;
		
		try {
			JSONObject object = new JSONObject(data);
			if(object!=null){
				result=object.has("fsk");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Json数据的ID值
	 * @param data
	 * @param domainName
	 * @return
	 */
	public static int getJsonID(String data){
		int id=0;
		
		try {
			JSONObject object = new JSONObject(data);
			if(object!=null){
				id=object.getInt("id");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * Json数据的是否正确返回
	 * @param data
	 * @param domainName
	 * @return
	 */
	public static boolean isReturnTrue(String data){
		boolean hasReturn=false;
		
		try {
			JSONObject object = new JSONObject(data);
			if(object!=null){
				boolean has=object.has(AutoSetJsonTools.NameAndValues.JSON_RESULT);
				if(has){
					hasReturn=object.getBoolean(AutoSetJsonTools.NameAndValues.JSON_RESULT);
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasReturn;
	}
	/**
	 * 查看是否包含通用的域名
	 * @param data
	 * @param domainName 查看该域名是否存在于data中
	 * @return
	 */
		public static boolean  getDomainEntity(String data,String domainName) {
			boolean isexist=false;
			try {
				JSONObject object = new JSONObject(data);
				if(object!=null){
					boolean has=object.has(AutoSetJsonTools.NameAndValues.JSON_RESULT);
					if(has){
						JSONObject res = object.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_RESULT);
						boolean hasaudio=res.has(domainName);
						if(hasaudio){
							isexist=true;
							
						}
					}
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return isexist;
		}
		
		
		/**
		 * 查看是否包含域名【问答域名】
		 * @param data
		 * @param domainName 查看该域名是否存在于data中
		 * @return
		 */
			public static boolean  getMediaInfoDomainEntity(String data,String domainName) {
				boolean isexist=false;
				try {
					JSONObject object = new JSONObject(data);
					if(object!=null){
						boolean has=object.has(AutoSetJsonTools.NameAndValues.JSON_PARAMS);
						if(has){
							JSONObject paramas = object.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_PARAMS);
							JSONObject res = paramas.getJSONObject(AnswerHelperEntity.PARAMAS_VALUES);
							boolean hasaudio=res.has(domainName);
							if(hasaudio){
								isexist=true;
								
							}
						}
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return isexist;
			}
}
