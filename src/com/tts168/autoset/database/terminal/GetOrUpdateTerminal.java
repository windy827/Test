package com.tts168.autoset.database.terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 终端设备数据库的操作【Mac地址是唯一评判一个云宝设备的标准】
 * @author 袁剑
 *
 */
public class GetOrUpdateTerminal {
	/**
	 * 删不掉的默认分组名
	 */
public static final String MYDEVICES="我的设备";
/**
 * 默认分组名没有child
 */
public static final String MYDEVICES_NULL_CHILDMAC="ǖ" ;
/**
 * 其它分组名没有子菜单的子菜单内容
 */
public static final String DEFAULT_NULL_CHILDMAC="ǘ" ;

	/**
	 * 根据child_mac_ip名获取父菜单名称字符串数组，主要供adapter使用
	 * @param db
	 * @param child_macip 设备的Mac地址
	 *
	 * @return 内有内容返回null
	 */
	public static ArrayList<String>  getDistinctGroup_name(SQLiteDatabase db) {
		ArrayList<String> result = new ArrayList<String>();
		
		String sql = "select DISTINCT group_name from terminal order by id desc;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		while (cursor.moveToNext()) {
			
		    String group_name=cursor.getString(cursor.getColumnIndex("group_name"));
		    result.add(group_name);
		}

		if(result.size()==0){
			result.add(MYDEVICES);
		}
		cursor.close();
		return result;

	}
	
	/**
	 * 根据group_name获取其父级菜单的所有信息
	 * @param db
	 * @param group_name
	 * @return
	 */
	public static ArrayList<HashMap<String,Object>> getInfoByGroupName(SQLiteDatabase db,String group_name) {
		ArrayList<HashMap<String,Object>> result=new ArrayList<HashMap<String,Object>>();
		
		String sql = "select * from terminal WHERE group_name ='"+group_name+"' order by id desc;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		
		while (cursor.moveToNext()) {
			HashMap<String,Object>temp=new HashMap<String,Object>();
		    String groupname=cursor.getString(cursor.getColumnIndex("group_name"));
		    String child_mac_ip=cursor.getString(cursor.getColumnIndex("child_mac_ip"));
		    String child_name=cursor.getString(cursor.getColumnIndex("child_name"));
		    temp.put("group_name", groupname);
		    temp.put("child_mac_ip", child_mac_ip);
		    temp.put("child_name", child_name);
		   result.add(temp);
		}

		cursor.close();
		return result;

	}
	
	
	
	/**
	 * 根据child_mac_ip获取其父级菜单的名称
	 * @param db
	 * @param child_mac_ip
	 * @return
	 */
	public static String getGroup_name(SQLiteDatabase db,String child_mac_ip) {
		String result = MYDEVICES;
		
		String sql = "select DISTINCT group_name from terminal WHERE child_mac_ip ='"+child_mac_ip+"' order by id desc;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		
		while (cursor.moveToNext()) {
			
		    String group_name=cursor.getString(cursor.getColumnIndex("group_name"));
		    result=group_name;
		}

		cursor.close();
		return result;

	}
	
	
	

	/**
	 * 判断child_mac_ip的父项是否存在
	 * @param db
	 * @param child_mac_ip
	 * @return
	 */
	public static boolean isGroup_nameEixst(SQLiteDatabase db,String child_mac_ip) {
	boolean isExist=false;
		
		String sql = "select DISTINCT group_name from terminal WHERE child_mac_ip ='"+child_mac_ip+"' order by id desc;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		
		while (cursor.moveToNext()) {
		    isExist=true;
		}

		cursor.close();
		return isExist;

	}
	
	
	
	/**
	 * 更新父菜单的名称
	 * @param db
	 * @param group_name
	 * @param child_mac_ip 设备的Mac地址
	 */
	public static void update_group_name(SQLiteDatabase db, String group_name,String child_mac_ip) {

		try {
			ContentValues values = new ContentValues();
			values.put("group_name", group_name);		
			db.update("terminal", values, "child_mac_ip=?",
					new String[] { String.valueOf(child_mac_ip) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 添加终端设备信息
	 * @param db
	 * @param group_name
	 * @param child_mac_ip
	 */
	public static void insert_Terminal(SQLiteDatabase db, String group_name,String child_mac_ip,String child_name){
		try {
			
			ContentValues values = new ContentValues();
			values.put("group_name", group_name);
			values.put("child_mac_ip", child_mac_ip);
			values.put("child_name", child_name);
			db.insert("terminal", null, values);
		} catch (SQLException e) {
			// TODO Auto-generated ch block
			e.printStackTrace();
		}

	}

/**
 * 此接口不建议使用【暂不适用】
 * @param db
 * @param child_mac_ip
 */
	public static void delete_Terminal(SQLiteDatabase db, String child_mac_ip) {

		
		try {
			String sql = "delete from terminal where child_mac_ip='" + child_mac_ip + "';";
			db.execSQL(sql);// insert ,delete,update,create table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * 根据	groupName删除父菜单
 * @param db
 * @param groupName
 */
public static void delete_TerminalByGroupName(SQLiteDatabase db, String groupName) {

		
		try {
			String sql = "delete from terminal where group_name='" + groupName + "';";
			db.execSQL(sql);// insert ,delete,update,create table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 插入或是更新
	 * @param db
	 * @param group_name
	 * @param child_mac_ip
	 */
	public static void insertOrUpdate_Terminal(SQLiteDatabase db, String group_name,String child_mac_ip,String child_name) {
		if (!GetOrUpdateTerminal.isGroup_nameEixst(db, child_mac_ip)) {
			GetOrUpdateTerminal.insert_Terminal(db, group_name,child_mac_ip,child_name);
			
		} else {
			GetOrUpdateTerminal.update_group_name(db, group_name, child_mac_ip);
		}

	}
}
