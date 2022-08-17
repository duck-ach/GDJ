package ex02_thread;

public class Soldier extends Thread {
	
	private String name;
	private Gun gun;
	
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	public void shoot() {
		System.out.print(name + "이(가) 총을 쐈다! ");
		gun.shoot();
	}
	
	@Override
	public void run() {
		// 1초에 한 발씩 쏘기
		
		try {
			while (gun.getBullet() != 0) {
				shoot();
				Thread.sleep(1000);
			}			
		} catch (InterruptedException e) { // 작업을 하고 있는 도중에 가로채기 당하여 대기를 하는 것
			e.printStackTrace();
		}
		
	}
	
}
