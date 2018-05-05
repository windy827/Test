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
 * �������������Ƿ񳬳���󳤶ȣ������ù��λ��,������
 * ��һ��TextView����ʾ��ǰ����Ľ��ȣ�������TextView��null����
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
			//��ʾ���볬��
			ToastTools.short_Toast(MyApplication.getInstance().getCur_Activity(), "�Ѿ��������޶ȵ�"+maxLen+"�ֳ��ȣ�������");
			int selEndIndex = Selection.getSelectionEnd(editable);
			String str = editable.toString();
			//��ȡ���ַ���
			String newStr = str.substring(0,maxLen);
			editText.setText(newStr);
			editable = editText.getText();
			
			//���ַ����ĳ���
			int newLen = editable.length();
			//�ɹ��λ�ó����ַ�������
			if(selEndIndex > newLen)
			{
				selEndIndex = editable.length();
			}
			//�����¹�����ڵ�λ��
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

