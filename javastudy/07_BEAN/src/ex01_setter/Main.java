package ex01_setter;

public class Main {

	public static void main(String[] args) {
		
		// BEAN - 값을 가지고있는 객체 (VO, DTO, BEAN)
		
		User user = new User();
		user.setUserNo(1);
		user.setId("admin");
		user.setEmail("admin@naver.com");
		
		System.out.println(user);
		
	}

}