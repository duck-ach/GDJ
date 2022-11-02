package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app04.domain.Member;

// 이 컨트롤러에 도착한 요청은 member에서 시작한다. 라는 뜻
@RequestMapping("member")	// URL Mapping이 member로 시작하는 모든 요청을 처리하는 컨트롤러
@Controller
public class MyController1 {
	
	// <a href="${contextPath}/member/detail1">
	@GetMapping("detail1") // 위에 RequestMapping을 member를 주었으므로 (/member/detail1)이 된다.
	public String detail1(HttpServletRequest request) { // 여기 매개변수로 index.jsp의 <a href="${contextPath}/member/detail?id=admin&pw=1234">의 매개변수가 전송된다.
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = new Member(id, pw);
		request.setAttribute("member", member);
		
		return "member/detail"; // member 폴더 아래 detail.jsp로 forward 하시오. 라는 뜻 		
	}
	
	// location.href=1${contextPath}/member/detail2?id=admin&pw=1234';
	@GetMapping("detail2")
	public String detail2(@RequestParam(value="id", required = false, defaultValue = "master") String id // 파라미터 id를 String id에 저장하시오
						, @RequestParam(value="pw", required = false, defaultValue = "1111") String pw // 파라미터 pw를 String pw에 저장하시오
						, Model model) {
		
		Member member = new Member(id, pw);
		
		// forward할 데이터를 model에 담아 두는 방법 (Spring에서는 isRedirect(false) 하지 않는다. 이것이 스프링의 방식)
		// request를 이용하는 방식에 비해 보안이 향상되었다.
		model.addAttribute("member", member); // model은 request를 저장소로 사용한다.
		
		return "member/detail";
	}
	/*
		@RequestParam
		
		@RequestParam을 사용할 때 파라미터가 없으면 오류(문제)가생긴다.
		필수속성. (required = "")
		default 처리. (defaultValue="")
		
		1. value 		: 파라미터 이름
		2. required 	: 파라미터의 필수 여부(Default - true)
		3. defaultValue : 파라미터가 없을 때 사용할 값
	
	*/
	
	/*
		Model
		
		Controller 에서 생성된 데이터를 담아서 View로 전달할 때 사용하는 객체
		Servlet의 request.setAttribute()와 유사한 역할을 한다.
		Method에 Model 타입이 지정된 경우 Model 타입의 객체를 만들어서 메서드에 주입한다.
		addAttribute("키", "값") 메소드를 사용하여 전달할 데이터 세팅을 한다.
		forward할 데이터를 담아두는 곳.
		
	*/
	
	
	// location.href='${contextPath}/member/detail3?id=admin&pw=1234';
	@GetMapping("detail3") 	// @RequestParam은 생략할 수 있다. 파라미터 id 가 없는 경우 null이 저장된다.
	public String detail3(String id // @RequestParam은 생략할 수 있다. 파라미터 pw 가 없는 경우 null이 저장된다.
					    , String pw
					    , Model model) {
		Member member = new Member(id, pw);
		model.addAttribute("member", member);
		return "member/detail";
	}
	
	// <form action="${contextPath}/member/detail4" method="get">
	@GetMapping("detail4")
	public String getDetail4(Member member // 파라미터 id, pw를 setId(), setPw() 메소드를 이용해서 member 객체에 저장해 준다.
						   , Model model) {
		model.addAttribute("member", member);
		
		return "member/detail";
	}
	
	// <form action="${contextPath}/member/detail4" method="post">
	@PostMapping("detail4") // 요청 : URLMapping + 요청메소드
	public String postDetail4(@ModelAttribute(value="member") Member member) { // 파라미터 id, pw를 이용해 Member member를 만들고, Model에 member라는 이름의 속성으로 저장하시오.
		return "member/detail";
	}
	
		
}