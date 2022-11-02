package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app04.domain.Board;

@RequestMapping("board") // URL Mapping이 board로 시작하는 모든 요청을 처리하는 컨트롤러
@Controller
public class MyController2 {

	/*
		까먹으면 죽음 뿐이다.
		
		1. forward
		return "board/detail";
		board 폴더 아래 detail.jsp로 forward 하시오.
		
		2. redirect
		return "redirect:/board/detail";
		URL Mapping 값이 /board/detail인 새로운 요청으로 redirect 하시오.
	*/
	
	// <a href="${contextPath}/board/detail1?title=공지사항&hit=10">
	// 1. Request로 받기 (@RequestParam)
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		request.setAttribute("title", title);
		request.setAttribute("hit", hit);
		
		// 새로운요청을 /board/detail2?title=title&hit=hit 로 만들어 detail2의 Mapping값을 가지고있는 곳으로 이동하시오.
		return "redirect:/board/detail2?title=" + title + "&hit=" + hit; // /board/detail2 == 새로운 요청, 아래의 detail2로 찾아가라는 요청
	}
	
	// 2. 변수로 받기
	// <a href="${contextPath}/board/detail2?title=공지사항&hit=10">
	@GetMapping("detail2")
	public String detail2(String title, int hit, Model model) {
		// 변수로받기, 객체로받기 두가지방법이 있는데 domain을 만들어두지 않았으므로 변수로 받는다.
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		return "board/detail";
	}
	
	/*
		속성(Attribute) 전달 방식
		
		구분				forward				redirect
		-------------------------------------------------------------
		인터페이스			Model				RedirectAttributes
		메소드				addAttribute()		addFlashAttribute()
												addAttribute()를 쓸수 있지만 forward처럼 동작하므로 사용하지 말 것
												Flash가 붙어있는지 확인하면 됨.
	*/
	
	// 3. 객체로 받기
	// domain에서 setter(setTitle, setHit)를 설정해 놓으면 Spring이 인식해서 받을 수 있다.
	// <a href="${contextPath}/board/detail2?title=notice&hit=10">
	@GetMapping("detail3")
	public String detail3(Board board, 
						  RedirectAttributes redirectAttributes) {  // RedirectAttributes : Redirect할 때 속성을 보내 주겠다. 
		redirectAttributes.addFlashAttribute("board", board);
		return "redirect:/board/detail4"; // 새로운 요청에 파라미터를 추가하지 않았음을 주의할 것
	}
	
	// 2번과 3번은 파라미터가 통일이 안되고, 세션도 쓰기 어렵기 때문에 request를 사용하는 1번을 추천한다.
	
	@GetMapping("detail4")
	public String detail4() {
		return "board/detail";
	}
}