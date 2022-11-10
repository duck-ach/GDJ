package com.gdu.app12.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app12.domain.BbsDTO;
import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service // Bean 등록
public class BbsServiceImpl implements BbsService {

	private BbsMapper bbsMapper;
	private PageUtil pageUtil;
	
	@Override
	public void findAllBbsList(HttpServletRequest request, Model model) {
		
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
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String ip = request.getRemoteAddr();
		
		BbsDTO bbs = new BbsDTO();
		bbs.setWriter(writer);
		bbs.setTitle(title);
		bbs.setIp(ip);
		
		return bbsMapper.insertBbs(bbs);
	}

	@Override
	public int addReply(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBbs(int bbsNo) {
		return bbsMapper.deleteBbs(bbsNo);
	}

}
