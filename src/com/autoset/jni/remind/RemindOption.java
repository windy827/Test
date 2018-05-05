package com.autoset.jni.remind;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.alarm.AlarmEntity;
import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.jni.wifi.WifiEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;
import com.autoset.json.MyTools;

/**
 * 操作备忘设置
 * 
 * @author 袁剑
 * 
 */
public class RemindOption {
	/**
	 * 设置备忘设置
	 * 
	 * @param setOrget
	 *            JsonParseOption.INSERT_USERDATA或JsonParseOption.SET_USERDATA
	 * 
	 */
	public static String setRemind(ArrayList<RemindEntity> entity,
			String setOrget, String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		ArrayList<Object[]> values = new ArrayList<Object[]>();
		for (int i = 0; i < entity.size(); i++) {
			int id = entity.get(i).getId();
			double is_valid = entity.get(i).getIs_valid();
			String data = entity.get(i).getDate();
			String content = entity.get(i).getContent();
			RemindEntity re = new RemindEntity(id,is_valid, data, content);
			Object[] temp = re.getValues();
			values.add(temp);
		}

		String result = autoSetJsonCreatTools.setDomainJsonArrayWithOptionPlay(
				 setOrget, RemindEntity.DOMAIN_REMIND, RemindEntity.KEYS,
				values,  PlayEntity.TYPE_TTS,setFailed+"", setSuccess+"");
		return result;
	}

	/**
	 * 得到备忘设置
	 * 
	 * @param data
	 * @return
	 */
	public static ArrayList<RemindEntity> getRemindEntity(String data) {

		ArrayList<RemindEntity> result = new ArrayList<RemindEntity>();
		RemindEntity entity = null;
		try {
			JSONArray array = JsonParsorTools.getDomainJsonArray(data,
					RemindEntity.DOMAIN_REMIND);
			int total = array.length();
			for (int i = 0; i < total; i++) {
				JSONObject childJson = (JSONObject) array.get(i);
				AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(childJson);
				double id = autoSetParsorTools.getDouble(RemindEntity.REMIND_ID);
				double is_valid = autoSetParsorTools.getDouble(RemindEntity.ALARM_IS_VALID);
				String edata = autoSetParsorTools.getString(RemindEntity.REMIND_DATE);
				String content = autoSetParsorTools.getString(RemindEntity.REMIND_CONTENT);
				entity = new RemindEntity((int) id,is_valid, edata, content);
				result.add(entity);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
