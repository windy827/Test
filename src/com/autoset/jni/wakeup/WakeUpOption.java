package com.autoset.jni.wakeup;

import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;

/**
 * 唤醒设置
 * 
 * @author 袁剑
 * 
 */
public class WakeUpOption {

	/**
	 * 构造唤醒
	 * 
	 * @param entity
	 * @param method
	 * @param setFailed
	 * @param setSuccess
	 * @return
	 */
	public static String setWeakUp(WakeUpEntity entity, String method,int playtype,
			String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		WakeUpEntity ge = new WakeUpEntity(entity.getOpen_weakup(),
				 entity.getTime_limit(),
				entity.getWeak_name(), entity.getTime_start(),
				entity.getTime_end());
		values = ge.getValues();
		String result = autoSetJsonCreatTools
				.setDomainJsonObjectWithOptionPlay(method,
						WakeUpEntity.DOMAIN_NAME, WakeUpEntity.KEYS, values,
						playtype, setFailed, setSuccess);
		return result;
	}

	/**
	 * 解析唤醒
	 * 
	 * @param data
	 * @return
	 */
	public static WakeUpEntity getWeakUp(String data) {
		WakeUpEntity result = null;
		// 设置焦点,得到的Domain成员数
		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
				WakeUpEntity.DOMAIN_NAME);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		double open_weakup = autoSetParsorTools
				.getDouble(WakeUpEntity.KEY_open_weakup);
		
		double time_limit = autoSetParsorTools
				.getDouble(WakeUpEntity.KEY_time_limit);

		String weak_name = autoSetParsorTools
				.getString(WakeUpEntity.KEY_weak_name);
		String time_start = autoSetParsorTools
				.getString(WakeUpEntity.KEY_time_start);
		String time_end = autoSetParsorTools.getString(WakeUpEntity.KEY_time_end);
		
			
		
		result = new WakeUpEntity(open_weakup,  time_limit,
				weak_name, time_start, time_end);

		return result;
	}

}
