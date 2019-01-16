package com.quadcore.lively.api.papago.model;

/***
 * 
 * 파파고 API Json Parsing을 위한 모델 데이터
 * (업데이트 되지 않는 이상 하단 코드들은 절대로 건드리지 마세요)
 * 
 * @author wgl
 * 
 	{
	    "message": {
	        "@type": "response",
	        "@service": "naverservice.labs.api",
	        "@version": "1.0.0",
	        "result": {
	            "translatedText": "번역될 텍스트"
	        }
	    }
	}

 */
public class PapagoTranslateModel {
	
	private String translatedText;

	public String getTranslatedText() {
		return translatedText;
	}
	
}
