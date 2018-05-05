package com.tts168.autoset.tools.uiltools;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tts168.autoset.activity.webmusic.AnimateFirstDisplayListener;
import com.tts168.autoset.tools.commen.MyApplication;

import android.widget.ImageView;
/**
 * UIL��ܵĹ�����
 * @author Ԭ��
 *
 */
public class ImageLoaderTools {

	/**
	 * isbigDefaultImg  ����ͼƬ�Ƿ�Ϊ��ͼ
	 *UIL ��ܼ��ر���ͼƬ **
	 *String imageUri = "http://site.com/image.png"; // from Web  
String imageUri = "file:///mnt/sdcard/image.png"; // from SD card  
String imageUri = "content://media/external/audio/albumart/13"; // from content provider  
String imageUri = "assets://image.png"; // from assets  
String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch) 
	 */
	public static void setImage(ImageView iv,int resid,boolean isbigDefaultImg){
		//ͼƬ��Դ��drawable
        String drawableUrl = "drawable://"+resid;//����WIFI ǿ�ȵ�ͼƬ
        ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
        DisplayImageOptions  op;
        if(isbigDefaultImg){
        	op=MyApplication.options_big;
        }else{
        	op=MyApplication.options;
        }
        ImageLoader.getInstance().displayImage(drawableUrl, iv, op, animateFirstListener);
	}
}
