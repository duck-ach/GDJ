package com.gdu.app12.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.gdu.app12.domain.BbsDTO;
import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;
import com.gdu.app12.util.SecurityUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service // Bean 등록
public class BbsServiceImpl implements BbsService {

	private BbsMapper bbsMapper;
	private PageUtil pageUtil;
	private SecurityUtil securityUtil;
	
	@Override
	public void findAllBbsList(HttpServletRequest request, Model model) {
		
		System.out.println(securityUtil.getAuthCode(4));
		System.out.println(securityUtil.getAuthCode(6));
		
		// 파라미터 page, 전달되지 않으면 page=1로 처리
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1")); // 페이지값이 null이면 대신 1을 사용
		
		// 파라미터 recordPerPAge, 전달되지않으면 recordPerPage=10으로 처리
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("recordPerPage"));
		int recordPerPage = Integer.parseInt(opt2.orElse("10")); // 페이지값이 null이면 대신 1을 사용
		
		// 전체 게시글 개수
		int totalRecord = bbsMapper.selectAllBbsCount(); // 전체개수
		
		// 페이징 필요한 모든 계산 완료
		pageUtil.setPageUtil(page, recordPerPage, totalRecord);
		
		// DB로 보낼 Map(begin + end)
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// DB에서 목록 가져오기
		List<BbsDTO> bbsList = bbsMapper.selectAllBbsList(map);
		
		// 뷰로 보낼 데이터
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/bbs/list"));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("recordPerPage", recordPerPage);
		
	}

	@Override
	public int addBbs(HttpServletRequest request) {
		String writer = securityUtil.sha256(request.getParameter("writer"));
		String title = securityUtil.preventXSS(request.getParameter("title"));
		String ip = request.getRemoteAddr();
		
		BbsDTO bbs = new BbsDTO();
		bbs.setWriter(writer);
		bbs.setTitle(title);
		bbs.setIp(ip);
		
		return bbsMapper.insertBbs(bbs);
	}
	
	/*
		@Transactional
		안녕. 난 트랜잭션을 처리하는 애너테이션이야.
		INSERT/UPDATE/DELETE 중 2개 이상이 호출되는 서비스에 추가하면 돼.
		8장에도 트랜잭션 사용함
		8장 : 물샐틈 없이 다 하고싶어요.. Impl에 있는 모든 메소드를 지정하고 전부 트랜잭션처리함
		     장점 : 놓치는 Transaction이 없다는 것
		     단점 : 전부 Transaction 처리를 하다보니 성능에 문제가 생기는 점
		12장 : 선택해서 Transaction 처리하는 것
		 	 장점 : 지정한 메소드들만 Transaction 처리를 하다보니 성능도 좋다는 것
		 	 단점 : Transaction 처리를 놓칠 수도 있다는 점
	*/
	@Transactional
	@Override
	public int addReply(HttpServletRequest request) {
		
		// 작성자, 제목
		String writer = request.getParameter("writer"); // 로그인이 있다면 자동으로 되지만, 작성자와 IP주소를 받아와야함.
		String title = securityUtil.preventXSS(request.getParameter("title"));
		
		// IP
		String ip = request.getRemoteAddr(); 
		
		// 원글의 DEPTH, GROUP_NO, GROUP_ORDER
		int depth = Integer.parseInt(request.getParameter("depth"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
		
		// 원글 DTO(updatePreviousReply를 위함)
		BbsDTO bbs = new BbsDTO();
		bbs.setDepth(depth);
		bbs.setGroupNo(groupNo);
		bbs.setGroupOrder(groupOrder);
		
		// updatePreviousReply 쿼리 실행
		bbsMapper.updatePreviousReply(bbs);
		
		// 답글 DTO
		BbsDTO reply = new BbsDTO();
		reply.setWriter(writer);
		reply.setTitle(title);
		reply.setIp(ip);
		reply.setDepth(depth + 1); 			 // 답글 depth : 원글 depth + 1
		reply.setGroupNo(groupNo); 			 // 답글 groupNo : 원글 groupNo
		reply.setGroupOrder(groupOrder + 1); // 답글 groupOrder : 원글 groupOrder + 1
		
		// insertReply 쿼리 실행
		bbsMapper.insertReply(reply);
		
		return 0;
	}

	@Override
	public int removeBbs(int bbsNo) {
		return bbsMapper.deleteBbs(bbsNo);
	}

}
