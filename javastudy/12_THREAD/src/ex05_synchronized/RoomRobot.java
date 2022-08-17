package ex05_synchronized;

public class RoomRobot extends Thread {
	
	private Cleaner cleaner;
	
	public RoomRobot(Cleaner cleaner) {
		super();
		this.cleaner = cleaner;
	}

	@Override
	public void run() {
		cleaner.toiletCleaning();
	}
	
}
