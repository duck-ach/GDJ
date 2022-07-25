package ex02_datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex05_LocalDateTime {

	public static void main(String[] args) {
		
		// java.time.LocalDateTime  클래스
		// JDK 1.8부터 사용이 가능한 클래스
		// 특정 날짜 요소 사용이 가능하다.
		// 날짜의 패턴 지정이 가능하다.
		LocalDateTime now = LocalDateTime.now();
		
		// 특정 날짜 요소 사용
		// Calendar는 get메소드 하나였지만, LDT는 다 따로되어있어 직관적이고 편리함
		int year = now.getYear();
		int month = now.getMonthValue(); 	// 1 ~ 12
		int day = now.getDayOfMonth(); 		// 1 ~ 31
		int hour = now.getHour(); 			// 0 ~ 59
		int minute = now.getMinute();		// 0 ~ 59
		int second = now.getSecond();		// 0 ~ 59
		
		// 가능하면 코드는 입력을 하지 않고 복사를 한다. (오타를 줄이기위해)
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(minute);
		System.out.println(second);
		
		// 패턴
		// 패턴의 적용 결과는 항상 String
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a h:mm yyyy-MM-dd");
		String date = dtf.format(now);
		System.out.println(date);
		
	}

}
