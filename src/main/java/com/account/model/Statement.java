package com.account.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Statement implements Comparable<Statement>{
    private int ID;
	private String datefield;
	private String amount;

	
	

	public Statement(){
		
		
	}

	public Statement(int ID,String datefield ,String amount){
		this.ID=ID;
		this.datefield=datefield;
		this.amount=amount;
	
		
		
	}
	
	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getDatefield() {
		return datefield;
	}



	public void setDatefield(String datefield) {
		this.datefield = datefield;
		
	}



	public String getAmount() {
		return amount;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Statement [ID=" + ID + ", datefield=" + datefield + ", amount="
				+ amount + "]";
	}

	@Override
	public int compareTo(Statement obj) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy"); 
		if(LocalDate.parse(datefield,dtf).equals(LocalDate.parse(obj.getDatefield(),dtf))){
			return 0;
		}
		
		return (LocalDate.parse(datefield,dtf).compareTo(LocalDate.parse(obj.getDatefield(),dtf)));
		
	}



	

}