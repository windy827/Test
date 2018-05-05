package com.tts168.autoset.tools.commen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import com.geniusgithub.lazyloaddemo.cache.OldImageLoader;
import com.larkiv.larksmart7618.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tts168.autoset.activity.MyBaseActivity;
import com.tts168.autoset.activity.SearchDevicesActivity;
import com.tts168.autoset.activity.alart.addalart.AlartFragmentTabActivity;
import com.tts168.autoset.service.CheckStateService;
import com.tts168.autoset.tools.network.Network;
import com.tts168.autoset.tools.others.NetWorkUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.AudioManager;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
@ReportsCrashes(formKey = "", // will not be used
mailTo ="yj583129781@163.com",
customReportContent = { ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT }, 
mode = ReportingInteractionMode.TOAST,

resToastText = R.string.crash_toast_text)
public class MyApplication extends Application {
	
	private List<Activity> activitys = null;
	private static MyApplication instance;
	private boolean isDownload;//是否下载
	//public static OldImageLoader asyncBitmapLoader;
	public static DisplayImageOptions options;
	public static DisplayImageOptions options_big;
	private Activity cur_Activity=null;
	private AudioManager mAudioManager=null;
	public MyApplication() {
		super();
		
	}
	/**
	 * 得到声音播放类
	 * @return
	 */
	public AudioManager getAudioManager(){
		if(mAudioManager==null){
			mAudioManager=(AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		}
		return mAudioManager;
	}
	public boolean isDownload() {
		return isDownload;
	}

	public Activity getCur_Activity() {
		return cur_Activity;
	}
	public void setCur_Activity(Activity cur_Activity) {
		this.cur_Activity = cur_Activity;
	}
	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	/**
	 * MyApplication的入口
	 * 单例模式中获取唯一的MyApplication实例
	 * 
	 */
	public static MyApplication getInstance(){
		return instance;
	}
	 // 异常捕获  
    protected boolean isNeedCaughtExeption = true;// 是否捕获未知异常  
    private PendingIntent restartIntent;  
    private MyUncaughtExceptionHandler uncaughtExceptionHandler;  
    private String packgeName;  
  
    private Thread th;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initImageLoader(this);
		activitys = new ArrayList<Activity>();
		isDownload = false;
		
		packgeName = getPackageName();  
		  
        if (isNeedCaughtExeption) {  
            cauchException();  
            ACRA.init(this);
        }  
      // this.startService(new Intent(this, CheckStateService.class));
//        th=new Thread(new Runnable() {
//        	 boolean isrun=true;
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				while(isrun){
//					if(!isRunningApp(getApplicationContext(),"com.larkiv.larksmart")){
//						isrun=false;
//						Network.getInstance(cur_Activity).closeAllSocket();
//						Log.d("EXITAPP","已关闭掉了所有的SOCKET");
//					}else{
//						Log.d("EXITAPP","应用还存在");
//					}
//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				
//			}
//		});
//        th.start();
	}
	
	
	
    // -------------------异常捕获-----捕获异常后重启系统-----------------//  
	  
    private void cauchException() {  
        Intent intent = new Intent();  
        // 参数1：包名，参数2：程序入口的activity  
        intent.setClassName(packgeName, "com.tts168.autoset.activity.welcome.MainActivity");  
        restartIntent = PendingIntent.getActivity(getApplicationContext(), -1, intent,  
                Intent.FLAG_ACTIVITY_NEW_TASK);  
  
        // 程序崩溃时触发线程  
        uncaughtExceptionHandler = new MyUncaughtExceptionHandler();  
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);  
    }  
  
    // 创建服务用于捕获崩溃异常  
    private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {  
        @Override  
        public void uncaughtException(Thread thread, Throwable ex) {  
            // 保存错误日志  
            saveCatchInfo2File(ex);  
  
            // 1秒钟后重启应用  
            AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);  
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent);  
  
