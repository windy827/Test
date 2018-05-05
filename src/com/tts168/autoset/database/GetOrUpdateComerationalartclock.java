package com.tts168.autoset.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * ����comerationalartclock���ռ����յ������
 * @author Ԭ��
 *
 */
public class GetOrUpdateComerationalartclock {
	/**
	 * ��ȡ�����Ĵ򿪵�Comerationalartclock����
	 * 
	 * @param db
	 *            isclosed 0����false,1����true
	 *  @param           limitTotal
	 *  			��ȡ������
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getLimitComerationalartclock_info(SQLiteDatabase db,int limitTotal) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "select * from comerationalartclock  order by isclosed desc limit"+limitTotal+";";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			int datatype =  cursor.getInt(cursor.getColumnIndex("datatype"));
			String incident = cursor.getString(cursor
					.getColumnIndex("incident"));
			String describe = cursor.getString(cursor
					.getColumnIndex("describe"));
			int isclosed = cursor.getInt(cursor.getColumnIndex("isclosed"));

			map.put("id", id);
			map.put("time", date);
			map.put("datatype", datatype);
			map.put("incident", incident);
			map.put("describe", describe);
			// ���ݿ��в��ܴ��Boolean�͵���������������0��1����false��true
//			boolean isclosed1 = true;
//			if (isclosed == 0) {
//				isclosed1 = false;
//			}
			map.put("isclosed", isclosed);

			list.add(map);
		}

		cursor.close();
		return list;
	}

	/**
	 * ��ȡComerationalartclock����
	 * 
	 * @param db
	 *            isclosed 0����false,1����true
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getComerationalartclock_info(SQLiteDatabase db) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "select * from comerationalartclock  order by isclosed desc;";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			int datatype = cursor.getInt(cursor.getColumnIndex("datatype"));
			String incident = cursor.getString(cursor
					.getColumnIndex("incident"));
			String describe = cursor.getString(cursor
					.getColumnIndex("describe"));
			int isclosed = cursor.getInt(cursor.getColumnIndex("isclosed"));

			map.put("id", id);
			map.put("time", date);
			map.put("datatype", datatype);
			map.put("incident", incident);
			map.put("describe", describe);
			// ���ݿ��в��ܴ��Boolean�͵���������������0��1����false��true
			boolean isclosed1 = true;
			if (isclosed == 0) {
				isclosed1 = false;
			}
			map.put("isclosed", isclosed1);

			list.add(map);
		}

		cursor.close();
		return list;
	}

	
	/**
	 * ����ID�Ż�ȡComerationalartclock����
	 * 
	 * @param db
	 *           
	 * @return
	 */
	public static HashMap<String, Object> getComerationalartclock_infoByID(SQLiteDatabase db,int id1) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select * from comerationalartclock  where id="+id1+";";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			int datatype = cursor.getInt(cursor.getColumnIndex("datatype"));
			String incident = cursor.getString(cursor
					.getColumnIndex("incident"));
			String describe = cursor.getString(cursor
					.getColumnIndex("describe"));
			int isclosed = cursor.getInt(cursor.getColumnIndex("isclosed"));

