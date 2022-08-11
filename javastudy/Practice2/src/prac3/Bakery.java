package prac3;

import javax.management.RuntimeErrorException;

public class Bakery {
	
	// 필드
	private int price; 	// 빵가격
	private int cnt; 	// 빵개수
	private int money;	// 자본금

	// 생성자
	public Bakery(int price, int cnt, int money) {
		super();
		this.price = price;
		this.cnt = cnt;
		this.money = money;
	}
	
	// 판매
	public BreadAndChange sell(int custMoney) throws RuntimeException {
		
		// 판매불가
		if(custMoney < price) {
			throw new RuntimeException("판매 불가.");
		} 
		// 구매불가
		if(custMoney > price*cnt) {
			throw new RuntimeException("재고 부족");
		}
		
		// 판매할 수 있는 빵은 몇개인가?
		int sellCnt = custMoney / price;
		// 잔돈은 얼마냐?
		int change = custMoney % price;

		// 매장 내부의 변화 처리
		cnt -= sellCnt;
		money += (custMoney - change);
		
		// 고객에게 되돌려줄 빵과 잔돈
		return new BreadAndChange(sellCnt, change);
		
		
//		for(int i = 0; i < money / price ; i++) {
//			this.money += price; // 판매한만큼 돈 받고,
//			 // 판매한만큼 빵갯수 줄이기
//		}
	}
	

	// 정보 확인
	public void info() {
		// 빵 개수 , 자본금
		System.out.println("빵 " + cnt + "개, 자본금 " + money + "원" );
	}
	
}
