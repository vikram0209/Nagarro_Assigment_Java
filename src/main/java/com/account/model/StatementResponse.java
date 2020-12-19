package com.account.model;

import java.io.Serializable;

public class StatementResponse implements Serializable{
    

	private static final long serialVersionUID = 1L;
	
	
	private boolean isSuccess=true;
	private String message="Success";
	private Object data;

	
	

	
	
	public StatementResponse(Object data){
		super();
		
		this.data=data;
		
	}
	public StatementResponse(Object data,String message){
		super();
		this.data=data;
		this.message=message;
	}
	
	public StatementResponse(String message){
		super();
		this.isSuccess=isSuccess;
		this.message=message;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
		

}