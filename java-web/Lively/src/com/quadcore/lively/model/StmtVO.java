package com.quadcore.lively.model;

public class StmtVO {
	private int stmtNo;			// 예문 번호
	private String stmt;		// 예문
	private long stmtTs;		// 예문 생성 timestamp
	private String stmtType;	// SNS 구분자
	private String location;	// 위치 정보 (String)
	private int likes;			// 좋아요 수 (Lively 서비스 내)
	private int sharing;		// 공유 수 (Lively 서비스로부터 공유되어진 카운트)
	private int cellbNo;		// SNS 셀럽 번호
	private String stmtURL;		// 예문 생성 URL
	private String profileURL;	// 프로필 URL (2019.03.18 추가)
	
	public StmtVO() {
		
	}

	public StmtVO(int stmtNo, String stmt, long stmtTs, String stmtType, String location, int likes, int sharing,
			int cellbNo, String stmtURL, String profileURL) {
		super();
		this.stmtNo = stmtNo;
		this.stmt = stmt;
		this.stmtTs = stmtTs;
		this.stmtType = stmtType;
		this.location = location;
		this.likes = likes;
		this.sharing = sharing;
		this.cellbNo = cellbNo;
		this.stmtURL = stmtURL;
		this.profileURL = profileURL;
	}

	public int getStmtNo() {
		return stmtNo;
	}

	public void setStmtNo(int stmtNo) {
		this.stmtNo = stmtNo;
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

	public String getStmtType() {
		return stmtType;
	}

	public void setStmtType(String stmtType) {
		this.stmtType = stmtType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getSharing() {
		return sharing;
	}

	public void setSharing(int sharing) {
		this.sharing = sharing;
	}

	public int getCellbNo() {
		return cellbNo;
	}

	public void setCellbNo(int cellbNo) {
		this.cellbNo = cellbNo;
	}

	public String getStmtURL() {
		return stmtURL;
	}

	public void setStmtURL(String stmtURL) {
		this.stmtURL = stmtURL;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StmtVO [stmtNo=").append(stmtNo).append(", stmt=").append(stmt).append(", stmtTs=")
				.append(stmtTs).append(", stmtType=").append(stmtType).append(", location=").append(location)
				.append(", likes=").append(likes).append(", sharing=").append(sharing).append(", cellbNo=")
				.append(cellbNo).append(", stmtURL=").append(stmtURL).append(", profileURL=").append(profileURL)
				.append("]");
		return builder.toString();
	}
	
}
