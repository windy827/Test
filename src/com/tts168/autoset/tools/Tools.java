package com.tts168.autoset.tools;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.autoset.jni.answer.AnswerHelperEntity;
import com.autoset.jni.general.GeneralEntity;

import com.autoset.jni.http.configAndupgrade.UpgradeEntity;
import com.autoset.jni.http.productinfo.ProductInfoEntity;
import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.RingerModelTools;
import com.tts168.autoset.tools.others.converopt.BinaryIntArray2ByteTools;
import com.tts168.autoset.tools.others.converopt.StrBinaryTurn;
import com.tts168.autoset.tools.others.converopt.StringConvertTools;
import com.tts168.autoset.tools.others.fileopt.WriteData;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.DisplayMetrics;
import android.widget.Toast;
/**
 * 工具类
 * @author 袁剑
 *
 */
public class Tools {
	
	/**
	 * 是否需要自动更新提示
	 */
	public static boolean isAutoUpdateAlarm=true;
	/**
	 * 所有实体类传递对应的key值
	 */
	public static final String KEY_ENTITY="entity";
	
	public static String CLICK_FAILD="请求太频繁了，请稍侯操作~";
	/**
	 * 所有通信的文本的格式
	 */
	public static final String TEXT_FORMAT="utf8";
	
	/**
	 * 标题名称
	 */
	public static String title="";
	
	public static final String TITLE_HELP="帮助";
	public static final String TITLE_SAVE="保存";
	public static final String TITLE_REFRESH="刷新";
	public static final String TITLE_RETURN_MAIN="回主菜单";
	public static final String TITLE_SUBMIT="提交";
	
	public static final String STATUS_UPDATE_SUCESS="设置成功";
	public static final String STATUS_UPDATE_FAILED="设置失败";
	public static final String OPTION_SAVING="保存中...";
	/**
	 * 当前连接的设备的昵称
	 */
	public static String Current_DeviceName="";
	/**
	 * 当前连接的设备的图片资源ID
	 */
	//public static Bitmap Current_DeviceBitmap=null;
	public static String Current_DeviceBitmapURL="";
	
	/**
	 * 当前连接的设备的GeneralEntity内容
	 */
	public static GeneralEntity Current_GeneralEntity=null;
	
	/**
	 * 帮助HTML后缀
	 */
	public static final String URL_HTML_HELPTAIL="/LarkHelp.html";
	/**
	 * HTML后缀
	 */
	public static final String URL_HTML_TAIL=".html";
	/**
	 * 当前的General信息
	 */
	public static GeneralEntity generalEntity=new GeneralEntity("", "", "", "", 1, 1, "", "", 0, "", "",false);;
	/**
	 * 当前的AnswerHelper信息
	 */
	public static AnswerHelperEntity answerHelperEntity=new AnswerHelperEntity(1111, "暂无内容", "暂无内容", "暂无内容", new Date().toString(),AnswerHelperEntity.STATUS_STOP);
	
	/**
	 * 当前连接的socket
	 */
	public static String Current_SocketIP="";
	//---------取得数据条数
	/**
	 * 生日纪念日取的条数
	 */
	public static final int DB_COMMERATION_LIMITTOTAL=30;
	/**
	 * 每日闹铃取的条数
	 */
	public static final int DB_DALIYALART_LIMITTOTAL=10;
	
	//---------------------------------------------------------------
	/**
	 * 无效数值，但方法需要此参数，只是暂时填充
	 */
	public static final int DATA_NO_USE=1;
	//Switch开关对应的值
	
	
	/**
	 * Switch开
	 */
	public static final int SWICH_ON=1;
	/**
	 * Switch关
	 */
	public static final int SWICH_OFF=0;
	
