package com.gdu.app11.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

/* Request, Response, Session, Model을 최초 선언하는 것은 Controller이다. */
public interface EmpService {
	public void findAllEmployees(HttpServletRequest request, Model model);
	public void findEmployees(HttpServletRequest request, Model model);
	public Map<String, Object> findAutoCompleteList(HttpServletRequest request);
}
