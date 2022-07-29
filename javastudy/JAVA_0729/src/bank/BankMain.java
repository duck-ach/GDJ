package bank;

public class BankMain {

	public static void main(String[] args) {
		Bank me = new Bank("1234", 50000);
		Bank mom = new Bank("4321", 100000);
		
		
		me.deposit(10000);
		mom.withdrawal(10000);
		
		mom.trancsfer(me, 50000);
		
		me.accInfo();
		mom.accInfo();
		
	}

}
