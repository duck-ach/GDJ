package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class NaverCaptchaServiceImpl implements NaverCaptchaService {

	// field
	private final String CLIENT_ID = "E4GVk9DUr941MwQkCkN3";
	private final String CLIENT_SECRET = "gj52u3dQ2n";
	
	@Override
	public String getCaptchaKey() {
		
		// code=0 : "키 발급", code=1 : "사용자 입력값 검증"
		// 반환할 
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=0";
		
		// apiURL접속
		URL url = null;
		HttpURLConnection con = null;
		
		String key = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			
			// 요청 메소드(HTTP 메소드)
			con.setRequestMethod("GET"); // 대문자로 작성할 것
			
			// 요청 헤더 : 클라이언트 아이디, 클라이언트 시크릿
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// 입력 스트림 생성(네이버 API서버의 정보를 읽기 위함)
			BufferedReader reader = null;
			if(con.getResponseCode() == 200) { // HttpConnection.HttpOK
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// 네이버 API서버가 보낸 데이터 저장
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			// 네이버 API서버가 보낸 데이터 확인 및 반환
			JSONObject obj = new JSONObject(sb.toString());
			key = obj.getString("key");
			
			// 자원 반납
			reader.close();
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 요청 헤더 : 클라이언트 아이디, 클라이언트 시크릿
		
		return key;
	}

	@Override
	public Map<String, String> getCaptchaImage(HttpServletRequest request, String key) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
		
		// apiURL접속
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			
			// 요청 메소드(HTTP 메소드)
			con.setRequestMethod("GET"); // 대문자로 작성할 것
			
			// 요청 헤더 : 클라이언트 아이디, 클라이언트 시크릿
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// 응답이 성공하면 이미지(JPG)가 응답
			if(con.getResponseCode() == 200) { // HttpConnection.HttpOK
				// 폴더이름
				String dirname = "ncaptcha";
				// 이미지 경로
				String realPath = request.getServletContext().getRealPath("ncaptcha");
				File dir = new File(realPath);
				if(dir.exists() == false) {
					dir.mkdirs(); // 항상 s붙은거 써랏
				}
				// 캡챠 이미지 이름은 timestamp값을 준다.
				String filename = System.currentTimeMillis() + ".jpg";
				// 캡챠 이미지 객체 생성
				File file = new File(dir, filename);
				// 네이버 API로부터 정보를 읽어서(in) 서버경로에 저장(out)
				BufferedInputStream in = new BufferedInputStream(con.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				// 저장(다운로드)
				byte[] b = new byte[1024];
				int readByte = 0;
				while((readByte = in.read(b)) != -1) { // read의 경우 -1이 파일 읽기 끝나는 것
					out.write(b, 0, readByte); // b바이트로 저장된 0번 인덱스부터 readByte까지
				}
				// login.jsp로 전달할 데이터를 보관해주기 위해(캡차이미지 경로 + 파일명)
				map.put("dirname", dirname);
				map.put("filename", filename);
				// 자원반납
				out.close();
				in.close();
			} 
			// 응답이 실패하면 텍스트 형식으로 응답
			else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				// 네이버 API서버가 보낸 데이터 저장
				StringBuilder sb = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				System.out.println("응답 실패 사유");
				System.out.println(sb.toString());
				reader.close();
			}
		
			// 자원 반납
			con.disconnect();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
				
	}
	
	@Override
	public void refreshCaptcha(HttpServletRequest request, HttpServletResponse response) {

		// 응답 데이터 형식 : JSON
		response.setContentType("application/json");
		
		// 응답 데이터
		// 캡차키 + 캡차이미지 새로 요청해서 JSON 생성
		/*
			{
				"dirname": "ncaptcha",
				"filename": "1111111111.jpg"
			}
		*/
		
		String key = getCaptchaKey();
		Map<String, String> map = getCaptchaImage(request, key); // captcha요청 다시하는것(키하고 이미지 다시 받아옴)
		JSONObject obj = new JSONObject(map); // map<> 전달
		
		// 응답
		try {
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public boolean validateUserInput(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

}
