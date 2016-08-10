package com.wq.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String encrypt(String str)throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] buf = md.digest(str.getBytes());
		//将字节数组转换成字符串
		BASE64Encoder encoder = new BASE64Encoder();
		String str2 = encoder.encode(buf);
		return str2;
	}
	public static void main(String[] args) {
		try {
			MD5Util.encrypt("love");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
