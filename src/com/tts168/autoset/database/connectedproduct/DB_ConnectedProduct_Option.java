package com.tts168.autoset.database.connectedproduct;

import java.util.ArrayList;
import java.util.HashMap;

import com.tts168.autoset.database.LarkSmartDataBaseConnection;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;
import com.tts168.autoset.tools.others.converopt.BinaryIntArray2ByteTools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
/**
 * ���������ӵ�productid���ݿ�Ĳ���
 * @author Ԭ��
 *
 */
public class DB_ConnectedProduct_Option {
	
	/**
	 * ��ȡproductid
	 * @param c
	 * @param productid
	 * @return
	 */
	public static String getProductids(Context c){
		String ids=""; 
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			ArrayList<String>al_ids=GetOrUpdateProductIDs.getDistinctProductIDs(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase());
			for(int i=0;i<al_ids.size();i++){
				ids=al_ids.get(i)+","+ids;
			}
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
		return ids;
		
	}
		/**
		 * ���productid
		 * @param c
		 * @param productid
		 * @return
		 */
		public static void addProductid(Context c,String productid){
			
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				
			try {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().beginTransaction();
				//��Ϊ�Ǹ�����������ȥ������������Բ������������Ϊ""������
			
				GetOrUpdateProductIDs.insertOrUpdate_Productid(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase(), productid);
				
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//if(Tools.db.inTransaction())
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
