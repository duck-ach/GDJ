package ex02_datetime;

import java.util.Calendar;

public class Ex04_Calendar {

	public static void main(String[] args) {
		
		// java.util.Calendar클래스
		// 현재 날짜 또는 특정 날짜를 나타낼 때 사용
		// 날짜의 특정 요소(년, 월, 일, 시, 분, 초, ...)를 쉽게 사용 
		
		// Calendar = class(클래스),  cal = Object(객체)
		// int a;   int = datatype(데이터 타입),  a = variable(변수)
		// 객체지향프로그래밍 , OOP (Object Oriented Programming)
		// 메소드(method)와 함수는 같은 말이다. (Class안에 포함되어있으면 메소드라고부르고, 아니라면 함수라고 부름.)
		// 			자바에서는 함수라고 안부르고 메소드라고 부른다.

		// 자동완성(ctrl+spacebar)을 통해 import를 자동으로 할 수 있기 때문에 손으로 쓰면 안된다.
		Calendar cal = Calendar.getInstance(); // 객체 Cal은 현재 날짜와 시간으로 구성
		
		// 년, 월, 일
		
		int year = cal.get(Calendar.YEAR);  // int year = cal.get(1);
		// 인터넷 주소 IP주소를 못외워서 Domain주소를 사용하는 것처럼, 인간은 숫자를 잘 못외우기 때문에
		// 문자형으로 변형해서 코드가 더 길더라도 이렇게 사용한다.
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH); // 월 기준 몇일
		int weekNo = cal.get(Calendar.DAY_OF_WEEK); // 일주일 중 몇번째 날짜인가(요일)
			
		System.out.println(year); // 2022
		System.out.println(month + 1); // 7, 월 : 0 ~ 11(주의가 필요함)
		System.out.println(day); // 25
		switch(weekNo) {
		case 1 : System.out.println("일요일"); break;
		case 2 : System.out.println("월요일"); break;
		case 3 : System.out.println("화요일"); break;
		case 4 : System.out.println("수요일"); break;
		case 5 : System.out.println("목요일"); break;
		case 6 : System.out.println("금요일"); break;
		default : System.out.println("토요일");
		}
	
		// 오전/오후, 시, 분, 초
		int ampm = cal.get(Calendar.AM_PM);
		int hour12 = cal.get(Calendar.HOUR); 		// 1 ~ 12시
		int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0 ~ 23시
		int min = cal.get(Calendar.MINUTE);			// 0 ~ 59분
		int second = cal.get(Calendar.SECOND);		// 0 ~ 59초
		
		switch(ampm) {
		case 0 : System.out.println("오전");
		case 1 : System.out.println("오후");
		}
		
		
		System.out.println("현재시각 : " + hour12 + "시 " + min + "분 " + second + "초");
		
		// timestamp
		long timestamp = cal.getTimeInMillis();
		System.out.println(timestamp);
		
		
		
	}

}
