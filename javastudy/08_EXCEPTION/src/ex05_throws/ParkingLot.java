package ex05_throws;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingLot {
	
	// field
	private Car[] cars;
	private int idx;
	private Scanner sc;
	
	// constructor
	public ParkingLot()  {
		sc = new Scanner(System.in);
	}
	
	// method
	public void addCar() throws RuntimeException { // manage한테 던진다.
			if(idx == cars.length) {
				throw new RuntimeException("FULL");
			}
	}
	
	public void deleteCar() throws RuntimeException { // manage한테 던진다.
		 if(idx == 0) {
				throw new RuntimeException("EMPTY");
		 }
	}
	
	public void findCar() throws RuntimeException { // manage한테 던진다.
		if(idx == 0) {
			throw new RuntimeException("EMPTY");
	 }
	}
	
	public void printAllCars() throws RuntimeException { // manage한테 던진다.
		if(idx == 0) {
			throw new RuntimeException("EMPTY");
	 }
	}
	
	public void manage() {
		while(true) {
			try {
				System.out.print("1.추가 2.제거 3.조회 4.전체목록 0.종료 >>> ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1 : addCar(); break;
				case 2 : deleteCar(); break;
				case 3 : findCar(); break;
				case 4 : printAllCars(); break;
				case 0 :  System.out.println("Good Bye"); return;
				default : throw new RuntimeException("Bad Request");
				}
			} catch(InputMismatchException e) {
				sc.next();
				System.out.println("처리 명령은 정수(1~4,0)입니다.");
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			} 
		}
		
		
	}
	
	public static void main(String[] args) {
		try {
			new ParkingLot().manage(); 
		
		} catch(RuntimeException e) {
			
		}
	}
	
}
