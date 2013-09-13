package com.zhangwei.yougu.profile;

import com.zhangwei.yougu.storage.SDCardStorageManager;





public class AccountInfo {
	
	private static transient final String AccountInfoKey = "AccountInfoKey_";// 需要确保全局唯一性
	private static transient AccountInfo ins = null;
	private static transient final String TAG = "AccountInfo";
	
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
		
	}

}
