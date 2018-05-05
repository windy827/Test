package com.autoset.json;

import java.io.UnsupportedEncodingException;

/**
 * JSon解析接口
 * 读取数据的顺序是先根据领域的名称赋值并锁定焦点，然后取出各领域的内容，再细分处理
 * 
 * @author 袁剑 set的先后顺序是setHead-->setObjectDomainHead-->insertObject...-->setObjectDomainTail
 * 
 */
public class JsonParseOption {
	/**
	 * setUserData对应的ID
	 */
	public static final int ID_setUserData=1;
	/**
	 * getUserData对应的ID
	 */
	public static final int ID_getUserData=2;
	//————————————是否挂载标志位
	/**
	 * 挂载
	 */
	public static final int FLAG_PUT=0x02;
	/**
	 * 不挂载
	 */
	public static final int FLAG_NOTPUT=0x00;

	public static final String GET_HEARTBEATDATE="heartbeat";//请求获取心跳用户数据 
	public static final String GET_USERDATA	="getUserData";//请求获取云宝用户数据 
	public static final String SET_USERDATA	="setUserData";//请求配置云宝用户数据
	public static final String INSERT_USERDATA="insertUserData";//添加云宝用户数据
	public static final String DELETE_USERDATA="deleteUserData";//删除云宝用户数据
	
	
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
	public static native void setHead(byte[] domainName,byte[]setOrGet,byte[]intent);
	
	/**
	 * 设置Object 的Domain的头
	 * 
	 * @param domainName
	 *            领域的名称
	 *   @param         setOrGet
	 *   				设置数据还是得到数据的命令
	 *    @param   intent
	 *   				 意图
	 *   @param  isPut
	 *    			是否挂载FLAG_PUT或FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectDomainHead(int isPut);

	/**
	 * 添加Object的字符串的键值对
	 * 
	 * @param name
	 *            键名称
	 * @param value
	 *            键对应的值
	 */
	public static native void insertObjectString(byte[] name, byte[] value);

	/**
	 * 添加Object的double型的键值对
	 * 
	 * @param name
	 *            键名称
	 * @param value
	 *            键对应的值
	 */
	public static native void insertObjectDouble(byte[] name, double value);

	/**
	 * 设置Object 的Domain的挂载，在这个地方会加上域名在花括号之前【尾】
	 * 
	 * @param domainName
	 *            领域的名称
	 *             @param  isPut
	 *    			是否挂载FLAG_PUT或FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectDomainTail(byte[] domainName,int isPut);

	/**
	 * 设置ObjectArray 的Domain的头
	 * 
	 * @param domainName
	 *            领域的名称
	 *             @param         setOrGet
	 *   				设置数据还是得到数据的命令
	 *    @param   intent
	 *   				 意图
	 *   @param  isPut
	 *    			是否挂载FLAG_PUT或FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectArrayDomainHead(int isPut);
/**
 * 每次添加一组数据的时候都要调用这个方法
 */
	public static native void addObjectArrayHead();
	
	/**
	 * 添加ObjectArray的字符串的键值对
	 * 
	 * @param name
	 *            键名称
	 * @param value
	 *            键对应的值
	 */
	public static native void insertObjectArrayString(int index,byte[] name, byte[] value);

	/**
	 * 添加ObjectArray的double型的键值对
	 * 
	 * @param name
	 *            键名称
	 * @param value
	 *            键对应的值
	 */
	public static native void insertObjectArrayDouble(int index,byte[] name, double value);
	/**
	 * 设置ObjectArray 的Domain的挂载【尾】
	 * 
	 * @param domainName
	 *            领域的名称
	 * @param  isPut
	 *    			是否挂载FLAG_PUT或FLAG_NOTPUT
	 * @return
	 */

	public static native void setObjectArrayDomainTail(byte[] domainName,int isPut);
	
	/**
	 * 返回设置的结果
	 */
	public static native byte[] setResult();

	/**
	 * Wifi
	 * 
	 * @param ssid
	 *            SSID名称
	 * @param password
	 *            密码
	 * @param priority
	 *            优先级
	 * @return
	 */
	public static native byte[] setWifi(byte[] ssid, byte[] password,
			double priority);

	/**
	 * Net
	 * 
	 * @param name
	 *            设备的名称
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口号
	 * @return
	 */
	public static native byte[] setNet(byte[] name, byte[] ip, double port);

	/**
	 * 
	 * @param nickname
	 *            产品名称
	 * @param voiceman
	 *            个性化发音人
	 * @param city
	 *            所在城市
	 * @param logfilter
	 *            日志上传开关
	 * @return
	 */
	public static native byte[] setDatageneral(byte[] nickname,
			byte[] voiceman, byte[] city, double logfilter);

	/**
	 * 根据领域的名称来设定焦点【读取数据用】,返回的是领域内的数组的个数，个数大于一的可循环获取
	 * 
	 * @param data
	 *            领域的数据
	 * @param domain
	 *            领域的名称
	 */
	public static native int setFocusByDomain(byte[] data, byte[] domain);
	/**
	 * 根据当前锁定的焦点，以及数组的第几个成员的键的名称返回对应的int结果值，需要做的事情是得到值以后判断是否大于零，大于则为true
	 * 
	 * @param num
	 *            第几个成员
	 * @param keyName
	 *            成员的键的名称
	 * @return
	 */
	public static native int getFocusJsonGetBool(int num, byte[] keyName);

	/**
	 * 根据当前锁定的焦点，以及数组的第几个成员的键的名称返回对应的String结果值
	 * 
	 * @param num
	 *            第几个成员
	 * @param keyName
	 *            成员的键的名称
	 * @return
	 */
	public static native byte[] getFocusJsonGetString(int num, byte[] keyName);

	/**
	 * 根据当前锁定的焦点，以及数组的第几个成员的键的名称返回对应的double结果值
	 * 
	 * @param num
	 *            第几个成员
	 * @param keyName
	 *            成员的键的名称
	 * @return
	 */
	public static native double getFocusJsonGetNumber(int num, byte[] keyName);


	/**
	 * 得到域名
	 */
	public static native byte[] getJsonDomainName();
	
	static {
		System.loadLibrary("JsonOption");
	}

}
