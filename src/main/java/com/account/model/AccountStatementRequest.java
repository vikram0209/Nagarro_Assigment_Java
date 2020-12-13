package com.account.model;

public class AccountStatementRequest {
    private int accountId;
	private String fromDate;
	private String toDate;

	
	

	public int getAccountId() {
		return accountId;
	}




	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}




	public String getFromDate() {
		return fromDate;
	}




	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}




	public String getToDate() {
		return toDate;
	}




	public void setToDate(String toDate) {
		this.toDate = toDate;
	}




	@Override
	public String toString() {
		return "Statement [accountId=" + accountId + ", fromDate=" + fromDate + "]" +", toDate=" + toDate + "]";
	}

}