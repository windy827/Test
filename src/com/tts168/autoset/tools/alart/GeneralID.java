package com.tts168.autoset.tools.alart;
import java.util.Random;

/**
 * ������һ����Χ��[MaxId]�������ID�����ɵ�ID���������������д���
 * @author Ԭ��
 *
 */
public class GeneralID {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean  canAdd=canGenerID(new int[]{0,1,2,3},7);
		for(int i=0;i<20;i++){
			if(canAdd){
				System.out.println(generalID(new int[]{0,1,2,3},7));
			}
			
		}

		
	}
	
	/**
	 * �жϵ�ǰ�����Ƿ����������ID
	 * @param hasID �Ѿ����ڵ�����
	 * @param maxID ��������ֵ
	 * @return
	 */
	public static boolean canGenerID(int[]hasID,int maxID){
		boolean canGenerID=true;
		if(hasID.length>=maxID){
			//��ʾ��ǰ����ID�Ѿ�ռ���ˣ��������
			canGenerID=false;
		}
		return canGenerID;
	}
/**
 * ����ID��
 * @param hasID �Ѿ����ڵ�ID��
 * @param maxID ���ID��
 * @return ������ص�ֵ������������ֵ��˵��û��ID��������
 */
	public static int generalID(int[]hasID,int maxID){
		int result=0;
		if(hasID.length>=maxID){
			//��ʾ��ǰ����ID�Ѿ�ռ���ˣ��������
			result=maxID+1;
		}else if(hasID.length==0){
			//��û������ʱ��Ĭ��IDΪ0
			result=0;
		}else{
			boolean isExist=true;
			while(isExist){
				Random rom=new Random();
				int gernalID=rom.nextInt(maxID);
				for(int i=0;i<hasID.length;i++){
					if(gernalID==hasID[i]){
						isExist=true;
						break;
					}else{
						if(i==hasID.length-1){
							isExist=false;
							result=gernalID;
						}
					}
				}
			}
			
			
			
		}
		
		return result;
		
	}

}
