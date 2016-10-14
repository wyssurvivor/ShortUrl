namespace java com.ssdut.wys.service

service ShortUrl{
	string generate(1:string fullUrl),
	string getFullUrl(1:string shortUrl),
}