package com.gdu.app06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;

// junit이 import 되는 이유 : pom.xml에 버전과 함께 선언되어있어서

/*
안녕. 난 테스트를 수행하는 클래스야.
JUnit4를 이용한 테스트를 수행해.
*/
@RunWith(SpringJUnit4ClassRunner.class)

/*
	안녕. 난 컨테이너에 저장된 bean이 어디에 있는지
	알려주는 역할이야.
	
	1. root-context.xml에 <bean> 태그를 작성한 경우
	   @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/servlet-context.xml"})
	2. com.gdu.app06.config.SpringBeanConfig.java에 @Bean을 작성한 경우
	   @ContextConfigration(classes={springBeanConfig.class}) // ,찍고 더 적어도 됨
	   
	3. component-scan과 컴포넌트(@Component, @Service, @Repository 등)를 이용한 경우
	   @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
*/
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // looping array : javascript = [대괄호], java = {중괄호}
public class BoardUnitTest {
	
	// DAO단위로 Unit 테스트를 진행하기 때문에
	// BoardDAO가 필요하다.
	
	@Autowired
	private BoardDAO dao;
	
	@Ignore
	public void 삽입테스트() {
		BoardDTO board = new BoardDTO(0, "테스트제목", "테스트내용", "테스트작성자", null, null);
		assertEquals(1, dao.insertBoard(board));
	}
	
	@Test
	public void 조회테스트() {
		
		assertNotNull(dao.selectBoardByNo(2)); // n번게시글 조회
		
	}
	
}
