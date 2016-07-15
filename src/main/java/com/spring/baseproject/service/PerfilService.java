package com.spring.baseproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.baseproject.entity.Perfil;
import com.spring.baseproject.entity.Role;
import com.spring.baseproject.repository.PerfilRepository;
import com.spring.baseproject.util.Util;

@Service
public class PerfilService extends Util {

	@Autowired
	PerfilRepository perfilRepository;

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

}
