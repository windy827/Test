package com.tts168.autoset.listener.edittext;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
/**
 * ���EditText�༭�������hint
 * @author yuanjian 
 *
 */

public class ClearHintOnFoucusChangeListener implements OnFocusChangeListener{

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		EditText et = (EditText) v;
		if (!hasFocus) {// ʧȥ����
			if(et.getTag()!=null){
				et.setHint(et.getTag().toString());
			}
			
		} else {
			String hint = et.getHint().toString();
			et.setTag(hint);// ����Ԥ����
			et.setHint(null);
		}
	}

}
