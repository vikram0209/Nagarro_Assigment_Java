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
import com.account.dao.AccountDao;
import com.account.dao.StatementDao;
import com.account.model.Account;
import com.account.model.Statement;
import com.account.service.AccountService;
import com.account.utility.Utility;

@Service
public class StatementServiceImpl implements AccountService {

	private LocalDate fromDate = null;
	private LocalDate toDate = null;
	private List<Statement> stmtlList = null;

	@Autowired
	private StatementDao statementDao;

	@Autowired
	private AccountDao accountDao;

	@Override
	public ResultDto getStatementAdmin(
			String fromDateString, String toDateString, String fromAmount,
			String toAmount, int accountID) {
		ResultDto dto = new ResultDto();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		if (StringUtils.isNotBlank(fromDateString)
				&& StringUtils.isNotBlank(toDateString)) {
			fromDate = LocalDate.parse(fromDateString, dtf);
			toDate = LocalDate.parse(toDateString, dtf);
		} else if (StringUtils.isNotBlank(fromDateString)) {
			fromDate = LocalDate.parse(fromDateString, dtf);
			toDate = fromDate.plusMonths(3);
		}

		List<Account> accountInfo = accountDao.getAccount(accountID);
		List<Statement> stmt = statementDao.getStatement(accountID);
		if (StringUtils.isNotBlank(fromAmount)
				&& StringUtils.isNotBlank(toAmount)) {
			stmtlList = stmt
					.stream()
					.filter(l -> Double.parseDouble(l.getAmount()) == Double
							.parseDouble(fromAmount)
							|| Double.parseDouble(l.getAmount()) == Double
									.parseDouble(toAmount)
							|| (Double.parseDouble(l.getAmount()) > Double
									.parseDouble(fromAmount) && Double
									.parseDouble(l.getAmount()) < Double
									.parseDouble(toAmount)))
					.collect(Collectors.toList());

			Collections.sort(stmtlList);
			dto.setStmtList(stmtlList);

		} else if (StringUtils.isNotBlank(toDateString)
				&& StringUtils.isNotBlank(fromDateString)) {
			stmtlList = getStatementsList(dtf, stmt);
			Collections.sort(stmtlList);
			dto.setStmtList(stmtlList);
		} else if (StringUtils.isNotBlank(fromDateString)) {
			stmtlList = getStatementsList(dtf, stmt);
			Collections.sort(stmtlList);
			dto.setStmtList(stmtlList);
		}

		if (StringUtils.isBlank(fromDateString)
				&& StringUtils.isBlank(toDateString)
				&& StringUtils.isBlank(fromAmount)
				&& StringUtils.isBlank(toAmount)) {
			Collections.sort(stmt);
			toDate = LocalDate.parse(stmt.get(stmt.size() - 1).getDatefield(),
					dtf);
			fromDate = toDate.minusMonths(3);
			stmtlList = getStatementsList(dtf, stmt);
			dto.setStmtList(stmtlList);
		}

		dto.setAccountType(accountInfo.get(0).getAccountType());
		dto.setAccountNumber(Utility.maskNumber(accountInfo.get(0)
				.getAccountNumber().toString(), "xxxxxxxxx####"));
		return dto;
	}

	

	@Override
	public ResultDto getStatementUser(int accountNo) {
		ResultDto dto = new ResultDto();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		List<Account> accountInfo = accountDao.getAccount(accountNo);
		List<Statement> stmt = statementDao.getStatement(accountNo);
		dto.setAccountNumber(Utility.maskNumber(accountInfo.get(0)
				.getAccountNumber().toString(), "xxxxxxxxx####"));
		dto.setAccountType(accountInfo.get(0).getAccountType());
		Collections.sort(stmt);
		LocalDate toDate = LocalDate.parse(stmt.get(stmt.size() - 1)
				.getDatefield(), dtf);
		LocalDate fromDate = toDate.minusMonths(3);
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

		return dto;
	}
	
	
	private List<Statement> getStatementsList(DateTimeFormatter dtf,
			List<Statement> stmt) {
		System.out.print("todate" + toDate);
		System.out.print("fromDate" + fromDate);
		stmtlList = stmt
				.stream()
				.filter(l -> (LocalDate.parse(l.getDatefield(), dtf).equals(
						fromDate) || LocalDate.parse(l.getDatefield(), dtf)
						.equals(toDate))
						|| LocalDate.parse(l.getDatefield(), dtf).isAfter(
								fromDate)
						&& LocalDate.parse(l.getDatefield(), dtf).isBefore(
								toDate)).collect(Collectors.toList());
		return stmtlList;
	}

}