package com.gdu.app06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("board/detail")
	public String detail() {
		return "board/detail";
	}
	
}
