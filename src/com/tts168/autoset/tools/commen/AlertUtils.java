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
   .setPositiveButton("ȷ��",   
   new DialogInterface.OnClickListener(){  
             public void onClick(DialogInterface dialoginterface, int i){   
                            //��ť�¼�   
                       
                         }   
                 }).show();   
} 

/**
 * �ڲ�ѯ�޸Ķ����󰴷��ؼ�ʱ��ʾ�Ƿ񱣴�󷵻�
 * @param c
 * @param title
 * @param content
 */
public static void SimpleAlert_SUBMIT(Context c,String title,String content){
	MyApplication.getInstance().setCur_Activity((Activity) c);
	new AlertDialog.Builder(c)  
    .setTitle(title)  
   .setMessage(content).setNeutralButton("ȡ��",  new DialogInterface.OnClickListener(){  
             public void onClick(DialogInterface dialoginterface, int i){   
                            //��ť�¼�   
                       
                         }   
                 })  
   .setPositiveButton("ȷ��",   
   new DialogInterface.OnClickListener(){  
             public void onClick(DialogInterface dialoginterface, int i){   
                            //��ť�¼�   
            	 //�������ύ���������Ĳ���
                       //Toast.makeText(Tools.c,"�ύ�ɹ���" , 1000).show();
                         }   
                 }).show();   
} 

}
