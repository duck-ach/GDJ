package ex02_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CSVWriter {

	public static void main(String[] args) {
		// CSV
		// 1. Comma Separate Values
		// 2. 콤마로 분리된 값들
		// 3. 확장자 .csv인 파일(기본 연결프로그램 - 엑셀, 메모장으로 오픈 가능)
		
		// C:\storage\product.csv
		// 제품번호, 제품명, 가격\n
		// 100,새우깡,1500\n
		// 101,양파링,2000\n
		// 102,홈런볼,3000\n
		
		List<String> header = Arrays.asList("제품번호", "제품명", "가격");
		List<String> product1 = Arrays.asList("100" , "새우깡" , "1500");
		List<String> product2 = Arrays.asList("101" , "양파링" , "2000");
		List<String> product3 = Arrays.asList("102" , "홈런볼" , "3000");
		
		List<List<String>> list = Arrays.asList(header, product1, product2, product3); // 2차원배열 (1차원배열이 여러개있다.)
		
		File file = new File("C:\\storage", "product.csv");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try { //java.nio.charset = nio는 newio이다.
			fw = new FileWriter(file, StandardCharsets.UTF_8); // 한글이 깨질수 있기 때문에 utf-8 세팅
			bw = new BufferedWriter(fw);

			for(int i = 0, length = list.size(); i<length; i++) {
				List<String> line = list.get(i);
				// 마지막요소면 enter 아니면 (,)
				for(int j = 0, size =line.size(); j <size; j++) {
					if(j == size -1) {
						bw.write(line.get(j) + "\n");
					} else {
						bw.write(line.get(j) + ",");
					}
				}
			}
		} catch(IOException e) {
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

}
