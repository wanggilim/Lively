package com.quadcore.lively.model;

import java.sql.Date;

public class MemberVO {

	private int userNo;
	private String userMail;
	private String userPass;
	private int userLevel;
	private String gender;
	private Date birthday;
	
	public MemberVO() {
		super();
	}
	
	public MemberVO(int userNo, String userMail, String userPass, int userLevel, String gender, Date birthday) {
		super();
		this.userNo = userNo;
		this.userMail = userMail;
		this.userPass = userPass;
		this.userLevel = userLevel;
		this.gender = gender;
		this.birthday = birthday;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberVO [userNo=").append(userNo).append(", userMail=").append(userMail).append(", userPass=")
				.append(userPass).append(", userLevel=").append(userLevel).append(", gender=").append(gender)
				.append(", birthday=").append(birthday).append("]");
		return builder.toString();
	}

	

	
}
