package com.tts168.autoset.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class GetOrUpdateVersion {


	/**
	 * 得到当前的数据库版本
	 * @param db
	 *
	 * @return
	 */
	public static int getDBVersion(SQLiteDatabase db) {
		int version=0;
		
		String sql = "select * from version;"; 
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			version=cursor.getInt(0);
		}

		cursor.close();
		return version;

	}
	
}
