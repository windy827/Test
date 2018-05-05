package com.example.hellojni;
/**
 * �����ļ��Ĳ���
 * @author Ԭ��
 *
 */
public class DBOption {

	
	//���������ݿ�����Ŀ���
	/**
	 * �汾��Ϣ
	 */
	public static final int	TYPE_VERSION_ID=	0;
	/**
	 * ���ݿ��С
	 */
	public static final int	TYPE_DATESIZE_ID	=1;
	/**
	 * �û���
	 */
	public static final int	TYPE_USERNAME_ID	=2;
	/**
	 * ����
	 */
	public static final int	TYPE_CODE_ID	=3;
	/**
	 * ������
	 */
	public static final int	TYPE_SPEAKER_ID	=4;
	/**
	 * ���㡢�������
	 */
	public static final int	TYPE_TIME_REMIND_ID=5;
	
	/**
	 * ������
	 */
	public static final int	TYPE_TQ_ID=6;
	/**
	 * Ц����
	 */
	public static final int	TYPE_JOKE_ID=7;
	/**
	 * WIFI��Ϣ��ȡʱ���
	 */
	public static final int	TYPE_INFO_GET_ID	=8;
	/**
	 * ���ս�����������
	 */
	public static final int	TYPE_FESTIVAL_ID	=9;
	/**
	 * �Ʊ��ǳ�
	 */
	public static final int	TYPE_YB_NAME_ID	=10;
	/**
	 * ���Ӹ���
	 */
	public static final int	TYPE_CLK_HEAD_ID	=11;
	/**
	 * ����������ϸ��Ϣ����������������Ϣ��
	 */
	public static final int	TYPE_CLK_CONTENT_ID=12;
	/**
	 * ��������Ԥ���� ����������ǰ����������ʱ�䡢��
	 */
	public static final int	TYPE_BIR_HEAD_ID	=13;
	/**
	 * ����������������
	 */
	public static final int	TYPE_BIR_CONTENT_ID=14;
	/**
	 * У����
	 */
	public static final int	TYPE_CHECKCODE_CONTENT=15;
	/**
	 * 
	 *�ڽ��д˲���֮ǰ��Ҫ�Ƚ��нṹ���ʼ�� �ļ���������
	 * @param type ���������ݵ�����
	 * @param changeDate ��������������
	 * @param changeDateLen ���������ݵĳ���
	 */
	 public static native byte[] getDBOption( int type,byte[] changeDate,int changeDateLen);
	 /**
	  * ��ʼ���ṹ������
	  * @param allDate
	  */
	 public static native void InitStruct(byte[] allDate);
	 /**
	  * 
	  * @param type ��Ŀ���
	  * @param changeDateLen ��ʾ�ڼ����������ѻ����������ѣ���ȡ��������ʱ���ֵ��������
	  * @return
	  */
	 public static native byte[]  getDataBaseRead(int type,int changeDateLen);
	 
	 static {
         System.loadLibrary("DBOption");
     }

}
