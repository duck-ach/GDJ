package ex03_hash;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		// hash = 어떤 알고리즘의 하나
		// 해시가 같으면 같은값일 수 있음
		// 해시가 다르면 다른 값임
		
//		Book book1 = new Book();
//		System.out.println(book1.hashCode()); // 653305407 - book1 객체의 참조값
//		
//		Book book2 = new Book();
//		System.out.println(book2.hashCode()); // 1227229563 - book2 객체의 참조값
//
//		System.out.println(book1.equals(book2)); // 객체가 만들어진 참조값을 비교한 것이므로 해시값 비교

		

		Book book1 = new Book(1, "어린왕자");
		Book book2 = new Book(2, "홍길동전");
		Book book3 = new Book(3, "소나기");
		Book book4 = new Book(3, "소나기"); // 중복 저장 시도(정상 동작하려면 Book 클래스에 hashCode(), equals()메소드를 오버라이드 해야 함)
		// bookNo와 title이 같으면 true로 되어 중복값이 사라진다.
		
		Set<Book> books = new HashSet<Book>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4); // 중복 저장 시도
		
		books.hashCode();
		
		for(Book book : books) {
			System.out.println(book);
		}
		
		
		
		
		
//		SHA-256bit
//		SHA-512bit - Secure Hash Algolism 
//		해쉬값을 만들어서 해당 데이터를 암호화한다.
//		웹사이트에서 비밀번호를 처리할때 비밀번호가 입력되면 Hash값으로 변환하여 DB에 저장한다.
//		DB에 저장된 비밀번호 Hash값을 DB관리자가 또다시 해독하려고 하더라도 다른 Hash값으로 변환되기때문에 불가능하다.
		
//		해시 특징
//		1. 뭘 넣든 비슷한 길이의 알 수 없는 난수가 결과로 출력이 된다.
//		2. 글자가 한글자만 바뀌어도 완전히 다른 결과가 출력이 된다.
//		3. 출력값으로 입력값을 예측할 수 없다.
		
	}

}
