package com.gdu.app12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

/* Request, Response, Session, Model을 최초 선언하는 것은 Controller이다. */
public interface BbsService { // request를 사용하는 이유는 ip때문 (ip를 알려면 request를 알아야함)
	public void findAllBbsList(HttpServletRequest request, Model model);
	public int addBbs(HttpServletRequest request);
	public int addReply(HttpServletRequest request);
	public int removeBbs(int bbsNo);
	
}
