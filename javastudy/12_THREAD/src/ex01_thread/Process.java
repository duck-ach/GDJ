package ex01_thread;

public class Process extends Thread {
	
	private String name;

	public Process(String name) {
		super();
		this.name = name;
	}
	
	@Override // 오버라이드 애너테이션 추가
	public void run() {
		// millies = 1/1000
		try {
			Thread.sleep(3000); // 1000 = 1초, 3초 일시중지
			System.out.println(name + " 작업 실행");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
