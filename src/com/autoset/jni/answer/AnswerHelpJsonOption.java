package com.autoset.jni.answer;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.deviceInfo.DeviceInfoEntity;
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
import com.tts168.autoset.tools.others.time.GetAndSetTime;

/**
 * 根据实际需要提供的给Java调用的AnswerHelp操作接口
 * 
 * @author 袁剑
 * 
 */
public class AnswerHelpJsonOption {
		
	public static final String NO_QUESTION="无识别结果";
	/**
	 * 得到问答内容实体类
	 * 
	 * @param data
	 * @return
	 */
	public static AnswerHelperEntity getAnswerHelper(String data) {
		AnswerHelperEntity result = null;
		// 设置焦点,得到的Domain成员数

		JSONObject object = JsonParsorTools.getDomainByEventJsonObject(data,
				AnswerHelperEntity.DOMAIN_NAME);
		AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(object);
		String question = autoSetParsorTools
				.getString(AnswerHelperEntity.QUERY_NAME);
		if(question.equals(AutoSetParsorTools.RETURN_NULL_String_ERR)){
			question=NO_QUESTION;
		}
		String reply = autoSetParsorTools
				.getString(AnswerHelperEntity.REPLY_NAME);
		String help = autoSetParsorTools
				.getString(AnswerHelperEntity.HELP_NAME);
		String status = autoSetParsorTools
				.getString(AnswerHelperEntity.STATUS_NAME);

		result = new AnswerHelperEntity(0, question, reply, help,
				GetAndSetTime.getData() + "  " + GetAndSetTime.setTime(),status);

		return result;
	}
}
