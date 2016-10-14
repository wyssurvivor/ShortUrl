package com.ssdut.wys.service.impl;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdut.wys.db.Persistency;
import com.ssdut.wys.db.RedisDB;
import com.ssdut.wys.generator.Generator;
import com.ssdut.wys.generator.factory.GenFactory;
import com.ssdut.wys.generator.factory.MD5GenFactory;
import com.ssdut.wys.service.*;
public class ShortUrlImpl implements ShortUrl.Iface{
    private static GenFactory genFactory; 
    private static Generator gen;
    private Logger log=LoggerFactory.getLogger(ShortUrlImpl.class);
    private static Persistency storage=new Persistency();
    
    static {
        genFactory=new MD5GenFactory();
        gen=genFactory.getGenerator();
    }
    
    public String generate(String fullUrl) throws TException {
        String shortUrl=gen.generate(fullUrl);
        log.debug("original url:{},shortened str:{}",fullUrl,shortUrl);
        if(!storage.set(shortUrl, fullUrl)){
        	return "";//thrift can not return null
        }
        return shortUrl;
    }

    public String getFullUrl(String shortUrl) throws TException {
        String fullUrl=storage.getByShort(shortUrl);
        if(fullUrl==null){
        	return "";//thrift can not return null
        }
        return fullUrl;
    }

}
