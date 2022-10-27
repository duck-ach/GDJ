package com.gdu.app1.xml08;

public class BMICalculator {
	
	// field
	private Calculator calc;
	private double height;
	private double weight;
	private double bmi;
	private String health;
	
	// constructor
	public BMICalculator(Calculator calc, double height, double weight) {
		super();
		this.calc = calc;
		this.height = height;
		this.weight = weight;
		bmi = calc.div(weight, calc.div(calc.mul(height, height), 10000));
	    health = (bmi < 20) ? "저체중" : (bmi < 25) ? "정상" : (bmi < 30) ? "과체중" : "비만";
		
		// height와 weight는 BMICalculator 클래스에서 보여줘도되고, Member 클래스에서 보여줘도 된다.
		// bmi와 health는 꼭 BMICalculator에서 보여줘야 함
	}
	
	// info() 메소드
	public void info() {
		System.out.println("BMI : " + bmi);
		System.out.println("Health : " + health);
	}
	
	
	
	
}
