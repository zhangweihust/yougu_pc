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
	public String username;
	public String passwd;
	public String userid;
	public String sessionid;
	
	public ArrayList<RespShowMyAttation_item> my_attations;
	public HashMap<String, RespGetAccount> people_accounts;
	public HashMap<String, ActionsRecord> people_actions;
	
	
	
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
	}
	
	synchronized public boolean isLogin(){
		if(username==null || passwd==null || userid==null || sessionid==null){
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
	 * @return 返回不在缓存中的操作记录，也就是新操作记录
	 * */
	synchronized public ArrayList<RespFindActionListByTimeVip_item> updatePeopleAction(String key, RespFindActionListByTimeVip actions){
		ArrayList<RespFindActionListByTimeVip_item> ret = new ArrayList<RespFindActionListByTimeVip_item>();
		if(actions!=null && "0000".equals(actions.status) && actions.result!=null && actions.result.length>0){
			ActionsRecord ar = null;
			for(RespFindActionListByTimeVip_item action_item : actions.result){
				ar = people_actions.get(key);
				if(ar==null){
					ar = new ActionsRecord();
					people_actions.put(key, ar);
				}
				boolean flag = ar.add(action_item);
				if(!flag){
					ret.add(action_item);
				}
			}
			


		}

		
		return ret;
	}
	
	synchronized public void clear(){
		my_attations.clear();
		people_accounts.clear();
	}
	
	
	synchronized public void persist() {
		Log.e(TAG, "AccountInfo:  persist!");
		SDCardStorageManager.getInstance().putItem(null, AccountInfoKey, ins, AccountInfo.class);
	}
}
