package quiz04_employee;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
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
		if(classes < 1 || classes > 2) {
			throw new EmployeeException("메뉴에 해당하는 숫자를 입력해 주세요", 2);
		}
		
		if(classes == 1) {
			System.out.print("사원번호 >>> ");
			int emNo = sc.nextInt();
			System.out.print("사원이름 >>> ");
			String emName = sc.next();
			emName = emName.replaceAll(" ", "");
			sc.nextLine();
			System.out.print("월급금액 >>> ");
			int sal = sc.nextInt();
			Regular regular = new Regular(emNo, emName, sal);
			System.out.println(regular.toString());
			employees[idx++] = regular;
		} else if (classes == 2) {
			System.out.print("사원번호 >>> ");
			int emNo = sc.nextInt();
			System.out.print("사원이름 >>> ");
			String emName = sc.next();
			emName = emName.replaceAll(" ", "");
			Temporary temporary = new Temporary(emNo, emName);
			System.out.print("노동시간 >>> ");
			int workTime = sc.nextInt();
			temporary.setWorkTimes(workTime);
			System.out.print("시급 >>> ");
			int pay = sc.nextInt();
			temporary.setPay(pay);
			System.out.println("등록이 완료되었습니다.");
			System.out.println(temporary.toString());
			employees[idx++] = temporary;
		}
		
		
	}
	
	public void dropEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
		//삭제
		System.out.print("제거할 사원번호 >>> ");
		int emNo = sc.nextInt();
		for(int i = 0; i < idx; i++) {
			if(employees[i].getEmpNo() == emNo) {
				System.arraycopy(employees, i+1, employees, i, idx - i - 1);
				employees[--idx] = null;
				System.out.println("사원번호 " + emNo + " 사원을 삭제했습니다.");
				return;
			}
		}
		System.out.println("사원번호가 " + emNo + "인 사원이 존재하지 않습니다.");
	}
	
	public void findEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
		System.out.print("검색할 사원번호 >>> ");
		int search = sc.nextInt();
		for(int i = 0; i < idx; i++) {
			if(employees[i].getEmpNo() == search) {
				System.out.println("직원번호가 " + search + "인 사원은 " + employees[i].toString());
			}
		}
		// empNo를 받아 검색
	}
	
	public void printAllEmployees() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("EMPTY", 2);
		}
		System.out.println("직원목록");
		for(int i = 0; i < idx; i++) {
			System.out.println(employees[i].toString());
		}
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
			} catch(InputMismatchException e) {
				sc.next();
				System.out.println("명령은 정수로 입력");
			} catch(RuntimeException e) {
				System.out.println("실행에 오류가 있습니다.");
			}
			catch (EmployeeException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	
}
