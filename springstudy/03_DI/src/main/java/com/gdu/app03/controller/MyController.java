package com.gdu.app03.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app03.domain.Board;
import com.gdu.app03.domain.Notice;

@Controller // Controller�� Setvlet���� �۾��� �ʿ���� Controller�� �ȴ�.
public class MyController {

	/*
		@RequestMapping(value="/", method=RequestMethod.GET)
		@RequestMapping(value="/", method=RequestMethod.POST)
		
		Spring4���� ���ο� �ֳ����̼�(Annotation)���� �ٲ� �� �ִ�.
		
		@GetMapping("/")
		@PostMapping("/")
	*/
	@RequestMapping("/") // ContextPath�� ��û�Ǹ� �̶�� �� [ http://localhost:9090/app03���� �����ϸ� ó���Ǵ� �޼ҵ� ]
	public String welcome() {
		return "default";
		// ViewResolver�� ���ؼ� 
		// return "/WEB-INF/views/default.jsp"�� �ؼ�
	}
	
	/*
		Container�� ��ϵ� Bean�� ��������� ��� (����¹������, ��������� ��)
		
		@Inject    : 
			1) Ÿ��(Class)�� ��ġ�ϴ� Bean�� ������ ���� �ֳ����̼�
			2) ���� Ÿ���� ���� �� �ִ� ��� @Qualifier�� �̿��ؼ� Bean�� �ĺ�
		@Resource  :
			1) �̸�(id)�� ��ġ�ϴ� Bean�� ������ ���� �ֳ����̼�
		@Autowired : 
			1) Ÿ��(class)�� ��ġ�ϴ� Bean�� ������ ���� �ֳ����̼�
			2) ���� Ÿ���� ���� �� �ִ� ��� �ڵ����� @Qualifier�� �����ؼ� Bean�� �ĺ�
			3) �ǹ����� �ַ� ���
	*/
	/*
		@Autowired ��� ���
		
		1. �ʵ�� ������ Bean ��������
			1) �ʵ帶�� @Autowired�� �߰��Ѵ�. (�ʵ尡 10���� @Autowired�� 10�� �ۼ�)
			2) �ʵ尡 ������ ������� �ʴ´�.
		2. �����ڸ� �̿��� Bean ��������
			1) �������� �Ű������� Bean�� �����´�.
			2) @Autowired�� �ۼ��� �ʿ䰡 ����.
		3. �޼ҵ带 �̿��� Bean ��������
			1) �޼ҵ��� �Ű������� Bean�� �����´�.
			2) @Autowired�� �ۼ��ؾ� �Ѵ�.
			3) �Ϲ������� setter�� ��������� setter�� �ƴϾ ��� ����.
	*/
	
	// 1. �ʵ�� ������ Bean �������� (Container�� Bean�� �����ͼ� ����� ���� ��)
	// Autowired��� �ְ� Bean�� ã�Ƽ� �ڵ����� ���� �־��ִ� ��(getter/setter)���� ����
/*	
 	@Autowired
	private Board board; 
*/
	
	
	/*
		@Autowired�� Ÿ��(class)�� ��ġ�ϴ� Bean�� Container���� �����´�.
		
		@Autowired
		private Board board; // Ÿ���� Board�� Bean�� Container���� ��������
		
		--- Container ---------------------------
		<bean id="board1" class="Board">
		<!-- <bean id="board2" class="Board"> -->
		-----------------------------------------
	*/
	
	// 2. �����ڸ� �̿��� Bean ��������
/*
	private Board board;
	
	public MyController(Board board) { // �Ű����� Board board�� Ÿ���� Board�� Bean�� Container���� �������Ŷ�.
		super();
		this.board = board;
	}
*/
	
//	// 3. �޼ҵ带 �̿��� Bean ��������
//	private Board board;
//	// setter�� ����
//	@Autowired // �Ϲ� �޼ҵ�� @Autowired�� �ݵ�� �ۼ��ؾ� �Ѵ�.
//	public void �޼ҵ��̸�������̾���(Board board) { // �Ű����� Board board�� Board Ÿ���� Bean�� Container���� 
//		this.board = board;
//	}


	/*
	  	Autowired�� class�� ���� ��ü�� ã�´�.
	  	root-content.xml ������ Board ��ü�� 2���� �ֱ� ������, Ÿ��(class)�� ���� ã�°��� ���з� ������. (plan A)
	  	�׷� ���� plan B �ܰ�� ���� id(������)�� �������� �ȴ�. (�׷��� �Ʒ� Autowired���� �������� board1�� �ƴ� b1���� �ٲٰԵǸ� ã�����Ͽ� ������ ���.)
	 */
	
