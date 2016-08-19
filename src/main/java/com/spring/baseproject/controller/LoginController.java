package com.spring.baseproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService service;

	@Autowired
	private MessageSource messages;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/")
	public String paginaInicial() {
		return "home/login";
	}

	@RequestMapping("/login")
	public String login() {
		return "home/login";
	}

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		return new ModelAndView("home/home");
	}

	@RequestMapping(value = "/acesso-negado")
	public ModelAndView acessoNegado() {
		return new ModelAndView("home/acesso-negado");
	}

	@RequestMapping(value = "/login/recuperarSenha")
	public String recuperarSenha() {
		return "home/recuperar-senha";
	}

	@RequestMapping(value = "/login/resetarSenha/{email}/{key}", method = RequestMethod.GET)
	public String resetarSenha(@PathVariable("email") String email, @PathVariable("key") String key, Model model) {
		try {
			String path = rooturl();
			service.verifcarKey(email, key, path);
			return "home/resetar-senha";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "home/recuperar-senha";
		}
	}

	@RequestMapping(value = "/login/resetarSenha/salvar", method = RequestMethod.POST)
	public String salvarNovaSenha(CadastroForm form, String key, Model model) {
		try {
			String path = rooturl();
			service.alterarSenha(form, key, path);
			model.addAttribute("msg", messages.getMessage("recuperar-senha.sucesso", null, null));
			return "home/login";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "home/recuperar-senha";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "home/login";
	}

	@RequestMapping(value = "/login/recuperarSenha/enviarEmail", method = RequestMethod.POST)
	public String enviarEmailSenha(@RequestParam String email, Model model) {
		try {
			String path = rooturl();
			service.recuperarSenha(email, path);
			model.addAttribute("msg", messages.getMessage("recuperar-senha.msg.emailenviado", null, null));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return recuperarSenha();

	}

	private String rooturl() {
		String path = null;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = ((HttpServletRequest) request);
			path = req.getRequestURL().toString().replace(req.getServletPath(), "");
		}
		return path;
	}

}
