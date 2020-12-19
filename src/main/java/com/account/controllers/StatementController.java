package com.account.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.account.service.AccountService;

@Controller
public class StatementController {
	private static final Logger log=LoggerFactory.getLogger(StatementController.class);

	@Autowired
	private AccountService accountService;

	@RequestMapping("/welcome")
	public ModelAndView welcomePage(ModelMap map, HttpServletRequest request) {
		return new ModelAndView("welcome", map);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/welcome/testadmin")
	public ModelAndView welcomeAdmin(ModelMap map, HttpServletRequest request) {
		map.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		return new ModelAndView("welcome_admin", map);
	}

	@Secured("ROLE_USER")
	@RequestMapping("/welcome/testuser")
	public ModelAndView welcomeUser(ModelMap map, HttpServletRequest request) {
		map.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		return new ModelAndView("welcome_user", map);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getAccountStatementAdmin", method = RequestMethod.POST)
	public ModelAndView getAccountStatementAdmin(HttpServletRequest request,
			ModelMap map, @ModelAttribute("fromdate") String fromDateString,
			@ModelAttribute("todate") String toDateString,
			@ModelAttribute("fromamount") String fromAmount,
			@ModelAttribute("toamount") String toAmount,
			@ModelAttribute("account") int accountID) {
		map.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		map.addAttribute("result", accountService.getStatementAdmin(
				fromDateString, toDateString, fromAmount, toAmount, accountID));
		return new ModelAndView("result", map);
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/getAccountStatementUser", method = RequestMethod.POST)
	public ModelAndView getAccountStatementUser(HttpServletRequest request,
			ModelMap map, @ModelAttribute("account") int accountID) {
		map.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		map.addAttribute("result", accountService.getStatementUser(accountID));
		return new ModelAndView("result", map);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg",
					"Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

}
