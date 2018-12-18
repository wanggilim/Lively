package com.quadcore.lively.model;

public class WordVO {
	private int stmtNo;			// statement number
	private int tokenNo;		// token number
	private String token;		// 검색한 단어
	private String mean;		// 검색한 단어에 대한 뜻
	private String tag;			// 형태소
	private String tokentype;	// 신조어, 통용어, 불용어 구분
	
	public WordVO() {
		
	}

	public WordVO(int stmtNo, int tokenNo, String token, String mean, String tag, String tokentype) {
		super();
		this.stmtNo = stmtNo;
		this.tokenNo = tokenNo;
		this.token = token;
		this.mean = mean;
		this.tag = tag;
		this.tokentype = tokentype;
	}

	public int getStmtNo() {
		return stmtNo;
	}

	public void setStmtNo(int stmtNo) {
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTokentype() {
		return tokentype;
	}

	public void setTokentype(String tokentype) {
		this.tokentype = tokentype;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WordVO [stmtNo=").append(stmtNo).append(", tokenNo=").append(tokenNo).append(", token=")
				.append(token).append(", mean=").append(mean).append(", tag=").append(tag).append(", tokentype=")
				.append(tokentype).append("]");
		return builder.toString();
	}
	
	
	
}
