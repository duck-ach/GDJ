package library;

import java.util.Arrays;
import java.util.Scanner;

public class Library {
	private Scanner sc;
	private Book[] books;
	private int idx;
	
	public Library() {
		sc = new Scanner(System.in);
		books = new Book[100];
	}

	private void addBook() {
		if(idx == books.length) {
			System.out.println("책이 가득 찼습니다.");
			return;
		}
		System.out.println("===책등록===");
		System.out.print("제목 입력 >>> ");
		String title = sc.nextLine();
		System.out.print("저자 >>> ");
		String author = sc.nextLine();
		Book book = new Book(idx + 1, title, author); //AllArgsConstructor
		books[idx++] = book;
		System.out.println("책 등록이 완료되었습니다.");
		System.out.println("책 번호 : " + idx);
		System.out.println("책 제목 : " + title);
		System.out.println("저자 : " + author);
	}
	
	private void removeBook() {
		if(idx == 0) {
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===책삭제===");
		System.out.print("삭제할 책의 번호 >>> ");
		int bookNo = sc.nextInt(); // 책의번호는 1~100 인덱스는 0~99라서
		for(int i = 0; i < idx; i++) { // 삭제할 책의 위치 : i 
			if(books[i].getBookNo() == bookNo) {
				System.arraycopy(books, i + 1 , books, i, idx - i - 1);
				books[--idx] = null; // 만약 인덱스가 10이라면 먼저 줄어야하므로
				//
				System.out.println("책 번호가 " + bookNo + "인 책을 삭제했습니다.");				
				return;
			} // 
		}
		System.out.println("책 번호가 " + bookNo + "인 책이 없습니다.");
	}
	
	private void findBook() {
		if(idx == 0) {
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===책조회===");
		System.out.print("조회할 책제목 입력 >>> ");
		String title = sc.nextLine();
		for(int i = 0; i < idx; i++) {
			if(title.equals(books[i].getTitle())) {
				System.out.println(books[i]);
				return; // 메소드 종료, break를 넣을경우, 찾고나서 밑의 제목이 title책없다를 출력한다.
			} //else는 넣으면 안된다. 그냥 메소드를 끝내야한다.
			
		}
		System.out.println("제목이 " + title + "인 책은 없습니다.");
	} // break = for문이 끝난다. return = 메소드가끝난다.
	
	private void printAllBooks() {
		if(idx == 0) {
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===전체조회===");
		for(int i = 0; i < idx; i++) {
			System.out.println(books[i]);
		}
	}
	
	public void manage() {
		
		while(true) { // 무한루프
			System.out.print("1.추가 2.삭제 3.조회 4.전체목록 0.프로그램종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1: addBook(); break;
			case 2: removeBook(); break;
			case 3: findBook(); break;
			case 4: printAllBooks(); break;
			case 0: System.out.println("Library 프로그램을 종료합니다. 감사합니다.");
					return; // manage() 메소드 종료
			default : System.out.println("알 수 없는 명령입니다. 다시 시도하세요.");
			}
		}
		
	}
}