package com.quadcore.lively.model;

public class StatementVO {
	private int stmtNo;			// 예문 번호
	private String stmt;		// 예문
	private String stmtType;	// SNS 구분자
	private String location;	// 위치 정보 (String)
	private int likes;			// 좋아요 수 (Lively 서비스 내)
	private int sharing;		// 공유 수 (Lively 서비스로부터 공유되어진 카운트)
	private int cellbNo;		// SNS 셀럽 번호
	
	public StatementVO() {
		
	}

	public StatementVO(int stmtNo, String stmt, String stmtType, String location, int likes, int sharing, int cellbNo) {
		super();
		this.stmtNo = stmtNo;
		this.stmt = stmt;
		this.stmtType = stmtType;
		this.location = location;
		this.likes = likes;
		this.sharing = sharing;
		this.cellbNo = cellbNo;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatementVO [stmtNo=").append(stmtNo).append(", stmt=").append(stmt).append(", stmtType=")
				.append(stmtType).append(", location=").append(location).append(", likes=").append(likes)
				.append(", sharing=").append(sharing).append(", cellbNo=").append(cellbNo).append("]");
		return builder.toString();
	}
	
	


}
