package com.autoset.jni.getdomain;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.json.AutoSetJsonTools;



/**
 * ��ȡ����������
 * @author Ԭ��
 *
 */
public class GetDomainOptions {
	/**
	 * Json�������Ƿ���fsk�����ϵ���Ϣ����
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
	 * Json���ݵ�IDֵ
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
	 * Json���ݵ��Ƿ���ȷ����
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
	 * �鿴�Ƿ����ͨ�õ�����
	 * @param data
	 * @param domainName �鿴�������Ƿ������data��
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
		 * �鿴�Ƿ�����������ʴ�������
		 * @param data
		 * @param domainName �鿴�������Ƿ������data��
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
