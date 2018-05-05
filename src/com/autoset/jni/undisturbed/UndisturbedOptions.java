package com.autoset.jni.undisturbed;

import org.json.JSONObject;

import com.autoset.jni.wakeup.WakeUpEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonParsorTools;
/**
 * 免扰控制的Json操作
 * @author 袁剑
 *
 */
public class UndisturbedOptions {
	/**
	 * 构造免扰控制
	 * 
	 * @param entity
	 * @param method
	 * @param setFailed
	 * @param setSuccess
	 * @return
	 */
	public static String setUndisturbed(UndisturbedEntity entity, String method,int playtype,
			String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		UndisturbedEntity ge = new UndisturbedEntity(
				entity.getOpen_undisturbed(), entity.getOpen_undisturbed_a_o(),
				entity.getUndisturbed_time_start(),entity.getUndisturbed_time_end(),entity.getUndisturbed_initVolume(),entity.getOpen_prompt());
		values = ge.getValues();
		String result = autoSetJsonCreatTools
				.setDomainJsonObjectWithOptionPlay(method,
						UndisturbedEntity.DOMAIN_NAME, UndisturbedEntity.KEYS, values,
						playtype, setFailed, setSuccess);
		return result;
	}

	/**
	 * 解析免扰控制
	 * 
	 * @param data
	 * @return
	 */
	public static UndisturbedEntity getUndisturbedEntity(String data) {
		UndisturbedEntity result = null;
		// 设置焦点,得到的Domain成员数
		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
				UndisturbedEntity.DOMAIN_NAME);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		
		double open_Undisturbed = autoSetParsorTools
				.getDouble(UndisturbedEntity.KEY_open_undisturbed);		
		String Undisturbed_time_start = autoSetParsorTools
				.getString(UndisturbedEntity.KEY_undisturbed_time_start);
		String Undisturbed_time_end = autoSetParsorTools
				.getString(UndisturbedEntity.KEY_undisturbed_time_end);
		int Undisturbed_initVolume= autoSetParsorTools
				.getInt(UndisturbedEntity.KEY_undisturbed_InitVolume);
		double open_prompt = autoSetParsorTools
				.getDouble(UndisturbedEntity.KEY_open_prompt);	
		double open_prompt_a_o = autoSetParsorTools
				.getDouble(UndisturbedEntity.KEY_open_prompt_a_o);	
		result = new UndisturbedEntity( open_Undisturbed,open_prompt_a_o,
				Undisturbed_time_start,Undisturbed_time_end,Undisturbed_initVolume,open_prompt);

		return result;
	}

}
