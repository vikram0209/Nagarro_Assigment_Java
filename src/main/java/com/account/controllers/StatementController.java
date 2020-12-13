package com.account.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.account.constant.AccountConstants;
import com.account.model.AccountStatementRequest;
import com.account.model.StatementResponse;
import com.account.service.AccountService;
import com.account.service.impl.ResultDto;

@Controller
public class StatementController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	JdbcTemplate template;
	

	@RequestMapping("/welcome")
	public ModelAndView firstPage(ModelMap map,HttpServletRequest httpRequest) {
		
		map.addAttribute("username",httpRequest.getUserPrincipal().getName());
		return new ModelAndView("welcome",map);
	}



	 @RequestMapping(value="/getAccountStatement", method=RequestMethod.POST)
	    public ModelAndView getAccountStatement(ModelMap map,HttpServletRequest httpRequest,AccountStatementRequest request) {
	        map.addAttribute("result", accountService.getStatement(httpRequest,httpRequest.getUserPrincipal().getName()));
	        return new ModelAndView("result", map);
	    }
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

}
