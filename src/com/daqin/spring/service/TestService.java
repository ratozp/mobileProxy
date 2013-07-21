package com.daqin.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.daqin.spring.domain.AppProxyConfig;
import com.daqin.spring.domain.ProxyRepairDo;
import com.daqin.spring.domain.ProxyUserInfoDo;

@Path("/user")
public class TestService extends BaseService{
	
	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("account") String account, 
			@FormParam("password") String password,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response){
		
		HttpSession session = request.getSession();
		System.out.println("sessionid: " + session.getId());
		
		System.out.println("account: " + account);
		System.out.println("password: " + password);
		
		if("user".equals(account) && "user".equals(password)){
			ProxyUserInfoDo user = new ProxyUserInfoDo();
			user.setRequestCode(AppProxyConfig.USER_LOGIN_SUCCESS);
			user.setNick("用户甲");
			user.setUid(001);
			return newSuccessResult(user);
		}
		 return newErrorResult("login false");
	}
	
	@Path("/loginForGet")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String loginForGet(@QueryParam("account") String account,
			@QueryParam("password") String password,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response){
		
		HttpSession session = request.getSession();
		System.out.println("sessionid: " + session.getId());
		
		System.out.println("account: " + account);
		System.out.println("password: " + password);
		if("foo@example.com".equals(account) && "hello".equals(password)){
			ProxyUserInfoDo user = new ProxyUserInfoDo();
			user.setRequestCode(AppProxyConfig.USER_LOGIN_SUCCESS);
			user.setNick("张三");
			user.setUid(001);
			return newSuccessResult(user);
		}
		 return newErrorResult("login false");
	}
	
	@Path("/repairList")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String loadRepairList(@FormParam("uid") int uid){
		System.out.println("loadRepairList: uid = " + uid);
		List list = new ArrayList();
		for(int i = 0; i < 10; i++){
			ProxyRepairDo repair = new ProxyRepairDo();
			repair.setId(2001 + i);
			repair.setName("收银系统");
			repair.setTitle("系统异常退出");
			repair.setInfo("进入收银系统入账功能时，卡死状态1分钟左右，然后提示异常并退出");
			list.add(repair);
		}
		return newSuccessResult(list);
	}
	
	public static void main(String[] args){
		try{
//			String url = "http://183.194.128.28:7001/maximo/mobile/test/login?username=yaorui&password=ABC";
//			String url1 = "http://183.194.128.28:7001/maximo/mobile/test/desc";
			String url = "http://localhost/zt/rest/user/loginForGet?account=foo@example.com&password=hello";
			String url1 = "http://localhost/zt/rest/user/loginForGet?account=foo@example.com&password=hello";
			
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
