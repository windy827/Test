package com.autoset.jni.alarm;

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
public class AlarmOption {
	/**
	 * 设置每日闹铃
	 * 
	 * @param setOrget
	 *            JsonParseOption.INSERT_USERDATA或JsonParseOption.SET_USERDATA
	 */

	public static String setAlarm(ArrayList<AlarmEntity> entity,
			String setOrget, String setFailed, String setSuccess) {

		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		ArrayList<Object[]> values = new ArrayList<Object[]>();
		for (int i = 0; i < entity.size(); i++) {
			int id = entity.get(i).getId();
			String title = entity.get(i).getTitle();
			String date = entity.get(i).getDate();
			String clock = entity.get(i).getClock();
			double islunar = entity.get(i).getIs_lunar();
			double is_valid = entity.get(i).getIs_valid();

			double fre_mode = entity.get(i).getFre_mode();
			double frequency = entity.get(i).getFrequency();
			

			AlarmEntity ae = new AlarmEntity(id, title, date, clock, 
					islunar, is_valid, fre_mode, frequency);
			Object[] temp = ae.getValues();
			values.add(temp);
		}
		String result = autoSetJsonCreatTools.setDomainJsonArrayWithOptionPlay(
				 setOrget, AlarmEntity.DOMAIN_ALARM, AlarmEntity.KEYS,
				values, PlayEntity.TYPE_TTS,setFailed+"", setSuccess+"");

		return result;
	}

	/**
	 * 得到每日闹铃
	 * 
	 * @param data
	 * @return
	 */
	public static ArrayList<AlarmEntity> getAlarmEntityEntity(String data) {
		ArrayList<AlarmEntity> result = new ArrayList<AlarmEntity>();
		AlarmEntity entity = null;

		try {
			
			JSONArray array = JsonParsorTools.getDomainJsonArray(data, AlarmEntity.DOMAIN_ALARM);
			int total = array.length();
			for (int i = 0; i < total; i++) {

				JSONObject childJson = (JSONObject) array.get(i);
				AutoSetParsorTools autoSetParsorTools=new AutoSetParsorTools(childJson);
				double id = autoSetParsorTools.getDouble(AlarmEntity.ALARM_ID);
				String title = autoSetParsorTools.getString(AlarmEntity.ALARM_TITLE);
				String date = autoSetParsorTools.getString(AlarmEntity.ALARM_DATE);
				String clock = autoSetParsorTools.getString(AlarmEntity.ALARM_CLOCK);
				//double enable = childJson.getDouble(AlarmEntity.ALARM_ENABLE);
				double islunar = AutoSetJsonTools.getDoubleInJson(childJson, AlarmEntity.ALARM_IS_LUNAR);
						
				double is_valid = AutoSetJsonTools.getDoubleInJson(childJson, AlarmEntity.ALARM_IS_VALID);
						
				double fre_mode =  AutoSetJsonTools.getDoubleInJson(childJson, AlarmEntity.ALARM_FRE_MODE);
						
				double frequency =  AutoSetJsonTools.getDoubleInJson(childJson, AlarmEntity.ALARM_FREQUENCY);
						

				entity = new AlarmEntity((int) id, title, date, clock, 
						islunar, is_valid, fre_mode, frequency);
				result.add(entity);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