	// 4. ������ Ÿ���� Bean�� ���� �� ��ϵ� ���
	// 	  1) �������� �ڵ����� �ĺ���(Qualifier)�� �ν��Ѵ�.
	// 	  2) �ĺ���(@Qualifier)�� Bean�� �̸�(id)�� ��ġ�ϴ� Bean�� �����´�.
//	@Autowired
//	private Board board1;
//	
//	@Autowired
//	private Board board2;

	// 4-2. �����ڸ� �̿��� Bean ��������
//	private Board b1;
//	private Board b2;
//	
//	public MyController(Board board1, Board board2) { // �Ű��������� Bean�� �̸�(id)�� ��ġ�ϹǷ� �ڵ����� ���Եȴ�.
//		b1 = board1;
//		b2 = board2;
//	}
	
	// 4-3. �޼ҵ带 �̿��� Bean ��������
	private Board b1;
	private Board b2;
	
//	@Autowired
//	public void setB1(Board board1) {
//		this.b1 = board1;
//	}
//	@Autowired
//	public void setB2(Board board2) {
//		this.b2 = board2;
//	}
	// ��ó�� �޼ҵ带 2�� ���������� ������� �Ʒ��Ͱ��� �ϳ��� �����ع�����.
	@Autowired
	public void setBoard(Board board1, Board board2) {
		this.b1 = board1;
		this.b2 = board2;
	}
	

	/*
		@Inject : ���� Ÿ��(class)�� Ȯ��. ���� Ÿ���� �ټ� �߰ߵǸ� @Qualifier�� ���ؼ� �̸�(id)�� ����.
					@Qualifier�� ������ ���� ����
		@Resource : �̸�(id)���� Ȯ��
		@Autowired : �ϴ� Ÿ��(class) Ȯ��. ���� Ÿ���� �ټ� �߰ߵǸ� �ڵ����� �������� @Qualifier�� �ν��ؼ� �̸�(id)�� Ȯ��
		
		Inject�� �Ⱦ��� ������ Qualifier�۾��� ���ε��� ���־�� �ؼ� �� �Ⱦ���.
		Autowired�� Qualifier�۾��� ����Ǿ� �ֱ� ������ 2������ �׷� �ʿ䰡 ���� ������ �� ���δ�.
	*/
	
	// ��û�� ����� Ȯ��
	@GetMapping("board/detail") // @GetMapping("/board/detail")
	public void boardDetail() {
		System.out.println(b1.getBoardNo());
		System.out.println(b1.getTitle());
		System.out.println(b1.getCreateDate());
		System.out.println(b2.getBoardNo());
		System.out.println(b2.getTitle());
		System.out.println(b2.getCreateDate());
	}
	
// @Autowired�� ����ϴ� ���� : @Inject + @Qualifier
/*
	@Inject
	@Qualifier(value="board1") // Qualifier�� ���� board1�� ã�ƶ� �ϴ°Ŵ�.
	private Board b1;
	@Inject
	@Qualifier(value="board2") // Qualifier�� ���� board1�� ã�ƶ� �ϴ°Ŵ�.
	private Board b2;
*/
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 1. field�� �̿��� Bean ��������
//	@Autowired
//	private Notice notice1;
//	
//	@Autowired
//	private Notice notice2;
	
	// 2. �����ڸ� �̿��� Bean ��������
//	@Autowired
//	private Notice notice1;
//	@Autowired
//	private Notice notice2;
//	
//	public MyController(Notice notice1, Notice notice2) {
//		super();
//		this.notice1 = notice1;
//		this.notice2 = notice2;
//	}

	
	// 3. �޼ҵ带 �̿��� Bean ��������
	private Notice noti1;
	private Notice noti2;
	@Autowired
	public void setNotice(Notice notice1, Notice notice2) {
		this.noti1 = notice1;
		this.noti2 = notice2;
	}

	
	@GetMapping("notice/detail")
	public void noticeDetail() {
		System.out.println(noti1.getNoticeNo());
		System.out.println(noti1.getTitle());
		System.out.println(noti2.getNoticeNo());
		System.out.println(noti2.getTitle());
	}
	
	
	
}
