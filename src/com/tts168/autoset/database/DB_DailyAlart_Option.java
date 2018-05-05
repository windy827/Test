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
 * 每日闹铃的数据库操作
 * @author 袁剑
 *
 */
public class DB_DailyAlart_Option {
	
	
	/**
	 *得到每日闹铃的前limit条信息
	 * @param limit 限定的条数
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
 * 根据ID号得到每日闹铃的信息
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
	 * 根据ID号删除每日闹铃的信息
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
		 * 根据ID号和提醒的标志为来修改是否五分钟以后重复提醒
		 * @param re_remind 0或1
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
	 * 根据ID号修改每日闹铃的相关信息
	 * @param id
	 * @param time 时间
	 * @param incident 事件
	 * @param describe 描述
	 * @param isclosed 是否开启
	 * @param cycle 周期
	 * @param re_remind 五分钟后是否再提醒
	 */
	public static void updateAllInfoByID(int id,String time,String incident,String describe,boolean isclosed,int cycle,int re_remind){
		
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			GetOrUpdateDaylyalartclock.update_Daylyalartclock(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase(), id, time, incident, describe, isclosed, cycle, re_remind);
			Toast.makeText(MyApplication.getInstance().getCur_Activity(), "编辑成功！", 300).show();
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
