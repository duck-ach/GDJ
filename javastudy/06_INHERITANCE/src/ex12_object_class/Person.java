package ex12_object_class;

public class Person {
	
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void eat() {
		System.out.println("먹는다.");
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

	@Override
	public boolean equals(Object anObject) { // p1.equals(p2)에서 사용된다.
		Person p = (Person) anObject; // 다운캐스팅 해준다.
		return name.equals(p.name); 
			// p1.equals(p2);
	}
	
	
	
	
}
