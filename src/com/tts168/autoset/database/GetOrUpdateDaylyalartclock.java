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
 * 操作daylyalartclock每日闹铃的数组库
 * @author 袁剑
 *
 */
public class GetOrUpdateDaylyalartclock {

	/**
	 * 获取有限条数打开的Daylyalartclock数据
	 * 
	 * @param db
	 *            isclosed 0代表false,1代表true
	 * @param limitTotal
	 *          限取的条数
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
			// 数据库中不能存放Boolean型的数，这里用数字0和1代表false和true
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
	 * 获取Daylyalartclock数据
	 * 
	 * @param db
	 *            isclosed 0代表false,1代表true
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
			// 数据库中不能存放Boolean型的数，这里用数字0和1代表false和true
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
	 * 根据ID号获取Daylyalartclock数据
	 * 
	 * @param db
	 *            isclosed 0代表false,1代表true
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
			// 数据库中不能存放Boolean型的数，这里用数字0和1代表false和true
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
	 * 更新是否需要5分钟后再提醒（传入的是0或1）
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
 * 更新周期信息，传入的是这个周期这个字节对应的值，如01010001对应的整型值
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
	 * 根据id修改闹铃定制的时间
	 * 
	 * @param db
	 * @param time
	 *            闹铃定制的时间
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
	 * 根据id修改闹铃定制的事件
	 * 
	 * @param db
	 * @param incident
	 *            闹铃定制的事件
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
	 * 根据id修改闹铃定制的描述
	 * 
	 * @param db
	 * @param describe
	 *            闹铃定制的描述
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
	 * 根据id修改闹铃定制的是否开启
	 * 
	 * @param db
	 * @param isclosed
	 *            闹铃定制的是否开启
	 * @param id
	 */
	public static void update_Daylyalartclock_isclosed(SQLiteDatabase db,
			boolean isclosed, int id) {

		try {
			ContentValues values = new ContentValues();
			// 将true和false转换成0和1
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
	 * 根据ID号更新相关数据
	 * 
	 * @param db
	 * @param id
	 *            这条闹铃的ID号
	 * @param time
	 *            时间
	 * @param incident
	 *            事件
	 * @param describe
	 *            描述
	 * @param isclosed
	 *            是否开启
	 * @param cycle
	 *            提醒的周期，周期信息，传入的是这个周期这个字节对应的值，如01010001对应的整型值
	 * @param re_remind
	 *            是否五分钟后再提醒（传入的是0或1）
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
	 * 添加一条每日闹铃信息
	 * 
	 * @param db
	 * @param time
	 *            闹铃时间
	 * @param incident
	 *            闹铃事件名称
	 * @param describe
	 *            闹铃描述
	 * @param isclosed
	 *            闹铃是否开启
	 * @param cycle
	 *            提醒的周期，周期信息，传入的是这个周期这个字节对应的值，如01010001对应的整型值
	 * @param re_remind
	 *            是否五分钟后再提醒（传入的是0或1）
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
			// 将true和false转换成0和1
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
	 * 根据id号删除一条每日闹铃
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
