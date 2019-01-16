package com.quadcore.lively.api.controller;

import com.quadcore.lively.api.service.PapagoService;
import com.quadcore.lively.api.twitter.main.TwitterApiMain;

public class LivelyApiController {
	
	private PapagoService service;
	
	public LivelyApiController() {
		service = new PapagoService();
	}
	
	public void crawl(String screenName) {
		new TwitterApiMain(screenName);
	}
	
	public int translateAndSave(
			String papagoId, String papagoPass,
			String directory, String name, String extensions) {
		return service.translateAndSave(
				papagoId, papagoPass,
				directory, name, extensions);
	}
	
}
