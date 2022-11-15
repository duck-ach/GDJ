package com.gdu.app13.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app13.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/user/agree")
	public String agree() {
		return "user/agree";
	}
	
	@GetMapping("/user/join/write")
	public String joinWrite(@RequestParam(required=false) String location
						, @RequestParam(required=false) String promotion
						, Model model) {
		model.addAttribute("location", location);	// jsp에서는 request에 담아 값을 넘겨줬지만
		model.addAttribute("promotion", promotion); // spring에서 forward 할때 model로 넘겨줌
		return "user/join";
	}
	
	@ResponseBody // ajax 처리를 위해 @ResponseBody
	@GetMapping(value="/user/checkReduceId", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceId(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return userService.isReduceId(map);
	}

	@ResponseBody // ajax 처리를 위해 @ResponseBody
	@GetMapping(value="/user/checkReduceEmail", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceEmail(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		return userService.isReduceEmail(map);
	}
	
	@ResponseBody // ajax 처리를 위해 @ResponseBody
	@GetMapping(value="/user/sendAuthCode", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> sendAuthCode(String email) {
		return userService.sendAuthCode(email);
	}
	
	@PostMapping("/user/join")
	public void join(HttpServletRequest request, HttpServletResponse response) {
		userService.join(request, response);
	}
	
	@GetMapping("user/retire")
	public void retire(HttpServletRequest request, HttpServletResponse response) {
		userService.retire(request, response);
	}
	
	// login페이지 이동
	@GetMapping("/user/login/form") // <a> 태그를 이용하여 값을 전달하면 GET 방식이다.
	public String loginForm(HttpServletRequest request, Model model) {
		// 요청 헤더 referer : 이전 페이지의 주소가 저장
		model.addAttribute("url", request.getHeader("referer")); // 로그인 후 되돌아 갈 주소 url (header값중에 referer)
		return "user/login";
	}
	
	@PostMapping("/user/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request, response);
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
}
