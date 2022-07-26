package ex02_casting;

public class Ex02 {

	public static void main(String[] args) {
		
		// 강제 형 변환
		// casting 이라고 한다.
		// 큰 크기의 데이터타입을 작은 크기의 데이터타입으로 변환할 때 강제로 진행한다.
		// 실수를 정수로 변환할 때 강제로 진행한다.
		// 강제로 변환할 변수 앞에 괄호를 붙이고 변활할 타입을 적어주면 된다.
		
		int score = 100;
		byte realScore = (byte)score;
		System.out.println(realScore); // 100
		
		// Casting을 할 때는 잘 해야함. 특히 소수점을 잘못 날리거나 아니면 int를 byte코드로 변환하는 코드에도 1000을 넣으면 -24가 출력된다.
		
		double grade = 4.5;
		int realGrade = (int)grade;
		System.out.println(realGrade); // 4 소수점은 항상 잘려나간다.
		
		
	} 

}