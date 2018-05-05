package com.tts168.autoset.tools;
/** 
 * 1.���ݿ�汾  DB_VERSION;
 * 2.���ݰ���С PACKAGE_SIZE;
 * 3.WiFi�û��� WIFI_NAME;
 * 4.WiFi���� WIFI_PASSWORD;
 * 5.������ѡ�� ANOUNCER;
 * 6.���㱨ʱ���� SWICH_FULL_READTIME;
 * 7.��㱨ʱ���� SWICH_HALF_READTIME;
 * 8.�����㱨ʱ����ʼʱ�� FULL_READTIME_START��
 * 9.�����㱨ʱ������ʱ�� FULL_READTIME_END;
 * 10.�������������� CITY_CLICK;
 * 11.������˫������ CITY_DOU_CLICK;
 * 12.�������������� CITY_LONG_PRESS;
 * 13.Ц������������ LAUGH_CLICK;
 * 14.Ц����˫������ LAUGH_DOU_CLICK;
 * 15.Ц������������ LAUGH_LONG_PRESS;
 * 16.WiFi�Զ���ȡʱ���ǡ�12�� ISAutoGetWifiTime;
 * 17.���պ�ף������ SWICH_FESTIVE;
 * 18.��ʮ�Ľ������� SWICH_JIEQI;
 * 19.������ǰ���� FESTIVE_AHEAD_DAYS;
 * 20.��1�β���ʱ�� FESTIVE_PLAYTIME1;
 * 21.��2�β���ʱ�� FESTIVE_PLAYTIME2;
 * 22.��3�β���ʱ�� FESTIVE_PLAYTIME3;
 //-------���Ӳ��֡�10���Ŀռ��ظ������͡�
 * 23.�Ƿ��� SWITCH_DAILY_ALART_ISOPEN;
 * 24.ʱ�� DAILY_ALART_ISOPEN;
 * 25.���� DAILY_ALART_CYCLE;
 * 26.5���Ӻ������ѱ�� DAILY_ALART_ISREREMIND;
 * 27.���� DAILY_ALART_INCIDENT;
 //��������
  * 28.���յ����ѣ���Ч���� COMMERATION_ALART_EFFCTIVE_TOTAL;
  * 29.���յ����ѣ���Ч������ǰ���� COMMERATION_ALART_AHEAD_DAYS;
  * 30.���յ����ѣ���1�β���ʱ�� COMMERATION_ALART_PLAYTIME1;
  * 31.���յ����ѣ���2�β���ʱ�� COMMERATION_ALART_PLAYTIME2;
  * 32.���յ����ѣ���3�β���ʱ�� COMMERATION_ALART_PLAYTIME3;
     //-------�����������ݲ��֡�30���Ŀռ��ظ������͡�
  * 33.�Ƿ��� SWITCH_COMMERATION_ALART_ISOPEN;
  * 34.���������� COMMERATION_ALART_TYPE;
  * 35.���� COMMERATION_ALART_DATE;
  * 36.�¼� COMMERATION_ALART_INCIDENT;
  * 37.ף���� COMMERATION_ALART_DESCRIBE;
 */
public class SendDataTools {
	/**
	 * 1.���ݿ�汾
	 */
	public static String DB_VERSION;
	/**
	 * 2.���ݰ���С
	 */
	public static int PACKAGE_SIZE;
	/**
	 * 3.WiFi�û���
	 */
	public static String WIFI_NAME;
	/**
	 * 4.WiFi����
	 */
	public static String WIFI_PASSWORD;
	/**
	 *5.������ѡ��[8]
	 */
	public static String ANOUNCER;
	
	/**
	 * 6.���㱨ʱ����
	 */
	public static boolean SWICH_FULL_READTIME;
	/**
	 * 7.��㱨ʱ����
	 */
	public static boolean SWICH_HALF_READTIME;
	/**
	 * 8.�����㱨ʱ����ʼʱ��
	 */
	public static String FULL_READTIME_START;
	/**
	 * 9.�����㱨ʱ������ʱ��
	 */
	public static String FULL_READTIME_END;
	/**
	 * 10.��������������
	 */
	public static String CITY_CLICK;
	/**
	 * 11.������˫������
	 */
	public static String CITY_DOU_CLICK;
	/**
	 * 12.��������������
	 */
	public static String CITY_LONG_PRESS;
	/**
	 * 13.Ц������������
	 */
	public static String LAUGH_CLICK;
	/**
	 * 14.Ц����˫������
	 */
	public static String LAUGH_DOU_CLICK;
	/**
	 * 15.Ц������������
	 */
	public static String LAUGH_LONG_PRESS;
	/**
	 * 16.WiFi�Զ���ȡʱ���ǡ�12����¼WiFi�Զ���ȡʱ���ǵ����飬��ʮ����������˼�Ϊ1��Ϊ��Ǽ�Ϊ0
	 */
	public static int []ISAutoGetWifiTime;
	/**
	 * �Զ���ȡWifiʱ��
	 */
	public static final int ISAutoGetWifiTime_YES=1;
	/**
	 * ���Զ���ȡWifiʱ��
	 */
	public static final int ISAutoGetWifiTime_NO=0;
	/**
	 * 17.���պ�ף������
	 */
	public static int SWICH_FESTIVE;
	/**
	 * 18.��ʮ�Ľ�������
	 */
	public static int SWICH_JIEQI;
	
