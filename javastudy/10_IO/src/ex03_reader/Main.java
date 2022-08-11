package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void m1() {
		
		File file = new File("C:\\storage", "m2.txt");
		// FileReader 클래스
		FileReader fr = null;
		
		try {
			// file 객체에 등록된 파일이 없으면 FileNotFoundException 발생
			fr = new FileReader(file);
			
			// File 입력 데이터
			// 1. 1글자 : int
			// 2. 여러글자 : char[]
			
			StringBuilder sb = new StringBuilder();
			// 1글자를 저장할 변수;
			int c;
			
			// read() 메소드
			// 1. 읽은 문자를 반환
			// 2. 모두 읽어서 읽을 문자가 없는 경우 -1 반환
			
//			while(true) {
//				c = fr.read();
//				if(c == -1) {
//					break;
//				}
//				sb.append((char)c);
//			}
//			String str = sb.toString();
//			System.out.println(str);
			
			// refactoring
			while((c = fr.read()) != -1) {
				sb.append((char)c);
			}
			String str = sb.toString();
			System.out.println(str);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m2() {
		
		File file = new File("C:\\storage", "m3.txt");
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(file);
			// 5글자를 저장할 배열
			char[] cbuf = new char[5];
			
			// read(char[]) 메소드
			// 1. 읽은 글자는 cbuf 배열에 저장
			// 2. 실제로 읽은 글자 수를 반환 (읽은 글자가 없을 경우 -1 반환)
			// [5] 배열에 13글자를 읽을 경우, [1. 5(반환)] [2. 5(반환)] [3. 3(반환)] [4. -1(반환)]
			
			// m3. txt파일 읽는 과정
			// 		readCnt 	cbuf
			// 1	5			a	p	p	l	e
			// 2 	5			\n	m	a	n	g
			// 3 	5			o	\n	a	n	g
			
			while(true) {
				// readCnt에는 5가 들어간다.
				int readCnt = fr.read(cbuf);
				
				if(readCnt == 1) {
					break;
				}
				for(int i = 0; i < readCnt; i++) { // 읽은 글자 수(readCnt)만큼 반복
					System.out.print(cbuf[i]);
				}
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m3() {
		// m3.txt 읽어서 String str에 저장하기
		File file = new File("C:\\storage", "m3.txt");
		FileReader fr = null;
		
		try {
//			fr = new FileReader(file);
//			
//			char[] cbuf = new char[5];
//			StringBuilder sb = new StringBuilder();
//			
//			while(true) {
//				int readCnt = fr.read(cbuf);
//				if(readCnt == -1) {
//					break;
//				} // 배열, 어디서부터, 몇개
//				sb.append(cbuf, 0, readCnt); // cbuf 배열의 인덱스 0부터 readCnt개만큼만 추가
//			}
//			String str = sb.toString();
//			System.out.println(str);
			
			fr = new FileReader(file);
			char[] cbuf = new char[5];
			StringBuilder sb = new StringBuilder();
			int readCnt;
			while((readCnt = fr.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
			}
			
			String str = sb.toString();
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m4() {
		
		// FileReader는 느리기 때문에
		// BufferedReader를 추가해서 속도를 향상 시킨다.
		
		// BufferedReader는 readLine() 메소드를 지원한다.
		// readLine() 메소드는 한 줄씩 읽어서 String에 저장한다.
		// 읽은 내용이 없으면 null을 반환한다.
		
		File file = new File("c:\\storage", "m3.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line).append("\n"); // = sb.append(line + "\n");
			}
			String str = sb.toString();
			System.out.println(str);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m5() {
		
		//try -catch-resources문으로 m4() 다시 풀기
		// File객체 없이도 풀 수 있음.
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"))) {
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			String str = sb.toString();
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
	}

}
