package com.ssdut.wys.client;

import java.lang.reflect.Proxy;

import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdut.wys.service.ShortUrl;

public class ClientFactory {
    private static Logger log=LoggerFactory.getLogger(ClientFactory.class);
    public static ShortUrl.Iface getClient(){
        ShortUrlClient client=new ShortUrlClient();
        ShortUrl.Iface proxyClient=(ShortUrl.Iface)Proxy.newProxyInstance(ShortUrl.Iface.class.getClassLoader(), new Class[]{ShortUrl.Iface.class}, new ClientHandler(client));
        return proxyClient;
    }
    
    public static void main(String[] args){
        ShortUrl.Iface client=ClientFactory.getClient();
        try {
            String shortUrl=client.generate("http://www.baidu.com");
            System.out.println("short url generated : "+shortUrl);
            String fullUrl=client.getFullUrl(shortUrl);
            System.out.println("full url stored : "+fullUrl);
        } catch (TException e) {
            log.error("{}",e);
        }
    }

}