	/**
	 * 19.������ǰ����
	*/
	public static int FESTIVE_AHEAD_DAYS;
	/**
	 * 20.��1�β���ʱ��
	*/
	public static String FESTIVE_PLAYTIME1;
	/**
	 * 21.��2�β���ʱ��
	*/
	public static String FESTIVE_PLAYTIME2;
	/**
	 * 22.��3�β���ʱ��
	*/
	public static String FESTIVE_PLAYTIME3;
	
	
	 //-------���Ӳ��֡�10���Ŀռ��ظ������͡�
	/*
	*23.�Ƿ���
	 * 24.ʱ��
	 * 25.����
	 * 26.5���Ӻ������ѱ��
	 * 27.����
	 */
	/**
	 * 23.�Ƿ���
	 */
	public static int SWITCH_DAILY_ALART_ISOPEN;
	/**
	 * ��ȡ��ÿ�����������
	 */
	public static final int DAILY_ALART_LIMIT_TOTAL=10; 
	/**
	 * 24.ʱ��
	 */
	public static String DAILY_ALART_ISOPEN;
	/**
	 * 25.����[1]
	 */
	public static int DAILY_ALART_CYCLE;
	/**
	 * 26.5���Ӻ������ѱ��[1]
	 */
	public static int DAILY_ALART_ISREREMIND;
	/**
	 * 27.����
	 */
	public static String DAILY_ALART_INCIDENT;
	
	
	//��������
	  /* 28.���յ����ѣ���Ч����
	  * 29.���յ����ѣ���Ч������ǰ����
	  * 30.���յ����ѣ���1�β���ʱ��
	  * 31.���յ����ѣ���2�β���ʱ��
	  * 32.���յ����ѣ���3�β���ʱ��
	  * */
	/**
	 * 28.���յ����ѣ���Ч����[2]
	 */
	public static int COMMERATION_ALART_EFFCTIVE_TOTAL;
	/**
	 * 29.���յ����ѣ���Ч������ǰ����
	 */
	public static int COMMERATION_ALART_AHEAD_DAYS;
	/**
	 * 30.���յ����ѣ���1�β���ʱ��
	*/
	public static String COMMERATION_ALART_PLAYTIME1;
	/**
	 * 31.���յ����ѣ���2�β���ʱ��
	*/
	public static String COMMERATION_ALART_PLAYTIME2;
	/**
	 * 32.���յ����ѣ���3�β���ʱ��
	*/
	public static String COMMERATION_ALART_PLAYTIME3;
	     //-------�����������ݲ��֡�30���Ŀռ��ظ������͡�
	  /* 33.�Ƿ���
	  * 34.����������
	  * 35.����
	  * 36.�¼�
	  * 37.ף����
	  */
	/**
	 * ��ȡ��ÿ�����������
	 */
	public static final int COMMERATION_ALART_LIMIT_TOTAL=30; 
	/**
	 * 33.�Ƿ���
	 */
	public static int SWITCH_COMMERATION_ALART_ISOPEN;
	/**
	 * 34.����������
	 */
	public static int COMMERATION_ALART_TYPE;
	/**
	 * 35.����
	 */
	public static String COMMERATION_ALART_DATE;
	/**
	 * 36.�¼�
	 */
	public static int COMMERATION_ALART_INCIDENT;
	/**
	 * 37.ף����
	 */
	public static int COMMERATION_ALART_DESCRIBE;
	
	/**
	 * ��07:30ת����0730
	 * @param time
	 * @return
	 */
	public static String getFormatTime(String time){
		String formatTime="";
		formatTime=time.split(Tools.FLAG_TIME_MAOHAO)[0]+time.split(Tools.FLAG_TIME_MAOHAO)[1];
		return formatTime;
	}
	
	/**
	 * ����true��false ת���ɴ�СΪ2 ��byte ����//1�����ǰ����ֽ���
	 * @param isTrue
	 * @return
	 */
		public static byte[]boolean2ByteArray2(boolean isTrue){
			byte[]result=new byte[2];
			if(isTrue){
				result[0]=1;
			}
			return result;
		}
		/**
		 * ���ݴ�СΪ2��byte����ת����true��false//1�����ǰ����ֽ���
		 * @param isTrue
		 * @return
		 */
		public static boolean byteArray2Boolean(byte[]isTrue){
			boolean result=false;
			if(isTrue[0]==1){
				result=true;
			}
			return result;
		}
	
	
	
}
