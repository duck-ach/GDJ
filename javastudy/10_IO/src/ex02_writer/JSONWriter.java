package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
	
	public static void m4() {
		
		String str = "[{\"name\":\"제임스\",\"age\":30},{\"name\":\"에밀리\",\"age\":40}]\r\n";
		
		JSONArray arr = new JSONArray(str); 
		System.out.println(arr.toString());
		
		// 값(Value)들을 추출하는 연습
		
		// 일반 for문
		for(int i = 0, length = arr.length(); i < length; i++) {
			JSONObject obj = arr.getJSONObject(i);
			String name = obj.getString("name");
			int age = obj.getInt("age");
			System.out.println(name + ", " + age);
		}
		
		System.out.println();
		
		// 향상 for문 // 데이터를 가져오는 get메소드로 ehdwkr. get()는 Object 반환
		for(Object o : arr) { // object객체로 캐스팅을 해와야 함.
			JSONObject obj = (JSONObject) o;
			String name = obj.getString("name");
			int age = obj.getInt("age");
			System.out.println(name + ", " + age);
		}
		
		
		
	}
	public static void m5() {

		List<String> product1 = Arrays.asList("100", "새우깡", "1500");
		List<String> product2 = Arrays.asList("101", "양파링", "2000");
		List<String> product3 = Arrays.asList("102", "홈런볼", "3000");
		
		List<List<String>> list = Arrays.asList(product1, product2, product3);
		
		JSONArray arr = new JSONArray();
		for(List<String> line : list) {
			JSONObject obj = new JSONObject();
			obj.put("number", line.get(0));
			obj.put("name", line.get(1));
			obj.put("price", line.get(2));
			// list의 요소들을 배열에 삽입
			arr.put(obj);

		}
		
		File file = new File("C:\\storage", "product.json");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			// list를 json String으로 만들어서
			// c:\\storage\\product.json 파일에 wirte();
			
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(arr.toString());
			
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
	}

}
