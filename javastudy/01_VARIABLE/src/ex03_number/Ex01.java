package ex03_number;

public class Ex01 {

	public static void main(String[] args) {
		
		// 산술 연산
		int a = 7;
		int b = 2;
		int result1 = a + b;
		int result2 = a - b;
		int result3 = a * b;
		int result4 = a / b;  // 나누기를 구할때 사용하는 식이 2개인 이유는 몫을 구할때 사용하는 식과,
		int result5 = a % b;  // 나머지를 구할 때 사용하는 식이 있기 때문이다.
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		
		System.out.println('\n');
		// 연습
		// 25를 2와 5로 나눠보세요
		int n = 25;
		int ten = n / 10; //2
		int one = n % 10; //5
		
		System.out.println(ten);
		System.out.println(one);
		
		System.out.println('\n');
		
		// 연습
		// 90초를 1분 30초로 나눠보기
		int second = 90;
		int m = second / 60;  //1
		int s = second % 60;  //30
		System.out.println(m);
		System.out.println(s);
		
		System.out.println('\n');
		
		// 연습
		// a = 7이고, b = 2이므로
		// a / b = 3.5이다.

		double result = (double)a / b;
		System.out.println(result);
		//b는 자동으로 promotion이 되어 2.0이 된다. 데이터타입이 안 맞으면 연산이 되지 않으므로 하나만 Casting해줘도 나머지는 자동으로 바뀐다.
		//(정수하고 실수가 섞여있으면 실수가 무조건 우선이다.)
	}

}
