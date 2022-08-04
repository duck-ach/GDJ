package ex01_exception;

import java.util.Scanner;

public class Main {
	
	
	public static void m1() { }
	public static void m2() { }
	public static void m3() { 
		//NumberFormatException : String을 Number타입으로 변경할 때 발생
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 입력(필수) >>> ");
		String name = sc.nextLine();
		
		System.out.print("나이 입력(선택) >>> ");
		String strAge = sc.nextLine(); // 입력없이 Enter만 누르면 strAge는 빈 문자열을 가진다.
		int age;
		if(strAge.isEmpty()) {
			age = 0;
		} else {
			age = Integer.parseInt(strAge);
		}
//		int age = Integer.parseInt(strAge);
		System.out.println("이름 : " + name + ", 나이 : " + age + "살");
		
	}
	
	public static void main(String[] args) {
		
		m3();
		
		
	}

}
