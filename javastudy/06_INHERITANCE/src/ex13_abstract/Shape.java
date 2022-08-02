package ex13_abstract;

// 추상 메소드
// 1. 본문이 없는 메소드
// 2. 호출용으로 사용되는 메소드
// 3. 중괄호{} 자체를 없애고 세미콜론(;)을 추가함 
// 4. public abstract(추천) 또는 abstract public 라고 붙이면된다.

// 추상 클래스
// 1. 추상 메소드가 1개 이상 존재하는 클래스
// 2. public abstract class
// 3. 추상 메소드가 1개라도 있다면 클래스도 함께 abstract로 선언해주어야한다.
// 4. 본문이 없는 메소드를 포함하기 때문에 객체 생성이 불허됨
public abstract class Shape {
	
	// field
	private String type;

	// constructor
	public Shape(String type) {
		super();
		this.type = type;
	}
	
	// method

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	// 모든 도형의 넓이를 구하는 것은 getArea()로 합시다 선언.
	// 어떤도형이 올 지 모르니 return값 0(zero)
	public abstract double getArea(); {
		 
	}
	
	
}
