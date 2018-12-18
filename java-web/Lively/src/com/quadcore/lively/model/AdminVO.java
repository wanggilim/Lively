package com.quadcore.lively.model;

public class AdminVO {
	
	private int adminNo;
	private String adminEmail;
	private String adminPass;
	private int adminLevel;
	
	
	public AdminVO() {
		super();
	}
	
	public AdminVO(int adminNo, String adminEmail, String adminPass, int adminLevel) {
		super();
		this.adminNo = adminNo;
		this.adminEmail = adminEmail;
		this.adminPass = adminPass;
		this.adminLevel = adminLevel;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
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
		builder.append("AdminVO [adminNo=").append(adminNo).append(", adminEmail=").append(adminEmail)
				.append(", adminPass=").append(adminPass).append(", adminLevel=").append(adminLevel).append("]");
		return builder.toString();
	}
	
	
	

}
