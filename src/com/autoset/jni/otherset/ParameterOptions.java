package com.autoset.jni.otherset;

import org.json.JSONObject;

import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonParsorTools;
/**
 * 夜间控制的Json操作
 * @author 袁剑
 *
 */
public class ParameterOptions {
	/**
	 * 构造其它设置
	 * 
	 * @param entity
	 * @param method
	 * @param setFailed
	 * @param setSuccess
	 * @return
	 */
	public static String setParameter(ParameterEntity entity, String method,int playtype,
			String setFailed, String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools = new AutoSetJsonTools();
		Object[] values = null;
		ParameterEntity ge = new ParameterEntity(
				entity.getDayInitVolume(), 
				entity.getLoopPlayTime(),entity.getChime(),entity.getHalfchime(),entity.getChime_start(),entity.getChime_end());
		values = ge.getValues();
		String result = autoSetJsonCreatTools
				.setDomainJsonObjectWithOptionPlay(method,
						ParameterEntity.DOMAIN_NAME, ParameterEntity.KEYS, values,
						playtype, setFailed, setSuccess);
		return result;
	}

	/**
	 * 解析其它设置
	 * 
	 * @param data
	 * @return
	 */
	public static ParameterEntity getParameterEntity(String data) {
		ParameterEntity result = null;
		// 设置焦点,得到的Domain成员数
		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
				ParameterEntity.DOMAIN_NAME);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		
		int dayInitVolume = autoSetParsorTools
				.getInt(ParameterEntity.KEY_dayInitVolume);
		
		int loopPlayTime = autoSetParsorTools
				.getInt(ParameterEntity.KEY_loopPlayTime);
		int chime = autoSetParsorTools
				.getInt(ParameterEntity.KEY_chime);
		
		int halfchime = autoSetParsorTools
				.getInt(ParameterEntity.KEY_halfchime);
				
		String chime_start = autoSetParsorTools
				.getString(ParameterEntity.KEY_chime_start);
		String chime_end = autoSetParsorTools
				.getString(ParameterEntity.KEY_chime_end);
		
		result = new ParameterEntity(dayInitVolume, loopPlayTime, chime, halfchime, chime_start, chime_end);

		return result;
	}

}
