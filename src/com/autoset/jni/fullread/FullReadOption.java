package com.autoset.jni.fullread;

import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.deviceInfo.DeviceInfoEntity;
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
public class FullReadOption {
	/**
	 * 设置整点报时
	 * 
	 * @param setOrget
	 *            JsonOption.GET_USERDATA或JsonOption.SET_USERDATA
	 * @param intent
	 *            JsonOption.INTENT_...
	 */
	public static String setFullRead(FullReadEntity entity, String setOrget,
			String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		FullReadEntity ge = new FullReadEntity(entity.getFull(),
				entity.getHalf(), entity.getStarttime(), entity.getEndtime());
		values = ge.getValues();
		String result = autoSetJsonCreatTools
				.setDomainJsonObjectWithOptionPlay( setOrget,
						FullReadEntity.DOMAIN_CLOCKCHIME, FullReadEntity.KEYS,
						values,PlayEntity.TYPE_PALYSOUND, setFailed, setSuccess);
		return result;
	}

	/**
	 * 得到整点报时
	 * 
	 * @param data
	 * @return
	 */
	public static FullReadEntity getFullReadEntity(String data) {
		FullReadEntity entity = null;
		
			JSONObject object = JsonParsorTools.getDomainJsonObject(data,
					FullReadEntity.DOMAIN_CLOCKCHIME);
			AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
			int full = (int) autoSetParsorTools.getDouble(FullReadEntity.CHIME_CHIME);
			int half = (int) autoSetParsorTools.getDouble(FullReadEntity.CHIME_HALF_CHIME);

			String starttime = autoSetParsorTools.getString(FullReadEntity.CHIME_START);
			String endtime = autoSetParsorTools.getString(FullReadEntity.CHIME_END);
			entity = new FullReadEntity(full, half, starttime, endtime);
		
		return entity;
	}
}
