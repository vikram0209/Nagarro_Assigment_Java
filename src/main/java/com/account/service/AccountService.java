package com.account.service;

import com.account.service.impl.ResultDto;

public interface AccountService {
	
	public ResultDto getStatementAdmin(String fromdate,String todate,String fromamount,String toamount,int accountNo);
	public ResultDto getStatementUser(int accountNo);

}