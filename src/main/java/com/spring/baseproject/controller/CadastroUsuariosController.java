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
import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.service.UsuariosSistemaService;

@Controller
@RequestMapping("/usuarios/*")
public class CadastroUsuariosController {

	@Autowired
	private UsuariosSistemaService userService;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/perfisdiponiveis/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Perfil> perfisDisponiveisJSON(@PathVariable Long id) {
		Usuario user = id != null ? userService.findBYId(id) : null;
		List<Perfil> perfisDisponiveis = userService.perfisDisponiveis(user);
		return perfisDisponiveis;
	}

	@RequestMapping(value = "/perfisassociados/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Perfil> perfisAssociadosJSON(@PathVariable Long id) {
		List<Perfil> perfisDisponiveis = new ArrayList<Perfil>();
		Usuario user = id != null ? userService.findBYId(id) : null;
		if (null != user) {
			perfisDisponiveis = user.getPerfis();
		}
		return perfisDisponiveis;
	}
	
	@RequestMapping(value = "/{userId}/add/perfil/{perfilId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody incluirRoleJSON(@PathVariable Long userId, @PathVariable Long perfilId) {
		try {
			userService.addPerfil(userId, perfilId);
			return  new AjaxResponseBody("Perfil Adicionado");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}
		
	@RequestMapping(value = "/{userId}/delete/perfil/{perfilId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody AjaxResponseBody deletarPerfil(@PathVariable Long userId, @PathVariable Long perfilId) {
		try {
			userService.deletePerfil(userId, perfilId);
			return new AjaxResponseBody("Perfil Removido");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{userId}/delete/todosperfis", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody AjaxResponseBody deletarTodosPerfis(@PathVariable Long userId) {
		try {
			userService.deletarTodosPerfis(userId);
			return new AjaxResponseBody("Todos Perfis Apagados");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/{userId}/add/todosperfis", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody AjaxResponseBody adicionarTodosPerfis(@PathVariable Long userId) {
		try {
			userService.adicionarTodosPerfis(userId);
			return new AjaxResponseBody("Todos Perfis Adicionados");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseBody(e.getMessage());
		}
	}

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

	

	

}
