package com.gdu.app1.java01;

public class Singer {

	// field
	private String name;
	private Song song; // ¥Î«•∞Ó 1∞≥
	
	// constructor
	public Singer() {
		// TODO Auto-generated constructor stub
	}
	public Singer(String name, Song song) {
		super();
		this.name = name;
		this.song = song;
	}
	
	// getter/setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	
	
	
}
