package fightman;

import java.util.Scanner;

public class FightManMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean turn = Math.random() < 0.5;
		
		
		User user1 = new User();
		User user2 = new User();
		
		
		// 게임방법 안내
		System.out.println("FIGHT MAN _ @Made Heera");
		System.out.println("게임 방법 : ");
		System.out.println(" - 게임에 필요한 유저는 2명이며, 각 HP를 200씩 나눠 갖는다.");
		System.out.println(" - 데미지는 1~100사이의 공격력을 가지며, 랜덤으로 가지게된다.");
		System.out.println(" - 20% 확률로 공격 직후 1~200사이의 HP를 회복한다.");
		System.out.println(" - 먼저 피를 0으로 만드는 사람이 이긴다.");
		System.out.println("■■■■■■■■<게임시작>■■■■■■■■");
		System.out.println("게임을 시작하려면 <Enter>를 누르세요.");
		sc.nextLine();
		
		
		// 닉네임 정하기
		System.out.print("역사의 첫번째 페이지를 장식할 찌질이여... 당신의 이름은 무엇인가... >>> ");
		user1.setName(sc.nextLine());
		System.out.println(user1.getName() + "찌질이! FightMan에 온걸 환영한다네!!");
		System.out.println();
		System.out.print("두번째 찌질이는 누구인고,,,? >>> ");
		user2.setName(sc.nextLine());
		System.out.println(user2.getName() + "찌질이도 FightMan에 온걸 환영한다네!!");
		
		// 순서 정하기
		System.out.println();
		System.out.println("선빵을 치는자.  싸움을 지배한다! \n  준비가되면 <Enter>를 누르시게.");
		sc.nextLine();
		// 순서 알림
		if(turn == true) {
			System.out.println("■ " + user1.getName() + "찌질이, 먼저쳐라!!! ■");
		} else {
			System.out.println("■ " + user2.getName() + "찌질이, 먼저쳐라!!! ■");
		}
		
		System.out.println("데미지는 1~100 중 랜덤이라네. 준비가되면 <Enter>를 누르시게.");
		System.out.println();
		sc.nextLine();
		
		// 공격하기
		while(user1.getHp() > 0 || user2.getHp() > 0) {
			
			user1.setPower((int) (Math.random() * 100) + 1);
			user2.setPower((int) (Math.random() * 100) + 1);
			
			boolean recoverRan = Math.random() < 0.2;
			int recover = (int) (Math.random() * 200) + 1;
			
			
			if(turn == true) {
				// 때리기
				user2.setHp(user2.getHp() - user1.getPower());
				System.out.println(user1.getName() + "찌질이가 " + user2.getName() + "찌질이를 " + user1.getPower() + "데미지로 공격했다!!");
				System.out.println("■ " + user2.getName() + "찌질이 남은 HP : " + user2.getHp() + " ■");
				
				
				
				
				// 사망
				if(user2.getHp() <= 0) {
					System.out.println("■■■■■■■■전투종료■■■■■■■■");
					System.out.println(user2.getName() + "찌질이가 사망했다!! ■" + user1.getName() + "■ 찌질이 승리!!!!!");
					return;
				}
				
				// 회복
					if(recoverRan == true) {
						user2.setHp(user2.getHp() +  recover);
						System.out.println("내가.. 맞았던가...? ■ " + user2.getName() + " ■ 찌질이가 운이 아주 좋게 HP를 " + recover + "만큼 회복했다!!");
						System.out.println("■ " + user2.getName() + "찌질이의 남은 HP : " + user2.getHp() + " ■");
				}
				sc.nextLine();
				turn = false;
				
				
			} else {
				
				
				// 때리기
				user1.setHp(user1.getHp() - user2.getPower());
				System.out.println(user2.getName() + "찌질이가 " + user1.getName() + "찌질이를 " + user2.getPower() + "데미지로 공격했다!!");
				System.out.println("■ " + user1.getName() + "찌질이 남은 HP : " + user1.getHp() + " ■");
				// 사망
				if(user1.getHp() <= 0) {
					System.out.println("■■■■■■■■전투종료■■■■■■■■");
					System.out.println(user1.getName() + "찌질이가 사망했다!! " + user2.getName() + "찌질이 승리!!!!!");
					return;
				}
				// 회복
				if(recoverRan == true) {
					user1.setHp(user1.getHp() +  recover);
					System.out.println("내가.. 맞았던가...? ■ " + user1.getName() + " ■ 찌질이가 운이 아주 좋게 HP를 " + recover + "만큼 회복했다!!");
					System.out.println("■ " + user1.getName() + "찌질이 남은 HP : " + user1.getHp() + " ■");
				}
				sc.nextLine();
				turn = true;
			}
		}
		
		// 내가 맞았던가..? 하면서 피회복
		
		
		
		
	}

}

