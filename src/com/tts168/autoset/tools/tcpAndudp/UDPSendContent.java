package com.tts168.autoset.tools.tcpAndudp;

import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.others.converopt.IntAndByteArrayConvert;

public class UDPSendContent {

	
	public static final int SEND_FLAG_JSON=0;
	public static final int SEND_FLAG_XML=1;
	/**
	 * 返回发送的字节数
	 * @param flag SEND_FLAG_JSON或SEND_FLAG_XML
	 * @param content 发送的内容
	 * @return
	 */
	public static byte[]getSendDate(int flag,byte[]content){
		byte[]result=new byte[content.length+8];
		byte[]sendData_TotalLength=IntAndByteArrayConvert.intToBytes2(content.length);
		byte[]sendData_Flag=ConvertTools.intToByte(flag);
		result=Tools.add3Byte(sendData_Flag,sendData_TotalLength , content);
		return result;
	}
	
}
