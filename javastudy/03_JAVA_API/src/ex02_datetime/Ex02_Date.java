package ex02_datetime;

import java.util.Date;
	//import는 손으로 쓰는게 아니다.
	//자동완성 ctrl + spacebar을 이용하여 불러온다.
public class Ex02_Date {
	
	public static void main(String[] args) {
		// 클래스 명명 규칙
		// 패키지 다음에 마침표(.)를 적고 클래스명을 적는다.
		
		java.lang.System.out.println("Hello");
		
		// 예외 규칙
		// java.lang 패키지에 소속된 클래스들은 패키지를 생략한다.
		
		// java.util 패키지의 Date 클래스
		// 1. java.util.Date (첫번째 원래 방법)
		//      - 문제 1 : 보기에 지저분하다.   문제 2 : 어떤라이브러리에 뭐가있는지 다 못외운다.
		// 2. import java.util.Date;
		//    Date
		
		///////////////////////////////////////////////////////////
		
		// 클래스와 객체
		// 1. 클래스 : 객체를 만들기 위한 설계도
		// 2. 객체   : 클래스를 이용해서 만든 실제 객체
		// 3. 일반적인 객체 생성 방법
		//    클래스 객체 = new 클래스();
		//	  객체 이름은 변수이름과 같이 정하면 된다.
		
		//  메소드 : 객체의 행동
		//  필드   : 객체의 속성
		
		// 메소드 사용
		// 1. 클래스를 이용해서 호출
//			System.out.println()
//			System.currentTimeMillis()
//			System.nanoTime()
//			Math.random()
		// 2. 객체를 만들어서 호출
//			String str = new String("Hello")  // Hello라는 문자를가진 str 객체가 만들어진다. // 클래스 String, 객체 str
//			str.equals("Hello")
//			Date now = new Date(); // 클래스 Date, 객체 now
//			now.getMonth()  //오늘 날짜 중 월을 얻는 것
//			StringBuilder sb = new StringBuilder(); // 클래스 StringBuilder, 객체 sb
//			sb.append()
//			붕어빵 붕1 = new 붕어빵(); // 클래스 붕어빵, 객체 붕1
//			붕어빵 붕2 = new 붕어빵(); // 클래스 붕어빵, 객체 붕2     //클래스 하나 가지고 객체 여러개 만들기
//			붕어빵 붕3 = new 붕어빵(); // 클래스 붕어빵, 객체 붕3
//			붕어빵 붕4 = new 붕어빵(); // 클래스 붕어빵, 객체 붕4
		
		
		// Date 클래스와 now 객체
		Date now = new Date();
		System.out.println(now);
		
		
		
		
	}
	
}
