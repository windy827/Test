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
	private boolean isDownload;//�Ƿ�����
	//public static OldImageLoader asyncBitmapLoader;
	public static DisplayImageOptions options;
	public static DisplayImageOptions options_big;
	private Activity cur_Activity=null;
	private AudioManager mAudioManager=null;
	public MyApplication() {
		super();
		
	}
	/**
	 * �õ�����������
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
	 * MyApplication�����
	 * ����ģʽ�л�ȡΨһ��MyApplicationʵ��
	 * 
	 */
	public static MyApplication getInstance(){
		return instance;
	}
	 // �쳣����  
    protected boolean isNeedCaughtExeption = true;// �Ƿ񲶻�δ֪�쳣  
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
//						Log.d("EXITAPP","�ѹرյ������е�SOCKET");
//					}else{
//						Log.d("EXITAPP","Ӧ�û�����");
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
	
	
	
    // -------------------�쳣����-----�����쳣������ϵͳ-----------------//  
	  
    private void cauchException() {  
        Intent intent = new Intent();  
        // ����1������������2��������ڵ�activity  
        intent.setClassName(packgeName, "com.tts168.autoset.activity.welcome.MainActivity");  
        restartIntent = PendingIntent.getActivity(getApplicationContext(), -1, intent,  
                Intent.FLAG_ACTIVITY_NEW_TASK);  
  
        // �������ʱ�����߳�  
        uncaughtExceptionHandler = new MyUncaughtExceptionHandler();  
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);  
    }  
  
    // �����������ڲ�������쳣  
    private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {  
        @Override  
        public void uncaughtException(Thread thread, Throwable ex) {  
            // ���������־  
            saveCatchInfo2File(ex);  
  
            // 1���Ӻ�����Ӧ��  
            AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);  
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent);  
  
            // �رյ�ǰӦ��  
            exitApp();  
           
        }  
    };  
  
    /** 
     * ���������Ϣ���ļ��� 
     *  
     * @return �����ļ����� 
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
                        // ����Ŀ¼ʧ��: һ������ΪSD�����γ���  
                        return "";  
                    }  
                }  
                System.out.println("filePath + fileName:" + filePath + fileName);  
                FileOutputStream fos = new FileOutputStream(filePath + fileName);  
                fos.write(sb.getBytes());  
                fos.close();  
                //�ļ���������֮��,��Ӧ���´�������ʱ��ȥ��������־,�����µĴ�����־,�ͷ��͸�������  
            }  
            return fileName;  
        } catch (Exception e) {  
            System.out.println("an error occured while writing file..." + e.getMessage());  
        }  
        return null;  
    } 
	/**
	 * �ж��Ƿ����ڴ濨
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
	 * ��ʼ��ImageLoader
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
		config.memoryCacheExtraOptions(480, 800); // max width, max height���������ÿ�������ļ�����󳤿�  
	    //config.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null); // Can slow ImageLoader, use it carefully (Better don't use it)/���û������ϸ��Ϣ����ò�Ҫ�������  
	    config.threadPoolSize(2);//�̳߳��ڼ��ص�����  
	    config.threadPriority(Thread.NORM_PRIORITY - 2);  
	    config.denyCacheImageMultipleSizesInMemory()  ;
	    
	    config.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)); // You can pass your own memory cache implementation/�����ͨ���Լ����ڴ滺��ʵ��  
	  // config.memoryCache(new WeakMemoryCache());
	    config.memoryCacheSize(2 * 1024 * 1024) ;   
	   // config.memoryCacheSize(100 * 1024) ;
	    	config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
			config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
	    	 config.discCache(new UnlimitedDiskCache(cacheDir));//�Զ��建��·��  
	 	
	    config.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)); // connectTimeout (5 s), readTimeout (30 s)��ʱʱ��  
		// Initialize ImageLoader with configuration.
	    config.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) ; 
		ImageLoader.getInstance().init(config.build());
		//asyncBitmapLoader=new OldImageLoader(context);
		if(hasSdcard()){
			options = new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ 
			.showImageOnLoading(R.drawable.default_small)
			.showImageForEmptyUri(R.drawable.default_small)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//����ͼƬ����εı��뷽ʽ��ʾ 
			
			.displayer(new SimpleBitmapDisplayer())//������ʾһ��ͼƬ
			 
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.build();
			options_big= new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ 
			.showImageOnLoading(R.drawable.default_big)
			.showImageForEmptyUri(R.drawable.default_big)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//����ͼƬ����εı��뷽ʽ��ʾ 
			
			.displayer(new SimpleBitmapDisplayer())//������ʾһ��ͼƬ
			 
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.considerExifParams(true)
			.build();
	 	   
	    }
		else{
			options = new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ 
			.showImageOnLoading(R.drawable.default_small)
			.showImageForEmptyUri(R.drawable.default_small)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//����ͼƬ����εı��뷽ʽ��ʾ 
			
			.displayer(new SimpleBitmapDisplayer())//������ʾһ��ͼƬ
			 
			.cacheInMemory(true)
			.considerExifParams(true)
			.build();
			options_big= new DisplayImageOptions.Builder()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ 
			.showImageOnLoading(R.drawable.default_small)
			.showImageForEmptyUri(R.drawable.default_small)
			.showImageOnFail(R.drawable.load_failed)
			.imageScaleType(ImageScaleType.EXACTLY)//����ͼƬ����εı��뷽ʽ��ʾ 
			
			.displayer(new SimpleBitmapDisplayer())//������ʾһ��ͼƬ
			 
			.cacheInMemory(true)
			.considerExifParams(true)
			.build();
		}
		
	}
	
	/**
	 * �õ���ǰActivity��ǰһ��ActivityName
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
	 * ���ٵ�֮ǰ��Activity��Դ
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
	 * �������˳�����Finish��activitys�����е�Activity
	 */
	public void exitApp(){
		Network.getInstance(cur_Activity).closeAllSocket();
		Log.d("EXITAPP","�ѹرյ������е�SOCKET");
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
		Log.d("EXITAPP","�ѹرյ������е�SOCKET");
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