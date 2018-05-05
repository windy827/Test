package com.autoset.jni.http.configAndupgrade;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.http.AudioEntity;
import com.autoset.json.AutoSetParsorTools;
import com.tts168.autoset.activity.welcome.SharedConfig;

/**
 * 配置信息，升级信息的Json处理
 * @author 袁剑
 *
 */
public class ConfigAndUpgradeJsonOptions {
/**
 * 得到config包含的网址信息
 * @param data
 * @param isGetMainDomain
 * @return
 */
	public static ConfigEntity getConfigEntity(String data) {
		ConfigEntity result = null;
		try {
			JSONObject object = new JSONObject(data);
			if (object != null) {
				boolean has = object.has("result");
				if (has) {
					JSONObject res = object.getJSONObject("result");
					
				boolean hasconfig = res.has(ConfigEntity.NAME_CONFIG);
				if (hasconfig) {
							JSONObject child = (JSONObject) res.getJSONObject(ConfigEntity.NAME_CONFIG);
							AutoSetParsorTools tools=new AutoSetParsorTools(child);
							String buy_url = tools.getString(ConfigEntity.URL_BUY);
							String help_url = tools.getString(ConfigEntity.URL_HELP);
							String product_url = tools.getString(ConfigEntity.URL_PRODUCT);
							String xmly_url=tools.getString(ConfigEntity.URL_WEBMUSIC_XMLY_HELP);
							String weixin_url=tools.getString(ConfigEntity.URL_WEIXIN);
							if(buy_url.equals(tools.RETURN_NULL_String_ERR)){
								buy_url=SharedConfig.DEFAULT_HELP;
							}
							if(help_url.equals(tools.RETURN_NULL_String_ERR)){
								help_url=SharedConfig.DEFAULT_URL_HELP;
							}
							if(product_url.equals(tools.RETURN_NULL_String_ERR)){
								product_url=SharedConfig.DEFAULT_URL_ABOUT;
							}
							if(xmly_url.equals(tools.RETURN_NULL_String_ERR)){
								xmly_url=SharedConfig.DEFAULT_URL_XMLY_HELP;
							}
							if(weixin_url.equals(tools.RETURN_NULL_String_ERR)){
								weixin_url=SharedConfig.DEFAULT_URL_WEIXIN;
							}
							result=new ConfigEntity(buy_url, help_url, product_url,xmly_url,weixin_url);
				}
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * 得到config包含的网址信息
	 * @param data
	 * @param isGetMainDomain
	 * @return
	 */
		public static UpgradeEntity getUpgradeEntity(String data) {
			UpgradeEntity result = null;
			try {
				JSONObject object = new JSONObject(data);
				if (object != null) {
					boolean has = object.has("result");
					if (has) {
						JSONObject res = object.getJSONObject("result");
						
					boolean hasupgrade = res.has(UpgradeEntity.NAME_Upgrade);
					if (hasupgrade) {
						
								JSONObject child = (JSONObject) res.getJSONObject(UpgradeEntity.NAME_Upgrade);
								AutoSetParsorTools tools=new AutoSetParsorTools(child);
								String configid = tools.getString(UpgradeEntity.NAME_ConfigId);
								String description=tools.getString(UpgradeEntity.NAME_Description);
								int reboot=tools.getInt(UpgradeEntity.NAME_Reboot);
								int forceupgrade=tools.getInt(UpgradeEntity.NAME_FORCEUPGRADE);
								String ver_code = tools.getString(UpgradeEntity.NAME_Vercode);
								String ver_name = tools.getString(UpgradeEntity.NAME_Vername);
								String url="";
								String fname="";
								boolean hasdown= child.has(UpgradeEntity.NAME_Download);
								if(hasdown){
									JSONObject downJson = (JSONObject) child.getJSONArray(UpgradeEntity.NAME_Download).get(0);
									AutoSetParsorTools tools1=new AutoSetParsorTools(downJson);
									url=tools1.getString(UpgradeEntity.NAME_Url);;
									fname=tools1.getString(UpgradeEntity.NAME_FName);;
								}
					
								result=new UpgradeEntity(configid, description,fname, url, reboot, ver_code, ver_name,forceupgrade);
					}
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

}
