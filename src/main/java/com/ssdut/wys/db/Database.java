package com.ssdut.wys.db;

public interface Database {
	public boolean set(String shortUrl,String fullUrl);
	public String getByShort(String shortUrl);
	public String getByFull(String fullUrl);
}
