package com.tts168.autoset.database.connectedproduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 记录连接过的产品的产品ID
 * @author 袁剑
 *
 */
public class GetOrUpdateProductIDs {


	/**
	 * 根据child_mac_ip名获取父菜单名称字符串数组，主要供adapter使用
	 * @param db
	 * @param child_macip 设备的Mac地址
	 *
	 * @return 内有内容返回null
	 */
	public static ArrayList<String>  getDistinctProductIDs(SQLiteDatabase db) {
		ArrayList<String> result = new ArrayList<String>();
		
		String sql = "select DISTINCT productid from connectedproductids order by id desc;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		while (cursor.moveToNext()) {
			
		    String group_name=cursor.getString(cursor.getColumnIndex("productid"));
		    result.add(group_name);
		}
		cursor.close();
		return result;

	}
	

	
	
	

	/**
	 * 判断productid是否存在
	 * @param db
	 * @param productid
	 * @return
	 */
	public static boolean isProductidEixst(SQLiteDatabase db,String productid) {
	boolean isExist=false;
		
		String sql = "select * from connectedproductids WHERE productid ='"+productid+"' order by id desc;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		
		while (cursor.moveToNext()) {
		    isExist=true;
		}

		cursor.close();
		return isExist;

	}
	
	
	/**
	 * 添加productid信息
	 * @param db
	 * @param productid
	 */
	public static void insert_Productid(SQLiteDatabase db, String productid){
		try {
			
			ContentValues values = new ContentValues();
			values.put("productid", productid);
			db.insert("connectedproductids", null, values);
		} catch (SQLException e) {
			// TODO Auto-generated ch block
			e.printStackTrace();
		}

	}


	
	/**
	 * 插入或是更新
	 * @param db
	 * @param group_name
	 * @param child_mac_ip
	 */
	public static void insertOrUpdate_Productid(SQLiteDatabase db, String productid) {
		if (!GetOrUpdateProductIDs.isProductidEixst(db, productid)) {
			GetOrUpdateProductIDs.insert_Productid(db, productid);
			
		} else {
			
		}

	}
}
