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
	
	// welcomePage ���
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	// index.jsp ���� member.jsp�� �̵�
	@GetMapping("member")
	public String member() {
		return "member";
	}
	
	// field
	@Autowired // Container(root-context.xml)���� Ÿ��(class)�� ��ġ�ϴ� bean�� ����������.
	private MemberService memberService;
	
	/*
		@ResponseBody
		
		�ȳ�. �� ajax ó���ϴ� �޼ҵ��.
		���� ��ȯ�ϴ� ���� ��(JSP) �̸��� �ƴϰ�, � ������(text, json, xml ��)��.
		`
	*/
	
	// btn1
	// member���� detail1 ��û�� ������ ��
	@ResponseBody
	@GetMapping(value="member/detail1"
			  , produces="text/plain; charset=UTF-8") // produces : ���䵥������ Type(mime-type)�� ����
	public String detail1(HttpServletRequest request, HttpServletResponse response) {
		String str = memberService.execute1(request, response);
		return str; // ���� @ResponseBody�� ���� @GetMapping�� �Ǿ��ִٸ� (str�����ִµ�����).jsp��� �ν�
	}
	
	// btn2
	@ResponseBody
	@GetMapping(value="member/detail2"
			  , produces="application/json; charset=UTF-8")
	public Member detail2(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {// MemberŸ���� ��ȯ�ҰŶ�� service�� ����صξ��� ������ ��ȯŸ���� Member
		Member member = memberService.execute2(id, pw);
		return member; // Jackson�� member ��ü�� {"id":���̵�, "pw":�н�����} ������ JSON���� �ٲ㼭 �����մϴ�.
		// Jackson�� Member��ü�� ��ȯ��Ű�µ� Jackson���õ� �ڵ尡 ����. (produces�� �˷��ذ��� �ν���..)
		
		/*
			�߾��� �ڵ�
			JSONObject obj = new JSONObject(member);
			return obj.toString();
		 */
	}
	
	// btn3
	@ResponseBody
	@GetMapping(value="member/detail3"
		      , produces=MediaType.APPLICATION_JSON_VALUE) // produces="application/json; charset=UTF-8"
	public Map<String, Object> detail3(Member member) {
		Map<String, Object> map = memberService.execute3(member); // execute3���� ���
		return map;
	 // return memberService.execute3(member); �̷��� ���ٷ� return�ϴ� ���� ����. 
	}
	
	// btn4 (postó��)
	/*
		@RequestBody
		
		�ȳ�. �� ��û �����Ͱ� body�� ���ԵǾ� �ִٰ� �˷��ִ� ���� ��
		��û �Ķ���Ϳ����� ����� �� ����,
		post ������� �Ķ���� ���� �����Ͱ� ���޵� �� ����� �� �־�.
	*/
	@ResponseBody
	@PostMapping(value="member/detail4"
			   , produces=MediaType.APPLICATION_JSON_VALUE)
	public Member detail4(@RequestBody Member member) {
		return memberService.execute4(member);
	}
	
}
