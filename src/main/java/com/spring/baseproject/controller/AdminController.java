package com.spring.baseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.baseproject.entity.User;
import com.spring.baseproject.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

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
		model.addAttribute("roles", userService.rolesDisponiveiss(user));
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

	@RequestMapping(value = "/admin/user/{userId}/delete/role/{roleId}", method = RequestMethod.GET)
	public String userRoleDelete(@PathVariable Long userId, @PathVariable Long roleId, Model model) {
		userService.deleteRoleBYId(roleId);
		return userForm(userId, model);
	}

	@RequestMapping(value = "/admin/user/{userId}/add/role/{roleName}", method = RequestMethod.GET)
	public String userRoleAdd(@PathVariable Long userId, @PathVariable String roleName, Model model) {
		userService.addRole(userId, roleName);
		return userForm(userId, model);
	}
	
	@RequestMapping(params = "action1")
	public void action1() {
		System.out.println("voltar");
		
	}

}
