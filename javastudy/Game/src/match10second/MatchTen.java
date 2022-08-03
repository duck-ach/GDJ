package match10second;

import java.util.Scanner;

public class MatchTen {

	public static void main(String[] args) {
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
		
		if(Math.abs(result1 - 10) < Math.abs(result2 - 10)) {
			System.out.println(user1 + "님이" + Math.abs(result1-result2) + "차이로 승리하셨습니다!!");
		} else {
			System.out.println(user2 + "님이" + Math.abs(result2-result1) + "차이로 승리하셨습니다!!");
		}
		
		
		

	}

}
