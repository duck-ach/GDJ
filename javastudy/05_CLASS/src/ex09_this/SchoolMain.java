package ex09_this;

public class SchoolMain {

	public static void main(String[] args) {
		
		Student student1 = new Student();
		
		

		School school = new School(2);

		school.printStudents(); //NullPointerException(주적)
		
		// student는 this는 같다.
		//ex09_this.Student@26f0a63f //@뒤에 오는것은 메모리참조값(16진수)
		//ex09_this.Student@26f0a63f
		
		student1.setStuNo("11025");
		student1.setName("전지현");
		
		// student2 는 getter, setter를 안 불러와도 된다.
		Student student2 = new Student("11026", "정우성");
		
		 // 2명 저장이 가능하다.
		school.addStudent(student1);
		school.addStudent(student2);
		school.printStudents();
		
		// student1이 인덱스의 초기값 배열 0번에 들어갔다.
		
//		school.addStudent(student3); // Full  // 3번째 idx를 사용해야한다. 없는 idx를 사용하려고 한다.
		// java.lang.ArrayIndexOutOfBoundsException (존재하지 않는 인덱스를 사용하는 오류가 뜬다.)
			
//		전지현, 11025
//		정우성, 11026

	}

}
