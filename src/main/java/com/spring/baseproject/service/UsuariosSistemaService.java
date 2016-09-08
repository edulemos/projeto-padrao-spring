package com.spring.baseproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.spring.baseproject.Exception.BussinesException;
import com.spring.baseproject.entity.Perfil;
import com.spring.baseproject.entity.Role;
import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.repository.PerfilRepository;
import com.spring.baseproject.repository.RoleRepository;
import com.spring.baseproject.repository.UserRepository;
import com.spring.baseproject.util.EmailUtil;
import com.spring.baseproject.util.Util;

@Service
public class UsuariosSistemaService extends Util {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MessageSource messages;

	@Autowired
	private EmailUtil emailUtil;

	public List<Usuario> findAll() {
		return userRepository.findAll();
	}

	public List<Usuario> find(String textotPesquisa) {
		return userRepository.findUsers(textotPesquisa, textotPesquisa);
	}

	public Usuario findUserByEmail(String userName) {
		return userRepository.findUsuarioByEmail(userName);
	}

	public Usuario findBYId(Long id) {
		return userRepository.findOne(id);
	}

	public void deleteBYId(Long id) {
		userRepository.delete(id);
	}

	public void salvar(Usuario user) {

		Usuario userAux = userRepository.findUsuarioByEmail(user.getEmail());

		boolean emailJaCadastrado = userAux != null;
		boolean novoUsuario = user.getId() == null;
		boolean mesmoUsuario = !novoUsuario && emailJaCadastrado && user.getId().equals(userAux.getId());

		if (emailJaCadastrado && novoUsuario) {
			throw new BussinesException(messages.getMessage("usuarios-form.msg.emailemuso", null, null));
		} else if (emailJaCadastrado && !novoUsuario && !mesmoUsuario) {
			throw new BussinesException(messages.getMessage("usuarios-form.msg.emailemuso", null, null));
		}

		if (novoUsuario) {

			user.setPassword(encryptMD5(user.getPassword()));

		} else {

			//se o email foi alterado tem que buscar pelo ID
			if (null == userAux) {
				userAux = userRepository.findOne(user.getId());
			}

			user.setPerfis(userAux.getPerfis());

		}

		userRepository.save(user);
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

	public List<Role> listarRoles() {
		return roleRepository.findAll();
	}

	public void deleteRoleBYId(Long id) {
		roleRepository.delete(id);
	}

	public void addPerfil(Long userId, Long perfilId) {
		Perfil p = new Perfil();
		p.setId(perfilId);
		Usuario user = userRepository.findOne(userId);
		user.getPerfis().add(p);
		userRepository.save(user);
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

	public List<Perfil> perfisDisponiveis(Usuario user) {
		List<Perfil> perfisDiposniveis = perfilRepository.findAll();
		if (null == user)
			return perfisDiposniveis;
		for (Perfil perfil : user.getPerfis()) {
			if (perfisDiposniveis.contains(perfil)) {
				perfisDiposniveis.remove(perfil);
			}
		}
		return perfisDiposniveis;
	}

	public void deletePerfil(Long userId, Long perfilId) {
		Perfil p = new Perfil();
		p.setId(perfilId);
		Usuario user = userRepository.findOne(userId);
		user.getPerfis().remove(p);
		userRepository.save(user);

	}

	public void deletarTodosPerfis(Long userId) {
		Usuario user = findBYId(userId);
		user.getPerfis().clear();
		userRepository.save(user);
	}

	public void adicionarTodosPerfis(Long userId) {
		Usuario user = findBYId(userId);
		user.getPerfis().clear();
		userRepository.save(user);
		
		List<Perfil> todosPerfis = perfilRepository.findAll();
		user.setPerfis(todosPerfis);
		userRepository.save(user);
	}

}
