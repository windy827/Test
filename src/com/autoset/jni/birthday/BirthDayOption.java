package com.autoset.jni.birthday;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.alarm.AlarmEntity;
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
 * 操作生日设置
 * 
 * @author 袁剑
 * 
 */
public class BirthDayOption {
	/**
	 * 设置生日设置
	 * 
	 * @param setOrget
	 *            JsonParseOption.INSERT_USERDATA或JsonParseOption.SET_USERDATA
	 */
	public static String setBirthDay(ArrayList<BirthDayEntity> entity,
			String setOrget, String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		ArrayList<Object[]> values = new ArrayList<Object[]>();
		for (int i = 0; i < entity.size(); i++) {
			int id = entity.get(i).getId();
			String who = entity.get(i).getWho();
			double format = entity.get(i).getDate_formate();
			String content = entity.get(i).getData_value();
			BirthDayEntity be = new BirthDayEntity(id, who, format, content);

			Object[] temp = be.getValues();
			values.add(temp);
		}

		String result = autoSetJsonCreatTools.setDomainJsonArrayWithOptionPlay(
				 setOrget, BirthDayEntity.DOMAIN_BIRTHDAY,
				BirthDayEntity.KEYS, values,  PlayEntity.TYPE_TTS,setFailed+"", setSuccess+"");
		return result;
	}

	/**
	 * 得到生日设置
	 * 
	 * @param data
	 * @return
	 */
	public static ArrayList<BirthDayEntity> getBirthDayEntity(String data) {

		ArrayList<BirthDayEntity> result = new ArrayList<BirthDayEntity>();
		BirthDayEntity entity = null;
		try {
			JSONArray array = JsonParsorTools.getDomainJsonArray(data,
					BirthDayEntity.DOMAIN_BIRTHDAY);
			int total = array.length();
			for (int i = 0; i < total; i++) {
				JSONObject childJson = (JSONObject) array.get(i);
				AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(childJson);
				double id = autoSetParsorTools
						.getDouble(BirthDayEntity.BIRTHDAY_ID);
				String who = autoSetParsorTools.getString(BirthDayEntity.BIRTHDAY_WHO);
				double format = autoSetParsorTools
						.getDouble(BirthDayEntity.BIRTHDAY_DATE_FORMATE);
				String data_values = autoSetParsorTools
						.getString(BirthDayEntity.BIRTHDAY_DATE_VALUE);
				entity = new BirthDayEntity((int) id, who, format, data_values);
				result.add(entity);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
