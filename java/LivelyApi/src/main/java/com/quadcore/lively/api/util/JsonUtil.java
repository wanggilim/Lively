package com.quadcore.lively.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.quadcore.lively.api.twitter.model.TwitterGeoLoc;
import com.quadcore.lively.api.twitter.model.TwitterStatus;
import com.quadcore.lively.api.twitter.model.TwitterUser;

public class JsonUtil {
	public void createUserInfo(TwitterUser myU) {
		
		JsonArray service = new JsonArray();
		service.add(myU.getServiceName());
		
		// if (SNSUser instanceof TwitterUser) {
		
		// user identity
		JsonObject user = new JsonObject();
		user.addProperty("id", myU.getId());
		user.addProperty("verified", myU.isVerified());
		user.addProperty("name", myU.getName());
		user.addProperty("screen_name", myU.getScreenName());
		user.addProperty("url", myU.getUrl());
		user.addProperty("profile_url", myU.getProfileUrl());
		user.addProperty("profile_bigger_url", myU.getBiggerProfileUrl());
		user.addProperty("friends", myU.getFriendsCount());
		user.addProperty("followers", myU.getFollowersCount());
		user.addProperty("favorites", myU.getFavoritesCount());
		user.addProperty("email", myU.getEmail());
		
		service.add(user);
		
		// GeoLocation
		JsonArray geoArr = new JsonArray();
		JsonObject geoObj = new JsonObject();
		geoObj.addProperty("enabled", myU.getGeoLocation().isEnabled());
		TwitterGeoLoc geoloc = myU.getGeoLocation();
		geoObj.addProperty("location", geoloc.getLocation());
		geoObj.addProperty("latitude", geoloc.getLatitude());
		geoObj.addProperty("longitude", geoloc.getLongitude());
		geoArr.add(geoObj);
		
		JsonArray result = new JsonArray();
		result.add(service);
		result.add(geoArr);

		write("./data/json/" + myU.getScreenName() + "/",
				"user.json",
				result.toString());
	}
	
	
	public void createData(List<TwitterStatus> myTS) {
		// if (SNSData instanceof TwitterStatus) {
		for (TwitterStatus s : myTS) {
			if (s != null) {
				createData(s);
			}
		}
	}
	
	
	public void createData(TwitterStatus s) {
		// if (SNSData instanceof TwitterStatus) {
		
		JsonObject status = new JsonObject();
		status.addProperty("created_at", s.getCreatedAt());
		status.addProperty("favorites", s.getFavoriteCount());
		status.addProperty("retweets", s.getRetweetCount());
		status.addProperty("language", s.getLanguage());
		status.addProperty("text", s.getText());
		status.addProperty("length", s.getLength());
		status.addProperty("bytes", s.getBytes());
		
		// wordcount
		JsonArray wordsArr = new JsonArray();
		JsonObject word = new JsonObject();
		Map <String, Long> wordcounts = s.getWordcounts();
		for (String key : wordcounts.keySet()) {
			word.addProperty(key, wordcounts.get(key));
		}
		wordsArr.add(word);
		
		// geoloc
		TwitterGeoLoc locObj = s.getGeoloc();
		JsonArray geoArr = new JsonArray();
		JsonObject geoObj = new JsonObject();
		boolean isOn = locObj.isEnabled();
		geoObj.addProperty("geoloc", isOn);
		geoObj.addProperty("latitude", locObj.getLatitude());
		geoObj.addProperty("longitude", locObj.getLongitude());
		geoArr.add(geoObj);
		
		
		// final
		JsonArray result = new JsonArray();
		result.add(status);
		result.add(wordsArr);
		result.add(geoArr);
		
		write("./data/json/" + s.getScreenName() + "/",
				s.getStatusId()+ ".json",
				result.toString()
		);
	}

	
	public String toString(JsonArray array) {
		return array.toString();
	}
	
	public String read(String filePath, String fileName) {
		StringBuffer sb = new StringBuffer();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(new File(filePath + fileName)), "UTF-8")
						//FileReaderWriter.class.getClassLoader().getResourceAsStream(filePath), "UTF-8")
				)){
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s+"\n");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("우선, 디렉토리에 대한 파일 읽기 권한이 있는지 확인해주세요.");
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public void write(String filePath, String fileName, String jsonString) {
		File dir = new File(filePath);
		if (dir.mkdirs()) {
			System.out.println(dir.getName() + " 디렉토리를 생성합니다.");
		}
		
		if (dir.exists()) {
			File file = new File(dir, fileName);
			try {
				if (file.createNewFile()) {
					System.out.println(file.getName() + " 파일이 생성됩니다...");
					
					try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName))) {
						writer.write(jsonString);
						writer.flush();
						System.out.println(jsonString);
						System.out.println("저장완료 ...\n");
						
					} catch (FileNotFoundException e) {
						System.out.println("파일이 존재하지 않습니다.");
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						System.out.println("인코딩 문자체계를 지원하지 않습니다.");
						e.printStackTrace();
					}

				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
