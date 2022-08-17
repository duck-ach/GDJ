package ex04_join;

public class Main {

	public static void main(String[] args) throws InterruptedException {
	
		// Calculator를 2개 준비
		// 작업을 반으로 나눠서 진행한다.
		
		// Calculator가 동시에 연산을 수행하려면 스레드로 처리해야 한다.
		
		Calculator calc1 = new Calculator(1, 5000);
		Thread thread1 = new Thread(calc1);
		thread1.start();
		
		Calculator calc2 = new Calculator(5001, 10000);
		Thread thread2 = new Thread(calc2);
		thread2.start();
				
//		Thread.sleep(1000);
		
		// 모든 계산기의 동작이 끝날때까지 기다린다.
		// 스레드가 종료(die)될 때까지 기다린다.
		thread1.join();
		thread2.join();
		
		System.out.println(thread1.isAlive()); // true
		System.out.println(thread2.isAlive()); // false 살아있는가?
		
		System.out.println(calc1.getTotal() + calc2.getTotal());
		

	}

}