	// 提前天数和提醒时间的类型
	/**
	 * 闹铃设置
	 */
	public static final int TYPE_SET_ALART=0x30;
	/**
	 * 节日节气设置
	 */
	public static final int TYPE_SET_FESTIVE=0x31;
	
	
	//闹铃部分的数据
	/**
	 * 记录当前的状态是添加还是更新
	 */
	public static int ALART_FLAG_ADD_OR_UPDATE=0;
	/**
	 * 记录当前的状态是添加
	 */
	public static final int ALART_FLAG_ADD=0x20;
	/**
	 * 记录当前的状态是更新
	 */
	public static final int ALART_FLAG_UPDATE=0x21;
	
	/**
	 * 16：30里面的冒号；用于分割字符串的标志
	 */
	public static final String FLAG_TIME_MAOHAO=":";
	/**
	 * 记录当前的时间或日期，在选择时间日期的Dialog里面会用到
	 */
	public static String time="";
	/**
	 * 记录当前的日期，在选择时间日期的Dialog里面会用到
	 */
	public static String date="";
	/**
	 * 显示日期标志
	 */
	public static final int FLAG_DATE=0x10;
	/**
	 * 显示时间标志
	 */
	public static final int FLAG_TIME=0x11;
	/**
	 * [时间]//每日闹铃		
	 */
	public static final int TYPE_TIME=0x01;
	/**
	 * [日期]//生日纪念日闹铃
	 */
	public static final int TYPE_DATE=0x04;
	/**
	 * [日期]//生日纪念日闹铃是否是阴历
	 */
	public static  boolean TYPE_DATE_Is_Lunar=true;
	/**
	 * 每日闹铃周期对应的值
	 */
	public static int ALART_CYCLE=0;
	/**
	 * 每日闹铃重复提醒标志位对应的值
	 */
	public static int ALART_FLAG_DAILY_ALART_REMIND=0;
	/**
	 * 开启五分钟后重复提醒
	 */
	public static final int ALART_FLAG__DAILY_ALART_REMIND_YES=1;
	/**
	 * 关闭五分钟后重复提醒
	 */
	public static final int ALART_FLAG__DAILY_ALART_REMIND_NO=0;
	
	/**
	 * 生日提醒传入的日期类型
	 */
	public static int ALART_DATE_TYPE=1;
	/**
	 * 阴历类型的值
	 */
	public static final int ALART_TYPE_LUNAR=2;
	/**
	 * 阳历类型的值
	 */
	public static final int ALART_TYPE_SOLAR=1;
	/**
	 * 最长描述文字的长度
	 */
	public static final int MAXLENGTH_DESCRIBE=50;
	/**
	 * 最长事件文字的长度
	 */
	public static final int MAXLENGTH_INCIDENT=10;
//********************************************************************************
//***********************************************************************
//×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
//×××××××××××××××××××××××××××××××××××××××××××××××××××××
	/**
	 * 配置音量的百分比,范围在04f~1.0f之间
	 * **
	 */
	
	public static  final float VOLUME_PERCENT=0.8f;
	public static  final float DEFAULT_VOLUME_PERCENT=0.8f;
	//public static  TCPSendAndRecieve tcp;//TCPSendAndRecieve对象
	//public static boolean Device_Is_Connect=false;//云宝与手机设备是否相连
	public static final int UDP_SEND_TARGET_PORT=8089;//UDP,TCP服务端共用的端口号
	public static final int UDP_REC_PORT=8800;//UDP接收端口号
public static boolean isSend=true;//判断是否让线程一直发送
	
	public static boolean isRec=true;//判断是否让线程一直接收
	
	//---------------------------TCP操作----------------------------------
	/**
	 * 存放TCP得到的配置文件的相关信息
	 */
	public static byte[]Set_Info;
	/**
	 * 修改后得到的内容
	 */
	public static byte[]Send_info;
	
	//--------------------------------TCP接收-------------------------
	/**
	 * 云宝设备的信息
	 */
	public static ArrayList<HashMap<String,Object>> al_Device_Info1=new ArrayList<HashMap<String,Object>>();
	/**
	 *UDP接收到的返回设备名称 
	 */
	public static String Rec_Device_name="";
	public static String Rec_IP="";//UDP接收到的返回的IP地址
	public static int Rec_Port=0;//UDP接收到的返回的端口号
	/**
	 * 存放TCP心跳检测的相关内容[包括socket和是否存活的判断]
	 */
	
