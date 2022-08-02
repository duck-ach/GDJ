package ex13_abstract;

public class Main {

	public static void main(String[] args) {
		
		// s1는 진짜 도형이아니다. 모양도모르고, 넓이도모른다.
		// Shape 클래스타입의 객체는 존재할 수 없는 객체이다.
		// 생성을 막아주는 것이 좋다. (abstract 처리)
		
		// 추상클래스 객체는 못만든다.
//		Shape s1 = new Shape("도형");
//		System.out.println(s1.getType());
//		System.out.println(s1.getArea());
		
		Shape s2 = new Circle("원", 1);
		System.out.println(s2.getType());
		System.out.println(s2.getArea());

	}

}
