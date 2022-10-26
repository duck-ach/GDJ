package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Member;
import repository.MemberDao;

public class MemberServiceImpl implements MemberService {

	@Override
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member memeber = Member.builder()
				.id(id)
				.pw(pw)
				.build();
		
		// mapper에서 온 데이터가 여기로옴. 성공하면 멤버가 반환되고, 
		Member login = MemberDao.getInstance().login(memeber);
		
		if(login != null) { // 페이지가 이동되어도 정보가 저장되는 공간인 Session에 저장 (session은 request파라미터로 알아낼 수 있음)
			// 성공
			HttpSession session = request.getSession();
			session.setAttribute("login", login); // (저장하는이름(마음대로해도됨), 저장할 데이터)
			return new ActionForward("/index.jsp", false); // Forward로 처리 (로그인이 성공하면 index.jsp 로 이동)
		} else { //request.getContextPath() <이건 Redirect 처리할 때
			// 실패
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 실패');");
				out.println("history.back()"); // 메세지 alert를 만들고 나면 꼭 이동(history.back(), location.href)는 같이 다닌다.
				out.println("</script>");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null; // 윙
		}

	}

	@Override
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 초기화
		return new ActionForward(request.getContextPath(), true);

	}

	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
