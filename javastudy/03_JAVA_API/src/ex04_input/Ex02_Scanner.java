package ex04_input;

import java.util.Scanner;

public class Ex02_Scanner {

	public static void main(String[] args) {

		// java.util.Scanner 클래스
		// 데이터타입별로 입력 받을 수 있는 메소드를 제공
		// 앞서 한 JOP와 다르게 객체를 생성해야 한다.
		
		// int : nextInt()
		// long : nextLong()
		// double : nextDouble()
		// String : nextLine() - 공백 포함 입력, next() - 공백 포함 불가능

		Scanner sc = new Scanner(System.in); // 객체 sc는 System.in(키보드)으로부터 입력을 받는다.

		System.out.print("이름을 입력하세요 >>> ");
		String name = sc.nextLine();
		
		System.out.print("나이를 입력하세요 >>> ");
		int age = sc.nextInt();
		
		System.out.println(name + "의 나이 : " + age);
		

		sc.close(); // 생략이 가능하다.
	}

}
