package ex02_lambda.sec02;

public interface Car {
	
	// 모든 자동차가 주유소에 기름 넣으러 온다.
	public void addOil();

	/*
	 	이렇게 구현하는 것은 된다. (이름없는 차 생성)
		Car car = new Car() {
			public void addOil() {
			
			}
		}
	*/
}
