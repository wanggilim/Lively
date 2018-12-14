package com.quadcore.lively.papago.config;

public class PapagoConfiguration {

	private static final String URL = "https://openapi.naver.com/v1/papago/n2mt";
	private static final String APP_NAME = "Lively";
	private static final String CLIENT_ID = "4CR7C6nYMNVCbpJap13v";
	private static final String CLIENT_SECRET = "1Ba9Xqjd3x";
	
	
	public static String getUrl() {
		return URL;
	}

	public static String getAppName() {
		return APP_NAME;
	}

	public static String getClientId() {
		return CLIENT_ID;
	}

	public static String getClientSecret() {
		return CLIENT_SECRET;
	}
	
}
