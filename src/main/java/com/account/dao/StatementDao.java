package com.account.dao;

import java.util.List;

import com.account.model.Statement;

public interface StatementDao {
	List<Statement> getStatement(int accountId);
}