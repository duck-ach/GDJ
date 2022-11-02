package com.gdu.app05.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieServiceImpl implements MovieService {

	// API 
	@Override
	public String getBoxOffice(String targetDt) {

		String key = "ede54cd52122fc564af09cd5f3449e56";
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=" + key + "&targetDt=" + targetDt;
		StringBuilder sb = null;
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// ��û �޼ҵ�(GET OR POST)
			con.setRequestMethod("GET"); // �빮�ڷ� �ۼ��� ��
			
			// 
			BufferedReader reader = null;
			if(con.getResponseCode() == 200) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			sb = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			reader.close();
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}

}
