package com.spring.baseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.spring.baseproject.Exception.BussinesException;
import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.repository.UserRepository;
import com.spring.baseproject.util.Util;

@Service
public class CadastroService extends Util {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messages;

	public void salvar(CadastroForm form) {

		boolean emailJaUtilizado = null != userRepository.findUsuarioByEmail(form.getEmail());		

		if (emailJaUtilizado) {
			throw new BussinesException(messages.getMessage("cadastro.msg.emailemuso", null, null));
		}

		Usuario usuario = new Usuario();
		usuario.setEmail(form.getEmail());
		usuario.setPassword(encryptMD5(form.getSenha()));
		usuario.setName(form.getNome());

		userRepository.save(usuario);

	}

}
