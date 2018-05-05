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
 * �ʴ��������ݿ�Ĳ���
 * @author Ԭ��
 *
 */
public class GetOrUpdateAnswerHelper {

	public static final String CusorName="answerhelper";
	
	public static final String KEY_ID="id";
	public static final String KEY_NICKNAME="nickname";//�豸�ǳ�
	public static final String KEY_QUESTION="question";//����
	public static final String KEY_REPLY="reply";//�ظ�
	public static final String KEY_HELP="help";//����
	public static final String KEY_DATE="date";//����
	
	
	public static final int LIMITCOUNT=5;
	/**
	 * ���ű����������Է������������
	 */
	public static final int MAX_DATACOUNT=10;
	
	/**
	 * ����group_name��ȡ�丸���˵���������Ϣ
	 * @param db
	 * @param limit ��ȡ������
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
	 * ����ʴ�������Ϣ
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
	 * ���¸��˵�������
	 * @param db
	 * @param id
	 * @param child_mac_ip �豸��Mac��ַ
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
 * ɾ������
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
	 * ɾ��ȫ��
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
	 * �õ���������
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
	 * �õ���������С��ID��
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
	 * �ж����ݲ�����Ƿ���Ҫɾ��������
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
