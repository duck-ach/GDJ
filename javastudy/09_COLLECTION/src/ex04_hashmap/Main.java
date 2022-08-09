package ex04_hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	// Map : key와 Value값으로 이루어진 데이터
	// Array나 ArrayList의 경우 자동으로 index가 할당 되지만, Map의 key값은 자유롭게 만들면 된다.
	// value는 중복값이 있을 수 있으나, key값은 중복값이 있을 수 없다.
	
	// 예를들어 value = "kim"   20   "goodee"
	//      	key	  = "name" "age" "work"		이렇게 변수처럼 사용할 수 있다. (name을 부르면 kim을 불러온다.) 변수라고 생각하면됨.
	
	// key | value <- 이걸 합쳐서 `Entry`라고 부른다.
	// 기본적으로 for(Entry entry : map.entrySet()) map에있는 entry단위의 데이터를 빼올 수 있도록 향상 for문을 사용한다.
	// key값을 뺄 때 getKey(), value를 뺄 때 getValue() 메소드를 이용하여 빼면된다.
	
	public static void m1() {
		
		// Map 생성
		// Map<Key,Value>
		Map<String, String> dictionary = new HashMap<String, String>();
		
		// 추가 put(key, value);
		dictionary.put("apple", "사과");
		dictionary.put("banana", "바나나"); 
		dictionary.put("tomato", "토마토");
		dictionary.put("mango", "망고");
		dictionary.put("melon", "멜론");
		// 10개의 데이터가 아닌 5개의 데이터이다.

		// 수정
		// 기존의 key값을 사용하면 수정이 된다.
		dictionary.put("melon", "메론");
		
		// 삭제
		// 삭제할 요소의 key를 전달하면 삭제된다.
		// 삭제된 value가 반환된다.
		String removeItem = dictionary.remove("tomato");
		System.out.println(removeItem); // 토마토
		
		// 값(Value) 확인
		System.out.println(dictionary.get("apple")); // 사과
		System.out.println(dictionary.get("peach")); // null
		
		
		
		// 확인
		System.out.println(dictionary);
	}

	public static void m2() {
		
		
		
		// Value를 String으로 관리 (실무에서 두번째로 많이 보는 맵)
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("title", "어린왕자");
		map1.put("author", "생택쥐베리");
		map1.put("price", 10000 + ""); // "10000" 이렇게적어놓으면 인생에 도움이안된다.
		
		// Value를 Object로 관리 (실무에서 가장 많이 보는 맵)
		Map<String, Object> map2 = new HashMap<String,Object>();
		map2.put("title", "홍길동전");
		map2.put("author", "허균");
		map2.put("price", 20000);
		System.out.println(map2);
		
		// (대부분 String을 사용한다.)
		
		
	}
	
	public static void m3() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 3개의 Entry로 구성되어 있다.
		map.put("title", "소나기");
		map.put("author", "황순원");
		map.put("price", 20000);
		
		// Entry 단위로 순회(for) // Map.entry(interface)
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());	
		}
		
		// Key를 이용한 순회(for)
		for(String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		}
		
	}
	
	public static void m4() {
		
		// 연습.
		// title, author, price 정보를 가진 임의의 Map 3개를 만들고,
		// 생성된 Map 3개를 ArrayList에 저장한 뒤
		// ArrayList에 저장된 Map 3개를 for문으로 순회하시오.
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("title", "summer");
		map1.put("author", "hisahisijo");
		map1.put("price", 10000);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "어린왕자");
		map2.put("author", "생택쥐베리");
		map2.put("price", 12000);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("title", "홍길동전");
		map3.put("author", "허균");
		map3.put("price", 16000);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		for(Map<String, Object> map : list) { // List
			for(Map.Entry<String, Object> entry : map.entrySet()) { // Map
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			System.out.println();
		}
		
		

		
		
	}
	
	public static void main(String[] args) {
		
//		m1();
//		m2();
		m3();
//		m4();
	}

}
