package com.gdu.rest.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.rest.domain.MemberDTO;
import com.gdu.rest.service.MemberService;
/*
	REST : Representational State Transfer
	
	1. 자원을 정의하고 자원의 주소를 지정하는 방식에 대한 하나의 형식이다.
	2. REST 방식을 따르는 시스템을 "RESTful하다"라고 표현한다.
	3. 동작의 결정을 URL + Method 조합으로 결정한다.
	4. 파라미터가 URL에 경로처럼 포함된다. (?를 사용하지 않는다.)
	5. CRUD 처리 방식
				 	URL			Method
	   	1) 삽입  	/members	POST
	   	2) 목록  	/members 	GET
	   	3) 상세 	/members/1	GET
	   	4) 수정  	/members	PUT
	   	5) 삭제		/members/1	DELETE
		
*/
@RestController // 이 컨트롤러는 모든 메소드에 @ResponseBody 애너테이션을 추가한다.
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	
	// 삽입
	@PostMapping(value="/members", produces="application/json") // @RequestBody (본문에 들어있는 요청을 받아온다.)
	public Map<String, Object> addMember(@RequestBody MemberDTO member, HttpServletResponse response) { // 파라미터에 MemberDTO, Map 두가지 선택지
		return memberService.register(member, response);
	}
	
	@GetMapping(value="/members/page/{page}", produces="application/json") // 경로에 들어가있는 변수는 #안쓰고 중괄호{}만써서 표시해준다.
	public Map<String, Object> getMemberList(@PathVariable(value="page", required=false) Optional<String> opt) {
		int page = Integer.parseInt(opt.orElse("1")); // @DefaultValue가 없어서 Optional을 사용해주었다.
		return memberService.getMemberList(page);
	}
	
	// 상세
	@GetMapping(value="/members/{memberNo}", produces="application/json")
	public Map<String, Object> getMember(@PathVariable(value="memberNo", required=false) Optional<String> opt) {
		int memberNo = Integer.parseInt(opt.orElse("0")); // 의도적으로 Null값이 나올 수 있도록 만들어주기.
		return memberService.getMemberByNo(memberNo);
	}
	
	// 수정
	@PutMapping(value="/members", produces="application/json")
	public Map<String, Object> modifyMember(@RequestBody Map<String, Object> map, HttpServletResponse response) { // 본문에 요청된 request를 받아라. (DTO아니면 Map)
		return memberService.modifyMember(map, response);
	}
	
	// 삭제
	@DeleteMapping(value="/members/{memberNoList}", produces="application/json")
	public Map<String, Object> deleteMemberList(@PathVariable String memberNoList) { // Optional로 처리 일부러안함.
		return memberService.removeMemberList(memberNoList);
	}
	
}
