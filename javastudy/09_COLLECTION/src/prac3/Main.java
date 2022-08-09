package prac3;

public class Main {

	public static void main(String[] args) {
		
		Bakery paris = new Bakery(500, 50, 10000); // 빵 1개 500원, 빵 100개, 자본금 10000원
		Bakery tour = new Bakery(1000, 50, 10000); // 빵 1개 1000원, 빵 50개, 자본금 10000원
		
		Customer customer = new Customer(10000); // 20000원 가진 고객
		
		try { // 구매만 try가 나오면 된다. 다른건 예외가 없을거기 때문에
		customer.buy(paris, 10000); // paris에 남은 빵 80개, 자본금 20000원
		customer.buy(tour, 500); // tour에 남은 빵 45개, 자본금 15000원
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		paris.info(); //빵 80개, 자본금 20000원
		tour.info(); // 빵 45개, 자본금 15000원

		
	}

}
