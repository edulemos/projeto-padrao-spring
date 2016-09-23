package com.spring.baseproject.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.baseproject.config.SpringDataConfig;
import com.spring.baseproject.config.SpringMvcConfig;
import com.spring.baseproject.config.SpringSecurityConfig;
import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.repository.UserRepository;
import com.spring.baseproject.test.util.TestBuild;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringSecurityConfig.class, SpringMvcConfig.class, SpringDataConfig.class })
public class LoginControllerTest extends TestBuild{
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	
	/** TESTE DE UMA SOLICITACAO DE RECUPERAR SENHA VALIDA - STATUS OK*/
	@Test
	public void testSolicitarRecuperacaoSenhaOK() throws Exception {
		
		Usuario testUser = getTestUser();
		repository.save(testUser);
		testUsers.add(testUser);
		
		this.mockMvc.perform(post("/login/recuperarSenha/enviarEmail")				
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", testUser.getEmail()))
				.andExpect(view().name("home/recuperar-senha"))
				.andExpect(model().attribute("msg", messages.getMessage("recuperar-senha.msg.emailenviado", null, null)));
		
	}
	
	/** TESTE UMA SOLICITACAO DE RECUPERAR SENHA COM UM EMAIL NAO CADASTRADO - STATUS NOK*/
	@Test
	public void testSolicitarRecuperacaoSenhaEmailNaoCadastrado() throws Exception {

		String emailNaoCadastrado = "emailnaocadastrado@email.com";

		this.mockMvc.perform(post("/login/recuperarSenha/enviarEmail")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", emailNaoCadastrado))
				.andExpect(view().name("home/recuperar-senha"))
				.andExpect(model().attribute("error", messages.getMessage("recuperar-senha.msg.emailnaocadastrado", null, null)));
	}
	
	/** TESTE CHAVE VALIDA DO LINK ENVIADO POR EMAIL - STATUS OK */
	@Test
	public void testResetarSenhaPageOK() throws Exception {
		
		String key = encryptMD5(EMAIL_USUARIO + CHAVE_MD5 + EMAIL_USUARIO);
		String getUrl = "/login/resetarSenha/"+EMAIL_USUARIO+"/"+key;
		
		this.mockMvc.perform(get(getUrl))				
		.andExpect(view().name("home/resetar-senha"));
	}
	
	/** TESTE CHAVE INVALIDA DO LINK ENVIADO POR EMAIL - STATUS NOK */
	@Test
	public void testResetarSenhaPageNOK() throws Exception {
		
		String key = encryptMD5(EMAIL_USUARIO + CHAVE_MD5_ERRADA + EMAIL_USUARIO);
		String getUrl = "/login/resetarSenha/"+EMAIL_USUARIO+"/"+key;
		
		this.mockMvc.perform(get(getUrl))				
				.andExpect(view().name("home/recuperar-senha"))
				.andExpect(model().attribute("error", messages.getMessage("recuperar-senha.msg.chaveinvalida", null, null)));
	}

	/** TESTE CHAVE VALIDA PAGINA DE RESETAR SENHA - STATUS OK */
	@Test
	public void testResetarSenhaOK() throws Exception {
		
		Usuario testUser = getTestUser();
		repository.save(testUser);
		testUsers.add(testUser);
		
		String senhaNova = "321321";
		String senhaNovaCripto = encryptMD5(senhaNova);

		String key = encryptMD5(testUser.getEmail() + CHAVE_MD5 + testUser.getEmail());
				
		this.mockMvc.perform(post("/login/resetarSenha/salvar")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", testUser.getEmail())
				.param("key", key)
				.param("senha", senhaNova))
				.andExpect(view().name("home/login"))
				.andExpect(model().attribute("msg", messages.getMessage("recuperar-senha.sucesso", null, null)));
		
		String senhaBanco = getSenhaCripto(testUser.getEmail());		
		
		Assert.assertEquals(senhaNovaCripto, senhaBanco);
		
	}
	
	/** TESTE CHAVE INVALIDA PAGINA DE RESETAR SENHA - STATUS NOK */
	@Test
	public void testResetarSenhaNOK() throws Exception {
		
		Usuario testUser = getTestUser();
		repository.save(testUser);
		testUsers.add(testUser);
		
		String outroEmail = "outro@email.com";
		String senhaNova = "321321";		
		String key = encryptMD5(testUser.getEmail() + CHAVE_MD5 + testUser.getEmail());
		
		this.mockMvc.perform(post("/login/resetarSenha/salvar")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", outroEmail)
				.param("key", key)
				.param("senha", senhaNova))
				.andExpect(view().name("home/recuperar-senha"))
				.andExpect(model().attribute("error", messages.getMessage("recuperar-senha.msg.chaveinvalida", null, null)));
	}
	
	
	@After
	public void deletarDadosDeTeste() throws ClassNotFoundException, SQLException{
		for (Usuario usuario : testUsers) {
			deleteUsuarioDeTeste(usuario.getEmail());
		}
	}
	
	
}
