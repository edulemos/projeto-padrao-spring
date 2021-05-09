package com.baseproject.auth.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baseproject.auth.dto.RecoverDto;
import com.baseproject.auth.dto.RegisterDto;
import com.baseproject.auth.service.LoginService;
import com.baseproject.shared.constants.Pages;
import com.baseproject.util.MessagesUtil;

@Controller
public class LoginController {

	@Autowired
	LoginService service;

	@Autowired
	MessagesUtil msg;

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("msg", msg.get("login.error"));
		if (logout != null)
			model.addAttribute("msg", msg.get("login.logout"));
		return Pages.LOGIN;
	}

	@GetMapping({ "/", "/index" })
	public String welcome(Model model, HttpSession session) {
		session.setAttribute("sessionDto", service.getSessionDto());
		return Pages.INDEX;
	}

	@GetMapping("/recover")
	public String forgot(Model model) {
		return Pages.RECOVER;
	}

	@PostMapping("/recover-email")
	public String recoverEmail(Model model, String email) throws MessagingException, IOException {
		if (service.checkEmail(email)) {
			model.addAttribute("msg", msg.get("login.recover.email"));
			service.recoverEmail(email);
			return Pages.LOGIN;
		} else {
			model.addAttribute("email", email);
			model.addAttribute("msg", msg.get("login.recover.email.error"));
			return Pages.RECOVER;
		}
	}

	@GetMapping("/recover-link")
	public String forgotCheckLink(@RequestParam String u, Model model) {
		if (!service.checkUuid(u)) {
			model.addAttribute("msg", msg.get("login.recover.link.invalid"));
			return Pages.LOGIN;
		} else if (service.checkUuidExpiration(u)) {
			model.addAttribute("msg", msg.get("login.recover.link.expired"));
			return Pages.LOGIN;
		} else {
			RecoverDto recover = new RecoverDto();
			recover.setUuid(u);
			model.addAttribute("recover", recover);
			return Pages.RECOVER_SAVE;
		}
	}

	@PostMapping("/recover-save")
	public String recoverSave(Model model, @Valid @ModelAttribute("recover") RecoverDto recover, BindingResult bindingResult) {
		if (!service.checkUuid(recover.getUuid())) {
			model.addAttribute("msg", msg.get("login.recover.link.invalid"));
			return Pages.LOGIN;
		} else if (service.checkUuidExpiration(recover.getUuid())) {
			model.addAttribute("msg", msg.get("login.recover.link.expired"));
			return Pages.LOGIN;
		} else if (bindingResult.hasErrors()) {
			return Pages.RECOVER_SAVE;
		} else {
			service.recoverSave(recover.getUuid(), recover.getPassword());
			model.addAttribute("msg", msg.get("login.recover.ok"));
			return "login";
		}
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("dto", new RegisterDto());
		return Pages.REGISTER;
	}

	@PostMapping("/register-save")
	public String registerSave(Model model, @Valid @ModelAttribute("dto") RegisterDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("dto", dto);
			return Pages.REGISTER;
		} else {
			service.registerSave(dto);
			model.addAttribute("msg", msg.get("login.register.save"));
			return Pages.LOGIN;
		}
	}
}
