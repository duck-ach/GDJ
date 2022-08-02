package ex13_abstract;

public class Circle extends Shape {
	private double radius;

	public Circle(String type, double radius) {
		super(type);
		this.radius = radius;
	}
	
	// Shape 클래스는 추상 클래스이므로, 반드시 double getArea() 메소드를 오버라이드 해야한다.
	@Override 
	public double getArea() { 	// Shape를 상속 받는 객체들이 호출할 때 사용하는 메소드
								// 사용되지는 않는다. -> 추상메소드로 바꿔준다. (본문이 없는 메소드)
		return Math.PI * Math.pow(radius, 2); // πr(2)
	}

}
