package com.daqin.spring.test;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("test")
public class Test {
	
	@Resource
	private TestService testService;
	
	private void print() {
		System.out.println(testService.getSomething());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(new String("WebContent/WEB-INF/applicationContext.xml"));
		Test test = (Test)ctx.getBean("test");
		test.print();
	}

}
