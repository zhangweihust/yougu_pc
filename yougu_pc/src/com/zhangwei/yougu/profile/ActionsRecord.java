package com.zhangwei.yougu.profile;

import java.util.ArrayList;

import com.zhangwei.yougu.pojo.Response.RespFindActionListByTimeVip_item;

public class ActionsRecord {
	private ArrayList<RespFindActionListByTimeVip_item> list;
	private int limit_size;
	
	public ActionsRecord(){
		list = new ArrayList<RespFindActionListByTimeVip_item>();
		limit_size = 20;
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
		
		return found;
	}

}
