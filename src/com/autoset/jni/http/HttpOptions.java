package com.autoset.jni.http;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.MediaStore.Audio;
import android.util.Log;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.udp.UDPNetEntity;
import com.autoset.jni.wifi.WifiEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;

public class HttpOptions {

	/**
	 * ���͵�������[params�������������Ա�Ҷ�ΪJsonObject]
	 * @param entity ��ͨʵ����
	 * @param donaminName ���������
	 * @return
	 */
	public static String sendRequestJson(HttpEntity entity,String donaminName) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		String result = autoSetJsonCreatTools.setHttpJsonObject(entity.getId(),
				entity.getMethod(),donaminName, entity.getParamsidValues());
		return result;
	}
	/**
	 * ������������[params�������������Ա���е�ΪJsonArray]
	 * @param entity �������ʵ����
	 * @param donaminName ���������
	 * @return
	 */
	public static String sendRequestJsonArray(HttpEntity entity,String donaminName) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		String result = autoSetJsonCreatTools.setHttpJsonArray(entity.getId(),
				entity.getMethod(), donaminName,entity.getParamsidsValues());
		Log.d("HttpOptions","sendParamsAllRequestJson59"+result);
		return result;
	}
	
	
	/**
	 * ���͵�������[params������������ԱΪJsonObject��JsonArray����]
	 * @param entity ��ͨʵ����
	 * @param donaminName ���������
	 * @return
	 */
	public static String sendParamsAllRequestJson(HttpEntity entity,String []donaminName) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		String result = autoSetJsonCreatTools.setParamsAllHttpJson(entity.getId(),
				entity.getMethod(),donaminName, entity.getAl_paramsidValues());
		Log.d("HttpOptions","sendParamsAllRequestJson59");
		return result;
	}
	/**
	 * �õ���������
	 * @param data
	 * @param isGetMainDomain �Ƿ�õ���Ŀ¼����falseΪ�õ���һ��Ŀ¼����
	 * @return
	 */
		public static ArrayList<AudioEntity>  getAudioEntity(String data,boolean isGetMainDomain) {
			ArrayList<AudioEntity> result= new ArrayList<AudioEntity>();
			try {
				JSONObject object = new JSONObject(data);
				if(object!=null){
					boolean has=object.has("result");
					if(has){
						JSONObject res = object.getJSONObject("result");
						boolean hasaudio=res.has("audio");
						if(hasaudio){
							JSONArray array=res.getJSONArray("audio");
							int total = array.length();
							for (int i = 0; i < total; i++) {
								JSONObject child=(JSONObject) array.get(i);
								AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(child);
								double duration=autoSetParsorTools.getDouble(AudioEntity.DURATION);
								String icon=autoSetParsorTools.getString(AudioEntity.ICON);
								String title=autoSetParsorTools.getString(AudioEntity.TITLE);
								String url=autoSetParsorTools.getString(AudioEntity.URL);
								AudioEntity temp=new AudioEntity(duration, icon, title, url);
								result.add(temp);
							}
						}
					}
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	/**
	 * �õ���������
	 * @param data
	 * @param isGetMainDomain �Ƿ�õ���Ŀ¼����falseΪ�õ���һ��Ŀ¼����
	 * @return
	 */
		public static ArrayList<AudioCategoryEntity>  getAudioCategoryEntity(String data,boolean isGetMainDomain) {
			ArrayList<AudioCategoryEntity> result= new ArrayList<AudioCategoryEntity>();
			int total =0;
			if(isGetMainDomain){
				total = JsonParseOption.setFocusByDomain(
						MyTools.getJNIUseByte(data),
						MyTools.getJNIUseByte(AudioCategoryEntity.DOMAINNAME));
			}else{
				total = JsonParseOption.setFocusByDomain(
						MyTools.getJNIUseByte(data),
						MyTools.getJNIUseByte(AudioCategoryEntity.SUBDOMAINNAME));
			}
			// ���ý���,�õ���Domain��Ա��
			for(int i=0;i<total;i++){
			String categoryid =MyTools.byteArray2String( JsonParseOption
					.getFocusJsonGetString(i+1, MyTools
							.getJNIUseByte(AudioCategoryEntity.Name_AudioCategory)));
			String title = MyTools.byteArray2String(JsonParseOption
					.getFocusJsonGetString(i+1, MyTools
							.getJNIUseByte(AudioCategoryEntity.TITLE)));
			boolean hassub=JsonEasyOptions.getFocusJsonGetJavaBoolean(i+1, AudioCategoryEntity.HASSUB);
			
			AudioCategoryEntity temp=new AudioCategoryEntity(categoryid, hassub, title);
			result.add(temp);
			}
			return result;
		}
		
		
		/**
		 * �õ����������ݣ������Ƿ�������⣬�Ƿ��������һ�������е�ǰ��һ����ID��
		 * 
		 * @param data
		 * @return
		 */
		public static CategoryEntity getCategoryEntity(String data) {
			CategoryEntity result = null;
			try {
				JSONObject object = new JSONObject(data);
				if (object != null) {
					boolean has = object.has("result");
					if (has) {
						JSONObject res = object.getJSONObject("result");
						boolean hasaudio = res.has("category");
						if (hasaudio) {
							JSONObject child = res.getJSONObject("category");
							AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(child);
							String categoryid = autoSetParsorTools
									.getString(CategoryEntity.AudioCategoryID);
							String title = autoSetParsorTools.getString(CategoryEntity.TITLE);
							String icon = autoSetParsorTools.getString(AudioEntity.ICON);
							boolean hassub = autoSetParsorTools
									.getBoolean(CategoryEntity.HASSUB);
							result = new CategoryEntity(categoryid, icon, hassub, title);

						}
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * �õ���һ�����б����ݣ������Ƿ�������⣬�Ƿ��������һ�������е�ǰ��һ����ID��
		 * 
		 * @param data
		 * @return
		 */
		public static ArrayList<CategoryEntity> getSubCategoryEntity(String data) {
			ArrayList<CategoryEntity> result = new ArrayList<CategoryEntity>();
			try {
				JSONObject object = new JSONObject(data);
				if (object != null) {
					boolean has = object.has("result");
					if (has) {
						JSONObject res = object.getJSONObject("result");
						boolean hasaudio = res.has("subCategory");
						if (hasaudio) {
							JSONArray array = res.getJSONArray("subCategory");
							int total = array.length();
							for (int i = 0; i < total; i++) {
								JSONObject child = (JSONObject) array.get(i);
								String categoryid = child
										.getString(CategoryEntity.AudioCategoryID);
								String icon = child.getString(AudioEntity.ICON);
								String title = child
										.getString(CategoryEntity.TITLE);
								boolean hassub = child
										.getBoolean(CategoryEntity.HASSUB);
								CategoryEntity entity = new CategoryEntity(categoryid, icon, hassub, title);
								result.add(entity);
							}
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
