# Web-Scraping-Using-Java

Web Scraping refers to the extraction of data from a website. This information is collected and then exported into a format that is more useful for the user. Be it a spreadsheet or an API.
Data displayed by most websites can only be viewed using a web browser. They do not offer the functionality to save a copy of this data for personal use. 

The only option then is to manually copy and paste the data - a very tedious job which can take many hours or sometimes days to complete.  
Web Scraping is the technique of automating this process, so that instead of manually copying the data from websites, the  Web Scraping Java Code will perform the same task 
within a fraction of the time.


![](https://github.com/Yashzyash/Web-Scarfing-Using-Java/blob/master/imgs/webscraping_from_imdb.png)

![](https://github.com/YashzAlphaGeek/Web-Scarfing-Using-Java/blob/master/imgs/Console_Output.png)
As shown in the above image, the most popular Netflix Series Information such as
 + Title
 + Year
 + Genre
 + Rating

## Backend Functionality:

  The web url (https://www.imdb.com/search/keyword/?keywords=netflix-original) connection and data gathering are done with Jsoup library. 

## Note:

  Please do the proxy settings if you are working in corporate industries because it might result you in UnknownHostException 

  java.net.UnknownHostException: www.imdb.com
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:184)
  
  
## Solution for UnknownHostException:

 + set HTTP proxy Host
   System.setProperty("https.proxyHost", ********);
 + set HTTP proxy port to 8080
   System.setProperty("https.proxyPort", "8080");
 + set HTTP proxyUser
   System.setProperty("https.proxyUser", "******");
 + set HTTP proxyPassword
   System.setProperty("https.proxyPassword", "******");
