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
 * 操作comerationalartclock生日纪念日的数组库
 * @author 袁剑
 *
 */
public class GetOrUpdateComerationalartclock {
	/**
	 * 获取限量的打开的Comerationalartclock数据
	 * 
	 * @param db
	 *            isclosed 0代表false,1代表true
	 *  @param           limitTotal
	 *  			限取的数量
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
			// 数据库中不能存放Boolean型的数，这里用数字0和1代表false和true
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
	 * 获取Comerationalartclock数据
	 * 
	 * @param db
	 *            isclosed 0代表false,1代表true
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
			// 数据库中不能存放Boolean型的数，这里用数字0和1代表false和true
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
	 * 根据ID号获取Comerationalartclock数据
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
			// 数据库中不能存放Boolean型的数，这里用数字0和1代表false和true
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
	 * 根据id修改闹铃定制的日期
	 * 
	 * @param db
	 * @param time
	 *            闹铃定制的阴历
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
	 * 根据id修改闹铃定制的数据类型
	 * 阳历：1    阴历：2
	 * @param db
	 * @param datatype
	 *            闹铃定制的应阳历类型值
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
	 * 根据id修改闹铃定制的事件
	 * 
	 * @param db
	 * @param incident
	 *            闹铃定制的事件
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
	 * 根据id修改闹铃定制的描述
	 * 
	 * @param db
	 * @param describe
	 *            闹铃定制的描述
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
	 * 根据id修改闹铃定制的是否开启
	 * 
	 * @param db
	 * @param isclosed
	 *            闹铃定制的是否开启
	 * @param id
	 */
	public static void update_Comerationalartclock_isclosed(SQLiteDatabase db,
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
			db.update("comerationalartclock", values, "id=?",
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
	 *  @param time
	 *            阴历
	 * @param solardate
	 *            阳历
	 * @param incident
	 *            事件
	 * @param describe
	 *            描述
	 * @param isclosed
	 *            是否开启
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
	 */

	public static void insert_Comerationalartclock(SQLiteDatabase db,String date, int datatype,
			String incident, String describe, boolean isclosed) {
		try {

			ContentValues values = new ContentValues();
			values.put("date", date);
			values.put("datatype", datatype);
			values.put("incident", incident);
			values.put("describe", describe);
			// 将true和false转换成0和1
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
	 * 根据id号删除一条每日闹铃
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
