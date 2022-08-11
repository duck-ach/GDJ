package ex03_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONReader {

	public static void main(String[] args) {
		
		File file = new File("C:\\storage", "product.json");
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null ) {
				sb.append(line); // "\n" 줄바꿈이 없어도 된다. JSON은 한줄로 쭈우우욱 있어도 형식대로만 존재한다면 데이터를 인식하므로.
			}
			String str = sb.toString();
//			System.out.println(str);
			JSONArray arr = new JSONArray(str);
			
			List<Product> products = new ArrayList<Product>();
			for(int i = 0, length = arr.length(); i <length; i++) {
				JSONObject obj = arr.getJSONObject(i);
				Product product = new Product();
				product.setNumber(obj.getString("number"));
				product.setName(obj.getString("name"));
				product.setPrice(Integer.parseInt(obj.getString("price")));
				
				products.add(product); // ArrayList에 저장
			}
			for(Product product : products) {
				System.out.println(product);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
