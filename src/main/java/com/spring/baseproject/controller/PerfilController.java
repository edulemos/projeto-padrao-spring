package com.spring.baseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.baseproject.entity.Perfil;
import com.spring.baseproject.service.PerfilService;

@Controller
@RequestMapping("/perfis/*")
public class PerfilController {

	@Autowired
	private PerfilService service;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/list")
	public String listarPerfis(Model model) {
		try {
			model.addAttribute("listaPerfis", service.listarPerfis());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "perfil/perfil-list";
	}

	@RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
	public String perfilForm(@PathVariable("id") Long id, Model model) {
		try {
			Perfil perfil = id != null ? service.findBYId(id) : null;
			model.addAttribute("roles", service.rolesDisponiveisPerfil(perfil));
			model.addAttribute("perfil", perfil);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return "perfil/perfil-form";
	}

	@RequestMapping(value = "/perfil/{perfilId}/delete/role/{roleId}", method = RequestMethod.GET)
	public String deletarRolePerfil(@PathVariable Long perfilId, @PathVariable Long roleId, Model model) {
		try {
			service.deleteRolePerfil(perfilId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return perfilForm(perfilId, model);
	}

	@RequestMapping(value = "/perfil/{perfilId}/add/role/{roleId}", method = RequestMethod.GET)
	public String adicionarRolePerfil(@PathVariable Long perfilId, @PathVariable Long roleId, Model model) {
		try {
			service.adicionarRolePerfil(perfilId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return perfilForm(perfilId, model);
	}

	@RequestMapping(value = "/save")
	public String salvarPerfil(Perfil perfil, Model model) {
		try {
			boolean novo = perfil.getId() == null;
			service.salvarPerfil(perfil);
			String str = !novo ? messages.getMessage("perfil-list.perfil.alterado", null, null) : messages.getMessage(
					"perfil-list.perfil.salvo", null, null);
			model.addAttribute("msg", str);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			return perfilForm(perfil.getId(), model);
		}
		return listarPerfis(model);
	}

	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public String pesquisarPerfil(String textotPesquisa, Model model) {
		try {
			if (!textotPesquisa.isEmpty()) {
				model.addAttribute("listaPerfis", service.pesquisar(textotPesquisa));
				return "perfil/perfil-list";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return listarPerfis(model);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletarPerfil(@PathVariable Long id, Model model) {
		try {
			service.deleteBYId(id);
			model.addAttribute("msg", messages.getMessage("perfil-list.perfil.deletado", null, null));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}
		return listarPerfis(model);
	}

}
