package com.tts168.autoset.view;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.larkiv.larksmart7618.R;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.listener.edittext.ClearHintOnFoucusChangeListener;
import com.tts168.autoset.watcher.MaxLengthWatcher;
/**
 * ����������TextView
 * @author Ԭ��
 *
 */
public class EditTextAndTextView{
	View view;
	Activity activity;
	public EditText et_suggest_content;// �������������
	public TextView tv_total;// ͳ���û�������ֵĸ����Ľ���
	public int MAX_CONTENTTEXTTOTAL = 40;// ��������Ľ�������ֵĸ���
	//public int MAX_USEINFOTEXTTOTAL = 100;// ��������Ľ�������ֵĸ���
	public int current_TextTotal = 0;// ��ǰ������ı��ĳ���
	public EditTextAndTextView(Activity activity) {
		this.activity=activity;
		staticFindViewByActivity();
		
	}
	public EditTextAndTextView(View view) {
		this.view=view;
		staticFindViewByView();
	}
	/**
	 * ����hiniֵ
	 * @param hinit
	 */
	public void setEditTextHinit(String hinit){
		et_suggest_content.setHint(hinit);
	}
/**
 * EditText�����������󳤶�
 * @param max
 */
	public void setMaxLength(int max){
		MAX_CONTENTTEXTTOTAL=max;
		

		tv_total.setText(current_TextTotal + "/" + MAX_CONTENTTEXTTOTAL);
		et_suggest_content.addTextChangedListener(new MaxLengthWatcher(
				MAX_CONTENTTEXTTOTAL, et_suggest_content, tv_total));
	}


	public void staticFindViewByActivity() {
		// TODO Auto-generated method stub
		et_suggest_content = (EditText) activity.findViewById(R.id.et_view_model_edittext_content);
		tv_total = (TextView) activity.findViewById(R.id.tv_view_model_wordtotal);
		tv_total.setText(current_TextTotal + "/" + MAX_CONTENTTEXTTOTAL);
		et_suggest_content.setOnFocusChangeListener(new ClearHintOnFoucusChangeListener());
		et_suggest_content.setOnEditorActionListener(new OnEditorActionListener() { 
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { 
			return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
			}
			});
	}
	public void staticFindViewByView() {
		// TODO Auto-generated method stub
		et_suggest_content = (EditText) view.findViewById(R.id.et_view_model_edittext_content);
		tv_total = (TextView) view.findViewById(R.id.tv_view_model_wordtotal);
		tv_total.setText(current_TextTotal + "/" + MAX_CONTENTTEXTTOTAL);
		et_suggest_content.setOnFocusChangeListener(new ClearHintOnFoucusChangeListener());
		et_suggest_content.setOnEditorActionListener(new OnEditorActionListener() { 
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { 
			return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
			}
			});
	}
	

}
