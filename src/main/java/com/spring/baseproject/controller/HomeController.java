package com.spring.baseproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/")
	public String index() {
		return "home/login";
	}

	@RequestMapping("/cadastro")
	public String cadastro(Model model) {
		return "home/cadastro";
	}

	@RequestMapping(value = "/cadastro/save", method = RequestMethod.POST)
	public String save(@Valid CadastroForm form, BindingResult bindingResult, Model model) {
		try {

			if (bindingResult.hasErrors()) {
				model.addAttribute("form", form);
				return cadastro(model);
			}

			userService.cadastrarUsuario(form);

			model.addAttribute("msg", messages.getMessage("cadastro.msg.sucesso", null, null));

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("form", form);
		}

		return cadastro(model);
	}

	@RequestMapping("/login")
	public String login() {
		return "home/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "home/login";
	}

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		return new ModelAndView("home/home");
	}

	@RequestMapping(value = "/acesso-negado")
	public ModelAndView accesssDenied() {
		return new ModelAndView("home/acesso-negado");
	}

	@RequestMapping(value = "/login/recuperarSenha")
	public String recuperarSenha() {
		return "home/recuperar-senha";
	}

	@RequestMapping(value = "/login/recuperarSenha/enviarEmail", method = RequestMethod.POST)
	public String recuperarSenha(@RequestParam String email, Model model) {
		try {
			String path = rootPatch();
			userService.recuperarSenha(email, path);
			model.addAttribute("msg", messages.getMessage("recuperar-senha.msg.emailenviado", null, null));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return recuperarSenha();

	}

	@RequestMapping(value = "/login/resetarSenha/{email}/{key}", method = RequestMethod.GET)
	public String resetarSenha(@PathVariable("email") String email, @PathVariable("key") String key, Model model) {
		try {
			String path = rootPatch();
			userService.verifcarKey(email, key, path);
			return "home/resetar-senha";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "home/recuperar-senha";
		}
	}
	
	@RequestMapping(value = "/login/resetarSenha/salvar", method = RequestMethod.POST)
	public String resetarSenha(CadastroForm form, String key, Model model) {
		try {
			String path = rootPatch();
			userService.alterarSenha(form, key, path);
			model.addAttribute("msg", messages.getMessage("recuperar-senha.sucesso", null, null));
			return "home/login";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "home/recuperar-senha";
		}
	}

	private String rootPatch() {
		String path = null;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = ((HttpServletRequest) request);
			path = req.getRequestURL().toString().replace(req.getServletPath(), "");
		}
		return path;
	}

}
