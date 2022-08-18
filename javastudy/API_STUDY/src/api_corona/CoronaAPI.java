package api_corona;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class CoronaAPI {

	public static void m1() {
		
		
		StringBuilder sbApiURL = null;
		try {
			sbApiURL = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
			String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";
			sbApiURL.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8")); // 인증키
//			sbApiURL.append("&pageNo=1"); // 페이지 번호
//			sbApiURL.append("&numOfRows=10"); // 한 페이지 결과 수
			sbApiURL.append("&startCreateDt=20220808"); // 검색할 생성일 범위의 시작
			sbApiURL.append("&endCreateDt=20220812"); // 검색할 생성일 범위의 종료
			
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(sbApiURL.toString());
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");

		} catch (MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch (IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		// 입력 스트림(응답) 생성
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			} // ErrorStream을 하면 색깔이 나온다.
			
			String line = null;
			while((line = reader.readLine()) != null) { // readLine()은 BufferedReader에서만 지원함.
				sb.append(line + "\n");
			}
			
			// 스트림 종료
			reader.close();
			
		} catch (IOException e) {
			System.out.println("API 응답실패");
		}
		
		// API로부터 전달받은 xml 데이터
		String response = sb.toString();
		
		// File 생성
		File file = new File("C:\\storage", "api1.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		
	}

	public static void m2() {
		
		// xml 파싱
		
		File file = new File("C:\\storage", "api1.xml");
		StringBuilder sb;
		
		try {
			// 문서를 객체로 바꾸는 것이 DOM이다.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			sb = new StringBuilder();
			
			
			Element root = doc.getDocumentElement(); // 최상위 요소
			
			// <item>...</item> 태그 하나 == 특정 날짜의 데이터
			NodeList items = root.getElementsByTagName("item");
			
			for(int i = 0; i < items.getLength(); i++) {
				Node item = items.item(i); // NodeList에 요소들을 가져오는 것 item()
				NodeList itemChildren = item.getChildNodes();
				// Node StateDt == <stateDt>20220812</stateDt>
				// stateDt.getNodeName() 		= stateDt(태그Name)
				// stateDt.getTextContent() 	= 20220812 (태그 내부 텍스트)
				for(int j = 0; j < itemChildren.getLength(); j++) {
					Node itemChild = itemChildren.item(j);
					if(itemChild.getNodeName().equals("stateDt")) {
						sb.append(" 날짜 : ").append(itemChild.getTextContent()); 
					}
					else if(itemChild.getNodeName().equals("decideCnt")) {
						sb.append(" 확진자수 : ").append(itemChild.getTextContent());
					}
					else if(itemChild.getNodeName().equals("deathCnt")) {
						sb.append(" 사망자수 : ").append(itemChild.getTextContent());
					}
					sb.append("\n"); // 발견된 순서대로 출력된다.
				}
			}
			System.out.println(sb.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		
		m1();
		m2();
	}

}
