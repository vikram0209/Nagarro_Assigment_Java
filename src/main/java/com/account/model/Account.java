package com.account.model;


public class Account {
    private int ID;
	private String accountType;
	private String accountNumber;

	
	
	public Account(int ID,String accountType,String accountNumber ){
		this.ID=ID;
		this.accountType=accountType;
		this.accountNumber=accountNumber;
		
		
		
	}
	

	public int getID() {
		return ID;
	}






	public void setID(int iD) {
		ID = iD;
	}






	public String getAccountType() {
		return accountType;
	}






	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}






	public String getAccountNumber() {
		return accountNumber;
	}






	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}






	public Account(){
		
		
	}

	

}