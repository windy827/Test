package com.tts168.autoset.database.answerhelper;

import java.util.ArrayList;
import java.util.HashMap;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.tts168.autoset.database.LarkSmartDataBaseConnection;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;
/**
 * 问答信息操作类
 * @author Administrator
 *
 */
public class AnswerHelperDBOptions {
	/**
	 * 获取问答的信息
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
	 *  添加数据【内不会进行判断，当大于一定量后会自动删除最老的数据】
	 * @param entity 问答实体类
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
	 * 根据ID删除信息
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
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "删除成功");
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "删除失败");
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
	 * 删除全部信息
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
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "全部删除成功");
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getSqliteDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "全部删除失败");
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
