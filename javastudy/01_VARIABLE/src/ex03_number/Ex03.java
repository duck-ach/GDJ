package ex03_number;

public class Ex03 {

	public static void main(String[] args) {
		
		// 대입 연산
		int score = 100; // 등호(=)가 대입 연산자이다. ("같다"라는 뜻이 아니다.)
		// score <- 100; DATA를 score로 옮겨주는 것이다.
		// 100 = score는 말도안되는 개소리이다.
		
		// 연습
		// x에 10이 있고, y에 20이 있다.
		// x와 y에 저장된 값을 서로 교환하시오.
		// 힌트 : int temp;
		int x = 10;
		int y = 20;
		int temp = x;
		x = y;
		y = temp;
		
		System.out.println(x);
		System.out.println(y);
		
		// 복합 대입 연산자
		// +=, -=, *=, /=, %= 등
		int wallet = 0;
		wallet = wallet + 5000;
		wallet += 5000; // wallet = wallet + 5000;
		wallet -= 3000; // wallet = wallet - 3000;
		System.out.println(wallet);

		// 연습.
		// 잔액(balance)에서 이자 5%를 받았음을 나타내자.
		long balance = 10000;
		balance *= 1.05; // balance를 double로 promotion해서 처리한다. (실수>정수)
		System.out.println(balance);
		
		balance *= 1.05;  // promotion이 된다.
//		balance = balance * 1.05; 실패. balance * 1.05 결과는 double이기 때문에 long balance에 저장할 수 없다.
		balance = (long)(balance *1.05); // 성공. 풀어서 굳이!!! 해야한다면 casting을 해주어야한다. 
		
//		balance = balance + balance * 0.05; 실패. balance + balance * 0.5결과는 double이기 때문에 long balance에 저장할 수 없다.
//		balance = (long)(balance + balance * 0.05); 성공. balance + balance * 0.05결과를 long으로 casting해서 저장할 수 있다.
		
		
	}

}
