package com.quadcore.lively.api.papago.controller;

import java.util.ArrayList;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.quadcore.lively.api.papago.model.PapagoTranslateModel;

public class PapagoJsonParser {
	
	/**
	 * 
		
	{
	  "message":
	  {
	    "@type":"response",
	    "@service":"naverservice.nmt.proxy",
	    "@version":"1.0.0",
	    "result":
	    {
	      "srcLangType":"en",
	      "tarLangType":"ko",
	      "translatedText":"....나는 내가 하겠다고 약속한 것과 우리 나라의 국민들이 하도록 선출한 것을 정확히 하고 있다. 내가 약속한 대로, 나는 너를 위해 싸우고 있어!"
	    }
	  }
	}
		
	 * 
	 */
	
	private Gson gson;
	
	public PapagoJsonParser() {
		gson = new GsonBuilder().create();
	}

	public String jsonToString(String json) throws NullPointerException {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json).getAsJsonObject()
				.getAsJsonObject().get("message")
				.getAsJsonObject().get("result");
		
		PapagoTranslateModel item = gson.fromJson(element.toString(), PapagoTranslateModel.class);
		
		return item.getTranslatedText();
	}
	
	public String[] jsonsToStrings(String[] jsons) {
		ArrayList<String> results = new ArrayList<String>(1);
		
		for (String json : jsons) {
			String result = jsonToString(json);
			results.add(result);
//			if (json != null) {
//				results.add(jsonToString(json));
//			}
			
		}
		
		String[] resultsArr = new String[results.size()];
		resultsArr = results.toArray(resultsArr);
		
		return resultsArr;
	}

}
