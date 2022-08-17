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



public class CoronaAPI {

	public static void m1() {
		
		String apiURL = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson";
		
		try {
			String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
			apiURL += "?ServiceKey=" + URLEncoder.encode(serviceKey, "UTF-8"); // 인증키
			apiURL += "&pageNo=1"; // 페이지 번호
			apiURL += "&numOfRows=10"; // 한 페이지 결과 수
			apiURL += "&startCreateDt=20200410"; // 검색할 생성일 범위의 시작
			apiURL += "&endCreateDt=20200410"; // 검색할 생성일 범위의 종료
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// API 주소 접속
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			url = new URL(apiURL);
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
			while((line = reader.readLine()) != null) {
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
		File file = new File("C:\\storage", "corona.xml");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		m1();

	}

}
