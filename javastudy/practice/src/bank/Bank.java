package bank;

public class Bank {
	
	// 필드
	private String accNo;
	private long balance;
	
	// 생성자
	public Bank(String accNo, long balance) { // Bank b = new Bank("1234",50000)
		this.accNo = accNo;
		this.balance = balance;
	}
	
	// 1. 입금
	public void deposit(long money) { // b.deposit(50000);
		// return이려면 void로 지정해야 함.
		if(money <= 0) {
			return; // 반환타입이 void인 메소드를 종료하는 코드
		}
		balance += money;
	}
	// 2. 출금 (마이너스 출금 + 잔액보다 큰 출금 불가)
	// 실제로 출금된 금액을 반환
	public long withdrawal(long money) {
		if(money < 0 || money > balance) {
			return 0;
		}
		balance -= money;
		return money;
	}
	
	// 3. 이체(출금 -> 입금)
	public void trancsfer(Bank other , long money) {
		// 내 통장에서 출금된 금액만큼 상대 계좌에 입금한다.
//		long withdrawalMoney = withdrawal(money);
//		other.deposit(withdrawalMoney);
		
		other.deposit(withdrawal(money));
	}
	
	public void accInfo() {
		System.out.println("계좌번호 : " + accNo + ", 잔액 : " + balance + "원");
	}
	
	
	
}
