package ex09_this;

public class Student {
	
	// this
	// 1) 현재 객체의 참조값
	// 2) 현재 객체의 멤버(필드, 메소드)를 호출할 때 사용 
	//    (메소드는 거의 사용되지않고 필드용으로 사용된다.)
	// 3) 생성자 내부에서 다른 생성자를 호출할 때 this() 형태로 사용
	
	// 필드
	private String stuNo;  	// this.stuNo 와 동일하다.
	private String name;	// this.name 와 동일하다.
	
	
	// 생성자
	public Student() {
		
	}
	// 학번하고 이름을 받아오는 생성자
	public Student(String stuNo, String name) {
		this.stuNo = stuNo;
		this.name = name;
	}
	
	
	
	
	// 메소드
//	public void printThis() {
//		System.out.println(this); 
//		System.out.println(this.stuNo);
//		System.out.println(this.name);
//	} 

	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// this를 쓰면 메소드에 매개변수를 넣어 필드에 값을 넣을 때 이름이 동일해도 구분이 간다.

	// getter setter 
	// get과 set을 자동 설정 할 수 있다.
	// (주의) : 메소드 이름을 바꾸면 안된다. DB하고 연결이 안됨.
	// Human Error가 날 수 있기 때문에 getter와 setter는 손으로 쓰지 않는다.
	
	
	
	
}
