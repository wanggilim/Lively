package com.quadcore.lively.api.controller;

import com.quadcore.lively.api.service.PapagoService;
import com.quadcore.lively.api.twitter.main.TwitterApiMain;

public class LivelyApiController {
	
	private PapagoService service;
	
	public LivelyApiController() {
		service = new PapagoService();
	}
	
	public void crawl() {
		new TwitterApiMain();
	}
	
	public int translateAndSave(String directory, String name, String extensions) {
		return service.translateAndSave(directory, name, extensions);
	}
	
}
