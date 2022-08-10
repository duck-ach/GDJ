package ex01_file;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Main {

	public static void m1() {
		
		// File 클래스
		// 1. 패키지 : java.io
		// 2. 파일 및 디렉터리 관리
		// 3. 생성 방법
		// 		1) new File(경로, 파일)
		//		2) new File(파일)
		// 4. 윈도우의 경로 구분 방법 : 백슬래시(\)
		// 5. 리눅스의 경로 구분 방법 : 슬래시(/)
		// C드라이브일 경우 소문자대문자 상관없고 :(콜론)만 들어가면 드라이브가 된다.
		// mkdir() : 1개의 폴더 만들기
		// mkdirs() : 1개이상의 폴더 만들기(갯수에 구애받지 않음)
		
		// 폴더(디렉터리) 만들기
		File dir = new File("C:\\storage");
		
		// 존재하지 않으면 만들겠다.
		if(dir.exists() == false) { // if(!dir.exists())
			dir.mkdirs();
		} // 존재하면 삭제한다.
		else {
			dir.delete();
		}
		
	}
	
	public static void m2() {
		
		File file = new File("C:\\storage", "my.txt");
		
		try {
			if(file.exists() == false) {
				file.createNewFile();
			}
			else {
				file.delete();
			}
		} catch(IOException e) {
			// 개발할 때 넣는 catch 블록 코드
			e.printStackTrace(); // 에러를 콘솔에 찍어라.
		}
		
	}
	
	public static void m3() {
		File file = new File("C:\\storage", "my.txt");
		System.out.println("파일명 : " + file.getName());
		System.out.println("경로 : " + file.getParent()); // 자신이 가진 상위폴더(경로)를 가지고와라.
		System.out.println("전체경로(경로+파일명) : " + file.getPath());
		
		System.out.println("디렉터리인가? " + file.isDirectory());
		System.out.println("파일인가? " + file.isFile());
		
		// 마지막으로 수정된 날짜
		long lastModifiedDate = file.lastModified(); // timestamp값으로 반환해주겠다.
		
		String lastModified = new SimpleDateFormat("a hh:mm yyyy-MM-dd").format(lastModifiedDate);
		System.out.println("수정한날짜 : " + lastModified);
		// 오전 09:50 2022-08-10
		
		
		long size = file.length(); // 바이트 단위
		System.out.println("파일크기 : " + size + " byte");
	}
	
	public static void m4() {
		
		File dir = new File("C:\\GDJ");
		
		File[] list = dir.listFiles(); // 디렉터리 내부의 모든 파일 디렉터리를 File객체로
		for(int i = 0; i <list.length; i++) {
			System.out.println(list[i].getName());
		}
		
		
	}
	
	public static void q1() {
//		File file = new File("C:\\GDJ");		
//		File[] list = file.listFiles();
//		for(int i = 0; i < list.length; i++) {
//			long lastModifiedDate = file.lastModified(); 
//			String lastModified = new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(lastModifiedDate);
//			System.out.print(lastModified + " \t");
//			
//			long size = list[i].length();
//
//			if(list[i].isDirectory()) {
//				System.out.print("<DIR>" + "\t" + "\t");
//			} else {
//				System.out.print("\t" + size + "\t");
//			}
//			
//			System.out.println(list[i].getName());
//		}
		
		// 선생님이 풀어준거 
		File dir = new File("C:\\GDJ");
		File[] list = dir.listFiles();
		int dirCnt = 0;
		int fileCnt = 0;
		long totalSize = 0;
		
		for(File file : list) {
			
			if(file.isHidden()) {
				continue;
			}
			String lastModifide = new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(file.lastModified());
			String directory = "";
			String size = "";
				if(file.isDirectory()) {
					directory = "<DIR>";
					size = "     ";
					dirCnt++;
				} else {
					directory = "     ";
					size = new DecimalFormat("#,##0").format(file.length()) + "";
					fileCnt++;
					totalSize += Long.parseLong(size);
				}
				String name = file.getName();
				
				System.out.println(lastModifide + " " + directory + " " + size + " " + name);
		}
		
		System.out.println(fileCnt + "개 파일 " + new DecimalFormat("#,##0").format(totalSize));
		
		// DecimalFormat 숫자에 패턴
		
		
	}
	
	
	public static void main(String[] args) {
		
//		m1();
//		m2();
//		m3();
//		m4();
		q1();
	}

}
