package parking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingLot {
	
	// field
	private String name; // 주차장이름
	private Car[] cars; // Car 인스턴스를 저장할 수 있는 배열
	private int idx; // 배열의 인덱스
	private Scanner sc; //키보드 입력을 처리하는 인스턴스
	
	
	// constructor
	public ParkingLot(String name) {
		this.name = name;
		cars = new Car[10];
		sc = new Scanner(System.in);
	}
	
	public void addCar() throws RuntimeException {
		// 주차장에 차량이 가득 찼을때
		if(idx == cars.length) {
			throw new RuntimeException("더 이상 차량 등록이 불가능합니다.");
		}
		// 기본 add 알림&입력
		System.out.println("현재 등록된 차량 " + idx +"대");
		System.out.print("차량번호 >>> ");
		String carNo = sc.nextLine();
		carNo = carNo.replaceAll(" ", "");
		System.out.print("모델 >>> ");
		String carName = sc.nextLine();
		carName = carName.replaceAll(" ", "");
		
		// 차량등록
		Car car = new Car(carNo, carName);
		
//			// 차량번호중복체크
//		for(int i = 0; i < idx; i++) {
//			if(carNo.equals(cars[i].getCarNo())) {
//				System.out.println("이미 등록된 차량입니다.");
//				return;
//			}
//		}
		
		cars[idx++] = car;
		System.out.println("차량번호 " + carName + " 차량이 등록되었습니다.");
	}
	
	public void deleteCar() throws RuntimeException {
		// 주차장이 비어있는 경우
		if(idx == 0) {
			throw new RuntimeException("등록된 차량이 없습니다.");
		}
		// 차량출차
		System.out.print("제거할 차량번호 >>> ");
		String carNo = sc.nextLine();
		carNo = carNo.replaceAll(" ", "");
		for(int i = 0; i <idx; i++) {
			if(carNo.equals(cars[i].getCarNo())) {
				System.arraycopy(cars, i+1, cars, i, idx - i - 1);
				cars[--idx] = null;
				System.out.println("차량번호 " + carNo + " 차량이 삭제되었습니다.");
				return;
			}
		}
		System.out.println("차량번호가" + carNo +"인 차량이존재하지 않습니다.");
		
	}
	
	public void printAllCars() throws RuntimeException {
		// 주차장이 비어있는 경우
		if(idx == 0) {
			throw new RuntimeException("등록된 차량이 없습니다.");
		}
		System.out.println(this.name + " 차량 목록");
		for(int i = 0; i < idx; i++) {
			System.out.println(cars[i]);
		}
	}
	
	public void manage()  {
		
		while(true) {
			try {
				
				// default notice
				System.out.print("1.추가 2.삭제 3.전체 0.종료 >>> ");
				
				// choice number
				int choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1: addCar(); break;
				case 2: deleteCar(); break;
				case 3: printAllCars(); break;
				case 0: System.out.println("주차등록 프로그램을 종료합니다. 감사합니다."); 
						return;
				default: System.out.println("존재하지 않는 메뉴입니다.");
				}
			} catch (InputMismatchException e) {
				System.out.println("메뉴에 알맞은 번호를 입력해 주세요.");
				sc.next();
				manage();
			}
			catch (RuntimeException e) {
					System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println("알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
				sc.next();
				manage();
			}
		}

	}
	
}
