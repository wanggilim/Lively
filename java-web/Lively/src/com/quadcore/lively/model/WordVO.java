package com.quadcore.lively.model;

public class WordVO {
	private int tokenNo;		// token number
	private String token;		// 검색한 단어
	private String mean;		// 검색한 단어에 대한 뜻
	private String tokenTag;			// 형태소
	private String tokenType;	// 신조어, 통용어, 불용어 구분
	private int stmtNo;			// statement number
	
	public WordVO() {
		
	}

	public WordVO(int tokenNo, String token, String mean, String tokenTag, String tokenType, int stmtNo) {
		super();
		this.tokenNo = tokenNo;
		this.token = token;
		this.mean = mean;
		this.tokenTag = tokenTag;
		this.tokenType = tokenType;
		this.stmtNo = stmtNo;
	}

	public int getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(int tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public String getTokenTag() {
		return tokenTag;
	}

	public void setTokenTag(String tokenTag) {
		this.tokenTag = tokenTag;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public int getStmtNo() {
		return stmtNo;
	}

	public void setStmtNo(int stmtNo) {
		this.stmtNo = stmtNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WordVO [tokenNo=").append(tokenNo).append(", token=").append(token).append(", mean=")
				.append(mean).append(", tokenTag=").append(tokenTag).append(", tokenType=").append(tokenType)
				.append(", stmtNo=").append(stmtNo).append("]");
		return builder.toString();
	}
	
}
