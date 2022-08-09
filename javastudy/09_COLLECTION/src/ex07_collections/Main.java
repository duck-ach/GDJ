package ex07_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void printMovies(List<String> list) {
		
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()) {
			
			String movie = itr.next();
			System.out.print(movie + " → ");
			if(itr.hasNext() == false) {
				System.out.println(movie);
			}
		}
		
		System.out.println();
		
		for(int i = 0, size = list.size(); i < size; i++) {
			System.out.print(list.get(i));
			if(i < size - 1) { // size - 1 : 마지막 요소의 인덱스
				System.out.print(" -> ");
			}
		}
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		
		List<String> movies = new ArrayList<String>();
		movies.add("아바타");
		movies.add("쇼생크탈출");
		movies.add("명량");
		movies.add("에일리언");
		movies.add("여인의향기");
		
		printMovies(movies);
		
		// movies 리스트를 오름차순 정렬시킴
		Collections.sort(movies);
		printMovies(movies);
		
		// movies 리스트를 내림차순 정렬시킴
		Collections.reverse(movies);
		printMovies(movies);
		
		// 특정 요소의 인덱스 반환
		// 이진 검색(binary search)을 이용하므로 검색 속도가 매우 빠름
		// 단, 크기순으로 정렬이 되어 있어야 한다.
		int idx = Collections.binarySearch(movies, "아바타");
		System.out.println(idx);
		
		// 정렬(sort)가 중요하지만, DB에서 정렬을 해오기때문에 JAVA에서는 정렬할 일이 별로 없다.
	}

}
