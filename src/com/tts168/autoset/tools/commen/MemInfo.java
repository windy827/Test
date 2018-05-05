package com.tts168.autoset.tools.commen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.content.Context;
/**
 * �ڴ���Ϣ������
 * @author Ԭ��
 *
 */
public class MemInfo {

    // ��ÿ��õ��ڴ�
	/**
	 * ��ÿ��õ��ڴ�
	 * @param mContext
	 * @return
	 */
    public static long getmem_UNUSED(Context mContext) {
        long MEM_UNUSED;
	// �õ�ActivityManager
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
	// ����ActivityManager.MemoryInfo����  

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);

	// ȡ��ʣ����ڴ�ռ� 

        MEM_UNUSED = mi.availMem / 1024/1024;
        return MEM_UNUSED;
    }

    // ������ڴ�
    /**
     * ������ڴ�
     * @return
     */
    public static long getmem_TOLAL() {
        long mTotal;
        // /proc/meminfo�������ں���Ϣ���н���
        String path = "/proc/meminfo";
        String content = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path), 8);
            String line;
            if ((line = br.readLine()) != null) {
                content = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // beginIndex
        int begin = content.indexOf(':');
        // endIndex
        int end = content.indexOf('k');
        // ��ȡ�ַ�����Ϣ

	content = content.substring(begin + 1, end).trim();
        mTotal = Integer.parseInt(content);
        return mTotal;
    }
}