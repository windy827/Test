package com.tts168.autoset.tools.localmusic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class SearchFileThread extends Thread{
	Context context;
	String key;
	String rootPath;
	public static ArrayList<HashMap<String, Object>>al_info11;
	public static final String FindSongType=".mp3";
	Handler handler;
	
	public static final String MUSIC_NAME="musicName";
	public static final String MUSIC_PATH="path";
	public static final String MUSIC_SIZE="size";
	private final String DATABASE_PATH =  android.os.Environment.
			getExternalStorageDirectory().getAbsolutePath();
	public SearchFileThread(Context context,Handler handler){
		this.context=context;
		this.handler=handler;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Looper.prepare();
		searchFile(FindSongType,new File(DATABASE_PATH));
		super.run();
		Looper.loop();
	}
	
	
	/* 
	 * searchFile �����ļ������뵽ArrayList ����ȥ 
	 *  @String keyword ���ҵĹؼ��� 
	 *  @File filepath  ���ҵ�Ŀ¼ 
	 * */  
	private void searchFile(String keyword,File filepath)  
	{   
	  
	   //�ж�SD���Ƿ����  
	     if (Environment.getExternalStorageState().equals(  
	                Environment.MEDIA_MOUNTED))  
	     {  
	      File[] files = filepath.listFiles();  
	          
	        if (files.length > 0)  
	        {  
	                for (File file : files)  
	                {  
	                 if (file.isDirectory())  
	                 {  
	                  //���Ŀ¼�ɶ���ִ�У�һ��Ҫ�ӣ���Ȼ��ҵ���  
	                  if(file.canRead()){  
	                   searchFile(keyword,file);  //�����Ŀ¼���ݹ����  
	                  }  
	    }  
	                 else {    
	                   //�ж����ļ���������ļ����ж�  
	                            try {     
	                             if (file.getName().endsWith(keyword)||file.getName().indexOf(keyword.toUpperCase()) > -1)   
	                             {     
	                            	 HashMap<String, Object>rowItem = new HashMap<String, Object>();  
	                                       
	                            	 Log.d("FINDSONG","�ҵ�������");
	                                        rowItem.put(MUSIC_NAME, file.getName());// ��������  
	                                        rowItem.put(MUSIC_PATH, file.getAbsolutePath());  // ����·��  
	                                        rowItem.put(MUSIC_SIZE, file.length());   // �����ļ���С  
	                                        al_info11.add(rowItem);  
//	                                        Message msg=new Message();
//	                                        msg.what=MainTestActivity.MSG_WHAT_UPDATE;
//	                                        handler.dispatchMessage(msg);
	                                 }     
	                           } catch(Exception e) {     
	                                    Toast.makeText(context,"���ҷ�������", Toast.LENGTH_SHORT).show();     
	                            }     
	                   }  
	                }  
	     }  
	    }  
	}  
}
