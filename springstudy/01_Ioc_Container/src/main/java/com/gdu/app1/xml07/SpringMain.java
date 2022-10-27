package com.gdu.app1.xml07;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml07/appCtx.xml");
		User user = ctx.getBean("user", User.class); // (이름, 타입)
		user.info();
		ctx.close();

	}

}
