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
 * ������
 * @author Ԭ��
 *
 */
public class Tools {
	
	/**
	 * �Ƿ���Ҫ�Զ�������ʾ
	 */
	public static boolean isAutoUpdateAlarm=true;
	/**
	 * ����ʵ���ഫ�ݶ�Ӧ��keyֵ
	 */
	public static final String KEY_ENTITY="entity";
	
	public static String CLICK_FAILD="����̫Ƶ���ˣ����Ժ����~";
	/**
	 * ����ͨ�ŵ��ı��ĸ�ʽ
	 */
	public static final String TEXT_FORMAT="utf8";
	
	/**
	 * ��������
	 */
	public static String title="";
	
	public static final String TITLE_HELP="����";
	public static final String TITLE_SAVE="����";
	public static final String TITLE_REFRESH="ˢ��";
	public static final String TITLE_RETURN_MAIN="�����˵�";
	public static final String TITLE_SUBMIT="�ύ";
	
	public static final String STATUS_UPDATE_SUCESS="���óɹ�";
	public static final String STATUS_UPDATE_FAILED="����ʧ��";
	public static final String OPTION_SAVING="������...";
	/**
	 * ��ǰ���ӵ��豸���ǳ�
	 */
	public static String Current_DeviceName="";
	/**
	 * ��ǰ���ӵ��豸��ͼƬ��ԴID
	 */
	//public static Bitmap Current_DeviceBitmap=null;
	public static String Current_DeviceBitmapURL="";
	
	/**
	 * ��ǰ���ӵ��豸��GeneralEntity����
	 */
	public static GeneralEntity Current_GeneralEntity=null;
	
	/**
	 * ����HTML��׺
	 */
	public static final String URL_HTML_HELPTAIL="/LarkHelp.html";
	/**
	 * HTML��׺
	 */
	public static final String URL_HTML_TAIL=".html";
	/**
	 * ��ǰ��General��Ϣ
	 */
	public static GeneralEntity generalEntity=new GeneralEntity("", "", "", "", 1, 1, "", "", 0, "", "",false);;
	/**
	 * ��ǰ��AnswerHelper��Ϣ
	 */
	public static AnswerHelperEntity answerHelperEntity=new AnswerHelperEntity(1111, "��������", "��������", "��������", new Date().toString(),AnswerHelperEntity.STATUS_STOP);
	
	/**
	 * ��ǰ���ӵ�socket
	 */
	public static String Current_SocketIP="";
	//---------ȡ����������
	/**
	 * ���ռ�����ȡ������
	 */
	public static final int DB_COMMERATION_LIMITTOTAL=30;
	/**
	 * ÿ������ȡ������
	 */
	public static final int DB_DALIYALART_LIMITTOTAL=10;
	
	//---------------------------------------------------------------
	/**
	 * ��Ч��ֵ����������Ҫ�˲�����ֻ����ʱ���
	 */
	public static final int DATA_NO_USE=1;
	//Switch���ض�Ӧ��ֵ
	
	
	/**
	 * Switch��
	 */
	public static final int SWICH_ON=1;
	/**
	 * Switch��
	 */
	public static final int SWICH_OFF=0;
	
	// ��ǰ����������ʱ�������
	/**
	 * ��������
	 */
	public static final int TYPE_SET_ALART=0x30;
	/**
	 * ���ս�������
	 */
	public static final int TYPE_SET_FESTIVE=0x31;
	
	
	//���岿�ֵ�����
	/**
	 * ��¼��ǰ��״̬����ӻ��Ǹ���
	 */
	public static int ALART_FLAG_ADD_OR_UPDATE=0;
	/**
	 * ��¼��ǰ��״̬�����
	 */
	public static final int ALART_FLAG_ADD=0x20;
	/**
	 * ��¼��ǰ��״̬�Ǹ���
	 */
	public static final int ALART_FLAG_UPDATE=0x21;
	
