package com.zhangwei.yougu;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.util.ByteArrayBuffer;

import com.zhangwei.yougu.androidconvert.Log;

public class RequestHelper {

	private static final String TAG = "RequestHelper";
	private static RequestHelper ins = null;

	private RequestHelper() {

	}

	public static RequestHelper getInstance() {
		if (ins == null) {
			ins = new RequestHelper();
		}

		return ins;
	}

	//sessionid: 20130902160341538458
	//userid: 538458
	//ak: 403001006
	//ts: 1378113440801538458
	    //1378991963020
	public String Get(String host, String url, String sessionid, String userid, String ak, String did, String am) {
		Log.e(TAG, "Get url:" + url);
		HttpClient httpclient = new DefaultHttpClient();
		
		HttpGet httpget = new HttpGet("http://" + host + url);
		HttpResponse response;

		String ret = null;
		try {
			if(sessionid!=null){
				httpget.setHeader("sessionid", sessionid);
			}

			if(userid!=null){
				httpget.setHeader("userid", userid);
			}

			if(ak!=null){
				httpget.setHeader("ak", ak);
			}
			
			if(did!=null){
				httpget.setHeader("did", did);
			}
			
			if(am!=null){
				httpget.setHeader("am", am);
			}

			if(userid!=null){
				httpget.setHeader("ts", String.valueOf(System.currentTimeMillis()) + userid);
			}else{
				httpget.setHeader("ts", String.valueOf(System.currentTimeMillis()) + "538458");
			}

			
			
			httpget.setHeader("Host",  host);
			httpget.setHeader("Connection",  "Keep-Alive");
			httpget.setHeader("User-Agent",  "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");

			
			
			//httpclient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpRequestRetryHandler());
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				int len = 0;
				byte[] data = new byte[1024];

				ByteArrayBuffer ba = new ByteArrayBuffer(1024);
				while ((len = instream.read(data)) > 0) {
					ba.append(data, 0, len);
				}
				
				ret = new String(ba.toByteArray());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.e(TAG, ret);

		return ret;

	}

}
