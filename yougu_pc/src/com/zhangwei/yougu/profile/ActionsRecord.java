package com.zhangwei.yougu.profile;

import java.util.ArrayList;

import com.zhangwei.yougu.androidconvert.Log;
import com.zhangwei.yougu.pojo.Response.RespFindActionListByTimeVip_item;

public class ActionsRecord {
	private transient static final String TAG = "ActionsRecord";
	private transient int limit_size;
	
	private ArrayList<RespFindActionListByTimeVip_item> list;

	
	public ActionsRecord(){
		list = new ArrayList<RespFindActionListByTimeVip_item>();
		limit_size = 100;
	}
	
	/**
	 * 
	 * @return false if contains,  true if not contains
	 * */
	public boolean add(RespFindActionListByTimeVip_item item){
		boolean found = false;
		if(list.size()>0){
			for(RespFindActionListByTimeVip_item  a : list){
				if(a.id.equals(item.id)){
					found = true;
					break;
				}
			}
			
			if(!found){
				list.add(item); //not found
			}

		}else{
			list.add(item); //empty
		}
		
		//limit size
		if(list.size()>limit_size){
			list.remove(0);
		}
		
		//Log.i(TAG, "item:" + item.id + ", found:" + found);
		return found;
	}

}
