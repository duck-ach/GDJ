package ex11_static;

public class MyMathMain {

	public static void main(String[] args) {
		
//		MyMath math1 = new MyMath();
//		System.out.println(math1.abs(-5));
//		
//		MyMath math2 = new MyMath();
//		System.out.println(math2.abs(-8));
//		
//		MyMath math3 = new MyMath();
//		System.out.println(math3.abs(12));
		
		System.out.println(MyMath.PI);
		System.out.println(MyMath.abs(12));
		
		System.out.println(Math.abs(12));
		
		System.out.println(MyMath.pow(2, 3));
		
		
		
		
		
		
		
	}

}

//     Math의 경우 이 객체를 시켜도 공식이 같고, 저 객체를 시켜도 공식이 같다.
//     모든 객체가 내보내는 값이 같으므로 추가로 객체를 만들지 못하게 한다.
// 	   메모리 공간 낭비다.
//		만약 필드에서 PI값을 하나 할당하면
// 		모든 객체에 PI값이 하나씩 추가되어, 메모리가 엄청나게 낭비가 된다. 
//				|-------|
//		math1   |  abs  | PI
//				|-------|
//		math2	|  abs  | PI
//				|-------|
//		math3	|  abs  | PI
//				|-------|




