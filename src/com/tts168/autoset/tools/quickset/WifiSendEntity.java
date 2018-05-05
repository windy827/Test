package com.tts168.autoset.tools.quickset;

import java.io.Serializable;
/**
 *  
Wifi FSKÅäÖÃ·¢ËÍµÄÄÚÈİ
 * @author Ô¬½£
 *
 */

public class WifiSendEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 901L;
	
	private byte[]finalWave;
	private byte[]result;
	public WifiSendEntity(byte[] finalWave, byte[] result) {
		super();
		this.finalWave = finalWave;
		this.result = result;
	}
	public byte[] getFinalWave() {
		return finalWave;
	}
	public void setFinalWave(byte[] finalWave) {
		this.finalWave = finalWave;
	}
	public byte[] getResult() {
		return result;
	}
	public void setResult(byte[] result) {
		this.result = result;
	}
	
	

}
