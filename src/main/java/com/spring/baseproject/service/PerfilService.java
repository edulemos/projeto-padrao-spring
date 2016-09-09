package com.spring.baseproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.spring.baseproject.Exception.BussinesException;
import com.spring.baseproject.entity.Perfil;
import com.spring.baseproject.entity.Role;
import com.spring.baseproject.repository.PerfilRepository;
import com.spring.baseproject.repository.RoleRepository;
import com.spring.baseproject.util.Util;

@Service
public class PerfilService extends Util {

	@Autowired
	PerfilRepository perfilRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MessageSource messages;

	public List<Perfil> listarPerfis() {
		return perfilRepository.findAll();
	}

	public Perfil findBYId(Long id) {
		return perfilRepository.findOne(id);
	}

	public void deleteRolePerfil(Long perfilId, Long roleId) {

		Perfil perfil = perfilRepository.findOne(perfilId);

		Role roleRemovida = new Role();
		roleRemovida.setId(roleId);

		perfil.getRoles().remove(roleRemovida);

		perfilRepository.save(perfil);

	}

	public void adicionarRolePerfil(Long perfilId, Long roleId) {

		Perfil perfil = perfilRepository.findOne(perfilId);

		Role roleIncluida = new Role();
		roleIncluida.setId(roleId);

		perfil.getRoles().add(roleIncluida);

		perfilRepository.save(perfil);

	}

	public void salvarPerfil(Perfil perfil) {

		Perfil perfilAux = perfilRepository.findPerfilByNome(perfil.getNome());

		boolean nomeJaCadastrado = perfilAux != null;
		boolean novoPerfil = perfil.getId() == null;
		boolean mesmoPerfil = !novoPerfil && nomeJaCadastrado && perfil.getId().equals(perfilAux.getId());

		if ((nomeJaCadastrado && novoPerfil) || (nomeJaCadastrado && !novoPerfil && !mesmoPerfil)) {

			throw new BussinesException(messages.getMessage("perfil-form.msg.nomeemuso", null, null));

		}

		if (!novoPerfil) {

			perfilAux = perfilRepository.findOne(perfil.getId());

			List<Role> roles = perfilAux.getRoles();

			perfil.setRoles(null != roles ? roles : null);
		}

		perfilRepository.save(perfil);

	}

	public List<Perfil> pesquisar(String textotPesquisa) {

		return perfilRepository.pesquisar(textotPesquisa);

	}

	public void deleteBYId(Long id) {

		Integer numAssociacoes = perfilRepository.buscarAssociacoesPerfil(id);

		List<Object> prmMsg = new ArrayList<Object>();
		prmMsg.add(numAssociacoes);

		if (numAssociacoes > 0) {
			throw new BussinesException(messages.getMessage("perfil-list.msg.perfilemuso", prmMsg.toArray(), null));
		}

		Perfil perfil = perfilRepository.findOne(id);
		perfil.getRoles().clear();

		perfilRepository.save(perfil);

		perfilRepository.delete(id);

	}

	public List<Role> rolesDisponiveisPerfil(Perfil perfil) {
		List<Role> roles = new ArrayList<Role>();
		String userRoles = "";
		if (null != perfil && !perfil.getRoles().isEmpty()) {
			for (Role p : perfil.getRoles()) {
				userRoles += p.getAuthority() + "#";
			}
		}

		List<Role> allRoles = roleRepository.findAll();

		for (Role r : allRoles) {
			if (userRoles.contains(r.getNome())) {
				continue;
			}
			roles.add(r);
		}

		return roles;
	}

	public String rootUrl(HttpServletRequest request) {
		String path = null;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = ((HttpServletRequest) request);
			path = req.getRequestURL().toString().replace(req.getServletPath(), "");
		}
		return path;
	}

	public void deletarTodasRoles(Long perfilId) {
		Perfil perfil = perfilRepository.findOne(perfilId);
		perfil.getRoles().clear();
		perfilRepository.save(perfil);
	}

	public void deleteRole(Long perfilId, Long roleId) {
		Role role = new Role();
		role.setId(roleId);
		Perfil perfil = perfilRepository.findOne(perfilId);
		perfil.getRoles().remove(role);
		perfilRepository.save(perfil);
	}

	public void adicionarTodasRoles(Long perfilId) {
		Perfil perfil = perfilRepository.findOne(perfilId);
		List<Role> allRoles = roleRepository.findAll();
		perfil.setRoles(allRoles);
		perfilRepository.save(perfil);
	}

	public void addRole(Long perfilId, Long roleId) {
		Role role = new Role();
		role.setId(roleId);
		Perfil perfil = perfilRepository.findOne(perfilId);
		perfil.getRoles().add(role);
		perfilRepository.save(perfil);

		
	}
	
	

}
