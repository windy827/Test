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
 * 创建和解析关于云宝的Json
 * @author 袁剑
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
		public static final String JSON_DELETEID="ID";//删除时的id对应的关键字
		public static final String JSON_RESULT="result";//云宝返回结果关键字
		public static final String JSON_ERROR="error";//云宝返回错误关键字
	}
	
	public static final String GET_USERDATA	="getUserData";//请求获取云宝用户数据 
	public static final String SET_USERDATA	="setUserData";//请求配置云宝用户数据
	public static final String INSERT_USERDATA="insertUserData";//添加云宝用户数据
	public static final String DELETE_USERDATA="deleteUserData";//删除云宝用户数据
	public static final String SET_USERDATA_WITHOUT_RETURN="setuserdata_WITHOUT_RETURN";//请求配置云宝用户数据,并且修改后不返回
	
	/**
	 * 根据name得到Json里面的Doule型数据
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
	 * 根据name得到Json里面的String型数据
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
	 * 根据name得到Json里面的boolean型数据
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
	 * 根据method的内容确定ID号
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
	 * setUserData对应的ID
	 */
	public static final int ID_setUserData=1;
	/**
	 * getUserData对应的ID
	 */
	public static final int ID_getUserData=2;
	
	/**
	 * insertUserData对应的ID
	 */
	public static final int ID_insertUserData=3;

	/**
	 * deleteUserData对应的ID
	 */
	public static final int ID_deleteUserData=4;
	
	
	//--------------------------------------------------------------------------------------------
	/**
	 * 包含状态恢复的Domain数组
	 * @param method
	 * @param domainName 域名称
	 * @param keys 关键字名称
	 * @param allvalues 多组关键字对应的值数组
	 * @param setFailed 设置失败的状态恢复内容
	 * @param setSuccess 设置成功的状态回复内容
	 * @return
	 */
	public String setDomainJsonArrayWithOptionPlay(String method,String domainName,String[]keys,ArrayList<Object[]>allvalues,int type,String setFailed,String setSuccess){
		String content="生成有误！"; 
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
	 * 不包含状态恢复的Domain数组
	 * @param method
	 * @param domainName 域名称
	 * @param keys 关键字名称
	 * @param allvalues 多组关键字对应的值数组
	
	 * @return
	 */
	public String setDomainJsonArrayNoOptionPlay(String method,String domainName,String[]keys,ArrayList<Object[]>allvalues){
		String content="生成有误！"; 
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
	 * 包含状态恢复的DomainJson
	 * @param method
	 * @param domainName 域名称
	 * @param keys 关键字名称
	 * @param allvalues 关键字对应的值数组
	 * @param setFailed 设置失败的状态恢复内容
	 * @param setSuccess 设置成功的状态回复内容
	 * @return
	 */
	public String setDomainJsonObjectWithOptionPlay(String method,String domainName,String[]keys,Object[]allvalues,int type,String setFailed,String setSuccess){
		String content="生成有误！"; 
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
	 * 不包含状态恢复的DomainJson
	 * @param id
	 * @param method
	 * @param domainName 域名称
	 * @param keys 关键字名称
	 * @param allvalues 多组关键字对应的值数组
	
	 * @return
	 */
	public String setDomainJsonObjectNoOptionPlay(String method,String domainName,String[]keys,Object[]allvalues){
		String content="生成有误！"; 
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
	//-------------------------------构造获取Domain的请求的Json---------------------------------------------
			/**
			 * 构造申请域名内容的 Json
			 * @param id 
			 * @param method
			 * @param categoryid 
			 * @param domainName
			 * @return
			 */
			public String setGetDomainJsonObject(String method,Object[] domainNames){
				String content="生成有误！"; 
				
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
	//-------------------------------构造Http请求的Json---------------------------------------------
			/**
			 * 构造Http请求的 Json
			 * @param id 
			 * @param method
			 *
			 * @param domainName
			 *  @param categoryid 
			 * @return
			 */
			public String setHttpJsonObject(int id,String method,String donaminName,String categoryid){
				String content="生成有误！"; 
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
			 * 构造Http请求的 Json
			 * @param id 
			 * @param method
			 *
			 * @param domainName
			 *  @param categoryid 
			 * @return
			 */
			public String setHttpJsonArray(int id,String method,String domainName,String[] categoryid){
				String content="生成有误！"; 
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
			 * params里面存放的值，可以为Object也可以为Array
			 * 
			 * @param id
			 * @param method
			 * @param paramsNames  params的成员名称，大小与paramsValues的大小对应
			 * @param paramsValues  paramsValues为paramsNames对应的值，若数组大于1则说明需要存放的是数组
			 * @return
			 */
			public String setParamsAllHttpJson(int id,String method,String []paramsNames,ArrayList<String[]> paramsValues){
				String content="生成有误！"; 
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

			 * 删除通用类
			 * @param id
			 * @param method
			 * @param paramsNames 要删除的域名
			 * @param paramsValues 要删除的ID
			 * @return
			 */
			public String setDataDeleteJson(int id,String method,String []paramsNames,ArrayList<Object[]> paramsValues,int type,String setFailed,String setSuccess){
				String content="生成有误！"; 
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
			
			
			
			
			
			
			
			
			
			
	//----------------------------构造MediaPlayJson-------------------------------------------------------
	
	public String setMediaJsonObject(String []queryTitle,int id,String method,String[]keys,ArrayList<Object[]>allvalues,boolean withMediaInfo){
		String content="生成有误！"; 
		try {
			this.keys = new String[] { "id", "method" };
			values = new Object[] { id, method };
			mainJSon = JSonCreatTools.creatCommentJsonObject(JSonCreatTools.getHashMapContainKeysAndValues(
					this.keys, this.values));
			paramsJSon = JSonCreatTools.creatCommentJsonObject(null);
			if(withMediaInfo){
				JSONObject mediaInfoJson=new JSONObject();
				mediaInfoJson.put("query", queryTitle[0]);
				mediaInfoJson.put("reply", "非识别回复");
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
	 * 音量的Json内容【get以及set】
	 * @param id
	 * @param method
	 * @param volumeParams 音量，查询的时候传null
	 * @return
	 */
	public String setVolumeJsonObject(int id,String method,Object volumeParams){
		String content="生成有误！"; 
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
	 * 设置头
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
	 * 设置DomianJson
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
	 * 设置MediaPlayJsonArray
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
	 * 设置DomianJsonArray
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

		
		//--------------往JSonArray里面的每一项Item里面添加Json--------------
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
	
	
	//--------------------------------UDP发送的内容
	/**
	 * UDP 发送的内容
	 * @param keys
	 * @param allvalues
	 * @return
	 */
			public String setUDPSendJson1(String[]keys,Object[]allvalues){
				String content="生成有误！"; 
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
	
	//--------------------------------心跳检测
			public String setHeartBeatJson1(String[]keys,Object[]allvalues){
				String content="生成有误！"; 
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
