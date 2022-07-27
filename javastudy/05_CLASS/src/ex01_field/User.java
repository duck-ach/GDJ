package ex01_field;

public class User {
	// User를 구성하는 속성(Attribute)이 필드(field)
	// 필드는 일반 변수와 달리 자동으로 초기화 된다.
	
	
	// User 클래스에 소속된 변수
	String id;    			// String = null
	String password;		// null
	String email;			// null
	int point;				// 0					// 모든 사용자(User)는 5개 필드(field)를 가진다.
	boolean isVip;     		// false 				// field 5개. User데이터를 보내면 User가 5개 데이터를 가지고 가는거다.
	
}
