package fightman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
	private String name;
	private int power;
	private int hp = 200;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}