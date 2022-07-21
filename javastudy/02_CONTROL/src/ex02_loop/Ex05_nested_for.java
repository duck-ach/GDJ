package ex02_loop;

public class Ex05_nested_for {

	public static void main(String[] args) {
		
		// 1일차 1교시
		// 1일차 2교시
		// ..
		// 1일차 8교시
		// 2일차 1교시
		// ...
		// 3일차 8교시
				
		for(int day=1; day<=3; day++) {
			for(int hour=1; hour<=8; hour++) {
				System.out.println(day + "일차 " + hour + "교시");
			}
		}
		
		System.out.println();
		
		// 문제.
		// 전체 구구단
		// 2x1=2
		// 2x2=4
		// ..
		// 9x9=81
		
		for(int dan=2; dan<=9; dan++) {
			for(int z=1; z<=9; z++) {
				System.out.println(dan + "x" + z + "=" + (dan*z));
			}
			System.out.println();
		}

		
		//연습.
		//2x1=2
		//2x2=4
		//...
		//5x5=25	
		
		
		/*
		 * for(int dan=2; dan<=9; dan++) { for(int z=2; z<=9; z++) {
		 * System.out.println(dan + "x" + z + "=" + (dan*z)); if(dan==5 && z==5) {
		 * break; }
		 * 
		 * System.out.println(dan + "x" + z + "=" + (dan*z)); // 여기서 출력하면 5x4까지 된다. } }
		 */		 
		
		//라벨을 이용한 풀이
		outer : for(int dan=2; dan<=9; dan++) {
					for(int z = 1; z<=9; z++) {
						System.out.println(dan + "x" + z + "=" + (dan*z));
				
						if(dan == 5 && z == 5) {
							break outer;
						}
					}
				}
		
		//문제.
		//구구단을 출력하시오
		//2x1=2 3x1=3 ... 9x1=9
		//2x1=4 3x2=6 ... 9x2=18
		
		for(int a=1; a<=9; a++) {
			
			for(int b=2; b<=9; b++) {
				
				System.out.print(b+ "x" + a+ "=" + (b*a) + "\t");
				// 라인을 바꿔주면 안되니까 print로한다.

			}
			System.out.println();
		}
	}

}
