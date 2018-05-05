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
 * ����daylyalartclockÿ������������
 * @author Ԭ��
 *
 */
public class GetOrUpdateDaylyalartclock {

	/**
	 * ��ȡ���������򿪵�Daylyalartclock����
	 * 
	 * @param db
	 *            isclosed 0����false,1����true
	 * @param limitTotal
	 *          ��ȡ������
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getLimitDaylyalartclock_info(
			SQLiteDatabase db,int limitTotal) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "select * from daylyalartclock  order by isclosed desc LIMIT"+limitTotal+" ;";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String incident = cursor.getString(cursor
					.getColumnIndex("incident"));
			String describe = cursor.getString(cursor
					.getColumnIndex("describe"));
			int isclosed = cursor.getInt(cursor.getColumnIndex("isclosed"));
			int cycle = cursor.getInt(cursor.getColumnIndex("cycle"));
			int re_remind = cursor.getInt(cursor.getColumnIndex("re_remind"));
			
			map.put("id", id);
			map.put("cycle", cycle);
			
			map.put("time", time);
			map.put("incident", incident);
			map.put("describe", describe);
			// ���ݿ��в��ܴ��Boolean�͵���������������0��1����false��true
//			boolean isclosed1 = true;
//			boolean is_remind=true;
//			if (isclosed == 0) {
//				isclosed1 = false;
//			}
//			if (re_remind == 0) {
//				is_remind = false;
//			}
			map.put("re_remind", re_remind);
			map.put("isclosed", isclosed);

			list.add(map);
		}

		cursor.close();
		return list;
	}
	
	
	/**
	 * ��ȡDaylyalartclock����
	 * 
	 * @param db
	 *            isclosed 0����false,1����true
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getDaylyalartclock_info(
			SQLiteDatabase db) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		String sql = "select * from daylyalartclock  order by isclosed desc;";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String incident = cursor.getString(cursor
					.getColumnIndex("incident"));
			String describe = cursor.getString(cursor
					.getColumnIndex("describe"));
			int isclosed = cursor.getInt(cursor.getColumnIndex("isclosed"));
			int cycle = cursor.getInt(cursor.getColumnIndex("cycle"));
			int re_remind = cursor.getInt(cursor.getColumnIndex("re_remind"));
			
			map.put("id", id);
			map.put("cycle", cycle);
			
			map.put("time", time);
			map.put("incident", incident);
			map.put("describe", describe);
			// ���ݿ��в��ܴ��Boolean�͵���������������0��1����false��true
			boolean isclosed1 = true;
			boolean is_remind=true;
			if (isclosed == 0) {
				isclosed1 = false;
			}
			if (re_remind == 0) {
				is_remind = false;
			}
			map.put("re_remind", is_remind);
			map.put("isclosed", isclosed1);

			list.add(map);
		}

		cursor.close();
		return list;
	}
	
	
	/**
	 * ����ID�Ż�ȡDaylyalartclock����
	 * 
	 * @param db
	 *            isclosed 0����false,1����true
	 * @return
	 */
	public static HashMap<String, Object> getDaylyalartclock_infoByID(
			SQLiteDatabase db,int id1) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String sql = "select * from daylyalartclock where id="+id1+";";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String incident = cursor.getString(cursor
					.getColumnIndex("incident"));
			String describe = cursor.getString(cursor
					.getColumnIndex("describe"));
			int isclosed = cursor.getInt(cursor.getColumnIndex("isclosed"));
			int cycle = cursor.getInt(cursor.getColumnIndex("cycle"));
			int re_remind = cursor.getInt(cursor.getColumnIndex("re_remind"));
			
			map.put("id", id);
			map.put("cycle", cycle);
			
