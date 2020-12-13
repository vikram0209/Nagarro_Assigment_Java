package com.account.dao;

import java.util.List;

import com.account.model.Account;

public interface AccountDao {
	List<Account> getAccount(int accountId);
}