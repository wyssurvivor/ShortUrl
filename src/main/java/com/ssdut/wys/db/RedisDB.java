package com.ssdut.wys.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssdut.wys.generator.Generator;
import com.ssdut.wys.generator.factory.MD5GenFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDB implements Database{
	
	private static JedisPool jPool=new JedisPool(new JedisPoolConfig(),"localhost");
	private static Logger log=LoggerFactory.getLogger(RedisDB.class);
	private static String KEY_PREFIX="shorturl:key:";
	
	private Jedis getRedisClient() throws Exception{
		Jedis redisClient=null;
		try{
			redisClient=jPool.getResource();
		}catch(Exception e){
			log.error("{}",e);
			throw e;
		}
		return redisClient;
	}
	
	private void closeRedisClient(Jedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
	
	public boolean set(String shortUrl, String fullUrl) {
		Jedis client=null;
		try{
			client=getRedisClient();
			if(client==null){
				log.error("create redis client failed");
				return false;
			}
			String key=getRedisKey(shortUrl);
			client.set(key, fullUrl);
			return true;
		}catch(Exception e){
			log.error("{}",e);
			return false;
		}finally{
			closeRedisClient(client);
		}
	}

	public String getByShort(String shortUrl) {
		Jedis client=null;
		try{
			client=getRedisClient();
			if(client==null){
				log.error("create redis client failed");
				return null;
			}
			String key=getRedisKey(shortUrl);
			String fullUrl=client.get(key);
			return fullUrl;
		}catch(Exception e){
			log.error("{}",e);
			return null;
		}
	}

	public String getByFull(String fullUrl) {
		Jedis client=null;
		try{
			client=getRedisClient();
			Generator gen=new MD5GenFactory().getGenerator();
			String shortUrl=gen.generate(fullUrl);
			if(client.exists(this.getRedisKey(shortUrl))){
				return shortUrl;
			}else{
				return null;
			}
		}catch(Exception e){
			log.error("{}",e);
			return null;
		}
	}
	
	private String getRedisKey(String shortStr){
		return KEY_PREFIX+shortStr;
	}
	
	public static void main(String[] args){
		RedisDB redis=new RedisDB();
		redis.set("uMBrU3", "http://www.baidu.com");
		System.out.println(redis.getByShort("uMBrU3"));
	}
	
}
