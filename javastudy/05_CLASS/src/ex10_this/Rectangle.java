package ex10_this;

public class Rectangle {

	// 필드
	private int width;
	private int height;
	
	// 생성자
	public Rectangle(int width, int height) {	 // <- 밑의 this(n, n)이 가리키는 것.
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(int n) {
//		this.width = n;
//		this.height = n;
		
		this(n, n);  //인수 2개인 다른 생성자를 호출한다.
		// 여기서 this란, (나말고 다른) 생성자를 이야기 하는것이다.
	}
	
	
	
	// 메소드
	public int getArea() {
		return width * height;
	}
	public int getCircumference() {
		return 2 * (width + height);
	}
	
	
}
