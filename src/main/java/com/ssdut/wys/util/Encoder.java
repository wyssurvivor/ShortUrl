package com.ssdut.wys.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    
    public String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md=MessageDigest.getInstance("MD5");
        return bytesToHexString(md.digest(str.getBytes("utf-8")));
    }
    
    public String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    public static void main(String [] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Encoder encoder=new Encoder();
        System.out.print(encoder.md5("http://iteye.blog.163.com/blog/static/1863080962012111223141936/"));
    }

}
