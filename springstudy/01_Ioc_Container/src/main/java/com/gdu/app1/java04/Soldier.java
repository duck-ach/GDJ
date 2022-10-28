package com.gdu.app1.java04;

public class Soldier {
	
	// field
	private String name;
	private Gun gun;
	
	// constructor
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	// info()
	public void info() {
		System.out.println("¿Ã∏ß : " + name);
		gun.info();
	}
	
	
}
