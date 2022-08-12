package ex04_outputstream;

import java.io.Serializable;

// Stream 을 이용해서 객체를 전송하려면 직렬화를 해야한다.
// 뭉쳐있는 데이터를 직렬화(행진하듯이 1렬로 차례차례)
// 직렬화가 필요한 객체는 Serializable 인터페이스를 구현해야한다.
// Serializable 인터페이스를 구현한 클래스는 serialVersionUID 필드가 필요하다.
// 예외처리할때 받던건데, 예외처리와 상관없이 스트림을 이용하기 위해 인터페이스를 구현한다.

public class User implements Serializable {
	
	private static final long serialVersionUID = -1830845902387248224L; // 직렬화 시켰던 애들을 다시 너네 하나였구나 하면서 인증키를 부여한 것.
	
	private int userNo;
	private String name;
	private int age;
	
	public User(int userNo, String name, int age) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", name=" + name + ", age=" + age + "]";
	}
	
}
