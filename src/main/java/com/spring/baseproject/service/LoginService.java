package com.spring.baseproject.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.spring.baseproject.Exception.BussinesException;
import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.repository.UserRepository;
import com.spring.baseproject.util.EmailUtil;
import com.spring.baseproject.util.Util;

@Service
public class LoginService extends Util {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messages;

	@Autowired
	private EmailUtil emailUtil;

	public void recuperarSenha(String email, String path) {

		Usuario userMail = userRepository.findUsuarioByEmail(email);

		if (userMail == null) {
			throw new BussinesException(messages.getMessage("recuperar-senha.msg.emailnaocadastrado", null, null));
		}

		HashMap<String, String> parametros = new HashMap<String, String>();
		parametros.put("$P{email}", email);
		parametros.put("$P{path}", path);
		parametros.put("$P{key}", encryptMD5(email + path + email));

		emailUtil.setAssunto("Recuperar Senha");
		emailUtil.setDestinatario(email);
		emailUtil.setNomeTemplate("recuperar-senha-email.html");
		emailUtil.setParametros(parametros);
		emailUtil.enviarEmailHtml();
	}

	public void verifcarKey(String email, String key, String path) {
		String encryptKey = encryptMD5(email + path + email);
		if (!encryptKey.equals(key)) {
			throw new BussinesException(messages.getMessage("recuperar-senha.msg.chaveinvalida", null, null));
		}
	}

	public void alterarSenha(CadastroForm form, String key, String path) {
		verifcarKey(form.getEmail(), key, path);
		Usuario user = userRepository.findUsuarioByEmail(form.getEmail());
		user.setPassword(encryptMD5(form.getSenha()));
		userRepository.save(user);
	}

}
