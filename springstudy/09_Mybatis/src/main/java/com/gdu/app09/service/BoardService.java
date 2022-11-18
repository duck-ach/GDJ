package com.gdu.app09.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app09.domain.BoardDTO;

public interface BoardService {
	// 서비스 계층의 이름은 "사용자 친화적으로" 작성
	public List<BoardDTO> findAllBoards();
	public int saveBoard(BoardDTO board);
	public BoardDTO findBoardByNo(int boardNo);
	public int modifyBoard(BoardDTO board);
	public int removeBoard(int boardNo);
}
