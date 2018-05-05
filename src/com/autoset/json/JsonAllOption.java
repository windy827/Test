package com.autoset.json;

import java.io.UnsupportedEncodingException;

/**
 * JSon�����ӿ�
 * ��ȡ���ݵ�˳�����ȸ�����������Ƹ�ֵ���������㣬Ȼ��ȡ������������ݣ���ϸ�ִ���
 * 
 * @author Ԭ�� set���Ⱥ�˳����setHead-->setObjectDomainHead-->insertObject...-->setObjectDomainTail
 * 
 */
public class JsonAllOption {
	

	public static class JsonType{
		public static final int TYPE_MAINJSON=1;
		public static final int TYPE_PARAMSJSON=2;
		public static final int TYPE_DataJson=6;
		public static final int TYPE_DOMAINJSON=3;
		public static final int TYPE_PlayObjectJson=4;
		public static final int TYPE_SixJson=5;
	}
	
	public static class NameAndValues{
		public static final String JSON_METHOD="method";
		public static final String JSON_PARAMS="params";
		public static final String JSON_DATA="data";
		public static final String JSON_ID="id";
		public static final String JSON_DELETEID="ID";//ɾ��ʱ��id��Ӧ�Ĺؼ���
		public static final String JSON_RESULT="result";//�Ʊ����ؽ���ؼ���
		public static final String JSON_ERROR="error";//�Ʊ����ش���ؼ���
	}
	
	public static final String GET_USERDATA	="getuserdata";//�����ȡ�Ʊ��û����� 
	public static final String SET_USERDATA	="setuserdata";//���������Ʊ��û�����
	public static final String INSERT_USERDATA="insertUserData";//����Ʊ��û���Ϣ
	public static final String DELETE_USERDATA="deleteUserData";//ɾ���Ʊ��û���Ϣ
	
	
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
	
	//������ص�Json���
	/**
	 * ����MainJson
	 */
	public static native void creatMainJsonObject();
	public static native void  creatMainJsonArray();
	/**
	 * ����ParamsJson
	 */
	public static native void  creatParamsJsonObject();
	public static native void  creatParamsJsonArray();
	/**
	 * ����DomainJson
	 */
	public static native void  creatDomainJsonObject();
	public static native void  creatDomainJsonArray();
	/**
	 * ����DataJson
	 */
	public static native void  creatDataJsonObject();
	public static native void  creatDataJsonArray();
	/**
	 * ����PlayObjectJson����ֹcJsonȫ�ֱ�����ͻ
	 */
	public static native void  creatPlayObjectJsonObject();
	public static native void  creatPlayObjectJsonArray();
	
	/**
	 * ����Json����㣬��ֹcJsonȫ�ֱ�����ͻ
	 */
	public static native void  creatSixJsonObject();
	public static native void  creatSixJsonArray();
	
//-----------------------------------------------------------------------

	
	public static native void SYNcJsonMEMInit();
	/**
	 * ����"{}"��
	 */
	public static native void cJSONCreateObject();
	/**
	 * ��Ӽ�ֵ�Ե���Ӧ��Json��ֵΪ�ַ���
	 * �磺"gener":{
	 * "name":"yunbao"
	 * }
	 * @param cJsonType
	 * @param name
	 * @param value
	 */	
	public static native void cJSONAddStringToObject(int cJsonType,byte[]name,byte[]value);
	/**
	 * ��Ӽ�ֵ�Ե���Ӧ��Json��ֵΪdouble��
	 * �磺"gener":{
	 * "type":12
	 * }
	 * @param cJsonType
	 * @param name
	 * @param value
	 */
	public static native void cJSONAddNumberToObject(int cJsonType,byte[] name, double value);
	/**
	 * ��������һ��Json���ص���һ��Json��
	 * @param cJsonType
	 * @param name
	 * @param totype
	 */
	public static native void cJSONAddItemToObject(int cJsonType,byte[] name, int totype);
	
	/**
	 * ����"[]"��
	 */
	public static native void cJSONCreateArray();
	public static native void cJSONGetArrayItem(int cJsonType,int item);
	/**
	 * ��Ӽ�ֵ�Ե���Ӧ��ĳ���ֶε�����Json�µ�ĳһ��Json��ֵΪdouble
	 * �磺"gener":[{
	 * "type":12
	 * },{
	 * "type":12
	 * }]
	 * @param cJsonType
	 * @param name
	 * @param value
	 */	
	public static native void cJSONAddNumberToArrayItem(int cJsonType,int item,byte[]name, double value);
	/**
	 * ��Ӽ�ֵ�Ե���Ӧ��ĳ���ֶε�����Json�µ�ĳһ��Json��ֵΪ�ַ���
	 * �磺"gener":[{
	 * "name":"yunbao"
	 * },{
	 * "name":"yunbao"
	 * }]
	 * @param cJsonType
	 * @param name
	 * @param value
	 */	
	public static native void cJSONAddStringToArrayItem(int cJsonType,int item,byte[]name, byte[] value);
	/**
	 * ������ݵ��ĸ�Json��
	 * @param cJsonType
	 */
	public static native void cJSONAddItemToArray(int cJsonType);
	/**
	 * ����ǰJson���ص���һ��
	 * @param cJsonType ��ǰJson������
	 * @param item
	 * @param name
	 */
	public static native void cJSONAddItemToArrayWithSomething(int cJsonType,int item,byte[] name);
	/**
	 * ��ӵ���String�����ݵ������� ���繹������
	 * "params":
	 * [
	 * 		"general",
	 * 		"net"
	 * ]
	 * ��������Ҫ�õ�
	 * @param cJsonType
	 * @param content
	 */
	public static native void cJSONAddSingleStringToArray(int cJsonType,byte[]content);
	/**
	 * ������������ӵ�PlayObjectJson��
	 */
	public static native void cJSONAddItemToPlayObjectJsonArray();
	  
	public static native byte[] cJSONPrint();
	public static native void cJSONDelete();
	public static native void cJSONPrintFree();
	
	
	static {
		System.loadLibrary("JsonOption");
	}

}
