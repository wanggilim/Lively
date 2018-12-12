package com.quadcore.lively.parser.main;

import com.quadcore.lively.parser.util.JsonFileReader;

public class JsonParserMain {

	public static void main(String[] args) {
		
		System.out.println("시작");
		JsonFileReader reader = new JsonFileReader();
		System.out.println(
				reader.read("youtube_20_ariana_grande_thankunext.json")
		);
		
		System.out.println("끝");
	}

}
