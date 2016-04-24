package com.spring.baseproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.spring.baseproject.Exception.BussinesException;
import com.spring.baseproject.entity.Role;
import com.spring.baseproject.entity.User;
import com.spring.baseproject.enumeration.RolesEnum;
import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.repository.RoleRepository;
import com.spring.baseproject.repository.UserRepository;
import com.spring.baseproject.util.EmailUtil;
import com.spring.baseproject.util.Util;

@Service
public class UserService extends Util {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MessageSource messages;
	
	@Autowired
	private EmailUtil  emailUtil;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> find(String textotPesquisa) {
		return userRepository.findUsers(textotPesquisa, textotPesquisa);
	}

	public User findUserByEmail(String userName) {
		return userRepository.findUserByEmail(userName);
	}

	public User findBYId(Long id) {
		return userRepository.findOne(id);
	}

	public void deleteBYId(Long id) {
		userRepository.delete(id);
	}

	public void cadastrarUsuario(CadastroForm form) {

		User user;

		user = userRepository.findUserByEmail(form.getEmail());

		if (null != user) {
			throw new BussinesException(messages.getMessage("user.email.emuso", null, null));
		}

		user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(encryptMD5(form.getSenha()));
		user.setName(form.getNome());
		user.setAuthorities(Arrays.asList(new Role(form.getRole())));

		userRepository.save(user);

	}

	public void salvar(User user) {

		User userMail = userRepository.findUserByEmail(user.getEmail());

		boolean emailJaCadastrado = userMail != null;
		boolean novoUsuario = user.getId() == null;
		boolean mesmoUsuario = !novoUsuario && emailJaCadastrado && user.getId().equals(userMail.getId());

		if (emailJaCadastrado && novoUsuario) {
			throw new BussinesException(messages.getMessage("user.email.emuso", null, null));
		} else if (emailJaCadastrado && !novoUsuario && !mesmoUsuario) {
			throw new BussinesException(messages.getMessage("user.email.emuso", null, null));
		}

		if (novoUsuario) {
			user.setPassword(encryptMD5(user.getPassword()));
		} else {
			List<Role> roles = roleRepository.findRoleByUser(user);
			user.setAuthorities(roles);
		}

		userRepository.save(user);
	}

	public void alterarSenha(User userSession, CadastroForm form) {

		String senhaAtualSistema = userSession.getPassword();
		String senhaAtualDigitada = encryptMD5(form.getSenha());
		String novaSenha = encryptMD5(form.getConfirmaSenha());

		if (!senhaAtualSistema.equals(senhaAtualDigitada)) {
			throw new BussinesException(messages.getMessage("user.password.error", null, null));
		}

		userSession.setPassword(novaSenha);

		userRepository.save(userSession);

	}

	public List<Role> allRoles() {
		List<Role> roles = new ArrayList<Role>();
		Role role = null;
		for (RolesEnum r : RolesEnum.values()) {
			role = new Role();
			role.setName(r.getValue());
			role.setLabel(r.name());
			roles.add(role);
		}
		return roles;
	}

	public void deleteRoleBYId(Long id) {
		roleRepository.delete(id);
	}

	public void addRole(Long userId, String roleName) {
		Role role = new Role();
		role.setName(roleName);
		User user = new User();
		user.setId(userId);
		role.setUser(user);
		roleRepository.save(role);
	}

	public List<Role> userRoles(User user) {
		List<Role> roles = new ArrayList<Role>();
		String userRoles = "";
		if (null != user && !user.getAuthorities().isEmpty()) {
			for (GrantedAuthority g : user.getAuthorities()) {
				userRoles += g.getAuthority() + "#";
			}
		}
		Role role = null;
		for (RolesEnum r : RolesEnum.values()) {
			if (userRoles.contains(r.getValue())) {
				continue;
			}
			role = new Role();
			role.setName(r.getValue());
			role.setLabel(r.name());
			roles.add(role);
		}

		return roles;
	}

	public void recuperarSenha(String email, String path) {
		
		User userMail = userRepository.findUserByEmail(email);
	
		if (userMail == null) {
			throw new BussinesException(messages.getMessage("email.recuperar.nao.cadastrado", null, null));
		} 
		
		HashMap<String, String> parametros = new HashMap<String, String>();
		parametros.put("email", email);		
		parametros.put("path", path);		
		parametros.put("key", encryptMD5(email+path+email));		
		
		emailUtil.setAssunto("Recuperar Senha");
		emailUtil.setDestinatario(email);		
		emailUtil.setNomeTemplate("emailRecuperarSenha.html");	
		emailUtil.setParametros(parametros);		
		emailUtil.enviarEmailHtml();
	}

	public void verifcarKey(String email, String key, String path) {
		String encryptKey = encryptMD5(email+path+email);
		if (!encryptKey.equals(key)) {
			throw new BussinesException(messages.getMessage("email.recuperar.chave.invalida", null, null));
		}
	}

	public void alterarSenha(CadastroForm form, String key, String path) {
		verifcarKey(form.getEmail(), key, path);
		User user = userRepository.findUserByEmail(form.getEmail());
		user.setPassword(encryptMD5(form.getSenha()));
		userRepository.save(user);	
	}

}
