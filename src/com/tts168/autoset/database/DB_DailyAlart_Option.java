package com.tts168.autoset.database;

import java.util.ArrayList;
import java.util.HashMap;

import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.others.converopt.BinaryIntArray2ByteTools;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
/**
 * ÿ����������ݿ����
 * @author Ԭ��
 *
 */
public class DB_DailyAlart_Option {
	
	
	/**
	 *�õ�ÿ�������ǰlimit����Ϣ
	 * @param limit �޶�������
	 * 
	 * @return
	 */
		public static ArrayList<HashMap<String,Object>> getlimitInfo(){
			ArrayList<HashMap<String,Object>>result=new ArrayList<HashMap<String,Object>>() ;
			
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				
			try {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().beginTransaction();
				result=GetOrUpdateDaylyalartclock.getLimitDaylyalartclock_info(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase(), Tools.DB_DALIYALART_LIMITTOTAL);
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
			return result;
		}
/**
 * ����ID�ŵõ�ÿ���������Ϣ
 * 
 * @param id
 * @return
 */
	public static HashMap<String,Object>getInfoByID(int id){
		HashMap<String,Object>result=new HashMap<String,Object>() ;
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			result=GetOrUpdateDaylyalartclock.getDaylyalartclock_infoByID(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase() , id);
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
		
		return result;
	}
	
	
	/**
	 * ����ID��ɾ��ÿ���������Ϣ
	 * 
	 * @param id
	 * @return
	 */
		public static void deleteInfoByID(int id){
			
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				
			try {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().beginTransaction();
				GetOrUpdateDaylyalartclock.delete_Daylyalartclock(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase() , id);
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
		/**
		 * ����ID�ź����ѵı�־Ϊ���޸��Ƿ�������Ժ��ظ�����
		 * @param re_remind 0��1
		 * @param id
		 */
		public static void updateRemind(int re_remind,int id){
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				
				
			try {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().beginTransaction();
				GetOrUpdateDaylyalartclock.update_Daylyalartclock_re_remind(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase(), re_remind, id);
				
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
	/**
	 * ����ID���޸�ÿ������������Ϣ
	 * @param id
	 * @param time ʱ��
	 * @param incident �¼�
	 * @param describe ����
	 * @param isclosed �Ƿ���
	 * @param cycle ����
	 * @param re_remind ����Ӻ��Ƿ�������
	 */
	public static void updateAllInfoByID(int id,String time,String incident,String describe,boolean isclosed,int cycle,int re_remind){
		
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			GetOrUpdateDaylyalartclock.update_Daylyalartclock(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase(), id, time, incident, describe, isclosed, cycle, re_remind);
			Toast.makeText(MyApplication.getInstance().getCur_Activity(), "�༭�ɹ���", 300).show();
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
}