	/**
	 * 16��30�����ð�ţ����ڷָ��ַ����ı�־
	 */
	public static final String FLAG_TIME_MAOHAO=":";
	/**
	 * ��¼��ǰ��ʱ������ڣ���ѡ��ʱ�����ڵ�Dialog������õ�
	 */
	public static String time="";
	/**
	 * ��¼��ǰ�����ڣ���ѡ��ʱ�����ڵ�Dialog������õ�
	 */
	public static String date="";
	/**
	 * ��ʾ���ڱ�־
	 */
	public static final int FLAG_DATE=0x10;
	/**
	 * ��ʾʱ���־
	 */
	public static final int FLAG_TIME=0x11;
	/**
	 * [ʱ��]//ÿ������		
	 */
	public static final int TYPE_TIME=0x01;
	/**
	 * [����]//���ռ���������
	 */
	public static final int TYPE_DATE=0x04;
	/**
	 * [����]//���ռ����������Ƿ�������
	 */
	public static  boolean TYPE_DATE_Is_Lunar=true;
	/**
	 * ÿ���������ڶ�Ӧ��ֵ
	 */
	public static int ALART_CYCLE=0;
	/**
	 * ÿ�������ظ����ѱ�־λ��Ӧ��ֵ
	 */
	public static int ALART_FLAG_DAILY_ALART_REMIND=0;
	/**
	 * ��������Ӻ��ظ�����
	 */
	public static final int ALART_FLAG__DAILY_ALART_REMIND_YES=1;
	/**
	 * �ر�����Ӻ��ظ�����
	 */
	public static final int ALART_FLAG__DAILY_ALART_REMIND_NO=0;
	
	/**
	 * �������Ѵ������������
	 */
	public static int ALART_DATE_TYPE=1;
	/**
	 * �������͵�ֵ
	 */
	public static final int ALART_TYPE_LUNAR=2;
	/**
	 * �������͵�ֵ
	 */
	public static final int ALART_TYPE_SOLAR=1;
	/**
	 * ��������ֵĳ���
	 */
	public static final int MAXLENGTH_DESCRIBE=50;
	/**
	 * ��¼����ֵĳ���
	 */
	public static final int MAXLENGTH_INCIDENT=10;
//********************************************************************************
//***********************************************************************
//������������������������������������������������������������������������������������������������������������������������������
//����������������������������������������������������������������������������������������������������������
	/**
	 * ���������İٷֱ�,��Χ��04f~1.0f֮��
	 * **
	 */
	
	public static  final float VOLUME_PERCENT=0.8f;
	public static  final float DEFAULT_VOLUME_PERCENT=0.8f;
	//public static  TCPSendAndRecieve tcp;//TCPSendAndRecieve����
	//public static boolean Device_Is_Connect=false;//�Ʊ����ֻ��豸�Ƿ�����
	public static final int UDP_SEND_TARGET_PORT=8089;//UDP,TCP����˹��õĶ˿ں�
	public static final int UDP_REC_PORT=8800;//UDP���ն˿ں�
public static boolean isSend=true;//�ж��Ƿ����߳�һֱ����
	
	public static boolean isRec=true;//�ж��Ƿ����߳�һֱ����
	
	//---------------------------TCP����----------------------------------
	/**
	 * ���TCP�õ��������ļ��������Ϣ
	 */
	public static byte[]Set_Info;
	/**
	 * �޸ĺ�õ�������
	 */
	public static byte[]Send_info;
	
	//--------------------------------TCP����-------------------------
	/**
	 * �Ʊ��豸����Ϣ
	 */
	public static ArrayList<HashMap<String,Object>> al_Device_Info1=new ArrayList<HashMap<String,Object>>();
	/**
	 *UDP���յ��ķ����豸���� 
	 */
	public static String Rec_Device_name="";
	public static String Rec_IP="";//UDP���յ��ķ��ص�IP��ַ
	public static int Rec_Port=0;//UDP���յ��ķ��صĶ˿ں�
	/**
	 * ���TCP���������������[����socket���Ƿ�����ж�]
	 */
	
	public static final String KEY_HEARTBEATDETECTION_SOCKET="socket";//���socket
	public static final String KEY_HEARTBEATDETECTION_SERVERIP="socketIP";//���socket
	public static final String KEY_HEARTBEATDETECTION_SERVERPORT="socketPort";//���socket
	public static final String KEY_HEARTBEATDETECTION_ISEXIST="isexist";//�Ƿ�����
	
//********************************************************************************
//***********************************************************************
//������������������������������������������������������������������������������������������������������������������������������
//����������������������������������������������������������������������������������������������������������	

//----------------------------------------------------
	public static String Title;//����ı�����
	public static String CurrentActivityName;//��¼��ǰ������
	public static boolean isPlaying=false;
//-----------------------------���ݿ�-------------------------------------	
//	public static SQLiteDatabase db= null;
//	public static LarkSmartDataBaseConnection con = null;
//--------------------���ɲ���ͷ����β��������ֵ-------------------------------------------
	/**
	 * ����һ��Ĳ��Σ�����ͷ����β��������
	 */
	public final static int FLAG_HEAD_AND_TAIL=1;
	/**
	 * ��������Ĳ��Σ�������Ч��������
	 */
	public final static int FLAG_EFFECTDATA=3;
	
	/**
	 * ÿ�μ���ʱ���ɵ�ͷ��������ֵ��Byte����
	 */
	public static  byte[]HEAD_WAVE;
	/**
	 * ÿ�μ���ʱ���ɵ�β��������ֵ��Byte����
	 */
	public static byte[]TAIL_WAVE;
	
