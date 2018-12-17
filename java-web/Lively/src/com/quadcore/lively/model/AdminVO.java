package com.quadcore.lively.model;

public class AdminVO {
	
	private int adminNo;
	private String adminMail;
	private String adminPass;
	private int adminLevel;
	
	
	public AdminVO() {
		super();
	}
	
	public AdminVO(int adminNo, String adminMail, String adminPass, int adminLevel) {
		super();
		this.adminNo = adminNo;
		this.adminMail = adminMail;
		this.adminPass = adminPass;
		this.adminLevel = adminLevel;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminMail() {
		return adminMail;
	}

	public void setAdminEmail(String adminMail) {
		this.adminMail = adminMail;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public int getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminVO [adminNo=").append(adminNo).append(", adminMail=").append(adminMail)
				.append(", adminPass=").append(adminPass).append(", adminLevel=").append(adminLevel).append("]");
		return builder.toString();
	}
	
	
	

}
