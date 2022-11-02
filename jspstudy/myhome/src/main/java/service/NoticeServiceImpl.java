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
		
		// board 폴더의 list.jsp로 forward
		return new ActionForward("/notice/list.jsp", false);
	}

}
	