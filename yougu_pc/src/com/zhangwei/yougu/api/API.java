package com.zhangwei.yougu.api;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.zhangwei.yougu.androidconvert.Log;
import com.zhangwei.yougu.pojo.Response;

public class API {
/*	public static String sessionid = "20130913114632538458";
	public static String userid = "538458";
	public static String imei = "862620027046913";*/
	
/*	public static void main(String args[]){
		Gson gson = new Gson();
//~1NDAzMDAxMDA2/~9Fb5pFr5xFMBqFM9pF8ZtFrZsGMZ/~1MzI5OA/~4Kd
		XmlBase64W.decode("~4Kg_uKw_1KRGvKRCuKBdyKwdxLRd");
		XmlBase64W.decode("~0IOHyI_Py");

		//long l = 1262727415000L;
		long l = 946656000000L;
 *               862620027046913
		//Date d = Date.valueOf("2000-1-1");
		//Log.e("TAG", "" + d.getTime());
		Log.e("TAG", new Date(l).toLocaleString());
		//XmlBase64W.decode(testDoUpdate());
		//testQueryEvents();
		    //XmlBase64W.decode(testQueryStockList());
		    //XmlBase64W.decode(testDoUserBack());

		String ret = new String(XmlBase64W.decode(testLogin_no_sessionid()));
		Response.RespLogin respLogin = gson.fromJson(ret, Response.RespLogin.class);
		if(respLogin!=null && "0000".equals(respLogin.status)){
			API.sessionid = respLogin.sessionid;
			API.userid = respLogin.userid;
		}
		 XmlBase64W.decode(testGetAllAccounts("3298"));
		//XmlBase64W.decode(testShowMyMoney());
		//XmlBase64W.decode(testDoquery());
		//XmlBase64W.decode(testShowMyAttention());
		//XmlBase64W.decode(testFindActionListByTimeVip("3298"));
		//XmlBase64W.decode(testShowMyStock("3298", "3"));
		//XmlBase64W.decode(testShowMyStock("538458", "1"));
	}*/
	
	
	/**
	 * GET /youguu/events/queryevents/<403001006> HTTP/1.1
	 * sessionid: 
	 * ak: 403001006
	 * ts: 1378991954701
	 * Host: mncg.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * 
	 * 
	 * resp:
	 * HTTP/1.1 200 OK
	 * Server: nginx/1.4.2
	 * Date: Thu, 12 Sep 2013 13:16:37 GMT
	 * Content-Type: text/html;charset=UTF-8
	 * Content-Length: 140
	 * Connection: keep-alive
	 * Pragma: No-Cache
	 * Cache-Control: No-Cache
	 * Expires: Thu, 01 Jan 1970 00:00:00 GMT
	 * {"status":"0000","message":"ok","result":[{"id":1,"name":"..............................","startdate":"2013-08-01","enddate":"2013-10-31"}]}*/
	public static void QueryEvents(String Product_ID) throws Exception{
		String url_prefix = "/youguu/events/queryevents/";	
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		String url = sb.toString();
		
		RequestHelper.getInstance().Get("mncg.youguu.com", url, "", null, Product_ID, null, null);
		//RequestHelper.getInstance().Get(url, "20130902160341538458", null, Product_ID);
	}
	
	/**
	 * GET /jhss/member/doupdate/~2E63pD63nD631 HTTP/1.1
	 * sessionid: 20130913105805538458
	 * userid: 538458
	 * ak: 403001006
	 * did: 862620027046913
	 * am: WIFI
	 * ts: 1379041497577538458
	 * Host: user.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * */
	public static String DoUpdate(String userid, String sessionid, String imei) throws Exception{
		String url_prefix = "/jhss/member/doupdate/";
		String Product_ID = "403001006";
	
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		String url = sb.toString();
		
		return RequestHelper.getInstance().Get("user.youguu.com", url, sessionid, userid, Product_ID, imei, "WIFI");
		//RequestHelper.getInstance().Get(url, "20130902160341538458", null, Product_ID);
	}
	
	
	/** querystocklist
	 * GET /youguu/quote/querystocklist/<403001006>/<>/<1372727415000>/<01,02,03,05> HTTP/1.1
	 * sessionid: 
	 * ak: 403001006
	 * Host: mncg.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * 
	 * resp:
	 * 太大，需要解码
	 * */
	public static String QueryStockList(String Product_ID, String date_str) throws Exception{
		//2013-7-2 9:10:15   ---  1372727415000
		String url_prefix = "/youguu/quote/querystocklist/";
		//String date = "1362727415000"; //2013-7-2 9:10:15
		//String date = "946656000000"; //2000-1-1
		String type = "01,02,03,05";
	
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(date_str.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(type.getBytes()));
		
		String url = sb.toString();
		
		return RequestHelper.getInstance().Get("mncg.youguu.com", url, "", null, Product_ID, null, null);
	}
	
