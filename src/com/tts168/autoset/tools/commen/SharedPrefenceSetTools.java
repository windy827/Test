package com.tts168.autoset.tools.commen;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 *@author Ԭ��
 *@version 0.9.0.1
 * @see SharedPrefence��Editor��
	 * ��¼�Ƿ��ǵ�һ��ѧϰ��д����isFirst����
	 * ��¼�ϴε�¼�ɹ���Wifi�������루SSID,PassWord��
 ��¼���͵����ڣ���SendDate����
��¼���͵�ʱ�䣨��SendTime����
��¼�汾����Version����
��¼�̰�����(ShortPro)
��¼��������(LongPro)
��¼˫������(DoublePro)
��¼�̰�����(ShortCity)
��¼��������(LongCity)
��¼˫������(DoubleCity)

��¼�̰�Ц��(ShortLaugh)
��¼����Ц��(LongLaugh)
��¼˫��Ц��(DoubleLaugh)

��¼���㱨ʱ��Full��
��¼��㱨ʱ��Half��

��¼�޶Ի�˯��ʱ�䣨Sleep��
��¼Wifi�Զ���ȡʱ�䣨AutoWifi��
��¼Crc16У���루Crc16��
	 */
 

public class SharedPrefenceSetTools {

	public static final String SendDate="SendDate";
	public static final String SendTime="SendTime";
	
	public static final String ShortPro="ShortPro";
	public static final String LongPro="LongPro";
	public static final String DoublePro="DoublePro";
	
	public static final String ShortCity="ShortCity";
	public static final String LongCity="LongCity";
	public static final String DoubleCity="DoubleCity";
	
	public static final String ShortLaugh="ShortLaugh";
	public static final String LongLaugh="LongLaugh";
	public static final String DoubleLaugh="DoubleLaugh";
	public static final String SSID="SSID";
	public static final String PassWord="PassWord";
	public static final String Version="Version";
	public static final String Full="Full";
	public static final String Half="Half";
	public static final String Sleep="Sleep";//Default 0
	public static final String AutoWifi="AutoWifi";
	public static final String Crc16="Crc16";
	
	

	/**
	 * ��¼�������ѵ���ǰ����
	 */
	public static final String AheadDAYS="AheadDAYS";
	public static final int DEFAULT_AheadDAYS=0;
	
	/**
	 * ��¼�������ѵ���ǰ����
	 */
	public static final String AheadDAYS_Festive="AheadDAYS_Festive";
	public static final int DEFAULT_AheadDAYS_Festive=0;
	
	/**
	 * ��¼�������ѵ�һ�β�����ʱ��
	 */
	public static final String PLAYTIME1_Festive="PLAYTIME1_Festive";
	public static final String DEFAULT_PLAYTIME1_Festive="0700";
	/**
	 * ��¼�������ѵڶ��β�����ʱ��
	 */
	public static final String PLAYTIME2_Festive="PLAYTIME2_Festive";
	public static final String DEFAULT_PLAYTIME2_Festive="1230";
	/**
	 * ��¼�������ѵ����β�����ʱ��
	 */
	public static final String PLAYTIME3_Festive="PLAYTIME3_Festive";
	public static final String DEFAULT_PLAYTIME3_Festive="1900";
	
	
	/**
	 * ��¼�������ѵ�һ�β�����ʱ��
	 */
	public static final String PLAYTIME1="PLAYTIME1";
	public static final String DEFAULT_PLAYTIME1="0700";
	/**
	 * ��¼�������ѵڶ��β�����ʱ��
	 */
	public static final String PLAYTIME2="PLAYTIME2";
	public static final String DEFAULT_PLAYTIME2="1230";
	/**
	 * ��¼�������ѵ����β�����ʱ��
	 */
	public static final String PLAYTIME3="PLAYTIME3";
	public static final String DEFAULT_PLAYTIME3="1900";
	
	/**
	 * ��¼������ʼ��ʱ��
	 */
	public static final String STARTTIME="STARTTIME";
	public static final String DEFAULT_STARTTIME="0700";
	
	/**
	 * ��¼����������ʱ��
	 */
	public static final String ENDTIME="ENDTIME";
	public static final String DEFAULT_ENDTIME="2100";
	
	/**
	 * ���������ã�enunciators��
	 */
	public static final String enunciators="enunciators";//����������
	/**
	 * Ĭ�Ϸ��������֣�Default_enunciator��
	 */
	public static final String Default_enunciator="����";//Ĭ�Ϸ���������
	public static SharedPreferences sp;
	public static Editor ed;
	public static final String SP_Flag="SETTING";

}
