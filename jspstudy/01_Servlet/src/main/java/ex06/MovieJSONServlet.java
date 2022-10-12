package ex06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@WebServlet("/MovieJSONServlet")
public class MovieJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트 아이디/시크릿
		String clientId = "E4GVk9DUr941MwQkCkN3";
		String clientSecret = "gj52u3dQ2n";
		
		// 요청
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String query = request.getParameter("query");
		String display = request.getParameter("display");
		
		// 검색어 UTF-8 인코딩
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			 response.setContentType("text/plain; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("검색어 인코딩 실패");
	         out.close();
		}
		
		
		// 응답할 JSON 객체 만들기
		JSONObject obj = new JSONObject();
		obj.put("query", query);
		obj.put("display", display);
		
		// API 접속
		String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + query + "&display=" + display;
		URL url = null;
		HttpsURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpsURLConnection)url.openConnection();
			
		} catch (MalformedURLException e) {
			response.setContentType("text/plain; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("API URL이 잘못되었습니다.");
	         out.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("API 연결에 실패했습니다");
	         out.close();
		}
		
		// API 요청
		try {
			// 요청 메소드
			con.setRequestMethod("GET");
			// 요청 헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		} catch (ProtocolException e) {
			 response.setContentType("text/plain; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("API 요청에 실패했습니다");
	         out.close();
		}
		
		// API 응답 스트림 생성 (정상 스트림, 에러 스트림)
		BufferedReader reader = null;
		try {
			int responseCode = con.getResponseCode();
			if(responseCode == HttpsURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));				
			}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("API 응답 스트린 생성이 실패했습니다");
	         out.close();
		}
		
		// API 응답 데이터 저장하기
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			 response.setContentType("text/plain; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("API 응답이 실패했습니다");
	         out.close();
		}
		
		// 클라이언트 html로 API 응답 결과 보내기
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
