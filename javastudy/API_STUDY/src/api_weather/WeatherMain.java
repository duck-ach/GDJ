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



public class WeatherMain {

	public static void m1() {
		
		StringBuilder sbApiURL = null;
		
		// API 요청변수
		try {
			sbApiURL = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0");
			String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
			
			sbApiURL.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sbApiURL.append("&pageNo=1");
			sbApiURL.append("&numOfRows=1000");
			sbApiURL.append("&dataType=JSON");
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
			con.setRequestProperty("content-Type", "application/JSON; charset=UTF-8");
			
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
		File file = new File("C:\\storage", "api2.json");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		
	}
	
	public static void main(String[] args) {
		
		m1();
		
	}

}
