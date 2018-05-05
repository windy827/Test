package com.autoset.jni.general;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.fullread.FullReadEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.jni.udp.UDPNetEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;
import com.autoset.json.MyTools;

/**
 * ����ʵ����Ҫ�ṩ�ĸ�Java���õ�General�����ӿ�
 * @author Ԭ��
 *
 */
public class GeneralJsonOption {

	/**
	 * ������������
	 *@param setOrget JsonOption.GET_USERDATA��JsonOption.SET_USERDATA
	 *@param intent JsonOption.INTENT_...
	 */
	public static String setData_general(GeneralEntity entity,String setOrget,String setFailed,String setSuccess) {
		AutoSetJsonTools autoSetJsonCreatTools=new AutoSetJsonTools();
		Object[]values=null;
		if(setOrget.equals(JsonParseOption.SET_USERDATA)){
			
			GeneralEntity ge=new GeneralEntity(entity.getNickname(), entity.getVoiceman(), entity.getCity(), entity.getLocalAdapter(), entity.getLogfilter(),
					entity.getDevice_power(),entity.getProduct_ID(),entity.getDeviceid(), entity.getUserSet(), entity.getProvince(),entity.getOpenid(),entity.isFSKSet());
			values=ge.getValues();
		}else{
		}
		String result=autoSetJsonCreatTools.setDomainJsonObjectWithOptionPlay( setOrget, GeneralEntity.DOMAIN_NAME, GeneralEntity.KEYS, values, PlayEntity.TYPE_PALYSOUND,setFailed, setSuccess);
		return result;
	}
/**
 * �õ���������
 * @param data
 * @return
 */
	public static GeneralEntity getGeneral(String data) {
		GeneralEntity result = null;
		// ���ý���,�õ���Domain��Ա��
		
		JSONObject object = JsonParsorTools.getDomainJsonObject(data,
					GeneralEntity.DOMAIN_NAME);
		AutoSetParsorTools autoSetParsorTools=new AutoSetParsorTools(object);
		String nickname =autoSetParsorTools.getString(GeneralEntity.GENERAL_NICKNAME);
		String voiceman = autoSetParsorTools.getString(GeneralEntity.GENERAL_VOICEMAN);
		String city = autoSetParsorTools.getString(GeneralEntity.GENERAL_CITY);
		Object localAdapter=null;
		double logfilter=autoSetParsorTools.getDouble(GeneralEntity.GENERAL_LOG_FILTER);
		int power=(int) autoSetParsorTools.getDouble(GeneralEntity.GENERAL_POWER);
		String pid = autoSetParsorTools.getString(GeneralEntity.GENERAL_PID);
		String deviceid = autoSetParsorTools.getString(GeneralEntity.GENERAL_DEVICEID);
		int userSet=(int) autoSetParsorTools.getDouble(GeneralEntity.GENERAL_userSet);
		String province = autoSetParsorTools.getString(GeneralEntity.GENERAL_province);
		String openid = autoSetParsorTools.getString(GeneralEntity.GENERAL_Openid);
		boolean isFSK=autoSetParsorTools.hasName(GeneralEntity.GENERAL_FSK);
		result=new GeneralEntity(nickname, voiceman, city, localAdapter, logfilter,power,pid,deviceid,userSet,province,openid,isFSK);
		
		return result;
	}
}
