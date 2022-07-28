package ex09_this;

public class School {

	// 필드
	private Student[] students;
	private int idx; // students 배열의 인덱스. students 배열에 저장된 학생수와 같다.
	
	
	// 생성자
	public School(int cnt) {
		students = new Student[cnt];
	}
	
	// 메소드
	public void addStudent(Student student) {
		
		
		if(idx == students.length) {
			System.out.println("Full");
			return; // return을 하면 메소드를 종료. 뒤에 코드를 실행하지 않는다.
		}  // if-else문을 사용하더라도 동일하게 동작한다.
		students[idx++] = student;
			
		} // idx++ (후위연산자)를 이용하여 사용후에 증가시킨다.
	
	
	
	
	public void printStudents() {
		
		// 일반 for문
//		for(int i = 0; i < idx; i++) { // 배열의 길이보다 작은값이라고 조건을 걸지말고, idx보다 작은값이라고 조건을 걸어야,
//									   // 학생의 수 가 배열의 값보다 작더라도 코드가 돌아간다.
//			System.out.println(students[i].getName() + ", " + students[i].getStuNo());
//		}
		
		// 향상 for문
		for(Student student : students) {
			if(student != null) {
			System.out.println(student.getName() + ", " + student.getStuNo());
			}
		}
		
	}
	
}
