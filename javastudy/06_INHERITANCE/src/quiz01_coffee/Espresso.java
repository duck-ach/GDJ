package quiz01_coffee;

public class Espresso extends Coffee{

	private int water;
	

	public Espresso(String origin, int water) {
		super(origin);
		this.water = water;
	}

	public int getWater() {
		return water;
	}
	
	public void info() {
		super.info();
		System.out.print("물" + water + "ml ");
	}
	

	
}
