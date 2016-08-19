package com.spring.baseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.baseproject.Exception.BussinesException;
import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.repository.UserRepository;
import com.spring.baseproject.util.Util;

@Service
public class UsuarioService extends Util {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messages;

	public void salvar(Usuario usuarioAlterado) {

		Usuario usuarioLogin = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean alterouEmail = !usuarioLogin.getEmail().equals(usuarioAlterado.getEmail());
		Usuario userAux = null;

		if (alterouEmail) {

			userAux = userRepository.findUsuarioByEmail(usuarioAlterado.getEmail());

			if (null != userAux) {				
				throw new BussinesException(messages.getMessage("usuarios-form.msg.emailemuso", null, null));				
			} 

		}

		usuarioAlterado.setPerfis(usuarioLogin.getPerfis());

		userRepository.save(usuarioAlterado);
	}

	public void alterarSenha(Usuario userSession, CadastroForm form) {

		String senhaAtualSistema = userSession.getPassword();
		String senhaAtualDigitada = encryptMD5(form.getSenha());
		String novaSenha = encryptMD5(form.getConfirmaSenha());

		if (!senhaAtualSistema.equals(senhaAtualDigitada)) {
			throw new BussinesException(messages.getMessage("alterar-senha.msg.senhaatualinvalida", null, null));
		}

		userSession.setPassword(novaSenha);

		userRepository.save(userSession);

	}

}
