package ex02_casting;

public class Ex03 {

	public static void main(String[] args) {
		
		// 검색창에 100이라는 것을 검색을 하면 100 이아닌 "100"으로 검색이 된다.
		// 그것을 DB로 넘겨주면서 숫자인 값도, 문자인값도 등등등 함께 넘겨줘야 다양한 데이터들을 사용자에게 보여줄 수 있으니 자주쓰는 것이다.
		String strScore = "100"; 
		String strMoney = "10000000000";
		String strGrade = "4.5";
		
		// 문자열을 숫자데이터로 변환하기
		// 아래 변환은 매우 중요하다.
		// parsing = 분석하다.
		int score = Integer.parseInt(strScore);
		long money = Long.parseLong(strMoney);
		double grade = Double.parseDouble(strGrade);
		
		System.out.println(score);
		System.out.println(money);
		System.out.println(grade);

		// ctrl + art + 방향키 = 한꺼번에 똑같은 코드 방향키에 따라 복사
		
		// 숫자데이터를 문자열로 변환하기
		// 100 - > "100"
		int age = 100;
		String strAge = String.valueOf(age);
		System.out.println(strAge);
	}

}
