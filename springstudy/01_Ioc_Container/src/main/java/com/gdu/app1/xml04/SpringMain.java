package com.gdu.app1.xml04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml04/appCtx.xml");
		
		Dao dao1 = ctx.getBean("dao", Dao.class);
		Dao dao2 = ctx.getBean("dao", Dao.class);
		Dao dao3 = ctx.getBean("dao", Dao.class);
		
		System.out.println(dao1 == dao2);
		System.out.println(dao2 == dao3);
		System.out.println(dao1 == dao3);
		
		ctx.close();
	}

}
