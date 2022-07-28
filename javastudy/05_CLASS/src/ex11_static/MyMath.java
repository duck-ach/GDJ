package ex11_static;

public class MyMath {

	
	// static
	// 1. 정적 요소
	// 2. 객체 생성 이전에 메모리에 미리 만들어 지는 공동 요소
	// 3. 클래스에서 1개만 만들어 짐
	// 4. 클래스를 이용해서 호출하기 때문에 클래스 변수, 클래스 메소드라고 부름
	
	// 필드
	public static final double PI = 3.141592;
	
	
	
	// static 클래스메소드로 만드는 방법.
	// final  마지막 (PI의 값이 마지막이다. 안변한다.)
	// public 공개  (final을 해놓기 때문에 어차피 값을 바꿀 수 없어서 외부에 공개해도 괜찮다.)
	// 따라서, 여러개 만들어도 좋다.
	
	
	
	
	// 메소드
	// 절대값 반환하는 메소드
	public static int abs(int n) {
		return (n >= 0)  ? n : -n ;
	}
	
	public static int pow(int a, int b) {
		// a의 b제곱 반환
		// for문 구현
		int result = 1;
		for(int cnt = 0; cnt < b; cnt++) {
			result *= a;
		}
		
		return result;
	
		
	}
	
	
}
