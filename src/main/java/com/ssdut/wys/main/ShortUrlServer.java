package com.ssdut.wys.main;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdut.wys.service.ShortUrl;
import com.ssdut.wys.service.impl.ShortUrlImpl;

public class ShortUrlServer {
    private static Logger log=LoggerFactory.getLogger(ShortUrlServer.class);
    public static void main(String[] args){
    	ShortUrl.Processor processor=new ShortUrl.Processor(new ShortUrlImpl());
        simple(processor);
    }
    
    public static void simple(ShortUrl.Processor processor) {
    	try{
    		TServerTransport serverTransport=new TServerSocket(7911);
//    		TServer server=new TSimpleServer(new Args(serverTransport).processor(processor));
    		TServer server=new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
    		System.out.println("start simple server on port 7911");
    		server.serve();
    	}catch(Exception e){
    		log.error("{}",e);
    	}
    }
    public static void secure(ShortUrl.Processor processor){
    	try{
    		//todo:add a secure server   
    		//https://git1-us-west.apache.org/repos/asf?p=thrift.git;a=blob;f=tutorial/java/src/JavaServer.java;hb=HEAD
    	}catch(Exception e){
    		
    	}
    }
}
