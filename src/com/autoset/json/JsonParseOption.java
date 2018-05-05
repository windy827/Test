package com.autoset.json;

import java.io.UnsupportedEncodingException;

/**
 * JSon�����ӿ�
 * ��ȡ���ݵ�˳�����ȸ�����������Ƹ�ֵ���������㣬Ȼ��ȡ������������ݣ���ϸ�ִ���
 * 
 * @author Ԭ�� set���Ⱥ�˳����setHead-->setObjectDomainHead-->insertObject...-->setObjectDomainTail
 * 
 */
public class JsonParseOption {
	/**
	 * setUserData��Ӧ��ID
	 */
	public static final int ID_setUserData=1;
	/**
	 * getUserData��Ӧ��ID
	 */
	public static final int ID_getUserData=2;
	//�������������������������Ƿ���ر�־λ
	/**
	 * ����
	 */
	public static final int FLAG_PUT=0x02;
	/**
	 * ������
	 */
	public static final int FLAG_NOTPUT=0x00;

	public static final String GET_HEARTBEATDATE="heartbeat";//�����ȡ�����û����� 
	public static final String GET_USERDATA	="getUserData";//�����ȡ�Ʊ��û����� 
	public static final String SET_USERDATA	="setUserData";//���������Ʊ��û�����
	public static final String INSERT_USERDATA="insertUserData";//����Ʊ��û�����
	public static final String DELETE_USERDATA="deleteUserData";//ɾ���Ʊ��û�����
	
	
	//�ⲿ�ն���Ҫ��ѯ��ͬ�����Ʊ�������ʱ�����������
	public static final String DOMAIN="domain";//���������ֶ�
	
	//һ�������Ʊ����ظ�β���ն�
	public static final String ERROR_CODE="errcode";//�������ֶ�
	public static final String ERROR_MESSAGE="errmsg";//������Ϣ�ֶ�

	//�ⲿ�ն�ϣ���Ʊ��������ú��״̬����
	public static final String INTENT_STATUS_PLAY="status_play";
	
	public static final String INTENT_SELECT="select";//����ѯ��
	public static final String INTENT_DOMAIN_UPDATE="domain_update";//(�����ã�������������и���)
	/**
	 * ��������Json��ͷ����ÿһ��Set������������ʱ����Ҫ������һ����
	 * @param domainName
	 * @param setOrGet
	 * @param intent
	 */
	public static native void setHead(byte[] domainName,byte[]setOrGet,byte[]intent);
	
	/**
	 * ����Object ��Domain��ͷ
	 * 
	 * @param domainName
	 *            ���������
	 *   @param         setOrGet
	 *   				�������ݻ��ǵõ����ݵ�����
	 *    @param   intent
	 *   				 ��ͼ
	 *   @param  isPut
	 *    			�Ƿ����FLAG_PUT��FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectDomainHead(int isPut);

	/**
	 * ���Object���ַ����ļ�ֵ��
	 * 
	 * @param name
	 *            ������
	 * @param value
	 *            ����Ӧ��ֵ
	 */
	public static native void insertObjectString(byte[] name, byte[] value);

	/**
	 * ���Object��double�͵ļ�ֵ��
	 * 
	 * @param name
	 *            ������
	 * @param value
	 *            ����Ӧ��ֵ
	 */
	public static native void insertObjectDouble(byte[] name, double value);

	/**
	 * ����Object ��Domain�Ĺ��أ�������ط�����������ڻ�����֮ǰ��β��
	 * 
	 * @param domainName
	 *            ���������
	 *             @param  isPut
	 *    			�Ƿ����FLAG_PUT��FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectDomainTail(byte[] domainName,int isPut);

	/**
	 * ����ObjectArray ��Domain��ͷ
	 * 
	 * @param domainName
	 *            ���������
	 *             @param         setOrGet
	 *   				�������ݻ��ǵõ����ݵ�����
	 *    @param   intent
	 *   				 ��ͼ
	 *   @param  isPut
	 *    			�Ƿ����FLAG_PUT��FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectArrayDomainHead(int isPut);
/**
 * ÿ�����һ�����ݵ�ʱ��Ҫ�����������
 */
	public static native void addObjectArrayHead();
	
