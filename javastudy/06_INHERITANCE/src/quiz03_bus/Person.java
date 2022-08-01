package quiz03_bus;

public class Person {
	
	// 상속의 기본 부모클래스는 공통여부를 만들어준다.
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
