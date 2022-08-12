package ex05_inputstream;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.List;

import ex04_outputstream.User;

public class Main {

	public static void m1() {
	
		File file = new File("C:\\storage", "b1.bin");
		FileInputStream fis = null;
		
		try {
			
			// 바이트 입력 스트림 생성
			fis = new FileInputStream(file);
			
			// 입력 데이터 단위
			// 1. 1개 : int
			// 2. 여러 개 : byte[]
			
			// 모든 정보를 StringBuilder에 저장한 뒤 확인
			StringBuilder sb = new StringBuilder();
			byte[] b = new byte[5]; // 5바이트씩 읽기 위해서 준비
			int readByte = 0;
			
			// int read(byte[] b)
			// 읽은 내용은 byte 배열 b에저장
			// 읽은 바이트 수를 반환
			// 읽은 내용이 없으면 -1 반환

			while((readByte = fis.read(b)) != -1) { // readByte로 b(5개씩 읽기)를 해서 저장.
				sb.append(new String(b, 0, readByte)); // String객체를 생성하면서 offset과 length지정 String으로는 할 수있음.
			}
			
			// 문자를 byteStream으로 읽었기 때문에 문제가 발생
			System.out.println(sb.toString()); // Apple Mange 맛있��.
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
				
		
	}
	
	public static void m2() {
		// m1()의 문제를 해결하려면 문자로 바꿔주는 필터를 껴주면 된다. (InputStreamReader) [ byte단위의 2진수들을 문자로 바꿔준다. ]
		// 많이써야하니까 연습 많이 해두기
		
		// 바이트 입력 스트림을 문자 입력 스트림으로 변환 (InputStreamReader)
		
		File file = new File("C:\\storage", "b2.bin");
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			// 속도 향상을 하고 싶으면 BufferedReader를 끼울 수 있다.
			
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[5]; // 5글자씩 읽기 위해서
			int readCnt = 0;
			
			while((readCnt = isr.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(isr != null) isr.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void simsimhaeser() {
		
		try {
			// System.in : 키보드와 연결된 바이트 스트림
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("입력 >>> ");
			String str = br.readLine();
			System.out.println(str);
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		} 
 	}
	
	public static void m3() {
		
		// 변수를 그대로 입력 받는 DataInputStream
		
		File file = new File("C:\\storage", "b3.dat");
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			
			String name = dis.readUTF();
			int age = dis.readInt();
			double height = dis.readDouble();
			
			System.out.println(name + ", " + age + ", " + height);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dis != null) dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void m4() {
		
		// 객체를 그대로 입력 받는 ObjectInputStream
		File file = new File("C:\\storage", "b4.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>)ois.readObject();
			User user = (User) ois.readObject(); // classcastException
			
			for(User u : users) {
				System.out.println(u);
			}
			System.out.println(user);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois != null) ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
//		m1();
//		m2();
		m3();
//		m4();
//		simsimhaeser();
	}

}
