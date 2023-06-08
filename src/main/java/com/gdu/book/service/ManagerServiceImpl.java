package com.gdu.book.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManagerServiceImpl implements ManagerService {

	@Override
	public Map<String, Object> addBook(HttpServletRequest request) {
	    
    String query = request.getParameter("query");
	
	String clientId = "RoXpq8aHMxKbbLSBeIrg";
	String clientSecret = "V5clNGosjT";
	try {
	  
	  String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + URLEncoder.encode(query, "UTF-8"); 
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
	  
	  JSONObject obj = new JSONObject(sb.toString());
	  
	  Map<String, Object> map = new HashMap<String, Object>();
	  
	  map.put("books", obj);
	      
      return map;
	    }catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	
	
	
}
