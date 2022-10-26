package com.gdu.app1.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml03/appCtx.xml");
		Person me = ctx.getBean("person", Person.class);
		
		System.out.println(me.getName());
		System.out.println(me.getAge());
		Address address = me.getAddr();
		System.out.println(address.getJibun());
		System.out.println(address.getRoad());
		System.out.println(address.getZipCode());
		ctx.close();
		
	}

}