	/**
	 * ������Ͱ�ť���͵���Ч���ݺϳɵ�����ֵ��byte����
	 */
	//public static byte []EFFECT_WAVE;
//---------------------------------------------------------------
	
	/**
	 * �����ж��������ĸ�Button�ĵ���ʱ��ʾ
	 */
	public static final int TYPE_WIFI=0001;
	public static final int TYPE_SET=0002;
	/**
	 * ���뷽ʽ
	 */
	public static final String ENCODING_TYPE="GBK";
	public static final String DOUHAO=",";
	public static final int[]DOUHAO_ARRAY1=new int[]{1,1,0,0,0,0,0,0};
	public static final int[]DOUHAO_ARRAY2=new int[]{0,0,0,0,0,0,1,1};
	public static final int[]DOUHAO_ARRAY3=new int[]{1,1,1,1,0,0,0,0};
	public static int[]FENGE_ARRAY=new int[]{0,0,1,1,1,1,1,1,
                                             1,1,1,1,1,1,0,0};
	public static final String MAOHAO=":";//����Wifi�͹�������
	/**
	 * ������־λSP_TAG
	 */
	public static final String SP_TAG_Short="SHORT";
	public static final String SP_TAG_Long="LONG";
	public static final String SP_TAG_Double="DOUBLE";
	/**
	 * ���͵�����ʱ���ʽ
	 * ��¼���ں�ʱ��
	 */
	public static String CUR_SEND_DATA,CUR_SEND_TIME,Cur_DataY,Cur_DataM,Cur_DataD,Cur_TimeH,Cur_TimeM;
	/**
	 * ��¼������Ц���������̰�˫���ķ��͵��ַ���
	 */
	public static String Short_Weather_SEND,Double_Weather_SEND,Long_Weather_SEND,
	                     Short_Laugh,Double_Laugh,Long_Laugh;
	

	/**
	 * ˯��ʱ��
	 */
	public static int Sleep_TIME=30;
	
	//public static AudioManager mAudioManager;
	public static int CUR_VOL;//��ȡϵͳ��ǰ����
	public static int CUR_MODE;//��ȡϵͳ��ǰ�龰ģʽ
	/**
	 * Ĭ�ϵĲ��Ŵ�����λ����Ƶ��1Ƶ��2��SSID�����룬GateWay
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
	 * ���Ͳ�ʱÿһ����ϵķָ�����ΪӢ��״̬�µģ�
	 */
	public static final String FENGE=".";
	public static final int GBK_NUM=8;
	public static final int UTF8_NUM=16;
	
	//--------Ȩ������
	public static int EDITENNABLE=1;
	public static int LOGIN=0;
	//--------Ȩ������
	
	
	
	//public static Context c;
	public static AudioTrack emAudio =  null;

	public static final int emStreamType = AudioManager.STREAM_MUSIC;
	public static final int emSampleRate = 16000;
	public static final int emBufferSize = 10240;
	
	
	/**
	 * ���20141102��������趨�ķֽ��ַ������������Ҫ�������յ�HashMap
	 * @param data
	 * @return year��month,day  keyֵ��Ӧ������
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
	 * ��ĳ����������ظ����
	 * @param r ��Ҫ�ظ���ӵ�����
	 * @param num ��ӵĴ���
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
	 * Toast ����
	 * @param c
	 * @param info ���ѵ�����
	 */
	public static void showToast(Context c, final String info){
				Toast.makeText(c, info, Toast.LENGTH_SHORT).show();
			
	}
	
