package com.tts168.autoset.setfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.GetSDCardPath;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class FileConnection {
	Context context;
	SQLiteDatabase sqliteDatabase = null;
	String sdcardpath;
	String path;
	//�õ�SD��·��
	private final String DATABASE_PATH = android.os.Environment.
			getExternalStorageDirectory().getAbsolutePath();
	File file;
	public static String Filename;
	//���ݿ���
	private final String DATABASE_FILENAME;

	public FileConnection(Context context) {
		this.context = context;
		DATABASE_FILENAME = "yunbaores.dat";
		//�õ����ݿ������·����
		Filename = DATABASE_PATH + "/" + DATABASE_FILENAME;
		sdcardpath=android.os.Environment.getExternalStorageDirectory().getPath();
		file = new File(Filename);
	}
/**
 * �õ��ļ���Byte���鷽�����
 * @return
 */
	public byte[] getFileByteArray() {
		copydb();
		
		byte[] fileByte = null;
		try {
			fileByte = readFileByteArray(Filename);
			Log.d("filecontent",new String(fileByte,"GBK"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileByte;

	}

	//������  
			public static byte[] readFileByteArray(String fileName) throws IOException{   
				 byte[]buffer=null;
			  try{   
			         FileInputStream fin = new FileInputStream(fileName);   
			         int length = fin.available();
			         System.out.println(length);
			         buffer = new byte[length];   
			         fin.read(buffer);       
			        
			         fin.close();     
			        
			     }   
			     catch(Exception e){   
			         e.printStackTrace();   
			     }   
			  return buffer;
			  
			}     
	private void copydb() {
		InputStream inputStream = null;
		FileOutputStream fos = null;
		try {

			if (file.exists() == false)
			{
				
				fos = new FileOutputStream(file);// sdcard

				inputStream = this.context.getResources().getAssets().open("yunbaores.dat");

		
				byte[] buffer = new byte[inputStream.available()];
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
