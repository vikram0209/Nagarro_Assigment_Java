package com.account.service.impl;

import java.util.List;

import com.account.model.Statement;

public class ResultDto {
	
	private List<Statement> stmtList;
	private int accountId;
	private String accountNumber;
	private String accountType;
	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ResultDto(){}

	public ResultDto(List<Statement> stmtList, int accountId,
			String accountNumber, String accountType) {
		super();
		this.stmtList = stmtList;
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<Statement> getStmtList() {
		return stmtList;
	}

	public void setStmtList(List<Statement> stmtList) {
		this.stmtList = stmtList;
	}

}
