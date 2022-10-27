package com.gdu.app1.xml08;

import java.util.List;

public class Member {

	// field
	private String name;
	private List<String> course; // ��ϰ���(�ｺ, ����, ���Ǵ�, �ʶ��׽� ��)
	private double height;
	private double weight;
	private BMICalculator bmiCalc;
	
	

	// constructor
	public Member(String name, List<String> course, double height, double weight, BMICalculator bmiCalc) {
		super();
		this.name = name;
		this.course = course;
		this.height = height;
		this.weight = weight;
		this.bmiCalc = bmiCalc;
	}


	// info() method
	public void info() {
		System.out.println("Name : " + name);
		for(String str : course) {
			System.out.println("Course : " + course);
		}
		System.out.println("Height : " + height);
		System.out.println("Weight : " + weight);
		bmiCalc.info();
	}
	
	
}
