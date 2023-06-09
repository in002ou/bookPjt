package com.gdu.book.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManagerServiceImpl implements ManagerService {

  @Override
  public String addBook(HttpServletRequest request) {
    
    String query = request.getParameter("query");
    int display = 100;
    String sort = "sim";
    
    String clientId = "RoXpq8aHMxKbbLSBeIrg";
    String clientSecret = "V5clNGosjT";
    
    try {
      
      String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + URLEncoder.encode(query, "UTF-8") + "&display=" + display + "&sort=" + sort; 
      URL url = new URL(apiURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      
      conn.setRequestMethod("GET");
      conn.setRequestProperty("X-Naver-Client-Id", clientId);
      conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
      
      BufferedReader br = null;
      if(conn.getResponseCode() == 200) {
        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
      } else {
        br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      
      StringBuilder sb = new StringBuilder();
      String line = null;
      
      while((line = br.readLine()) != null) {
        sb.append(line);
      }
      br.close();
      
      conn.disconnect();
      
      return sb.toString();
      /*
      JSONObject obj = new JSONObject(sb.toString());
      JSONArray bookList = obj.getJSONArray("items");
      
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("books", bookList);
      return map;
      */
      
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
	
	
	
}
