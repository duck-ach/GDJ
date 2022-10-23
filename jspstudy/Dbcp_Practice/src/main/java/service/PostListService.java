package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Post;
import repository.PostDao;

public class PostListService implements PostService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// DB에서 가져온 게시글 목록
		List<Post> posts = PostDao.getInstance().selectAllBoard();
		
		// 게시글 목록을 JSP로 보내기 위해서 request에 저장
		request.setAttribute("posts", posts);
		
		// 어디로 / 어떻게
		ActionForward af = new ActionForward();
		af.setView("/post/list.jsp");
		af.setRedirect(false);
		
		return af;
	}

}
