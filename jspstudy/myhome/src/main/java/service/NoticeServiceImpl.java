package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.Notice;
import repository.NoticeDao;

public class NoticeServiceImpl implements NoticeService {


	@Override
	public ActionForward findAllNotices(HttpServletRequest request) {
		// 파라미터 page 확인하기
		// 파라미터 page가 없으면 page=1로 처리
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// begin과 end 전에는 전체목록 개수가 꼭 필요한 상황이다.
		// 전체 목록의 갯수 (singleton pattern 으로 생성)
		NoticeDao dao = NoticeDao.getInstance();
		int totalRecordCnt = dao.selectAllNoticesCnt(); // 다오가 전체목록 개수를 가져온다.
		
		// 한 페이지에 표시할 목록의 개수(목록 하나를 record라고 하겠다.)
		int recordPerPage = 10;
		
		/*
		 	특정 page에 표시할 목록의 시작번호와 끝번호
		 				   begin	end
		 		page = 1 : 1		10
		 		page = 2 : 11		20
		 		page = 3 : 21		30
		 		page = 4 : 31		35		(전체 목록이 35인 경우)
		 	* 게시글 번호를 직접 쓰면 큰일남 ROWNUM으로 써야한다(삭제했을때 없어지므로)
		 	* begin과 end는 ROWNUM을 의미한다.
		 	* JK라는 기술스택이있는데 그건 paging기능을 그냥 해준다.
		*/ 
		int begin = (page - 1) * recordPerPage + 1;
		int end = begin + recordPerPage - 1; // 이것만으로는 정답이 될 수 없음. 마지막이 35인 경우도 있으므로
		if(end > totalRecordCnt) {
			end = totalRecordCnt;
		}
		
		// begin + end를 Map으로 만들어서 NoticeDao에게 전달해야 함
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		
		// begin에서 end사이 목록 가져오기
		List<Notice> notices = dao.selectAllNotices(map);
		
		// 목록을 forward 하기 위해 request에 저장
		request.setAttribute("notices", notices);
		
		/*
			block 개념 이해하기
			1 block 당 3 page가 표시되는 상황
			전체 7페이지가 있는 상황
			
					  beginPage	endPage
			1 block : 1			3			1 	2	3
			2 block : 4			6			4	5	6
			3 block : 7			7			7
			
			각 block의 beginPage와 endPage를 알아내기 위한 과정
				1) 전체 page의 개수를 구한다. (totalPage)
				2) 1 block당 표시할 page의 개수를 임의로 정한다.
				3) 현재 page와 전체 page 개수와 1 block 당 표시할 page 개수로 beginPage를 구한다.
				4) beginPage를 이용해서 endPage를 구한다.
				5) endPage와 전체 page 개수를 비교해서 작은 값을 endPage로 확정한다.
		*/
		
		int totalPageCnt = totalRecordCnt / recordPerPage; // 전체 page의 개수 = 전체 목록 개수 / 한 페이지에 표시할 페이지 개수
		if(totalRecordCnt % recordPerPage > 0) {
			totalPageCnt++;
		}
		
		int pagePerBlock = 3; // block당 pageCnt
		
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1; // 3
		int endPage = beginPage + pagePerBlock - 1; // 4
		if(endPage > totalPageCnt) {
			endPage = totalPageCnt;
		} // 5
		
		// 페이징 처리에 필요한 정보를 list.jsp로 전달
		request.setAttribute("page", page); 		  // 현재페이지
		request.setAttribute("beginPage", beginPage); // 시작페이지
		request.setAttribute("endPage", endPage); 	  // 종료페이지
		request.setAttribute("totalPageCnt", totalPageCnt); // 전체 페이지 개수
		request.setAttribute("pagePerBlock", pagePerBlock); // block당 페이지 수
		
		// board 폴더의 list.jsp로 forward
		return new ActionForward("/notice/list.jsp", false);
	}

}
	