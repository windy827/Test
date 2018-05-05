package com.autoset.jni.answer;

import java.io.Serializable;
/**
 * 问答助手实体类
 * @author 袁剑
 *
 */
public class AnswerHelperEntity implements Serializable{
	public static final String METHOD_VALUES = "eventInd";// METHOD名称
	public static final String PARAMAS_VALUES = "event";// PARAMAS下的内容名称
	public static final String EVENT_NAME = "name";// event下的内容名称
	public static final String EVENT_NAME_VALUES = "mediaPlay";// name对应的值
	
	public static final String DOMAIN_NAME = "mediaInfo";// 参数领域
	public static final String QUERY_NAME = "query";// 问题
	public static final String REPLY_NAME = "reply";// 回复
	public static final String HELP_NAME = "help";// 帮助
	public static final String STATUS_NAME = "status";// 播放状态
	
	public static final String STATUS_START="start";//正在播放状态
	public static final String STATUS_PAUSE="pause";//暂停状态
	public static final String STATUS_RESUME="resume";//暂停恢复播放状态
	public static final String STATUS_STOP="stop";//停止状态
	
	private int id;//编号
	private String question;//问题
	private String reply;//回复
	private String help;//帮助
	private String date;//日期
	private String status;//播放状态
	

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
