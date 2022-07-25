package ex03_string;

public class Ex02_StringBuilder {

	public static void main(String[] args) {
		
		// java.lang.StringBuilder 클래스
		
		StringBuilder sb = new StringBuilder();
		sb.append(1);
		sb.append(true);
		sb.append('T');
		sb.append(3.14);
		sb.append("hello");
		System.out.println(sb); // 1trueT3.14hello
		
		// "1" + "true" + "T" + "3.14" + "hello" 와 같음.
		
		// hello가 포함되었는가?
		// sb.contains("hello"); 안된다.
		
		// 첫 번째 글자 가져오기
		System.out.println(sb.charAt(1));
		System.out.println(sb.substring(0, 1));
		
		// 동등비교
		System.out.println(sb.equals("1trueT3.14hello")); // false
		
		// StringBuilder은 String이 아니다. Builder다.
		// StringBuilder로 만든 문자열은 반드시 마지막에 String으로 변환해야 함.
		String result = sb.toString();
		
		// 동등비교 2
		System.out.println(result.equals("1trueT3.14hello")); // true
		
		// 간단한테스트. 성능비교
		// 아래 문장을 출력했을때 String을 계속 합치는것과 StringBuilder를 활용한 방법 성능테스트
		// abcdefghijklmnopqrstuvwxyz
		
		// String을 합치는 것
		String alphabet1 = "";
		long begin1 = System.nanoTime();
		for(char ch='a'; ch<='z'; ch++) {
			alphabet1 += ch;
		}
		long end1 = System.nanoTime();
		System.out.println((end1 - begin1) + " " + alphabet1);
		
		// StringBuilder
		StringBuilder sb2 = new StringBuilder();
		long begin2 = System.nanoTime();
		for(char ch='a'; ch<='z'; ch++) {
			sb2.append(ch);
		}
		long end2 = System.nanoTime();
		String alphabet2 = sb2.toString();
		System.out.println((end2 - begin2) + " " + alphabet2);
		
		// 결론 : StringBuilder가 더 빠르다.
		
	
	}

}
