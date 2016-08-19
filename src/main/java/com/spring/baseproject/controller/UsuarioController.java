package com.spring.baseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/dadosCadastro")
	public String dadosCadastroForm(Model model) {
		Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		return "usuario/dados-cadastro";
	}

	@RequestMapping(value = "/alterarSenha")
	public String alterarSenhaForm(Model model) {
		return "usuario/alterar-senha";
	}

	@RequestMapping(value = "/dadosCadastro/salvar")
	public String dadosCadastrosSalvar(Usuario user, Model model) {
		try {
			service.salvar(user);
			model.addAttribute("msg", messages.getMessage("dados-cadastro.usuario.alterado", null, null));
			model.addAttribute("user", user);

			// atualizo o usuario da sessao do spring com os dados alterados
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			model.addAttribute("user", user);
		}
		return dadosCadastroForm(model);
	}

	@RequestMapping(value = "/alterarSenha/salvar")
	public String alterarSenhaSalvar(CadastroForm form, Model model) {
		try {
			Usuario userSession = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			service.alterarSenha(userSession, form);
			model.addAttribute("msg", messages.getMessage("alterar-senha.sucesso", null, null));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return alterarSenhaForm(model);
	}

}
