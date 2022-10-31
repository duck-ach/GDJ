package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("board") // URL Mapping�� board�� �����ϴ� ��� ��û�� ó���ϴ� ��Ʈ�ѷ�
@Controller
public class MyController2 {

	/*
		������� ���� ���̴�.
		
		1. forward
		return "board/detail";
		board ���� �Ʒ� detail.jsp�� forward �Ͻÿ�.
		
		2. redirect
		return "redirect:/board/detail";
		URL Mapping ���� /board/detail�� ���ο� ��û���� redirect �Ͻÿ�.
	*/
	
	// <a href="${contextPath}/board/detail?title=��������&hit=10">
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		request.setAttribute("title", title);
		request.setAttribute("hit", hit);
		
		// ���ο��û�� /board/detail2?title=title&hit=hit �� ����� detail2�� Mapping���� �������ִ� ������ �̵��Ͻÿ�.
		return "redirect:/board/detail2?title=" + title + "&hit=" + hit; // /board/detail2 == ���ο� ��û, �Ʒ��� detail2�� ã�ư���� ��û
	}
	
	@GetMapping("detail2")
	public String detail2(String title, int hit, Model model) {
		// �����ιޱ�, ��ü�ιޱ� �ΰ�������� �ִµ� domain�� �������� �ʾ����Ƿ� ������ �޴´�.
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		return "board/detail";
	}
	
}
