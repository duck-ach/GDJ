package fight;

import java.util.Scanner;

public class FightManMain {

	public static void main(String[] args) {
		
		// 영웅들 닉네임 입력
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome To FightMan");
		System.out.print("첫번째 영웅님 닉네임을 입력하세요 >>> ");
		String user1 = sc.next();
		System.out.print("두번째 영웅님 닉네임을 입력하세요 >>> ");
		String user2 = sc.next();
		
		System.out.println("=====전투시작=====");
		
		//공격 차례 정하기
		boolean myTurn = Math.random() < 0.5;
		
		while(myTurn) {
			
		}
	}

}
