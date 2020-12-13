package com.account.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.account.dao.StatementDao;
import com.account.model.Statement;


@Repository
public class StatementDaoImpl implements StatementDao {
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Statement> getStatement(int id){
		String stmtsql = "SELECT * FROM statement where account_id="+id;
		String accountsql = "SELECT * FROM account where ID="+id;
		List<Statement> stmtList = jdbcTemplate.query(stmtsql,(rs,rowNum)-> new Statement(
		rs.getInt("ID"),rs.getString("datefield"),rs.getString("amount")
		)
	);
		return stmtList;
	}

	
}