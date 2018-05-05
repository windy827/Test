package com.tts168.autoset.tools.entity.terminal;

import java.io.Serializable;

import com.autoset.jni.general.GeneralEntity;
/**
 * 终端列表适配器上的内容
 * @author 袁剑
 *
 */
public class TerminalAdapterEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 157L;
	
	private GeneralEntity generalEntity;
	private String socketIp;
	private int rssi;//Wifi信号强度
	public TerminalAdapterEntity(GeneralEntity generalEntity, String socketIp,
			 int rssi) {
		super();
		this.generalEntity = generalEntity;
		this.socketIp = socketIp;
		
		this.rssi = rssi;
	}
	public GeneralEntity getGeneralEntity() {
		return generalEntity;
	}
	public void setGeneralEntity(GeneralEntity generalEntity) {
		this.generalEntity = generalEntity;
	}
	public String getSocketIp() {
		return socketIp;
	}
	public void setSocketIp(String socketIp) {
		this.socketIp = socketIp;
	}
	
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	
	

}
