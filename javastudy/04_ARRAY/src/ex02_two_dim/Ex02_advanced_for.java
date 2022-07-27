package ex02_two_dim;

public class Ex02_advanced_for {

	public static void main(String[] args) {
		
		// 2차원 배열을 향상 for문으로 만들 수 있는가?
		
		String[][] timeTable = {
				{"국어", "윤리", "수학", "영어"},
				{"미술", "수학", "과학"},
				{"체육", "사회", "수학", "영어"},
				{"국어", "한자", "문학", "수학", "영어"},
				{"음악", "국어", "수학", "영어"}
		};
		// 일반 for문
		for(int i = 0; i<timeTable.length; i++) {
			for(int j = 0; j<timeTable[i].length; j++) {
				System.out.print(timeTable[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		// 향상 for문
		
		for(String[] weekName : timeTable) {
			for(String course : weekName) {
				System.out.print(course + " ");
			}
			System.out.println();
		}
		// 			|---------|
		// 	  arr	|  0x123  |
		// 			|---------|
		// 	arr[0]	| 0x1000  |
		// 			|---------|
		// 	arr[1]	| 0x2000  |
		// 			|---------|
		// 	arr[2]	| 0x3000  |
		//			|---------|
		// 			|		  |
		//			|---------|
		// arr[0][0]|    0    | 0x1000
		// 			|---------|
		// arr[0][1]|	 0	  |
		// 			|---------|
		// 			|		  |
		// 			|---------|
		// arr[1][0]| 	 0	  | 0x2000
		// 			|---------|
		// arr[1][1]| 	 0 	  |
		// 			|---------|
		// 			|		  |
		// 			|---------|
		// arr[2][0]|    0 	  | 0x3000
		// 			|---------|
		// arr[2][1]| 	 0 	  |
		//			|---------|
		
		
		
		

	}

}
