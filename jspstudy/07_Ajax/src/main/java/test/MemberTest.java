package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.junit.Test;

public class MemberTest {

	@Test
	public void 회원목록테스트() {
		
		// 회원목록을 반환하는 MemberListService() 동작 전반을 살펴보는 테스트
		
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			
			String apiURL = "http://localhost:9090/07_Ajax/member/list.do";
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject obj = new JSONObject(sb.toString());
			assertEquals(5, obj.getInt("count"));
			System.out.println("회원수 : " + obj.getInt("count"));
			
			br.close();
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
