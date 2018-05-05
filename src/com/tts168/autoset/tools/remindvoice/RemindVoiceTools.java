package com.tts168.autoset.tools.remindvoice;
/**
 * ��Ŷ��������ID������Ҫ����������ʱ���MediaPlay��content����
 * @author Ԭ��
 *
 */
public class RemindVoiceTools {
/**
	ID	Ӧ�ó���	��ʾ������

	10101	���Ѳ���-�޸ĳɹ�	���Ѳ����޸ĳɹ�;
	10102	���Ѳ���-�޸�ʧ��	���Ѳ����޸�ʧ��;
	10112	��ʾ�����������ɹ�	���Ѳ��������ɹ�;
	10113	��ʾ������-�رճɹ�	���Ѳ����رճɹ�;
	
	10103	��������Hi�Ʊ�	Hi�Ʊ�;
	10104	��������HiС��	HiС��;
	10105	���������Ʊ�	�Ʊ�;
	10106	��������С��	С��;
	10107	��������Hi���Ʊ�	Hi���Ʊ�;
	10108	��������Hi��С��	Hi��С��
	10109	������������
	10110	��������Hi����
	10111	��������Hi������
 	*/
	public static class AwakeRemindTools{
		public static final int SUCESS_UPDATE_WAKEUP=10101;
		public static final int FAILED_UPDATE_WAKEUP=10102;
		
		public static final int SUCESS_UPDATE_WAKEUP_promp_switch_on=10112;
		public static final int SUCESS_UPDATE_WAKEUP_promp_switch_off=10113;
		
		public static final int NAME1_WAKEUP=10103;
		public static final int NAME2_WAKEUP=10104;
		public static final int NAME3_WAKEUP=10105;
		
		public static final int NAME4_WAKEUP=10106;
		public static final int NAME5_WAKEUP=10107;
		public static final int NAME6_WAKEUP=10108;
		
		public static final int NAME7_WAKEUP=10109;
		public static final int NAME8_WAKEUP=10110;
		public static final int NAME9_WAKEUP=10111;
	}

	/**10201	������-����ʧ��	���÷�����ʧ��;
	10202	������-���óɹ�	���÷����˳ɹ�;
	10203	������-����	[m3]��������[m3];
	10204	������-��С��	[m51]������С��[m3];
	10205	������-��Сǿ	[m52]������Сǿ[m3];
	10206	������-������	[m53]����������[m3];
	10207	������-����Ѽ	[m54]��������Ѽ[m3];
	10208	������-С����	[m55]����С����[m3];
	10301	�ն��б�����豸�����İ�ť	[m55]������[m3]*/
	public static class AnounciatorRemindTools{
		public static final int SUCESS_UPDATE_ANOUCIATOR=10201;
		public static final int FAILED_UPDATE_WAKEUP=10202;
		
		public static final int ANOUCIATOR_XIAOLING=10203;
		public static final int ANOUCIATOR2_YINXIAOJIAN=10204;
		public static final int ANOUCIATOR3_YIXIAOQIANG=10205;
		public static final int ANOUCIATOR4_TIANBEIBEI=10206;
		public static final int ANOUCIATOR5_TAOLAOYA=10207;
		public static final int ANOUCIATOR6_XIAOYANZI=10208;
		public static final int FINDDEVICE=10301;
	}

	/**
	10302	���ֻ�App���������Ʊ��豸ʱ	������*/
	public static class OthersRemind{
	
		public static final int DEVICE_CONNECTED=10302;
	}
			
	/**11101	����-��---���-�ɹ�	��������ӳɹ�
	11102	����-��---�޸�-�ɹ�	�������޸ĳɹ�
	11103	����-��---ɾ��-�ɹ�	������ɾ���ɹ�
	11104	����-��---�ر�-�ɹ�	������رճɹ�
	
	11105	����-��---���-ʧ��	���������ʧ��
	11106	����-��---�޸�-ʧ��	�������޸�ʧ��
	11107	����-��---ɾ��-ʧ��	������ɾ��ʧ��
	11108	����-��---�ر�-ʧ��	������ر�ʧ��*/
	public static class AwakeUpAlartRemindTools{
		public static final int SUCESS_ADD_AwakeUpAlart=11101;
		public static final int FAILED_ADD_AwakeUpAlart=11105;
		
		public static final int SUCESS_UPDATE_AwakeUpAlart=11102;
		public static final int FAILED_UPDATE_AwakeUpAlart=11106;
		
		public static final int SUCESS_DELETE_AwakeUpAlart=11103;
		public static final int FAILED_DELETE_AwakeUpAlart=11107;
		
		public static final int SUCESS_CLOSE_AwakeUpAlart=11104;
		public static final int FAILED_CLOSE_AwakeUpAlart=11108;
	}

	/**11201	����-˯��---���-�ɹ�	˯��������ӳɹ�
	11202	����-˯��---�޸�-�ɹ�	˯�������޸ĳɹ�
	11203	����-˯��---ɾ��-�ɹ�	˯������ɾ���ɹ�
	11204	����-˯��---�ر�-�ɹ�	˯������رճɹ�
	11205	����-˯��---���-ʧ��	˯���������ʧ��
	11206	����-˯��---�޸�-ʧ��	˯�������޸�ʧ��
	11207	����-˯��---ɾ��-ʧ��	˯������ɾ��ʧ��
	11208	����-˯��---�ر�-ʧ��	˯������ر�ʧ��*/
	public static class SleepAlartRemindTools{
		public static final int SUCESS_ADD_SleepAlart=11201;
		public static final int FAILED_ADD_SleepAlart=11205;
		
