package api_movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		String key = "ede54cd52122fc564af09cd5f3449e56";
		
		Scanner sc = new Scanner(System.in);
		System.out.print("날짜(yyyymmdd)>>>");
		String targetDt = sc.next().trim();
		
		try {
			key = URLEncoder.encode(key, "UTF-8");
			targetDt = URLEncoder.encode(targetDt, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}

		String apiURL = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=" + key + "&targetDt=" + targetDt;
		
		String response = getResponse(apiURL); // 메소드화 시키는 연습
		createFile(response);
		System.out.println(response);
	}
	
	public static void createFile(String response) { 
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\boxoffice.xml"))){
			bw.write(response);
			bw.flush();
		} catch (IOException e) {
			throw new RuntimeException("파일 생성 오류", e);
		}
		
	}
	
	public static String getResponse(String apiURL) { // 응답하는 메소드
		
		HttpURLConnection con = getConnection(apiURL); // 커넥션만 하는 메소드
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			} else {
				return readBody(con.getErrorStream());
			}			
		} catch (IOException e) {
			throw new RuntimeException("API 요청 오류", e);
		}
		
	}
	
	public static String readBody(InputStream in) {
		try(BufferedReader br = new BufferedReader(new java.io.InputStreamReader(in))) {
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
			
		} catch (IOException e) {
			throw new RuntimeException("API 응답 오류", e);
		}
	}
	
	
	private static HttpURLConnection getConnection(String apiURL) {
		try {
			
			URL url = new URL(apiURL);
			return (HttpURLConnection)url.openConnection();
			
		} catch (MalformedURLException e){
			throw new RuntimeException("API 주소 오류", e);
		} catch (IOException e) {
			throw new RuntimeException("API 연결 오류", e);
		}
		
	}

}
