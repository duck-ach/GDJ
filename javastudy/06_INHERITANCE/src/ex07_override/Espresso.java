package ex07_override;

public class Espresso extends Coffee {
	
	// Override(오버라이드)의 핵심은 Super와 Sub에 똑같은 메소드가 있어야한다.
	@Override
	public void taste() {
		System.out.println("쓰다.");
	}
}
