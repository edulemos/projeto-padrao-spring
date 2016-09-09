package com.spring.baseproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.baseproject.entity.AjaxResponseBody;
import com.spring.baseproject.entity.Perfil;
import com.spring.baseproject.entity.Role;
import com.spring.baseproject.service.PerfilService;

@Controller
@RequestMapping("/perfis/*")
public class PerfilController {

	@Autowired
	private PerfilService service;

	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value = "/rolesdiponiveis/perfil/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Role> rolesDisponiveisJSON(@PathVariable Long id) {
		Perfil perfil = id != null ? service.findBYId(id) : null;
		return service.rolesDisponiveisPerfil(perfil);
	}

	@RequestMapping(value = "/rolesAssociadas/perfil/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Role> rolesAssociadosJSON(@PathVariable Long id) {
		List<Role> rolesDisponiveis = new ArrayList<Role>();
		Perfil perfil = id != null ? service.findBYId(id) : null;
		if (null != perfil) {
			rolesDisponiveis = perfil.getRoles();
		}
		return rolesDisponiveis;
	}
	
	@RequestMapping(value = "/{perfilId}/delete/todasroles", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody AjaxResponseBody deletarTodasRoles(@PathVariable Long perfilId) {
		try {
			service.deletarTodasRoles(perfilId);
			return new AjaxResponseBody(messages.getMessage("perfil-form.msg.todasrolesremovidas", null, null));
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{perfilId}/delete/role/{roleId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody AjaxResponseBody deletarRole(@PathVariable Long perfilId, @PathVariable Long roleId) {
		try {
			service.deleteRole(perfilId, roleId);
			return new AjaxResponseBody(messages.getMessage("perfil-form.msg.roleremovida", null, null));
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{perfilId}/add/todasroles", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody AjaxResponseBody adicionarTodasRoles(@PathVariable Long perfilId) {
		try {
			service.adicionarTodasRoles(perfilId);
			return new AjaxResponseBody(messages.getMessage("perfil-form.msg.todasrolesadicionadas", null, null));
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{perfilId}/add/role/{roleId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody incluirRoleJSON(@PathVariable Long perfilId, @PathVariable Long roleId) {
		try {
			service.addRole(perfilId, roleId);
			return new AjaxResponseBody(messages.getMessage("perfil-form.msg.roleadicionada", null, null));
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}

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
