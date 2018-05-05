package com.autoset.jni.getupset;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.statusplay.StatusPlayEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JSonCreatTools;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;
import com.autoset.json.MyTools;

/**
 * 每日闹铃的实体类
 * 
 * @author 袁剑
 * 
 */
public class GetUpSetOption {
	/**
	 * 设置每日闹铃
	 * 
	 * @param setOrget
	 *            JsonParseOption.INSERT_USERDATA或JsonParseOption.SET_USERDATA
	 */

	public static String setGetUpSet(GetUpSetEntity entity,
			String setOrget, String setFailed, String setSuccess) {

		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
	
		

			double ring_type = entity.getRing_type();
			String ring_path = entity.getRing_path();
			double play_time = entity.getPlay_time();

			double delay_time = entity.getDelay_time();
			double delay_num = entity.getDelay_num();
			double message_times = entity.getMessage_times();
			double message_open = entity.getMessage_open();
			double will_do = entity.getWill_do();

			GetUpSetEntity ae = new GetUpSetEntity(ring_type, ring_path,
					play_time, delay_num, delay_time, message_times,
					message_open, will_do);
			Object[] values = ae.getValues();
			
		
		String result = autoSetJsonCreatTools.setDomainJsonObjectWithOptionPlay(
				setOrget, GetUpSetEntity.DOMAIN_NAME, GetUpSetEntity.KEYS,
				values, PlayEntity.TYPE_TTS, setFailed + "", setSuccess + "");

		return result;
	}

	/**
	 * 得到每日闹铃
	 * 
	 * @param data
	 * @return
	 */
	public static GetUpSetEntity getGetUpSetEntity(String data) {

		GetUpSetEntity entity = null;

		JSONObject childJson = JsonParsorTools.getDomainJsonObject(data,
				GetUpSetEntity.DOMAIN_NAME);
		double ring_type = AutoSetJsonTools.getDoubleInJson(childJson,
				GetUpSetEntity.ALARM_RING_TYPE);

		String ring_path = AutoSetJsonTools.getStringInJson(childJson,
				GetUpSetEntity.ALARM_RING_PATH);

		double play_time = AutoSetJsonTools.getDoubleInJson(childJson,
				GetUpSetEntity.ALARM_RING_PLAY_TIME);

		double delay_time = AutoSetJsonTools.getDoubleInJson(childJson,
				GetUpSetEntity.ALARM_DELAY_TIME);

		double delay_num = AutoSetJsonTools.getDoubleInJson(childJson,
				GetUpSetEntity.ALARM_DELAY_NUM);

		double message_times = AutoSetJsonTools.getDoubleInJson(childJson,
				GetUpSetEntity.ALARM_MESSAGE_TIMES);

		double message_open = AutoSetJsonTools.getDoubleInJson(childJson,
				GetUpSetEntity.ALARM_MESSAGE_OPEN);

		double will_do = AutoSetJsonTools.getDoubleInJson(childJson,
				GetUpSetEntity.ALARM_WILL_DO);

		entity = new GetUpSetEntity(ring_type, ring_path, play_time,
				delay_num, delay_time, message_times, message_open, will_do);
		return entity;
	}
}
