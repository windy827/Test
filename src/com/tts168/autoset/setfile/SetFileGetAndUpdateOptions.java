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
	 * keys[i] i��Ӧ��ֵ
	 *          0�汾��(VERSION)��1 ����SSID(SSID)��2 ��������(PASSWORD)
 *                      3 ����1(BACKUP1)��4 ����2(BACKUP2)
 *                      5 ���㱨ʱ����(FULL)�� 6 ��㱨ʱ����(HALF)
 *                      7 ��������������(SHORET_WEARTH)��8 ������˫������(DOUBLE_WEARTH)��
 *                      9 ��������������(LONG_WEARTH) ,10 Ц������������(SHORET_LAUGH)��11 Ц����˫������(DOUBLE_LAUGH)��
 *                      12 Ц������������(LONG_LAUGH)
 *               13 �޶Ի��೤ʱ�����˯��(SLEEP)��
 *               14 WiFi�Զ���ȡʱ����(AUTOWIFI)��
 *               15 ����2(BACKUP3)��
 *               16 Crc16 У����(Crc16);
	 */
	public static final String[]keys=new String[]{"key1","key2","key3","key4","key5","key6","key7","key8",
            "key9","key10","key11","key12","key13","key14","key15","key16","key17"};
	/**
	 * ���������ļ����� ,start,endֵ�ο�FileStartAndEndTag�������Ӧ�Ĳ���ֵ
	 * 
	 * @param fileByteArray
	 *            �ļ���Byte����
	 * @param fileName
	 *            �ļ���
	 * @param updateContent
	 *            ���µ�����
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����ֵ��end-start=length byte���鳤�� start-end ֵ��0-4 ����汾�ţ�4-36
	 *            ����SSID��36-100 �������� 100-108 ����1��108-112 ����2�� 112-113 ���㱨ʱ���أ�
	 *            113-114 ��㱨ʱ���أ� 114-134 �������������У�134-154 ������˫�����У�154-174
	 *            �������������У� 174-206 Ц�����������ͣ�206-238 Ц����˫�����ͣ�238-270 Ц�����������ͣ�
	 *            270-272 �޶Ի��೤ʱ�����˯�ߣ� 272-296 WiFi�Զ���ȡʱ���ǣ� 296-318 ���ã� 318-320
	 *            Crc16 У����;
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
	 * ��¼�ļ�ָ��λ�ñ�ǵ���ʼλ�� start, end
	 * 
	 * @author Yuanjian
	 * @version
	 * @see start-end ֵ��0-4 ����汾��(VERSION)��4-36 ����SSID(SSID)��36-100
	 *      ��������(PASSWORD) 100-108 ����1(BACKUP1)��108-112 ����2(BACKUP2) 112-113
	 *      ���㱨ʱ����(FULL)�� 113-114 ��㱨ʱ����(HALF) 114-134
	 *      ��������������(SHORET_WEARTH)��134-154 ������˫������(DOUBLE_WEARTH)��154-174
	 *      ��������������(LONG_WEARTH) 174-206 Ц������������(SHORET_LAUGH)��206-238
	 *      Ц����˫������(DOUBLE_LAUGH)��238-270 Ц������������(LONG_LAUGH) 270-272
	 *      �޶Ի��೤ʱ�����˯��(SLEEP)�� 272-296 WiFi�Զ���ȡʱ����(AUTOWIFI)�� 296-318
	 *      ����2(BACKUP3)�� 318-320 Crc16 У����(Crc16);
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
