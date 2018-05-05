package com.autoset.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.autoset.jni.heartbeat.HeartBeatEntity;
import com.autoset.jni.play.PlayEntity;
import com.autoset.jni.play.PlayItemEntity;
import com.autoset.jni.udp.SearchSendEntity;
import com.tts168.autoset.tools.others.time.GetAndSetTime;
/**
 * �����ͽ��������Ʊ���Json
 * @author Ԭ��
 *
 */
public class AutoSetJsonTools {
	JSONObject mainJSon;
	JSONObject paramsJSon;
	JSONObject dataJSon;
	JSONArray getDomainDataJSonArray;
	JSONArray mediaplayJSonArray;
	JSONObject domainJSon;
	JSONArray domainJSonArray;
	String[] keys ;
	Object[] values ;
	ArrayList<HashMap<String, Object[]>> list;
	
	public static class NameAndValues{
		public static final String JSON_METHOD="method";
		public static final String JSON_PARAMS="params";
		public static final String JSON_DATA="data";
		public static final String JSON_ID="id";
		public static final String JSON_DELETEID="ID";//ɾ��ʱ��id��Ӧ�Ĺؼ���
		public static final String JSON_RESULT="result";//�Ʊ����ؽ���ؼ���
		public static final String JSON_ERROR="error";//�Ʊ����ش���ؼ���
	}
	
	public static final String GET_USERDATA	="getUserData";//�����ȡ�Ʊ��û����� 
	public static final String SET_USERDATA	="setUserData";//���������Ʊ��û�����
	public static final String INSERT_USERDATA="insertUserData";//����Ʊ��û�����
	public static final String DELETE_USERDATA="deleteUserData";//ɾ���Ʊ��û�����
	public static final String SET_USERDATA_WITHOUT_RETURN="setuserdata_WITHOUT_RETURN";//���������Ʊ��û�����,�����޸ĺ󲻷���
	
	/**
	 * ����name�õ�Json�����Doule������
	 * @param json
	 * @param name
	 * @return
	 */
	public static double getDoubleInJson(JSONObject json,String name){
		double result=0.0;
		try{
			result=json.getDouble(name);
		}catch(JSONException e){
			//e.printStackTrace();
			result=0.0;
		}
		return result;
	}
	
	/**
	 * ����name�õ�Json�����String������
	 * @param json
	 * @param name
	 * @return
	 */
	public static String getStringInJson(JSONObject json,String name){
		String result="";
		try{
			result=json.getString(name);
		}catch(JSONException e){
			//e.printStackTrace();
			result="";
		}
		
		return result;
	}
	
	/**
	 * ����name�õ�Json�����boolean������
	 * @param json
	 * @param name
	 * @return
	 */
	public static boolean getBooleanInJson(JSONObject json,String name){
		boolean result=true;
		try{
			result=json.getBoolean(name);
		}catch(JSONException e){
			//e.printStackTrace();
			result=true;
		}
		return result;
	}
	/**
	 * ����method������ȷ��ID��
	 * @param method
	 * @return
	 */
	public static int getJsonIDByMethod(String method){
		int result=ID_getUserData;
		if(method.equals(GET_USERDATA)){
			result=ID_getUserData;
		}else if(method.equals(SET_USERDATA)){
			result=ID_setUserData;
		}else if(method.equals(INSERT_USERDATA)){
			result=ID_insertUserData;
		}
		else if(method.equals(DELETE_USERDATA)){
			result=ID_deleteUserData;
		}
		return result;
	}
	/**
	 * setUserData��Ӧ��ID
	 */
	public static final int ID_setUserData=1;
	/**
	 * getUserData��Ӧ��ID
	 */
	public static final int ID_getUserData=2;
	
	/**
	 * insertUserData��Ӧ��ID
	 */
	public static final int ID_insertUserData=3;

