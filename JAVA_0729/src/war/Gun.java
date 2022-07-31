package war;

public class Gun {

	private String model;
	private int bullet = 0;
	private final int MAX_BULLET = 15;
	
	public Gun(String model) {
		this.model = model;
	}
	
	// 장전
		public void reload(int bullet) {
			this.bullet += bullet;
			if(this.bullet > MAX_BULLET)
				this.bullet = MAX_BULLET;
			
			System.out.println(bullet + "발 장전 완료! (총 " + this.bullet + "발)");
		}
		
		// 발사
		public void shoot() {
			if(bullet <= 0) {
				System.out.println("총알이 없다.");
				return;
			}
			bullet--;
			System.out.println("빵! " + bullet + "발 남았다.");
		}
		
		// 조회
		public void info() {
			System.out.println( "총 모델 : " + model + ", 총알 : (" + bullet + ")");
		}
	
}
