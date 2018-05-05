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
	 * searchFile 查找文件并加入到ArrayList 当中去 
	 *  @String keyword 查找的关键词 
	 *  @File filepath  查找的目录 
	 * */  
	private void searchFile(String keyword,File filepath)  
	{   
	  
	   //判断SD卡是否存在  
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
	                  //如果目录可读就执行（一定要加，不然会挂掉）  
	                  if(file.canRead()){  
	                   searchFile(keyword,file);  //如果是目录，递归查找  
	                  }  
	    }  
	                 else {    
	                   //判断是文件，则进行文件名判断  
	                            try {     
	                             if (file.getName().endsWith(keyword)||file.getName().indexOf(keyword.toUpperCase()) > -1)   
	                             {     
	                            	 HashMap<String, Object>rowItem = new HashMap<String, Object>();  
	                                       
	                            	 Log.d("FINDSONG","找到歌曲了");
	                                        rowItem.put(MUSIC_NAME, file.getName());// 加入名称  
	                                        rowItem.put(MUSIC_PATH, file.getAbsolutePath());  // 加入路径  
	                                        rowItem.put(MUSIC_SIZE, file.length());   // 加入文件大小  
	                                        al_info11.add(rowItem);  
//	                                        Message msg=new Message();
//	                                        msg.what=MainTestActivity.MSG_WHAT_UPDATE;
//	                                        handler.dispatchMessage(msg);
	                                 }     
	                           } catch(Exception e) {     
	                                    Toast.makeText(context,"查找发生错误", Toast.LENGTH_SHORT).show();     
	                            }     
	                   }  
	                }  
	     }  
	    }  
	}  
}
