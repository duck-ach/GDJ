package quiz02_coffee;

public class Coffee {
	private String origin;

	public Coffee(String origin) {
		super();
		this.origin = origin;
	}

	public void info() {
		System.out.print(origin + " 원두 , ");
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
