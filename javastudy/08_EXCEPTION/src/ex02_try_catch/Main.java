package ex02_try_catch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void m1() {
		try {
			String[] hobbies = new String[3];
			hobbies[1] = "swimming";
			hobbies[2] = "running";
			
			for(String hobby : hobbies) {
				System.out.println(hobby.substring(0, 2));
			}
		} catch (RuntimeException e) { // RuntimeException, NullPointerException 가능
			System.out.println("NullPointerException 발생");
		}
	}
	
	public static void m2() {
		
		try {
			String input = "20,21,22,23,24,25";
			String[] inputs = input.split(",");
			int[] ages = new int[inputs.length];
			for(int i = 0; i < inputs.length; i++) {
				ages[i] = Integer.parseInt(inputs[i]);
				System.out.println(ages[i]);
			}
		} catch(NumberFormatException e) { // RuntimeException, NullPointException, NumberFormatException
			System.out.println("NumberFormatException 발생");
		} catch(Exception e) {
			
		}
		
			
			
//			System.out.println(ages);
//		int[] ages = {20, 21, 22, 23, 24, 25};
	}
	
	public static void m3() {
		try { 
			Scanner sc = new Scanner(System.in);
		
			System.out.print("정수 1 >>> ");
			int a = sc.nextInt();
			System.out.print("정수 2 >>> ");
			int b = sc.nextInt();
			
			System.out.println(a + "+" + b + "=" + (a + b));
			System.out.println(a + "-" + b + "=" + (a - b));
			System.out.println(a + "*" + b + "=" + (a * b));
			System.out.println(a + "/" + b + "=" + (a / b));
			System.out.println(a + "%" + b + "=" + (a % b));
		} catch (ArithmeticException e) {
			System.out.println("ArithmeticException이 발생했다.");
		} catch (InputMismatchException e) {
			System.out.println("InputMismatchException이 발생했다.");
		}
	}
	
	public static void m4() {
		File file = new File("C:\\sample.txt");
		try {
			FileReader fr = new FileReader(file);
		} catch (FileNotFoundException e) { // Exception, FileNotFoundException
			e.printStackTrace();
		} //checked exception의 특징 : try-catch문이 없으면 실행불가
	}
	public static void main(String[] args) {
		
//		m1();
//		m2();
//		m3();
		m4();

	}

}
