package service;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;

public interface NoticeService {
	public ActionForward findAllNotices(HttpServletRequest request); // map으로 몇페이지부터~ 몇페이지까지 가져오자를 가져옴
}
