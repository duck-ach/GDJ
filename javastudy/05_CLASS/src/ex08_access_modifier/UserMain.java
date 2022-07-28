package ex08_access_modifier;

public class UserMain {

	public static void main(String[] args) {
		
		User user = new User();
//		System.out.println(user.id);
//		user.id = "admin";
		// 필드를 통해 직접 접근하는게 접근제어자로 인해 제한되어 있다.
		
		// ID get, set
		System.out.println("Id");
		System.out.println(user.getId()); // null
		user.setId("admin");
		System.out.println(user.getId()); // admin
		
		System.out.println("PW");
		// Password get, set
		System.out.println(user.getPW());
		user.setPW("12345678");
		System.out.println(user.getPW());
		
		System.out.println("Email");
		// Email get, set
		System.out.println(user.getEmail()); // null
		user.setEmail("djagmlfk@naver.com");
		System.out.println(user.getEmail());
		
		System.out.println("Point");
		// Point get, set
		System.out.println(user.getPoint());
		user.setPoint(13015);
		System.out.println(user.getPoint());
		
		System.out.println("Vip");
		// IsVip get, set
		System.out.println(user.getIsVip());
		
	}

}
