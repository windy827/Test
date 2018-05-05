package com.example.hellojni;
/**
 * 数据文件的操作
 * @author 袁剑
 *
 */
public class DBOption {

	
	//定义总数据库中项目编号
	/**
	 * 版本信息
	 */
	public static final int	TYPE_VERSION_ID=	0;
	/**
	 * 数据库大小
	 */
	public static final int	TYPE_DATESIZE_ID	=1;
	/**
	 * 用户名
	 */
	public static final int	TYPE_USERNAME_ID	=2;
	/**
	 * 密码
	 */
	public static final int	TYPE_CODE_ID	=3;
	/**
	 * 发音人
	 */
	public static final int	TYPE_SPEAKER_ID	=4;
	/**
	 * 整点、半点提醒
	 */
	public static final int	TYPE_TIME_REMIND_ID=5;
	
	/**
	 * 天气键
	 */
	public static final int	TYPE_TQ_ID=6;
	/**
	 * 笑话键
	 */
	public static final int	TYPE_JOKE_ID=7;
	/**
	 * WIFI信息获取时间点
	 */
	public static final int	TYPE_INFO_GET_ID	=8;
	/**
	 * 节日节气提醒设置
	 */
	public static final int	TYPE_FESTIVAL_ID	=9;
	/**
	 * 云宝昵称
	 */
	public static final int	TYPE_YB_NAME_ID	=10;
	/**
	 * 闹钟个数
	 */
	public static final int	TYPE_CLK_HEAD_ID	=11;
	/**
	 * 闹钟设置详细信息（整条完整闹钟信息）
	 */
	public static final int	TYPE_CLK_CONTENT_ID=12;
	/**
	 * 生日提醒预设置 （条数、提前提醒天数、时间、）
	 */
	public static final int	TYPE_BIR_HEAD_ID	=13;
	/**
	 * 整条生日提醒设置
	 */
	public static final int	TYPE_BIR_CONTENT_ID=14;
	/**
	 * 校验码
	 */
	public static final int	TYPE_CHECKCODE_CONTENT=15;
	/**
	 * 
	 *在进行此操作之前需要先进行结构体初始化 文件所有数据
	 * @param type 操作的数据的类型
	 * @param changeDate 用来操作的数据
	 * @param changeDateLen 操作的数据的长度
	 */
	 public static native byte[] getDBOption( int type,byte[] changeDate,int changeDateLen);
	 /**
	  * 初始化结构体数据
	  * @param allDate
	  */
	 public static native void InitStruct(byte[] allDate);
	 /**
	  * 
	  * @param type 项目编号
	  * @param changeDateLen 表示第几个闹钟提醒或者生日提醒，读取其他数据时这个值不起作用
	  * @return
	  */
	 public static native byte[]  getDataBaseRead(int type,int changeDateLen);
	 
	 static {
         System.loadLibrary("DBOption");
     }

}
