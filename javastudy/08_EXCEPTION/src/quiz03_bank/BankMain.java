package quiz03_bank;

public class BankMain {

	public static void main(String[] args) throws BankException {

		Bank me = new Bank("1111", 10_000);
		Bank mom = new Bank("2222", 100_000);
		
		try {
			mom.transfer(me, 1_00_000);
		} catch(BankException e) {
			System.out.println(e.getMessage() + "," + e.getErrorCode());
		}
		
		
		me.inquiry();
		mom.inquiry();
		
	}

}


// try-catch문을 한번도 안 넣을 수 있다.
// 하지만 넣어주는 게 좋다.