package ex02_api;

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
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main_JSON {

	// JSON
	// 경량화 데이터 라고한다. (light weight)
	// xml데이터는 줄바꿈도 자식으로 인식하지만, JSON데이터는 줄바꿈은 다 제거해주고 정말 자식요소만 추출해낼수있다.
	
	public static void m1() {
		// 전국종량제봉투가격표준데이터

				String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";
				
				try {
					// 원래는 스트링 += 대신 StringBuilder를 쓰는게 맞다.
					String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
					apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey ,"UTF-8");
					apiURL += "&pageNo="+ URLEncoder.encode("0", "UTF-8");
					apiURL += "&numOfRows="+ URLEncoder.encode("100", "UTF-8");
					apiURL += "&type="+ URLEncoder.encode("json", "UTF-8");
					apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
					apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
					apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
					apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
					apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
					apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
//					apiURL += "&PRICE_1=0";
//			        apiURL += "&PRICE_1_HALF=0";
//			        apiURL += "&PRICE_2=0";
//			        apiURL += "&PRICE_2_HALF=0";
//			        apiURL += "&PRICE_3=0";
//			        apiURL += "&PRICE_5=160";
//			        apiURL += "&PRICE_10=310";
//			        apiURL += "&PRICE_20=0";
//			        apiURL += "&PRICE_30=0";
//			        apiURL += "&PRICE_50=0";
//			        apiURL += "&PRICE_60=0";
//			        apiURL += "&PRICE_75=0";
//			        apiURL += "&PRICE_100=3060";
//			        apiURL += "&PRICE_120=0";
//			        apiURL += "&PRICE_125=0";
//			        apiURL += "&CHRG_DEPT_NM=" + URLEncoder.encode("청결지도팀","UTF-8");
//			        apiURL += "&PHONE_NUMBER=" + URLEncoder.encode("032-450-5464", "UTF-8");
//			        apiURL += "&REFERENCE_DATE=" + URLEncoder.encode("2020-02-01", "UTF-8");
//			        apiURL += "&instt_code=B551295";
					
					
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
					con.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); 
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
				File file = new File("C:\\storage", "api1.json");
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(file));
					bw.write(response);
					bw.close();			
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
	}
	
	public static void m2() {
		
		// JSONObject 클래스 : { }, 객체를 의미
		// JSONArray  클래스 : [ ], 배열을 의미
		File file = new File("C:\\storage", "api1.json");
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject(sb.toString()); // 내용을 읽어서 String으로 보관하라
		JSONObject obj2 = obj.getJSONObject("response");
		obj.getJSONObject("response"); // response의 값을 가져옴 // {"header":{"resultCode":"03","resultMsg":"NODATA_ERROR"}}
		JSONObject obj3 = obj2.getJSONObject("header");
		String resultCode = obj3.getString("resultCode");
		String resultMsg = obj3.getString("resultMsg");
		
		System.out.println(resultCode + ", " + resultMsg);
		
		
	}
	// {"프로퍼티" : "값"}
	// {property : value}
	
	// {"response":{"header":{"resultCode":"03","resultMsg":"NODATA_ERROR"}}}
	// 프로퍼티 = 1개 (response)
	// 값 = 1개 ({"header":{"resultCode":"03","resultMsg":"NODATA_ERROR"}})
	
	public static String m3() {
		StringBuilder sbApiURL = null;
		
		// API 요청변수
		try {
			sbApiURL = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
			String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
			
			sbApiURL.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sbApiURL.append("&pageNo=1");
			sbApiURL.append("&numOfRows=1000");
			sbApiURL.append("&dataType=JSON");
			sbApiURL.append("&base_date=20220819");
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
			con.setRequestProperty("content-Type", "application/json; charset=UTF-8");
			
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
		
		return response;
	}
	
	
	public static void m4() {
				
		JSONObject obj = new JSONObject(m3()); // property는 하나고 타입은 JSONObject
		JSONObject response = obj.getJSONObject("response"); // postman Library에서는 property를 key라고 부른다.
		JSONObject body = response.getJSONObject("body");
		JSONObject items = body.getJSONObject("items");
		JSONArray item = items.getJSONArray("item");
		
		for(int i = 0; i < item.length(); i++) {
			JSONObject element = item.getJSONObject(i); // 배열안의 객체무리를 가져와서 인덱스 지정
			// element.getString("category") : PTY
			// element.getString("obsrValue") : 0
			System.out.println(element.getString("category") + " : " + element.getString("obsrValue"));
			
		}
		
		
	}
	public static String m5() {
		StringBuilder sbApiURL = null;
		
		// API 요청변수
		try {
			sbApiURL = new StringBuilder("http://apis.data.go.kr/B553077/api/open/sdsc2/storeZoneOne");
			
//			String serviceKey = "S/M+l5y2TRDSRrmRIEX8Xjcg7bl4rnZAL/iIEPmLOt9tBrpkFTdhk3DvFsLT3fZl/4JqEP82TVdHhAVnY5Q+uQ==";
			String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";
			sbApiURL.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sbApiURL.append("&key=9940");
			sbApiURL.append("&type=json");
			
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
			con.setRequestProperty("content-Type", "application/json; charset=UTF-8");
			
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
		File file = new File("C:\\storage", "api3.json");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
		
	}
	
	public static void m6() {
//		System.out.println(m5());
		
		JSONObject obj = new JSONObject(m5());
		JSONObject header = obj.getJSONObject("header");
		JSONArray columns = header.getJSONArray("columns");
		for(int i = 0; i < columns.length(); i++) {
//			System.out.println(columns.getString(i));
		}
		JSONObject body = obj.getJSONObject("body");
		JSONArray items = body.getJSONArray("items");
		
		JSONObject item = items.getJSONObject(0);
//		System.out.print(item.toString());
		
//		String[] p = {"trarNo", "mainTrarNm", "ctprvnNm",  "signguCd", "signguNm", "trarArea", "coordNum", "stdrDt" };
		String[] p = {"trarNo", "mainTrarNm", "ctprvnCd", "ctprvnNm", "signguCd", "signguNm", "trarArea", "coordNum", "coords", "stdrDt"};
		
		Map<String, Object> map = new HashMap<String, Object>();
		for(int i = 0; i < columns.length(); i++) {
			map.put(columns.getString(i), item.get(p[i]));
		}
		System.out.println(map);
		
	}
	
	public static String m7() {
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
				sb.append(line);
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
		
		return sb.toString();
	}
	
	public static void m8() {
		
		JSONObject obj = XML.toJSONObject(m7());
		
		JSONArray dataList = obj.getJSONObject("rss")
								.getJSONObject("channel")
								.getJSONObject("item")
								.getJSONObject("description")
								.getJSONObject("body")
								.getJSONArray("data");
		for(int i = 0; i < dataList.length(); i++) {
			JSONObject weather = dataList.getJSONObject(i);
			System.out.println(weather.getInt("hour") + "시 : " + weather.getInt("temp") + "도, " + weather.getString("wfKor"));
			
		}
		
	}
	
	public static void main(String[] args) {
		
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
		m6();
//		m7();
//		m8();
	}

}
