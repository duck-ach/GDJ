package com.gdu.app1.xml08;

import org.springframework.context.support.GenericXmlApplicationContext;

import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml08/appCtx.xml");
		Member member = ctx.getBean("member", Member.class);
		member.info();
		ctx.close();

	}

}
