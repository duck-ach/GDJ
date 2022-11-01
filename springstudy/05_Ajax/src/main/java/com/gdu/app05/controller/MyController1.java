package com.gdu.app05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Member;
import com.gdu.app05.service.MemberService;

@Controller
public class MyController1 {
	
	// welcomePage 등록
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	// index.jsp 에서 member.jsp로 이동
	@GetMapping("member")
	public String member() {
		return "member";
	}
	
	// field
	@Autowired // Container(root-context.xml)에서 타입(class)이 일치하는 bean을 가져오세요.
	private MemberService memberService;
	
	/*
		@ResponseBody
		
		안녕. 난 ajax 처리하는 메소드야.
		내가 반환하는 값은 뷰(JSP) 이름이 아니고, 어떤 데이터(text, json, xml 등)야.
		`
	*/
	
	// btn1
	// member에서 detail1 요청이 들어왔을 때
	@ResponseBody
	@GetMapping(value="member/detail1"
			  , produces="text/plain; charset=UTF-8") // produces : 응답데이터의 Type(mime-type)을 적음
	public String detail1(HttpServletRequest request, HttpServletResponse response) {
		String str = memberService.execute1(request, response);
		return str; // 만약 @ResponseBody가 없고 @GetMapping만 되어있다면 (str에들어가있는데이터).jsp라고 인식
	}
	
	// btn2
	@ResponseBody
	@GetMapping(value="member/detail2"
			  , produces="application/json; charset=UTF-8")
	public Member detail2(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {// Member타입을 반환할거라고 service에 명시해두었기 때문에 반환타입이 Member
		Member member = memberService.execute2(id, pw);
		return member; // Jackson이 member 객체를 {"id":아이디, "pw":패스워드} 형식의 JSON으로 바꿔서 전달합니다.
		// Jackson이 Member객체를 반환시키는데 Jackson관련된 코드가 없다. (produces가 알려준거임 인싸임..)
		
		/*
			추억의 코드
			JSONObject obj = new JSONObject(member);
			return obj.toString();
		 */
	}
	
	// btn3
	@ResponseBody
	@GetMapping(value="member/detail3"
		      , produces=MediaType.APPLICATION_JSON_VALUE) // produces="application/json; charset=UTF-8"
	public Map<String, Object> detail3(Member member) {
		Map<String, Object> map = memberService.execute3(member); // execute3번의 결과
		return map;
	 // return memberService.execute3(member); 이렇게 한줄로 return하는 것을 권장. 
	}
	
	// btn4 (post처리)
	/*
		@RequestBody
		
		안녕. 난 요청 데이터가 body에 포함되어 있다고 알려주는 일을 해
		요청 파라미터에서는 사용할 수 없고,
		post 방식으로 파라미터 없이 데이터가 전달될 때 사용할 수 있어.
	*/
	@ResponseBody
	@PostMapping(value="member/detail4"
			   , produces=MediaType.APPLICATION_JSON_VALUE)
	public Member detail4(@RequestBody Member member) {
		return memberService.execute4(member);
	}
	
}
