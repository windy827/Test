package com.tts168.autoset.watcher;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.tools.Tools;
import com.tts168.autoset.tools.commen.MyApplication;
import com.tts168.autoset.tools.commen.ToastTools;

import android.graphics.Color;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 监听输入内容是否超出最大长度，并设置光标位置,并可以
 * 用一个TextView来显示当前输入的进度，不想用TextView传null即可
 * */
public class MaxLengthWatcher implements TextWatcher {

	private int maxLen = 0;
	private EditText editText = null;
	private TextView tv_remind = null;
	
	public MaxLengthWatcher(int maxLen, EditText editText,TextView tv_remind) {
		this.maxLen = maxLen;
		this.editText = editText;
		this.tv_remind=tv_remind;
		setTextTotal();
	}

	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void setTextTotal(){
		Editable editable = editText.getText();
		int len = editable.length();
		if(tv_remind!=null){
			tv_remind.setText(len+"/"+maxLen);
		}
	}
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		String text=editText.getText().toString();
		
		if(text.startsWith(" ")){
			String temp=text.replace(" ", "");
			editText.setText(temp);
		}
		Editable editable = editText.getText();
		
		int len = editable.length();
		
		if(len > maxLen)
		{
			//提示输入超标
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "已经超过了限度的"+maxLen+"字长度！！！！");
			int selEndIndex = Selection.getSelectionEnd(editable);
			String str = editable.toString();
			//截取新字符串
			String newStr = str.substring(0,maxLen);
			editText.setText(newStr);
			editable = editText.getText();
			
			//新字符串的长度
			int newLen = editable.length();
			//旧光标位置超过字符串长度
			if(selEndIndex > newLen)
			{
				selEndIndex = editable.length();
			}
			//设置新光标所在的位置
			Selection.setSelection(editable, selEndIndex);
			
		}
		else{
			if(tv_remind!=null){
				tv_remind.setText(len+"/"+maxLen);
			}
			
			if(len== maxLen){
				if(tv_remind!=null){
					tv_remind.setTextColor(Color.RED);
				}
				
			}else{
				if(tv_remind!=null){
					tv_remind.setTextColor(R.color.gray66_color);
				}
				
			}
			
		}
	}

}

