package com.gdu.app1.xml05;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class SpringMain {

	public static void main(String[] args) {
		
		// ������Ʈ�� Build Path�� ojdbc6.jar ����ϰ� �����մϴ�.
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml05/appCtx.xml");
		MyConnection myCon = ctx.getBean("conn", MyConnection.class);
		Connection con = myCon.getConnection();
		
		if(con != null) {
			ctx.close();			
		}
		
		ctx.close();
		
		

	}

}
