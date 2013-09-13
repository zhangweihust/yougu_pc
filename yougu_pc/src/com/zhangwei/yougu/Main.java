package com.zhangwei.yougu;

import com.zhangwei.yougu.profile.AccountInfo;
import com.zhangwei.yougu.service.YouguScanThread;

public class Main {
	public static void main(String args[]){

		
		YouguScanThread t = new YouguScanThread("zweric", "654321");
		t.start();
	}
}
