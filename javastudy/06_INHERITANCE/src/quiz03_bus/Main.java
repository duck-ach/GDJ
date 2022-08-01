package quiz03_bus;

public class Main {

	public static void main(String[] args) {
		
		Bus bus = new Bus(25);
		Seat[] seats = new Seat[25];

		bus.ride(1, new Person("kim"));
		bus.ride(1, new Person("lee"));
		bus.ride(4, new Student("choi"));
		bus.ride(6, new Person("min"));
		bus.info();

	}

}

// 콘서트 예매 프로그램
