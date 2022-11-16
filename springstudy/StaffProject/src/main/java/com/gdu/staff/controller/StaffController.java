package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@GetMapping(value="/list.json", produces="application/json; charset=UTF-8")
	public List<StaffDTO> listStaff(){// list로 해줘야 Jackson이 이를 바꿔줌
		return staffService.getStaffList();
	}
	
	@ResponseBody
	@PostMapping(value="/add", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> add(StaffDTO staff) { // 컨트롤러는 심플하고 작업이 작은 것이 좋다(가장 자주 호출되기 때문)
		return staffService.addStaff(staff);
	}
	
	@ResponseBody
	@GetMapping(value="/query", produces="application/json; charset=UTF-8")
	public StaffDTO listStaffByNo(HttpServletRequest request) {
		String sno = request.getParameter("sno");
		System.out.println(staffService.getStaffByNo(sno));
		return staffService.getStaffByNo(sno);
	}
	
}
