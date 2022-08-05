package quiz04_employee;

public class Temporary extends Employee {
	
	private int pay; 		// 페이
	private int workTimes;	// 일하는 시간
	
	public Temporary(int empNo, String name) {
		super(empNo, name);
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getWorkTimes() {
		return workTimes;
	}

	public void setWorkTimes(int workTimes) {
		this.workTimes = workTimes;
	}
	

	@Override
	public String toString() {
		return super.toString() + ", pay=" + pay + ", workTimes=" + workTimes + "]";
	}
	
	
	
	
}