	/**
	 * ���ObjectArray���ַ����ļ�ֵ��
	 * 
	 * @param name
	 *            ������
	 * @param value
	 *            ����Ӧ��ֵ
	 */
	public static native void insertObjectArrayString(int index,byte[] name, byte[] value);

	/**
	 * ���ObjectArray��double�͵ļ�ֵ��
	 * 
	 * @param name
	 *            ������
	 * @param value
	 *            ����Ӧ��ֵ
	 */
	public static native void insertObjectArrayDouble(int index,byte[] name, double value);
	/**
	 * ����ObjectArray ��Domain�Ĺ��ء�β��
	 * 
	 * @param domainName
	 *            ���������
	 * @param  isPut
	 *    			�Ƿ����FLAG_PUT��FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectArrayDomainTail(byte[] domainName,int isPut);
	
	/**
	 * �������õĽ��
	 */
	public static native byte[] setResult();

	/**
	 * Wifi
	 * 
	 * @param ssid
	 *            SSID����
	 * @param password
	 *            ����
	 * @param priority
	 *            ���ȼ�
	 * @return
	 */
	public static native byte[] setWifi(byte[] ssid, byte[] password,
			double priority);

	/**
	 * Net
	 * 
	 * @param name
	 *            �豸������
	 * @param ip
	 *            IP��ַ
	 * @param port
	 *            �˿ں�
	 * @return
	 */
	public static native byte[] setNet(byte[] name, byte[] ip, double port);

	/**
	 * 
	 * @param nickname
	 *            ��Ʒ����
	 * @param voiceman
	 *            ���Ի�������
	 * @param city
	 *            ���ڳ���
	 * @param logfilter
	 *            ��־�ϴ�����
	 * @return
	 */
	public static native byte[] setDatageneral(byte[] nickname,
			byte[] voiceman, byte[] city, double logfilter);

	/**
	 * ����������������趨���㡾��ȡ�����á�,���ص��������ڵ�����ĸ�������������һ�Ŀ�ѭ����ȡ
	 * 
	 * @param data
	 *            ���������
	 * @param domain
	 *            ���������
	 */
	public static native int setFocusByDomain(byte[] data, byte[] domain);
	/**
	 * ���ݵ�ǰ�����Ľ��㣬�Լ�����ĵڼ�����Ա�ļ������Ʒ��ض�Ӧ��int���ֵ����Ҫ���������ǵõ�ֵ�Ժ��ж��Ƿ�����㣬������Ϊtrue
	 * 
	 * @param num
	 *            �ڼ�����Ա
	 * @param keyName
	 *            ��Ա�ļ�������
	 * @return
	 */
	public static native int getFocusJsonGetBool(int num, byte[] keyName);

	/**
	 * ���ݵ�ǰ�����Ľ��㣬�Լ�����ĵڼ�����Ա�ļ������Ʒ��ض�Ӧ��String���ֵ
	 * 
	 * @param num
	 *            �ڼ�����Ա
	 * @param keyName
	 *            ��Ա�ļ�������
	 * @return
	 */
	public static native byte[] getFocusJsonGetString(int num, byte[] keyName);

	/**
	 * ���ݵ�ǰ�����Ľ��㣬�Լ�����ĵڼ�����Ա�ļ������Ʒ��ض�Ӧ��double���ֵ
	 * 
	 * @param num
	 *            �ڼ�����Ա
	 * @param keyName
	 *            ��Ա�ļ�������
	 * @return
	 */
	public static native double getFocusJsonGetNumber(int num, byte[] keyName);


	/**
	 * �õ�����
	 */
	public static native byte[] getJsonDomainName();
	
	static {
		System.loadLibrary("JsonOption");
	}

}
