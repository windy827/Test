package com.autoset.jni.wifi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.remind.RemindEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;
import com.autoset.json.MyTools;

/**
 * 设置Wifi
 * 
 * @author 袁剑
 * 
 */
public class WifiOption {
	/**
	 * 设置云宝设备的名称,IP 地址以及端口号[TTS语音] PlayEntity.TYPE_TTS
	 * 
	 * @param JsonParseOption
	 *            .INSERT_USERDATA或JsonParseOption.SET_USERDATA
	 */
	public static String setWifi(ArrayList<WifiEntity> entity, String setOrget,
			String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		ArrayList<Object[]> values = new ArrayList<Object[]>();
		for (int i = 0; i < entity.size(); i++) {
			int id = entity.get(i).getId();
			String ssid1 = entity.get(i).getSsid();
			String password1 = entity.get(i).getPassword();
			String s = (i + 1) + "";
			double ss = Double.parseDouble(s);

			WifiEntity re = new WifiEntity(id, ssid1, password1, (int) ss);
			Object[] temp = re.getValues();
			values.add(temp);
		}
		String result = autoSetJsonCreatTools.setDomainJsonArrayWithOptionPlay(
				setOrget, WifiEntity.DOMAIN_WIFI, WifiEntity.KEYS, values,
				PlayEntity.TYPE_TTS, setFailed, setSuccess);
		;
		return result;
	}

	/**
	 * 得到Wifi
	 * 
	 * @param data
	 * @return
	 */
	public static ArrayList<WifiEntity> getWifiEntity(String data) {
		ArrayList<WifiEntity> result = new ArrayList<WifiEntity>();
		WifiEntity entity = null;
		try {
			JSONArray array = JsonParsorTools.getDomainJsonArray(data,
					WifiEntity.DOMAIN_WIFI);
			int total = array.length();
			for (int i = 0; i < total; i++) {
				JSONObject childJson = (JSONObject) array.get(i);
				AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(
						childJson);
				double id = autoSetParsorTools.getDouble(WifiEntity.WIFI_ID);
				String ssid = autoSetParsorTools
						.getString(WifiEntity.WIFI_SSID);
				String password = autoSetParsorTools
						.getString(WifiEntity.WIFI_PASSWORD);
				int priority = (int) autoSetParsorTools
						.getDouble(WifiEntity.WIFI_PRIORITY);
				entity = new WifiEntity((int) id, ssid, password, priority);
				result.add(entity);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
