package com.quadcore.lively.twitter.model;


public class TwitterStatus {
	private String screenName;				// Twitter Screen Name 또는 URL로 표기되는 계정아이디
	private String name;					// Twitter로 등록된 Name 또는 실제 이름
	private String stmt;					// Twitter mention
	private long stmtTs;					// mention created timestamp
	private String stmtURL;					// mention URL
	private final String stmtType = "t";	// SNS 구분자 (DB에서 char 처리)
	private String location;				// SNS 멘션 작성 위치
	private String language;				// Twitter mention 언어
	private int cellebNo;					// Twitter 글 올린 사람의 번호 (celleb 테이블)
	private String userProfileURL;			// Twitter User Profile URL
	
	public TwitterStatus() {
		
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStmt() {
		return stmt;
	}

	public void setStmt(String stmt) {
		this.stmt = stmt;
	}

	public long getStmtTs() {
		return stmtTs;
	}

	public void setStmtTs(long stmtTs) {
		this.stmtTs = stmtTs;
	}

	public String getStmtURL() {
		return stmtURL;
	}

	public void setStmtURL(String stmtURL) {
		this.stmtURL = stmtURL;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getCellebNo() {
		return cellebNo;
	}

	public void setCellebNo(int cellebNo) {
		this.cellebNo = cellebNo;
	}

	public String getUserProfileURL() {
		return userProfileURL;
	}

	public void setUserProfileURL(String userProfileURL) {
		this.userProfileURL = userProfileURL;
	}

	public String getStmtType() {
		return stmtType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TwitterStatus [screenName=").append(screenName).append(", name=").append(name).append(", stmt=")
				.append(stmt).append(", stmtTs=").append(stmtTs).append(", stmtURL=").append(stmtURL)
				.append(", stmtType=").append(stmtType).append(", location=").append(location).append(", language=")
				.append(language).append(", userProfileURL=").append(userProfileURL).append("]");
		return builder.toString();
	}

	

}
