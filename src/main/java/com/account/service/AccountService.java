package com.account.service;

import javax.servlet.http.HttpServletRequest;
import com.account.service.impl.ResultDto;

public interface AccountService {
	
	ResultDto getStatement(HttpServletRequest request ,String userNames);

}