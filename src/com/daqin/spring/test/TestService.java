package com.daqin.spring.test;

import org.springframework.stereotype.Service;

@Service("testService")
public class TestService {
	
	protected String getSomething() {
		return "hello world";
	}

}
