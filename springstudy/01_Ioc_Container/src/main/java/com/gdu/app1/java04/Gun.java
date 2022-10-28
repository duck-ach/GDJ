package com.gdu.app1.java04;

public class Gun {

	// field
	private String model;
	private int bullet;
	
	// constructor
	public Gun(String model, int bullet) {
		super();
		this.model = model;
		this.bullet = bullet;
	}
	
	// info
	public void info() {
		System.out.println("¸ðµ¨¸í : " + model);
		System.out.println("ÃÑ¾Ë¼ö : " + bullet);
	}
	
}
