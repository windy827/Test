package com.autoset.jni.otherset;

import org.json.JSONObject;

import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonParsorTools;
/**
 * ҹ����Ƶ�Json����
 * @author Ԭ��
 *
 */
public class ParameterOptions {
	/**
	 * ������������
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
	 * ������������
	 * 
	 * @param data
	 * @return
	 */
	public static ParameterEntity getParameterEntity(String data) {
		ParameterEntity result = null;
		// ���ý���,�õ���Domain��Ա��
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
