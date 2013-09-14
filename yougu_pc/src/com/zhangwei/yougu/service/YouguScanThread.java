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
import com.zhangwei.yougu.pojo.Response.RespShowMyMoney;
import com.zhangwei.yougu.profile.AccountInfo;
import com.zhangwei.yougu.windows.TipWindowHelper;

public class YouguScanThread extends Thread {
	private static final String TAG = "YouguScanThread";
	public String  my_username = null;
	public String  my_passwd = null;
	public String  my_userid = null;// "538458";
	public String  my_sessionid = null; //"20130913114632538458";
	
	public final String imei = "862620027046913";
	public final String Product_ID = "403001006";
	private boolean stop;

	
	public YouguScanThread(String username, String passwd){
		this.my_username = username;
		this.my_passwd = passwd;
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
		    resp = API.Login(Product_ID, my_username, my_passwd, my_sessionid);

		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(resp!=null && "0000".equals(resp.status)){
			my_sessionid = resp.sessionid;
			my_userid = resp.userid;
			if(!ai.isLogin()){
				ai.my_username = my_username;
				ai.my_passwd = my_passwd;
				ai.my_sessionid = my_sessionid;
				ai.my_userid = my_userid;
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
			resp_my_attation = API.ShowMyAttention(Product_ID, my_sessionid, my_userid);
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
				Response.RespGetAccount stock_acct = API.GetAllAccounts(Product_ID, attation.userid, my_userid, my_sessionid);
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
		
		//step4: get people's money 
		for(Entry<String, RespGetAccount>  item : ai.people_accounts.entrySet()){
			String people_userid = item.getKey();
			RespGetAccount stock_acct = item.getValue();
			try{
				if(stock_acct!=null && stock_acct.result!=null && stock_acct.result.length>0){
					StringBuilder sb = new StringBuilder();
					for(RespGetAccount_item stock_acct_item:stock_acct.result){
						RespShowMyMoney money = API.ShowMyMoney(Product_ID, my_sessionid, my_userid, people_userid, stock_acct_item.match_id);
					
						if("0000".equals(money.status)){
							Log.i(TAG, "RespShowMyMoney ok");
							if("1".equals(stock_acct_item.match_id)){
								sb.append(" 普:" + money.zyl);
							}else if("2".equals(stock_acct_item.match_id)){
								sb.append(" 中:" + money.zyl);
							}else if("3".equals(stock_acct_item.match_id)){
								sb.append(" 大:" + money.zyl);
							}
							
						}
						
					}
					
					ai.updatePeopleMoney(people_userid, sb.toString());
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//step5: find actions in loop 
		while(stop){
			for(Entry<String, RespGetAccount>  item : ai.people_accounts.entrySet()){
				String people_userid = item.getKey();
				RespGetAccount stock_acct = item.getValue();
				try{
					if(stock_acct!=null && stock_acct.result!=null && stock_acct.result.length>0){
						ArrayList<RespFindActionListByTimeVip_item> newPeopleActions = new ArrayList<RespFindActionListByTimeVip_item>();
						for(RespGetAccount_item stock_acct_item:stock_acct.result){
							Response.RespFindActionListByTimeVip action = API.FindActionListByTimeVip(Product_ID, my_userid, my_sessionid, people_userid, stock_acct_item.match_id);
							if("0000".equals(action.status)){
								ArrayList<RespFindActionListByTimeVip_item> newActions = ai.updatePeopleAction(people_userid + "_" + stock_acct_item.match_id, action);
								if(newActions!=null && newActions.size()>0){
									for(RespFindActionListByTimeVip_item  newActionItem : newActions){
										Log.e(TAG, "new actions:" + newActionItem.text);
									}
									
									newPeopleActions.addAll(newActions);
									
								}
							}
						}
						TipWindowHelper.getInstance().show(ai.getZYL(people_userid), newPeopleActions);
						
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
