package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	// 액션포워드로 반환값이 없다는 건 액션포워드로 이동하지 않는다는 것
	// ajax 통신은 페이지 변경이 없는 통신이다.
	// MVC는 페이지 이동이 있고, ajax는 페이지 이동이 없다. (페이지의 이동이 있다면 화면이 깜빡인다)
}
