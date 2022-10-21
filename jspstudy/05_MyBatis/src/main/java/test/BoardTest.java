package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import domain.Board;
import repository.BoardDao;


/*
 	JUnit 단위 테스트
 	
 	1. DAO의 메소드 단위로 단위 테스트를 수행한다.
 	2. Service 실행 결과가 "특정값"인 경우 Service를 대상으로 단위 테스트를 수행할 수 있다.
 	3. 프로젝트의 Build Path에서 JUnit 라이브러리를 추가하고 사용한다.
 	4. 테스트 수행
 		프로젝트 실행 : Run - JUnit (서버로 하는게 아니고, 서버 꺼놓고 실행한다. (Controller를 사용하지 못함)
 	5. 주요 애너테이션
 		1) @Test   : 단위 테스트를 수행하는 메소드
 		2) @Before : 단위 테스트 수행 이전에 실행하는 메소드
*/

public class BoardTest {

	@Test
	public void 목록테스트() { // 메소드명으로 한글을 많이 사용한다.
		
		// 목록의 개수가 5개이면 테스트 성공, 아니면 실패
		List<Board> boards = BoardDao.getInstance().selectAllBoards();
		assertEquals(7, boards.size()); // 몇개를 기대했는데 실제론 몇개다.
		
	}
	
	@Test
	public void 상세조회테스트() {
		
//		// 2번 게시글의 제목이 '긴급공지'이면 테스트 성공, 아니면 실패
//		Board board = BoardDao.getInstance().selectBoardByNo(2);
//		assertEquals("긴급공지", board.getTitle());
		
		// 2번 게시글을 가진 게시글이 있으면 테스트 성공, 아니면 실패
		Board board = BoardDao.getInstance().selectBoardByNo(2);
		assertNotNull(board);
		
	}

	@Test
	public void 게시글삽입테스트() {
		// 제목 : 테스트2
		// 내용 : 테스트 내용2
		Board board = new Board();
		board.setTitle("Test");
		board.setContent("테스트입니다.");
		int result = BoardDao.getInstance().insertBoard(board);
		assertEquals(9, result);
		
	}
	
	@Test
	public void 게시글수정테스트() {
		// 제목 : 테스트2
		// 내용 : 테스트 내용2
		Board board = new Board();
		board.setTitle("Test");
		board.setContent("테스트입니다.");
		board.setBoardNo(11);
		int result = BoardDao.getInstance().updateBoard(board);
		assertEquals(1, result);
	}
	
	@Test
	public void 게시글삭제테스트() {
		// 게시글삽입테스트로 삽입한 게시글 삭제
		int result = BoardDao.getInstance().deleteBoard(7);
		assertEquals(11, result);
	}
	
	
	
	
	
}
