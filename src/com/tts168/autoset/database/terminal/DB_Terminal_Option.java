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
 * ���ռ����յ����ݿ����
 * @author Ԭ��
 *
 */
public class DB_Terminal_Option {
	
	
	/**
	 *�жϵ�ǰ�ķ��������Ƿ��Ѿ�����
	 * @param groupName ��ǰ��������
	 * 
	 * @return trueΪ�Ѿ�����
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
		 * ��ӷ�������
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
					//��Ϊ�Ǹ�����������ȥ������������Բ������������Ϊ""������
				
					GetOrUpdateTerminal.insert_Terminal(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase(), groupName, GetOrUpdateTerminal.DEFAULT_NULL_CHILDMAC,"");
					ToastTools.short_Toast(c, "��ӷ���ɹ���");
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
		 * ɾ����������[�ҵ��豸ΪĬ�Ϸ��鲻��ɾ��]
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
					//��Ϊ�Ǹ�����������ȥ������������Բ������������Ϊ""������
					ArrayList<HashMap<String,Object>>list=GetOrUpdateTerminal.getInfoByGroupName(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase(), groupName);
					for(int i=0;i<list.size();i++){
						if(list.get(i).get("child_mac_ip").equals(GetOrUpdateTerminal.DEFAULT_NULL_CHILDMAC)){
							//ɾ��
							GetOrUpdateTerminal.delete_TerminalByGroupName(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
									.getCur_Activity()).getSqliteDatabase(), groupName);
						}else{
							//���¡�Ĭ�Ͻ���groupName�������Ƶ�GetOrUpdateTerminal.MYDEVICES�ϡ�
							GetOrUpdateTerminal.insertOrUpdate_Terminal(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
									.getCur_Activity()).getSqliteDatabase(), GetOrUpdateTerminal.MYDEVICES,(String)list.get(i).get("child_mac_ip"),(String)list.get(i).get("child_name") );
							
						}
					}
					ToastTools.short_Toast(c, "ɾ������ɹ���");
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
		 * �õ����������б�
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
				//��Ϊ�Ǹ�����������ȥ������������Բ������������Ϊ""������
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
		 * ��������
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
					//��Ϊ�Ǹ�����������ȥ������������Բ������������Ϊ""������
					GetOrUpdateTerminal.insertOrUpdate_Terminal(LarkSmartDataBaseConnection.getInstance(MyApplication.getInstance()
							.getCur_Activity()).getSqliteDatabase(), groupName, child_mac,childName);
					ToastTools.short_Toast(c, "���·���ɹ���");
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
