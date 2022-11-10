package com.gdu.app11.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component // Component 아래 Service, Repository, Controller
@Getter
public class PageUtil {

	private int page; 		 	// 현재 페이지 (파라미터)
	private int totalRecord; 	// 전체 레코드 개수 (DB)
	private int recordPerPage; 	// 페이지에 표시할 레코드 개수 (임의로 정함)
	private int begin; 		 	// 가져올 목록의 시작 번호 (계산)
	private int end;		 	// 가져올 목록의 마지막 번호 (계산)
	
	private int totalPage; 		// 전체 페이지 개수 (계산)
	private int pagePerBlock = 5; // 블록에 표시할 페이지 개수(임의로 정함)
	private int beginPage; 		// 블록의 시작페이지 번호(계산)
	private int endPage; 		// 블록의 마지막 페이지 번호(계산)
	
	public void setPageUtil(int page, int totalRecord) {
		
		// page, totalRecord 필드 저장
		this.page = page;
		this.totalRecord = totalRecord;
		
		// begin, end 계산
		recordPerPage = 10;
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;
		if(end > totalRecord) { 
			end = totalRecord;
		}
        /* 
	     	원하는 사원번호를 정렬시킨 후, 가상으로 붙여준 rownum기준으로 begin값과 end값 처리 
	     	-> 가지고 오기로 했던 인원수(recordPerPage)만큼 정보를 가지고 온다.
        */
		
		// totalPage 계산
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) { // total페이지가 나누어 떨어지지 않았으면 페이지를 하나 더 추가해준다.
			totalPage++;
		}
		
		// beginPage, endPage 계산
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
	}
	
	public String getPaging(String path) { // 경로는 변수처리
		
		StringBuilder sb =  new StringBuilder();
		
		// 이전 블록 : 1block이 아니면 이전블록이 없다.
		if(beginPage != 1) {
			sb.append("<a class=\"pageNum\" href=\"" + path + "?page=" + (beginPage-1) + "\">◀</a>");
		}
		
		// 페이지번호 : 현재 페이지는 링크가 없다.
		for(int p = beginPage; p <= endPage; p++) {
			if(p == page) {
				sb.append("<p class=\"p\">" + p + "</p>");
			} else {
				sb.append("<a class=\"pageNum\" href=\"" + path + "?page=" + p + "\">" + p + "</a>");
			}
		}
		
		// 다음블록 : 마지막 블록이 아니면 다음블록이 있다.
		if(endPage != totalPage) {
			sb.append("<a class=\"pageNum\" href=\"" + path + "?page=" + (endPage+1) + "\">▶</a>");
		}
		
		
		return sb.toString();
	}
}
