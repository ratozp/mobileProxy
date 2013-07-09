package com.daqin.spring.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
