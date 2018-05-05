package com.autoset.jni.getdomain;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.autoset.jni.wifi.WifiEntity;
import com.autoset.json.JsonParseOption;
import com.autoset.json.MyTools;
/**
 * �õ�������û�з���"",Ŀǰ�趨ֻ��geData�з���
 * @author Ԭ��
 *
 */
public class GetDomainName {
	
	/**
	 * �жϵ�ǰ��GETDOMAIN��Ϣ
	 * @param data
	 * @return 
	 */
	public static GetDomainEntity  isDomainFromGET_DATARETURN(String data){
		GetDomainEntity result=new GetDomainEntity("", true);
		try{
		boolean isFromGetdata=true;
		String domainName="";
		JSONTokener jsonParser = new JSONTokener(data);
		// ��ʱ��δ��ȡ�κ�json�ı���ֱ�Ӷ�ȡ����һ��JSONObject����
		JSONObject main = (JSONObject) jsonParser.nextValue();
		int typeid=main.getInt("id");
		if(typeid!=2){
			isFromGetdata=false;
			
		}else{
			JSONTokener jsonParser1 = new JSONTokener(data);
			jsonParser1.skipPast("result");
			jsonParser1.next(8);
			domainName=jsonParser1.nextString('"');
			
		}
		
		result=new GetDomainEntity(domainName, isFromGetdata);
		}catch(JSONException e){
			e.printStackTrace();
		}
		return result;
	}
}