			map.put("id", id);
			map.put("time", date);
			map.put("datatype", datatype);
			map.put("incident", incident);
			map.put("describe", describe);
			// ���ݿ��в��ܴ��Boolean�͵���������������0��1����false��true
			boolean isclosed1 = true;
			if (isclosed == 0) {
				isclosed1 = false;
			}
			map.put("isclosed", isclosed1);

			
		}

		cursor.close();
		return map;
	}

	/**
	 * ����id�޸����嶨�Ƶ�����
	 * 
	 * @param db
	 * @param time
	 *            ���嶨�Ƶ�����
	 * @param id
	 */
	public static void update_Comerationalartclock_date(SQLiteDatabase db,
			String date, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("date", date);

			// db.update(table, values, whereClause, whereArgs)
			db.update("comerationalartclock", values, "id=?",
					new String[] { String.valueOf(id) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * ����id�޸����嶨�Ƶ���������
	 * ������1    ������2
	 * @param db
	 * @param datatype
	 *            ���嶨�Ƶ�Ӧ��������ֵ
	 * @param id
	 */
	public static void update_Comerationalartclock_datatype(SQLiteDatabase db,
			int datatype, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("datatype", datatype);

			// db.update(table, values, whereClause, whereArgs)
			db.update("comerationalartclock", values, "id=?",
					new String[] { String.valueOf(id) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ����id�޸����嶨�Ƶ��¼�
	 * 
	 * @param db
	 * @param incident
	 *            ���嶨�Ƶ��¼�
	 * @param id
	 */
	public static void update_Comerationalartclock_incident(SQLiteDatabase db,
			String incident, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("incident", incident);

			// db.update(table, values, whereClause, whereArgs)
			db.update("comerationalartclock", values, "id=?",
					new String[] { String.valueOf(id) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ����id�޸����嶨�Ƶ�����
	 * 
	 * @param db
	 * @param describe
	 *            ���嶨�Ƶ�����
	 * @param id
	 */
	public static void update_Comerationalartclock_describe(SQLiteDatabase db,
			String describe, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("describe", describe);

			// db.update(table, values, whereClause, whereArgs)
			db.update("comerationalartclock", values, "id=?",
					new String[] { String.valueOf(id) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ����id�޸����嶨�Ƶ��Ƿ���
	 * 
	 * @param db
	 * @param isclosed
	 *            ���嶨�Ƶ��Ƿ���
	 * @param id
	 */
	public static void update_Comerationalartclock_isclosed(SQLiteDatabase db,
			boolean isclosed, int id) {

		try {
			ContentValues values = new ContentValues();
			// ��true��falseת����0��1
			int isclosed1 = 0;
			if (isclosed) {
				isclosed1 = 1;
			}
			values.put("isclosed", isclosed);

			// db.update(table, values, whereClause, whereArgs)
			db.update("comerationalartclock", values, "id=?",
					new String[] { String.valueOf(id) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ����ID�Ÿ����������
	 * 
	 * @param db
	 * @param id
	 *            ���������ID��
	 *  @param time
	 *            ����
	 * @param solardate
	 *            ����
	 * @param incident
	 *            �¼�
	 * @param describe
	 *            ����
	 * @param isclosed
	 *            �Ƿ���
	 */
	public static void update_Comerationalartclock(SQLiteDatabase db, int id,
			String date, int datatype, String incident, String describe, boolean isclosed) {
		update_Comerationalartclock_describe(db, describe, id);
		update_Comerationalartclock_incident(db, incident, id);
		update_Comerationalartclock_isclosed(db, isclosed, id);
		update_Comerationalartclock_date(db, date, id);
		update_Comerationalartclock_datatype(db, datatype, id);
	}

	/**
	 * ���һ��ÿ��������Ϣ
	 * 
	 * @param db
	 * @param time
	 *            ����ʱ��
	 * @param incident
	 *            �����¼�����
	 * @param describe
	 *            ��������
	 * @param isclosed
	 *            �����Ƿ���
	 */

	public static void insert_Comerationalartclock(SQLiteDatabase db,String date, int datatype,
			String incident, String describe, boolean isclosed) {
		try {

			ContentValues values = new ContentValues();
			values.put("date", date);
			values.put("datatype", datatype);
			values.put("incident", incident);
			values.put("describe", describe);
			// ��true��falseת����0��1
			int isclosed1 = 0;
			if (isclosed) {
				isclosed1 = 1;
			}
			values.put("isclosed", isclosed1);
			db.insert("comerationalartclock", null, values);
		} catch (SQLException e) {
			// TODO Auto-generated ch block
			e.printStackTrace();
		}

	}

	/**
	 * ����id��ɾ��һ��ÿ������
	 * 
	 * @param db
	 * @param id
	 */
	public static void delete_Comerationalartclock(SQLiteDatabase db, int id) {

		try {
			String sql = "delete from comerationalartclock where id=" + id + ";";
			db.execSQL(sql);// insert ,delete,update,create table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
