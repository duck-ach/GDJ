package prac3;

import java.util.InputMismatchException;

public class Customer {
	
	// 필드
	private int money; 	// 고객이 가진 돈
	private int cnt; 	// 구매한 빵의 갯수
	
	// 생성자
	public Customer(int money) {
		this.money = money;
	}
	
	// 구매
	// Bakery에서 판매한 빵을 가질 수 있다. (Bakery의 sell()메소드를 사용) 
	public void buy(Bakery bakery, int buyMoney) throws RuntimeException {
		
		if(money - buyMoney < 0) {
			throw new RuntimeException("구매 불가.");
		}
		
		BreadAndChange bnc = bakery.sell(buyMoney);
		
		// 고객이 구매한 빵
		cnt += bnc.getBread();
		// 고객의 남은 거스름 돈
		money += bnc.getChange();
		// 구매한 돈 까기
		money -= buyMoney;
		
		System.out.println("구매한 빵 " + cnt + "개, 남은 돈 " + money + "원");
	}

}
