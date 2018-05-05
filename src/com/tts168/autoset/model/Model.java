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
 * 一些模板，例如PopWindow，查询数据库事务
 * @author Administrator
 *
 */
public class Model {
	/**
	 * 一些模板，例如PopWindow
	 * @author Administrator
	 *
	 */
	public static void getSetDropListPopWin(Activity a1,final Button v,float widthScale,float heightScale,final ArrayList<HashMap<String,String>> al){
		/**
		 LayoutInflater inflater = LayoutInflater.from(a1);  
		
	    // 引入窗口配置文件  
	   // View view = inflater.inflate(R.layout.popsetting, null);  
	    // 创建PopupWindow对象  
	    final PopupWindow pop = new PopupWindow(view, (int) (v.getWidth()), android.support.v4.view.ViewPager.LayoutParams.WRAP_CONTENT, false);  
	  
	 
	    // 需要设置一下此参数，点击外边可消失  
	    pop.setBackgroundDrawable(new BitmapDrawable());  
	    //设置点击窗口外边窗口消失  
	    pop.setOutsideTouchable(true);  
	    // 设置此参数获得焦点，否则无法点击  
	    pop.setFocusable(true);  
	  pop.showAsDropDown(v,0,0);
	*/
	    
	 
	}
	
	
	/**
	 * 访问数据库的模板，查询数据库事务
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
