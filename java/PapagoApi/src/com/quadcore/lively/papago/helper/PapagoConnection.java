package com.quadcore.lively.papago.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.quadcore.lively.papago.config.PapagoConfiguration;

public class PapagoConnection {
	/**
	 *  -d "source=ko&target=en&text=만나서 반갑습니다." -v
	 */
	
	public PapagoConnection() {
		
	}
	
	public String getJson (String textEnglish) throws IOException {
		
        URL url = new URL(PapagoConfiguration.getUrl());
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("X-Naver-Client-Id", PapagoConfiguration.getClientId());
        con.setRequestProperty("X-Naver-Client-Secret", PapagoConfiguration.getClientSecret());
        
        // post request
        String postParams = "source=en&target=ko&text=" + textEnglish;
        con.setDoOutput(true);
        
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        
        
        StringBuffer response = new StringBuffer();
        String inputLine;
        if(responseCode == 200) { // 정상 호출
        	try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        		while ((inputLine = br.readLine()) != null) {
        			response.append(inputLine);
        		}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        } else {  // 에러 발생
        	try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
        		while ((inputLine = br.readLine()) != null) {
        			response.append(inputLine);
        		}
				
        	} catch (IOException e) {
        		e.printStackTrace();
			}
        }
        
		return response.toString();
	}

	public void connect(char service, String url, boolean isPost) throws IOException {
		
		//get 메서드와 URL 설정
		// method 파악
		HttpPost post = null;
		HttpGet get = null;
		if (isPost) {
			// post
			post = new HttpPost(url);
		} else {
			// get
			get = new HttpGet(url);
		}
		
		//agent 정보 설정
		get.setHeader("User-Agent", "curl/7.49.1");
		get.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		get.addHeader("X-Naver-Client-Id", PapagoConfiguration.getClientId());
		get.addHeader("X-Naver-Client-Secret", PapagoConfiguration.getClientSecret());
		
		
        //get 요청
		System.out.println("GET Response Status");
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(get);
		
		String json = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		System.out.println(json);
		
		client.close();
		
	}
	
	
	
	
	
}
