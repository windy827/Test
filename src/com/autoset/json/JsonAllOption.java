package com.autoset.json;

import java.io.UnsupportedEncodingException;

/**
 * JSon解析接口
 * 读取数据的顺序是先根据领域的名称赋值并锁定焦点，然后取出各领域的内容，再细分处理
 * 
 * @author 袁剑 set的先后顺序是setHead-->setObjectDomainHead-->insertObject...-->setObjectDomainTail
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
		public static final String JSON_DELETEID="ID";//删除时的id对应的关键字
		public static final String JSON_RESULT="result";//云宝返回结果关键字
		public static final String JSON_ERROR="error";//云宝返回错误关键字
	}
	
	public static final String GET_USERDATA	="getuserdata";//请求获取云宝用户数据 
	public static final String SET_USERDATA	="setuserdata";//请求配置云宝用户数据
	public static final String INSERT_USERDATA="insertUserData";//添加云宝用户信息
	public static final String DELETE_USERDATA="deleteUserData";//删除云宝用户信息
	
	
	//外部终端需要查询（同步）云宝的数据时请求的领域名
	public static final String DOMAIN="domain";//领域名称字段
	
	//一般用于云宝返回给尾部终端
	public static final String ERROR_CODE="errcode";//错误码字段
	public static final String ERROR_MESSAGE="errmsg";//错误信息字段

	//外部终端希望云宝参数设置后的状态提醒
	public static final String INTENT_STATUS_PLAY="status_play";
	
	public static final String INTENT_SELECT="select";//（查询）
	public static final String INTENT_DOMAIN_UPDATE="domain_update";//(表重置：对整个领域进行更新)
	/**
	 * 设置所有Json的头，在每一次Set。。。方法的时候都需要加入这一方法
	 * @param domainName
	 * @param setOrGet
	 * @param intent
	 */
	
	//创建相关的Json句柄
	/**
	 * 创建MainJson
	 */
	public static native void creatMainJsonObject();
	public static native void  creatMainJsonArray();
	/**
	 * 创建ParamsJson
	 */
	public static native void  creatParamsJsonObject();
	public static native void  creatParamsJsonArray();
	/**
	 * 创建DomainJson
	 */
	public static native void  creatDomainJsonObject();
	public static native void  creatDomainJsonArray();
	/**
	 * 创建DataJson
	 */
	public static native void  creatDataJsonObject();
	public static native void  creatDataJsonArray();
	/**
	 * 创建PlayObjectJson，防止cJson全局变量冲突
	 */
	public static native void  creatPlayObjectJsonObject();
	public static native void  creatPlayObjectJsonArray();
	
	/**
	 * 创建Json第五层，防止cJson全局变量冲突
	 */
	public static native void  creatSixJsonObject();
	public static native void  creatSixJsonArray();
	
//-----------------------------------------------------------------------

	
	public static native void SYNcJsonMEMInit();
	/**
	 * 创建"{}"号
	 */
	public static native void cJSONCreateObject();
	/**
	 * 添加键值对到相应的Json，值为字符串
	 * 如："gener":{
	 * "name":"yunbao"
	 * }
	 * @param cJsonType
	 * @param name
	 * @param value
	 */	
	public static native void cJSONAddStringToObject(int cJsonType,byte[]name,byte[]value);
	/**
	 * 添加键值对到相应的Json，值为double型
	 * 如："gener":{
	 * "type":12
	 * }
	 * @param cJsonType
	 * @param name
	 * @param value
	 */
	public static native void cJSONAddNumberToObject(int cJsonType,byte[] name, double value);
	/**
	 * 将创建的一个Json挂载到另一个Json上
	 * @param cJsonType
	 * @param name
	 * @param totype
	 */
	public static native void cJSONAddItemToObject(int cJsonType,byte[] name, int totype);
	
	/**
	 * 创建"[]"号
	 */
	public static native void cJSONCreateArray();
	public static native void cJSONGetArrayItem(int cJsonType,int item);
	/**
	 * 添加键值对到相应的某个字段的数组Json下的某一个Json，值为double
	 * 如："gener":[{
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
	 * 添加键值对到相应的某个字段的数组Json下的某一个Json，值为字符串
	 * 如："gener":[{
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
	 * 添加内容到哪个Json上
	 * @param cJsonType
	 */
	public static native void cJSONAddItemToArray(int cJsonType);
	/**
	 * 将当前Json挂载到上一级
	 * @param cJsonType 当前Json的类型
	 * @param item
	 * @param name
	 */
	public static native void cJSONAddItemToArrayWithSomething(int cJsonType,int item,byte[] name);
	/**
	 * 添加单个String的内容到数组上 ，如构造形如
	 * "params":
	 * [
	 * 		"general",
	 * 		"net"
	 * ]
	 * 的内容需要用到
	 * @param cJsonType
	 * @param content
	 */
	public static native void cJSONAddSingleStringToArray(int cJsonType,byte[]content);
	/**
	 * 创建的内容添加到PlayObjectJson上
	 */
	public static native void cJSONAddItemToPlayObjectJsonArray();
	  
	public static native byte[] cJSONPrint();
	public static native void cJSONDelete();
	public static native void cJSONPrintFree();
	
	
	static {
		System.loadLibrary("JsonOption");
	}

}
