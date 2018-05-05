package com.tts168.autoset.setfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;

public class SetFileGetAndUpdateOptions {
	/**
	 * keys[i] i对应的值
	 *          0版本号(VERSION)，1 代表SSID(SSID)，2 代表密码(PASSWORD)
 *                      3 备用1(BACKUP1)，4 备用2(BACKUP2)
 *                      5 整点报时开关(FULL)， 6 半点报时开关(HALF)
 *                      7 天气键单击城市(SHORET_WEARTH)，8 天气键双击城市(DOUBLE_WEARTH)，
 *                      9 天气键长按城市(LONG_WEARTH) ,10 笑话键单击类型(SHORET_LAUGH)，11 笑话键双击类型(DOUBLE_LAUGH)，
 *                      12 笑话键长按类型(LONG_LAUGH)
 *               13 无对话多长时间进入睡眠(SLEEP)；
 *               14 WiFi自动获取时间标记(AUTOWIFI)；
 *               15 备用2(BACKUP3)；
 *               16 Crc16 校验码(Crc16);
	 */
	public static final String[]keys=new String[]{"key1","key2","key3","key4","key5","key6","key7","key8",
            "key9","key10","key11","key12","key13","key14","key15","key16","key17"};
	/**
	 * 更新配置文件数据 ,start,end值参考FileStartAndEndTag类里面对应的参数值
	 * 
	 * @param fileByteArray
	 *            文件的Byte数组
	 * @param fileName
	 *            文件名
	 * @param updateContent
	 *            更新的内容
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束值，end-start=length byte数组长度 start-end 值：0-4 代表版本号，4-36
	 *            代表SSID，36-100 代表密码 100-108 备用1，108-112 备用2， 112-113 整点报时开关，
	 *            113-114 半点报时开关， 114-134 天气键单击城市，134-154 天气键双击城市，154-174
	 *            天气键长按城市， 174-206 笑话键单击类型，206-238 笑话键双击类型，238-270 笑话键长按类型，
	 *            270-272 无对话多长时间进入睡眠； 272-296 WiFi自动获取时间标记； 296-318 备用； 318-320
	 *            Crc16 校验码;
	 * @throws IOException
	 */
	public static void UpdateSetFile(
			String updateContent, int start, int end) throws IOException {
		try {
			 FileConnection fc=new FileConnection(MyApplication.getInstance().getCur_Activity());
				byte[]fileByteArray=fc.getFileByteArray();
			byte[] fileByte = fileByteArray;
			File file = new File(FileConnection.Filename);
			
		
			FileOutputStream fout = new FileOutputStream(file);
			int leng = end - start;
			byte[] temp = new byte[leng];
			byte[] bytes = updateContent.getBytes(Tools.ENCODING_TYPE);

			for (int j = 0; j < bytes.length; j++) {
				temp[j] = bytes[j];
			}
			for (int i = start; i < end; i++) {

				fileByte[i] = temp[i - start];
			}
			fout.write(fileByte);
			fout.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 记录文件指定位置标记的起始位置 start, end
	 * 
	 * @author Yuanjian
	 * @version
	 * @see start-end 值：0-4 代表版本号(VERSION)，4-36 代表SSID(SSID)，36-100
	 *      代表密码(PASSWORD) 100-108 备用1(BACKUP1)，108-112 备用2(BACKUP2) 112-113
	 *      整点报时开关(FULL)， 113-114 半点报时开关(HALF) 114-134
	 *      天气键单击城市(SHORET_WEARTH)，134-154 天气键双击城市(DOUBLE_WEARTH)，154-174
	 *      天气键长按城市(LONG_WEARTH) 174-206 笑话键单击类型(SHORET_LAUGH)，206-238
	 *      笑话键双击类型(DOUBLE_LAUGH)，238-270 笑话键长按类型(LONG_LAUGH) 270-272
	 *      无对话多长时间进入睡眠(SLEEP)； 272-296 WiFi自动获取时间标记(AUTOWIFI)； 296-318
	 *      备用2(BACKUP3)； 318-320 Crc16 校验码(Crc16);
	 * */
	public static HashMap<String, String> getFileContent(
			byte[] fileArrayByte) {
		HashMap<String, String> info = new HashMap<String, String>();
		
		byte[] temp1 = new byte[FileStartAndEndTag.VERSION_END
								- FileStartAndEndTag.VERSION_START];
		byte[] temp2 = new byte[FileStartAndEndTag.SSID_END
								- FileStartAndEndTag.SSID_START];
		byte[] temp3= new byte[FileStartAndEndTag.PASSWORD_END
								- FileStartAndEndTag.PASSWORD_START];
		
		byte[] temp4 = new byte[FileStartAndEndTag.BACKUP1_END
								- FileStartAndEndTag.BACKUP1_START];
		byte[] temp5 = new byte[FileStartAndEndTag.BACKUP2_END
								- FileStartAndEndTag.BACKUP2_START];
		byte[] temp6 = new byte[FileStartAndEndTag.FULL_END
								- FileStartAndEndTag.FULL_START];
		byte[] temp7 = new byte[FileStartAndEndTag.HALF_END
								- FileStartAndEndTag.HALF_START];
		byte[] temp8 = new byte[FileStartAndEndTag.SHORET_WEARTH_END
								- FileStartAndEndTag.SHORET_WEARTH_START];
		byte[] temp9 = new byte[FileStartAndEndTag.DOUBLE_WEARTH_END
								- FileStartAndEndTag.DOUBLE_WEARTH_START];
		byte[] temp10 = new byte[FileStartAndEndTag.LONG_WEARTH_END
								- FileStartAndEndTag.LONG_WEARTH_START];
		byte[] temp11 = new byte[FileStartAndEndTag.SHORET_LAUGH_END
								- FileStartAndEndTag.SHORET_LAUGH_START];
		byte[] temp12 = new byte[FileStartAndEndTag.DOUBLE_LAUGH_END
								- FileStartAndEndTag.DOUBLE_LAUGH_START];
		byte[] temp13 = new byte[FileStartAndEndTag.LONG_LAUGH_END
								- FileStartAndEndTag.LONG_LAUGH_START];
		byte[] temp14 = new byte[FileStartAndEndTag.SLEEP_END
								- FileStartAndEndTag.SLEEP_START];
		byte[] temp15 = new byte[FileStartAndEndTag.AUTOWIFI_END
								- FileStartAndEndTag.AUTOWIFI_START];
		byte[] temp16 = new byte[FileStartAndEndTag.BACKUP3_END
								- FileStartAndEndTag.BACKUP3_START];
		byte[] temp17 = new byte[FileStartAndEndTag.Crc16_END
								- FileStartAndEndTag.Crc16_START];
		int len = fileArrayByte.length;
		for (int i = 0; i < len; i++) {
			if (i >= FileStartAndEndTag.VERSION_START
					&& i < FileStartAndEndTag.VERSION_END) {
				temp1[i - FileStartAndEndTag.VERSION_START] = fileArrayByte[i];
				
			} else if (i >= FileStartAndEndTag.SSID_START
					&& i < FileStartAndEndTag.SSID_END) {
				temp2[i - FileStartAndEndTag.SSID_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.PASSWORD_START
					&& i < FileStartAndEndTag.PASSWORD_END) {
				temp3[i - FileStartAndEndTag.PASSWORD_START] = fileArrayByte[i];

			}

			else if (i >= FileStartAndEndTag.BACKUP1_START
					&& i < FileStartAndEndTag.BACKUP1_END) {
				temp4[i - FileStartAndEndTag.BACKUP1_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.BACKUP2_START
					&& i < FileStartAndEndTag.BACKUP2_END) {
				temp5[i - FileStartAndEndTag.BACKUP2_START] = fileArrayByte[i];

			}

			else if (i >= FileStartAndEndTag.FULL_START
					&& i < FileStartAndEndTag.FULL_END) {
				temp6[i - FileStartAndEndTag.FULL_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.HALF_START
					&& i < FileStartAndEndTag.HALF_END) {
			
				temp7[i - FileStartAndEndTag.HALF_START] = fileArrayByte[i];

			}

			// WEARTH
			else if (i >= FileStartAndEndTag.SHORET_WEARTH_START
					&& i < FileStartAndEndTag.SHORET_WEARTH_END) {
				temp8[i - FileStartAndEndTag.SHORET_WEARTH_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.DOUBLE_WEARTH_START
					&& i < FileStartAndEndTag.DOUBLE_WEARTH_END) {
				temp9[i - FileStartAndEndTag.DOUBLE_WEARTH_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.LONG_WEARTH_START
					&& i < FileStartAndEndTag.LONG_WEARTH_END) {
				temp10[i - FileStartAndEndTag.LONG_WEARTH_START] = fileArrayByte[i];

			}

			// LAUGH
			else if (i >= FileStartAndEndTag.SHORET_LAUGH_START
					&& i < FileStartAndEndTag.SHORET_LAUGH_END) {
				temp11[i - FileStartAndEndTag.SHORET_LAUGH_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.DOUBLE_LAUGH_START
					&& i < FileStartAndEndTag.DOUBLE_LAUGH_END) {
				temp12[i - FileStartAndEndTag.DOUBLE_LAUGH_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.LONG_LAUGH_START
					&& i < FileStartAndEndTag.LONG_LAUGH_END) {
				temp13[i - FileStartAndEndTag.LONG_LAUGH_START] = fileArrayByte[i];

			}

			else if (i >= FileStartAndEndTag.SLEEP_START
					&& i < FileStartAndEndTag.SLEEP_END) {
				temp14[i - FileStartAndEndTag.SLEEP_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.AUTOWIFI_START
					&& i < FileStartAndEndTag.AUTOWIFI_END) {
				temp15[i - FileStartAndEndTag.AUTOWIFI_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.BACKUP3_START
					&& i < FileStartAndEndTag.BACKUP3_END) {
				temp16[i - FileStartAndEndTag.BACKUP3_START] = fileArrayByte[i];

			} else if (i >= FileStartAndEndTag.Crc16_START
					&& i < FileStartAndEndTag.Crc16_END) {
				temp17[i - FileStartAndEndTag.Crc16_START] = fileArrayByte[i];

			}

		}
		try {
			String VERSION = new String(temp1,Tools.ENCODING_TYPE);
			String SSID =new String(temp2,Tools.ENCODING_TYPE);
			String PASSWORD =new String(temp3,Tools.ENCODING_TYPE);

			String BACKUP1 = new String(temp4,Tools.ENCODING_TYPE);
			String BACKUP2 = new String(temp5,Tools.ENCODING_TYPE);

			String FULL = new String(temp6,Tools.ENCODING_TYPE);
			String HALF = new String(temp7,Tools.ENCODING_TYPE);

			String SHORET_WEARTH = new String(temp8,Tools.ENCODING_TYPE);
			String DOUBLE_WEARTH = new String(temp9,Tools.ENCODING_TYPE);
			String LONG_WEARTH = new String(temp10,Tools.ENCODING_TYPE);

			String SHORET_LAUGH = new String(temp11,Tools.ENCODING_TYPE);
			String DOUBLE_LAUGH = new String(temp12,Tools.ENCODING_TYPE);
			String LONG_LAUGH = new String(temp13,Tools.ENCODING_TYPE);

			String SLEEP = new String(temp14,Tools.ENCODING_TYPE);
			String AUTOWIFI = new String(temp15,Tools.ENCODING_TYPE);
			String BACKUP3 = new String(temp16,Tools.ENCODING_TYPE);
			String Crc16 = new String(temp17,Tools.ENCODING_TYPE);
			
			String []values=new String[]{VERSION,SSID,PASSWORD,BACKUP1,
					                    BACKUP2,FULL,HALF,SHORET_WEARTH,
					                    DOUBLE_WEARTH,LONG_WEARTH,SHORET_LAUGH,DOUBLE_LAUGH,
					                    LONG_LAUGH,SLEEP,AUTOWIFI,BACKUP3,
					                    Crc16};
			for(int i=0;i<keys.length;i++){
				info.put(keys[i], values[i].toString().trim());
				Log.d("myinfo", keys[i]+"--------->"+values[i]);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
}
