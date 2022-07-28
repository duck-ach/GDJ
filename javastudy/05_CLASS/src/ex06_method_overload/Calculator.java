package ex06_method_overload;

public class Calculator {
	
	// 메소드 오버로딩(method overloading)
	// 1) 같은 이름의 메소드가 2개 이상 존재한다.
	// 2) 같은 이름과 다른 매개변수를 가져야 오버로딩 할 수 있다.
	// 3) 반환타입은 오버로딩과 상관이 없다.
	
	int add(int a, int b) {
		return a + b;
	}
	
	int add(int a, int b, int c) {
		return a + b + c;
	}
	
	int add(int a, int b, int c, int d) {
		return a + b + c + d;
	}
	
	// Main에 있는 변수명과 매개변수를 같게쓰는 것을 권장한다.
	// 나중에 코드가 많아지고 길어지면 이름을 안맞춰놓으면 찾기 힘들기 때문이다.
	
	int add(int[] arr) {
		int total = 0;
		for(int n : arr) {
			total += n;
		}
		return total;
		
//		return a[0] + a[1] + a[2] + a[3] + a[4];
		
	}
}
