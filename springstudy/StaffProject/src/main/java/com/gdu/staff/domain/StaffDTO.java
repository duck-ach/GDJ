package com.gdu.staff.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDTO {
	private String sno;  // 사원번호
	private String name; // 사원명
	private String dept; // 부서
	private int salary;  // 연봉
}
