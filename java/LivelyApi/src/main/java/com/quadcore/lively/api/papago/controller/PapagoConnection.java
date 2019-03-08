package com.quadcore.lively.api.papago.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PapagoConnection {
	
//	private String papagoId;
//	private String papagoPass;
//
//	public PapagoConnection(String papagoId, String papagoPass) {
//		this.papagoId = papagoId;
//		this.papagoPass = papagoPass;
//	}
//	
//	public String[] getJson(String[] args) throws IOException {
//		ArrayList<String> jsons = new ArrayList<String>();
//		
//		String[] results = null;
//		for (String argu : args) {
//			String result = getJson(argu);
//			if (!result.contains("errorCode")) {
//				jsons.add(argu + "\t" + result);
//			}
//			
//		}
//		results = new String[jsons.size()];
//		results = jsons.toArray(results);
//		
//		return results;
//		
//	}
//	
//	public String getJson (String textEnglish) throws IOException, NullPointerException {
//		
//        URL url = new URL(.getUrl());
//        
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("X-Naver-Client-Id", papagoId);
//        con.setRequestProperty("X-Naver-Client-Secret", papagoPass);
//        
//        // post request
//        String postParams = "source=en&target=ko&text=" + textEnglish;
//        con.setDoOutput(true);
//        
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(postParams);
//        wr.flush();
//        wr.close();
//        int responseCode = con.getResponseCode();
//        
//        
//        StringBuffer response = new StringBuffer();
//        String inputLine;
//        if(responseCode == 200) { // 정상 호출
//        	try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
//        		while ((inputLine = br.readLine()) != null) {
//        			response.append(inputLine);
//        		}
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//        	
//        } else {
//        	/**
//        	 * 에러발생시
//        	 * 
//        	 * ex)
//        	 * {
//        	 * 	"errorMessage":
//        	 * 	"{count/quota=10039/10000} : Query limit exceeded. (쿼리 한도를 초과했습니다.)",
//        	 * 	"errorCode":"010"
//        	 * }
//        	 * 
//        	 */
//        	try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"))) {
//        		while ((inputLine = br.readLine()) != null) {
//        			response.append(inputLine);
//        		}
//				
//        	} catch (IOException e) {
//        		e.printStackTrace();
//			}
//        }
//        
//		return response.toString();
//	}
//
//	public void connect(char service, String url, boolean isPost) throws IOException {
//		
//		//get 메서드와 URL 설정
//		HttpPost post = null;
//		HttpGet get = null;
//		if (isPost) {
//			// post
//			post = new HttpPost(url);
//		} else {
//			// get
//			get = new HttpGet(url);
//		}
//		
//		//agent 정보 설정
//		get.setHeader("User-Agent", "curl/7.49.1");
//		get.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//		get.addHeader("X-Naver-Client-Id", papagoId);
//		get.addHeader("X-Naver-Client-Secret", papagoPass);
//		
//		
//        //get 요청
//		System.out.println("GET Response Status");
//		CloseableHttpClient client = HttpClients.createDefault();
//		CloseableHttpResponse response = client.execute(get);
//		
//		String json = EntityUtils.toString(response.getEntity(), "UTF-8");
//		
//		System.out.println(json);
//		
//		client.close();
//		
//	}	
	
}
