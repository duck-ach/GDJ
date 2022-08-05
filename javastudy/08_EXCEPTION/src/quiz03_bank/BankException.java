package quiz03_bank;

public class BankException extends Exception {

	private static final long serialVersionUID = -3082086743772450915L;
	
	private int errorCode;
	// 마이너스 입금 불가, 코드값 1000
	// 마이너스 출금 불가, 코드값 2000
	// 잔액보다 큰 출금 불가, 코드값 2001

	public BankException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
	
	
}
