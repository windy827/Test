package com.tts168.autoset.database.answerhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.tts168.autoset.tools.answer.AnswerTools;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 问答助手数据库的操作
 * @author 袁剑
 *
 */
public class GetOrUpdateAnswerHelper {

	public static final String CusorName="answerhelper";
	
	public static final String KEY_ID="id";
	public static final String KEY_NICKNAME="nickname";//设备昵称
	public static final String KEY_QUESTION="question";//问题
	public static final String KEY_REPLY="reply";//回复
	public static final String KEY_HELP="help";//帮助
	public static final String KEY_DATE="date";//日期
	
	
	public static final int LIMITCOUNT=5;
	/**
	 * 这张表里面最多可以放入的数据条数
	 */
	public static final int MAX_DATACOUNT=10;
	
	/**
	 * 根据group_name获取其父级菜单的所有信息
	 * @param db
	 * @param limit 限取的数量
	 * @return
	 */
	public static ArrayList<HashMap<String,Object>> getInfoByGroupNameLimit(SQLiteDatabase db,int limit) {
		ArrayList<HashMap<String,Object>>info=new ArrayList<HashMap<String,Object>>();
		
		String sql = "select * from answerhelper  order by id desc limit "+limit+";"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		
		while (cursor.moveToNext()) {
			HashMap<String,Object>temp=new HashMap<String, Object>();
			int id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
			String nickname=cursor.getString(cursor.getColumnIndex(KEY_NICKNAME));
		    String question=cursor.getString(cursor.getColumnIndex(KEY_QUESTION));
		    String reply=cursor.getString(cursor.getColumnIndex(KEY_REPLY));
		    String help=cursor.getString(cursor.getColumnIndex(KEY_HELP));
		    String date=cursor.getString(cursor.getColumnIndex(KEY_DATE));
		    AnswerHelperEntity entity=new AnswerHelperEntity(id, question, reply, help, date,AnswerHelperEntity.STATUS_STOP);
		    temp.put(AnswerTools.KEY_ENTITY, entity);
		    temp.put(AnswerTools.KEY_NICKNAME, nickname);
		    info.add(temp);
		}
		cursor.close();
		return info;

	}
	
	
	
	
	
	/**
	 * 添加问答助手信息
	 * @param db
	 * @param temp
	 */
	public static void insert_answerhelper(SQLiteDatabase db, String nickname, AnswerHelperEntity temp){
		try {
			
			ContentValues values = new ContentValues();
			values.put(KEY_NICKNAME, nickname);
			values.put(KEY_QUESTION, temp.getQuestion());
			values.put(KEY_REPLY, temp.getReply());
			values.put(KEY_HELP, temp.getHelp());
			values.put(KEY_DATE, temp.getDate());
			db.insert(CusorName, null, values);
		} catch (SQLException e) {
			// TODO Auto-generated ch block
			e.printStackTrace();
		}

	}

	
	/**
	 * 更新父菜单的名称
	 * @param db
	 * @param id
	 * @param child_mac_ip 设备的Mac地址
	 */
	public static void update_answerhelper_name(SQLiteDatabase db,int id, String nickname,AnswerHelperEntity temp) {

		try {
			ContentValues values = new ContentValues();
			values.put(KEY_NICKNAME, nickname);
			values.put(KEY_QUESTION, temp.getQuestion());
			values.put(KEY_REPLY, temp.getReply());
			values.put(KEY_HELP, temp.getHelp());
			values.put(KEY_DATE, temp.getDate());	
			
			db.update(CusorName, values, "id=?",
					new String[] { String.valueOf(id) });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * 删除单个
 * @param db
 * @param id
 */
	public static void delete_answerhelper(SQLiteDatabase db, int id) {

		
		try {
			String sql = "delete from "+CusorName+" where "+KEY_ID+"='" + id + "';";
			db.execSQL(sql);// insert ,delete,update,create table
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 删除全部
	 * @param db
	 * @param id
	 */
		public static void delete_All_answerhelper(SQLiteDatabase db) {

			
			try {
				String sql = "delete from "+CusorName+" where "+KEY_ID+">'" + 0 + "';";
				db.execSQL(sql);// insert ,delete,update,create table
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	/**
	 * 得到数据条数
	 * @param db
	 * @return
	 */
	public static int getCount(SQLiteDatabase db) {
		int count=0;
		
		String sql = "select count(*) from "+CusorName+" ;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		
		while (cursor.moveToNext()) {
			
		   count=cursor.getInt(0);
		}

		cursor.close();
		return count;

	}
	/**
	 * 得到数据中最小的ID号
	 * @param db
	 * @return
	 */
	public static int getMinID(SQLiteDatabase db) {
		int id=0;
		
		String sql = "select min(id) from "+CusorName+" ;"; 
		Cursor cursor = db.rawQuery(sql, null);
		
		
		while (cursor.moveToNext()) {
			
		   id=cursor.getInt(0);
		}

		cursor.close();
		return id;

	}
	/**
	 * 判断数据插入后是否还需要删除旧数据
	 * @param db
	 * @param group_name
	 * @param child_mac_ip
	 */
	public static void insertAndJudgeNeedDelete_answerhelper(SQLiteDatabase db,String nickname, AnswerHelperEntity temp) {
		GetOrUpdateAnswerHelper.insert_answerhelper(db, nickname,temp);
		if (GetOrUpdateAnswerHelper.getCount(db)>MAX_DATACOUNT) {
			int minID=GetOrUpdateAnswerHelper.getMinID(db);
			GetOrUpdateAnswerHelper.delete_answerhelper(db, minID);
			
		} 

	}
}
