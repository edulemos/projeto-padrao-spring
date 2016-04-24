package com.spring.baseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.baseproject.entity.User;
import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/user/home")
	public ModelAndView home() {
		return new ModelAndView("user/home");
	}

	@RequestMapping(value = "/user/dadosCadastrais")
	public String dadosCadastrais(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		return "user/dadosCadastrais";
	}

	@RequestMapping(value = "/user/dadosCadastrais/save")
	public String dadosCadastraisSave(User user, Model model) {
		try {
			User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userSession.setEmail(user.getEmail());
			userSession.setName(user.getName());
			service.salvar(userSession);
			model.addAttribute("msg", messages.getMessage("user.edit.success", null, null));
			model.addAttribute("user", userSession);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("user", user);
		}
		return dadosCadastrais(model);
	}

	@RequestMapping(value = "/user/alterarSenha")
	public String alterarSenha(Model model) {
		return "user/alterarSenha";
	}

	@RequestMapping(value = "/user/alterarSenha/save")
	public String alterarSenhaSave(CadastroForm form, Model model) {
		try {
			User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			service.alterarSenha(userSession, form);
			model.addAttribute("msg", messages.getMessage("user.edit.password.success", null, null));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return alterarSenha(model);
	}

}
