package com.zhangwei.yougu.profile;


import java.util.ArrayList;
import java.util.HashMap;

import com.zhangwei.yougu.androidconvert.Log;
import com.zhangwei.yougu.pojo.Response;
import com.zhangwei.yougu.pojo.Response.RespFindActionListByTimeVip;
import com.zhangwei.yougu.pojo.Response.RespFindActionListByTimeVip_item;
import com.zhangwei.yougu.pojo.Response.RespGetAccount;
import com.zhangwei.yougu.pojo.Response.RespGetAccount_item;
import com.zhangwei.yougu.pojo.Response.RespShowMyAttation_item;
import com.zhangwei.yougu.storage.SDCardStorageManager;





public class AccountInfo {
	/**************************************************************/
	private static transient final String AccountInfoKey = "AccountInfoKey.txt";// 需要确保全局唯一性
	private static transient AccountInfo ins = null;
	private static transient final String TAG = "AccountInfo";
	/**************************************************************/
	public String my_username;
	public String my_passwd;
	public String my_userid;
	public String my_sessionid;
	
	public ArrayList<RespShowMyAttation_item> my_attations;
	public HashMap<String, RespGetAccount> people_accounts;
	public HashMap<String, ActionsRecord> people_actions;
	public HashMap<String, String> people_money;
	
	
	
	public static AccountInfo getInstance(){
		if(ins==null){
			AccountInfo a = (AccountInfo) SDCardStorageManager.getInstance().getItem(null, 	AccountInfoKey , AccountInfo.class);
			if(a!=null){
				ins = a;
			}else{
				ins = new AccountInfo();
			}

		}
		
		return ins;
	}
	
	private AccountInfo(){
		my_attations = new ArrayList<RespShowMyAttation_item>();
		people_accounts = new HashMap<String, RespGetAccount>();
		people_actions = new HashMap<String, ActionsRecord>();
		people_money = new HashMap<String, String>();
	}
	
	synchronized public boolean isLogin(){
		if(my_username==null || my_passwd==null || my_userid==null || my_sessionid==null){
			return false;
		}else{
			return true;
		}
	}
	
	synchronized public void updateShowMyAttation(Response.RespShowMyAttation resp){
		if(resp!=null && resp.result!=null && resp.result.length>0){
			for(RespShowMyAttation_item item : resp.result){
				my_attations.add(item);
			}
		}
	}
	
	synchronized public void updatePeopleAccount(String target_userid, RespGetAccount account){
		if(target_userid!=null && account!=null && "0000".equals(account.status)){
			people_accounts.put(target_userid, account);
		}
	}
	
	/**
	 *  记录个人的几个帐户盈利情况
	 * */
	synchronized public void updatePeopleMoney(String people_userid, String zyl_text) {
		// TODO Auto-generated method stub
		people_money.put(people_userid, zyl_text);
	}
	
	/**
	 * @return 返回不在缓存中的操作记录，也就是新操作记录
	 * */
	synchronized public ArrayList<RespFindActionListByTimeVip_item> updatePeopleAction(String key, RespFindActionListByTimeVip actions){
		ArrayList<RespFindActionListByTimeVip_item> ret = new ArrayList<RespFindActionListByTimeVip_item>();
		boolean hasNew = false;
		if(actions!=null && "0000".equals(actions.status) && actions.result!=null && actions.result.length>0){
			ActionsRecord ar = null;
			if(!people_actions.containsKey(key)){
				ar = new ActionsRecord();
			}else{
				ar = people_actions.get(key);
			}

			for(RespFindActionListByTimeVip_item action_item : actions.result){

				boolean flag = ar.add(action_item);
				ret.add(action_item);
				if(!flag){
					hasNew = true;
					//ret.add(action_item);
				}
			}
			people_actions.put(key, ar);


		}

		if(hasNew){
			return ret;
		}else{
			return null;
		}

	}
	
	synchronized public String getZYL(String people_userid){
		return people_money.get(people_userid);
	}
	
	synchronized public void clear(){
		my_attations.clear();
		people_accounts.clear();
		//people_actions.clear();
		people_money.clear();
	}
	
	
	synchronized public void persist() {
		Log.e(TAG, "AccountInfo:  persist!");
		SDCardStorageManager.getInstance().putItem(null, AccountInfoKey, ins, AccountInfo.class);
	}

	public void logout() {
		// TODO Auto-generated method stub
		my_sessionid = null;
	}


}
