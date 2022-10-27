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
	    health = (bmi < 20) ? "��ü��" : (bmi < 25) ? "����" : (bmi < 30) ? "��ü��" : "��";
		
		// height�� weight�� BMICalculator Ŭ�������� �����൵�ǰ�, Member Ŭ�������� �����൵ �ȴ�.
		// bmi�� health�� �� BMICalculator���� ������� ��
	}
	
	// info() �޼ҵ�
	public void info() {
		System.out.println("BMI : " + bmi);
		System.out.println("Health : " + health);
	}
	
	
	
	
}
