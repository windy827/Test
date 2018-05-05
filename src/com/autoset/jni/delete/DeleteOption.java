package com.autoset.jni.delete;

import java.util.ArrayList;

import android.util.Log;

import com.autoset.json.AutoSetJsonTools;

/**
 * Json��ɾ������
 * @author Ԭ��
 *
 */
public class DeleteOption {

	/**
	 * һ���ɾ��������Method�̶�ΪdeleteUserData
	 * @param id
	 * @param domainnames����
	 * @param IDs��Ҫɾ����ID
	 * @return
	 */
	public static String deleteContent(int id,String []domainnames,ArrayList<int[]> IDs,String setFailed,String setSuccess){
		String result="";
		AutoSetJsonTools tools=new AutoSetJsonTools();
		ArrayList<Object[]> IDs_String=new ArrayList<Object[]>();
		for(int i=0;i<IDs.size();i++){
			int[]con=IDs.get(i);
			Object []id_String=new Object[con.length];
			for(int j=0;j<id_String.length;j++){
				id_String[j]=con[i];
			}
			IDs_String.add(id_String);
		}
		result=tools.setDataDeleteJson(id, AutoSetJsonTools.DELETE_USERDATA, domainnames, IDs_String,0,setFailed,setSuccess);
		Log.d("DeleteOption",result);
		return result;
	}
}
