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
 * �ն��豸���ݿ�Ĳ�����Mac��ַ��Ψһ����һ���Ʊ��豸�ı�׼��
 * @author Ԭ��
 *
 */
public class GetOrUpdateTerminal {
	/**
	 * ɾ������Ĭ�Ϸ�����
	 */
public static final String MYDEVICES="�ҵ��豸";
/**
 * Ĭ�Ϸ�����û��child
 */
public static final String MYDEVICES_NULL_CHILDMAC="��" ;
/**
 * ����������û���Ӳ˵����Ӳ˵�����
 */
public static final String DEFAULT_NULL_CHILDMAC="��" ;

	/**
	 * ����child_mac_ip����ȡ���˵������ַ������飬��Ҫ��adapterʹ��
	 * @param db
	 * @param child_macip �豸��Mac��ַ
	 *
	 * @return �������ݷ���null
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
	 * ����group_name��ȡ�丸���˵���������Ϣ
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
	 * ����child_mac_ip��ȡ�丸���˵�������
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
	 * �ж�child_mac_ip�ĸ����Ƿ����
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
	 * ���¸��˵�������
	 * @param db
	 * @param group_name
	 * @param child_mac_ip �豸��Mac��ַ
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
	 * ����ն��豸��Ϣ
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
 * �˽ӿڲ�����ʹ�á��ݲ����á�
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
 * ����	groupNameɾ�����˵�
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
	 * ������Ǹ���
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
