package com.autoset.jni.udp;

import org.json.JSONObject;

import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonParsorTools;
import com.tts168.autoset.tools.others.time.GetAndSetTime;

/**
 * UDP发送接收Json操作类
 * @author 袁剑
 *
 */
public class SearchJsonOption {

	/**
	 * 设置UDP 发送的内容
	 */
	public static String setUDPSearch(SearchSendEntity entity) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		SearchSendEntity ge = new SearchSendEntity(entity.getIp(), entity.getDeviceid(),
				entity.getApp_version(),entity.getcSendTime());
		values = ge.getValues();
		String result = autoSetJsonCreatTools
				.setUDPSendJson1(SearchSendEntity.KEYS, values);
		return result;
	}

	/**
	 * 得到UDP接受的内容
	 * 
	 * @param data
	 * @return
	 */
	public static SearchRetrunEntity getSearchRetrunEntity(String data) {
		SearchRetrunEntity entity = null;
		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
				SearchRetrunEntity.DomainName);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		String ip = autoSetParsorTools.getString(SearchRetrunEntity.SearchRet_IP);

		int port = (int) autoSetParsorTools.getDouble(SearchRetrunEntity.SearchRet_PORT);
		String deviceid = autoSetParsorTools.getString(SearchRetrunEntity.SearchRet_DEVICEID);
		String version = autoSetParsorTools.getString(SearchRetrunEntity.SearchRet_VERSION);
		String productid = autoSetParsorTools.getString(SearchRetrunEntity.SearchRet_PRODUCTID);
		String cSendTime = autoSetParsorTools.getString(SearchRetrunEntity.SearchRet_CSendtime);
		String dReciveTime = autoSetParsorTools.getString(SearchRetrunEntity.SearchRet_DRecivetime);
		String dSendTime = autoSetParsorTools.getString(SearchRetrunEntity.SearchRet_DSendtime);
		entity = new SearchRetrunEntity(ip, port, deviceid, version, productid,cSendTime,dReciveTime,dSendTime,GetAndSetTime.setTime());

		return entity;
	}
}
