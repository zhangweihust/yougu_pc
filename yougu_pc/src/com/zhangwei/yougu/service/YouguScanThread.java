package com.zhangwei.yougu.service;

import java.util.ArrayList;
import java.util.Map.Entry;

import com.zhangwei.yougu.androidconvert.Log;
import com.zhangwei.yougu.api.API;
import com.zhangwei.yougu.pojo.Response;
import com.zhangwei.yougu.pojo.Response.RespFindActionListByTimeVip_item;
import com.zhangwei.yougu.pojo.Response.RespGetAccount;
import com.zhangwei.yougu.pojo.Response.RespGetAccount_item;
import com.zhangwei.yougu.pojo.Response.RespShowMyAttation_item;
import com.zhangwei.yougu.profile.AccountInfo;

public class YouguScanThread extends Thread {
	private static final String TAG = "YouguScanThread";
	public String  username = null;
	public String  passwd = null;
	public String  userid = null;// "538458";
	public String  sessionid = null; //"20130913114632538458";
	
	public final String imei = "862620027046913";
	public final String Product_ID = "403001006";
	private boolean stop;

	
	public YouguScanThread(String username, String passwd){
		this.username = username;
		this.passwd = passwd;
		this.stop = true;
	}
	
	public void stopThread(){
		stop = false;
		this.interrupt();
	}
	
	public void run() {
		AccountInfo ai = AccountInfo.getInstance();
		
		//step1: login:
		Response.RespLogin resp = null;
		try{
		    resp = API.Login(Product_ID, username, passwd, sessionid);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(resp!=null && "0000".equals(resp.status)){
			sessionid = resp.sessionid;
			userid = resp.userid;
			if(!ai.isLogin()){
				ai.username = username;
				ai.passwd = passwd;
				ai.sessionid = sessionid;
				ai.userid = userid;
			}
			Log.i(TAG, "login ok!");
		}else{
			Log.e(TAG, "login failed, exit");
			return;
		}
		
		//reset accountinfo
		ai.clear();
		
		//step2: get my attation people
		Response.RespShowMyAttation resp_my_attation = null;
		try{
			resp_my_attation = API.ShowMyAttention(Product_ID, sessionid, userid);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(resp_my_attation!=null && "0000".equals(resp_my_attation.status)){
			Log.i(TAG, "ShowMyAttention ok!");
			ai.updateShowMyAttation(resp_my_attation);
		}else{
			Log.e(TAG, "ShowMyAttention fail!");
			return;
		}
		
		//step3: get the people's account info
		try{
			for(RespShowMyAttation_item attation : ai.my_attations){
				Response.RespGetAccount stock_acct = API.GetAllAccounts(Product_ID, attation.userid, userid, sessionid);
				if("0000".equals(stock_acct.status)){
					Log.i(TAG, "GetAllAccounts ok :" + attation.nickname);
					ai.updatePeopleAccount(attation.userid, stock_acct);

				}else{
					Log.e(TAG, "GetAllAccounts fail :" + attation.nickname);
				}			
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		
		//step4: find actions in loop 
		
		while(stop){
			for(Entry<String, RespGetAccount>  item : ai.people_accounts.entrySet()){
				String people_userid = item.getKey();
				RespGetAccount stock_acct = item.getValue();
				try{
					if(stock_acct!=null && stock_acct.result!=null && stock_acct.result.length>0){
						for(RespGetAccount_item stock_acct_item:stock_acct.result){
							Response.RespFindActionListByTimeVip action = API.FindActionListByTimeVip(Product_ID, userid, sessionid, people_userid, stock_acct_item.match_id);
							if("0000".equals(action.status)){
								ArrayList<RespFindActionListByTimeVip_item> newActions = ai.updatePeopleAction(userid + "_" + stock_acct_item.match_id, action);
								if(newActions!=null && newActions.size()>0){
									for(RespFindActionListByTimeVip_item  newActionItem : newActions){
										Log.e(TAG, "new actions:" + newActionItem.text);
									}
									
								}
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}

			}
			
			ai.persist();
			
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		

	}
}
