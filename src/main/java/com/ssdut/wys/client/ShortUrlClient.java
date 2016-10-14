package com.ssdut.wys.client;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdut.wys.service.ShortUrl;

public class ShortUrlClient {
    private int port=7911;
    private String address="localhost";
    private static Logger log=LoggerFactory.getLogger(ShortUrlClient.class);
    
    public ShortUrlClient(){}
    public ShortUrlClient(String address,int port){
        this.address=address;
        this.port=port;
    }
    
    public ShortUrl.Client getClient(){
        try{
            TTransport transport=new TSocket(address,port);
            transport.open();
            TProtocol protocol=new TBinaryProtocol(transport);
            ShortUrl.Client client=new ShortUrl.Client(protocol);
            return client;
        }catch(Exception e){
            log.error("{}",e);
            return null;
        }
    }
    
    public static void main(String[] args){
        
    }
}
