package ex01_branch;

public class Ex03_else_if {

	public static void main(String[] args) {
		
		// else if문
		// 조건이 여러개가 사용되는 경우에 각 조건을 처리한다.
		// if(조건) {
		//		실행문
		// } else if (조건) {
		//		실행문    		//else if는 원하는 만큼 사용할 수 있음.
		// } else if (조건) {
		//		실행문
		// } else {
		//		실행문
		// }

		// 나이에 따른 결과 출력
		// 00 ~ 07 : 미취학 아동
		// 08 ~ 13 : 초등학생
		// 14 ~ 16 : 중학생
		// 17 ~ 19 : 고등학생
		// 20 ~    : 성인
		
		int age = 5;
		
		if(age < 0 || age > 100) {
			System.out.println("잘못된 나이");
		} else if(age <= 7) {
			System.out.println("미취학 아동");
		} else if(age <= 13) {
			System.out.println("초등학생");
		} else if(age <= 16) {
			System.out.println("중학생");
		} else if(age <= 19) {
			System.out.println("고등학생");
		} else {
			System.out.println("성인");
		}
		
		
		// 문제.
		// 월에 따른 계절 출력
		// 봄   : 3 ~ 5
		// 여름 : 6 ~ 8
		// 가을 : 9 ~ 11
		// 겨울 : 12, 1 ~ 2
		
		int month = 3;
		if(month < 1 || month > 12) {
			System.out.println("잘못된 월");
		} else if (month == 12 || month <= 2) {
			System.out.println("겨울");
		} else if (month <= 6) {
			System.out.println("봄"); 
		} else if (month <= 8) {
			System.out.println("여름");
		} else {
			System.out.println("가을");
		}
		
		// 나머지 연산을 활용한 modular 연산
		int mod = month % 12;
		if(mod <= 2) {
			System.out.println("겨울");
		} else if (mod <= 5) {
			System.out.println("봄");
		} else if (mod <= 8) {
			System.out.println("여름");
		} else {
			System.out.println("가을");
		}
		
		
		// 연습.
		// 점수에 따른 학점
		// 점수 = score, 학점 = grade
		// 100 ~ 90 = A
		//  89 ~ 80 = B
		//  79 ~ 70 = C
		//  69 ~ 60 = D
		//  59 ~    = F
		
		int score = 100;
		char grade = 'F';
		
		if(score < 0 || score > 100) {
			System.out.println("확인되지 않는 점수입니다.");
		} else if (score >= 90) {
			System.out.println(grade = 'A');
		} else if (score >= 80) {
			System.out.println(grade = 'B');
		} else if (score >= 70) {
			System.out.println(grade = 'C');
		} else if (score >= 60) {
			System.out.println(grade = 'D');
		} else {
			System.out.println(grade = 'F');
		}
		System.out.println("점수는 " + score + "점");
		System.out.println("학점은 " + grade + "학점");
		
		
		// 연습.
		// 오직 일 수만 고려
		// 1일이 수요일이다.
		// n일 후 무슨 요일인지 출력하기.
		int day = 1;
		int n = 1;
		String weekName; //목요일
		
		int mod2 = day+n % 7;
		if (mod2 == 1) {
			weekName = "수";
		} else if (mod2 == 2) {
			weekName = "목";
		} else if (mod2 == 3) {
			weekName = "금";
		} else if (mod2 == 4) {
			weekName = "토";
		} else if (mod2 == 5) {
			weekName = "일";
		} else if (mod2 == 6) {
			weekName = "월";
		} else {
			weekName = "화";
		}
		System.out.println(weekName + "요일");
		
		
		
		
	}

}
