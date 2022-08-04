package ex03_finally;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// fanally 블록
		// 1. try-catch문 마지막에 추가하는 블록
		// 2. 언제나 마지막에 실행되는 블록
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("나이 입력 >>> "); // null이나 다른데이터타입을 입력하면 예외가 발생한다.
			String strAge = sc.nextLine();
			int age = Integer.parseInt(strAge);
			System.out.println(age >= 20 ? "성인" : "미성년자");
//			sc.close();  // 13번에서 예외가 발생했을 경우 바로 catch문으로 가기 때문에 닫지못한다.
		} 
		catch(Exception e) {
			System.out.println("예외 발생");
		}
		finally {
			sc.close();
			System.out.println("finally 블록 실행");
		}
	}

}
