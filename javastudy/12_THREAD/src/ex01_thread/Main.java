package ex01_thread;

// 스레드(Thread)
// 1. 세부 실행 단위
// 2. 자바 실행의 기본 단위(main 스레드)
// 3. main 스레드 이외의 스레드 추가 가능

// 스레드 생성
// 1. Thread 클래스 상속받는다.
// 2. Runnable 인터페이스 구현

// Thread 클래스 상속받는 방법
// 1. extends Thread
// 2. Thread 클래스의 public void run() 메소드를 오버라이드 (오버라이드하는이유 : 약속이기 때문)

// 스레드 실행
// 1. start() 메소드를 호출
// 2. start() 메소드를 호출하면 run() 메소드에 오버라이드 한 내용이 실행된다.
// Thread의 동작은 run() 안에 명시해야한다. (약속)

public class Main {

	public static void main(String[] args) {
		
		System.out.println("main 시작");
		
		Process process = new Process("연산");
		process.start(); // Process 클래스의 오버라이드된 run() 메소드가 호출
		
		Process process2 = new Process("제어");
		process2.start();
		
		System.out.println("main 종료"); 
		
		// Main은 시작, 메소드, 종료 순으로 일만시키고 메소드는 다른애한테 맡겼기때문에 나중에 실행된다.
		// 그래서 시작 -> 종료 -> 메소드 순으로 실행이 된다.
	}

}
