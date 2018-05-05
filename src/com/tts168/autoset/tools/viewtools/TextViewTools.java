package com.tts168.autoset.tools.viewtools;

import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class TextViewTools {

	/**
	 * 设置TextView的颜色
	 * @param textView
	 * @param start
	 * @param end
	 * @param color
	 */
	public static  void setTextColor(TextView textView,int start,int end,int color){
		SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());  
		  
		//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色  
		ForegroundColorSpan redSpan = new ForegroundColorSpan(color);  
		builder.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);   
		textView.setText(builder);  
	}
	/**
	 * 设置下划线
	 * @param textView
	 */
	public static void setTextUnderLine(TextView textView){
		textView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线

		textView.getPaint().setAntiAlias(true);//抗锯齿
	}
}