	/**
	 * ��������������鴴��һ���µ����飬��˳�����
	 *  @param length �û���+��,��+������ַ����ĳ���
	* @param times ���͵ı���
	 * @param a �û������������
	 * @param b  �����������
	 * @param c  �ָ���������
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
	 * ���ɲ��Ӿ���������Ч��������
	 * 3��byte������Ӵ���һ���µ����飬��˳�����
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
	 * 3������������鴴��һ���µ����飬��˳�����
	 
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
	 * 3������������鴴��һ���µ����飬��˳�����
	 
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
	 * 2������������鴴��һ���µ����飬��˳�����
	 
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
	 * ����byte����
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
 * ����һ���ַ������ض����Ƶ�һ����������
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
 * ����һ���������鷵��һ���ֽ�����
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
	      
	    // ��floatת��Ϊbyte[]  
	    int fbit = Float.floatToIntBits(f);  
	      
	    byte[] b = new byte[4];    
	    for (int i = 0; i < 4; i++) {    
	        b[i] = (byte) (fbit >> (24 - i * 8));    
	    }   
	      
	    // ��ת����  
	    int len = b.length;  
	    // ����һ����Դ����Ԫ��������ͬ������  
	    byte[] dest = new byte[len];  
	    // Ϊ�˷�ֹ�޸�Դ���飬��Դ���鿽��һ�ݸ���  
	    System.arraycopy(b, 0, dest, 0, len);  
	    byte temp;  
	    // ��˳λ��i���뵹����i������  
	    for (int i = 0; i < len / 2; ++i) {  
	        temp = dest[i];  
	        dest[i] = dest[len - i - 1];  
	        dest[len - i - 1] = temp;  
	    }  
	      
	    return dest;  
	      
	}  
	
	/**
	 * ���ؾ����������γ��µ�����
	 * @param pre ����������ǰ�˵�����
	 * @param y �����������˵�����
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
	/**���þ�����byte����
	 * //1ms ����32���ֽ� numΪ��Ҫ���ŵĴ���
	 * @param num
	 * @return
	 */
	public static byte[] tobyte(int num){
		
		byte []y=new byte[num*(emSampleRate/1000)*2];
		return y;
	}
	
	/**
	 * �õ���̬��ͷ��β��ֵ����LoadingActrivity�м���
	
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
	 * �ϳ�������Ҫ�Ĳ�
	 * @param singlewave �ظ���ĳһ����������
	 * @param allwave �����ظ��Ĳ��ϳɵ�����
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
	 * ��һ�����ε�ʮ������ת����Ϊ����������
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
	 * ���͵�һ��������ַ���
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
		//all3_eachReadd�Ȼ��ȥ�����������������ظ����֣�ֻ����÷�������
		byte []finalwave=all3;
		return finalwave;
	}
	
	
	/**
	 * �����������ÿ���������ظ���������
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
	 * ��¼���β�����
	 */
	public static void WriteAndPlay(Context context,byte[]finalwave,final byte[]result){
		String s="";
		for(int i=0;i<finalwave.length;i++){
			s=s+finalwave[i]+"";
		}
		byte[]cp=s.getBytes();
		/**
		 * 
		 * WriteData ����Ƶ�ļ������·����SD��Ŀ¼�£�����copydb����������Ƶ�ļ���д����
		 */
//		WriteData wd=new WriteData(Tools.c);
//		wd.copydbtext(result);
//	
//		wd.copydb(result);
//		Log.d("result",result.length+"");
		
		byte []peacevoice=Tools.tobyte(50);//���ɾ�������numΪ������
		//ͷ�����ؾ����� 
		final byte[]realvoice_temp=Tools.creatnewbytelist(result, peacevoice);
		//β�����ؾ����� 
		final byte[]realvoice=Tools.creatnewbytelist(peacevoice, realvoice_temp);
		
		
		//��ȡϵͳ���������һ�룬��ǰ����
//		
		//float VOLUME_PERCENT=new SharedConfig(context).GetConfig().getFloat(SharedConfig.KEY_AUDIO_VOL_PERCENT, SharedConfig.DEFAUT_AUDIO_VOL_PERCENT);
		
		
		
		//Tools.EmSpeakerplay(realvoice.length, realvoice);
	/**
	 * ��ԭ����,��ԭ�龰ģʽ���߳�
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
				//�ﵽһ��ʱ�����Ϊ����������֮ǰ��ֵ
				
			//	MyApplication.getInstance().getAudioManager().setStreamVolume(AudioManager.STREAM_MUSIC, Tools.CUR_VOL, 0);

				RingerModelTools.setByRingMode(MyApplication.getInstance().getCur_Activity(), Tools.CUR_MODE);
				
				
			}
		});
			t.start();
			
			
	}
	
	
	

	/**
	 * ��С��10������ׯ������λ���ַ� �� 6=06������ʱ��ת��
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
	 * // ɾ��ArrayList���ظ�Ԫ�أ�����˳��
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
	
	
	//--------------------------�õ��������õ�������Ϣ
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
		 * �õ�һ��ArrayList<HashMap<String, String>>�����÷����˵�����
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
		
		public static final String WEEK[]=new String[]{"��һ","�ܶ�","����","����","����","����","����"};
		public static final String Week_FENGEFU="/";
		/**
		 * ��һ���ֽڵĵ���λ�ж϶�Ӧ�����ڼ�ת������Ҫ�Ľ��
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
				result="ÿ��";
			}
			return result;
		}
		
//----------------------------------------------------------------------------------------------------------------		
		
		/**
		 * ����keys�����key�ҵ���Ӧ�����µ�values��value
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
		 * ����keys�����key�ҵ���Ӧ�����µ�values��value
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
