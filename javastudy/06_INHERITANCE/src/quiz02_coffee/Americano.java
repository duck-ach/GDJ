package quiz02_coffee;

public class Americano {
	
	private Espresso espresso;
	private int shot;
	private String type;
	
	public Americano(Espresso espresso, int shot, String type) {
		super();
		this.espresso = espresso;
		this.shot = shot;
		this.type = type;
	}
	
	public void info() {
		espresso.info();
		System.out.print(shot + "샷, ");
		System.out.print(type + " 아메리카노");
	}
	
	
	


	
	
}
