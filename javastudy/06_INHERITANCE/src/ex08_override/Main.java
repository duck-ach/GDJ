package ex08_override;

public class Main {

	public static void main(String[] args) {
		
		Circle circle = new Circle("도넛", 7.5);
		circle.info();

		Rectangle rectangle = new Rectangle("조금 기다란 네모", 3, 4);
		rectangle.info();
		
		Square square = new Square("높이와 길이가 같은 반듯한 네모", 5);
		square.info();
	}

}
