package ex04_throw;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// throw
		// 1. 예외 객체를 만들어서 직접 throw 할 수 있다.
		// 2. 자바는 예외로 인식하지 않지만 실제로는 예외인 경우에 주로 사용된다.
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("나이 입력 >>> "); // null이나 다른데이터타입을 입력하면 예외가 발생한다.
			String strAge = sc.nextLine();
			int age = Integer.parseInt(strAge);
			if(age < 0 || age > 100) {
				throw new RuntimeException("나이는 0 이상 100 이하만 가능합니다."); // 예외 객체 생성.
			} // throw가 던지면 catch가 받는다.
			System.out.println(age >= 20 ? "성인" : "미성년자");
//			sc.close();  // 13번에서 예외가 발생했을 경우 바로 catch문으로 가기 때문에 닫지못한다.
		} 
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			sc.close();
			System.out.println("finally 블록 실행");
		}
	}

}
