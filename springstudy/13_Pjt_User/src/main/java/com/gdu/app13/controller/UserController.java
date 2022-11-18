package com.gdu.app13.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	// 컨트롤러의 모든 요청 이전에 일어나는 intercepter가 일어난다.
	// intercepter는 가로채기를 하는 애다. 어떤 처리를 하기전에 자기가 개입해서 일처리를 하고 들어옴
	// 모든 intercepter의 위치는 여기(field와 Mapping 사이)
	// true와 false를 반환함. (true : intercepter가 개입하고 일을 처리, false : 작업을 취소하고 이건안되겠다 하면서 막아버림)
	// 만들어만 두면 스스로 개입한다.
	
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
		
		// 네이버 로그인
		model.addAttribute("apiURL", userService.getNaverLoginApiURL(request));
		
		return "user/login";
	}
	
	@PostMapping("/user/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request, response);
	}
	
	@GetMapping("/user/naver/login")
	public void naverLogin(HttpServletRequest request) {
		userService.getNaverLoginTokenNProfile(request);
	}
	
	
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {// 세션 초기화 (자동로그인을 풀려면 쿠키 한쪽 제거해줘야함)
		userService.logout(request, response);
		return "redirect:/";
	}
	
	@GetMapping("/user/check/form")
	public String requiredLogin_checkForm() {
		return "user/check";
	}
	
	@ResponseBody
	@PostMapping(value="/user/check/pw", produces="application/json") // true false 들어갈예정이라 charset은 굳이 해주지 않아도 된다.
	public Map<String, Object> requiredLogin_checkPw(HttpServletRequest request) { // service에서 Map을 반환하므로 Map, request를 필요로 하므로 request를 인자값으로 넘겨준다.
		return userService.confirmPassword(request);
	}
	
	@GetMapping("/user/mypage") // location으로 이동하는것이기 때문에 GetMapping
	public String requiredLogin_mypage() {
		return "user/mypage";
	}
	
	@PostMapping("/user/modify/pw")
	public void requiredLogin_modifyPw(HttpServletRequest request, HttpServletResponse response) {
		userService.modifyPassword(request, response);
	}
	
	@GetMapping("/user/sleep/display") // sendRedirect라서 주소창이동. GETMAPPING
	public String sleepDisplay() {
		return "user/sleep";
	}
	
	@PostMapping("/user/restore")
	public void restore(HttpServletRequest request, HttpServletResponse response) {
		userService.restoreUser(request, response);
	}
}
