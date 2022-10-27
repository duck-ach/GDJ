package com.gdu.app1.xml07;

public class Contact {
	
	// field
	private String address;
	private String email;
	private String tel;
	
	// constructor
	public Contact(String address, String email, String tel) {
		super();
		this.address = address;
		this.email = email;
		this.tel = tel;
	}
	
	// method
	public void info() {
		System.out.println("林家 : " + address);
		System.out.println("捞皋老 : " + email);
		System.out.println("楷遏贸 : " + tel);
	}
	
}
