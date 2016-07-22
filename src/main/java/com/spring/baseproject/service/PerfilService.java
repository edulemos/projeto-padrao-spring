package com.spring.baseproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.spring.baseproject.Exception.BussinesException;
import com.spring.baseproject.entity.Perfil;
import com.spring.baseproject.entity.Role;
import com.spring.baseproject.repository.PerfilRepository;
import com.spring.baseproject.util.Util;

@Service
public class PerfilService extends Util {

	@Autowired
	PerfilRepository perfilRepository;

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

			throw new BussinesException(messages.getMessage("admin-perfil-form.msg.nomeemuso", null, null));

		}
		
		if (!novoPerfil) {
			
			perfilAux = perfilRepository.findOne(perfil.getId());
			
			List<Role> roles = perfilAux.getRoles();
			
			perfil.setRoles(null != roles ? roles : null);
		}

		perfilRepository.save(perfil);

	}

	public Object pesquisar(String textotPesquisa) {

		return perfilRepository.pesquisar(textotPesquisa);

	}

	public void deleteBYId(Long id) {
		
		Integer numAssociacoes = perfilRepository.buscarAssociacoesPerfil(id);
		
		List <Object> prmMsg = new ArrayList<Object>();
		prmMsg.add(numAssociacoes);
		
		if(numAssociacoes > 0 ){
			throw new BussinesException(messages.getMessage("admin-perfil-list.msg.perfilemuso", prmMsg.toArray(), null));
		}
		
		Perfil perfil = perfilRepository.findOne(id);
		perfil.getRoles().clear();
		
		perfilRepository.save(perfil);
		
		perfilRepository.delete(id);
		
	}

}
