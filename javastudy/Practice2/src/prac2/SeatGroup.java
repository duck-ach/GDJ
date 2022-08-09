package prac2;

import java.util.Scanner;

public class SeatGroup {
	
	private String seatType; // "S", "R", "A"
	private Seat[] seats;
	private Scanner sc;
	
	public SeatGroup(String seatType, int cnt) {
		this.seatType = seatType;
		seats = new Seat[cnt];
		for(int i = 0; i < cnt; i++) {
			seats[i] = new Seat();
		}
		sc = new Scanner(System.in);
	}
	
	// 예약
	public boolean reserve() {
		reserveInfo();
		System.out.print("예약할 시트번호 >>> ");
		int seatNo = sc.nextInt();
		if(seatNo < 1 || seatNo > seats.length) {
			System.out.println(seatNo + "번 좌석은 없는 좌석입니다.");
			return false;
		}
		// 예약할 시트인지 확인
		if(seats[seatNo - 1].isOccupied()) {
			System.out.println(seatNo + "번 좌석은 이미 예약된 좌석입니다.");
			return false;
		}
		
		// 예약 진행
		System.out.print("예약자 이름 >>> ");
		String name = sc.next();
		seats[seatNo - 1].reserve(name);
		System.out.println(seatNo + "번 좌석 예약이 완료되었습니다.");
		return true;
	}
	
	
	// 예약 취소
	public boolean cancel() {
		reserveInfo();
		System.out.print("취소자 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < seats.length; i++) {
			if(seats[i].isOccupied()) { // 예약된 좌석만 비교
				if(seats[i].isMatched(name)) {
					seats[i].cancel();
					System.out.println("예약자 " + name + "의 예약이 취소되었습니다.");
					return true;
				}
			}
		} // for
		System.out.println(name + "으로 예약된 좌석이 없습니다.");
		return false;
	}
	
	
	// 예약 상황 출력
	public void reserveInfo() {
		System.out.println("[" + seatType + "]");
		for(int i = 0; i < seats.length; i++) {
			if(seats[i].isOccupied()) {
				System.out.print(seats[i].getName().substring(0, 1) + "* "); // 예약자성함
			} else {
				System.out.print((i+1) + (i < 9 ? "   " : "  ")); // 시트번호
			}
			if((i+1) % 10 == 0 ) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
}
