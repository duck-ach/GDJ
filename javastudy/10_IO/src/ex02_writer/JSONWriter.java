package ex02_writer;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONWriter {

	public static void m1() {

		
		// JSON
		// 1. JavaScript Object Notation
		// 2. 자바스크립트 객체 표기법
		// 3. 객체 : { }
		// 4. 배열 : [ ]
		
		// JSON-Java(JSON in Java) 라이브러리
		// 1. 객체 : JSONObject 클래스 (Map 기반)
		// 2. 배열 : JSONArray 클래스 (List 기반)
		
		// org.json 패키지에 JSON이 다보인다.
		JSONObject obj = new JSONObject();
		obj.put("name", "엄희라");
		obj.put("age", 24);
		obj.put("man", false);
		obj.put("height", 162.4);
		System.out.println(obj.toString());
		
	}

	public static void m2() {
		
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "제임스");
		obj1.put("age", 30);
		
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "에밀리");
		obj2.put("age", 40);
		
		// 객체가 2개이상 == 배열
		JSONArray arr = new JSONArray();
		arr.put(obj1);
		arr.put(obj2);
		
		System.out.println(arr.toString());
	}
	
	public static void m3() {
		
		String str = "{\"name\":\"엄희라\",\"man\":false,\"age\":24,\"height\":162.4}";
		
		JSONObject obj = new JSONObject(str);
		
		String name = obj.getString("name");
		boolean man = obj.getBoolean("man");
		int age = obj.getInt("age");
		double height = obj.getDouble("height");
		
		System.out.println(name);
		System.out.println(man);
		System.out.println(age);
		System.out.println(height);
		
	}
	public static void main(String[] args) {
		
//		m1();
//		m2();
		m3();
	}

}
