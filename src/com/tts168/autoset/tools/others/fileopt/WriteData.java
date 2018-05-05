package com.tts168.autoset.tools.others.fileopt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class WriteData {
	Context context;

	String sdcardpath;
	String path;
	//得到SD卡路径
	private final String DATABASE_PATH = android.os.Environment
	.getExternalStorageDirectory().getAbsolutePath();
	File file,file1;
	//数据库名
	private final String DATABASE_FILENAME,DATABASE_FILENAME1;

	public WriteData(Context context) {
		this.context = context;
		DATABASE_FILENAME = "mywave2.pcm";
		DATABASE_FILENAME1 = "mywave.txt";
		//得到数据库的完整路径名
		String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
		String databaseFilename1 = DATABASE_PATH + "/" + DATABASE_FILENAME1;
		sdcardpath=android.os.Environment.getExternalStorageDirectory().getPath();
		file = new File(databaseFilename);
		file1 = new File(databaseFilename1);
	}

	public void copydbtext(byte[]buffer) {
		InputStream inputStream = null;
		FileOutputStream fos = null;
		try {

//			if (file1.exists() == false)
//			{
				
				fos = new FileOutputStream(file1);// sdcard

		

	fos.write(buffer);

			
			
			//}

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

	
	public void copydb(byte[]buffer) {
		InputStream inputStream = null;
		FileOutputStream fos = null;
		try {

//			if (file.exists() == false)
//			{
				
				fos = new FileOutputStream(file);// sdcard		
			fos.write(buffer);
			//}

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
