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

// �� ��Ʈ�ѷ��� ������ ��û�� member���� �����Ѵ�. ��� ��
@RequestMapping("member")	// URL Mapping�� member�� �����ϴ� ��� ��û�� ó���ϴ� ��Ʈ�ѷ�
@Controller
public class MyController1 {
	
	// <a href="${contextPath}/member/detail1">
	@GetMapping("detail1") // ���� RequestMapping�� member�� �־����Ƿ� (/member/detail1)�� �ȴ�.
	public String detail1(HttpServletRequest request) { // ���� �Ű������� index.jsp�� <a href="${contextPath}/member/detail?id=admin&pw=1234">�� �Ű������� ���۵ȴ�.
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = new Member(id, pw);
		request.setAttribute("member", member);
		
		return "member/detail"; // member ���� �Ʒ� detail.jsp�� forward �Ͻÿ�. ��� �� 		
	}
	
	// location.href=1${contextPath}/member/detail2?id=admin&pw=1234';
	@GetMapping("detail2")
	public String detail2(@RequestParam(value="id", required = false, defaultValue = "master") String id // �Ķ���� id�� String id�� �����Ͻÿ�
						, @RequestParam(value="pw", required = false, defaultValue = "1111") String pw // �Ķ���� pw�� String pw�� �����Ͻÿ�
						, Model model) {
		
		Member member = new Member(id, pw);
		
		// forward�� �����͸� model�� ��� �δ� ��� (Spring������ isRedirect(false) ���� �ʴ´�. �̰��� �������� ���)
		// request�� �̿��ϴ� ��Ŀ� ���� ������ ���Ǿ���.
		model.addAttribute("member", member); // model�� request�� ����ҷ� ����Ѵ�.
		
		return "member/detail";
	}
	/*
		@RequestParam
		
		@RequestParam�� ����� �� �Ķ���Ͱ� ������ ����(����)�������.
		�ʼ��Ӽ�. (required = "")
		default ó��. (defaultValue="")
		
		1. value 		: �Ķ���� �̸�
		2. required 	: �Ķ������ �ʼ� ����(Default - true)
		3. defaultValue : �Ķ���Ͱ� ���� �� ����� ��
	
	*/
	
	/*
		Model
		
		Controller ���� ������ �����͸� ��Ƽ� View�� ������ �� ����ϴ� ��ü
		Servlet�� request.setAttribute()�� ������ ������ �Ѵ�.
		Method�� Model Ÿ���� ������ ��� Model Ÿ���� ��ü�� ���� �޼��忡 �����Ѵ�.
		addAttribute("Ű", "��") �޼ҵ带 ����Ͽ� ������ ������ ������ �Ѵ�.
		forward�� �����͸� ��Ƶδ� ��.
		
	*/
	
	
	// location.href='${contextPath}/member/detail3?id=admin&pw=1234';
	@GetMapping("detail3") 	// @RequestParam�� ������ �� �ִ�. �Ķ���� id �� ���� ��� null�� ����ȴ�.
	public String detail3(String id // @RequestParam�� ������ �� �ִ�. �Ķ���� pw �� ���� ��� null�� ����ȴ�.
					    , String pw
					    , Model model) {
		Member member = new Member(id, pw);
		model.addAttribute("member", member);
		return "member/detail";
	}
	
	// <form action="${contextPath}/member/detail4" method="get">
	@GetMapping("detail4")
	public String getDetail4(Member member // �Ķ���� id, pw�� setId(), setPw() �޼ҵ带 �̿��ؼ� member ��ü�� ������ �ش�.
						   , Model model) {
		model.addAttribute("member", member);
		
		return "member/detail";
	}
	
	// <form action="${contextPath}/member/detail4" method="post">
	@PostMapping("detail4") // ��û : URLMapping + ��û�޼ҵ�
	public String postDetail4(@ModelAttribute(value="member") Member member) { // �Ķ���� id, pw�� �̿��� Member member�� �����, Model�� member��� �̸��� �Ӽ����� �����Ͻÿ�.
		return "member/detail";
	}
	
		
}
