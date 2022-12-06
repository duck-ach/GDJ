package ex02_lambda.sec04;

public class Soil {

	private int totalOil = 1000; 	// 총 기름
	private int payPerLiter = 2000; // 기름에 비례한 돈
	private int earning; 			// 얼마벌었는지
	
	// 3. 메소드의 매개변수로 익명 객체 저장하기
	public void sellOil(Car car) { // 차를 받아와서 기름을 팔음
		car.addOil();
	}
	
	
	public int getTotalOil() {
		return totalOil;
	}

	public void setTotalOil(int totalOil) {
		this.totalOil = totalOil;
	}

	public int getPayPerLiter() {
		return payPerLiter;
	}

	public void setPayPerLiter(int payPerLiter) {
		this.payPerLiter = payPerLiter;
	}

	public int getEarning() {
		return earning;
	}

	public void setEarning(int earning) {
		this.earning = earning;
	}
	
}
