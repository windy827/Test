package com.autoset.jni.answer;

import java.io.Serializable;
/**
 * �ʴ�����ʵ����
 * @author Ԭ��
 *
 */
public class AnswerHelperEntity implements Serializable{
	public static final String METHOD_VALUES = "eventInd";// METHOD����
	public static final String PARAMAS_VALUES = "event";// PARAMAS�µ���������
	public static final String EVENT_NAME = "name";// event�µ���������
	public static final String EVENT_NAME_VALUES = "mediaPlay";// name��Ӧ��ֵ
	
	public static final String DOMAIN_NAME = "mediaInfo";// ��������
	public static final String QUERY_NAME = "query";// ����
	public static final String REPLY_NAME = "reply";// �ظ�
	public static final String HELP_NAME = "help";// ����
	public static final String STATUS_NAME = "status";// ����״̬
	
	public static final String STATUS_START="start";//���ڲ���״̬
	public static final String STATUS_PAUSE="pause";//��ͣ״̬
	public static final String STATUS_RESUME="resume";//��ͣ�ָ�����״̬
	public static final String STATUS_STOP="stop";//ֹͣ״̬
	
	private int id;//���
	private String question;//����
	private String reply;//�ظ�
	private String help;//����
	private String date;//����
	private String status;//����״̬
	

	public AnswerHelperEntity(int id, String question, String reply,
			String help, String date,String status) {
		super();
		this.id = id;
		this.question = question;
		this.reply = reply;
		this.help = help;
		this.date = date;
		this.status=status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