	public static final String KEY_HEARTBEATDETECTION_SOCKET="socket";//存放socket
	public static final String KEY_HEARTBEATDETECTION_SERVERIP="socketIP";//存放socket
	public static final String KEY_HEARTBEATDETECTION_SERVERPORT="socketPort";//存放socket
	public static final String KEY_HEARTBEATDETECTION_ISEXIST="isexist";//是否存活着
	
//********************************************************************************
//***********************************************************************
//×××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××××
//×××××××××××××××××××××××××××××××××××××××××××××××××××××	

//----------------------------------------------------
	public static String Title;//界面的标题名
	public static String CurrentActivityName;//记录当前的类名
	public static boolean isPlaying=false;
//-----------------------------数据库-------------------------------------	
//	public static SQLiteDatabase db= null;
//	public static LarkSmartDataBaseConnection con = null;
//--------------------生成波的头部和尾部的能量值-------------------------------------------
	/**
	 * 生成一遍的波形，用于头波和尾波的生成
	 */
	public final static int FLAG_HEAD_AND_TAIL=1;
	/**
	 * 生成三遍的波形，用于有效波的生成
	 */
	public final static int FLAG_EFFECTDATA=3;
	
	/**
	 * 每次加载时生成的头部的能量值的Byte数组
	 */
	public static  byte[]HEAD_WAVE;
	/**
	 * 每次加载时生成的尾部的能量值的Byte数组
	 */
	public static byte[]TAIL_WAVE;
	
	/**
	 * 点击发送按钮发送的有效数据合成的能量值的byte数组
	 */
	//public static byte []EFFECT_WAVE;
//---------------------------------------------------------------
	
	/**
	 * 用于判断是来自哪个Button的倒计时显示
	 */
	public static final int TYPE_WIFI=0001;
	public static final int TYPE_SET=0002;
	/**
	 * 编码方式
	 */
	public static final String ENCODING_TYPE="GBK";
	public static final String DOUHAO=",";
	public static final int[]DOUHAO_ARRAY1=new int[]{1,1,0,0,0,0,0,0};
	public static final int[]DOUHAO_ARRAY2=new int[]{0,0,0,0,0,0,1,1};
	public static final int[]DOUHAO_ARRAY3=new int[]{1,1,1,1,0,0,0,0};
	public static int[]FENGE_ARRAY=new int[]{0,0,1,1,1,1,1,1,
                                             1,1,1,1,1,1,0,0};
	public static final String MAOHAO=":";//区分Wifi和功能配置
	/**
	 * 按键标志位SP_TAG
	 */
	public static final String SP_TAG_Short="SHORT";
	public static final String SP_TAG_Long="LONG";
	public static final String SP_TAG_Double="DOUBLE";
	/**
	 * 发送的日期时间格式
	 * 记录日期和时间
	 */
	public static String CUR_SEND_DATA,CUR_SEND_TIME,Cur_DataY,Cur_DataM,Cur_DataD,Cur_TimeH,Cur_TimeM;
	/**
	 * 记录天气键笑话键长按短按双击的发送的字符串
	 */
	public static String Short_Weather_SEND,Double_Weather_SEND,Long_Weather_SEND,
	                     Short_Laugh,Double_Laugh,Long_Laugh;
	

	/**
	 * 睡眠时长
	 */
	public static int Sleep_TIME=30;
	
	//public static AudioManager mAudioManager;
	public static int CUR_VOL;//获取系统当前音量
	public static int CUR_MODE;//获取系统当前情景模式
	/**
	 * 默认的播放次数，位数，频率1频率2，SSID，密码，GateWay
	 */
	public static final String DEFAULT_TIMES="1";
	public static final String DEFAULT_BITS="64";
	public static final String DEFAULT_KHZ1="2000";
	public static final String DEFAULT_KHZ2="1000";
	public static final String DEFAULT_SSID="RT123456";
	public static final String DEFAULT_PASSWORD="yj123456";
	public static final String DEFAULT_GATEWAY="192.128.0.103";
	public static final String DEFAULT_SIMPLERATE="16";
	
	
	/**
	 * 发送波时每一遍加上的分隔符（为英文状态下的）
	 */
	public static final String FENGE=".";
	public static final int GBK_NUM=8;
	public static final int UTF8_NUM=16;
	
