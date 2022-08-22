package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiMain {
	
	public static void m1() {
		StringBuilder sbApiURL = null;
		
		// API 요청변수
		try {
			sbApiURL = new StringBuilder("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath");
			String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
			
			sbApiURL.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sbApiURL.append("&searchYear=2021"); // 조회하고자 하는 연도값
			sbApiURL.append("&siDo=1100"); // 시도 코드
			sbApiURL.append("&guGun=1125"); // 시군구 코드
			sbApiURL.append("&type=json"); // 결과형식(xml/JSON)
			sbApiURL.append("&numOfRows=100");
			sbApiURL.append("&pageNo=1");
			
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
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

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
		
		// API로부터 전달받은 json데이터
		String response = sb.toString();
		
		// File 생성
		File file = new File("accident_json.txt");
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
		File file = new File("accident_json.txt");
		StringBuilder sb = new StringBuilder();
				
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
			JSONObject obj = new JSONObject(sb.toString()); // 내용을 읽어서 String으로 보관
			JSONObject obj2 = obj.getJSONObject("items");
			JSONArray item = obj2.getJSONArray("item");
			
			List<Object> list = null;
			Iterator<Object> itr = null;
			String result = null;
			
			for(int i = 0; i < item.length(); i++) {
				String occrrncDt = item.getJSONObject(i).getString("occrrnc_dt");
				String occrrncDayCd = item.getJSONObject(i).getString("occrrnc_day_cd");
				int dthDnvCnt = item.getJSONObject(i).getInt("dth_dnv_cnt");
				int injpsnCnt = item.getJSONObject(i).getInt("injpsn_cnt");
				
				list = new ArrayList<Object>();
				
				
				Accident ac = new Accident(occrrncDt, occrrncDayCd, dthDnvCnt, injpsnCnt);
//				list.add("발생일자 " + occrrncDt + " " + weekNm + ", 사망자수 " + dthDnvCnt + "명, 부상자수 " + injpsnCnt + "명");
//				result = list.toString();
//				System.out.println(result);
//				itr = list.iterator();
				
				File finalFile = new File("accident.txt");
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(finalFile))){
					
					for(int j = 0; j < item.length(); j++) {
						bw.write(ac.result() + "\n");
					}
					bw.write(ac.result());
					System.out.println(ac.result());
					
				} catch (IOException e) {
					e.printStackTrace();
				} 

			}
			
		
		
				
		
		
	}
	
	
	
	public static void main(String[] args) {
		m1();
		m2();
		
	}
	
	
}

