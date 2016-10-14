package com.ssdut.wys.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.thrift.TServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdut.wys.service.ShortUrl;

public class ClientHandler implements InvocationHandler{
    private ShortUrlClient client;
    private static Logger log=LoggerFactory.getLogger(ClientHandler.class);
    public ClientHandler(ShortUrlClient client){
        this.client=client;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ShortUrl.Client serviceClient=client.getClient();
        Object obj=method.invoke(serviceClient,args);
        return obj;
    }

}
