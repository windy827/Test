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
 * ���ռ����յ����ݿ����
 * @author Ԭ��
 *
 */
public class DB_Commeration_Option {
	
	
	/**
	 *�õ����ռ����������ǰlimit����Ϣ
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
				
					result=GetOrUpdateComerationalartclock.getLimitComerationalartclock_info(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase(), Tools.DB_COMMERATION_LIMITTOTAL);
				
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
 * ����ID�ŵõ����ռ������������Ϣ
 * 
 * @param id
 * @return
 */
	public static HashMap<String,Object>getInfoByID(int id){
		HashMap<String,Object>result=new HashMap<String,Object>() ;;
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			
				result=GetOrUpdateComerationalartclock.getComerationalartclock_infoByID(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase(), id);
			
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
	 * ����ID��ɾ�����ռ������������Ϣ
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
				GetOrUpdateComerationalartclock.delete_Comerationalartclock(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase(), id);
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
	 *  ����ID�Ÿ����������
	 * @param id
	 * @param date ����
	 * @param datatype ��������
	 * @param incident �¼�
	 * @param describe ������
	 * @param isclosed �Ƿ���
	 */
	 
	
		public static void updateAllInfoByID(int id, String date,int datatype,String incident,String describe,boolean isclosed){
			
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				
			try {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().beginTransaction();
				GetOrUpdateComerationalartclock.update_Comerationalartclock(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase(), id, date, datatype, incident, describe, isclosed);
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
