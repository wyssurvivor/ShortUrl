package com.ssdut.wys.db;

public class Persistency implements Database{

	private static RedisDB redisClient=new RedisDB();
	
	public boolean set(String shortUrl, String fullUrl) {
		if(redisClient.set(shortUrl, fullUrl)){
			return true;
		}
		return false;
	}

	public String getByShort(String shortUrl) {
		String fullUrl=redisClient.getByShort(shortUrl);
		return fullUrl;
	}

	public String getByFull(String fullUrl) {
		String shortUrl=redisClient.getByFull(fullUrl);
		return shortUrl;
	}

}