	/**
	 * deleteUserData��Ӧ��ID
	 */
	public static final int ID_deleteUserData=4;
	
	
	//--------------------------------------------------------------------------------------------
	/**
	 * ����״̬�ָ���Domain����
	 * @param method
	 * @param domainName ������
	 * @param keys �ؼ�������
	 * @param allvalues ����ؼ��ֶ�Ӧ��ֵ����
	 * @param setFailed ����ʧ�ܵ�״̬�ָ�����
	 * @param setSuccess ���óɹ���״̬�ظ�����
	 * @return
	 */
	public String setDomainJsonArrayWithOptionPlay(String method,String domainName,String[]keys,ArrayList<Object[]>allvalues,int type,String setFailed,String setSuccess){
		String content="��������"; 
		try {
			int id=getJsonIDByMethod( method);
			setMyJsonHead(method);
			 setDomainJsonArray( domainName,keys,allvalues);
			 setOptionsJsonArray( type,setFailed, setSuccess);
			 mainJSon.put("params", paramsJSon);
			 content=mainJSon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	/**
	 * ������״̬�ָ���Domain����
	 * @param method
	 * @param domainName ������
	 * @param keys �ؼ�������
	 * @param allvalues ����ؼ��ֶ�Ӧ��ֵ����
	
	 * @return
	 */
	public String setDomainJsonArrayNoOptionPlay(String method,String domainName,String[]keys,ArrayList<Object[]>allvalues){
		String content="��������"; 
		try {
			int id=getJsonIDByMethod( method);
			setMyJsonHead(method);
			 setDomainJsonArray( domainName,keys,allvalues);
			 mainJSon.put("params", paramsJSon);
			 content=mainJSon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * ����״̬�ָ���DomainJson
	 * @param method
	 * @param domainName ������
	 * @param keys �ؼ�������
	 * @param allvalues �ؼ��ֶ�Ӧ��ֵ����
	 * @param setFailed ����ʧ�ܵ�״̬�ָ�����
	 * @param setSuccess ���óɹ���״̬�ظ�����
	 * @return
	 */
	public String setDomainJsonObjectWithOptionPlay(String method,String domainName,String[]keys,Object[]allvalues,int type,String setFailed,String setSuccess){
		String content="��������"; 
		try {
			int id=getJsonIDByMethod( method);
			setMyJsonHead(method);
			setDomainJsonObject( domainName,keys,allvalues);
			 setOptionsJsonArray( type,setFailed, setSuccess);
			 mainJSon.put("params", paramsJSon);
			 content=mainJSon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	/**
	 * ������״̬�ָ���DomainJson
	 * @param id
	 * @param method
	 * @param domainName ������
	 * @param keys �ؼ�������
	 * @param allvalues ����ؼ��ֶ�Ӧ��ֵ����
	
	 * @return
	 */
	public String setDomainJsonObjectNoOptionPlay(String method,String domainName,String[]keys,Object[]allvalues){
		String content="��������"; 
		try {
			int id=getJsonIDByMethod( method);
			setMyJsonHead(method);
			setDomainJsonObject( domainName,keys,allvalues);
			 mainJSon.put("params", paramsJSon);
			 content=mainJSon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	//-------------------------------�����ȡDomain�������Json---------------------------------------------
			/**
			 * ���������������ݵ� Json
			 * @param id 
			 * @param method
			 * @param categoryid 
			 * @param domainName
			 * @return
			 */
			public String setGetDomainJsonObject(String method,Object[] domainNames){
				String content="��������"; 
				
				try {
					int id=getJsonIDByMethod( method);
					keys = new String[] { "id", "method" };
					values = new Object[] { id, method };
					mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							this.keys, this.values));
					paramsJSon = JSonCreatTools.creatCommentJsonObject(null);  
					getDomainDataJSonArray = JSonCreatTools.createCommentJsonArray(domainNames);
					paramsJSon.put("data", getDomainDataJSonArray);
					
					 mainJSon.put("params", paramsJSon);
					 mainJSon.put("timeD", GetAndSetTime.setTime());
					 content=mainJSon.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content;
			}
	//-------------------------------����Http�����Json---------------------------------------------
			/**
			 * ����Http����� Json
			 * @param id 
			 * @param method
			 *
			 * @param domainName
			 *  @param categoryid 
			 * @return
			 */
			public String setHttpJsonObject(int id,String method,String donaminName,String categoryid){
				String content="��������"; 
				try {
					keys = new String[] { "id", "method" };
					values = new Object[] { id, method };
					mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							this.keys, this.values));
					paramsJSon = JSonCreatTools.creatCommentJsonObject(null);
					paramsJSon.put(donaminName, categoryid);
					
					 mainJSon.put("params", paramsJSon);
					 content=mainJSon.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content;
			}
			
			/**
			 * ����Http����� Json
			 * @param id 
			 * @param method
			 *
			 * @param domainName
			 *  @param categoryid 
			 * @return
			 */
			public String setHttpJsonArray(int id,String method,String domainName,String[] categoryid){
				String content="��������"; 
				try {
					keys = new String[] { "id", "method" };
					values = new Object[] { id, method };
					mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							this.keys, this.values));
					paramsJSon = JSonCreatTools.creatCommentJsonObject(null);
					getDomainDataJSonArray=JSonCreatTools.createCommentJsonArray(categoryid);
					paramsJSon.put(domainName, getDomainDataJSonArray);
					
					 mainJSon.put("params", paramsJSon);
					 content=mainJSon.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content;
			}
			
			/**
			 * params�����ŵ�ֵ������ΪObjectҲ����ΪArray
			 * 
			 * @param id
			 * @param method
			 * @param paramsNames  params�ĳ�Ա���ƣ���С��paramsValues�Ĵ�С��Ӧ
			 * @param paramsValues  paramsValuesΪparamsNames��Ӧ��ֵ�����������1��˵����Ҫ��ŵ�������
			 * @return
			 */
			public String setParamsAllHttpJson(int id,String method,String []paramsNames,ArrayList<String[]> paramsValues){
				String content="��������"; 
				try {
					keys = new String[] { "id", "method" };
					values = new Object[] { id, method };
					mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							this.keys, this.values));
					paramsJSon = JSonCreatTools.creatCommentJsonObject(null);
					for(int i=0;i<paramsNames.length;i++){
						if(paramsValues.get(i).length>1){
							getDomainDataJSonArray=JSonCreatTools.createCommentJsonArray(paramsValues.get(i));
							paramsJSon.put(paramsNames[i], getDomainDataJSonArray);
						}
						else{
							paramsJSon.put(paramsNames[i], paramsValues.get(i)[0]);
						}
					}
					 mainJSon.put("params", paramsJSon);
					 content=mainJSon.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content;
			}
			//-----------------------------------------------------------------------------------------------------------------
			
			
			/**
			 * "id": 4,
    "method": "deleteUserData",
    "params": {
        "data": {
            "wifi": [
                {
                    "ID": 1
                },
                {
                    "ID": 2
                }
            ]
        },

			 * ɾ��ͨ����
			 * @param id
			 * @param method
			 * @param paramsNames Ҫɾ��������
			 * @param paramsValues Ҫɾ����ID
			 * @return
			 */
			public String setDataDeleteJson(int id,String method,String []paramsNames,ArrayList<Object[]> paramsValues,int type,String setFailed,String setSuccess){
				String content="��������"; 
				try {
					keys = new String[] { "id", "method" };
					values = new Object[] { id, method };
					mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							this.keys, this.values));
					paramsJSon = JSonCreatTools.creatCommentJsonObject(null);
					
					
					JSONObject dataJson=JSonCreatTools.creatCommentJsonObject(null); 
					for(int i=0;i<paramsNames.length;i++){
						ArrayList<HashMap<String, Object[]>>list=new ArrayList<HashMap<String,Object[]>>();
						Object[]ids=paramsValues.get(i);
						String []ids_name=new String[ids.length];
						for(int k=0;k<ids_name.length;k++){
							ids_name[i]="ID";
						}
						HashMap<String, Object[]>map=new HashMap<String, Object[]>();
						map.put("key", ids_name);
						map.put("values", ids);
						list.add(map);
							getDomainDataJSonArray=JSonCreatTools.createJsonObjectInJsonArray(list);
							dataJson.put(paramsNames[i], getDomainDataJSonArray);
						
						
					}
					paramsJSon.put("data", dataJson);
					 setOptionsJsonArray( type,setFailed, setSuccess);
					 mainJSon.put("params", paramsJSon);
					 content=mainJSon.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content;
			}
			
			
			
			
			
			
			
			
			
			
	//----------------------------����MediaPlayJson-------------------------------------------------------
	
	public String setMediaJsonObject(String []queryTitle,int id,String method,String[]keys,ArrayList<Object[]>allvalues,boolean withMediaInfo){
		String content="��������"; 
		try {
			this.keys = new String[] { "id", "method" };
			values = new Object[] { id, method };
			mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
					this.keys, this.values));
			paramsJSon = JSonCreatTools.creatCommentJsonObject(null);
			if(withMediaInfo){
				JSONObject mediaInfoJson=new JSONObject();
				mediaInfoJson.put("query", queryTitle[0]);
				mediaInfoJson.put("reply", "��ʶ��ظ�");
				paramsJSon.putOpt("mediaInfo", mediaInfoJson);
			}
			
			
			
			mediaplayJSonArray=new JSONArray();
			setMediaPlayItemJsonArray(PlayItemEntity.NAME, keys, allvalues);
			this.paramsJSon.put(PlayEntity.DOMAINNAME,this.mediaplayJSonArray);
			 mainJSon.put("params", paramsJSon);
			 content=mainJSon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	
	
	/**
	 * ������Json���ݡ�get�Լ�set��
	 * @param id
	 * @param method
	 * @param volumeParams ��������ѯ��ʱ��null
	 * @return
	 */
	public String setVolumeJsonObject(int id,String method,Object volumeParams){
		String content="��������"; 
		try {
			keys = new String[] { "id", "method" };
			values = new Object[] { id, method };
			mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
					this.keys, this.values));
			
			if(volumeParams==null){
				mainJSon.put("params", null);
			}else{
				mainJSon.put("params", (Integer)volumeParams);
			}
			 
			 content=mainJSon.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	//--------------------------------------------------------------------------------------------
	/**
	 * ����ͷ
	 */
	public void setMyJsonHead(String method){
		int id=getJsonIDByMethod( method);
		keys = new String[] { "id", "method" };
		values = new Object[] { id, method };
		mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
				this.keys, this.values));
		paramsJSon = JSonCreatTools.creatCommentJsonObject(null);
		dataJSon = JSonCreatTools.creatCommentJsonObject(null);
	}
	
	/**
	 * ����DomianJson
	 * @param domainName
	 * @param keys
	 * @param allvalues
	 */
	public void setDomainJsonObject(String domainName,String[]keys,Object[]allvalues){
	
		this.domainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(keys, allvalues));
		try {
			this.dataJSon.put(domainName, this.domainJSon);
			this.paramsJSon.put("data",this.dataJSon);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ����MediaPlayJsonArray
	 * @param domainName
	 * @param keys
	 * @param allvalues
	 */
	public void setMediaPlayItemJsonArray(String domainName,String[]keys,ArrayList<Object[]>allvalues){
		try {
		this.list = new ArrayList<HashMap<String, Object[]>>();
		this.keys = keys;
		for(int i=0;i<allvalues.size();i++){
			this.values = allvalues.get(i);
			this.list.add(JSonCreatTools.getHashMapContainKeysAndValues(this.keys, this.values));
		
		}
		this.domainJSonArray = JSonCreatTools.createJsonObjectInJsonArray(this.list);
		
		JSONObject itemJson=new JSONObject();
		
		itemJson.put(domainName, this.domainJSonArray);
		mediaplayJSonArray.put(itemJson);
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * ����DomianJsonArray
	 * @param domainName
	 * @param keys
	 * @param allvalues
	 */
	public void setDomainJsonArray(String domainName,String[]keys,ArrayList<Object[]>allvalues){
		this.list = new ArrayList<HashMap<String, Object[]>>();
		this.keys = keys;
		for(int i=0;i<allvalues.size();i++){
			this.values = allvalues.get(i);
			this.list.add(JSonCreatTools.getHashMapContainKeysAndValues(this.keys, this.values));
		
		}
		this.domainJSonArray = JSonCreatTools.createJsonObjectInJsonArray(this.list);
		
		try {
			this.dataJSon.put(domainName, this.domainJSonArray);
			this.paramsJSon.put("data",this.dataJSon);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setOptionsJsonArray(int type,String setFailed,String setSuccess){
		try {
		list = new ArrayList<HashMap<String, Object[]>>();
		keys = new String[] { "status" };
		values = new Object[] { 0 };
		list.add(JSonCreatTools.getHashMapContainKeysAndValues(keys, values));
		keys = new String[] { "status" };
		values = new Object[] { 1 };
		list.add(JSonCreatTools.getHashMapContainKeysAndValues(keys, values));
		domainJSonArray = JSonCreatTools.createJsonObjectInJsonArray(list);

		
		//--------------��JSonArray�����ÿһ��Item�������Json--------------
		list = new ArrayList<HashMap<String, Object[]>>();
		keys = new String[] { "type","content" };
		
		list.add(JSonCreatTools.getHashMapContainKeysAndValues(keys, new Object[] { type,setFailed }));
		list.add(JSonCreatTools.getHashMapContainKeysAndValues(keys, new Object[] { type,setSuccess }));
		JSonCreatTools.createJsonObjectsInJSONArray(domainJSonArray, "playobject", list);

	
			paramsJSon.put("optionPlay", domainJSonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	//--------------------------------UDP���͵�����
	/**
	 * UDP ���͵�����
	 * @param keys
	 * @param allvalues
	 * @return
	 */
			public String setUDPSendJson1(String[]keys,Object[]allvalues){
				String content="��������"; 
				try {
					int id=SearchSendEntity.Search_ID;
					String[] temp_keys = new String[] { "id", "method" };
					Object[]temp_values = new Object[] { id, SearchSendEntity.Search_Method};
					mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							temp_keys, temp_values));
					paramsJSon=new JSONObject();
					domainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							keys, allvalues));
					paramsJSon.put("caller", domainJSon);
					paramsJSon.put("device", "lark");
					 mainJSon.put("params", paramsJSon);
					 content=mainJSon.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content;
			}
	
	//--------------------------------�������
			public String setHeartBeatJson1(String[]keys,Object[]allvalues){
				String content="��������"; 
				try {
					int id=ID_Manager.ID_HeartBeat;
					String[] temp_keys = new String[] { "id", "method" };
					Object[]temp_values = new Object[] { id, "setTimeout" };
					mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
							temp_keys, temp_values));
				JSONObject paramsJSon=new JSONObject();
				for(int i=0;i<keys.length;i++){
					paramsJSon.put(keys[i], allvalues[i]);
				}
					 mainJSon.put("params", paramsJSon);
					 content=mainJSon.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return content;
			}
	
}
