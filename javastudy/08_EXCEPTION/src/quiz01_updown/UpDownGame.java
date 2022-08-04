package quiz01_updown;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpDownGame {
	
	//필드
	private int rand; // 1 ~ 100사이 난수
	private int count; // 시도 횟수
	private Scanner sc;
	
	// 생성자
	public UpDownGame() {
		// rand, sc 만들기
		rand = (int) (Math.random() * 100 ) + 1;
		sc = new Scanner(System.in);
	}
	
	// 입력
	public int input() {
		try {
		count++;
		System.out.print("입력 >>> ");
		int n = sc.nextInt();
		if(n < 1 || n > 100) {
			throw new RuntimeException("랜덤 숫자는 1보다 작거나 100보다 클 수 없습니다.");
		}
		return n;
		}
		catch (InputMismatchException e) {
			System.out.println("1~100범위의 정수만 입력할 수 있습니다.");
			sc.next(); // 잘못 입력된 문자열 먹어 치우기
			input(); // 다시실행. 다시돌아가므로 count도 올라감.
		}
		return 0; // 이클립스 안심시키는 용도 (실제로 돌아가는일 x)
	}
	
	// 실행
	public void play() {
		
		
			while(true) {
				int n = input();
				
				
				if(n == rand) {
					System.out.println(count + "번만에 정답!");
					return;
				} else if(n < rand) {
					System.out.println("Up!!");
				} else if(n > rand) {
					System.out.println("Down!!");
				}
				
			
			// 맞출 때 까지 무한루프
		}
		
	
		
	}
	
}
