package com.account.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import com.account.model.AccountStatementRequest;
import com.account.model.Statement;
import com.account.service.AccountService;
import com.account.service.impl.ResultDto;

@SpringBootTest(classes = StatementControllerTest.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class StatementControllerTest {

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
	public void getAccountStatement() throws Exception {

		Mockito.when(
				accountService.getStatement(
						 Mockito.any(HttpServletRequest.class),
						Mockito.anyString())).thenReturn(dtoResult);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getAccountStatement").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		Assert.assertNotNull(result);
	}

}
