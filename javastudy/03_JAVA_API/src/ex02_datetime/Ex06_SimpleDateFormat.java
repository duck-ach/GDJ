package ex02_datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex06_SimpleDateFormat {

	public static void main(String[] args) {
		
		// java.text.SimpleDateFormat 클래스
		// 패턴이 적용된 String 타입의 날짜 반환 
		
		Date date1 = new Date();
		// java.sql패키지에 있는 Date를 활용하려면 이렇게 import가 되지않음.
		// 이름이 같은 메소드인데 패키지가 달라 다른 import가 중복되면 컴퓨터가 코드를 읽을 때
		// 코드가 헷갈릴 수 있으므로!
		java.sql.Date date2 = new java.sql.Date(System.currentTimeMillis());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result1 = sdf.format(date1);
		String result2 = sdf.format(date2);
		
		System.out.println(result1);
		System.out.println(result2);
		
		
	}

}
