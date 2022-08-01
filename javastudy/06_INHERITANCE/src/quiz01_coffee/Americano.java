package quiz01_coffee;

public class Americano extends Espresso {
	String tem;

	public Americano(String origin, int water, String tem) {
		super(origin, water);
		this.tem = tem;
	}

	@Override
	public void info() {
		super.info();
		System.out.print(", " + tem + "아메리카노");
	}
	
	

}
