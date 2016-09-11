package com.hairdress.entity;

public class CustomInfo {

	private int id;
	private String username;
	private long userTelNumber;
	private int storedMoney;
	private int remainMoney;
	private int remainHairdressCount;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUserTelNumber() {
		return userTelNumber;
	}

	public void setUserTelNumber(long userTelNumber) {
		this.userTelNumber = userTelNumber;
	}

	public int getStoredMoney() {
		return storedMoney;
	}

	public void setStoredMoney(int storedMoney) {
		this.storedMoney = storedMoney;
	}

	public int getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(int remainMoney) {
		this.remainMoney = remainMoney;
	}

	public int getRemainHairdressCount() {
		return remainHairdressCount;
	}

	public void setRemainHairdressCount(int remainHairdressCount) {
		this.remainHairdressCount = remainHairdressCount;
	}


}
