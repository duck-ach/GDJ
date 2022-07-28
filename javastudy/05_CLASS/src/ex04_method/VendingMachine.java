package ex04_method;

public class VendingMachine {

	String getCoffee(int a, int b) {
//		String[] coffee = {"아메리카노", "카페라떼"};
//		String coffeeName = "";
//		int coffeeCS = 0;
//		
//		for(int i = 0; i < b; i++) {
//			coffeeName = coffee[i];
//		}
//		
//		coffeeCS = (a / 1000);
//		
//		String ment = coffeeName + " " + coffeeCS +"잔";
//		
//		return ment;

		// 여기부터 선생님 코드
//		String[] menu = {"", "아메리카노", "카페라떼"};
//		
//		return menu[b] + " " + (a / 1000) + "잔";
		
		
		String[] menu = {"아메리카노", "카페라떼"};
		
		return menu[b - 1] + " " + (a / 1000) + "잔";
		
	}
	
}
