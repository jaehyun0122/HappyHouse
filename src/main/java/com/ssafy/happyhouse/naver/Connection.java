package com.ssafy.happyhouse.naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component
public class Connection {
	public String searchImg(String searchName) {
		HttpURLConnection conn = null;
		String responseJson = null;
		try {
			searchName = URLEncoder.encode(searchName, "UTF-8");

			URL url = new URL(Global.API_SEARCH_IMG_URL+"?query="+searchName);
		
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", Global.X_Naver_Client_Id);
			conn.setRequestProperty("X-Naver-Client-Secret", Global.X_Naver_Client_Secret);
			
			int responseCode = conn.getResponseCode();
			if(responseCode == 400) {
				System.out.println("waitingLine : 400");
			}
			else if(responseCode == 401) {
				System.out.println("waitingLine : 401");
			}
			else if(responseCode == 500) {
				System.out.println("waitingLine : 500");
			}
			else {
				// Success
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while( (line = br.readLine()) != null) {
					sb.append(line);
				}
				responseJson = sb.toString();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseJson;
	}
	
	public String searchNews(String searchKeyword) {
		HttpURLConnection conn = null;
		String responseJson = null;
		try {
			searchKeyword = URLEncoder.encode(searchKeyword, "UTF-8");
			URL url = new URL(Global.API_SEARCH_NEWS_URL+"?query="+searchKeyword);

		
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", Global.X_Naver_Client_Id);
			conn.setRequestProperty("X-Naver-Client-Secret", Global.X_Naver_Client_Secret);
			
			int responseCode = conn.getResponseCode();
			if(responseCode == 400) {
				System.out.println("waitingLine : 400");
			}
			else if(responseCode == 401) {
				System.out.println("waitingLine : 401");
			}
			else if(responseCode == 500) {
				System.out.println("waitingLine : 500");
			}
			else {
				// Success
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while( (line = br.readLine()) != null) {
					sb.append(line);
				}
				responseJson = sb.toString();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseJson;
	}
}
