package com.gdu.app05.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.gdu.app05.domain.Movie;

public class MovieServiceImpl implements MovieService {

	// API �ϱ�
	@Override
	public String getBoxOffice(String targetDt) {

		// key
		String key = "ede54cd52122fc564af09cd5f3449e56";
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=" + key + "&targetDt=" + targetDt;
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			
			// ��û �޼ҵ�(GET OR POST)
			con.setRequestMethod("GET"); // �빮�ڷ� �ۼ��� ��
			
			// �Է� ��Ʈ�� ����
			BufferedReader reader = null;
			if(con.getResponseCode() == 200) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			reader.close();
			con.disconnect();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key;
	}

}
