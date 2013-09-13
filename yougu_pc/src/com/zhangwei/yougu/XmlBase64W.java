package com.zhangwei.yougu;

import java.io.ByteArrayOutputStream;

import com.jhss.youguu.http.XmlBase64;
import com.zhangwei.yougu.androidconvert.Log;

public class XmlBase64W extends XmlBase64 {
	private static final String TAG = "XmlBase64W";

	public XmlBase64W() {
		super();
	}

	public static void decode(String str, ByteArrayOutputStream stream) {
		Log.e(TAG, "decode1 - In: str:" + str);
		XmlBase64.decode(str, stream);
	}

	public static byte[] decode(String str) {
		byte[] ret =  XmlBase64.decode(str);
		Log.e(TAG, "decode2 - in: " + str + "\nout:" + new String(ret));
		
		return ret;
	}

	public static String encode(byte[] byteArray) {
		String ret =  XmlBase64.encode(byteArray);
		Log.e(TAG, "encode1 - in: " + new String(byteArray) + ", out:" + ret);
		return ret;
	}

	public static StringBuffer encode(byte[] byteArray, int arg1, int arg2,
			StringBuffer sb) {
		StringBuffer ret = XmlBase64.encode(byteArray, arg1, arg2, sb);
		Log.e(TAG, "encode2 - in: " + new String(byteArray) +  ", arg1:" + arg1 + ", arg2:" + arg2 + ", out:" + ret.toString());
		
		return ret;
	}

	public static boolean needBase64(String str) {

		return XmlBase64.needBase64(str);
	}

	public static String utf8_decode(byte[] byteArray, int arg1, int arg2) {
		String ret =  XmlBase64.utf8_decode(byteArray, arg1, arg2);
		Log.e(TAG, "utf8_decode - in: " + new String(byteArray) +  ", arg1:" + arg1 + ", arg2:" + arg2 + ", out:" + ret);
		return ret;

	}

}
