package pratice8;

public class Watch {
	// field
	int hour;
	int mimute;
	int second;
	
	// constructor
	public Watch(int hour, int minute, int second) {
		this.hour = hour;
		this.mimute = minute;
		this.second = second;
	}
	
	// method
	public void addHour(int hour) {

		if(hour < 0) {
				return;
		}
		this.hour += hour;
		this.hour %= 24;
		
	}
	
	public void addMinute(int minute) {
		
		if(mimute < 0) {
				return;
		}
		this.mimute += minute;
		addHour(this.mimute / 60);
		this.mimute %= 60;
		
	}
	
	public void addSecond(int second) {

		if(second < 0 ) {
			return;
		} 
		this.second += second;
		addMinute(this.second / 60);
		this.second %= 3600;
	}
	
	
	
	public void see() {
		System.out.println(hour + "시 " + mimute + "분 " + second + "초");
	}
	
	
	
	
}
