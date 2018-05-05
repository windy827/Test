package com.autoset.jni.http.productinfo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.http.HttpEntity;
import com.autoset.jni.http.configAndupgrade.ConfigEntity;
import com.autoset.json.AutoSetJsonTools;
import com.autoset.json.AutoSetParsorTools;
/**
 * 产品信息操作类
 * @author 袁剑
 *
 */
public class ProductInfoOptions {
	/**
	 * 得到产品信息
	 * 
	 * @param data
	 * @return
	 */
	public static ArrayList<ProductInfoEntity> getProductInfoEntity(String data) {
		ArrayList<ProductInfoEntity> result = new ArrayList<ProductInfoEntity>();
		if (data.length() > 0) {
			try {
				JSONObject object = new JSONObject(data);
				if (object != null) {
					boolean has = object.has("result");
					if (has) {
						JSONObject res = object.getJSONObject("result");

						boolean hasconfig = res
								.has(ProductInfoEntity.DOMAINNAME);
						if (hasconfig) {
							JSONArray array = (JSONArray) res
									.getJSONArray(ProductInfoEntity.DOMAINNAME);
							int total = array.length();
							for (int i = 0; i < total; i++) {

								JSONObject childJson = (JSONObject) array.get(i);
								AutoSetParsorTools autoSetParsorTools = new AutoSetParsorTools(childJson);
								String productid=autoSetParsorTools.getString(ProductInfoEntity.ID_product_name);
								String guideimage=autoSetParsorTools.getString(ProductInfoEntity.Guideimage_name);
								int guidetype=autoSetParsorTools.getInt(ProductInfoEntity.Guidetype_name);
								String title=autoSetParsorTools.getString(ProductInfoEntity.Title_product_name);
								String url=autoSetParsorTools.getString(ProductInfoEntity.URL_product_name);
								String icon=autoSetParsorTools.getString(ProductInfoEntity.ICON_product_name);
								String description=autoSetParsorTools.getString(ProductInfoEntity.Description_product_name);
								String lastupdatatime=autoSetParsorTools.getString(ProductInfoEntity.LASTUPDATETIME_product_name);
								ProductInfoEntity entity=new ProductInfoEntity(productid,guideimage,guidetype, title, description, icon, url,lastupdatatime);
								result.add(entity);
							}
						}
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
}
