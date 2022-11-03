package com.gdu.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("contact/add")
	public String add() {
		return "contact/add";
	}
	
}
