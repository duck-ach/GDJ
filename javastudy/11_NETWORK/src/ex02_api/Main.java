package ex02_api;

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


public class Main {

	// S%2FM%2Bl5y2TRDSRrmRIEX8Xjcg7bl4rnZAL%2FiIEPmLOt9tBrpkFTdhk3DvFsLT3fZl%2F4JqEP82TVdHhAVnY5Q%2BuQ%3D%3D
	// URLEncoder.encode("S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==,"UTF-8");
	// 위는 같은 코드이다. (인코딩한코드 == 원본데이터를 인코딩)
	
	// 요청 (어떤 데이터를 (처리)달라고 요청하는 것)
	// client -> server
	
	// 요청할 주소 : apiURL
	// 요청 파라미터(서버로 보내줄 데이터)
	// 필수/선택
	
	
	// apiURL?파라미터=값&파라미터=값...
	
	
	// 요청
	// 1. Request
	// 2. 클라이언트 - > 서버
	
	// 응답
	// 1. Response
	// 2. 서버 -> 클라이언트
	
	// 파라미터
	// 1. Parameter
	// 2. 보내는 데이터(변수 개념)
	
	public static void m1() {
		
		// 전국종량제봉투가격표준데이터

		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";
		
		try {
			// 원래는 스트링 += 대신 StringBuilder를 쓰는게 맞다.
			String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
			apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey ,"UTF-8");
			apiURL += "&pageNo="+ URLEncoder.encode("0", "UTF-8");
			apiURL += "&numOfRows="+ URLEncoder.encode("100", "UTF-8");
			apiURL += "&type="+ URLEncoder.encode("xml", "UTF-8");
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
//			apiURL += "&PRICE_1=0";
//	        apiURL += "&PRICE_1_HALF=0";
//	        apiURL += "&PRICE_2=0";
//	        apiURL += "&PRICE_2_HALF=0";
//	        apiURL += "&PRICE_3=0";
//	        apiURL += "&PRICE_5=160";
//	        apiURL += "&PRICE_10=310";
//	        apiURL += "&PRICE_20=0";
//	        apiURL += "&PRICE_30=0";
//	        apiURL += "&PRICE_50=0";
//	        apiURL += "&PRICE_60=0";
//	        apiURL += "&PRICE_75=0";
//	        apiURL += "&PRICE_100=3060";
//	        apiURL += "&PRICE_120=0";
//	        apiURL += "&PRICE_125=0";
//	        apiURL += "&CHRG_DEPT_NM=" + URLEncoder.encode("청결지도팀","UTF-8");
//	        apiURL += "&PHONE_NUMBER=" + URLEncoder.encode("032-450-5464", "UTF-8");
//	        apiURL += "&REFERENCE_DATE=" + URLEncoder.encode("2020-02-01", "UTF-8");
//	        apiURL += "&instt_code=B551295";
			
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			// Content-type
			// text/html
			// application/xml
			// application/json
			// 대부분 위의 세가지 content type을 사용한다.
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8"); 
		} catch(MalformedURLException e) {
			System.out.println("API 주소 오류");
		} catch(IOException e) {
			System.out.println("API 주소 접속 실패");
		}
		
		// 입력 스트림(응답)생성
		// 1. 응답 성공 시 정상 스트림, 실패 시 에러 스트림
		// 2. 응답 데이터는 StringBuilder에 저장
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
			
			// 스트림 종료
			reader.close();
			
			
		} catch(IOException e) {
			System.out.println("API 응답 실패");
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
		
		// xml 분석
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 접속종료
		con.disconnect();
		
		
	}
	
	public static void main(String[] args) {
		
		m1();
		

	}

}
