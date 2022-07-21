package ex01_branch;

public class Ex01_if {

	public static void main(String[] args) {
		
		// if문
		// 조건을 만족하는 경우에만 실행
		// 실행문이 하나일 경우 중괄호{}는 생략할 수 있지만 생략안하는게 좋음
		// 형식
		// if(조건) {
		//    실행문  ← 실행문은 깔끔하게 보이기 위해 꼭 tap키를 이용하여 들여쓰기를 해줘야 한다.
		// }
		
		int score = 100;
		if (score >= 60) {
			System.out.println("합격");
			System.out.println("축하합니다.");
		}
		if (score < 60) {
			System.out.println("불합격");
		}
		

	}

}