            // 关闭当前应用  
            exitApp();  
           
        }  
    };  
  
    /** 
     * 保存错误信息到文件中 
     *  
     * @return 返回文件名称 
     */  
    private String saveCatchInfo2File(Throwable ex) {  
        Writer writer = new StringWriter();  
        PrintWriter printWriter = new PrintWriter(writer);  
        ex.printStackTrace(printWriter);  
        Throwable cause = ex.getCause();  
        while (cause != null) {  
            cause.printStackTrace(printWriter);  
            cause = cause.getCause();  
        }  
        printWriter.close();  
        String sb = writer.toString();  
        try {  
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
            String time = formatter.format(new Date());  
            String fileName = time + ".txt";  
            System.out.println("fileName:" + fileName);  
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  
                String filePath = Environment.getExternalStorageDirectory() + "/HKDownload/" + packgeName  
                        + "/crash/";  
                File dir = new File(filePath);  
                if (!dir.exists()) {  
                    if (!dir.mkdirs()) {  
                        // 创建目录失败: 一般是因为SD卡被拔出了  
                        return "";  
                    }  
                }  
                System.out.println("filePath + fileName:" + filePath + fileName);  
                FileOutputStream fos = new FileOutputStream(filePath + fileName);  
                fos.write(sb.getBytes());  
                fos.close();  
                //文件保存完了之后,在应用下次启动的时候去检查错误日志,发现新的错误日志,就发送给开发者  
            }  
            return fileName;  
        } catch (Exception e) {  
            System.out.println("an error occured while writing file..." + e.getMessage());  
        }  
        return null;  
    } 
	/**
	 * 判断是否有内存卡
	 * 
	 * @return
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 初始化ImageLoader
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		File cacheDir = StorageUtils.getOwnCacheDirectory(context.getApplicationContext(), "/mnt/sdcard/larksmart/");
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		
		
		
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app
		config.memoryCacheExtraOptions(480, 800); // max width, max height，即保存的每个缓存文件的最大长宽  
	    //config.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null); // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个  
	    config.threadPoolSize(2);//线程池内加载的数量  
	    config.threadPriority(Thread.NORM_PRIORITY - 2);  
	    config.denyCacheImageMultipleSizesInMemory()  ;
	    
	    config.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)); // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
	  // config.memoryCache(new WeakMemoryCache());
	    config.memoryCacheSize(2 * 1024 * 1024) ;   
	   // config.memoryCacheSize(100 * 1024) ;
	    	config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
			config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
	    	 config.discCache(new UnlimitedDiskCache(cacheDir));//自定义缓存路径  
	 	
	    config.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)); // connectTimeout (5 s), readTimeout (30 s)超时时间  
		// Initialize ImageLoader with configuration.
	    config.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) ; 
		ImageLoader.getInstance().init(config.build());
		//asyncBitmapLoader=new OldImageLoader(context);
		if(hasSdcard()){
			options = new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位 
			.showImageOnLoading(R.drawable.default_small)
			.showImageForEmptyUri(R.drawable.default_small)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示 
			
			.displayer(new SimpleBitmapDisplayer())//正常显示一张图片
			 
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.build();
			options_big= new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位 
			.showImageOnLoading(R.drawable.default_big)
			.showImageForEmptyUri(R.drawable.default_big)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示 
			
			.displayer(new SimpleBitmapDisplayer())//正常显示一张图片
			 
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.build();
	 	   
	    }
		else{
			options = new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位 
			.showImageOnLoading(R.drawable.default_small)
			.showImageForEmptyUri(R.drawable.default_small)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示 
			
			.displayer(new SimpleBitmapDisplayer())//正常显示一张图片
			 
			.cacheInMemory(true)
			.considerExifParams(true)
			.build();
			options_big= new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位 
			.showImageOnLoading(R.drawable.default_small)
			.showImageForEmptyUri(R.drawable.default_small)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示 
			
			.displayer(new SimpleBitmapDisplayer())//正常显示一张图片
			 
			.cacheInMemory(true)
			.considerExifParams(true)
			.build();
		}
		
	}
	
	/**
	 * 得到当前Activity的前一个ActivityName
	 * @return
	 */
	public String getPreActivityName(){
		String name="";
		MyBaseActivity activity=null;
		try {
			activity=(MyBaseActivity) activitys.get(activitys.size()-2);
			name=activity.getActivityName();
		} catch (Exception e) {
			// TODO: handle exception
			name=AlartFragmentTabActivity.ActivityName;
		}
		
		
		return name;
	}

	/**
	 * 销毁掉之前的Activity资源
	 */
//	public void finishPreviewActivity1(){
//		if(activitys!=null&&activitys.size()>0){
//			for(Activity activity:activitys){
//				if(!activity.isFinishing())
//				{activity.finish();
//				}
//			}
//		}
//		activitys.clear();
//	}
	/**
	 * 遍历并退出程序，Finish掉activitys中所有的Activity
	 */
	public void exitApp(){
		Network.getInstance(cur_Activity).closeAllSocket();
		Log.d("EXITAPP","已关闭掉了所有的SOCKET");
		MyActivityManager.getScreenManager().popAllActivity();
		ImageLoader.getInstance().clearMemoryCache();
		ImageLoader.getInstance().stop();
		//asyncBitmapLoader.clearCache(true, false);
		
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		Network.getInstance(cur_Activity).closeAllSocket();
		Log.d("EXITAPP","已关闭掉了所有的SOCKET");
		MyActivityManager.getScreenManager().popAllActivity();
		ImageLoader.getInstance().clearMemoryCache();
		ImageLoader.getInstance().stop();
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onTerminate();
	}
	
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		ImageLoader.getInstance().clearMemoryCache();
		ActivitySetting.startActivity(MyApplication.getInstance().getCur_Activity(), SearchDevicesActivity.class, null, true);
		Log.d("MEMORY_APPLICATION","onLowMemory---Aplication");
		super.onLowMemory();
	}
}