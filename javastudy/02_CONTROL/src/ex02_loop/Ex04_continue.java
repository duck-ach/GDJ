package ex02_loop;

public class Ex04_continue {

	public static void main(String[] args) {
		
		// continue문
		// 반복문의 시작 지점으로 이동한다.
		// 크게 중요하지 않다. continue라는게 있었다. 정도만 기억해둘 것.
		// 형식
		//		while() {
		//			a;
		//			b;
		//			c;
		//			continue; // a b c 만 실행하고 다시 while()문으로 이동한다.
		//			d;
		//			e;
		//			f;
		//			g; // d e f g는 실행되지 않는다. (Dead code)
		//		} 

		// 실행에서 제외할 코드가 있는 경우에 사용한다.
		// 실무에서는 continue보다 좋은코드들이 있기 때문에 굳이 깊이 공부할 필요는 없다.
		
		// 문제.
		// 1~ 100 중에서 3의 배수를 제외하고 모두 더하기
		int n = 0;
		int total = 0;

		while(n < 100) {
			
			n++;
			if(n % 3 == 0) {
				continue;
			}
			total += n;
		}
		System.out.println(total);
		
		//continue를 굳이 쓰지 않아도 이렇게 구현할 수 있다.
		int sum = 0;
		int a = 0;
		while(a<100) {
			a++;
			if(a % 3 != 0) {
			sum += a;
		}
	}
			System.out.println(sum);
}
}