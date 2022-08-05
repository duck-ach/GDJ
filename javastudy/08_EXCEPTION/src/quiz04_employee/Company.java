package quiz04_employee;

import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Company {
	
	private Employee[] employees;
	private int idx;
	private Scanner sc;
	
	public Company() {
		employees = new Employee[5];
		sc = new Scanner(System.in);
	}

	public void addEmployee() throws EmployeeException {
		// Exception
		if(idx == employees.length) {
			throw new EmployeeException("Full", 1);
		}
		// 직급고르기
		System.out.print("1.Regular / 2.Temporary >>> ");
		int classes = sc.nextInt();
		if(classes == 1) {
			
		}
		System.out.print("사원번호 >>> ");
		int emNo = sc.nextInt();
		System.out.print("사원이름 >>> ");
		String emName = sc.nextLine();
		emName = emName.replaceAll(" ", "");
		Employee employee = new Employee(emNo, emName);
		
	}
	
	public void dropEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
	}
	
	public void findEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
		
		// empNo를 받아 검색
	}
	
	public void printAllEmployees() throws EmployeeException {
		
	}
	
	public void manage() {
		while(true) {
			try {
				System.out.print("1.추가 2.삭제 3.조회 4.목록 0.종료 >>> ");
				
				int choice = sc.nextInt();
				sc.nextLine();
				switch (choice){
				case 1 : addEmployee(); break;
				case 2 : dropEmployee(); break;
				case 3 : findEmployee(); break;
				case 4 : printAllEmployees(); break;
				case 0 : return;
				default : throw new RuntimeException("알 수 없는 명령입니다.");
				}
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	
}
