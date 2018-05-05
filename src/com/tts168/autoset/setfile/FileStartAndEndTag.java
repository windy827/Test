package com.tts168.autoset.setfile;
/**
 * ��¼�ļ�ָ��λ�ñ�ǵ���ʼλ�� start, end
 * @author Yuanjian
 * @version
 * @see
 * start-end ֵ��0-4 ����汾��(VERSION)��4-36 ����SSID(SSID)��36-100 ��������(PASSWORD)
 *               100-108 ����1(BACKUP1)��108-112 ����2(BACKUP2)
 *               112-113 ���㱨ʱ����(FULL)�� 113-114 ��㱨ʱ����(HALF)
 *               114-134 ��������������(SHORET_WEARTH)��134-154 ������˫������(DOUBLE_WEARTH)��154-174 ��������������(LONG_WEARTH)
 *               174-206 Ц������������(SHORET_LAUGH)��206-238 Ц����˫������(DOUBLE_LAUGH)��238-270 Ц������������(LONG_LAUGH)
 *               270-272 �޶Ի��೤ʱ�����˯��(SLEEP)��
 *               272-296 WiFi�Զ���ȡʱ����(AUTOWIFI)��
 *               296-318 ����2(BACKUP3)��
 *               318-320 Crc16 У����(Crc16);
 *               */
public class FileStartAndEndTag {
	
	public static final int VERSION_START=0,VERSION_END=4;
	public static final int SSID_START=4,SSID_END=36;
	public static final int PASSWORD_START=36,PASSWORD_END=100;
	
	public static final int BACKUP1_START=102,BACKUP1_END=108;
	public static final int BACKUP2_START=108,BACKUP2_END=112;
	
	public static final int FULL_START=112,FULL_END=113;
	public static final int HALF_START=113,HALF_END=114;
	
	public static final int SHORET_WEARTH_START=114,SHORET_WEARTH_END=134;
	public static final int DOUBLE_WEARTH_START=134,DOUBLE_WEARTH_END=154;
	public static final int LONG_WEARTH_START=154,LONG_WEARTH_END=174;
	
	public static final int SHORET_LAUGH_START=174,SHORET_LAUGH_END=206;
	public static final int DOUBLE_LAUGH_START=206,DOUBLE_LAUGH_END=238;
	public static final int LONG_LAUGH_START=238,LONG_LAUGH_END=270;
	
	public static final int SLEEP_START=270,SLEEP_END=272;
	
	public static final int AUTOWIFI_START=272,AUTOWIFI_END=296;
	
	public static final int BACKUP3_START=296,BACKUP3_END=318;
	
	public static final int Crc16_START=318,Crc16_END=320;
}
