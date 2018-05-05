package com.tts168.autoset.database.terminal;

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
 * 生日纪念日的数据库操作
 * @author 袁剑
 *
 */
public class DB_Terminal_Option {
	
	
	/**
	 *判断当前的分组名称是否已经存在
	 * @param groupName 当前分组名称
	 * 
	 * @return true为已经存在
	 */
		public static boolean isGroupNameExist(Context c,String groupName){
			boolean isExist=false;
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
			
				
				try {
					LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase().beginTransaction();
					ArrayList<String> groupnames=GetOrUpdateTerminal.getDistinctGroup_name(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase());
					for(int i=0;i<groupnames.size();i++){
						if(groupnames.get(i).equals(groupName)){
							isExist=true;
							break;
						}
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
			
			
			return isExist;
		}
		
		/**
		 * 添加分组名称
		 * @param c
		 * @param groupName
		 * @return
		 */
		public static void addGroupName(Context c,String groupName){		
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				try {
					LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase().beginTransaction();
					//因为是根据子项名称去搜索父项的所以不存在添加子项为""的问题
				
					GetOrUpdateTerminal.insert_Terminal(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase(), groupName, GetOrUpdateTerminal.DEFAULT_NULL_CHILDMAC,"");
					ToastTools.short_Toast(c, "添加分组成功！");
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
		 * 删除分组名称[我的设备为默认分组不可删除]
		 * @param c
		 * @param groupName
		 * @return
		 */
		public static void deleteGroupName(Context c,String groupName){
				
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getlock().lock();
				try {
					LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase().beginTransaction();
					//因为是根据子项名称去搜索父项的所以不存在添加子项为""的问题
					ArrayList<HashMap<String,Object>>list=GetOrUpdateTerminal.getInfoByGroupName(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase(), groupName);
					for(int i=0;i<list.size();i++){
						if(list.get(i).get("child_mac_ip").equals(GetOrUpdateTerminal.DEFAULT_NULL_CHILDMAC)){
							//删除
							GetOrUpdateTerminal.delete_TerminalByGroupName(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
									.getCur_Activity()).getSqliteDatabase(), groupName);
						}else{
							//更新【默认将该groupName的内容移到GetOrUpdateTerminal.MYDEVICES上】
							GetOrUpdateTerminal.insertOrUpdate_Terminal(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
									.getCur_Activity()).getSqliteDatabase(), GetOrUpdateTerminal.MYDEVICES,(String)list.get(i).get("child_mac_ip"),(String)list.get(i).get("child_name") );
							
						}
					}
					ToastTools.short_Toast(c, "删除分组成功！");
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
		 * 得到分组名称列表
		 * @param c
		 * @param groupName
		 * @return
		 */
		public static ArrayList<HashMap<String,String>> getGroupName(Context c){
			ArrayList<HashMap<String,String>>result=new ArrayList<HashMap<String,String>>();
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				
				
			try {
				LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase().beginTransaction();
				//因为是根据子项名称去搜索父项的所以不存在添加子项为""的问题
				ArrayList<String>al_temp=GetOrUpdateTerminal.getDistinctGroup_name(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
						.getCur_Activity()).getSqliteDatabase());
				for(int i=0;i<al_temp.size();i++){
					HashMap<String,String>temp=new HashMap<String, String>();
					temp.put("name", al_temp.get(i));
					result.add(temp);
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
		 * 更换分组
		 * @param c
		 * @param groupName
		 * @param childName
		 * @return
		 */
		public static void changeGroupName(Context c,String groupName,String child_mac,String childName){
			
			LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
					.getCur_Activity()).getlock().lock();
				
				try {
					LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase().beginTransaction();
					//因为是根据子项名称去搜索父项的所以不存在添加子项为""的问题
					GetOrUpdateTerminal.insertOrUpdate_Terminal(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase(), groupName, child_mac,childName);
					ToastTools.short_Toast(c, "更新分组成功！");
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

				
					LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getlock().unlock();
			}
	
			
			
		}
		
		
		


}
