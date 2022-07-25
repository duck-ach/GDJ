package ex02_datetime;

public class Ex01_System {

	public static void main(String[] args) {
		
		// 타임스탬프는 전산공부할 때 많이 나온다. 나노타임은 알고만 있기로하자.
		
		// 1. 타임스탬프(timestamp)
		// 1970-01-01 0:00부터 1/1000초마다 증가하는 long타입의 정수값 (1초에 1000개가 늘음)
		long timestamp = System.currentTimeMillis(); // currentTime = 현재시간 Millis 1000분의 1초
		System.out.println(timestamp);
		// 개발자들이 업로드 제목 정할 때, 파일이름 정할 때 사용 (시간은 계속 늘어나므로 중복X)
		// 경과시간 계산할 때
		
		
		// 2. 나노타임(nanoTime)
		// s > ㎳(1/1000) > ㎲(1/백만1000000) > ㎱(1/십억1000000000) > ㎰(1/1조1000000000000)
		// 나노초(㎱) 값을 가지는 long 타입의 정수값
		// 어떤 기준일자는 없어서 두 개의 나노초(㎱) 사이의 경과시간 계산용
		long beginTime = System.nanoTime();
		int total = 1 + 2 + 3 + 4 + 5;
		long endTime = System.nanoTime();
		System.out.println(total + " 계산에 걸린 시간 : " + (endTime - beginTime) + "㎱");
		
		
		
		
	}

}
