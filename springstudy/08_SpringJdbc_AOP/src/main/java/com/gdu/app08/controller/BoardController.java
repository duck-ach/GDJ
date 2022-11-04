package com.gdu.app08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app08.domain.BoardDTO;
import com.gdu.app08.service.BoardService;

@Controller
public class BoardController {
	
	// Contoller는 Service를 사용합니다.
	// @Autowired // field만 선언하면 null이므로, container의 값을 가져오기 위해서 @Autowired를 사용한다.
	private BoardService boardService;
	// 쓰는 사람은 있는데 굳이?.. 써야하나 하는 위와 같은 코드
//	AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BoardAppContext.class); // Autowired를 사용하지 않기 위해
//	private BoardDAO dao = ctx.getBean("dao", BoardDAO.class); // 메소드 이름 : bean의 이름, 타입 = 클래스이름
	
	// @Autowired와 같은 역할을 함.
	// 생성자의 매개변수로 Autowired 하여 Bean을 가져오는 방법
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	
	
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
	public String detail(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no
						, Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/detail";
	}
	
	@PostMapping("brd/edit")
	public String edit(int board_no
			         , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/edit";  // board 폴더의 edit.jsp로 forward 
	}
	
	@PostMapping("brd/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);  // modifyBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/detail?board_no=" + board.getBoard_no();
	}
	
	@PostMapping("brd/remove")
	public String remove(int board_no) {
		boardService.removeBoard(board_no); // modifyBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list";
	}
	
	// 트랜잭션 확인을 위해서 testTransaction() 메소드를 호출하는 매핑 작성
	@GetMapping("brd/transaction")
	public String transaction() {
		boardService.testTransaction();
		return "redirect:/brd/list";
	}

}
