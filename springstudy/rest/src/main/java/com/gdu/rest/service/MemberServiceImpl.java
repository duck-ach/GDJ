package com.gdu.rest.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gdu.rest.domain.MemberDTO;
import com.gdu.rest.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response) {
		
		try {
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("insertResult", memberMapper.insertMember(member));
			
		} catch(DuplicateKeyException e) {
									// 아이디가 중복되었을 때
		} catch(Exception e) {
			System.out.println(e.getClass().getName()); // 예외이유(예외가 일어난 클래스의 이름)
		}
		
		return null;
	}
	
}
