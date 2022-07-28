package ex05_method;

public class Car {
	
	// 필드(field)
	int oil;
	int speed;
	
	// 메소드(method)
	
	// 1) 기름 넣기 
	// 반환 타입 : void (반환값이 없다)
	// 메소드명 : addOil
	// 매개변수 : int o
	
	//문제. 이 자동차는 기름통이 60짜리이다.
	// 더 많이 넣더라도 60만 들어가게 해야합니다.
	void addOil(int o) {
		oil += o;
		if(oil > 60) {
			oil = 60;
		}
	}

	// 2) 달리기
	// 반환타입 : void
	// 메소드명 : pushAccel
	// 매개변수 : X
		
	void pushAccel() {
	 	// 속도는 25씩 증가, 최대 속도 120
		// 기름은 1씩 사용
		
//		for(int n = 0; n < oil; n++) {
//			speed += 25;
//				if(speed > 120) {
//					speed = 120;
//				}
//		}
		if(oil == 0 ) {
			return; // 메소드 중지기능. 
					// void일때만 사용 가능
		}
		if(speed == 120) {
			oil--;
			return;
		}
		speed += 25;
		if(speed > 120) {
			speed = 120;
		}
		oil--;
		
	}
	
	// 3) 브레이크
	// 기름은 안줄고
	// 속도는 25씩 줄어든다.
	void pushBrake() {
		
		
		if(speed == 0) {
			return;
		}
		
		speed -= 25;
		
		if(speed < 0) {
			speed = 0;
		}
	}
	
	// 4) 계기판(기름, 속도) 확인
	// 반환타입 : void
	// 메소드명 : panel
	// 매개변수 : X
	
	void panel() {
		System.out.println("기름 " + oil);
		System.out.println("속도 " + speed);
		
	}
	
	
	


}

