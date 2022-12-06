package ex03_functional_interface.sec03;

public class Main {

	public static void main(String[] args) {
		// 10 출력하기
		MyInterface3 my = () -> 10;
		System.out.println(my.method());
		
		// 20 출력하기
		MyInterface3 you = () -> 20;
		System.out.println(you.method());
		
	}

}
