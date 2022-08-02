package ex11_downcasting;

public class Main {

	public static void main(String[] args) {
		
//		String[] cars = new String[10];
//		String[] type = {"Car", "Ev", "Hybrid"};
//		
//		Car c = new Hybrid();
//		
//		//랜덤
//		for(int i = 0; i < cars.length; i++) {
//			int j = (int)(Math.random() * 3) + 0;
//			cars[i] = type[j];
////			System.out.println(cars[i]);
//		
//			if(cars[i] == type[0]) {
//				if(c instanceof Car) {
//					c.drive();
//				}
//			} else if (cars[i] == type[1]) {
//				if(c instanceof Ev) {
//					((Ev) c).charge();
//				}
//			} else {
//				if(c instanceof Hybrid) {
//					((Hybrid) c).addOil();
//				}
//			}
//		
//		}
		
		
		
		Car[] cars = new Car[10];
		for(int i=0; i < cars.length; i++) {
			if(Math.random() < 0.33) {
				cars[i] = new Car();
			} else if (Math.random() < 0.66) {
				cars[i] = new Ev();
			} else {
				cars[i] = new Hybrid();
			}

		}			
		
		/*
		   Car 이면 drive()호출 
		   Ev이면 charge()호출 
		   Hybrid이면 addOil()호출
		 */
		
		// 부모와 자식간의 관계라면 자식부터 호출해주어야
		// 상속관계에 얽메이지 않는다.
		// 만약 부모부터 호출한다면 모두 부모로 볼것이다.
		
		for(int i = 0; i < cars.length; i++) {
			if(cars[i] instanceof Hybrid) {
				((Hybrid) cars[i]).addOil();
			} else if(cars[i] instanceof Ev) {
				((Ev) cars[i]).charge();
			} else if(cars[i] instanceof Car) {
				cars[i].drive();
			} 
		}

	}

}
