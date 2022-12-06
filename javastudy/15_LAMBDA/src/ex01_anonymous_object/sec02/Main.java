package ex01_anonymous_object.sec02;

public class Main {

	public static void main(String[] args) {
		
		Soil soil = new Soil();
		
		soil.getCar().addOil(); // 자동차 기름넣기
		
		System.out.println(soil.getTotalOil());
		System.out.println(soil.getEarning());

	}

}
