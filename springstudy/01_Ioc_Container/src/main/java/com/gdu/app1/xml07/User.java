package com.gdu.app1.xml07;

public class User {

	// field
	private String id;
	private Contact contact;

	// constructor
	public User(String id, Contact contact) {
		super();
		this.id = id;
		this.contact = contact;
	}
	
	// method
	public void info() {
		System.out.println("¾ÆÀÌµð : " + id);
		contact.info();
	}
	
	
	
}
