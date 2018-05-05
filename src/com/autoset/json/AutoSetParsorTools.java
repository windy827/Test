package com.autoset.json;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * ����Json���ݷ�����һЩ�ݴ���
 * @author Ԭ��
 *
 */
public class AutoSetParsorTools {

	public static final String RETURN_NULL_String_ERR="���ص�����û�д���";
	public static final int RETURN_NULL_INT_ERR=0;
	JSONObject object;
	public AutoSetParsorTools(JSONObject object){
		this.object=object;
	}
	/**
	 * ���󷵻�Ϊ "���ص�����û�д���"
	 * @param name
	 * @return
	 */
	public String getString(String name){
		String result=RETURN_NULL_String_ERR;
		if(this.object.has(name)){
			try {
				result=this.object.getString(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * ���󷵻�Ϊ0
	 * @param name
	 * @return
	 */
	public double getDouble(String name){
		 double result=RETURN_NULL_INT_ERR;
		if(this.object.has(name)){
			try {
				result=this.object.getDouble(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * ���󷵻�Ϊfalse
	 * @param name
	 * @return
	 */
	public boolean getBoolean(String name){
		 boolean result=false;
		if(this.object.has(name)){
			try {
				result=this.object.getBoolean(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * ���Json�ַ��������Ƿ���ĳ���ֶ�
	 * @param name
	 * @return
	 */
	public boolean hasName(String name){
		 boolean result=false;
		if(this.object.has(name)){
			result=true;
		}
		return result;
	}
	
	/**
	 * ���󷵻�Ϊ1111
	 * @param name
	 * @return
	 */
	public int getInt(String name){
		 int result=RETURN_NULL_INT_ERR;
		if(this.object.has(name)){
			try {
				result=this.object.getInt(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
