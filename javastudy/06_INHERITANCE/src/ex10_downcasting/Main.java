package ex10_downcasting;

public class Main {

	public static void main(String[] args) {
		
		// 클래스타입 		: Person
		// 객체(인스턴스) 	: p
		
		Person p = new Alba(); // 업캐스팅(Up Casting)
		Person q = new Student(); // 업캐스팅(Up Casting)
		
		// instanceof 연산자
		// 특정 인스턴스가 어떤 클래스 타입인지 점검하는 연산자
		// 해당 클래스타입이면 true반환, 아니면 false반환
		
		System.out.println(p instanceof Person);
		System.out.println(p instanceof Student);
		System.out.println(p instanceof Alba);
		System.out.println();
		System.out.println(q instanceof Person);
		System.out.println(q instanceof Student);
		System.out.println(q instanceof Alba);
		
		
		// p가 Student타입의 인스턴스이면 study()
		if(q instanceof Student) {
			((Student) q).study(); // 공부한다. 다운캐스팅 자동완성
		}
		// 캐스팅은 실행하면 무조건바뀌므로 주로 if문 안에 넣어준다.
		// 조심해서사용해야한다. 실행하기전엔 오류가 있는지 모르는경우가 많음.
		
		if(q instanceof Alba) {
			((Alba) q).work();  // Alba타입을 인스턴스로 가지고있지않아서 호출이 되지않는다.
		}
		
		if(p instanceof Alba) {
			((Alba) p).work(); // 일한다.
		}
		
		
	}

}
