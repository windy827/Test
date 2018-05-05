package com.autoset.jni.presskey;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.birthday.BirthDayEntity;
import com.autoset.jni.general.GeneralEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.remind.RemindEntity;
import com.autoset.jni.statusplay.StatusPlayOption;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
import com.autoset.json.JsonAllOption;
import com.autoset.json.JsonEasyOptions;
import com.autoset.json.JsonParseOption;
import com.autoset.json.JsonParsorTools;
import com.autoset.json.MyTools;

/**
 *操作UDP接收到的数据
 * @author 袁剑
 *
 */
public class PressKeyOption {
/**
 * 设置按键设置
 * *@param setOrget  JsonParseOption.INSERT_USERDATA
 */
	public static String setPressKey(ArrayList<PressKeyEntity> entity,String setOrget,String setFailed,String setSuccess){
		AutoSetJsonTools autoSetJsonCreatTools=new AutoSetJsonTools();
		ArrayList<Object[]>values=new ArrayList<Object[]>();
		for(int i=0;i<entity.size();i++){
			double id=entity.get(i).getKey_id();
			String name =  entity.get(i).getName();
			String type = entity.get(i).getType();
			String sclick = entity.get(i).getSclick();
			String dclick = entity.get(i).getDclick();
			String lclick = entity.get(i).getLclick();
		
			PressKeyEntity be=new PressKeyEntity(id, name, type, sclick, dclick, lclick);
			
			Object[] temp=be.getValues();
			values.add(temp);	
		}
		String result=autoSetJsonCreatTools.setDomainJsonArrayWithOptionPlay( setOrget, PressKeyEntity.DOMAIN_KEYBOARD, PressKeyEntity.KEYS, values,PlayEntity.TYPE_PALYSOUND, setFailed, setSuccess);
		return result;
	}
	
	
	/**
	 * 得到按键设置
	 * @param data
	 * @return
	 */
	public static ArrayList<PressKeyEntity> getPressKeyEntity(String data){
		ArrayList<PressKeyEntity> result=new ArrayList<PressKeyEntity>();
		PressKeyEntity entity=null;
		try {
			JSONArray array =new JSONArray();
			try {
				JSONObject object = new JSONObject(data);
				if(object!=null){
					boolean has=object.has(AutoSetJsonTools.NameAndValues.JSON_RESULT);
					if(has){
						JSONObject res = object.getJSONObject(AutoSetJsonTools.NameAndValues.JSON_RESULT);
						boolean hasaudio=res.has(PressKeyEntity.DOMAIN_KEYBOARD);
						if(hasaudio){
							JSONObject domainarray = res.getJSONObject(PressKeyEntity.DOMAIN_KEYBOARD);
							boolean hascontent=domainarray.has(PressKeyEntity.DOMAIN_KEYBOARD);
							if(hascontent){
								array = (JSONArray) res.getJSONArray(PressKeyEntity.DOMAIN_KEYBOARD);
							}
							
							
						}
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int total = array.length();
		for(int i=0;i<total;i++){
			JSONObject childJson = (JSONObject) array.get(i);
			AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(childJson);
			int key_id=(int) autoSetParsorTools.getDouble(PressKeyEntity.KEYBOARD_KEY_ID);
			String name =autoSetParsorTools.getString(PressKeyEntity.KEYBOARD_NAME);
			String type =autoSetParsorTools.getString(PressKeyEntity.KEYBOARD_TYPE);
			String sclick =autoSetParsorTools.getString(PressKeyEntity.KEYBOARD_SCLICK);		
			String dclick =autoSetParsorTools.getString(PressKeyEntity.KEYBOARD_DCLICK);
			String lclick =autoSetParsorTools.getString(PressKeyEntity.KEYBOARD_LCLICK);
			entity=new PressKeyEntity(key_id, name, type, sclick, dclick, lclick);
			result.add(entity);
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
