package com.tts168.autoset.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class LarkSmartDataBaseConnection {
	Context context;
	SQLiteDatabase sqliteDatabase = null;
	String sdcardpath;
	String path;
	//�õ�SD��·��
	private final String DATABASE_PATH =  android.os.Environment.
			getExternalStorageDirectory().getAbsolutePath();
	File file;
	//���ݿ���
	public static final String DATABASE_FILENAME="autoset.ab";

	public static LarkSmartDataBaseConnection instance=null;
	
	Lock lock;
	public static LarkSmartDataBaseConnection getInstance(Context context){
		if(instance==null){
			instance=new LarkSmartDataBaseConnection(context);
		}
		return instance;
	}
	/**
	 * �õ��߳���
	 * @return
	 */
	public Lock getlock(){
		if(lock==null){
			lock=new ReentrantLock();
		}
		return lock;
	}
	public LarkSmartDataBaseConnection(Context context) {
		this.context = MyApplication.getInstance();
		//DATABASE_FILENAME = "autoset.ab";
		//�õ����ݿ������·����
		String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
		sdcardpath=android.os.Environment.getExternalStorageDirectory().getPath();
		file = new File(databaseFilename);
	}

	public SQLiteDatabase getSqliteDatabase() {
		if(this.sqliteDatabase==null||!this.sqliteDatabase.isOpen()){
			copydb();
			this.sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
		}
		
		return sqliteDatabase;

	}

	
	private void copydb() {
		InputStream inputStream = null;
		FileOutputStream fos = null;
		try {

			if (file.exists() == false)
			{
				int book = R.raw.autoset;
				fos = new FileOutputStream(file);// sdcard
				inputStream = this.context.getResources().openRawResource(book);		
				byte[] buffer = new byte[8 * 1024];// 8K
				int readCount = -1;
				while ((readCount = inputStream.read(buffer)) > 0) {
					Log.d("readCount", readCount + "");
					fos.write(buffer);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
