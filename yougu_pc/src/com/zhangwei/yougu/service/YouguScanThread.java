package com.zhangwei.yougu.service;

import com.zhangwei.yougu.androidconvert.Log;
import com.zhangwei.yougu.api.API;
import com.zhangwei.yougu.pojo.Response;
import com.zhangwei.yougu.profile.AccountInfo;

public class YouguScanThread extends Thread {
	private static final String TAG = "YouguScanThread";
	public String  username = null;
	public String  passwd = null;
	public String  userid = null;// "538458";
	public String  sessionid = null; //"20130913114632538458";
	
	public final String imei = "862620027046913";
	public final String Product_ID = "403001006";

	
	public YouguScanThread(String username, String passwd){
		this.username = username;
		this.passwd = passwd;
	}
	
	public void run() {
		//step1: login:

		Response.RespLogin resp = API.Login(Product_ID, username, passwd, sessionid);
		if(resp!=null && "0000".equals(resp.status)){
			sessionid = resp.sessionid;
			userid = resp.userid;
			AccountInfo ai = AccountInfo.getInstance();
			if(!ai.isLogin()){
				ai.username = username;
				ai.passwd = passwd;
				ai.sessionid = sessionid;
				ai.userid = userid;
				
			}
			Log.i(TAG, "login ok!");
			
			Response.RespShowMyAttation resp_my_attation = API.ShowMyAttention(Product_ID, sessionid, userid);
			
			if(resp_my_attation!=null && "0000".equals(resp_my_attation.status)){
				Log.i(TAG, "ShowMyAttention ok!");
				ai.updateShowMyAttation(resp_my_attation);
			}else{
				Log.e(TAG, "ShowMyAttention fail!");
			}
			
			ai.persist();
		}else{
			Log.e(TAG, "login failed, exit");
			return;
		}
	}
}
