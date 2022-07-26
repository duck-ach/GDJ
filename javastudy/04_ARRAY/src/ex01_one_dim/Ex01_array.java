package ex01_one_dim;

public class Ex01_array {

	public static void main(String[] args) {
		
		// String도 원래 배열이었다.
		// "hello" <---  X
		// 'h''e''l''l''o' <--- O
		
		// 배열(Array)
		// 1. 여러 개의 변수를 하나의 이름으로 관리하는 자료구조
		// 2. 구성 요소
		//		1) 인덱스(index) : 각 변수의 위치 정보. 0으로 시작
		//		2) 배열명(array name) : 모든 변수를 관리하는 하나의 이름
		// 3. 각 변수는 배열명에 대괄호[]와 인덱스를 붙여서 구분
		
		// 배열 선언 및 생성
		// 1. 배열 선언
		// 		int[] arr;
		//		int arr[];
		// 두가지 방법이 있는데 두개 다 동일하게 동작. (JAVA에서 추천하는 것은 첫번째꺼. 
		// 이유: 2번째선언은 C언어와 호환이 되기 때문. 하지만 사람들이 익숙한 것에 더 편리함을 느껴 JAVA는 두개 다 지원)
		// 2. 배열 생성
		// 		arr = new int[3];  // 3개의 배열 공간을 열겠다. [] 안에 적어준 숫자만큼 변수를 만들어 관리.
		// 3. 배열 선언 및 생성
		// 		int[] arr = new int[3]    // 선언+생성 함께 해줘야한다.
		
		// 배열 요소
		// 1. 배열로 관리되는 각각의 변수를 뜻한다.
		// 2. 모든 배열 요소의 호출
//				arr[0]
//		 		arr[1]
//		 		arr[2]
		// 3. 배열 요소는 자동으로 초기화된다. (어떤 값을 가진다.)
		//		예를들어 일반 변수타입은
		//		int a; // garbage(사용할 수 없는 쓰레기 값을 가진다.)
		// 		System.out.println(a); = 빨간줄이 그인다.
		//		배열은
		//		값이 없음을 의미하는 0, 0.0, false, null 값을 가진다.
		
		// 배열의 장점
		// * 변수 3개가 있는 상황
		// [일반 변수] 					[배열]
		// int a, b, c; 				int[] arr = new int[3];
		// System.out.println(a);		for(int i = 0; i < 3; i++) {
		// System.out.println(b);			System.out.println(arr[i]);
		// System.out.println(c);		}
		
		// 배열의 for문 안에는 변수이름을 되도록이면 i를 입력. / i를 못쓰는 상황이면 j, k 사용.
		int[] arr = new int[3];
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]); // 0, 0, 0  / 자동으로 초기화가 돼서 값을 설정하지 않아도 0이 할당되어 있다.
		}
		
		arr[0] = 100;
		arr[1] = 50;
		arr[2] = 80;
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]); // 100, 50, 80
		}
		
		int total = 0;
		for(int i=0; i<arr.length; i++) {
			total += arr[i];
		}
		
		double average = (double)total / arr.length;
//		double average = total / 3.0;   // 연산에 최소 1개는 double이 포함되어 있어야 double로 연산이 된다.
//		double average = total / 3d;
		System.out.println("평균 : " + average + "점");
		
	}

}
