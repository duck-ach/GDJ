package test;

import static org.junit.Assert.*;

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

/*
	DBCP 설정은 context.xml에 작성되어 있는데, 해당 내용은 Tomcat이 처리한다.
	JUnit 단위 테스트는 Tomcat 없이 실행되므로, BoardDao의 DataSource가 생성되지 않아
	정상적인 테스트가 어렵다.
	
	테스트 수행할 때 Tomcat이 참여할 수 있도록 URL을 통해 접근하는 별도 테스트 코드를 작성한다.
	(테스트를 위해 json 라이브러리를 추가하였다.) 
*/

public class BoardTest {

	@Test
	public void 목록테스트() { // 메소드명으로 한글을 많이 사용한다.
		
		// 목록의 개수가 5개이면 테스트 성공, 아니면 실패
		List<Board> boards = BoardDao.getInstance().selectAllBoard();
		assertEquals(5, boards.size()); // 몇개를 기대했는데 실제론 몇개다.
		
	}

}