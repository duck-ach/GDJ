package ex02_lambda.sec01;

/*
	람다식(lambda)
	
	1. 익명 구현 객체를 생성하기 위한 표현식
	2. 이름이 없는 익명 함수를 사용함
	3. 실행(runtime)할 때 익명 구현 객체가 만들어지면서 동작함
	4. 형식
		(매개변수) -> { 메소드본문 }
	5. 예시
	   1) (int a) -> { System.out.println(a); }
	   2) (a) -> { System.out.println(a); } 		매개변수의 타입은 작성하지 않는다. (형식이 정해져 있지 않다. 언제든지 바뀔 수 있음)
	   3) a -> System.out.println(a); 				매개변수가 하나이거나, 메소드 본문이 하나이면 중괄호를 생략한다. (2번과 3번은 똑같은 코드이다.)
	   4) () -> System.out.println("Hello"); 		매개변수가 없으면 빈 괄호를 작성한다.
	   5) (a, b) -> {
	   	  System.out.println(a + b);
	   	  return a + b;								반환타입의 명시를 하지 않는다. (매개변수도 정해져있지 않기 때문에. 언지든지 바뀔 수 있음)
	   }
	   6) (a, b) -> a + b; 							5)와 같은 식. 실행문이 하나여서 중괄호를 생략했으나, return만 있는 경우 return을 작성하지 않는다.
*/

/*
	추상메소드가 하나 있을 때 사용
	functional interface들은 lambda로 만들어 낼 수 있다.
*/
public class Main {

	public static void main(String[] args) {
		
		/*
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("HELLO WORLD!");
				}
			});
			이 코드를 람다식으로 고쳐보겠다.
		*/
	
		// run() 메소드만 람다식으로 바꾸면, Runnable 익명 구현 객체는 실행할 때 만들어진다.
		Thread thread = new Thread(() -> System.out.println("HELLO WORLD!"));
		thread.start();
		
	}

}
