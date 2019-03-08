package com.quadcore.lively.api.twitter.service;

import com.quadcore.lively.api.twitter.dao.CrawlDAO;
import com.quadcore.lively.api.util.TxtUtil;

import twitter4j.Twitter;
import twitter4j.conf.Configuration;

public class CrawlService {
	
	private CrawlDAO dao;
	
	public CrawlService() {
		dao = new CrawlDAO();
	}

	public void target(Twitter twitter) {
		
//		String[] targets = new String[] {
//			"itsDakotaFannin", "danedehaan", "VancityReynolds", "LeoDiCaprio",
//			"RobertDowneyJr", "RWitherspoon", "selenagomez", "justinbieber",
//			"Camila_Cabello", "ArianaGrande", "ddlovato", "rihanna", "ladygaga",
//			"Pontifex", "BarackObama", "realDonaldTrump", "TomCruise",
//			"BBCBreaking", "CNN", "arirangworld", "TheKoreaHerald",
//			"HillaryClinton", "UN", "BillGates",  "teamcoco",
//			"billyjoel", "therock", "vindiesel"
//		}; // 검색할 트윗 스크린네임/계정명
		
		TxtUtil	txt = new TxtUtil();
		String[] targets = txt.read("./resources/", "targets").split("\n");
		dao.target(twitter, targets);
	}
	
	public void random(Configuration config) {
		dao.random(config);
	}

}
