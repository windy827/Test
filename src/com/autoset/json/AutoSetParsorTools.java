package com.autoset.json;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 对于Json内容返回做一些容错处理
 * @author 袁剑
 *
 */
public class AutoSetParsorTools {

	public static final String RETURN_NULL_String_ERR="返回的数据没有此项";
	public static final int RETURN_NULL_INT_ERR=0;
	JSONObject object;
	public AutoSetParsorTools(JSONObject object){
		this.object=object;
	}
	/**
	 * 错误返回为 "返回的数据没有此项"
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
	 * 错误返回为0
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
	 * 错误返回为false
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
	 * 检查Json字符串里面是否含有某个字段
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
	 * 错误返回为1111
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
