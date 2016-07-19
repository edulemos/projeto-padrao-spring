package com.spring.baseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.baseproject.entity.Perfil;
import com.spring.baseproject.entity.User;
import com.spring.baseproject.service.PerfilService;
import com.spring.baseproject.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private MessageSource messages;

	@RequestMapping("/admin/user/list")
	public String userList(Model model) {
		model.addAttribute("listaUsuarios", userService.findAll());
		return "admin/admin-user-list";
	}

	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
	public String userForm(@PathVariable("id") Long id, Model model) {
		User user = id != null ? userService.findBYId(id) : null;
		model.addAttribute("perfis", userService.perfisDisponiveis(user));
		model.addAttribute("user", user);
		return "admin/admin-user-form";
	}

	@RequestMapping(value = "/admin/user/find", method = RequestMethod.POST)
	public String userPesquisar(String textotPesquisa, Model model) {
		if (!textotPesquisa.isEmpty()) {
			model.addAttribute("listaUsuarios", userService.find(textotPesquisa));
			return "admin/admin-user-list";
		}
		return userList(model);
	}

	@RequestMapping(value = "/admin/user/save", method = RequestMethod.POST)
	public String userSave(User user, Model model) {
		try {
			boolean novo = user.getId() == null;
			userService.salvar(user);
			String str = !novo ? messages.getMessage("admin-user-list.usuario.alterado", null, null) : messages.getMessage(
					"admin-user-list.usuario.salvo", null, null);
			model.addAttribute("msg", str);
			return userList(model);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("user", user);
			return userForm(user.getId(), model);
		}
	}

	@RequestMapping(value = "/admin/user/delete/{id}", method = RequestMethod.GET)
	public String userDelete(@PathVariable Long id, Model model) {
		model.addAttribute("msg", messages.getMessage("admin-user-list.usuario.deletado", null, null));
		userService.deleteBYId(id);
		return userList(model);
	}

	@RequestMapping(value = "/admin/user/{userId}/delete/perfil/{perfilId}", method = RequestMethod.GET)
	public String userRoleDelete(@PathVariable Long userId, @PathVariable Long perfilId, Model model) {
		userService.deletePerfil(userId, perfilId);
		return userForm(userId, model);
	}

	@RequestMapping(value = "/admin/user/{userId}/add/perfil/{perfilId}", method = RequestMethod.GET)
	public String userRoleAdd(@PathVariable Long userId, @PathVariable Long perfilId, Model model) {
		userService.addPerfil(userId, perfilId);
		return userForm(userId, model);
	}

	/** PERFIL */

	@RequestMapping(value = "/admin/perfil/list")
	public String listarPerfis(Model model) {
		model.addAttribute("listaPerfis", perfilService.listarPerfis());
		return "admin/admin-perfil-list";
	}

	@RequestMapping(value = "/admin/perfil/{id}", method = RequestMethod.GET)
	public String perfilForm(@PathVariable("id") Long id, Model model) {
		Perfil perfil = id != null ? perfilService.findBYId(id) : null;
		model.addAttribute("roles", userService.rolesDisponiveisPerfil(perfil));
		model.addAttribute("perfil", perfil);
		return "admin/admin-perfil-form";
	}

	@RequestMapping(value = "/admin/perfil/{perfilId}/delete/role/{roleId}", method = RequestMethod.GET)
	public String deleteRolePerfil(@PathVariable Long perfilId, @PathVariable Long roleId, Model model) {
		perfilService.deleteRolePerfil(perfilId, roleId);
		return perfilForm(perfilId, model);
	}

	@RequestMapping(value = "/admin/perfil/{perfilId}/add/role/{roleId}", method = RequestMethod.GET)
	public String adicionarRolePerfil(@PathVariable Long perfilId, @PathVariable Long roleId, Model model) {
		perfilService.adicionarRolePerfil(perfilId, roleId);
		return perfilForm(perfilId, model);
	}

	@RequestMapping(value = "/admin/perfil/save")
	public String salvarPerfil(Perfil perfil, Model model) {
		try {
			boolean novo = perfil.getId() == null;
			String str = !novo ? messages.getMessage("admin-perfil-list.perfil.alterado", null, null) : messages.getMessage(
					"admin-perfil-list.perfil.salvo", null, null);
			model.addAttribute("msg", str);
			perfilService.salvarPerfil(perfil);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return listarPerfis(model);
	}

	@RequestMapping(value = "/admin/perfil/pesquisar", method = RequestMethod.POST)
	public String pesquisarPerfil(String textotPesquisa, Model model) {
		if (!textotPesquisa.isEmpty()) {
			model.addAttribute("listaPerfis", perfilService.pesquisar(textotPesquisa));
			return "admin/admin-perfil-list";
		}
		return userList(model);
	}

	@RequestMapping(value = "/admin/perfil/delete/{id}", method = RequestMethod.GET)
	public String deletarPerfil(@PathVariable Long id, Model model) {
		try {
			perfilService.deleteBYId(id);
			model.addAttribute("msg", messages.getMessage("admin-perfil-list.perfil.deletado", null, null));
		} catch (Exception e) {			
			model.addAttribute("error", e.getMessage());
		}
		return listarPerfis(model);
	}

}
