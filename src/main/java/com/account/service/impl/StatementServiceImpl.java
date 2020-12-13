package com.account.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.account.constant.AccountConstants;
import com.account.dao.AccountDao;
import com.account.dao.StatementDao;
import com.account.model.Account;
import com.account.model.Statement;
import com.account.service.AccountService;

@Service
public class StatementServiceImpl implements AccountService {

	LocalDate fromDate = null;
	LocalDate toDate = null;
	List<Statement> finalList =null;

	@Autowired
	StatementDao statementDao;

	@Autowired
	AccountDao accountDao;

	@Override
	public ResultDto getStatement(HttpServletRequest request, String userName) {
		ResultDto dto = new ResultDto();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String fromDateString = request.getParameter("fromdate");
		String toDateString = request.getParameter("todate");
		String accountNo = request.getParameter("account");
		String fromAmount = request.getParameter("fromamount");
		String toAmount = request.getParameter("toamount");

		if (userName.equals(AccountConstants.TEST_USER)) {
			toDate = LocalDate.now();
			fromDate = toDate.minusMonths(3);
		} else if (StringUtils.isNotBlank(fromDateString)
				&& StringUtils.isNotBlank(toDateString)
				&& userName.equals(AccountConstants.ADMIN_USER)) {
			fromDate = LocalDate.parse(fromDateString, dtf);
			toDate = LocalDate.parse(toDateString, dtf);
		} else if (StringUtils.isNotBlank(fromDateString)) {
			fromDate = LocalDate.parse(fromDateString, dtf);
			toDate = fromDate.plusMonths(3);
		} else {
			fromDate = LocalDate.now();
			toDate = fromDate.minusMonths(3);
		}

		List<Account> accountInfo = accountDao.getAccount(Integer
				.parseInt(accountNo));
		List<Statement> stmt = statementDao.getStatement(Integer
				.parseInt(accountNo));
		if(StringUtils.isNotBlank(toDateString) && StringUtils.isNotBlank(fromDateString)){
		 finalList = stmt
				.stream()
				.filter(l -> (LocalDate.parse(l.getDatefield(), dtf).equals(
						fromDate) || LocalDate.parse(l.getDatefield(), dtf)
						.equals(toDate))
						|| LocalDate.parse(l.getDatefield(), dtf).isAfter(
								fromDate)
						&& LocalDate.parse(l.getDatefield(), dtf).isBefore(
								toDate)).collect(Collectors.toList());
		}else if(StringUtils.isNotBlank(fromAmount) && StringUtils.isNotBlank(toAmount)){
			 finalList = stmt
					.stream()
					.filter(l ->Double.parseDouble(l.getAmount())==
							Double.parseDouble(fromAmount) || Double.parseDouble(l.getAmount())
							==Double.parseDouble(toAmount)
							|| (Double.parseDouble(l.getAmount())>
							Double.parseDouble(fromAmount)
							&& Double.parseDouble(l.getAmount())<
							Double.parseDouble(toAmount))).collect(Collectors.toList());
			
		}
		
		
		dto.setAccountNumber(maskNumber(accountInfo.get(0).getAccountNumber()
				.toString(), "xxxxxxxxx####"));
		dto.setAccountType(accountInfo.get(0).getAccountType());
        if(userName.equals(AccountConstants.TEST_USER) || (userName.equals(AccountConstants.ADMIN_USER))&& StringUtils.isBlank(fromDateString)
				&& StringUtils.isBlank(toDateString)&& StringUtils.isBlank(fromAmount) && StringUtils.isBlank(toAmount) ){
        	Collections.sort(stmt);
        	LocalDate toDate =LocalDate.parse(stmt.get(stmt.size()-1).getDatefield(), dtf);
        	LocalDate fromDate =toDate.minusMonths(3);
        	List<Statement> defaultuserList = stmt
    				.stream()
    				.filter(l -> (LocalDate.parse(l.getDatefield(), dtf).equals(
    						fromDate) || LocalDate.parse(l.getDatefield(), dtf)
    						.equals(toDate))
    						|| LocalDate.parse(l.getDatefield(), dtf).isAfter(
    								fromDate)
    						&& LocalDate.parse(l.getDatefield(), dtf).isBefore(
    								toDate)).collect(Collectors.toList());
        	dto.setStmtList(defaultuserList);
        	
        }else{
        	Collections.sort(finalList);
    		dto.setStmtList(finalList);
        }
	
		return dto;
	}

	public static String maskNumber(String number, String mask) {

		int index = 0;
		StringBuilder masked = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) {
			char c = mask.charAt(i);
			if (c == '#') {
				masked.append(number.charAt(index));
				index++;
			} else if (c == 'x') {
				masked.append(c);
				index++;
			} else {
				masked.append(c);
			}
		}
		return masked.toString();
	}

}