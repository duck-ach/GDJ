package ex12_object_class;

public class Main {

	public static void main(String[] args) {
		
		// Object 클래스는 모든 객체(변수)를 저장할 수있다.
		
		// Object는 모든 걸 저장할 수 있다.
		// Object에 저장된 객체는 캐스팅해서 사용한다.
		// 객체 정보 확인을 위해서는 toString() 메소드를 만든다.(Override)
		// 객체 정보 비교를 위해서는 equals() 메소드를 만든다. (Override)
		// - source 메뉴에 가면 toString(), equals()를 만들어준다.
		
		Object p = new Person();
		
		// 오브젝트로 호출을하면 eat()이 호출이 되지 않으므로
		// 다운캐스팅으로 호출해준다.
		// Object클래스타입의 객체는
		// 항상 다운캐스팅해서 사용해야한다. (anyway)
		if(p instanceof Person) {
			((Person) p).eat();
		}
		
		// 새로운 Person (to String() 확인용)
		Person person = new Person();
		person.setName("james");
		
		
		System.out.println(person); // 이름 : "james" // System.out.println(person);에서 사용
		
		// 새로운 Person(equals() 확인용)
		// 목표 : name이 같으면 동일한 객체로 인식하기
		Person p1 = new Person();
		Person p2 = new Person();
		
		p1.setName("kim");
		p2.setName("kim");
		System.out.println(p1.equals(p2)); // 원래 false, 
		// 하지만 Person클래스에서 override를 해서 문자열이 같으면 true를 반환하라고 해주면 true를 반환시킬 수 있다.
		

	}

}
