package com.gdu.app14.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadList", uploadService.getUploadList()); // list.jsp에서 uploadList라는 이름으로 가져가므로
		return "upload/list";
	}
	
	@GetMapping("/upload/write")
	public String write() {
		return "upload/write";
	}
	
	// 첨부할 때는 multipartRequest 사용 (일반 Request 사용 불가능)
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		uploadService.save(multipartRequest, response);
	}
	
}
