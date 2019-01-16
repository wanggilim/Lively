package com.quadcore.lively.api.papago.main;

import java.io.File;
import java.io.IOException;

import com.quadcore.lively.api.papago.util.PapagoConnection;

public class PapagoApiMain {
	
	private File file;
	
	public PapagoApiMain(String[] args) {
		main(args);
	}
	
	public PapagoApiMain(File file) {
		this.file = file;
	}

	public static void main(String[] args) {
//		try {
//			PapagoConnection conn = new PapagoConnection();
//			
//			System.out.println(conn.getJson(args));
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
