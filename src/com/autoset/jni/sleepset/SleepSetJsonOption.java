package com.autoset.jni.sleepset;

import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;

public class SleepSetJsonOption {
	/**
	 * ����˯ǰ��������
	 * 
	 * @param setOrget
	 *            JsonOption.GET_USERDATA��JsonOption.SET_USERDATA
	 * @param intent
	 *            JsonOption.INTENT_...
	 */
	public static String setData_SleepSet(SleepSetEntity entity,
			String setOrget, String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		if (setOrget.equals(JsonParseOption.SET_USERDATA)) {

			SleepSetEntity ge = new SleepSetEntity(entity.getCategoryid(),
					entity.getPlaytime(), entity.getWilldo());
			values = ge.getValues();
		} else {
		}
		String result = autoSetJsonCreatTools
				.setDomainJsonObjectWithOptionPlay(setOrget,
						SleepSetEntity.DOMAIN_NAME, SleepSetEntity.KEYS,
						values, PlayEntity.TYPE_PALYSOUND, setFailed,
						setSuccess);
		return result;
	}

	/**
	 * �õ�˯ǰ��������
	 * 
	 * @param data
	 * @return
	 */
	public static SleepSetEntity getSleepSet(String data) {
		SleepSetEntity result = null;
		// ���ý���,�õ���Domain��Ա��

		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
				SleepSetEntity.DOMAIN_NAME);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		String categoryid = autoSetParsorTools
				.getString(SleepSetEntity.CATEGORYID);
		int playtime = autoSetParsorTools.getInt(SleepSetEntity.PLAYTIME);
		int willdo = autoSetParsorTools.getInt(SleepSetEntity.WILL_DO);

		result = new SleepSetEntity(categoryid, playtime, willdo);

		return result;
	}
}
