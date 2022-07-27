package ex02_field;

public class SchoolMain {

	public static void main(String[] args) {
		
		School school = new School();
		Student student1 = new Student();
		Student student2 = new Student();
		
		
		student1.name = "전지현";
		student1.stuNo = "11024";
		
		student2.name = "정우성";
		student2.stuNo = "11025";
		
		school.name = "구디아카데미";
		
		school.students[0] = student1;
		school.students[1] = student2;
		
		for(int i = 0; i<school.students.length; i++) {
			System.out.println(school.students[i].stuNo);
			System.out.println(school.students[i].name);
		}
		
		
		// 객체는 배열이 아니니까 변수를 하나씩 처리할 수 밖에 없음
		// NullPointerException 우리의 주적. 아무것도없는데 무언가를 호출하려고 할 때.
		

		
//		
//		school.students[0] = student1;
//		school.students[1] = student2;
//		
//				|-------|
//			arr	| 0x123 |
//				|-------|
//				|  ...  |
//				|-------|
//	students[0]	| 0x456 | 0x123
//				|-------|
//	students[1]	| 0x789 |
//				|-------|
//				|  ...  |
//				|-------|
//	 student1	|0x1111 |
//				|-------|
//	 student2	|0x2222 |
//				|-------|
//				|  ...  |
//				|-------|
//		  		|"11025"| 0x1111
//		  		| 전지현|		
//				|-------|
//				|"11026"| 0x2222
//  			| 정우성|
//				|-------|
//				|  ...  |
//				|-------|
		
		
	}

}
