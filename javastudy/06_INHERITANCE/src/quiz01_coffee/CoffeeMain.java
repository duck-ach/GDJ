package quiz01_coffee;

public class CoffeeMain {

	public static void main(String[] args) {
		
		Espresso espresso = new Espresso("케냐", 50);
		espresso.info();
		
		System.out.println();
		
		Americano americano = new Americano("케냐", 300, "아이스");
		americano.info();

	}

}
