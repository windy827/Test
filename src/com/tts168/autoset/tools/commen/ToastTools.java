package com.tts168.autoset.tools.commen;

import android.content.Context;
import android.widget.Toast;

public class ToastTools {
	/**
	 * 100ºÁÃëµÄToast
	 * @param c
	 * @param msg
	 */
public static void short_Toast(Context c,String msg){
	Toast.makeText(c, msg, 100).show();
}
/**
 * 1000ºÁÃëµÄToast
 * @param c
 * @param msg
 */
public static void long_Toast(Context c,String msg){
Toast.makeText(c, msg, 2000).show();
}
}
