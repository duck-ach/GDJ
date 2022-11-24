package com.gdu.app15.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app15.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/blog/list")
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("request", request); // model에 request저장
		blogService.getBlogList(model); // model만 넘기지만 이미 model에는 request가 존재 
		return "blog/list";				// 장점 : 서비스 하나당 메소드 하나 있을 시절 많이 사용했었음 
										// (매개변수의 통일이 필요한 시점) 
										// 모든 시스템에 매개변수를 model로 통합할 수 있음
										// 하지만 지금은 굳이 필요는 없지만 사용해보는 중
	}
	
	@GetMapping("/blog/write")
	public String write() {
		return "blog/write";
	}

	@PostMapping("/blog/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		blogService.saveBlog(request, response);
	}
	
	@ResponseBody // ajax
	@PostMapping(value="/blog/uploadImage", produces="application/json")
	public Map<String, Object> uploadImage(MultipartHttpServletRequest multipartRequest) {
		return blogService.saveSummernoteImage(multipartRequest);
	}
	
}
