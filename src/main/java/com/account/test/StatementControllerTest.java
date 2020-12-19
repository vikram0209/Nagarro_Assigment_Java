package com.account.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.account.controllers.StatementController;
import com.account.model.Statement;
import com.account.service.AccountService;
import com.account.service.impl.ResultDto;

@SpringBootTest(classes = StatementControllerTest.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class StatementControllerTest {
	
	private static final Logger log=LoggerFactory.getLogger(StatementControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AccountService accountService;

	private List<Statement> stmtList;
	private int accountId;
	private String accountNumber;
	private String accountType;

	ResultDto dtoResult = new ResultDto(stmtList, accountId, accountNumber,
			accountType);

	@Test
	public void getAccountStatementUser() throws Exception {

		Mockito.when(
				accountService.getStatementUser(
						 Mockito.any(Integer.class))).thenReturn(dtoResult);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getAccountStatementUser").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		log.info("response"+result.getResponse());
		Assert.assertNotNull(result);
	}
	@Test
	public void getAccountStatementAdmin() throws Exception {

		Mockito.when(
				accountService.getStatementAdmin(
						 Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Integer.class))).thenReturn(dtoResult);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getAccountStatementAdmin").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		log.info("response"+result.getResponse());
		Assert.assertNotNull(result);
	}
}
