package api_weather;

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




public class WeatherMain {

	public static void m1() {
		
		StringBuilder sbApiURL = null;
		
		// API 요청변수
		try {
			sbApiURL = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
			String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
			
			sbApiURL.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sbApiURL.append("&pageNo=1");
			sbApiURL.append("&numOfRows=1000");
			sbApiURL.append("&dataType=XML");
			sbApiURL.append("&base_date=20220818");
			sbApiURL.append("&base_time=0600");
			sbApiURL.append("&nx=58");
			sbApiURL.append("&ny=125");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
	
		try {
			url = new URL(sbApiURL.toString());
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("content-Type", "application/xml; charset=UTF-8");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 입력 스트림(response) 생성
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			reader.close();
			
		} catch (IOException e) {
			System.out.println("API 응답실패");
		}
		
		// API로부터 전달받은 데이터
		String response = sb.toString();
		
		// File 생성
		File file = new File("C:\\storage", "api2.xml");
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
		
		File file = new File("C:\\storage", "api2.xml");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			
			Element root = doc.getDocumentElement();
			
			NodeList items = root.getElementsByTagName("item");
			
			for(int i = 0; i < items.getLength(); i++) {
				Element item = (Element)items.item(i);
				NodeList categories = item.getElementsByTagName("category");
				Node category = categories.item(0);
				Node obsrValue = item.getElementsByTagName("obsrValue").item(0); // 하나만나오니까 item(0)
				String strCategory = null;
				switch(category.getTextContent()) {
				case "PTY": strCategory = "강수형태"; break;
				case "REH": strCategory = "습도"; break;
				case "RN1": strCategory = "강수량(1시간)"; break;
				case "T1H": strCategory = "기온"; break;
				case "UUU": strCategory = "동서바람속도"; break;
				case "VEC": strCategory = "풍향"; break;
				case "VVV": strCategory = "남북바람속도"; break;
				case "WSD": strCategory = "풍속"; break;
				}
				
				System.out.println(strCategory + ":" + obsrValue.getTextContent());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	// 기상청 RSS
	public static void m3() {
		
		String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5013061000";
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL.toString());
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 입력 스트림
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String response = sb.toString();
		
		// File생성
		File file = new File("C:\\storage","m7.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		
		
	}
	

	public static void m4() {
		// parsing 기상청 RSS
		
		File file = new File("C:\\storage" , "m7.xml");
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement(); // 최상위요소  
			
			StringBuilder sb = new StringBuilder();
			
			Node title = root.getElementsByTagName("title").item(0);
			sb.append(title.getTextContent() + "\n");
			
			Node pubDate = root.getElementsByTagName("pubDate").item(0);
			sb.append(pubDate.getTextContent() + "\n");
			
			NodeList data = root.getElementsByTagName("data");
			System.out.println(title.getTextContent());
			System.out.println(pubDate.getTextContent());
			for(int i = 0; i < data.getLength(); i++) {
				Element item = (Element)data.item(i);
				Node hour = item.getElementsByTagName("hour").item(0);
				Node temp = item.getElementsByTagName("temp").item(0);
				Node wfKor = item.getElementsByTagName("wfKor").item(0);
				
				System.out.println(hour.getTextContent() + "시 \t" + temp.getTextContent() + "도 \t" + wfKor.getTextContent());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void m10() {
		
		File file = new File("C:\\storage", "sfc_web_map.xml");
		try {
			
			StringBuilder sb = new StringBuilder();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			
			Element root = doc.getDocumentElement(); // <current>
							// 타입을 Element로 바꿈
			Element weather = (Element)root.getElementsByTagName("weather").item(0);
			sb.append(weather.getAttribute("year") + "년");
			sb.append(weather.getAttribute("month") + "월");
			sb.append(weather.getAttribute("day") + "일");
			sb.append(weather.getAttribute("hour") + "시\n");
			
			NodeList locals = root.getElementsByTagName("local");
			for(int i = 0; i < locals.getLength(); i++) {
				Element local = (Element)locals.item(i);
				sb.append(local.getTextContent() + ":");
				sb.append(local.getAttribute("ta") + "℃");
				sb.append(local.getAttribute("desc") + "\n");
			}
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
//		m1();
//		m2();
//		m3();
//		m4();
		m10();
		
	}

}
