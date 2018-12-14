package com.quadcore.lively.papago.main;

import java.io.IOException;

import com.quadcore.lively.papago.helper.PapagoConnection;

public class PapagoApiMain {

	public static void main(String[] args) {
		try {
			PapagoConnection conn = new PapagoConnection();
			
			System.out.println(conn.getJson("make America great again!"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
