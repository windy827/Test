package com.autoset.jni.heartbeat;

import org.json.JSONObject;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;

/**
 * �������Json������
 * 
 * @author Ԭ��
 * 
 */
public class HeartBeatJsonOptions {
	/**
	 * ������������
	 * 
	 * @param setOrget
	 *            JsonOption.GET_USERDATA��JsonOption.SET_USERDATA
	 * @param intent
	 *            JsonOption.INTENT_...
	 */
	public static String setData_HeartBeat(HeartBeatEntity entity) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		HeartBeatEntity ge = new HeartBeatEntity(entity.getTimer(),
				entity.getValue());
		values = ge.getValues();
		String result = autoSetJsonCreatTools.setHeartBeatJson1(
				HeartBeatEntity.KEYS, values);
		return result;
	}
}
