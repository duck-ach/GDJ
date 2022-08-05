package quiz03_bank;

public class Bank {
	
	// 필드
	private String accNo;
	private long balance;
	
	public Bank(String accNo, long balance) {
		super();
		this.accNo = accNo;
		this.balance = balance;
	}
	
	// 입금
	public void deposit(long money) throws BankException {
		if(money < 0) {
			throw new BankException("마이너스 입금 불가", 1);
		}
		balance += money;
	}
	
	// 출금
	public long withdrawal(long money) throws BankException {
		if(money < 0) {
			throw new BankException("마이너스 출금 불가", 2);
		} else if(money > balance) {
			throw new BankException("잔액부족", 3);
		}
		balance -= money;
		return money;
	}
	
	// 이체
	public void transfer(Bank acc, long money) throws BankException {
		acc.deposit(withdrawal(money)); 
	}
	
	// 출력
	public void inquiry() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Bank [accNo=" + accNo + ", balance=" + balance + "]";
	}
	
	
	
	
	
}