	//--------权限设置
	public static int EDITENNABLE=1;
	public static int LOGIN=0;
	//--------权限设置
	
	
	
	//public static Context c;
	public static AudioTrack emAudio =  null;

	public static final int emStreamType = AudioManager.STREAM_MUSIC;
	public static final int emSampleRate = 16000;
	public static final int emBufferSize = 10240;
	
	
	/**
	 * 针对20141102这种情况设定的分解字符串变成我们想要的年月日的HashMap
	 * @param data
	 * @return year，month,day  key值对应年月日
	 */
	public static HashMap<String,String> splitStringData(String data){
		String year=data.substring(0, 4);
		String month=data.substring(4, 6);
		String day=data.substring(6, 8);
		HashMap<String,String>result=new HashMap<String, String>();
		result.put("year", year);
		result.put("month", month);
		result.put("day", day);
		return result;
	}
	
	
	
	
	public static int getScreenWidth(Context context){
		 int width=0;
		 DisplayMetrics dm=new DisplayMetrics();
		 ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		 width=dm.widthPixels;
		 return width;
	 }
	 public static int getScreenHeight(Context context){
		 int height=0;
		 DisplayMetrics dm=new DisplayMetrics();
		 ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		 height=dm.heightPixels;
		 return height;
	 }
	 
	
	/**
	 * 对某个数组进行重复添加
	 * @param r 需要重复添加的数组
	 * @param num 添加的次数
	 * @return
	 */
	public static byte[] reAdd(byte[] r, int num) {
		byte []newint=new byte[num*r.length];
		for(int i=0;i<num;i++){
			for(int j=0;j<r.length;j++){
				newint[i*r.length+j]=r[j];
			}
		}
		return newint;
	}
	
	/**
	 * Toast 提醒
	 * @param c
	 * @param info 提醒的内容
	 */
	public static void showToast(Context c, final String info){
				Toast.makeText(c, info, Toast.LENGTH_SHORT).show();
			
	}
	
	/**
	 * 三个数组添加数组创建一个新的数组，按顺序添加
	 *  @param length 用户名+“,”+密码的字符串的长度
	* @param times 发送的遍数
	 * @param a 用户名密码的数组
	 * @param b  检验码的数组
	 * @param c  分隔符的数组
	 * 
	 * @return
	 */
	public static int[] add(int length,int times,int[]a,int[]b,int[]c){
		int [] len=StrBinaryTurn.byte2GBKBinary((byte) length);
		int [] time=StrBinaryTurn.byte2GBKBinary((byte) times);
		int [] result=new int[len.length+time.length+a.length+b.length+c.length];
		for(int i=0;i<result.length;i++){
			if(i<GBK_NUM){
				result[i]=len[i];
			}
			else if(i>=GBK_NUM&&i<GBK_NUM*2){
				result[i]=time[i-GBK_NUM];
			}
		else if(i>=GBK_NUM*2&&i<a.length+GBK_NUM*2){
				result[i]=a[i-GBK_NUM*2];
			}
			else if(i>=a.length+GBK_NUM*2&&i<b.length+a.length+GBK_NUM*2){
				result[i]=b[i-a.length-GBK_NUM*2];
			}
			
			else {
				result[i]=c[i-a.length-b.length-GBK_NUM*2];
			}
		}
		return result;
	}
	
	
	/**
	 * 生成不加静音波的有效波形数组
	 * 3个byte数组添加创建一个新的数组，按顺序添加
	 * @param a Tools.HEAD_WAVE;
	 * @param b Tools.EFFECT_WAVE
	 * @param c Tools.TAIL_WAVE
	 
	 * @return
	 */
	public static byte[] getFinalByte(byte[]effectwave){
		int a_len=Tools.HEAD_WAVE.length;
		int b_len=effectwave.length;
		int c_len=Tools.TAIL_WAVE.length;
		byte [] result=new byte[a_len+b_len+c_len];
		for(int i=0;i<result.length;i++){
			if(i<a_len){
				result[i]=Tools.HEAD_WAVE[i];
			}
			else if(i>=a_len&&i<b_len+a_len){
				result[i]=effectwave[i-a_len];
			}
			
			else {
				result[i]=Tools.TAIL_WAVE[i-a_len-b_len];
			}
			
		}
		return result;
	}
	
