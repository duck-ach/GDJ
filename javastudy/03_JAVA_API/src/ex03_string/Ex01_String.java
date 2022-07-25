package ex03_string;

import java.util.Scanner;

public class Ex01_String {

	public static void main(String[] args) {
		
		// String에 관한 이해를 시작하겠다.
		String str1 = "hello";
		String str2 = "hello";
		
//		str1	0x123
//		str2	0x123			//0x123(참조값)을 이용하여 str1 == str2인것을 나타낸다.
//								//str1 == str2 true
//				hello	0x123
		
		System.out.println(str1 == str2); // true
		
		String str3 = new String("hi");
		String str4 = new String("hi");
		
//		str3	0x123
//		str4	0x456			//str3 == str4  false
//			
//				hi		0x123
//				hi		0x456
		System.out.println(str3 == str4); // false

		// 1. 문자열 동등 비교
		boolean result1 = str1.equals(str2); 
		boolean result2 = str3.equals(str4); 
		System.out.println(result1); // true
		System.out.println(result2); // true
		
		if(str1.equals(str2)) {
			System.out.println("str1, str2는 같아요");
		} else {
			System.out.println("str1, str2는 달라요");
		}
		if(!str3.equals(str4)) { 
			System.out.println("str3, st4는 달라요");
		} else {
			System.out.println("str3, str4는 같아요");
		}
		// !(NOT) 개발스탠다드 문서에는 가급적 사용하지말라고 명시되어있음.
		// 이유 : 잘 보이지 않아서
		// 그래도 사람들이 사용할 때는 사용함
		
		System.out.println();
		
		// 2. 대소문자를 무시한 문자열 동등비교
		String str5 = "Hello World";
		String str6 = "hELLO wORLD";
		boolean result3 = str5.equalsIgnoreCase(str6); // Ignore:무시 Case:대소문자 
														// Upper Case : 대문자 Lower Case : 소문자
		System.out.println(result3);
		
		System.out.println();
		
		// 3. 문자열 길이 반환
		String name = "Duck achk";
		int length = name.length();
		System.out.println("글자 수 : " + length); // 글자 수 : 5 (공백도 문자열에 포함됨)
		
		System.out.println();
		
		// 4. 특정 위치의 문자(char)만 반환
		// 특정 위치
		// 인덱스(Index)라고 함
		// 글자마다 부여된 정수값
		// 0으로 시작한다.
		
		System.out.println(name.charAt(0)); // D
		System.out.println(name.charAt(1)); // u
		System.out.println(name.charAt(2)); // c
		System.out.println(name.charAt(3)); // k
		System.out.println(name.charAt(4)); // 
		System.out.println(name.charAt(5)); // a 
		
		// char타입 != String타입
		// 현업에서는 char타입보다는 String타입으로 많이 사용해서, String으로도 문자열을 빼는 방법이 있지만,
		// 공부를 위해 char타입을 이용해서 해보았다.
		
		System.out.println();
		
		// 5. 문자열의 일부 문자열(String)을 반환
		// 	1) substring(begin) : 인덱스 begin(포함) 부터 끝까지 반환
		// 	2) substring(begin, end) : 인덱스 begin(포함) 부터 인덱스 end(불포함)까지 반환
		// 		ex) substring(0,1) = 첫번째 0만 출력
		// 	substring 완전 중요합니당! 연습하기
		System.out.println(name.substring(0,5));
		System.out.println(name.substring(5));
		
		System.out.println();
		
		// 6. 특정 문자열을 찾아서 해당 인덱스(int)를 반환
		//		1) indexOf
		// 			(1) 만약 중복값이 여러개 있다면, 발견된 첫번째 값을 반환한다.
		//			(2) 해당 값이 발견되지 않았을 때(존재하지 않을 때)는 -1을 반환한다.
		int idx1 = name.indexOf("Duck");
		int idx2 = name.indexOf("k");
		System.out.println(idx1); // 0
		System.out.println(idx2); // 3
		
		//		2) lastIndexOf
		//			(1) 발견된 마지막 문자열의 인덱스를 반환
		//			(2) 발견된 문자열이 없는 경우 -1을 반환
		int idx3 = name.lastIndexOf("Duck");
		int idx4 = name.lastIndexOf("k");
		System.out.println(idx3); // 0
		System.out.println(idx4); // 8
		
		System.out.println();
		
		// 7. 문자열이 특정 패턴으로 시작하는가?
		//	  startsWith(문자열)
		if(name.startsWith("D")) {
			System.out.println("D씨 입니다.");
		} else {
			System.out.println("D씨가 아닙니다.");
		}
		
		System.out.println();
		
		// 8. 문자열이 특정 패턴으로 끝나는지 여부를 boolean(true, false) 반환
		//    endsWith(문자열)
		String filename = "image.jpg"; // jpg, png로 끝나면 이미지
		if(filename.endsWith("jpg") || filename.endsWith("png")) {
			System.out.println("이미지 입니다.");
		} else {
			System.out.println("이미지가 아닙니다.");
		}
		
		System.out.println();
		
		// 9. 문자열이 특정 패턴을 포함하는지 여부를 boolean(true, false) 반환
		String email = "director0902@naver.com";
		if(email.contains("@") && email.contains(".")) {
			System.out.println("이메일 입니다.");
		} else {
			System.out.println("이메일이 아닙니다.");
		}
		
		// 현업에서는 못쓰지만 나중에 첨부파일제한을 하거나 파일 유효성 검사를 할 때 사용을 할 수 있다.
		
		System.out.println();
		
		// 10. 불필요한 공백 제거(좌우 공백)
		String message = "   안녕  하세요  ";
		System.out.println(message.trim()); // "안녕  하세요"
		System.out.println(message.trim().length());  // 7
		
		// 괜히 스페이스바를 마지막에 한번 더 치는 습관이라던지,
		// 불필요한 공백들을 받을때가 생각보다 많다.
		
		System.out.println();
		
		// 11. 대소문자 변환하기
		// toUpperCase() : 대문자로 변환하기
		// toLowerCase() : 소문자로 변환하기
		String source = "best of best";
		System.out.println(source.toUpperCase());
		System.out.println(source.toLowerCase());
		
		System.out.println();
		
		// 12. 찾아 바꾸기
		// replace(old, new) : old를 new로 변환하기
		String replaced = source.replace("best", "worst");
		System.out.println(source);
		System.out.println(replaced);
		
		// 주의. replacedAll() 메소드는 특정 문자열을 찾아서 변환하는 것이 아님.
		String ip = "192.168.101.91";
		String replacedIp = ip.replaceAll(".", "_"); // 192_168_101_91 를 기대하지만,
		// "." 이 (모든것)을 의미하므로
		System.out.println(replacedIp); // ______________
		// 백슬래시를 앞에 붙여주거나 대괄호로 감싸서 해결할 수 있다.
		String replacedIp2 = ip.replaceAll("[.]", "_");
		System.out.println(replacedIp); // 192_168_101_91
		
		System.out.println();
		
		// 13. 빈 문자열인지 여부를 검사한 뒤 boolean(true, false) 반환
		String id = " ";
		// 공백도 문자열로 인식한다.
		if(id.isEmpty()) {
			System.out.println("빈 문자열");
		} else {
			System.out.println("빈 문자열 아님");
		}
		
		// 공백을 무시한다. 
		if(id.isBlank()) {
			System.out.println("빈 문자열");
		} else {
			System.out.println("빈 문자열 아님");
		}
//		 isBlank는 JDK 11에 출시되었음. (신기술)
//		 trim을 넣지 않게 하기위해 출시하였지만
//		 빈 문자열을 그냥 무시하고 두게되므로 되도록 이용하지 않는게 좋다.
//		 최신코드는 항상 조심해야 한다.
		
		System.out.println();
		System.out.println( "연습문제 ==========");
		
		// 연습. 파일이름을 파일명과 확장자로 분리
		// 단, jpg, git, png 이미지인 경우에만 작업을 진행한다.
		String fullName = "apple.jpg";
		String fileName = ""; // apple
		String extName = ""; // jpg
		int idxOfDot = fullName.lastIndexOf(".");
		fileName = fullName.substring(0, idxOfDot);
		extName = fullName.substring(idxOfDot + 1);
		System.out.println(fileName);
		System.out.println(extName);
//		if(fullName.endsWith("jpg") || fullName.endsWith("git") || fullName.endsWith("png")) {
//			fileName = fullName.trim().substring(0,5);
//			extName = fullname.trim().substring(6);
//			System.out.println(fileName);
//			System.out.println(extName);
//		} else {
//			System.out.println("지원하지 않는 기능입니다.");
//		}
		
		
		
		// 연습. 문자열 "abc12345def67890ghijk" 에서
		// 아라비아 숫자12345, 67890을 제외하고 한 글자씩 화면에 출력하시오.
		String str = "abc12345def67890ghijk";
		
		
		
		for(int i = 0; i <str.length(); i++) {
			char ch = str.charAt(i);
			if(ch >= '0' && ch<='9') {
				continue;
			}
			System.out.println(ch);
		}
		
		//System.out.println(syn.trim().substring(0,3) + syn.trim().substring(8,11) + syn.trim().substring(16));
		
		
		
		
		// 연습. 대문자 6자리로 구성된 인증코드 작성하기
		StringBuilder sbCode = new StringBuilder();
		
		for(int i=0; i<6; i++) {
		sbCode.append((char)((int)(Math.random() * 26) + 'A'));
		}
		String code = sbCode.toString();
		System.out.println("인증코드 : " + code);
		
		
		// 연습. 1 2 3 4 5 6 7 8 9 10 만들기
		Scanner sc = new Scanner(System.in);
		StringBuilder sbPaging = new StringBuilder();
		
		for(int n = 1; n <= 10; n++) {
			sbPaging.append(n + " ");
		}
		String paging = sbPaging.toString();
		System.out.println(paging);
		
		
		// 연습. char 타입의 성별을 입력 받기
		
		System.out.print("성별(남/여)을 입력하세요 >>> ");
		char gender = sc.next().charAt(0);
		System.out.println(gender);
		sc.close();
		
		
		
		
	} 

}
