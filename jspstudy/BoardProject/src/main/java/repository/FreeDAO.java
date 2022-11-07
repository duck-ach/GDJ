package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Free;

public class FreeDAO {

	// SqlSessionFactory 빌드
	private SqlSessionFactory factory;
	
	// singleton pattern
	private static FreeDAO dao = new FreeDAO();
	private FreeDAO() {
		try {
			// SqlSessionFactory 빌드
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static FreeDAO getInstance() {
		return dao;
	}
	
	// 모든 method는 SqlSessionFactory로부터 SqlSession을 얻어서 사용
	// method
	
	// Tip. 메소드 이름을 실행할 쿼리문의 id와 맞추자
	String mapper = "mybatis.mapper.free.";
	// 1. 게시글 목록
	public List<Free> selectAllFrees() {
		SqlSession ss = factory.openSession(); // SELECT(커밋이 필요 없는 경우)
		List<Free> frees = ss.selectList(mapper + "selectAllFrees");
		ss.close();
		return frees;
	}
	
	// 2. 게시글 상세 보기
	public Free selectFreeByNo(long freeNo) {
		SqlSession ss = factory.openSession();
		Free free = ss.selectOne(mapper + "selectFreeByNo", freeNo); // boardNo를 파라미터로 전달
		ss.close();
		return free;
	}
	
	// 3. 게시글 삽입
	public int insertFree(Free free) {
		SqlSession ss = factory.openSession(false); // INSERT(커밋이 필요한 경우)
		int result = ss.insert(mapper + "insertFree", free); // board를 파라미터로
		if(result > 0) {
			ss.commit();
		}
		return result;
	}
	
	// 4. 게시글 삭제
	public int deleteFree(long freeNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(mapper + "deleteFree", freeNo);
		if(result > 0) {
			ss.commit();
		} 
		ss.close();
		return result;
	}
	
	// 5. 게시글 수정
	public int updateFree(Free free) {
		SqlSession ss = factory.openSession(false); // UPDATE(커밋이 필요한 경우)
		int result = ss.update(mapper + "updateFree", free);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
}
