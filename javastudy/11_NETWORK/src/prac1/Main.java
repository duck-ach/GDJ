package prac1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	public static void main(String[] args) {
		
		
		try {
			// 접속
			String apiURL = "http://kma.go.kr/XML/weather/sfc_web_map.xml";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 접속 확인
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("API 접속 실패");
				return;
			}
			// 바이트 입력 스트림 -> 문자 입력 스트림 -> 버퍼 추가
			
			File file = new File("c:\\storage","sfc_web_map.xml");
	
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			String line = null;
			
			// leadLine()을 이용한 복사
			while((line = br.readLine()) != null) {
				bw.write(line + "\n");
			}
			
			br.close();
			bw.close();
			con.disconnect();
			
			
		} catch (MalformedURLException e) {
			System.out.println("API 주소에 오류가 있다.");
		} catch (IOException e) {
			System.out.println("API 서버 오류");
		}
		
		
		
		
	}

}