	/**
	 * 3个数组添加数组创建一个新的数组，按顺序添加
	 
	 * @return
	 */
	public static byte[] add3Byte(byte[]a,byte[]b,byte[]c){
		byte [] result=new byte[a.length+b.length+c.length];
		for(int i=0;i<result.length;i++){
			if(i<a.length){
				result[i]=a[i];
			}
			else if(i>=a.length&&i<b.length+a.length){
				result[i]=b[i-a.length];
			}
			
			else {
				result[i]=c[i-a.length-b.length];
			}
			
		}
		return result;
	}
	
	
	/**
	 * 3个数组添加数组创建一个新的数组，按顺序添加
	 
	 * @return
	 */
	public static int[] add3(int[]a,int[]b,int[]c){
		int [] result=new int[a.length+b.length+c.length];
		for(int i=0;i<result.length;i++){
			if(i<a.length){
				result[i]=a[i];
			}
			else if(i>=a.length&&i<b.length+a.length){
				result[i]=b[i-a.length];
			}
			
			else {
				result[i]=c[i-a.length-b.length];
			}
			
		}
		return result;
	}
	
	/**
	 * 2个数组添加数组创建一个新的数组，按顺序添加
	 
	 * @return
	 */
	public static int[] add2(int[]a,int[]b){
		int [] result=new int[a.length+b.length];
		for(int i=0;i<result.length;i++){
			if(i<a.length){
				result[i]=a[i];
			}
			else if(i>=a.length&&i<b.length+a.length){
				result[i]=b[i-a.length];
			}
			
			
			
		}
		return result;
	}
	public static byte[] yihuo(byte []a,byte []b){
		byte[] result=new byte[a.length];
		for(int i=0;i<a.length;i++){
			result[i]=(byte) (a[i]^b[i]);
		}
		return result;
	}
	/**
	 * 播放byte数组
	 * @param size
	 * @param data
	 */
	public static void EmSpeakerplay(int size,byte[] data)
	{
		int pos=0;
		
		
		try
		{
			
			if(Tools.emAudio.getPlayState()==AudioTrack.PLAYSTATE_PLAYING){
				Tools.emAudio.stop(); 
				Tools.emAudio.release(); 
			}
			Tools.emAudio.pause();
			Tools.emAudio.flush();
			Tools.emAudio.play();
			WriteData wd=new WriteData(MyApplication.getInstance().getCur_Activity());
			//wd.copydbtext(cp);
		
			wd.copydb(data);
			//pos = EmSpeaker.emAudio.getNotificationMarkerPosition();
			Tools.emAudio.write(data,0,size);
			
			//Tools.emAudio.stop();
		
//			while(emAudio.getPlayState()==AudioTrack.PLAYSTATE_PLAYING){ 
//				
//               // System.out.println("111111111"); 
//
//            }
			//emAudio.stop(); 
			//emAudio.release(); 
			isPlaying=false;
			//EmSpeaker.emAudio.play();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
/**
 * 传入一个字符串返回二进制的一个整形数组
 * @param str
 * @return
 */
	public static int[] str2intlist(String str) {
		// TODO Auto-generated method stub

		char[] strChar = str.toCharArray();
		String result = "";

		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]) + "";
		}
		int[] temp = BinstrToIntArray(result);
		int[] newtemp=new int[8];
		if(temp.length<8){
			for(int j=0;j<temp.length;j++){
				newtemp[8-temp.length+j]=temp[j];
			}
			
		}
		return  newtemp;

	}