	/**
	 * douserback
	 * GET /jhss/member/douserback/<403001006>/<862620027046913> HTTP/1.1
	 * sessionid: 
	 * ak: 403001006
	 * ts: 1378991963020
	 * Host: user.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * */
	public static String DoUserBack(String Product_ID, String imei) throws Exception{
		String url_prefix = "/jhss/member/douserback/";
/*		String Product_ID = "403001006";
		String imei = "862620027046913"; */
	
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(imei.getBytes()));
		
		String url = sb.toString();
		
		return RequestHelper.getInstance().Get("user.youguu.com", url, "", null, Product_ID, null, null);
		/**
		 * {"status":"0000","message":"查询成功.","result":[{"nickName":"zweric","userID":"538458","userName":"zweric"}]}*/
	}
	
	/**
	 * dologonnew
	 * GET /jhss/member/dologonnew/<403001006>/<zweric>/<123456> HTTP/1.1
	 * sessionid: 
	 * ak: 403001006
	 * ts: 1378991963072
	 * Host: user.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * 
	 * resp:
	 * HTTP/1.1 200 OK
	 * Server: nginx/1.4.2
	 * Date: Thu, 12 Sep 2013 13:16:57 GMT
	 * Content-Type: text/html;charset=UTF8
	 * Content-Length: 157
	 * Connection: keep-alive
	 * Pragma: No-Cache
	 * Cache-Control: No-Cache
	 * Expires: Thu, 01 Jan 1970 00:00:00 GMT
	 * ~9XqCrW-_sWQFaHaBoF85oBaoaUPOrVu_fSLByBmXSm1eS4mTBcHPDfqwaE7CrSQGrTP0mTPJaHaBqF89rF8cpFbBpFMRtGbNrH8JtH7BkBfOrSQChS7ByBbNrH8JtH7BkBexhRulmRPtdBbgaXfWdVedbBfs
	 * */
	public static Response.RespLogin Login(String Product_ID, String username, String passwd, String sessionid) throws Exception{
		String url_prefix = "/jhss/member/dologonnew/";
/*		String Product_ID = "403001006";
		String user = "zweric"; 
		String passwd = "654321";*/
	
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(username.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(passwd.getBytes()));
		
		String url = sb.toString();
		
		byte[] ret =  XmlBase64W.decode(RequestHelper.getInstance().Get("user.youguu.com", url, sessionid, null, Product_ID, null, null));

		Gson gson = new Gson();
		return gson.fromJson(new String(ret), Response.RespLogin.class);
	}
	
	/**
	 * GET /youguu/simtrade/getAllAccounts/403001006/538458 HTTP/1.1
	 * Host: mncg.youguu.com
	 * Connection: Keep-Alive
	 * */
	public static Response.RespGetAccount GetAllAccounts(String Product_ID, String target_userid, String my_userid, String sessionid) throws Exception{
		String url_prefix = "/youguu/simtrade/getAllAccounts/";
/*		String Product_ID = "403001006";*/
	
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(Product_ID);
		sb.append("/");
		sb.append(target_userid);
		
		String url = sb.toString();
		
		byte[] ret = XmlBase64W.decode(RequestHelper.getInstance().Get("mncg.youguu.com", url, sessionid, my_userid, Product_ID, null, null));
		/**
		 * {"status":"0000","message":"OK","result":[{"account_id":"95126749104395000","init_fund":100000,"join_time":"2013-08-30 00:03:49","match_id":"1","match_name":"普通账户","u_id":"538458","u_nick":"zweric","vip_valid_days":0}]}
*/
		
		Gson gson = new Gson();
		return gson.fromJson(new String(ret), Response.RespGetAccount.class);
	}
	
	/**
	 * GET /youguu/simtrade/showmymoney/<403001006>/<2013 09 12 21 11 08 538458>/<538458>/<1> HTTP/1.1
	 * sessionid: 20130912211108538458
	 * userid: 538458
	 * ak: 403001006
	 * ts: 1378991626031538458
	 * Host: mncg.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * */
	public static String ShowMyMoney(String Product_ID, String sessionid, String my_userid, String target_userid) throws Exception{
		String url_prefix = "/youguu/simtrade/showmymoney/";
		/*String Product_ID = "403001006";*/
/*		SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMddHHmmss");
		String a1 = dateformat.format(new Date(System.currentTimeMillis()));
		String arg2 = a1 + my_userid;*/
	
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(sessionid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(target_userid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("1".getBytes()));
		
		String url = sb.toString();
		
		return RequestHelper.getInstance().Get("mncg.youguu.com", url, sessionid, my_userid, Product_ID, null, null);
		/**out:{"status":"0101","message":"你的账户已在别处登录，若非本人操作，请及时修改密码"}*/
		/**{"cgsz":"111241.00","fdyk":"11844.67","message":"查询成功.","rank":41668,"resetTip":0,"status":"0000","vipTip":0,"vipValidDays":0,"zjye":"638.22","zyl":"11.88%","zzc":"111879.22"}*/
	}
	
	/**
	 * GET /youguu/simtrade/doquery/<403001006>/<20130912211108538458>/<538458>/<1>/<3>/<1>/<30>/<0000-00-00>/<0000-00-00> HTTP/1.1
	 * sessionid: 20130912211108538458
	 * userid: 538458
	 * ak: 403001006
	 * ts: 1378991626031538458
	 * Host: mncg.youguu.com
	 * */
	public static String Doquery(String Product_ID, String sessionid, String my_userid, String target_userid) throws Exception{
		String url_prefix = "/youguu/simtrade/doquery/";
		String userid = "538458"; 

		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(sessionid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(target_userid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("1".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("3".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("1".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("30".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("0000-00-00".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("0000-00-00".getBytes()));
		
		String url = sb.toString();
		
		return RequestHelper.getInstance().Get("mncg.youguu.com", url, sessionid, userid, Product_ID, null, null);
		/**out:{"status":"0101","message":"你的账户已在别处登录，若非本人操作，请及时修改密码"}*/
		/**{"cgsz":"111241.00","fdyk":"11844.67","message":"查询成功.","rank":41668,"resetTip":0,"status":"0000","vipTip":0,"vipValidDays":0,"zjye":"638.22","zyl":"11.88%","zzc":"111879.22"}*/
	}
	
	/**
	 * GET /youguu/attention/showmyattention/<403001006>/<20130912211108538458>/<538458> HTTP/1.1
	 * sessionid: 20130912211108538458
	 * userid: 538458
	 * ak: 403001006
	 * ts: 1378991642798538458
	 * Host: mncg.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1*/
	public static Response.RespShowMyAttation ShowMyAttention(String Product_ID , String sessionid, String my_userid) throws Exception{
		String url_prefix = "/youguu/attention/showmyattention/";

		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(sessionid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(my_userid.getBytes()));
		
		String url = sb.toString();
		
		byte[] ret = XmlBase64W.decode(RequestHelper.getInstance().Get("mncg.youguu.com", url, sessionid, my_userid, Product_ID, null, null));
		Gson gson = new Gson();
		return gson.fromJson(new String(ret), Response.RespShowMyAttation.class);
	}
	
	
	
	/**
	 * GET /youguu/simtrade/showmystock/<403001006>/<20130912211108538458>/<3298>/<2> HTTP/1.1
	 * sessionid: 20130912211108538458
	 * userid: 538458
	 * ak: 403001006
	 * ts: 1378991657037538458
	 * Host: mncg.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * */
	public static Response.RespShowMyStock ShowMyStock(String Product_ID, String my_userid, String sessionid, String target_userid, String type) throws Exception{
		String url_prefix = "/youguu/simtrade/showmystock/";

		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode(Product_ID.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(sessionid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(target_userid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(type.getBytes()));
		
		String url = sb.toString();
		
		byte[] ret =  XmlBase64W.decode(RequestHelper.getInstance().Get("mncg.youguu.com", url, sessionid, my_userid, Product_ID, null, null));
//{"status":"0000","message":"持仓查询成功.","result":[{"cbj":"8.84","closePrice":"9.03","djs":"0","dqcb":"47740347.75","gfye":"5397500","kygf":"5397500","markUp":"-5.65%","stockCode":"600708","stockName":"海博股份","yk":"-1753645.28","ykl":"-3.67%","zts":"0","zxj":"8.52","zxsz":"45986702.47"}]}
		Gson gson = new Gson();
		return gson.fromJson(new String(ret), Response.RespShowMyStock.class);
	}
	
	/**
	 * GET /youguu/attention/findactionlistbytimevip/<>/<>/<3298>/<3>/<-1>/<1>/<20>/<1,2> HTTP/1.1
	 * sessionid: 20130912211108538458
	 * userid: 538458
	 * ak: 403001006
	 * ts: 1378991685688538458
	 * Host: mncg.youguu.com
	 * Connection: Keep-Alive
	 * User-Agent: Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1
	 * */
	public static Response.RespFindActionListByTimeVip FindActionListByTimeVip(String Product_ID, String my_userid, String sessionid, String target_userid, String type) throws Exception{
		String url_prefix = "/youguu/attention/findactionlistbytimevip/";
		StringBuffer sb = new StringBuffer();
		sb.append(url_prefix);
		sb.append(XmlBase64W.encode("".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(target_userid.getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode(type.getBytes())); //1,2,3
		sb.append("/");
		sb.append(XmlBase64W.encode("-1".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("1".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("20".getBytes()));
		sb.append("/");
		sb.append(XmlBase64W.encode("1,2".getBytes()));
		
		String url = sb.toString();
		
		byte[] ret = XmlBase64W.decode(RequestHelper.getInstance().Get("mncg.youguu.com", url, sessionid, my_userid, Product_ID, null, null));
		
		Gson gson = new Gson();
		return gson.fromJson(new String(ret), Response.RespFindActionListByTimeVip.class);
	}
}
