package com.zhangwei.yougu.pojo;

public class Response {
	public static class RespQueryStockList{
		public String status; //"status":"0000";
		public String message; //"message":"成功",
		public int num; //"num":322,
		public RespQueryStockList_StockItem[] result;
	}
	
	class RespQueryStockList_StockItem{
		public double amountScale; //"amountScale":879.48,
		public String attribute; //"attribute":"",
		public String closeDate; //"closeDate":null,
		public String code; //"code":"11600869",
		public String code2; //"code2":"",
		public String firstType;// "firstType":"03",
		public String modifyDate; //"modifyDate":"20130913091044",
		public String name; //"name":"远东电缆",
		public String openDate; //"openDate":"20130913091044",
		public String pyjc;// "pyjc":"YDDL",
		public String secondType;//"secondType":"0"
	}
	
	public static class RespLogin{
		public String status; //"status":"0000",
		public String message;//"message":"登陆成功.",
		public String sessionid;//"sessionid":"20130913095904538458",
		public String userid; //"userid":"538458",
		public String nickname; //"nickname":"zweric"
	}
	
	public static class RespGetAccount{
		public String status;//"status":"0000",
		public String message;//"message":"OK",
		public RespGetAccount_item[] result;//"result":[{"account_id":"95126749104395000","init_fund":100000,"join_time":"2013-08-30 00:03:49","match_id":"1","match_name":"普通账户","u_id":"538458","u_nick":"zweric","vip_valid_days":0}]}
	}
	
	public class RespGetAccount_item{
		public String account_id; //"account_id":"95126749104395000",
		public long init_fund; //"init_fund":100000, 初始资本
		public String join_time; //"join_time":"2013-08-30 00:03:49",
		public String match_id;//"match_id":"1",
		public String match_name;//"match_name":"普通账户",
		public String u_id;//"u_id":"538458",
		public String u_nick; //"u_nick":"zweric",
		public int vip_valid_days;// "vip_valid_days":0
	}
	
	public static class RespShowMyMoney{
		public String status; //"status":"0000",
		public String message;//"message":"查询成功.",
		
		public String cgsz;//"cgsz":"111241.00", 持股市值
		public String fdyk;//"fdyk":"11844.67", 浮动盈亏
		public String zjye;//"zjye":"638.22", 资金余额
		public String zyl;//"zyl":"11.88%", 总盈利
		public String zzc;//"zzc":"111879.22" 总资产

		public int rank; //"rank":41668,
		public int resetTip;//"resetTip":0,
		public int vipTip;//"vipTip":0,
		public int vipValidDays; //"vipValidDays":0,

	}
	
	public static class RespShowMyAttation{
		public String  status; //"status":"0000",
		public String  message; //"message":"查询返回成功.",
		public RespShowMyAttation_item[] result;
	}
	
	public class RespShowMyAttation_item{
		public int fans; //"fans":0,
		public int holds;//"holds":0,
		public String nickname; //"nickname":"koreafinkl",
		public int plates; //"plates":0,
		public int trades;//"trades":0,
		public String userid;// "userid":"358553",
		public String zyl; //"zyl":"110.40%"
	}
	
	public static class RespShowMyStock{
		public String status; //"status":"0000",
		public String message; //"message":"持仓查询成功."
		public RespShowMyStock_item[] result;
	}
	
	public class RespShowMyStock_item{
		public String cbj; //"cbj":"8.84",  成本价
		public String closePrice;//"closePrice":"9.03", 收盘价
		public String djs;//"djs":"0", 冻结数
		public String dqcb;//"dqcb":"47740347.75", 当前成本
		public String gfye;//"gfye":"5397500", 股份余额
		public String kygf;//"kygf":"5397500", 可用股份
		public String markUp;//"markUp":"-5.65%", 今日涨幅
		public String stockCode;//"stockCode":"600708",
		public String stockName;//"stockName":"海博股份",
		public String yk;//"yk":"-1753645.28", 盈亏
		public String ykl;//"ykl":"-3.67%", 盈亏率
		public String zts;//"zts":"0", 在途数
		public String zxj;//"zxj":"8.52",最新价
		public String zxsz;//"zxsz":"45986702.47" 最新市值
	}
	
	public static class RespFindActionListByTimeVip{
		public String status; //"status":"0000",
		public String message; //"message":"成功",
		public int num;//"num":20,
		public RespFindActionListByTimeVip_item[] result;
	}
	
	public class RespFindActionListByTimeVip_item{
		public String aid; //"aid":"3",
		public String id; //"id":"5231657f90ee6039c11e1dfe",
		public String nickname; //"nickname":"leo",
		public String stockcode; //"stockcode":"",
		public String text; //"text":"买入,海博股份,600708,3549700,9.01,31982798.00",
		public long time; //"time":1378968958000,
		public int type; //"type":1买入 2卖出
		public String uid; //"uid":"3298"
	}

}
