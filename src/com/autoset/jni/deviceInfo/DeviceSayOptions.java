package com.autoset.jni.deviceInfo;

import java.io.Serializable;

import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.jni.udp.UDPNetEntity;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;
/**
 * �Ʊ���Ӧ
 * @author Ԭ��
 *
 */
public class DeviceSayOptions{

	public static final String DOMAIN_DEVICE_SAY="device_say";//�Ʊ���Ӧ
	public static final String DEVICE_SAY_CONTENT="content";//�����Ʊ����ŵ�����
	/**
	 * �������� �����ޡ�
	 */
	public static final String SaySound="sound502";
	/**
	 * �����Ʊ���Ӧ
	 * sayContent �����˶�Ӧ�Ĳ�����[m3]
	 * setOrget JsonOption.GET_USERDATA��JsonOption.SET_USERDATA
	 * @param intent JsonOption.INTENT_...
	 */
		public static String setDeviceSay(String sayContent,String setOrget,String setFailed,String setSuccess){
			String domainName= DOMAIN_DEVICE_SAY;
			JsonEasyOptions.setObjectJsonHead(JsonAllOption.SET_USERDATA);
			byte[] content = MyTools.getJNIUseByte(sayContent);
			JsonAllOption.cJSONAddStringToObject(JsonAllOption.JsonType.TYPE_DOMAINJSON,MyTools.getJNIUseByte(DEVICE_SAY_CONTENT),content);
			JsonEasyOptions.setJsonTailWithReply(domainName, setFailed, setSuccess, true);
			String result=MyTools.byteArray2String(JsonAllOption.cJSONPrint());
			JsonAllOption.cJSONDelete();
			return result;
		}
		
	/**
	 * �õ��Ʊ���Ӧ
	 * @param data
	 * @return
	 */
	public static String getUDPNetEntity(String data){
		UDPNetEntity entity=null;
		int total = JsonParseOption.setFocusByDomain(
				MyTools.getJNIUseByte(data),
				MyTools.getJNIUseByte(DOMAIN_DEVICE_SAY));
		
		String sayContent =MyTools.byteArray2String( JsonParseOption
				.getFocusJsonGetString(1, MyTools
						.getJNIUseByte(DEVICE_SAY_CONTENT)));
		return sayContent;
	}
}
