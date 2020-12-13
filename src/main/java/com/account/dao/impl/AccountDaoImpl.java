package com.account.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.account.dao.AccountDao;
import com.account.model.Account;


@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Account> getAccount(int id){
		String accountsql = "SELECT * FROM account where ID="+id;
		List<Account> account = jdbcTemplate.query(accountsql,(rs,rowNum)-> new Account(
		rs.getInt("ID"),rs.getString("account_type"),rs.getString("account_number")
		)
	);
		return account;
	}

	
}