		public static final int SUCESS_UPDATE_SleepAlart=11202;
		public static final int FAILED_UPDATE_SleepAlart=11206;
		
		public static final int SUCESS_DELETE_SleepAlart=11203;
		public static final int FAILED_DELETE_SleepAlart=11207;
		
		public static final int SUCESS_CLOSE_SleepAlart=11204;
		public static final int FAILED_CLOSE_SleepUpAlart=11208;
	}

	/**11301	����-�Զ���-���-�ɹ�	�Զ���������ӳɹ�
	11302	����-�Զ���-�޸�-�ɹ�	�Զ��������޸ĳɹ�
	11303	����-�Զ���-ɾ��-�ɹ�	�Զ�������ɾ���ɹ�
	11304	����-�Զ���-�ر�-�ɹ�	�Զ�������رճɹ�
	11305	����-�Զ���-���-ʧ��	�Զ����������ʧ��
	11306	����-�Զ���-�޸�-ʧ��	�Զ��������޸�ʧ��
	11307	����-�Զ���-ɾ��-ʧ��	�Զ�������ɾ��ʧ��
	11308	����-�Զ���-�ر�-ʧ��	�Զ�������ر�ʧ��*/
	public static class DefinedAlartRemindTools{
		public static final int SUCESS_ADD_DefinedAlart=11301;
		public static final int FAILED_ADD_DefinedAlart=11305;
		
		public static final int SUCESS_UPDATE_DefinedAlart=11302;
		public static final int FAILED_UPDATE_DefinedAlart=11306;
		
		public static final int SUCESS_DELETE_DefinedAlart=11303;
		public static final int FAILED_DELETE_DefinedAlart=11307;
		
		public static final int SUCESS_CLOSE_DefinedAlart=11304;
		public static final int FAILED_CLOSE_DefinedAlart=11308;
	}
			
	/**11401	����-����---���-�ɹ�	���챸����ӳɹ���
	11402	����-����---�޸�-�ɹ�	���챸���޸ĳɹ���
	11403	����-����---ɾ��-�ɹ�	���챸��ɾ���ɹ���
	11404	����-����---�ر�-�ɹ�	���챸���رճɹ���
	11405	����-����---���-ʧ��	���챸�����ʧ�ܣ�
	11406	����-����---�޸�-ʧ��	���챸���޸�ʧ�ܣ�
	11407	����-����---ɾ��-ʧ��	���챸��ɾ��ʧ�ܣ�
	11408	����-����---�ر�-ʧ��	���챸���ر�ʧ��*/
	public static class RemindAlartRemindTools{
		public static final int SUCESS_ADD_RemindAlart=11401;
		public static final int FAILED_ADD_RemindAlart=11405;
		
		public static final int SUCESS_UPDATE_RemindAlart=11402;
		public static final int FAILED_UPDATE_RemindAlart=11406;
		
		public static final int SUCESS_DELETE_RemindAlart=11403;
		public static final int FAILED_DELETE_RemindAlart=11407;
		
		public static final int SUCESS_CLOSE_RemindAlart=11404;
		public static final int FAILED_CLOSE_RemindAlart=11408;
	}

	/** 11501	����-����---���-�ɹ�	������ӳɹ���
		11502	����-����---�޸�-�ɹ�	�����޸ĳɹ���
		11503	����-����---ɾ��-�ɹ�	����ɾ���ɹ���
		11504	����-����---�ر�-�ɹ�	���չرճɹ���
		11505	����-����---���-ʧ��	�������ʧ�ܣ�
		11506	����-����---�޸�-ʧ��	�����޸�ʧ�ܣ�
		11507	����-����---ɾ��-ʧ��	����ɾ��ʧ�ܣ�
		11508	����-����---�ر�-ʧ��	���չر�ʧ��*/
	public static class BirthdayAlartRemindTools{
		public static final int SUCESS_ADD_BirthdayAlart=11501;
		public static final int FAILED_ADD_BirthdayAlart=11505;
		
		public static final int SUCESS_UPDATE_BirthdayAlart=11502;
		public static final int FAILED_UPDATE_BirthdayAlart=11506;
		
		public static final int SUCESS_DELETE_BirthdayAlart=11503;
		public static final int FAILED_DELETE_BirthdayAlart=11507;
		
		public static final int SUCESS_CLOSE_BirthdayAlart=11504;
		public static final int FAILED_CLOSE_BirthdayAlart=11508;
	}
	
	
	
	
	/**
	40101	ͨ�ã���ӳɹ�;
	40102	ͨ�ã��޸ĳɹ�;
	40103	ͨ�ã�ɾ���ɹ�;
	40104	ͨ�ã��رճɹ�;
	40105	ͨ�ã����óɹ�;
	
	40106	ͨ�ã����ʧ��;
	40107	ͨ�ã��޸�ʧ��;
	40108	ͨ�ã�ɾ��ʧ��;
	40109	ͨ�ã��ر�ʧ��;
	40110	ͨ�ã�����ʧ��*/
	public static class CommentRemindTools{
		public static final int SUCESS_ADD=40101;
		public static final int FAILED_ADD=40106;
		
		public static final int SUCESS_UPDATE=40102;
		public static final int FAILED_UPDATE=40107;
		
		public static final int SUCESS_DELETE=40103;
		public static final int FAILED_DELETE=40108;
		
		public static final int SUCESS_CLOSE=40104;
		public static final int FAILED_CLOSE=40109;
		
		public static final int SUCESS_SET=40105;
		public static final int FAILED_SET=40110;
	}

}
