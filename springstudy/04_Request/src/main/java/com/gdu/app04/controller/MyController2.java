package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	// <a href="${contextPath}/board/detail?title=공지사항&hit=10">
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		request.setAttribute("title", title);
		request.setAttribute("hit", hit);
		
		// 새로운요청을 /board/detail2?title=title&hit=hit 로 만들어 detail2의 Mapping값을 가지고있는 곳으로 이동하시오.
		return "redirect:/board/detail2?title=" + title + "&hit=" + hit; // /board/detail2 == 새로운 요청, 아래의 detail2로 찾아가라는 요청
	}
	
	@GetMapping("detail2")
	public String detail2(String title, int hit, Model model) {
		// 변수로받기, 객체로받기 두가지방법이 있는데 domain을 만들어두지 않았으므로 변수로 받는다.
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		return "board/detail";
	}
	
}
