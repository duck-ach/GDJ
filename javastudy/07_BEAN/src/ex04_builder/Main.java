package ex04_builder;

public class Main {

	public static void main(String[] args) {
		
		User user = User.builder()
				.userNo(1)
				.id("admin")
				.email("admin@gmail.com")
				.build();
		
		System.out.println(user);

	}

}
