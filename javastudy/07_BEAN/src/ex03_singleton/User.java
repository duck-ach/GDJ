package ex03_singleton;

public class User {

	private static User user = new User();
	
	// 데이터베이스 접근 객체를 만들때 사용한다. (중첩이 되면 데이터 무결성에 영향을 미치므로.)
	
	private User() { // 'User의 객체생성은 내부에서만 가능하다.' 는 뜻
		
	}
	
	// static 메소드는 class 메소드라고 부른다.
	// 클래스가 생성될 때 함께 생성되는 메소드.
	// 객체 생성 이전에 만들어지기 때문에 객체로 접근하지 않는다.
	// 클래스 이름으로 접근한다.
	// User user = User.getInstance()
	public static User getInstance() {
		return user;
	}

	
	
	
}
