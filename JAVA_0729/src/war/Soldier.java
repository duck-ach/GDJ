package war;

public class Soldier {
	
	private Gun gun;
	private String name;
	
	public Soldier(Gun model, String name) {
		this.gun = model ;
		this.name = name;
	}
	
	public void reload(int bullet) {
		gun.reload(bullet);
	}
	
	public void shoot() {
		gun.shoot();
	}
	
	public void info() {
		gun.info();
		System.out.println("군인 이름 : " + name);
	}
	
	
}
