package com.tts168.autoset.tools.commen;






import com.tts168.autoset.tools.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class AlertUtils {
public static void SimpleAlert(Context c,String title,String content){
	new AlertDialog.Builder(c)  
    .setTitle(title)  
   .setMessage(content)   
   .setPositiveButton("确定",   
   new DialogInterface.OnClickListener(){  
             public void onClick(DialogInterface dialoginterface, int i){   
                            //按钮事件   
                       
                         }   
                 }).show();   
} 

/**
 * 在查询修改订单后按返回键时提示是否保存后返回
 * @param c
 * @param title
 * @param content
 */
public static void SimpleAlert_SUBMIT(Context c,String title,String content){
	MyApplication.getInstance().setCur_Activity((Activity) c);
	new AlertDialog.Builder(c)  
    .setTitle(title)  
   .setMessage(content).setNeutralButton("取消",  new DialogInterface.OnClickListener(){  
             public void onClick(DialogInterface dialoginterface, int i){   
                            //按钮事件   
                       
                         }   
                 })  
   .setPositiveButton("确定",   
   new DialogInterface.OnClickListener(){  
             public void onClick(DialogInterface dialoginterface, int i){   
                            //按钮事件   
            	 //做订单提交给服务器的操作
                       //Toast.makeText(Tools.c,"提交成功！" , 1000).show();
                         }   
                 }).show();   
} 

}
