package com.tts168.autoset.database.answerhelper;

import java.util.ArrayList;
import java.util.HashMap;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.tts168.autoset.database.LarkSmartDataBaseConnection;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;
/**
 * �ʴ���Ϣ������
 * @author Administrator
 *
 */
public class AnswerHelperDBOptions {
	/**
	 * ��ȡ�ʴ����Ϣ
	 * @return
	 */

	public static ArrayList<HashMap<String,Object>> getInfo() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String,Object>> result=new ArrayList<HashMap<String,Object>>();
		
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			ArrayList<HashMap<String,Object>> temp=new ArrayList<HashMap<String,Object>>();
			temp=GetOrUpdateAnswerHelper.getInfoByGroupNameLimit(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase(), GetOrUpdateAnswerHelper.LIMITCOUNT);
			for(int i=temp.size()-1;i>=0;i--){
				result.add(temp.get(i));
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
		return result;
	}
	
	/**
	 *  ������ݡ��ڲ�������жϣ�������һ��������Զ�ɾ�����ϵ����ݡ�
	 * @param entity �ʴ�ʵ����
	 */
	 

	public static void insertInfo(String nickname,AnswerHelperEntity entity) {
		// TODO Auto-generated method stub
		ArrayList<AnswerHelperEntity> result=new ArrayList<AnswerHelperEntity>();
		
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			GetOrUpdateAnswerHelper.insertAndJudgeNeedDelete_answerhelper(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase(),nickname, entity);
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
	 * ����IDɾ����Ϣ
	 * @param id
	 */
	public static void delInfo(int id) {
		// TODO Auto-generated method stub
		ArrayList<AnswerHelperEntity> result=new ArrayList<AnswerHelperEntity>();
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			GetOrUpdateAnswerHelper.delete_answerhelper(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase(), id);
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "ɾ���ɹ�");
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "ɾ��ʧ��");
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
	 * ɾ��ȫ����Ϣ
	 * @param id
	 */
	public static void delAllInfo() {
		// TODO Auto-generated method stub
		ArrayList<AnswerHelperEntity> result=new ArrayList<AnswerHelperEntity>();
		LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
				.getCur_Activity()).getlock().lock();
			
		try {
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().beginTransaction();
			GetOrUpdateAnswerHelper.delete_All_answerhelper(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase());
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "ȫ��ɾ���ɹ�");
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "ȫ��ɾ��ʧ��");
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
