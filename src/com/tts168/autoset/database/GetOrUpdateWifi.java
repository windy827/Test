package com.tts168.autoset.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class GetOrUpdateWifi {


	/**
	 * 根据wifi SSID 名获取数据
	 * @param db
	 * @param SSID 
	 *
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getWifiSet_info(SQLiteDatabase db,String ssid) {
		ArrayList<HashMap<String, Object>>list = new ArrayList<HashMap<String, Object>>();
		
		String sql = "select * from wifiset WHERE SSID ='"+ssid+"' order by id desc;"; 
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			HashMap<String,Object>map=new HashMap<String,Object>();
		    String SSID=cursor.getString(cursor.getColumnIndex("SSID"));
			String PASSWORD=cursor.getString(cursor.getColumnIndex("PASSWORD"));
			
			
			map.put("SSID", SSID);
			map.put("PASSWORD", PASSWORD);
		
		
			list.add(map);
		}

		cursor.close();
		return list;

	}
	
	
	
	
	public static void update_WifiSet_PASSWORD(SQLiteDatabase db, String PASSWORD,String SSID) {

		try {
			ContentValues values = new ContentValues();

			values.put("PASSWORD", PASSWORD);

			// db.update(table, values, whereClause, whereArgs)
			db.update("wifiset", values, "SSID=?",
					new String[] { String.valueOf(SSID) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void insert_WifiSet(SQLiteDatabase db, String SSID,String PASSWORD){
		try {
			
			ContentValues values = new ContentValues();
			values.put("SSID", SSID);
			values.put("PASSWORD", PASSWORD);
			db.insert("wifiset", null, values);
		} catch (SQLException e) {
			// TODO Auto-generated ch block
			e.printStackTrace();
		}

	}


	public static void delete_WifiSet(SQLiteDatabase db, String SSID) {

		
		try {
			String sql = "delete from wifiset where SSID='" + SSID + "';";
			db.execSQL(sql);// insert ,delete,update,create table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void insertOrUpdate_WifiSet(SQLiteDatabase db, String SSID,String PASSWORD) {
		if (GetOrUpdateWifi.getWifiSet_info(db, SSID).size()==0) {
			GetOrUpdateWifi.insert_WifiSet(db, SSID,PASSWORD);
			
		} else {
			GetOrUpdateWifi.update_WifiSet_PASSWORD(db, PASSWORD, SSID);
		}

	}
}
