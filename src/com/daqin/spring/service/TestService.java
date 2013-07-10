package com.daqin.spring.service;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sun.jersey.api.core.HttpResponseContext;

@Path("/test")
public class TestService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		return "hi,are you ok?";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postTest(){
		return "this is post type";
	}
	
	public static void main(String[] args){
		try{
			String url = "http://183.194.128.28:7001/maximo/mobile/test/login?username=yaorui&password=ABC";
			String url1 = "http://183.194.128.28:7001/maximo/mobile/test/desc";
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			CookieStore cookie = httpClient.getCookieStore();
			
			System.out.println("Login form get: " + response.getStatusLine());
			
			HttpEntity entity = response.getEntity();
			System.out.println("login content: " + EntityUtils.toString(entity, "utf-8"));

            System.out.println("login Initial set of cookies:");
            List<Cookie> cookies = httpClient.getCookieStore().getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
            
            System.out.println("\n");
            
            
            httpClient = new DefaultHttpClient();
			httpGet = new HttpGet(url1);
			httpClient.setCookieStore(cookie);
			response = httpClient.execute(httpGet);
			
			 System.out.println("desc form get: " + response.getStatusLine());
			
			entity = response.getEntity();
			System.out.println("desc content: " + EntityUtils.toString(entity, "utf-8"));

            System.out.println("desc Initial set of cookies:");
            cookies = httpClient.getCookieStore().getCookies();
            if (cookies.isEmpty()) {
                System.out.println("None");
            } else {
                for (int i = 0; i < cookies.size(); i++) {
                    System.out.println("- " + cookies.get(i).toString());
                }
            }
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
