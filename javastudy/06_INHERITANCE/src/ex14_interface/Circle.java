package ex14_interface;

public class Circle implements Shape {
	
	public double radius;
	
	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return  PI * Math.pow(radius, 2);
	}
	
	
	
}
