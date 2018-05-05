package com.tts168.autoset.tools;
/**
 * �߳�ͬ���������߳�������lock()��unlock()
 * @author Ԭ��
 *
 */
public class SyncLock {

	private boolean syncLock;
	public SyncLock(){
		syncLock=false;
	}
	
	
	public synchronized void lock(){
		while(syncLock==true){
			try{
				wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		syncLock=true;
	}
	public synchronized void unlock(){
		syncLock=false;
		notifyAll();
	}
}
