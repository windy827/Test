package com.tts168.autoset.tools.localmusic;

public class MusicSortModel {

	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母
	private String path;   //显示的路径
	private String url;   //显示的Http的URL
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
