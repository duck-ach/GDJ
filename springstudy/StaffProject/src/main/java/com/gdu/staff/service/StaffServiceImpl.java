package com.gdu.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public List<StaffDTO> getStaffList() {
		return staffMapper.selectStaffList();
	}
	
	@Override
	public ResponseEntity<String> addStaff(StaffDTO staff) { // sno, name, depf 파라미터로 넘어온 값이 staff로 넘어옴
		// staff에 salary 넣기 : 기획부 1000, 개발부 2000, 영업부 3000, 나머지 4000
		try {
			switch(staff.getDept()) {
			case "기획부" : staff.setSalary(1000);
			break;
			case "개발부" : staff.setSalary(5000); // 개발부 짱!!!!
			break;
			case "영업부" : staff.setSalary(3000);
			break;
			default : staff.setSalary(2000);
			}
			staffMapper.insertStaff(staff);
			
			return new ResponseEntity<String>("사원 등록이 성공했습니다.", HttpStatus.OK); // <String>body(응답할 String), HttpStatus	
		} catch (Exception e) {
			return new ResponseEntity<String>("사원 등록이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public StaffDTO getStaffByNo(String sno) {
		return staffMapper.selectStaffBySno(sno);
	}

}