/**
 * 传入一个整形数组返回一个字节数组
 * @param temp
 */
	public static void getbytelist( int[] temp) {
		
		int[] rr = new int[temp.length];
		byte[] bb = new byte[temp.length];
		for (int i = 0; i < temp.length; i++) {
			rr[i] = temp[i] - 48;
			bb[i] = (byte) temp[i];
		}
	}

	static int[] BinstrToIntArray(String binStr) {
		char[] temp = binStr.toCharArray();
		int[] result = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i] - 48;
		}
		return result;
	}
	
	
	public static byte[] floatlist2bytelist(float[]f){
		byte[]result=new byte[f.length*4];
		for(int j=0;j<f.length;j++){
			byte []temp=float2byte(f[j]);
			result[4*j]=temp[0];
			result[4*j+1]=temp[1];
			result[4*j+2]=temp[2];
			result[4*j+3]=temp[3];
		}
		return result;
		
	}
	public static byte[] float2byte(float f) {  
	      
	    // 把float转换为byte[]  
	    int fbit = Float.floatToIntBits(f);  
	      
	    byte[] b = new byte[4];    
	    for (int i = 0; i < 4; i++) {    
	        b[i] = (byte) (fbit >> (24 - i * 8));    
	    }   
	      
	    // 翻转数组  
	    int len = b.length;  
	    // 建立一个与源数组元素类型相同的数组  
	    byte[] dest = new byte[len];  
	    // 为了防止修改源数组，将源数组拷贝一份副本  
	    System.arraycopy(b, 0, dest, 0, len);  
	    byte temp;  
	    // 将顺位第i个与倒数第i个交换  
	    for (int i = 0; i < len / 2; ++i) {  
	        temp = dest[i];  
	        dest[i] = dest[len - i - 1];  
	        dest[len - i - 1] = temp;  
	    }  
	      
	    return dest;  
	      
	}  
	
	/**
	 * 加载静音波数组形成新的数组
	 * @param pre 放在新数组前端的数组
	 * @param y 放在新数组后端的数组
	 */
	public static byte[] creatnewbytelist(byte[] pre, byte[] behind) {
		byte []newbyte=new byte[pre.length+behind.length];
		for(int j=0;j<pre.length+behind.length;j++){
			if(j<pre.length){
				newbyte[j]=pre[j];
			}
			else{
				newbyte[j]=behind[j-pre.length];
			}
			//System.out.println(newbyte[j]);
		}
		return newbyte;
	}
	/**设置静音波byte数组
	 * //1ms 播放32个字节 num为需要播放的次数
	 * @param num
	 * @return
	 */
	public static byte[] tobyte(int num){
		
		byte []y=new byte[num*(emSampleRate/1000)*2];
		return y;
	}
	
	/**
	 * 得到静态的头和尾赋值，在LoadingActrivity中加载
	
	 * @return
	 */
	
	public static void setHeadAndTail_Wave(){
		byte []first={1,1,1,1,1,1,1,1,
				     0,0,0,0,0,0,0,0};
		
		byte []end={0,0,0,0,0,0,0,0,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1
				};
//		Tools.HEAD_WAVE=GetWave.WavGen(first, (short) first.length);
//					
//		Tools.TAIL_WAVE=GetWave.WavGen(end, (short) end.length);
	}
	
	
	/**
	 * 合成最终需要的波
	 * @param singlewave 重复的某一个波的数组
	 * @param allwave 所有重复的波合成的数组
	 * @return
	 */
	
	public static int []finalwave(int []singlewave,int[]allwave){
		int []first={1,1,1,1,1,1,1,1,
				     0,0,0,0,0,0,0,0};
		
		int []end={0,0,0,0,0,0,0,0,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1,
				1,1,1,1,1,1,1,1
				};
		int []finalwave=Tools.add3(first,  allwave,end);
		return finalwave;
		
		
		
	}
	
	/**
	 * 将一个整形的十进制数转换成为二进制数组
	 * @param c  
	 * @return
	 */
	public static int[] int2Binary(int c) {
		String b=Integer.toBinaryString(c);
		int []r=str2intlist(b+"");
		int []temp=new int[b.length()];
		int[] newtemp=new int[8];
		for (int i = 0; i < temp.length; i++) {
			temp[i]= Integer.parseInt(b.charAt(i)+"");
		}
		if(temp.length<8){
			for(int j=0;j<temp.length;j++){
				newtemp[8-temp.length+j]=temp[j];     
			}
			
		}
		
		return newtemp;
	}
	
	/**
	 * 发送第一遍的数据字符串
	 */
	public static byte[] getFinalByteArray(String str,int playtimes){
	
		byte[] all1 = null;
		try {
			all1 = str.getBytes(StringConvertTools.FORMAT_UTF8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final int times=playtimes;
		byte []all3=Tools.reAdd(all1, playtimes);
		//all3_eachReadd等会得去掉，用来生成三个重复数字，只需调用方法即可
		byte []finalwave=all3;
		return finalwave;
	}
	
	
	/**
	 * 在数组里面的每个数后面重复该数两词
	 * @param primary
	 * @return
	 */
	public static int[] eachReadd2(int [] primary){
		int len=primary.length;
		int[]addresult=new int[len*3];
		
		for(int i=0;i<len;i++){
			for(int j=0;j<3;j++){
				addresult[i*3+j]=primary[i];
			}
		}
		return addresult;
	}
	/**
	 * 记录波形并播放
	 */
	public static void WriteAndPlay(Context context,byte[]finalwave,final byte[]result){
		String s="";
		for(int i=0;i<finalwave.length;i++){
			s=s+finalwave[i]+"";
		}
		byte[]cp=s.getBytes();
		/**
		 * 
		 * WriteData 有音频文件输出的路径在SD卡目录下，调用copydb方法进行音频文件的写操作
		 */
//		WriteData wd=new WriteData(Tools.c);
//		wd.copydbtext(result);
//	
//		wd.copydb(result);
//		Log.d("result",result.length+"");
		
		byte []peacevoice=Tools.tobyte(50);//生成静音波，num为毫秒数
		//头部加载静音波 
		final byte[]realvoice_temp=Tools.creatnewbytelist(result, peacevoice);
		//尾部加载静音波 
		final byte[]realvoice=Tools.creatnewbytelist(peacevoice, realvoice_temp);
		
		
		//获取系统最大音量的一半，当前音量
//		
		//float VOLUME_PERCENT=new SharedConfig(context).GetConfig().getFloat(SharedConfig.KEY_AUDIO_VOL_PERCENT, SharedConfig.DEFAUT_AUDIO_VOL_PERCENT);
		
		
		
		//Tools.EmSpeakerplay(realvoice.length, realvoice);
	/**
	 * 还原音量,还原情景模式的线程
	 */
	
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isPlaying=true;
				Tools.emAudio=new AudioTrack(Tools.emStreamType,Tools.emSampleRate,
						AudioFormat.CHANNEL_OUT_MONO,AudioFormat.ENCODING_PCM_16BIT,
						Tools.emBufferSize,AudioTrack.MODE_STREAM);
				Tools.EmSpeakerplay(realvoice.length, realvoice);
				while(isPlaying){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//达到一定时间后人为降低音量到之前的值
				
			//	MyApplication.getInstance().getAudioManager().setStreamVolume(AudioManager.STREAM_MUSIC, Tools.CUR_VOL, 0);

				RingerModelTools.setByRingMode(MyApplication.getInstance().getCur_Activity(), Tools.CUR_MODE);
				
				
			}
		});
			t.start();
			
			
	}
	
	
	

	/**
	 * 蒋小于10的数字庄换乘两位的字符 如 6=06；用于时间转换
	 * @return 
	 */
	public static  String OneChar2Double(int num){
		String s="";
		
		if(num<10){
			s=0+s+num;
		}else{
			if(num>2000){
				num=num-2000;
			}
			s=num+s;
		}
		return s;
	}
	
	
	/**
	 * // 删除ArrayList中重复元素，保持顺序
	 * 
	 * @param list
	 * @return
	 */
	public static ArrayList removeDuplicateWithOrder(ArrayList list) {
		Set set = new HashSet();
		ArrayList newList = new ArrayList<HashMap<String, Object>>();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add((HashMap<String, Object>) element);
		}
		list.clear();
		list.addAll(newList);
		return list;

	}
	
	
	//--------------------------得到总体设置的数组信息
		public static final String SET_WHOLE_KEY="wholeset";
		public static final String[]anounce=new String[]{"[m3]","[m51]", "[m52]","[m53]","[m54]", "[m55]"};
		public static String getSendEnunciator(Context context,String anouncer){
			String result="";
			ArrayList<HashMap<String, String>> time=getEnunciator(context);
			for(int i=0;i<time.size();i++){
				if(anouncer.equals(time.get(i).get(SET_WHOLE_KEY))){
					result=anounce[i];
					break;
				}
			}
			return result;
		}
		public static String getDisplayEnunciator(Context context,String anouncer){
			String result="";
			ArrayList<HashMap<String, String>> time=getEnunciator(context);
			for(int i=0;i<time.size();i++){
				if(anouncer.equals(anounce[i])){
					result=time.get(i).get(SET_WHOLE_KEY);
					break;
				}
			}
			return result;
		}
		/**
		 * 得到一个ArrayList<HashMap<String, String>>的设置发音人的数组
		 */
		public static ArrayList<HashMap<String, String>> getEnunciator(
				Context context) {
			ArrayList<HashMap<String, String>> time = new ArrayList<HashMap<String, String>>();
			String[] times = context.getResources().getStringArray(
					R.array.enunciators);
			int len = times.length;
			for (int i = 0; i < len; i++) {
				HashMap<String, String> temp = new HashMap<String, String>();
				temp.put(SET_WHOLE_KEY, times[i]);
				time.add(temp);
			}
			return time;

		}
		
		public static final String WEEK[]=new String[]{"周一","周二","周三","周四","周五","周六","周日"};
		public static final String Week_FENGEFU="/";
		/**
		 * 将一个字节的第七位判断对应的星期几转换出想要的结果
		 * @param cycle
		 * @return
		 */
		public static String intArray2StringWeek(byte cycle){
			String result="";
			int index=0;
			int[]array_temp=BinaryIntArray2ByteTools.byte2BinaryInt(cycle);
			for(int i=1;i<array_temp.length;i++){
				if(array_temp[i]==1){
					result=result+Week_FENGEFU+WEEK[i-1];
					index++;
				}
			}
			if(index==7){
				result="每天";
			}
			return result;
		}
		
//----------------------------------------------------------------------------------------------------------------		
		
		/**
		 * 根据keys里面的key找到对应索引下的values的value
		 * @param key
		 * @param keys
		 * @param values
		 * @return
		 */
		public static int getIntValuesByStringKey(String key,String[]keys,int []values){
			int result=0;
			int index=0;
			for(String temp:keys){
				if(temp.equals(key)){
					break;
				}
				index++;
			}
			if(index<keys.length){
				result=values[index];
			}
			return result;
		}
		
		/**
		 * 根据keys里面的key找到对应索引下的values的value
		 * @param key
		 * @param keys
		 * @param values
		 * @return
		 */
		public static String getStringValuesByIntKey(int key,int[]keys,String []values){
			String result="";
			int index=0;
			for(int temp:keys){
				if(temp==key){
					break;
				}
				index++;
			}
			if(index<keys.length){
				result=values[index];
			}
			return result;
		}
//----------------------------------------------------------------------------------------------------------------	
		
}
