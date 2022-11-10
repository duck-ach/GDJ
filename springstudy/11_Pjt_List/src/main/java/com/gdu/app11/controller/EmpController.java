package com.gdu.app11.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app11.service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/emp/list")
	public String list(HttpServletRequest request, Model model) {
		empService.findAllEmployees(request, model);
	      return "employee/list";
	      // 폴더이름 앞에 슬래시 붙이든 안붙이든 상관없다.
	}
	
	@GetMapping("/emp/search")
	public String search(HttpServletRequest request, Model model) {
		empService.findEmployees(request, model);
		return "employee/list";
	}
	
	@ResponseBody // ajax처리에는 @ResponseBody붙여야함 
	@GetMapping(value="/emp/autoComplete", produces="application/json")
	public Map<String, Object> autoComplete(HttpServletRequest request) {
		return empService.findAutoCompleteList(request);
	} // post 방식으로 넘어온 JSON을 Map에 저장할 수 있다.
}
