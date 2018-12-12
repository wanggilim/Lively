package com.quadcore.lively.parser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.quadcore.lively.parser.action.JsonParsing;

public class JsonFileReader {
	
	public String read(String filePath) {
		String result = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
			
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			result = new JsonParsing().getParsed(line);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}
