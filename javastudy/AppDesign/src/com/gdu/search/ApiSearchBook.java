package com.gdu.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Base64.Decoder;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;


public class ApiSearchBook {

	public static void main(String[] args) {
		
		String cilentId = "E4GVk9DUr941MwQkCkN3";
		String cilentSecret = "gj52u3dQ2n";
		
		
		try {
			Scanner sc = new Scanner(JOptionPane.showInputDialog("책 제목을 입력하세요."));
			
			String search = sc.next();
			
			cilentSecret = URLEncoder.encode(cilentSecret, "UTF-8");
			search = URLEncoder.encode(search, "UTF-8");
					
			String apiURL = "https://openapi.naver.com/v1/search/book?query=" + search;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", cilentId);
			con.setRequestProperty("X-Naver-Client-Secret", cilentSecret);
			
			BufferedReader br = null;
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			br.close();
			con.disconnect();
			
			// JSONObject를 이용하여 꺼내야함. (Property들 value 꺼낼때)
			JSONObject obj = new JSONObject(sb.toString());
			JSONArray items = obj.getJSONArray("items");
			
			File dir = new File("C:/download");
			if(dir.exists() == false) {
				dir.mkdirs();
			}
			
			search = URLDecoder.decode(search);
			
			File file = new File(dir, search + ".html"); // 이 파일로 데이터 보내는 거 하고싶은 것
			
			PrintWriter out = new PrintWriter(file);
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title=\"" + search + "검색결과\">");
			out.println("</head>");
			out.println("<body>");
			
			for(int i = 0; i < items.length(); i++) {
				String title = items.getJSONObject(i).getString("title").replaceAll(search, "<strong>" + search + "</strong>");
				out.println("<a href=\""+ items.getJSONObject(i).getString("link") +"\">" + title + "</a><br>");
				out.println("<img src=\"" + items.getJSONObject(i).getString("image") + "\" width=\"100px\">");
				out.println("<hr>");
			}
			
			out.println("</body>");
			out.println("</html>");
			
			out.close();
		} catch(Exception e) {
			
			try {
				File dir = new File("C:/download/log");
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				File file = new File(dir, "error_log.txt");
				PrintWriter out = new PrintWriter(file);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
				out.println("예외메시지\t" + e.getMessage());
				out.println("예외발생시간\t" + sdf);
				
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
