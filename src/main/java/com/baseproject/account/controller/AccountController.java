package com.baseproject.account.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.baseproject.account.dto.ChagePwdDto;
import com.baseproject.account.service.AccountService;
import com.baseproject.auth.model.User;
import com.baseproject.shared.constants.Pages;
import com.baseproject.util.MessagesUtil;

@Controller
public class AccountController {

	@Autowired
	MessagesUtil msgs;

	@Autowired
	AccountService service;

	@GetMapping("/account-data")
	public String data(Model model) {
		User userLoged = service.getUserLoged();
		model.addAttribute("user", userLoged);
		return Pages.ACCOUNT_DATA;
	}

	@PostMapping("/account-data-save")
	public String dataSave(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return Pages.ACCOUNT_DATA;
		} else {
			service.dataSave(user);
			model.addAttribute("msg", msgs.get("account.save"));
			return Pages.ACCOUNT_DATA;
		}

	}

	@GetMapping("/account-pwd")
	public String pwd(Model model) {
		User user = service.getUserLoged();
		ChagePwdDto dto = new ChagePwdDto();
		dto.setUuid(user.getUuid());
		model.addAttribute("pwdDto", dto);
		return Pages.ACCOUNT_PWD;
	}

	@PostMapping("/account-pwd-save")
	public String pwdSave(Model model, @Valid @ModelAttribute("pwdDto") ChagePwdDto pwdDto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return Pages.ACCOUNT_PWD;
		} else {
			service.pwdSave(pwdDto);
			model.addAttribute("msg", msgs.get("account.pwd.save"));
			return Pages.ACCOUNT_PWD;
		}

	}

}