			map.put("time", time);
			map.put("incident", incident);
			map.put("describe", describe);
			// ���ݿ��в��ܴ��Boolean�͵���������������0��1����false��true
			boolean isclosed1 = true;
			boolean is_remind=true;
			if (isclosed == 0) {
				isclosed1 = false;
			}
			if (re_remind == 0) {
				is_remind = false;
			}
			map.put("re_remind", is_remind);
			map.put("isclosed", isclosed1);

			
		}

		cursor.close();
		return map;
	}
	/**
	 * �����Ƿ���Ҫ5���Ӻ������ѣ��������0��1��
	 * @param db
	 * @param cycle
	 * @param id
	 */
		public static void update_Daylyalartclock_re_remind(SQLiteDatabase db,
				int re_remind, int id) {

			try {
				ContentValues values = new ContentValues();

				values.put("re_remind", re_remind);

				// db.update(table, values, whereClause, whereArgs)
				db.update("daylyalartclock", values, "id=?",
						new String[] { String.valueOf(id) });
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
/**
 * ����������Ϣ��������������������ֽڶ�Ӧ��ֵ����01010001��Ӧ������ֵ
 * @param db
 * @param cycle
 * @param id
 */
	public static void update_Daylyalartclock_cycle(SQLiteDatabase db,
			int cycle, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("cycle", cycle);

			// db.update(table, values, whereClause, whereArgs)
			db.update("daylyalartclock", values, "id=?",
					new String[] { String.valueOf(id) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * ����id�޸����嶨�Ƶ�ʱ��
	 * 
	 * @param db
	 * @param time
	 *            ���嶨�Ƶ�ʱ��
	 * @param id
	 */
	public static void update_Daylyalartclock_time(SQLiteDatabase db,
			String time, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("time", time);

			// db.update(table, values, whereClause, whereArgs)
			db.update("daylyalartclock", values, "id=?",
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
	public static void update_Daylyalartclock_incident(SQLiteDatabase db,
			String incident, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("incident", incident);

			// db.update(table, values, whereClause, whereArgs)
			db.update("daylyalartclock", values, "id=?",
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
	public static void update_Daylyalartclock_describe(SQLiteDatabase db,
			String describe, int id) {

		try {
			ContentValues values = new ContentValues();

			values.put("describe", describe);

			// db.update(table, values, whereClause, whereArgs)
			db.update("daylyalartclock", values, "id=?",
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
	public static void update_Daylyalartclock_isclosed(SQLiteDatabase db,
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
			db.update("daylyalartclock", values, "id=?",
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
	 * @param time
	 *            ʱ��
	 * @param incident
	 *            �¼�
	 * @param describe
	 *            ����
	 * @param isclosed
	 *            �Ƿ���
	 * @param cycle
	 *            ���ѵ����ڣ�������Ϣ��������������������ֽڶ�Ӧ��ֵ����01010001��Ӧ������ֵ
	 * @param re_remind
	 *            �Ƿ�����Ӻ������ѣ��������0��1��
	 */
	public static void update_Daylyalartclock(SQLiteDatabase db, int id,
			String time, String incident, String describe, boolean isclosed,int cycle,int re_remind) {
		update_Daylyalartclock_describe(db, describe, id);
		update_Daylyalartclock_incident(db, incident, id);
		update_Daylyalartclock_isclosed(db, isclosed, id);
		update_Daylyalartclock_time(db, time, id);
		update_Daylyalartclock_cycle(db, cycle, id);
		update_Daylyalartclock_re_remind(db, re_remind, id);
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
	 * @param cycle
	 *            ���ѵ����ڣ�������Ϣ��������������������ֽڶ�Ӧ��ֵ����01010001��Ӧ������ֵ
	 * @param re_remind
	 *            �Ƿ�����Ӻ������ѣ��������0��1��
	 */

	public static void insert_Daylyalartclock(SQLiteDatabase db, String time,
			String incident, String describe,boolean isclosed,int cycle,int re_remind) {
		try {

			ContentValues values = new ContentValues();
			values.put("time", time);
			values.put("incident", incident);
			values.put("describe", describe);
			values.put("cycle", cycle);
			values.put("re_remind", re_remind);
			// ��true��falseת����0��1
			int isclosed1 = 0;
			if (isclosed) {
				isclosed1 = 1;
			}
			values.put("isclosed", isclosed1);
			db.insert("daylyalartclock", null, values);
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
	public static void delete_Daylyalartclock(SQLiteDatabase db, int id) {

		try {
			String sql = "delete from daylyalartclock where id=" + id + ";";
			db.execSQL(sql);// insert ,delete,update,create table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
