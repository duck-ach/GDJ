package ex01_anonymous_object.sec01;

public class Main {

	public static void main(String[] args) {
		
		// 인터페이스는 new를 할 수 없다. : 이름이 있는 객체를 만드는 것
		// 인터페이스는 new를 할 수 있다. : 이름이 없는 객체를 만드는 것

		/*  
			이건 안된다.
			
			Runnable runnable = new Runnable();
			Thread thread = new Thread(runnable);
		*/
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("HELLO WORLD!");
			}
			
		});
		
		// thread 실행
		thread.start(); // run()이 아니고 start()를 돌려야 함
		
		
	}

}
