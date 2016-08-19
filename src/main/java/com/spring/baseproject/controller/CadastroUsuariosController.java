package com.spring.baseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.service.UsuariosSistemaService;

@Controller
@RequestMapping("/usuarios/*")
public class CadastroUsuariosController {

	@Autowired
	private UsuariosSistemaService userService;

	@Autowired
	private MessageSource messages;

	@RequestMapping("/list")
	public String listar(Model model) {
		try {
			model.addAttribute("listaUsuarios", userService.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "usuarios/usuarios-list";
	}

	@RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
	public String formulario(@PathVariable("id") Long id, Model model) {
		try {
			Usuario user = id != null ? userService.findBYId(id) : null;
			model.addAttribute("perfis", userService.perfisDisponiveis(user));
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "usuarios/usuarios-form";
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public String pesquisar(String textotPesquisa, Model model) {
		try {
			if (!textotPesquisa.isEmpty()) {
				model.addAttribute("listaUsuarios", userService.find(textotPesquisa));
				return "usuarios/usuarios-list";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return listar(model);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String salvar(Usuario user, Model model) {
		try {
			boolean novo = user.getId() == null;
			userService.salvar(user);
			String str = !novo ? messages.getMessage("usuarios-list.usuario.alterado", null, null) : messages.getMessage(
					"usuarios-list.usuario.salvo", null, null);
			model.addAttribute("msg", str);
			return listar(model);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			model.addAttribute("user", user);
			return formulario(user.getId(), model);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletar(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("msg", messages.getMessage("usuarios-list.usuario.deletado", null, null));
			userService.deleteBYId(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return listar(model);
	}

	@RequestMapping(value = "/{userId}/delete/perfil/{perfilId}", method = RequestMethod.GET)
	public String deletarRole(@PathVariable Long userId, @PathVariable Long perfilId, Model model) {
		try {
			userService.deletePerfil(userId, perfilId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return formulario(userId, model);
	}

	@RequestMapping(value = "/{userId}/add/perfil/{perfilId}", method = RequestMethod.GET)
	public String incluirRole(@PathVariable Long userId, @PathVariable Long perfilId, Model model) {
		try {
			userService.addPerfil(userId, perfilId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return formulario(userId, model);
	}

	

}
