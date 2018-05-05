package com.autoset.jni.udp;

import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.fullread.FullReadEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;
import com.autoset.json.MyTools;

/**
 * 操作UDP接收到的数据
 * 
 * @author 袁剑
 * 
 */
public class UDPNetOption {
	/**
	 * 设置UDP setOrget JsonOption.GET_USERDATA或JsonOption.SET_USERDATA
	 * 
	 * @param intent
	 *            JsonOption.INTENT_...
	 */
	public static String setUDPNet(UDPNetEntity entity, String setOrget,
			String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		UDPNetEntity ge = new UDPNetEntity(entity.getIp(), entity.getPort(),
				entity.getApk_version(), entity.getAndroid_version());
		values = ge.getValues();
		String result = autoSetJsonCreatTools
				.setDomainJsonObjectWithOptionPlay(setOrget,
						UDPNetEntity.DOMAIN_NET, UDPNetEntity.KEYS, values,
						PlayEntity.TYPE_PALYSOUND, setFailed, setSuccess);
		return result;
	}

	/**
	 * 得到UDP
	 * 
	 * @param data
	 * @return
	 */
	public static UDPNetEntity getUDPNetEntity(String data) {
		UDPNetEntity entity = null;
		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
				UDPNetEntity.DOMAIN_NET);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		String ip = autoSetParsorTools.getString(UDPNetEntity.NET_IP);

		int port = (int) autoSetParsorTools.getDouble(UDPNetEntity.NET_PORT);
		int apk_version = (int) autoSetParsorTools
				.getDouble(UDPNetEntity.NET_APK_VERSION);
		int android_version = (int) autoSetParsorTools
				.getDouble(UDPNetEntity.NET_ANDROID_VERSION);
		entity = new UDPNetEntity(ip, port, apk_version, android_version);

		return entity;
	}
}
