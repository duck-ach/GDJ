package ex03_singleton;

public class Main {

	public static void main(String[] args) {
		
		
		// singleton 객체는 하나만 생성된다.
		
		User user1 = User.getInstance();
		System.out.println(user1); // 주소값 : ex03_singleton.User@26f0a63f
		
		User user2 = User.getInstance();
		System.out.println(user2); // 주소값 : ex03_singleton.User@26f0a63f

		
		// User user = new User(); 외부에서는 새로운 객체를 생성할 수 없다.
	}

}
