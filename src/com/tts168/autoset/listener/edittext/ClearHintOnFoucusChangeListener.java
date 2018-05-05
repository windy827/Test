package com.tts168.autoset.listener.edittext;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
/**
 * µã»÷EditText±à¼­¿òÇå³ýµôhint
 * @author yuanjian 
 *
 */

public class ClearHintOnFoucusChangeListener implements OnFocusChangeListener{

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		EditText et = (EditText) v;
		if (!hasFocus) {// Ê§È¥½¹µã
			if(et.getTag()!=null){
				et.setHint(et.getTag().toString());
			}
			
		} else {
			String hint = et.getHint().toString();
			et.setTag(hint);// ±£´æÔ¤Éè×Ö
			et.setHint(null);
		}
	}

}
