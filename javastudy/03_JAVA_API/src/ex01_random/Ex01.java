package ex01_random;

public class Ex01 {  // 클래스(Class)

	public static void main(String[] args) { // 메소드(method)
		
		// 난수(Random number) 발생
		// Random 클래스, Math 클래스를 주로 활용한다.
		
		System.out.println(Math.random());
		
		// 0.0 <= Math.random() < 1.0
		// 0% <= Math.random() < 100%
		
		// 1. 확률 처리하기
		// 10% 확률로 "대박", 90% 확률로 "쪽박"
		if(Math.random() < 0.1) {
			System.out.println("대박");
		} else {
			System.out.println("쪽박");
		}
		
		// 2. 난수 값 생성
		// Math.random()                  0.0 <= n < 1.0
		// Math.random() * 5              0.0 <= n < 5.0
		// (int)(Math.random() * 5)         0 <= n < 5   // Casting
		// 10을 곱해주면 0~9가 나오고 15를 곱해주면 0~14가 나온다.
		
		// (int)(Math.random() * 6) + 1     1 <= n < 5  
		//                          + 4    // 4부터 6개의 숫자가 발생합니다. [ 4, 5, 6, 7, 8, 9 ]
		//                          + 2    // 2부터 6개의 숫자가 발생합니다. [ 2, 3, 4, 5, 6, 7 ]
		
		//문제.
		//주사위가 2개가 있습니다. 2개가 어떤 숫자가 나올지 출력하시오.
		
		
//		  int diceA = (int)(Math.random() * 6) + 1; 
//		  System.out.println("Dice 1 : " + diceA); 
//		  int diceB = (int)(Math.random() * 6) + 1;
//		  System.out.println("Dice 2 : " + diceB);
		 
		
		for(int n = 0; n < 2; n++) {
			int dice = (int)(Math.random() * 6) + 1;
			System.out.println("Dice " + dice);
		}
		
		// 문제.
		// 3. 여섯자리 휴대폰 인증 숫자 만들기.
		// String code = "243252";
		String code = "";
		for(int n = 0; n < 6; n++) {
			code +=(int)(Math.random() * 10); // "" + 100 = "100" 이것을 활용한다.
		}
		System.out.println(code);
		
		// int로 하면 안되는 이유
		// 1. 앞자리가 0이라면 나타나지 않음
		// 2. +=를 했을때 연산이 되므로
		
		// 실제 휴대폰 인증번호는 보안문제 때문에 Random은 가급적이면 사용하지 않고 JAVA에 내장된 라이브러리를 사용하여 만든다.
		
		//ASKII Table
		//america standard code
		
		
		// 문제.
		// ASKII문자표에 따른 A~Z(65~90)사이의 코드값을 랜덤으로 생성하시오.
		
		System.out.println((char)((int)(Math.random() * 26) + 'a'));
		// 1 + 'A' 연산이가능하다. 1 + 65 // 한글은 불가능하다.
		
		// 문제.
		// 대문자와 소문자를 섞어서 랜덤하게 6글자 만들기
		String enCode = "";
		for(int n = 0; n < 6; n++) {
			if(Math.random() < 0.5) { // 조건 : 50% 확률로
				enCode += (char)((int)(Math.random() * 26) + 'A');
			} else {
				enCode += (char)((int)(Math.random() * 26) + 'a');
			}
				
		}
		System.out.println(enCode);
		
		
		
		
		
	}

}
