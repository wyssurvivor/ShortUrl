package com.ssdut.wys.generator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdut.wys.util.Encoder;

public class MD5Generator implements Generator{
    static Logger log=LoggerFactory.getLogger(MD5Generator.class);
    private static MD5Generator gen=new MD5Generator();
    private MD5Generator(){}
    public static MD5Generator getInstance(){
        return gen;
    }
    String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,  
            "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,  
            "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,  
            "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,  
            "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,  
            "U" , "V" , "W" , "X" , "Y" , "Z"  
     }; 
    
    public String generate(String url){
        String[] result=getShortUrls(url);
        //todo:use tools like bloomfilter to optimize.
        return result[0];
    }
    
    private String[] getShortUrls(String url) {
        String md5edurl=getMD5Str(url);
        String []result=new String[4];
        for(int i=0;i<4;i++){
            String subStr=md5edurl.substring(i*8, (i+1)*8);
            long lHexLong=0x3FFFFFFF&Long.parseLong(subStr,16);
            String encodedChars="";
            for(int j=0;j<6;j++){
                long index=lHexLong&0x0000003D;
                encodedChars+=chars[(int)index];
                lHexLong=lHexLong>>5;
            }
            result[i]=encodedChars;
        }
        return result;
    }
    
    private String getMD5Str(String str){
        Encoder encoder=new Encoder();
        String md5edurl="";
        try{
            md5edurl=encoder.md5(str);
        }catch(NoSuchAlgorithmException e){
            log.error("NoSuchAlgorithmException {}",e);
        }catch(UnsupportedEncodingException e){
            log.error("unsupportedEncodingException {}",e);
        }
        return md5edurl;
    }
    
    public static void main(String[] args){
        MD5Generator gen=new MD5Generator();
        System.out.println(gen.generate("http://music.163.com/#/my/m/music/playlist?id=6441451"));
    }

}
