package com.tts168.autoset.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.tts168.autoset.tools.Tools;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;


/**
 * һЩģ�壬����PopWindow����ѯ���ݿ�����
 * @author Administrator
 *
 */
public class Model {
	/**
	 * һЩģ�壬����PopWindow
	 * @author Administrator
	 *
	 */
	public static void getSetDropListPopWin(Activity a1,final Button v,float widthScale,float heightScale,final ArrayList<HashMap<String,String>> al){
		/**
		 LayoutInflater inflater = LayoutInflater.from(a1);  
		
	    // ���봰�������ļ�  
	   // View view = inflater.inflate(R.layout.popsetting, null);  
	    // ����PopupWindow����  
	    final PopupWindow pop = new PopupWindow(view, (int) (v.getWidth()), android.support.v4.view.ViewPager.LayoutParams.WRAP_CONTENT, false);  
	  
	 
	    // ��Ҫ����һ�´˲����������߿���ʧ  
	    pop.setBackgroundDrawable(new BitmapDrawable());  
	    //���õ��������ߴ�����ʧ  
	    pop.setOutsideTouchable(true);  
	    // ���ô˲�����ý��㣬�����޷����  
	    pop.setFocusable(true);  
	  pop.showAsDropDown(v,0,0);
	*/
	    
	 
	}
	
	
	/**
	 * �������ݿ��ģ�壬��ѯ���ݿ�����
	 */

	private static void getInfo() {
		// TODO Auto-generated method stub
		/**
		if (Tools.con == null) {
			Tools.con = new LarkSmartDataBaseConnection(Tools.c);
		}
		if(Tools.db ==null){
				Tools.db = Tools.con.getSqliteDatabase();
			}
			
			synchronized (Tools.db) {
		try {
			Tools.db.beginTransaction();
		
			Tools.db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Tools.db.endTransaction();
			if (Tools.db != null) {
				Tools.db.close();
			}

		}
		}
		*/
	}
}
