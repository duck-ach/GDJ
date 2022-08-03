package ex14_interface;

public class Main {

	public static void main(String[] args) {
		
		Shape s = new Circle(1);
		System.out.println(s.getArea());
		

	}

}
// 인터페이스 구현하라 = 상속받아서 구현해라.
// 인터페이스는 상속받는다고 하지 않고, 구현한다고 표현한다.