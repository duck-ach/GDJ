package pratice8;

public class WatchMain {

	public static void main(String[] args) {
		
		Watch watch = new Watch(16, 15, 30);
		
		watch.addSecond(3600);
		
		watch.see();
	}

}
