package ex02_loop;

public class Ex01_for {

	public static void main(String[] args) {
		
		// for문
		// 연속된 숫자를 생성할 때 주로 사용한다.
		// 배열과 함께 자주 사용된다.
		// for(초기문; 조건문; 증감문) {
		//		실행문
		// }
		
		
		// 1 ~ 10까지 만들어보기
		// 증감식은 n++, ++n 상관없음
		for(int n = 1; n<=10; n++) {
			System.out.print(n + " ");
		}
		
		// 초기문 -> 조건문 -> 실행문 -> 증감문 -> ( 조건문 -> 실행문 -> 증감문 ) 조건에 따라 반복
		// print = 줄바꿈을 해주지않음. println = 프린트라인이라서 줄바꿈을 해줌.
		
		System.out.println(); // 줄바꿈
		
		// 연습.
		// 10 ~ 1 출력
		for(int a = 10; a>=1; a--) {
			System.out.print(a + " ");
		}
		
		System.out.println();
		
		// 연습.
		// 구구단 7단 출력
		int dan = 7;
		for(int chill = 1; chill <= 9; chill++) {
			System.out.println("태권도 7단 : " + dan + " X " + chill + " = " + (dan*chill));
		}
		
		// 연습.
		// 1 ~ 100 사이의 모든 3의 배수만 출력하기
		for(int n = 1; n <= 100; n++) {
			if(n%3 == 0) {
				System.out.print(n + " ");
			}
		}
		
		System.out.println();
		
		// 연습.
		// 1~100 모든 정수 더하기(5050)
		int sum = 0;
		for(int i=1; i<=100; i++) {
			sum += i;
		}
		System.out.println("전체 합 : " + sum);
		
		// 연습.
		// begin ~ end 모든정수 더하기
		// begin과 end 중 누가 큰지 모르는 상황
		// begin을 end보다 항상 작은 값으로 바꾼 뒤 begin ~ end 모두 더하기 진행
		// begin이 end보다 크다면 begin과 end를 교환
		
//		int begin = 10;
//		int end = 1;
//		int total = 0;
//		
//		if(begin>end) {
//			
//			int temp;			
//			temp = begin;
//			end = begin;
//			begin = temp;			
//			for(begin=0; begin<=end; begin++) {
//				total += begin;
//			}
//			System.out.println("begin+total=" + total);	
//
//		} else {
//			
//			for(begin=0; begin<=end; begin++) {
//				total += begin;
//			}
//			System.out.println("begin+total=" + total);
//			
//		}
		
		int begin = 5;
		int end = 1;
		
		if(begin > end) {
			int temp;
			temp = begin;
			begin = end;
			end = temp;
		}
		
		int total = 0;
		for(int n = begin; n <= end; n++) {
			total += n;
		}
		System.out.println("begin+total=" + total);
		

		// 연습.
		// 평점(1~5)에 따른 별(★) 출력하기
		
//		int score = 1;
//		switch(score) {
//		case 1 : System.out.println("☆☆☆☆★"); break;
//		case 2 : System.out.println("☆☆☆★★"); break;
//		case 3 : System.out.println("☆☆★★★"); break;
//		case 4 : System.out.println("☆★★★★"); break;
//		case 5 : System.out.println("★★★★★"); break;
//		default : System.out.println("유효하지 않는 평점입니다.");
//		
//		}

		int point = 1;
		String star = "";
		// n=0으로 초기화하면 조건문에서 n < point 라고 해도 되고,
		// n=1으로 초기화하면 조건문에서 n <= point라고 하면된다.
		for(int n = 0; n < point; n++) {
			star += "★";
		}
		System.out.println(star);
		
	}

}
