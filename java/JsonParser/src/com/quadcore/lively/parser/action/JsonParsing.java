package com.quadcore.lively.parser.action;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonParsing {

	private Gson gson;
	
	public JsonParsing() {
		gson = new Gson();
	}
	
	public String getParsed(String jsonContent) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonContent);
		String youtubeText = element.getAsJsonObject().get("textOriginal").getAsString();
		
		return youtubeText;
	}
	
}
