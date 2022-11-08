package com.gdu.app09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app09.domain.BoardDTO;
import com.gdu.app09.service.BoardService;

@Controller
public class BoardController {
	
	// 생성자를 삭제하고 @Autowired를 넣음
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	

	// Forward할 때 Spring에서는 Model을 사용한다.
	@GetMapping("brd/list") // Mapping이름 구분 하라고 일부러 brd로 바꾸심
	public String list(Model model) { // Model은 forward할 속성(Attribute)을 저장할 때 사용한다.
		model.addAttribute("boards", boardService.findAllBoards()); // Service 실행결과를 불러온다.
		return "board/list"; // board 폴더의 list.jsp
	}
	
	@GetMapping("brd/write")
	public String write() {
		return "board/write"; // board 폴더의 write.jsp로 forward
	}
	
	/*
	 	파라미터 받는 3가지
	 	1. request로 받자.
	 	2. 각각의 변수로 받자.
	 	3. boardDTO (Bean으로 받자)
	*/
	@PostMapping("brd/add")
	public String add(BoardDTO board) { // Insert, update, delete = Redirect
		boardService.saveBoard(board); // saveBoard()로 부터 0/1이 반환되지만 처리되지 않았다.
		return "redirect:/brd/list"; // 까먹으면 죽음뿐 : redirect뒤에는 jsp주소가 아닌 요청 주소 값을 적어주어야 함
	}
	
	@GetMapping("brd/detail")
	public String detail(@RequestParam(value="boardNo", required = false, defaultValue = "0") int boardNo
						, Model model) {
		model.addAttribute("board", boardService.findBoardByNo(boardNo));
		return "board/detail";
	}
	
	@PostMapping("brd/edit")
	public String edit(int boardNo
			         , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(boardNo));
		return "board/edit";  // board 폴더의 edit.jsp로 forward 
	}
	
	@PostMapping("brd/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);  // modifyBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/detail?board_no=" + board.getBoardNo();
	}
	
	@PostMapping("brd/remove")
	public String remove(int boardNo) {
		boardService.removeBoard(boardNo); // modifyBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list";
	}
	
	// 트랜잭션확인하는 것 제거

}
