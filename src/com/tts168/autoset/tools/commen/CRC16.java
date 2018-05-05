package com.tts168.autoset.tools.commen;

	/**
	 * 得到检验的结果 0x0101
	 */
public class CRC16 {
	/*
	  *      crc16.c
	  *
	  * This source code is licensed under the GNU General Public License,
	  * Version 2. See the file COPYING for more details.
	  */
	 
	 /** CRC table for the CRC-16. The poly is 0x8005 (x^16 + x^15 + x^2 + 1) */
	 static short  mmi_crc16_table[]= new short[]{
	         0x0000, (short) 0xC0C1, (short) 0xC181, 0x0140, (short) 0xC301, 0x03C0, 0x0280, (short)0xC241,
	         (short) 0xC601, 0x06C0, 0x0780, (short) 0xC741, 0x0500, (short) 0xC5C1, (short) 0xC481, 0x0440,
	         (short) 0xCC01, 0x0CC0, 0x0D80, (short) 0xCD41, 0x0F00, (short)0xCFC1,(short) 0xCE81, 0x0E40,
	         0x0A00, (short) 0xCAC1, (short) 0xCB81, 0x0B40, (short)0xC901, 0x09C0, 0x0880, (short)0xC841,
	         (short)0xD801, 0x18C0, 0x1980, (short)0xD941, 0x1B00, (short)0xDBC1, (short)0xDA81, 0x1A40,
	         0x1E00, (short)0xDEC1, (short)0xDF81, 0x1F40, (short)0xDD01, 0x1DC0, 0x1C80, (short)0xDC41,
	         0x1400, (short)0xD4C1, (short)0xD581, 0x1540, (short)0xD701, 0x17C0, 0x1680, (short)0xD641,
	         (short)0xD201, 0x12C0, 0x1380, (short)0xD341, 0x1100, (short)0xD1C1, (short)0xD081, 0x1040,
	         (short)0xF001, 0x30C0, 0x3180, (short)0xF141, 0x3300, (short)0xF3C1, (short)0xF281, 0x3240,
	         0x3600, (short)0xF6C1, (short)0xF781, 0x3740, (short)0xF501, 0x35C0, 0x3480, (short)0xF441,
	         0x3C00, (short)0xFCC1, (short)0xFD81, 0x3D40,(short) 0xFF01, 0x3FC0, 0x3E80,(short) 0xFE41,
	         (short)0xFA01, 0x3AC0, 0x3B80,(short) 0xFB41, 0x3900, (short)0xF9C1, (short)0xF881, 0x3840,
	         0x2800, (short)0xE8C1, (short)0xE981, 0x2940, (short)0xEB01, 0x2BC0, 0x2A80, (short)0xEA41,
	         (short)0xEE01, 0x2EC0, 0x2F80, (short)0xEF41, 0x2D00, (short)0xEDC1, (short)0xEC81, 0x2C40,
	         (short)0xE401, 0x24C0, 0x2580, (short)0xE541, 0x2700, (short)0xE7C1, (short)0xE681, 0x2640,
	         0x2200, (short)0xE2C1, (short)0xE381, 0x2340, (short)0xE101, 0x21C0, 0x2080, (short)0xE041,
	         (short)0xA001, 0x60C0, 0x6180, (short)0xA141, 0x6300, (short)0xA3C1,(short) 0xA281, 0x6240,
	         0x6600, (short)0xA6C1, (short)0xA781, 0x6740, (short)0xA501, 0x65C0, 0x6480,(short) 0xA441,
	         0x6C00, (short)0xACC1, (short)0xAD81, 0x6D40,(short) 0xAF01, 0x6FC0, 0x6E80, (short)0xAE41,
	         (short)0xAA01, 0x6AC0, 0x6B80, (short)0xAB41, 0x6900,(short) 0xA9C1, (short)0xA881, 0x6840,
	         0x7800, (short)0xB8C1,(short) 0xB981, 0x7940, (short)0xBB01, 0x7BC0, 0x7A80, (short)0xBA41,
	         (short)0xBE01, 0x7EC0, 0x7F80, (short)0xBF41, 0x7D00, (short)0xBDC1, (short)0xBC81, 0x7C40,
	         (short)0xB401, 0x74C0, 0x7580, (short)0xB541, 0x7700, (short)0xB7C1, (short)0xB681, 0x7640,
	         0x7200, (short)0xB2C1, (short)0xB381, 0x7340,(short) 0xB101, 0x71C0, 0x7080, (short)0xB041,
	         0x5000, (short)0x90C1, (short)0x9181, 0x5140, (short)0x9301, 0x53C0, 0x5280, (short)0x9241,
	         (short)0x9601, 0x56C0, 0x5780, (short)0x9741, 0x5500, (short)0x95C1,(short) 0x9481, 0x5440,
	         (short)0x9C01, 0x5CC0, 0x5D80, (short)0x9D41, 0x5F00, (short)0x9FC1,(short) 0x9E81, 0x5E40,
	         0x5A00, (short)0x9AC1, (short)0x9B81, 0x5B40, (short)0x9901, 0x59C0, 0x5880, (short)0x9841,
	         (short)0x8801, 0x48C0, 0x4980, (short)0x8941, 0x4B00, (short)0x8BC1, (short)0x8A81, 0x4A40,
	         0x4E00, (short)0x8EC1, (short)0x8F81, 0x4F40, (short)0x8D01, 0x4DC0, 0x4C80, (short)0x8C41,
	         0x4400, (short)0x84C1, (short)0x8581, 0x4540, (short)0x8701, 0x47C0, 0x4680, (short)0x8641,
	         (short)0x8201, 0x42C0, 0x4380, (short)0x8341, 0x4100, (short)0x81C1, (short)0x8081, 0x4040
	 };
	
	 
	 /**
	  * crc16 - compute the CRC-16 for the data buffer
	  * @crc:        previous CRC value
	  * @buffer:     data pointer
	  * @len:        number of bytes in the buffer
	  *
	  * Returns the updated CRC value.
	  */
	/**
	 * 得到检验的结果 0x0101
	 * @param crc
	 * @param buffer
	 * @param len
	 * @return
	 */
	 public short get_crc16(short crc, byte []buffer, int len)
	 {
		 int index=0;
	     while (len-1-index>0)
	     {
	    	 
	        crc = (short) ((crc >> 8) ^(mmi_crc16_table[(crc^(buffer[index]))&0xff]));
	        index++;
	        
	     }
	     return crc;
	 }
	
	
	 
	 
}

