package com.gdu.app03.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app03.domain.Board;
import com.gdu.app03.domain.Notice;

@Controller // Controller는 Setvlet에서 작업할 필요없이 Controller가 된다.
public class MyController {

	/*
		@RequestMapping(value="/", method=RequestMethod.GET)
		@RequestMapping(value="/", method=RequestMethod.POST)
		
		Spring4부터 새로운 애너테이션(Annotation)으로 바꿀 수 있다.
		
		@GetMapping("/")
		@PostMapping("/")
	*/
	@RequestMapping("/") // ContextPath로 요청되면 이라는 뜻 [ http://localhost:9090/app03으로 접속하면 처리되는 메소드 ]
	public String welcome() {
		return "default";
		// ViewResolver에 의해서 
		// return "/WEB-INF/views/default.jsp"로 해석
	}
	
	/*
		Container에 등록된 Bean을 가지고오는 방법 (만드는방법말고, 가지고오는 것)
		
		@Inject    : 
			1) 타입(Class)이 일치하는 Bean을 가지고 오는 애너테이션
			2) 동일 타입이 여러 개 있는 경우 @Qualifier를 이용해서 Bean을 식별
		@Resource  :
			1) 이름(id)이 일치하는 Bean을 가지고 오는 애너테이션
		@Autowired : 
			1) 타입(class)이 일치하는 Bean을 가지고 오는 애너테이션
			2) 동일 타입이 여러 개 있는 경우 자동으로 @Qualifier를 동록해서 Bean을 식별
			3) 실무에서 주로 사용
	*/
	/*
		@Autowired 사용 방법
		
		1. 필드로 생성된 Bean 가져오기
			1) 필드마다 @Autowired를 추가한다. (필드가 10개면 @Autowired도 10번 작성)
			2) 필드가 많으면 사용하지 않는다.
		2. 생성자를 이용해 Bean 가져오기
			1) 생성자의 매개변수로 Bean을 가져온다.
			2) @Autowired를 작성할 필요가 없다.
		3. 메소드를 이용해 Bean 가져오기
			1) 메소드의 매개변수로 Bean을 가져온다.
			2) @Autowired를 작성해야 한다.
			3) 일반적으로 setter를 사용하지만 setter가 아니어도 상관 없다.
	*/
	
	// 1. 필드로 생성된 Bean 가져오기 (Container의 Bean을 가져와서 여기다 넣은 것)
	// Autowired라는 애가 Bean을 찾아서 자동으로 값을 넣어주는 것(getter/setter)없이 실행
/*	
 	@Autowired
	private Board board; 
*/
	
	
	/*
		@Autowired는 타입(class)이 일치하는 Bean을 Container에서 가져온다.
		
		@Autowired
		private Board board; // 타입이 Board인 Bean을 Container에서 가져오기
		
		--- Container ---------------------------
		<bean id="board1" class="Board">
		<!-- <bean id="board2" class="Board"> -->
		-----------------------------------------
	*/
	
	// 2. 생성자를 이용해 Bean 가져오기
/*
	private Board board;
	
	public MyController(Board board) { // 매개변수 Board board로 타입이 Board인 Bean을 Container에서 가져오거라.
		super();
		this.board = board;
	}
*/
	
//	// 3. 메소드를 이용해 Bean 가져오기
//	private Board board;
//	// setter만 생성
//	@Autowired // 일반 메소드는 @Autowired를 반드시 작성해야 한다.
//	public void 메소드이름은상관이없다(Board board) { // 매개변수 Board board로 Board 타입의 Bean이 Container에서 
//		this.board = board;
//	}


	/*
	  	Autowired는 class를 통해 객체를 찾는다.
	  	root-content.xml 에서는 Board 객체가 2개가 있기 때문에, 타입(class)를 통해 찾는것은 실패로 돌린다. (plan A)
	  	그럼 이제 plan B 단계로 들어서서 id(변수명)를 가져오게 된다. (그래서 아래 Autowired에서 변수명을 board1이 아닌 b1으로 바꾸게되면 찾지못하여 오류가 뜬다.)
	 */
	
