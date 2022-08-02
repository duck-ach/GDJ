package pratice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Pratice {

	// 문제 1
	// 점수와 학년을 입력받아 60점 이상이면 합격, 60점 미만이면 불합격을 출력하시오.
	// 4학년인 경우 70점 이상이어야 합격이다.
	public static void q1() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("점수를 입력하세요(0~100) >>> ");
		int score = sc.nextInt();
		
		System.out.print("학년을 입력하세요(1~4) >>> ");
		int grade = sc.nextInt();
		
		if(grade < 4) {
			if(score >= 60) {
				System.out.println("합격!");
			} else {
				System.out.println("불합격!");
			}
		} else {
			if(score >= 70) {
				System.out.println("합격!");
			} else {
				System.out.println("불합격!");
			}
		}
	
		
	}
	
	
	// 문제 2
	// 커피 메뉴를 입력받아 가격을 알려주는 프로그램을 구현하시오. switch문 이용
	// (에스프레소, 카푸치노, 카페라떼는 3500원. 아메리카노는 2000원이다.)
	public static void q2() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("무슨 커피 드릴까요? >>> ");
		String coffee = sc.next();
		
		switch(coffee) {
		case "에스프레소" : 
		case "카푸치노" : 
		case "카페라떼" : System.out.println(coffee + "는 3500원 입니다."); break;
		case "아메리카노" : System.out.println(coffee + "는 2000원 입니다."); break;
		default : System.out.println(coffee + "는 메뉴에 없습니다.");
		
		}
		
		
	}
	
	// 문제 3
	// 돈을 입력받아 오만원권, 만원권, 오천원권, 천원권, 오백원 동전, 백원 동전, 오십원 동전,
	// 십원동전, 오원동전 일원동전이 각각 몇개로 변환되는지 출력하시오.
	// 이 때 반드시 다음과 같은 배열을 사용하여 반복문으로 처리하시오.
	public static void q3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("금액을 입력하시오 >> ");
		int cash = sc.nextInt();
		
		int[] unit = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		int result = 0;
		
		for(int i = 0; i < unit.length; i++) {	
			result = cash / unit[i];

			
			
			if(result > 0) {
				System.out.println(unit[i] + "원 짜리 : " + result + "개");
			} 
			cash = cash - unit[i] * result;
			
			
			
		}
		
	}
	
	// 문제 4
	// 정수를 몇 개 저장할지 (최대100개) 입력받아서 해당 길이를 가진 배열을 생성하고,
	// 이곳에 1에서 100사이 범위의 정수를 랜덤하게 삽입하시오.
	// 같은 값은 생성하지 못하도록 설정하고 생성된 배열을 출력하시오
	
	public static void q4() {
		HashSet<Integer> set = new HashSet<Integer>();
		Scanner sc = new Scanner(System.in);
		System.out.print("몇 개의 랜덤을 생성할까요? >>> ");
		int T = sc.nextInt();
		boolean a = true;
		
		
		if(T > 100 || T < 0) {
			System.out.println("다음에는 1~100사이로 입력하세요!");
			
		} else {
				while(a) {
					set.add((int) (Math.random() * 100) + 1);
				}
			Integer[] arr = set.toArray(new Integer[T]);
				
			
			
			System.out.println(Arrays.toString(arr));
				
				
				
					
					
			}
	}
		
		
	

	
		
	
	// 문제 5
	// 3명의 학생의 점수를 입력 받아서 평균 점수와 1등의 이름과 꼴등의 이름을 출력하시오.
	
	public static void q5() {
		int[] score = new int[3];
		String[] cha1 = {"피카츄"};
		int sum = 0;
//		int max = score[0];
//		int min = score[0];
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("피카츄의 점수 입력 >>> ");
		score[0] = sc.nextInt();
		System.out.print("뽀로로의 점수 입력 >>> ");
		score[1] = sc.nextInt();
		System.out.print("브레드의 점수 입력 >>> ");
		score[2] = sc.nextInt();
		
		
		for(int i = 0; i < score.length ; i++) {
			sum += score[i];
		}
		
		double avg = (double) sum / 3;
		System.out.println("평균 : " + avg + "점");
		System.out.println(sum);
		
		
//		for(int i = 0; i < score.length; i++) {
//			if (max < score[i]) {
//				max = score[i];
//			} 
//			if (min > score[i]) {
//				min = score[i];
//			}
//		}
//		System.out.println("1등 : " + max);
//		System.out.println("3등 : " + min);
		
	}
		
	// 문제 6
	// 랜덤으로 윷놀이를 구현하시오. 도개걸윷모 중 랜덤생성하여 이동횟수와 함께 화면에 출력하시오.
	// 윷이나 모가 나오면 계속 랜덤생성하여 총 이동 횟수를 계산하여 출력하시오.
	public static void q6() {
		boolean a = true;

		int count = 0;
		while(a) {	
			int yut = (int)(Math.random() * 5 ) + 1;
	
			switch(yut) {
			case 5 : System.out.print("모, ");
			count += 5;
			case 4 : System.out.print("윷, ");
			count += 4;
			case 3 : System.out.print("걸, ");
			count += 3; a = false; break;
			case 2 : System.out.print("개, ");
			count += 2; a = false; break;
			case 1 : System.out.print("도, ");
			count += 1; a = false; break;		
			}	
		}
		System.out.println(count +"칸 이동한다.");

		
	}
	
	public static void q7() {
		Scanner sc = new Scanner(System.in);
		long a = 0;
		long b = 0;
		double result1 = 0;
		System.out.print("첫번째 도전자님 닉네임을 입력하세요! >>> ");
		String user1 = sc.next();
		
		System.out.println(user1 + "님 시작하려면 <Enter>를 누르세요.");
		sc.nextLine();
		sc.nextLine();
		a = System.currentTimeMillis();
		
		System.out.println("10초가 된 것 같으면 <Enter>를 누르세요.");
		sc.nextLine();
		b = System.currentTimeMillis();
		
		result1 = (double) ((b - a) * 0.001);
		
		System.out.println("종료시간 : " + result1 + "초");
		
		long c = 0;
		long d = 0;
		double result2 = 0;
		
		System.out.print("두번째 도전자님 닉네임을 입력하세요! >>> ");
		String user2 = sc.next();
		
		System.out.println(user2 + "님 시작하려면 <Enter>를 누르세요.");
		sc.nextLine();
		sc.nextLine();
		c = System.currentTimeMillis();
		
		System.out.println("10초가 된 것 같으면 <Enter>를 누르세요.");
		sc.nextLine();
		d = System.currentTimeMillis();
		
		result2 = (double) ((d - c) * 0.001);
		
		System.out.println("종료시간 : " + result2 + "초");
		
		if(Math.abs(result1 - result2) < Math.abs(result2 - result1)) {
			System.out.println(user1 + "님이" + Math.abs(result1-result2) + "차이로 승리하셨습니다!!");
		} else {
			System.out.println(user2 + "님이" + Math.abs(result2-result1) + "차이로 승리하셨습니다!!");
		}
		
		
		
	}
	
	
	
	 
		
	
	
	public static void main(String[] args) {
//		q1();
//		q2();
//		q3();
		q4();
//		q5();
//		q6();
//		q7();
	}
	
}

