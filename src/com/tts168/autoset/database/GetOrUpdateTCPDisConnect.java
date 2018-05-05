package com.tts168.autoset.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
import com.tts168.autoset.tools.test.UdpAndTcpTestEntity;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 记录UDP扫描流程连接失败的设备
 * @author 袁剑
 *
 */
public class GetOrUpdateTCPDisConnect {


/**
 * 外部调用添加记录接口
 */
	public static void recordDisConnect(UdpAndTcpTestEntity entity){
		
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			insert_DisConnect(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase(),entity);
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().endTransaction();
			if (LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase() != null) {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().close();
			}

		}
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().unlock();
	}  
	
	
	public static void insert_DisConnect(SQLiteDatabase db,UdpAndTcpTestEntity entity){
		try {
			
			ContentValues values = new ContentValues();
			values.put("date", GetAndSetTime.getData()+"  "+GetAndSetTime.setTime());
			values.put("state8", entity.getState_8());
			values.put("state11", entity.getState_11());
			values.put("statewhite", entity.getState_white());
			values.put("stategold", entity.getState_gold());
			db.insert("tcptest", null, values);
		} catch (SQLException e) {
			// TODO Auto-generated ch block
			e.printStackTrace();
		}

	}

}
