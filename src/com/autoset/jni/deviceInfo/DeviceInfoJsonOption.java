package com.autoset.jni.deviceInfo;

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
import com.tts168.autoset.tools.others.time.GetAndSetTime;

/**
 * 根据实际需要提供的给Java调用的General操作接口
 * 
 * @author 袁剑
 * 
 */
public class DeviceInfoJsonOption {

	/**
	 * 设置设备信息
	 * 
	 * @param setOrget
	 *            JsonOption.SET_USERDATA
	 */
	public static String setData_DeviceInfo(DeviceInfoEntity entity,
			String setOrget, String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		DeviceInfoEntity de = new DeviceInfoEntity(entity.getDevice_power(),
				entity.getDevice_ip(), entity.getDevice_mac(),
				entity.getDevice_version(), entity.getSw_version(),
				entity.getDevice_product_ID(), entity.getGoodsid(),entity.getRssi(),entity.getHost(),entity.getConfig(),entity.getOpenid());
		values = de.getValues();
		String result = autoSetJsonCreatTools
				.setDomainJsonObjectWithOptionPlay(setOrget,
						DeviceInfoEntity.DOMAIN_DEVICE_INFO,
						DeviceInfoEntity.KEYS, values,
						PlayEntity.TYPE_PALYSOUND, setFailed, setSuccess);
		;

		return result;
	}

	/**
	 * 得到设备信息
	 * 
	 * @param data
	 * @return
	 */
	public static DeviceInfoEntity getDeviceInfo(String data) {
		DeviceInfoEntity result = null;
		// 设置焦点,得到的Domain成员数
		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
				DeviceInfoEntity.DOMAIN_DEVICE_INFO);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		int power = (int) autoSetParsorTools
				.getDouble(DeviceInfoEntity.DEVICE_INFO_POWER);
		String deviceIp = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_IP);
		String deviceMac = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_MAC);
		String device_version = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_HWVER);
		String sw_version = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_SWVER);
		String device_id = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_PID);
		String goodsid = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_GID);
		int rssi=(int) autoSetParsorTools
				.getDouble(DeviceInfoEntity.DEVICE_INFO_RSSI);
		String host = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_HOST);
		String config = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_CONFIG);
		String openid = autoSetParsorTools
				.getString(DeviceInfoEntity.DEVICE_INFO_OPENID);
		result = new DeviceInfoEntity(power, deviceIp, deviceMac,
				device_version, sw_version, device_id, goodsid,rssi,host,config,openid);
		return result;
	}
}
