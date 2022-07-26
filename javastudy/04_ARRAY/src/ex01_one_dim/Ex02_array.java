package ex01_one_dim;

import java.util.Arrays;

public class Ex02_array {

	public static void main(String[] args) {
		
		// 배열의 초기화
//		int[] arr = new int[] {10, 20, 30, 40, 50};  // 선언과 함께 초기화 할 수 있다.
		int[] arr = {10, 20, 30, 40, 50}; // 위와 같은 코드. 이렇게 하면 더 간단하게 할 수 있다.
		
		// 문제.
		// 최대값 최소값 구하기
		// arr의 요소중에 최대값과 최소값을 구하라.
		int max = arr[0];
		int min = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			} 
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		System.out.println("최대 : " + max);
		System.out.println("최소 : " + min);
		
		System.out.println();
		
		// 이건 내가 알고리즘 풀어보다가 알게 된 것.
		Arrays.sort(arr);
		max = arr[arr.length-1];
		min = arr[0];
		System.out.println("최대 : " + max);
		System.out.println("최소 : " + min);
		
		

	}

}
