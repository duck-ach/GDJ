package com.gdu.app13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private Long userNo; 			// 유저번호
	private String id;				// 아이디
	private String pw;				// 비밀번호(암호화된 비번 최대 64바이트)
	private String name;			// 이름
	private String gender;			// 성별(M, F, NO)
	private String email;			// 이메일(이메일 인증 때문에 UNIQUE)
	private String mobile;			// 휴대폰전화번호(하이픈제외 최대 11자리)
	private String birthyear; 		// 출생년도(YYYY)
	private String birthday;		// 생일(MMDD)
	private String postcode;		// 우편번호
	private String roadAddress;		// 도로명주소
	private String jibunAddress;	// 지번주소
	private String detailAddress;	// 상세주소
	private String extraAddress;	// 참고항목
	private Integer agreeCode;		// 동의여부(0:필수, 1:필수+위치, 2:필수+프로모션, 3:필수+위치+프로모션)
	private String snsType;			// 간편가입종류(사이트가입:null, 네아로:naver)
	private Date joinDate;			// 가입일
	private Date pwModifyDate;		// 비번 수정일
	private Date infoModifyDate;	// 회원정보 수정일
	private String sessionId;		// 세션 아이디
	private Date sessionLimitDate;  // 세션 만료일
}