	// 4. 동일한 타입의 Bean이 여러 개 등록된 경우
	// 	  1) 변수명을 자동으로 식별자(Qualifier)로 인식한다.
	// 	  2) 식별자(@Qualifier)는 Bean의 이름(id)이 일치하는 Bean을 가져온다.
//	@Autowired
//	private Board board1;
//	
//	@Autowired
//	private Board board2;

	// 4-2. 생성자를 이용해 Bean 가져오기
//	private Board b1;
//	private Board b2;
//	
//	public MyController(Board board1, Board board2) { // 매개변수명이 Bean의 이름(id)과 일치하므로 자동으로 주입된다.
//		b1 = board1;
//		b2 = board2;
//	}
	
	// 4-3. 메소드를 이용해 Bean 가져오기
	private Board b1;
	private Board b2;
	
//	@Autowired
//	public void setB1(Board board1) {
//		this.b1 = board1;
//	}
//	@Autowired
//	public void setB2(Board board2) {
//		this.b2 = board2;
//	}
	// 위처럼 메소드를 2개 만들이유가 없어보여서 아래와같이 하나로 통합해버린다.
	@Autowired
	public void setBoard(Board board1, Board board2) {
		this.b1 = board1;
		this.b2 = board2;
	}
	

	/*
		@Inject : 오직 타입(class)만 확인. 동일 타입이 다수 발견되면 @Qualifier를 통해서 이름(id)을 구분.
					@Qualifier가 없으면 최종 오류
		@Resource : 이름(id)으로 확인
		@Autowired : 일단 타입(class) 확인. 동일 타입이 다수 발견되면 자동으로 변수명을 @Qualifier로 인식해서 이름(id)를 확인
		
		Inject를 안쓰는 이유는 Qualifier작업을 따로따로 해주어야 해서 잘 안쓴다.
		Autowired는 Qualifier작업이 내장되어 있기 때문에 2번쓰고 그럴 필요가 없기 때문에 잘 쓰인다.
	*/
	
	// 요청을 만들어 확인
	@GetMapping("board/detail") // @GetMapping("/board/detail")
	public void boardDetail() {
		System.out.println(b1.getBoardNo());
		System.out.println(b1.getTitle());
		System.out.println(b1.getCreateDate());
		System.out.println(b2.getBoardNo());
		System.out.println(b2.getTitle());
		System.out.println(b2.getCreateDate());
	}
	
// @Autowired를 사용하는 이유 : @Inject + @Qualifier
/*
	@Inject
	@Qualifier(value="board1") // Qualifier를 보고 board1을 찾아라 하는거다.
	private Board b1;
	@Inject
	@Qualifier(value="board2") // Qualifier를 보고 board1을 찾아라 하는거다.
	private Board b2;
*/
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 1. field를 이용해 Bean 가져오기
//	@Autowired
//	private Notice notice1;
//	
//	@Autowired
//	private Notice notice2;
	
	// 2. 생성자를 이용해 Bean 가져오기
//	@Autowired
//	private Notice notice1;
//	@Autowired
//	private Notice notice2;
//	
//	public MyController(Notice notice1, Notice notice2) {
//		super();
//		this.notice1 = notice1;
//		this.notice2 = notice2;
//	}

	
	// 3. 메소드를 이용해 Bean 가져오기
	private Notice noti1;
	private Notice noti2;
	@Autowired
	public void setNotice(Notice notice1, Notice notice2) {
		this.noti1 = notice1;
		this.noti2 = notice2;
	}

	
	@GetMapping("notice/detail")
	public void noticeDetail() {
		System.out.println(noti1.getNoticeNo());
		System.out.println(noti1.getTitle());
		System.out.println(noti2.getNoticeNo());
		System.out.println(noti2.getTitle());
	}
	
	
	
}
